/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
// GENERATED CODE: DO NOT EDIT.
<<<<<<< HEAD
// genprod generated these sources at: Thu Apr 14 13:08:25 PDT 2011
=======
// genprod generated these sources at: Sun Jul 31 00:37:30 CEST 2011
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

package scala


/** A function of 0 parameters.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  In the following example, the definition of javaVersion is a
 *  shorthand for the anonymous class definition anonfun0:
 *
 *  {{{
<<<<<<< HEAD
 *  object Main extends Application { 
=======
 *  object Main extends Application {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    val javaVersion = () => sys.props("java.version")
 *
 *    val anonfun0 = new Function0[String] {
 *      def apply(): String = sys.props("java.version")
 *    }
 *    assert(javaVersion() == anonfun0())
 *  }
 *  }}}
 */
trait Function0[@specialized +R] extends AnyRef { self =>
  /** Apply the body of this function to the arguments.
   *  @return   the result of function application.
   */
  def apply(): R
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toString() = "<function0>"
}
