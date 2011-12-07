/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel

import scala.collection.GenIterable
import scala.collection.generic._
import scala.collection.parallel.mutable.ParArrayCombiner
import scala.collection.parallel.mutable.ParArray

/** A template trait for parallel iterable collections.
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
 *
 *  @define Coll ParIterable
 *  @define coll parallel iterable
 */
trait ParIterable[+T]
extends GenIterable[T]
   with GenericParTemplate[T, ParIterable]
   with ParIterableLike[T, ParIterable[T], Iterable[T]] {
  override def companion: GenericCompanion[ParIterable] with GenericParCompanion[ParIterable] = ParIterable
  //protected[this] override def newBuilder = ParIterable.newBuilder[T]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def stringPrefix = "ParIterable"
}

/** $factoryInfo
 */
object ParIterable extends ParFactory[ParIterable] {
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParIterable[T]] = new GenericCanCombineFrom[T]
<<<<<<< HEAD
  
  def newBuilder[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]
  
=======

  def newBuilder[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newCombiner[T]: Combiner[T, ParIterable[T]] = ParArrayCombiner[T]
}

