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
import parallel.mutable.ParArray

/** An implementation of the `Buffer` class using an array to
 *  represent the assembled sequence internally. Append, update and random
 *  access take constant time (amortized time). Prepends and removes are
 *  linear in the buffer size.
 *
 *  @author  Matthias Zenger
 *  @author  Martin Odersky
 *  @version 2.8
 *  @since   1
<<<<<<< HEAD
 *  
 *  @tparam A    the type of this arraybuffer's elements.
 *  
=======
 *  @see [[http://docs.scala-lang.org/overviews/collections/concrete-mutable-collection-classes.html#array_buffers "Scala's Collection Library overview"]]
 *  section on `Array Buffers` for more information.

 *
 *  @tparam A    the type of this arraybuffer's elements.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll ArrayBuffer
 *  @define coll arraybuffer
 *  @define thatinfo the class of the returned collection. In the standard library configuration,
 *    `That` is always `ArrayBuffer[B]` because an implicit of type `CanBuildFrom[ArrayBuffer, B, ArrayBuffer[B]]`
 *    is defined in object `ArrayBuffer`.
 *  @define bfinfo an implicit value of class `CanBuildFrom` which determines the
 *    result class `That` from the current representation type `Repr`
 *    and the new element type `B`. This is usually the `canBuildFrom` value
 *    defined in object `ArrayBuffer`.
<<<<<<< HEAD
 *  @define orderDependent 
=======
 *  @define orderDependent
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define orderDependentFold
 *  @define mayNotTerminateInf
 *  @define willNotTerminateInf
 */
@SerialVersionUID(1529165946227428979L)
<<<<<<< HEAD
class ArrayBuffer[A](override protected val initialSize: Int) 
  extends Buffer[A] 
     with GenericTraversableTemplate[A, ArrayBuffer]
     with BufferLike[A, ArrayBuffer[A]]
     with IndexedSeqOptimized[A, ArrayBuffer[A]]
     with Builder[A, ArrayBuffer[A]] 
