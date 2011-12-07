/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// DO NOT EDIT, CHANGES WILL BE LOST.

package scala


<<<<<<< HEAD
/** Unit is a member of the value classes, those whose instances are
 *  not represented as objects by the underlying host system.  There is
 *  only one value of type Unit: `()`.
=======
/** `Unit` is a subtype of [[scala.AnyVal]]. There is only one value of type
 *  `Unit`, `()`, and it is not represented by any object in the underlying
 *  runtime system. A method with return type `Unit` is analogous to a Java
 *  method which is declared `void`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
final class Unit extends AnyVal {
  def getClass(): Class[Unit] = sys.error("stub")
}

object Unit extends AnyValCompanion {

  /** Transform a value type into a boxed reference type.
   *
   *  @param  x   the Unit to be boxed
   *  @return     a scala.runtime.BoxedUnit offering `x` as its underlying value.
   */
  def box(x: Unit): scala.runtime.BoxedUnit = scala.runtime.BoxedUnit.UNIT

  /** Transform a boxed type into a value type.  Note that this
   *  method is not typesafe: it accepts any Object, but will throw
   *  an exception if the argument is not a scala.runtime.BoxedUnit.
   *
   *  @param  x   the scala.runtime.BoxedUnit to be unboxed.
   *  @throws     ClassCastException  if the argument is not a scala.runtime.BoxedUnit
   *  @return     the Unit value ()
   */
  def unbox(x: java.lang.Object): Unit = ()

  /** The String representation of the scala.Unit companion object.
   */
  override def toString = "object scala.Unit"
}

