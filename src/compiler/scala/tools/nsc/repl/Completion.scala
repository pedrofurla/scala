/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 * @author Paul Phillips
 */


package scala.tools.nsc
package repl

import jline._
import java.util.{ List => JList }
import util.returning

object Completion {  
  def looksLikeInvocation(code: String) = (
    (code == ".") || (
      (code != null)
      &&  (code startsWith ".")
      && !(code startsWith "./")
      && !(code startsWith "..")
    )
  )
  
  object Forwarder {
    def apply(forwardTo: () => Option[CompletionAware]): CompletionAware = new CompletionAware {
      def completions(verbosity: Int) = forwardTo() map (_ completions verbosity) getOrElse Nil
      override def follow(s: String) = forwardTo() flatMap (_ follow s)
    }
  }
}
import Completion._

// REPL completor - queries supplied interpreter for valid
// completions based on current contents of buffer.
class Completion(val repl: Interpreter) extends CompletionOutput {
  // verbosity goes up with consecutive tabs
  private var verbosity: Int = 0
  def resetVerbosity() = verbosity = 0
  
  import repl.{ publicMembers, isCompletionDebug, isReplDebug }
  def DBG(msg: => Any) = if (isCompletionDebug) println(msg.toString)
  def debugging[T](msg: String): T => T = (res: T) => returning[T](res)(x => DBG(msg + x))
  
  import repl.compiler._
  import definitions.{ PredefModule, RootClass, AnyClass, AnyRefClass, ScalaPackage, JavaLangPackage }
  def CompletionAwareClass = definitions.getClass("scala.tools.nsc.repl.CompletionAware")

  def anyRefMethodsToShow = List("isInstanceOf", "asInstanceOf", "toString")
  def topLevelNever(s: String) = anyRefMethodsToShow contains s
    
  trait CompilerCompletion {
    def tp: Type
    def effectiveTp = tp match {
      case MethodType(Nil, resType) => resType
      case PolyType(Nil, resType)   => resType
      case _                        => tp
    }

    // for some reason any's members don't show up in subclasses, which
    // we need so 5.<tab> offers asInstanceOf etc.
    private def anyMembers = publicMembers(AnyClass.tpe)

    def tos(sym: Symbol) = sym.name.decode.toString
    def memberNamed(s: String) = members find (x => tos(x) == s)
    def hasMethod(s: String) = methods exists (x => tos(x) == s)
    def memberIsAware(s: String) = memberNamed(s) exists (_.tpe <:< CompletionAwareClass.tpe)
    
    // XXX we'd like to say "filterNot (_.isDeprecated)" but this causes the
    // compiler to crash for reasons not yet known.
    def members     = (publicMembers(effectiveTp) ++ anyMembers) filter (_.isPublic)
    def methods     = members filter (_.isMethod)
    def packages    = members filter (_.isPackage)
    def aliases     = members filter (_.isAliasType)

    def memberNames   = members map tos
    def methodNames   = methods map tos
    def packageNames  = packages map tos
    def aliasNames    = aliases map tos
  }

  object TypeMemberCompletion {
    def apply(name: String): TypeMemberCompletion = new TypeMemberCompletion(name)
    def apply(tp: Type): TypeMemberCompletion = {
      if (tp.typeSymbol.isPackageClass) new PackageCompletion(tp)
      else new TypeMemberCompletion(tp)
    }
    def imported(tp: Type) = new ImportCompletion(tp)
  }
  
  class TypeMemberCompletion(val tp: Type) extends CompletionAware with CompilerCompletion {
    def this(name: String) = this(definitions.getClass(name).tpe)

    def excludeEndsWith: List[String] = Nil
    def excludeStartsWith: List[String] = List("<") // <byname>, <repeated>, etc.
    def excludeNames: List[String] =
      anyref.methodNames.filterNot(anyRefMethodsToShow contains) ++ List("_root_")
    
    def methodSignatureString(sym: Symbol) = {
      def asString = new MethodSymbolOutput(sym).methodString()
      
      if (isCompletionDebug)
        repl.showAtAllPhases(asString)

      atPhase(currentRun.typerPhase)(asString)
    }
    def methodDocString(sym: Symbol): String = {
      println("methodDocString on " + sym)
      try repl.scaladoc(sym)
      catch { case x: Exception => println("methodDocString: " + x) ; throw x }
    }

    def exclude(name: String): Boolean = (
      (name contains "$") ||
      (excludeNames contains name) ||
      (excludeEndsWith exists (name endsWith _)) ||
      (excludeStartsWith exists (name startsWith _))
    )
    def filtered(xs: List[String]) = xs filterNot exclude distinct

    def completions(verbosity: Int) =
      debugging(tp + " completions ==> ")(filtered(memberNames))
    
