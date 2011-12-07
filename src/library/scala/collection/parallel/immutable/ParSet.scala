/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package parallel.immutable

import scala.collection.GenSet
import scala.collection.generic._
import scala.collection.parallel.ParSetLike
import scala.collection.parallel.Combiner

/** An immutable variant of `ParSet`.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll mutable.ParSet
 *  @define coll mutable parallel set
 */
trait ParSet[T]
extends collection/*.immutable*/.GenSet[T]
   with GenericParTemplate[T, ParSet]
   with parallel.ParSet[T]
   with ParIterable[T]
   with ParSetLike[T, ParSet[T], collection.immutable.Set[T]]
{
self =>
  override def empty: ParSet[T] = ParHashSet[T]()
<<<<<<< HEAD
  
  override def companion: GenericCompanion[ParSet] with GenericParCompanion[ParSet] = ParSet
  
  override def stringPrefix = "ParSet"
  
=======

  override def companion: GenericCompanion[ParSet] with GenericParCompanion[ParSet] = ParSet

  override def stringPrefix = "ParSet"

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // ok, because this could only violate `apply` and we can live with that
  override def toSet[U >: T]: ParSet[U] = this.asInstanceOf[ParSet[U]]
}

/** $factoryInfo
 *  @define Coll mutable.ParSet
 *  @define coll mutable parallel set
 */
object ParSet extends ParSetFactory[ParSet] {
  def newCombiner[T]: Combiner[T, ParSet[T]] = HashSetCombiner[T]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParSet[T]] = new GenericCanCombineFrom[T]
}
