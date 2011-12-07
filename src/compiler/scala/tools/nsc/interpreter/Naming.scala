/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package interpreter

/** This is for name logic which is independent of the compiler (notice there's no Global.)
 *  That includes at least generating, metaquoting, mangling, and unmangling.
 */
trait Naming {
  def unmangle(str: String): String = {
    val cleaned = removeIWPackages(removeLineWrapper(str))
    var ctrlChars = 0
    cleaned map { ch =>
      if (ch.isControl && !ch.isWhitespace) {
        ctrlChars += 1
        if (ctrlChars > 5) return "[line elided for control chars: possibly a scala signature]"
        else '?'
      }
      else ch
    }
  }
<<<<<<< HEAD
  
  // The two name forms this is catching are the two sides of this assignment:
  //
  // $line3.$read.$iw.$iw.Bippy = 
  //   $line3.$read$$iw$$iw$Bippy@4a6a00ca
  
=======

  // The two name forms this is catching are the two sides of this assignment:
  //
  // $line3.$read.$iw.$iw.Bippy =
  //   $line3.$read$$iw$$iw$Bippy@4a6a00ca

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def noMeta(s: String) = "\\Q" + s + "\\E"
  private lazy val lineRegex = {
    val sn = sessionNames
    val members = List(sn.read, sn.eval, sn.print) map noMeta mkString ("(?:", "|", ")")
    debugging("lineRegex")(noMeta(sn.line) + """\d+[./]""" + members + """[$.]""")
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def removeLineWrapper(s: String) = s.replaceAll(lineRegex, "")
  private def removeIWPackages(s: String)  = s.replaceAll("""\$iw[$.]""", "")

  trait SessionNames {
    // All values are configurable by passing e.g. -Dscala.repl.naming.read=XXX
    final def propOr(name: String): String = propOr(name, "$" + name)
    final def propOr(name: String, default: String): String =
      sys.props.getOrElse("scala.repl.naming." + name, default)

    // Prefixes used in repl machinery.  Default to $line, $read, etc.
<<<<<<< HEAD
    def line  = propOr("line")
    def read  = propOr("read")
    def eval  = propOr("eval")
    def print = propOr("print")
    
=======
    def line   = propOr("line")
    def read   = propOr("read")
    def eval   = propOr("eval")
    def print  = propOr("print")
    def result = propOr("result")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // The prefix for unnamed results: by default res0, res1, etc.
    def res   = propOr("res", "res")  // INTERPRETER_VAR_PREFIX
    // Internal ones
    def ires  = propOr("ires")
  }
  lazy val sessionNames: SessionNames = new SessionNames { }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Generates names pre0, pre1, etc. via calls to apply method */
  class NameCreator(pre: String) {
    private var x = -1
    var mostRecent: String = ""
<<<<<<< HEAD
    
    def apply(): String = { 
=======

    def apply(): String = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      x += 1
      mostRecent = pre + x
      mostRecent
    }
    def reset(): Unit = x = -1
    def didGenerate(name: String) =
      (name startsWith pre) && ((name drop pre.length) forall (_.isDigit))
  }
<<<<<<< HEAD
  
  private lazy val userVar     = new NameCreator(sessionNames.res)  // var name, like res0
  private lazy val internalVar = new NameCreator(sessionNames.ires) // internal var name, like $ires0
  
=======

  private lazy val userVar     = new NameCreator(sessionNames.res)  // var name, like res0
  private lazy val internalVar = new NameCreator(sessionNames.ires) // internal var name, like $ires0

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isLineName(name: String)        = (name startsWith sessionNames.line) && (name stripPrefix sessionNames.line forall (_.isDigit))
  def isUserVarName(name: String)     = userVar didGenerate name
  def isInternalVarName(name: String) = internalVar didGenerate name

  val freshLineId            = {
    var x = 0
    () => { x += 1 ; x }
  }
  def freshUserVarName()     = userVar()
  def freshInternalVarName() = internalVar()
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def resetAllCreators() {
    userVar.reset()
    internalVar.reset()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def mostRecentVar = userVar.mostRecent
}
