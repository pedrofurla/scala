/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Alexander Spoon
 */

package scala.tools.nsc
package interpreter

/** Settings for the interpreter
 *
 * @version 1.0
 * @author Lex Spoon, 2007/3/24
 **/
class ISettings(intp: IMain) {
  /** A list of paths where :load should look */
  var loadPath = List(".")
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Set this to true to see repl machinery under -Yrich-exceptions.
   */
  var showInternalStackTraces = false

  /** The maximum length of toString to use when printing the result
   *  of an evaluation.  0 means no maximum.  If a printout requires
   *  more than this number of characters, then the printout is
   *  truncated.
   */
  var maxPrintString = 800
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The maximum number of completion candidates to print for tab
   *  completion without requiring confirmation.
   */
  var maxAutoprintCompletion = 250
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** String unwrapping can be disabled if it is causing issues.
   *  Settings this to false means you will see Strings like "$iw.$iw.".
   */
  var unwrapStrings = true
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def deprecation_=(x: Boolean) = {
    val old = intp.settings.deprecation.value
    intp.settings.deprecation.value = x
    if (!old && x) println("Enabled -deprecation output.")
    else if (old && !x) println("Disabled -deprecation output.")
  }
  def deprecation: Boolean = intp.settings.deprecation.value
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def allSettings = Map(
    "maxPrintString" -> maxPrintString,
    "maxAutoprintCompletion" -> maxAutoprintCompletion,
    "unwrapStrings" -> unwrapStrings,
    "deprecation" -> deprecation
  )
<<<<<<< HEAD
  
  private def allSettingsString =
    allSettings.toList sortBy (_._1) map { case (k, v) => "  " + k + " = " + v + "\n" } mkString
    
=======

  private def allSettingsString =
    allSettings.toList sortBy (_._1) map { case (k, v) => "  " + k + " = " + v + "\n" } mkString

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toString = """
    | ISettings {
    | %s
    | }""".stripMargin.format(allSettingsString)
}
