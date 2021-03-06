/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
// GENERATED CODE: DO NOT EDIT. See scala.Function0 for timestamp.

package scala


/** A function of 5 parameters.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait Function5[-T1, -T2, -T3, -T4, -T5, +R] extends AnyRef { self =>
  /** Apply the body of this function to the arguments.
   *  @return   the result of function application.
   */
  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5): R
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Creates a curried version of this function.
   *
   *  @return   a function `f` such that `f(x1)(x2)(x3)(x4)(x5) == apply(x1, x2, x3, x4, x5)`
   */
  def curried: T1 => T2 => T3 => T4 => T5 => R = {
    (x1: T1) => ((x2: T2, x3: T3, x4: T4, x5: T5) => self.apply(x1, x2, x3, x4, x5)).curried
  }
<<<<<<< HEAD
  @deprecated("Use 'curried' instead", "2.8.0")
  def curry = curried
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** Creates a tupled version of this function: instead of 5 arguments,
   *  it accepts a single [[scala.Tuple5]] argument.
   *
   *  @return   a function `f` such that `f((x1, x2, x3, x4, x5)) == f(Tuple5(x1, x2, x3, x4, x5)) == apply(x1, x2, x3, x4, x5)`
   */
  def tupled: Tuple5[T1, T2, T3, T4, T5] => R = {
    case Tuple5(x1, x2, x3, x4, x5) => apply(x1, x2, x3, x4, x5)
  }
  override def toString() = "<function5>"
}