    override def follow(s: String): Option[CompletionAware] =      
      debugging(tp + ".follow(" + s + ") ==> ")(memberNamed(s) map (x => TypeMemberCompletion(x.tpe)))
    
    override def alternativesFor(id: String): List[String] =
      debugging(id + " alternatives ==> ") {
        val ms    = members filter (x => x.isMethod && tos(x) == id)
        val alts  = ms map methodSignatureString
        val docs  = ms map methodDocString
        val all: List[String] = (alts ::: docs) filterNot (_ == "")

        if (all.nonEmpty) "" :: all else Nil
      }

    override def toString = "TypeMemberCompletion(%s)".format(tp)
  }
  
  class PackageCompletion(tp: Type) extends TypeMemberCompletion(tp) {
    override def excludeNames = anyref.methodNames
  }

  class LiteralCompletion(lit: Literal) extends TypeMemberCompletion(lit.value.tpe) {
    override def completions(verbosity: Int) = verbosity match {
      case 0    => filtered(memberNames)
      case _    => memberNames
    }
  }
  
  class ImportCompletion(tp: Type) extends TypeMemberCompletion(tp) {
    override def completions(verbosity: Int) = verbosity match {
      case 0    => filtered(members filterNot (_.isSetter) map tos)
      case _    => super.completions(verbosity)
    }
  }
  
  // not for completion but for excluding
  object anyref extends TypeMemberCompletion(AnyRefClass.tpe) { }
  
  // the unqualified vals/defs/etc visible in the repl
  object ids extends CompletionAware {
    implicit def nameListToStrings(xs: List[Name]): List[String] = xs map (_.toString)
    override def completions(verbosity: Int) = repl.termNames :+ nme.classOf
    override def follow(id: String): Option[CompletionAware] =
      repl.completionAware(id) orElse {
        repl.latest(newTermName(id)) map (r => TypeMemberCompletion(r.tpe))
      }
  }

  // wildcard imports in the repl like "import global._" or "import String._"
  private def imported = repl.wildcardImportedTypes map TypeMemberCompletion.imported

  // literal Ints, Strings, etc.
  object literals extends CompletionAware {    
    def simpleParse(code: String): Tree = {
      val unit    = new CompilationUnit(new BatchSourceFile("<console>", code))
      val scanner = new syntaxAnalyzer.UnitParser(unit)
      val tss     = scanner.templateStatSeq(false)._2

      if (tss.size == 1) tss.head else EmptyTree
    }
  
    def completions(verbosity: Int) = Nil
    
    override def follow(id: String) = simpleParse(id) match {
      case x: Literal   => Some(new LiteralCompletion(x))
      case _            => None
    }
  }

  // top level packages
  object rootClass extends TypeMemberCompletion(RootClass.tpe) { }
  // members of Predef
  object predef extends TypeMemberCompletion(PredefModule.tpe) {
    override def excludeEndsWith    = super.excludeEndsWith ++ List("Wrapper", "ArrayOps")
    override def excludeStartsWith  = super.excludeStartsWith ++ List("wrap")
    override def excludeNames       = anyref.methodNames
    
    override def exclude(name: String) = super.exclude(name) || (
      (name contains "2")
    )
    
    override def completions(verbosity: Int) = verbosity match {
      case 0    => Nil
      case _    => super.completions(verbosity)
    }
  }
  // members of scala.*
  object scalalang extends PackageCompletion(ScalaPackage.tpe) {
    def arityClasses = List("Product", "Tuple", "Function")
    def skipArity(name: String) = arityClasses exists (x => name != x && (name startsWith x))
    override def exclude(name: String) = super.exclude(name) || (
      skipArity(name)
    )
    
    override def completions(verbosity: Int) = verbosity match {
      case 0    => filtered(packageNames ++ aliasNames)
      case _    => super.completions(verbosity)
    }
  }
  // members of java.lang.*
  object javalang extends PackageCompletion(JavaLangPackage.tpe) {
    override lazy val excludeEndsWith   = super.excludeEndsWith ++ List("Exception", "Error")
    override lazy val excludeStartsWith = super.excludeStartsWith ++ List("CharacterData")
    
    override def completions(verbosity: Int) = verbosity match {
      case 0    => filtered(packageNames)
      case _    => super.completions(verbosity)
    }
  }

  // the list of completion aware objects which should be consulted
  lazy val topLevelBase: List[CompletionAware] = List(ids, rootClass, predef, scalalang, javalang, literals)
  def topLevel = topLevelBase ++ imported
  
  // the first tier of top level objects (doesn't include file completion)
  def topLevelFor(parsed: Parsed) = (topLevel flatMap (_ completionsFor parsed)) filterNot topLevelNever

