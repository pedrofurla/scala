/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc
package repl

import collection.{ mutable, immutable }
import util._
import Spawn._
import doc.model
import Strings._
import JarUtil.scalaLibrarySourceJar
import java.util.concurrent.LinkedBlockingQueue
import reporters.{ ConsoleReporter, Reporter }

trait Docs {
  self: Interpreter =>
  
  // Typers.typed1
  // case ddef @ DocDef(comment, defn) =>
  //   if (onlyPresentation && (sym ne null) && (sym ne NoSymbol)) {
  //     docComments(sym) = comment
  //     comment.defineVariables(sym)
  //     val typer1 = newTyper(context.makeNewScope(tree, context.owner))
  //     for (useCase <- comment.useCases) 
  //       typer1.silent(_.typedUseCase(useCase)) match {
  //         case ex: TypeError =>
  //           unit.warning(useCase.pos, ex.msg)
  //         case _ =>
  //       }
  //   }
  //   typed(defn, mode, pt)
  // 

  private def docSettings = returning(new doc.Settings(settings.out println _)) { s =>
    s.docsourceurl.value = "http://foo/bar/baz"
  }
  val docReporter = new ConsoleReporter(docSettings)
  
  /** The unique compiler instance used by this processor and constructed from its `settings`. */
  object docCompiler extends Global(docSettings, docReporter) {
    override protected def computeInternalPhases() {
      phasesSet += syntaxAnalyzer
      phasesSet += analyzer.namerFactory
      phasesSet += analyzer.packageObjects
      phasesSet += analyzer.typerFactory
      phasesSet += superAccessors
      phasesSet += pickler
      phasesSet += refchecks
    }
    override def onlyPresentation = true
    lazy val addSourceless = {
      val sless = new doc.SourcelessComments { val global = docCompiler }
      docComments ++= sless.comments
    }
  }
  
  object docFactory extends model.ModelFactory(docCompiler, docSettings) with model.comment.CommentFactory {
    import docCompiler._
    import definitions._
    
    { new docCompiler.Run() ; () }
    
    def matches(s1: Global#Symbol, s2: Global#Symbol) =
      (s1.name == s2.name) && (s1.owner.fullName == s2.owner.fullName)

    def equivalentSym(other: Global#Symbol): Symbol = {
      val otherName: Name = other.name.toString
      val ownerSym        = other.ownersIterator find (x => x.isClass || x.isModule) getOrElse { return NoSymbol }
      
      if (ownerSym.isModule)
        getMember(definitions.getModule(ownerSym.fullName), otherName)
      else
        getMember(definitions.getClass(ownerSym.fullName), otherName)
    }

    private var alreadyCompiled = new immutable.HashSet[String]
    private val compileQueue    = new LinkedBlockingQueue[(String, String)]
    private val compilerThread  = {
      val runner = runnable {
        while (true) {
          import collection.JavaConversions._
          val xs = new collection.mutable.ListBuffer[(String, String)]
          xs += compileQueue.take()     // block
          compileQueue drainTo xs       // empty
          println("Compiling " + xs.size + " sources.")
          compileMany(xs.toList)
        }
      }
      
      submit(runner)
    }
    
    def compileSourcesNamed(sourceName: String): Unit = {
      if (alreadyCompiled(sourceName) || scalaLibrarySourceJar == null)
        return

      alreadyCompiled = alreadyCompiled + sourceName
      val toCompile   = scalaLibrarySourceJar files sourceName

      toCompile.toList map { case (k, v) => (k.getName, v) } foreach (compileQueue put _)
    }
    def compileMany(sources: List[(String, String)]) = {
      val fs = sources map { case (name, code) => new BatchSourceFile(name, code) }
      val run = new Run()
      run.compileSources(fs)
    }
  
    def lookup(sym: Global#Symbol): Option[String] = {
      val possibles = docComments.keys filter (_.name.toString == sym.name.toString) toList
      val definites = possibles filter (_.enclClass.name.toString == sym.enclClass.name.toString)
      
      if (definites.nonEmpty) Some(definites map expandedDocComment mkString "\n")
      else None
      // 
      // 
      // val s = equivalentSym(sym)
      // 
      // (docComments get s) match {
      //   case Some(dc)   => Some(expandedDocComment(s))
      //   case _          =>
      //     val xs = docComments.keys.toList filter (_.name.toString == sym.name.toString)
      //     if (xs.isEmpty) { println("Really None.") ; None }
      //     else { println("Hey... " + xs) ; None }
      // }
    }
    // def getDocMap = immutable.Map[Symbol, String]() ++ docMap
  }
  
