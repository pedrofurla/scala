/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.collection

import generic._
import mutable.ArrayBuffer
import scala.annotation.tailrec

/** A template trait for indexed sequences of type `IndexedSeq[A]`.
 *
 *  $indexedSeqInfo
 *
 *  This trait just implements `iterator` in terms of `apply` and `length`.
 *  However, see `IndexedSeqOptimized` for an implementation trait that overrides operations
<<<<<<< HEAD
 *  to make them run faster under the assumption of fast random access with `apply`. 
=======
 *  to make them run faster under the assumption of fast random access with `apply`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 *  @define  Coll  IndexedSeq
 *  @define indexedSeqInfo
 *  Indexed sequences support constant-time or near constant-time element
 *  access and length computation. They are defined in terms of abstract methods
 *  `apply` for indexing and `length`.
<<<<<<< HEAD
 * 
 *  Indexed sequences do not add any new methods wrt `Seq`, but promise
 *  efficient implementations of random access patterns.
 * 
=======
 *
 *  Indexed sequences do not add any new methods wrt `Seq`, but promise
 *  efficient implementations of random access patterns.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @tparam A    the element type of the $coll
 *  @tparam Repr the type of the actual $coll containing the elements.
 *  @author Martin Odersky
 *  @version 2.8
 *  @since   2.8
 *  @define willNotTerminateInf
 *  @define mayNotTerminateInf
 */
trait IndexedSeqLike[+A, +Repr] extends SeqLike[A, Repr] {
  self =>

<<<<<<< HEAD
=======
  def seq: IndexedSeq[A]
  override def hashCode() = util.MurmurHash3.seqHash(seq)  // TODO - can we get faster via "indexedSeqHash" ?

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override protected[this] def thisCollection: IndexedSeq[A] = this.asInstanceOf[IndexedSeq[A]]
  override protected[this] def toCollection(repr: Repr): IndexedSeq[A] = repr.asInstanceOf[IndexedSeq[A]]

  /** The class of the iterator returned by the `iterator` method.
   *  multiple `take`, `drop`, and `slice` operations on this iterator are bunched
   *  together for better efficiency.
   */
  // pre: start >= 0, end <= self.length
  @SerialVersionUID(1756321872811029277L)
<<<<<<< HEAD
  protected class Elements(start: Int, end: Int) extends BufferedIterator[A] with Serializable {
=======
  protected class Elements(start: Int, end: Int) extends AbstractIterator[A] with BufferedIterator[A] with Serializable {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def initialSize = if (end <= start) 0 else end - start
    private var index = start
    private def available = (end - index) max 0

    def hasNext: Boolean = index < end

<<<<<<< HEAD
    def next: A = {
=======
    def next(): A = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (index >= end)
        Iterator.empty.next

      val x = self(index)
      index += 1
<<<<<<< HEAD
      x      
=======
      x
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    def head = {
      if (index >= end)
        Iterator.empty.next
<<<<<<< HEAD
      
      self(index)
    }
    
=======

      self(index)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def drop(n: Int): Iterator[A] =
      if (n <= 0) new Elements(index, end)
      else if (index + n >= end) new Elements(end, end)
      else new Elements(index + n, end)
    override def take(n: Int): Iterator[A] =
      if (n <= 0) Iterator.empty
      else if (n <= available) new Elements(index, index + n)
      else new Elements(index, end)
    override def slice(from: Int, until: Int): Iterator[A] =
      this take until drop from
  }

  override /*IterableLike*/
  def iterator: Iterator[A] = new Elements(0, length)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Overridden for efficiency */
  override def toBuffer[A1 >: A]: mutable.Buffer[A1] = {
    val result = new mutable.ArrayBuffer[A1](size)
    copyToBuffer(result)
    result
  }
}
<<<<<<< HEAD

=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
