/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc
package interpreter

object Results {
  /** A result from the Interpreter interpreting one line of input. */
  abstract sealed class Result

  /** The line was interpreted successfully. */
  case object Success extends Result

  /** The line was erroneous in some way. */
  case object Error extends Result
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The input was incomplete.  The caller should request more input.
   */
  case object Incomplete extends Result
}
