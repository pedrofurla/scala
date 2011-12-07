/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection

import generic._
import scala.util.control.Breaks._
import mutable.Builder

/** A base trait for iterable collections.
 *  $iterableInfo
 */
<<<<<<< HEAD
trait Iterable[+A] extends Traversable[A] 
=======
trait Iterable[+A] extends Traversable[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                      with GenIterable[A]
                      with GenericTraversableTemplate[A, Iterable]
                      with IterableLike[A, Iterable[A]] {
  override def companion: GenericCompanion[Iterable] = Iterable
<<<<<<< HEAD
 
  override def seq = this
  
=======

  override def seq = this

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /* The following methods are inherited from trait IterableLike
   *
  override def iterator: Iterator[A]
  override def takeRight(n: Int): Iterable[A]
  override def dropRight(n: Int): Iterable[A]
  override def sameElements[B >: A](that: GenIterable[B]): Boolean
  override def view
  override def view(from: Int, until: Int)
  */

}
<<<<<<< HEAD
                                         
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
/** $factoryInfo
 *  The current default implementation of a $Coll is a `Vector`.
 *  @define coll iterable collection
 *  @define Coll Iterable
 */
object Iterable extends TraversableFactory[Iterable] {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** $genericCanBuildFromInfo */
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, Iterable[A]] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]

  def newBuilder[A]: Builder[A, Iterable[A]] = immutable.Iterable.newBuilder[A]
<<<<<<< HEAD
  
  /** The minimum element of a non-empty sequence of ordered elements */
  @deprecated("use <seq>.min instead, where <seq> is the sequence for which you want to compute the minimum", "2.8.0")
  def min[A](seq: Iterable[A])(implicit ord: Ordering[A]): A = seq.min

  /** The maximum element of a non-empty sequence of ordered elements */
  @deprecated("use <seq>.max instead, where <seq> is the sequence for which you want to compute the maximum", "2.8.0")
  def max[A](seq: Iterable[A])(implicit ord: Ordering[A]): A = seq.max

  @deprecated("use View instead", "2.8.0")
  type Projection[A] = IterableView[A, Coll]
}
=======
}

/** Explicit instantiation of the `Iterable` trait to reduce class file size in subclasses. */
private[scala] abstract class AbstractIterable[+A] extends AbstractTraversable[A] with Iterable[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
