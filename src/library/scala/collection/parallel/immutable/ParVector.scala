/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package parallel.immutable




import scala.collection.generic.{GenericParTemplate, CanCombineFrom, ParFactory}
import scala.collection.parallel.ParSeqLike
import scala.collection.parallel.Combiner
import scala.collection.parallel.SeqSplitter
import mutable.ArrayBuffer
import immutable.Vector
import immutable.VectorBuilder
import immutable.VectorIterator



/** Immutable parallel vectors, based on vectors.
<<<<<<< HEAD
 *  
 *  $paralleliterableinfo
 *  
 *  $sideeffects
 *  
 *  @tparam T    the element type of the vector
 *  
 *  @author Aleksandar Prokopec
 *  @since 2.9
 *  
=======
 *
 *  $paralleliterableinfo
 *
 *  $sideeffects
 *
 *  @tparam T    the element type of the vector
 *
 *  @author Aleksandar Prokopec
 *  @since 2.9
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll immutable.ParVector
 *  @define coll immutable parallel vector
 */
class ParVector[+T](private[this] val vector: Vector[T])
extends ParSeq[T]
   with GenericParTemplate[T, ParVector]
   with ParSeqLike[T, ParVector[T], Vector[T]]
   with Serializable
{
  override def companion = ParVector
<<<<<<< HEAD
  
  def this() = this(Vector())
  
  type SCPI = SignalContextPassingIterator[ParVectorIterator]
  
  def apply(idx: Int) = vector.apply(idx)
  
  def length = vector.length
  
=======

  def this() = this(Vector())

  type SCPI = SignalContextPassingIterator[ParVectorIterator]

  def apply(idx: Int) = vector.apply(idx)

  def length = vector.length

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def splitter: SeqSplitter[T] = {
    val pit = new ParVectorIterator(vector.startIndex, vector.endIndex) with SCPI
    vector.initIterator(pit)
    pit
  }
<<<<<<< HEAD
  
  override def seq: Vector[T] = vector
  
=======

  override def seq: Vector[T] = vector

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class ParVectorIterator(_start: Int, _end: Int) extends VectorIterator[T](_start, _end) with ParIterator {
  self: SCPI =>
    def remaining: Int = remainingElementCount
    def dup: SeqSplitter[T] = (new ParVector(remainingVector)).splitter
    def split: Seq[ParVectorIterator] = {
      val rem = remaining
      if (rem >= 2) psplit(rem / 2, rem - rem / 2)
      else Seq(this)
    }
    def psplit(sizes: Int*): Seq[ParVectorIterator] = {
      var remvector = remainingVector
      val splitted = new ArrayBuffer[Vector[T]]
      for (sz <- sizes) {
        splitted += remvector.take(sz)
        remvector = remvector.drop(sz)
      }
      splitted.map(v => new ParVector(v).splitter.asInstanceOf[ParVectorIterator])
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}



/** $factoryInfo
 *  @define Coll immutable.ParVector
 *  @define coll immutable parallel vector
 */
object ParVector extends ParFactory[ParVector] {
  implicit def canBuildFrom[T]: CanCombineFrom[Coll, T, ParVector[T]] =
    new GenericCanCombineFrom[T]
<<<<<<< HEAD
  
  def newBuilder[T]: Combiner[T, ParVector[T]] = newCombiner[T]
  
=======

  def newBuilder[T]: Combiner[T, ParVector[T]] = newCombiner[T]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newCombiner[T]: Combiner[T, ParVector[T]] = new LazyParVectorCombiner[T] // was: with EPC[T, ParVector[T]]
}



private[immutable] class LazyParVectorCombiner[T] extends Combiner[T, ParVector[T]] {
//self: EnvironmentPassingCombiner[T, ParVector[T]] =>
  var sz = 0
  val vectors = new ArrayBuffer[VectorBuilder[T]] += new VectorBuilder[T]
<<<<<<< HEAD
  
  def size: Int = sz
  
=======

  def size: Int = sz

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def +=(elem: T): this.type = {
    vectors.last += elem
    sz += 1
    this
  }
<<<<<<< HEAD
  
  def clear = {
    vectors.clear
    vectors += new VectorBuilder[T]
    sz = 0
  }
  
=======

  def clear() = {
    vectors.clear()
    vectors += new VectorBuilder[T]
    sz = 0
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def result: ParVector[T] = {
    val rvb = new VectorBuilder[T]
    for (vb <- vectors) {
      rvb ++= vb.result
    }
    new ParVector(rvb.result)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def combine[U <: T, NewTo >: ParVector[T]](other: Combiner[U, NewTo]) = if (other eq this) this else {
    val that = other.asInstanceOf[LazyParVectorCombiner[T]]
    sz += that.sz
    vectors ++= that.vectors
    this
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}






