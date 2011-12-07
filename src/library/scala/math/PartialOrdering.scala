/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.math

/** A trait for representing partial orderings.  It is important to
<<<<<<< HEAD
 *  distinguish between a type that has a partial order and a representation 
 *  of partial ordering on some type.  This trait is for representing the
 *  latter.
 *  
=======
 *  distinguish between a type that has a partial order and a representation
 *  of partial ordering on some type.  This trait is for representing the
 *  latter.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  A [[http://en.wikipedia.org/wiki/Partial_order partial ordering]] is a
 *  binary relation on a type `T` that is also an equivalence relation on
 *  values of type `T`.  This relation is exposed as the `lteq` method of
 *  the `PartialOrdering` trait. This relation must be:
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  - reflexive: `lteq(x, x) == '''true'''`, for any `x` of type `T`.
 *  - anti-symmetric: `lteq(x, y) == '''true'''` and `lteq(y, x) == true`
 *    then `equiv(x, y)`, for any `x` and `y` of type `T`.
 *  - transitive: if `lteq(x, y) == '''true'''` and
 *    `lteq(y, z) == '''true'''` then `lteq(x, z) == '''true'''`,
 *    for any `x`, `y`, and `z` of type `T`.
 *
 *  @author  Geoffrey Washburn
 *  @version 1.0, 2008-04-0-3
 *  @since 2.7
 */

trait PartialOrdering[T] extends Equiv[T] {
  outer =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Result of comparing `x` with operand `y`.
   *  Returns `None` if operands are not comparable.
   *  If operands are comparable, returns `Some(r)` where
   *  - `r < 0`    iff    `x < y`
   *  - `r == 0`   iff    `x == y`
   *  - `r > 0`    iff    `x > y`
   */
  def tryCompare(x: T, y: T): Option[Int]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns `'''true'''` iff `x` comes before `y` in the ordering.
   */
  def lteq(x: T, y: T): Boolean

<<<<<<< HEAD
  /** Returns `'''true'''` iff `y` comes before `x` in the ordering. 
=======
  /** Returns `'''true'''` iff `y` comes before `x` in the ordering.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def gteq(x: T, y: T): Boolean = lteq(y, x)

  /** Returns `'''true'''` iff `x` comes before `y` in the ordering
   *  and is not the same as `y`.
   */
  def lt(x: T, y: T): Boolean = lteq(x, y) && !equiv(x, y)

  /** Returns `'''true'''` iff `y` comes before `x` in the ordering
   *  and is not the same as `x`.
   */
  def gt(x: T, y: T): Boolean = gteq(x, y) && !equiv(x, y)

<<<<<<< HEAD
  /** Returns `'''true'''` iff `x` is equivalent to `y` in the ordering. 
=======
  /** Returns `'''true'''` iff `x` is equivalent to `y` in the ordering.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def equiv(x: T, y: T): Boolean = lteq(x,y) && lteq(y,x)

  def reverse : PartialOrdering[T] = new PartialOrdering[T] {
    override def reverse = outer
    def lteq(x: T, y: T) = outer.lteq(y, x)
    def tryCompare(x: T, y: T) = outer.tryCompare(y, x)
  }
}