  def sourceNameForSym(sym: compiler.Symbol) = {
    for {
      classSym <- sym.ownersIterator find (_.isClass)
      classData <- javapWrapper(classSym.fullName)
    } yield {
      classData.sourceName
    }
  } getOrElse ""
  
  def allcomms = docCompiler.docComments

  def scaladoc(sym: compiler.Symbol): String = {
    // docCompiler.init
    docFactory compileSourcesNamed sourceNameForSym(sym)
    docFactory.lookup(sym) getOrElse ""
  }

  // private 
  // object docCompiler extends doc.DocFactory(docReporter, docSettings) {
    // val docGlobal = this.compiler
    // import docGlobal._
    // import definitions._
    // 
    // lazy val init = { new Run() ; () }
    // 
    // def matches(s1: Global#Symbol, s2: Global#Symbol) =
    //   (s1.name == s2.name) && (s1.owner.fullName == s2.owner.fullName)
    // 
    // def equivalentSym(other: Global#Symbol): Symbol = {
    //   val otherName: Name = other.name.toString
    //   val ownerSym        = other.ownersIterator find (x => x.isClass || x.isModule) getOrElse { return NoSymbol }
    //   
    //   if (ownerSym.isModule)
    //     getMember(definitions.getModule(ownerSym.fullName), otherName)
    //   else
    //     getMember(definitions.getClass(ownerSym.fullName), otherName)
    // }
    // 
    // private var alreadyCompiled = new immutable.HashSet[String]
    // private val compileQueue    = new LinkedBlockingQueue[(String, String)]
    // private val compilerThread  = {
    //   val runner = runnable {
    //     while (true) {
    //       import collection.JavaConversions._
    //       val xs = new collection.mutable.ListBuffer[(String, String)]
    //       xs += compileQueue.take()     // block
    //       compileQueue drainTo xs       // empty
    //       compileMany(xs.toList)
    //     }
    //   }
    //   
    //   submit(runner)
    // }
  // 
  //   def compileSourcesNamed(sourceName: String): Unit = {
  //     if (alreadyCompiled(sourceName) || scalaLibrarySourceJar == null)
  //       return
  //     
  //     alreadyCompiled = alreadyCompiled + sourceName
  //     val toCompile   = scalaLibrarySourceJar files sourceName
  // 
  //     toCompile.toList map { case (k, v) => (k.getName, v) } foreach (compileQueue put _)
  //   }
  //   def compileMany(sources: List[(String, String)]) = {
  //     val fs = sources map { case (name, code) => new BatchSourceFile(name, code) }
  //     val run = new Run()
  //     run.compileSources(fs)
  //   }
  //   
  //   lazy val sless    = new doc.SourcelessComments { val global: docGlobal.type = docGlobal }
  //   lazy val docMap   = mutable.Map[Symbol, String]() ++ (sless.comments mapValues (_.toString))
  //   lazy val docFactory =
  //     new model.ModelFactory(docGlobal, docSettings) with model.comment.CommentFactory
  // 
  //   def lookup(sym: Global#Symbol): Option[String] = {
  //     val s = equivalentSym(sym)
  //     
  //     (docMap get s) match {
  //       case Some(str)  => Some(str)
  //       case _          =>
  //         println("factory = " + docFactory)
  //         None
  //     }
  //   }
  //   def getDocMap = immutable.Map[Symbol, String]() ++ docMap
  // }
}