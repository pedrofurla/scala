/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel.mutable


import scala.collection.generic._
import scala.collection.parallel.ParIterableLike
import scala.collection.parallel.Combiner
import scala.collection.GenIterable


/** A template trait for mutable parallel iterable collections.
<<<<<<< HEAD
 *  
 *  $paralleliterableinfo
 *  
 *  $sideeffects
 *  
 *  @tparam T    the element type of the collection
 *  
=======
 *
 *  $paralleliterableinfo
 *
 *  $sideeffects
 *
 *  @tparam T    the element type of the collection
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait ParIterable[T] extends collection/*.mutable*/.GenIterable[T]
                        with collection.parallel.ParIterable[T]
                        with GenericParTemplate[T, ParIterable]
                        with ParIterableLike[T, ParIterable[T], Iterable[T]] {
  override def companion: GenericCompanion[ParIterable] with GenericParCompanion[ParIterable] = ParIterable
  //protected[this] override def newBuilder = ParIterable.newBuilder[T]
<<<<<<< HEAD
  
  // if `mutable.ParIterableLike` is introduced, please move these methods there
  override def toIterable: ParIterable[T] = this
  
  override def toSeq: ParSeq[T] = toParCollection[T, ParSeq[T]](() => ParSeq.newCombiner[T])
  
=======

  // if `mutable.ParIterableLike` is introduced, please move these methods there
  override def toIterable: ParIterable[T] = this

  override def toSeq: ParSeq[T] = toParCollection[T, ParSeq[T]](() => ParSeq.newCombiner[T])

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def seq: collection.mutable.Iterable[T]
}

/** $factoryInfo
 */
object ParIterable extends ParFactory[ParIterable] {
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParIterable[T]] =
    new GenericCanCombineFrom[T]
<<<<<<< HEAD
  
  def newBuilder[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]
  
=======

  def newBuilder[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newCombiner[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]
}














