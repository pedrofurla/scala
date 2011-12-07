/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package parallel.immutable


import scala.collection.generic.GenericParTemplate
import scala.collection.generic.GenericCompanion
import scala.collection.generic.GenericParCompanion
import scala.collection.generic.CanCombineFrom
import scala.collection.generic.ParFactory
import scala.collection.parallel.ParSeqLike
import scala.collection.parallel.Combiner
import scala.collection.GenSeq



/** An immutable variant of `ParSeq`.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll mutable.ParSeq
 *  @define coll mutable parallel sequence
 */
trait ParSeq[+T]
extends collection/*.immutable*/.GenSeq[T]
   with collection.parallel.ParSeq[T]
   with ParIterable[T]
   with GenericParTemplate[T, ParSeq]
   with ParSeqLike[T, ParSeq[T], collection.immutable.Seq[T]]
{
  override def companion: GenericCompanion[ParSeq] with GenericParCompanion[ParSeq] = ParSeq
  override def toSeq: ParSeq[T] = this
}


/** $factoryInfo
 *  @define Coll mutable.ParSeq
 *  @define coll mutable parallel sequence
 */
object ParSeq extends ParFactory[ParSeq] {
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParSeq[T]] = new GenericCanCombineFrom[T]
<<<<<<< HEAD
  
  def newBuilder[T]: Combiner[T, ParSeq[T]] = ParVector.newBuilder[T]
  
=======

  def newBuilder[T]: Combiner[T, ParSeq[T]] = ParVector.newBuilder[T]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newCombiner[T]: Combiner[T, ParSeq[T]] = ParVector.newCombiner[T]
}



