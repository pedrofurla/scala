/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection.parallel



import scala.collection.generic.GenericCompanion
import scala.collection.generic.GenericParCompanion
import scala.collection.generic.GenericParTemplate
import scala.collection.generic.ParFactory
import scala.collection.generic.CanCombineFrom
import scala.collection.GenSeq
import scala.collection.parallel.mutable.ParArrayCombiner
import scala.collection.parallel.mutable.ParArray



/** A template trait for parallel sequences.
<<<<<<< HEAD
 *  
 *  $parallelseqinfo
 *  
 *  $sideeffects
 *  
=======
 *
 *  $parallelseqinfo
 *
 *  $sideeffects
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @tparam T      the type of the elements in this parallel sequence
 *
 *  @author Aleksandar Prokopec
 */
trait ParSeq[+T] extends GenSeq[T]
                    with ParIterable[T]
                    with GenericParTemplate[T, ParSeq]
                    with ParSeqLike[T, ParSeq[T], Seq[T]]
{
  override def companion: GenericCompanion[ParSeq] with GenericParCompanion[ParSeq] = ParSeq
  //protected[this] override def newBuilder = ParSeq.newBuilder[T]
<<<<<<< HEAD
  
  def apply(i: Int): T
  
  override def toString = super[ParIterable].toString
  
=======

  def apply(i: Int): T

  override def toString = super[ParIterable].toString

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def stringPrefix = getClass.getSimpleName
}


object ParSeq extends ParFactory[ParSeq] {
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParSeq[T]] = new GenericCanCombineFrom[T]
<<<<<<< HEAD
  
  def newBuilder[T]: Combiner[T, ParSeq[T]] = ParArrayCombiner[T]
  
  def newCombiner[T]: Combiner[T, ParSeq[T]] = ParArrayCombiner[T]
  
=======

  def newBuilder[T]: Combiner[T, ParSeq[T]] = ParArrayCombiner[T]

  def newCombiner[T]: Combiner[T, ParSeq[T]] = ParArrayCombiner[T]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}























