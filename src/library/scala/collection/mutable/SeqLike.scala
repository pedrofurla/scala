/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package mutable

import generic._
import parallel.mutable.ParSeq

/** A template trait for mutable sequences of type `mutable.Seq[A]`.
 *  @tparam A    the type of the elements of the set
 *  @tparam This the type of the set itself.
 *
 */
trait SeqLike[A, +This <: SeqLike[A, This] with Seq[A]]
  extends scala.collection.SeqLike[A, This]
     with Cloneable[This]
     with Parallelizable[A, ParSeq[A]]
{
  self =>
<<<<<<< HEAD
  
  protected[this] override def parCombiner = ParSeq.newCombiner[A]
  
=======

  protected[this] override def parCombiner = ParSeq.newCombiner[A]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Replaces element at given index with a new value.
   *
   *  @param n       the index of the element to replace.
   *  @param lem     the new value.
   *  @throws   IndexOutOfBoundsException if the index is not valid.
   */
  def update(idx: Int, elem: A)
<<<<<<< HEAD
  
  /** Applies a transformation function to all values contained in this sequence.
   *  The transformation function produces new values from existing elements.
   * 
=======

  /** Applies a transformation function to all values contained in this sequence.
   *  The transformation function produces new values from existing elements.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param f  the transformation to apply
   * @return   the sequence itself.
   */
  def transform(f: A => A): this.type = {
    var i = 0
    this foreach { el =>
      this(i) = f(el)
      i += 1
    }
    this
  }
}