=======
class ArrayBuffer[A](override protected val initialSize: Int)
  extends AbstractBuffer[A]
     with Buffer[A]
     with GenericTraversableTemplate[A, ArrayBuffer]
     with BufferLike[A, ArrayBuffer[A]]
     with IndexedSeqOptimized[A, ArrayBuffer[A]]
     with Builder[A, ArrayBuffer[A]]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     with ResizableArray[A]
     with CustomParallelizable[A, ParArray[A]]
     with Serializable {

  override def companion: GenericCompanion[ArrayBuffer] = ArrayBuffer

  import scala.collection.Traversable

  def this() = this(16)

  def clear() { reduceToSize(0) }

  override def sizeHint(len: Int) {
    if (len > size && len >= 1) {
      val newarray = new Array[AnyRef](len)
      compat.Platform.arraycopy(array, 0, newarray, 0, size0)
      array = newarray
    }
  }
<<<<<<< HEAD
  
  override def par = ParArray.handoff[A](array.asInstanceOf[Array[A]], size)
  
=======

  override def par = ParArray.handoff[A](array.asInstanceOf[Array[A]], size)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Appends a single element to this buffer and returns
   *  the identity of the buffer. It takes constant amortized time.
   *
   *  @param elem  the element to append.
   *  @return      the updated buffer.
   */
  def +=(elem: A): this.type = {
    ensureSize(size0 + 1)
    array(size0) = elem.asInstanceOf[AnyRef]
    size0 += 1
    this
  }

  /** Appends a number of elements provided by a traversable object.
   *  The identity of the buffer is returned.
   *
   *  @param xs    the traversable object.
   *  @return      the updated buffer.
   */
  override def ++=(xs: TraversableOnce[A]): this.type = xs match {
<<<<<<< HEAD
    case v: IndexedSeq[_] =>
=======
    case v: collection.IndexedSeqLike[_, _] =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val n = v.length
      ensureSize(size0 + n)
      v.copyToArray(array.asInstanceOf[scala.Array[Any]], size0, n)
      size0 += n
      this
    case _ =>
      super.++=(xs)
  }

  /** Prepends a single element to this buffer and returns
<<<<<<< HEAD
   *  the identity of the buffer. It takes time linear in 
   *  the buffer size.
   *
   *  @param elem  the element to append.
   *  @return      the updated buffer. 
=======
   *  the identity of the buffer. It takes time linear in
   *  the buffer size.
   *
   *  @param elem  the element to append.
   *  @return      the updated buffer.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def +=:(elem: A): this.type = {
    ensureSize(size0 + 1)
    copy(0, 1, size0)
    array(0) = elem.asInstanceOf[AnyRef]
    size0 += 1
    this
  }
<<<<<<< HEAD
   
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Prepends a number of elements provided by a traversable object.
   *  The identity of the buffer is returned.
   *
   *  @param xs    the traversable object.
   *  @return      the updated buffer.
   */
  override def ++=:(xs: TraversableOnce[A]): this.type = { insertAll(0, xs.toTraversable); this }
<<<<<<< HEAD
  
  /** Inserts new elements at the index `n`. Opposed to method
   *  `update`, this method will not replace an element with a
   *  one. Instead, it will insert a new element at index `n`.
   *  
=======

  /** Inserts new elements at the index `n`. Opposed to method
   *  `update`, this method will not replace an element with a
   *  one. Instead, it will insert a new element at index `n`.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n     the index where a new element will be inserted.
   *  @param seq   the traversable object providing all elements to insert.
   *  @throws Predef.IndexOutOfBoundsException if `n` is out of bounds.
   */
  def insertAll(n: Int, seq: Traversable[A]) {
    if (n < 0 || n > size0) throw new IndexOutOfBoundsException(n.toString)
    val xs = seq.toList
    val len = xs.length
    ensureSize(size0 + len)
    copy(n, n + len, size0 - n)
    xs.copyToArray(array.asInstanceOf[scala.Array[Any]], n)
    size0 += len
  }
<<<<<<< HEAD
  
  /** Removes the element on a given index position. It takes time linear in
   *  the buffer size.
   *  
=======

  /** Removes the element on a given index position. It takes time linear in
   *  the buffer size.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n       the index which refers to the first element to delete.
   *  @param count   the number of elements to delete
   *  @throws Predef.IndexOutOfBoundsException if `n` is out of bounds.
   */
  override def remove(n: Int, count: Int) {
    require(count >= 0, "removing negative number of elements")
    if (n < 0 || n > size0 - count) throw new IndexOutOfBoundsException(n.toString)
    copy(n + count, n, size0 - (n + count))
    reduceToSize(size0 - count)
  }

  /** Removes the element at a given index position.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n  the index which refers to the element to delete.
   *  @return   the element that was formerly at position `n`.
   */
  def remove(n: Int): A = {
    val result = apply(n)
    remove(n, 1)
    result
  }

  /** Return a clone of this buffer.
   *
   *  @return an `ArrayBuffer` with the same elements.
   */
  override def clone(): ArrayBuffer[A] = new ArrayBuffer[A] ++= this

  def result: ArrayBuffer[A] = this

  /** Defines the prefix of the string representation.
   */
  override def stringPrefix: String = "ArrayBuffer"
<<<<<<< HEAD
  
}

/** Factory object for the `ArrayBuffer` class.
 *  
=======

}

/** Factory object for the `ArrayBuffer` class.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  $factoryInfo
 *  @define coll array buffer
 *  @define Coll ArrayBuffer
 */
object ArrayBuffer extends SeqFactory[ArrayBuffer] {
  /** $genericCanBuildFromInfo */
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, ArrayBuffer[A]] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]
  def newBuilder[A]: Builder[A, ArrayBuffer[A]] = new ArrayBuffer[A]
}