  // the most recent result
  def lastResult = Forwarder(() => ids follow (repl.latestVar map (_.toString) getOrElse ""))

  def lastResultFor(parsed: Parsed) = {
    /** The logic is a little tortured right now because normally '.' is
     *  ignored as a delimiter, but on .<tab> it needs to be propagated.
     */
    val xs = lastResult completionsFor parsed
    if (parsed.isEmpty) xs map ("." + _) else xs
  }

  // chasing down results which won't parse
  def execute(line: String): Option[Any] = {
    val parsed = Parsed(line)
    def noDotOrSlash = line forall (ch => ch != '.' && ch != '/')
    
    if (noDotOrSlash) None  // we defer all unqualified ids to the repl.
    else {
      (ids executionFor parsed) orElse
      (rootClass executionFor parsed) orElse
      (FileCompletion executionFor line)
    }
  }
  
  // generic interface for querying (e.g. interpreter loop, testing)
  def completions(buf: String): List[String] =
    topLevelFor(Parsed.dotted(buf + ".", buf.length + 1))

  // jline's entry point
  lazy val jline: ArgumentCompletor =
    returning(new ArgumentCompletor(new JLineCompletion, new JLineDelimiter))(_ setStrict false)

  /** This gets a little bit hairy.  It's no small feat delegating everything
   *  and also keeping track of exactly where the cursor is and where it's supposed
   *  to end up.  The alternatives mechanism is a little hacky: if there is an empty
   *  string in the list of completions, that means we are expanding a unique
   *  completion, so don't update the "last" buffer because it'll be wrong.
   */
  class JLineCompletion extends Completor {
    // For recording the buffer on the last tab hit
    private var lastBuf: String = ""
    private var lastCursor: Int = -1

    // Does this represent two consecutive tabs?
    def isConsecutiveTabs(buf: String, cursor: Int) =
      cursor == lastCursor && buf == lastBuf

    // Longest common prefix
    def commonPrefix(xs: List[String]) =
      if (xs.isEmpty) ""
      else xs.reduceLeft(_ zip _ takeWhile (x => x._1 == x._2) map (_._1) mkString)

    // This is jline's entry point for completion.
    override def complete(_buf: String, cursor: Int, candidates: JList[String]): Int = {
      if (!repl.initLatch.hasFlipped) {
        DBG("Aborting completion, repl latch has not flipped.")
        return cursor
      }
      val buf = onull(_buf)
      verbosity = if (isConsecutiveTabs(buf, cursor)) verbosity + 1 else 0
      DBG("\ncomplete(%s, %d) last = (%s, %d), verbosity: %s".format(buf, cursor, lastBuf, lastCursor, verbosity))

      // we don't try lower priority completions unless higher ones return no results.
      def tryCompletion(p: Parsed, completionFunction: Parsed => List[String]): Option[Int] = {
        completionFunction(p) match {
          case Nil  => None
          case xs   =>
            // modify in place and return the position
            xs foreach (candidates add _)

            // update the last buffer unless this is an alternatives list
            if (xs contains "") Some(p.cursor)
            else {
              val advance = commonPrefix(xs)            
              lastCursor = p.position + advance.length
              lastBuf = (buf take p.position) + advance
  
              DBG("tryCompletion(%s, _) lastBuf = %s, lastCursor = %s, p.position = %s".format(p, lastBuf, lastCursor, p.position))
              Some(p.position) 
            }
        }
      }
      
      def mkDotted      = Parsed.dotted(buf, cursor) withVerbosity verbosity
      def mkUndelimited = Parsed.undelimited(buf, cursor) withVerbosity verbosity

      // a single dot is special cased to completion on the previous result
      def lastResultCompletion =
        if (!looksLikeInvocation(buf)) None            
        else tryCompletion(Parsed.dotted(buf drop 1, cursor), lastResultFor)

      def regularCompletion = tryCompletion(mkDotted, topLevelFor)
      def fileCompletion    = tryCompletion(mkUndelimited, FileCompletion completionsFor _.buffer)
      
      /** This is the kickoff point for all manner of theoretically possible compiler
       *  unhappiness - fault may be here or elsewhere, but we don't want to crash the
       *  repl regardless.  Hopefully catching Exception is enough, but because the
       *  compiler still throws some Errors it may not be.
       */
      try {
        (lastResultCompletion orElse regularCompletion orElse fileCompletion) getOrElse cursor
      }
      catch {
        case ex: Exception =>
          if (isCompletionDebug || isReplDebug)
            println("Error: complete(%s, %s, _) provoked %s".format(_buf, cursor, ex))
            
          candidates add " "
          candidates add "<completion error>"
          cursor
      }
    }
  }
}
