/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

import generic._
import mutable.ListBuffer
import immutable.List
import scala.util.control.Breaks._

/** A template trait for linear sequences of type `LinearSeq[A]`  which optimizes
 *  the implementation of several methods under the assumption of fast linear access.
 *
 *  $linearSeqInfo
 */
trait LinearSeqOptimized[+A, +Repr <: LinearSeqOptimized[A, Repr]] extends LinearSeqLike[A, Repr] { self: Repr =>

  def isEmpty: Boolean

  def head: A

  def tail: Repr

  /** The length of the $coll.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $willNotTerminateInf
   *
   *  Note: the execution of `length` may take time proportial to the length of the sequence.
   */
  def length: Int = {
    var these = self
    var len = 0
    while (!these.isEmpty) {
      len += 1
      these = these.tail
    }
    len
  }

  /** Selects an element by its index in the $coll.
   *  Note: the execution of `apply` may take time proportial to the index value.
   *  @throws `IndexOutOfBoundsException` if `idx` does not satisfy `0 <= idx < length`.
   */
  def apply(n: Int): A = {
    val rest = drop(n)
    if (n < 0 || rest.isEmpty) throw new IndexOutOfBoundsException("" + n)
    rest.head
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
=======
  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def foreach[B](f: A => B) {
    var these = this
    while (!these.isEmpty) {
      f(these.head)
      these = these.tail
    }
  }

<<<<<<< HEAD
                                                                                   
  override /*IterableLike*/ 
=======

  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def forall(p: A => Boolean): Boolean = {
    var these = this
    while (!these.isEmpty) {
      if (!p(these.head)) return false
      these = these.tail
    }
    true
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
=======
  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def exists(p: A => Boolean): Boolean = {
    var these = this
    while (!these.isEmpty) {
      if (p(these.head)) return true
      these = these.tail
    }
    false
  }

<<<<<<< HEAD
  override /*TraversableLike*/ 
=======
  override /*TraversableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def count(p: A => Boolean): Int = {
    var these = this
    var cnt = 0
    while (!these.isEmpty) {
      if (p(these.head)) cnt += 1
      these = these.tail
    }
    cnt
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
=======
  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def find(p: A => Boolean): Option[A] = {
    var these = this
    while (!these.isEmpty) {
      if (p(these.head)) return Some(these.head)
      these = these.tail
    }
    None
  }

<<<<<<< HEAD
  override /*TraversableLike*/ 
=======
  override /*TraversableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def foldLeft[B](z: B)(f: (B, A) => B): B = {
    var acc = z
    var these = this
    while (!these.isEmpty) {
      acc = f(acc, these.head)
      these = these.tail
    }
    acc
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
  def foldRight[B](z: B)(f: (A, B) => B): B = 
    if (this.isEmpty) z
    else f(head, tail.foldRight(z)(f))

  override /*TraversableLike*/ 
  def reduceLeft[B >: A](f: (B, A) => B): B = 
    if (isEmpty) throw new UnsupportedOperationException("empty.reduceLeft")
    else tail.foldLeft[B](head)(f)

  override /*IterableLike*/ 
  def reduceRight[B >: A](op: (A, B) => B): B = 
    if (isEmpty) throw new UnsupportedOperationException("Nil.reduceRight")
    else if (tail.isEmpty) head
    else op(head, tail.reduceRight(op))
  
  override /*TraversableLike*/ 
=======
  override /*IterableLike*/
  def foldRight[B](z: B)(f: (A, B) => B): B =
    if (this.isEmpty) z
    else f(head, tail.foldRight(z)(f))

  override /*TraversableLike*/
  def reduceLeft[B >: A](f: (B, A) => B): B =
    if (isEmpty) throw new UnsupportedOperationException("empty.reduceLeft")
    else tail.foldLeft[B](head)(f)

  override /*IterableLike*/
  def reduceRight[B >: A](op: (A, B) => B): B =
    if (isEmpty) throw new UnsupportedOperationException("Nil.reduceRight")
    else if (tail.isEmpty) head
    else op(head, tail.reduceRight(op))

  override /*TraversableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def last: A = {
    if (isEmpty) throw new NoSuchElementException
    var these = this
    var nx = these.tail
    while (!nx.isEmpty) {
      these = nx
      nx = nx.tail
    }
    these.head
  }
<<<<<<< HEAD
  
  override /*IterableLike*/ 
=======

  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def take(n: Int): Repr = {
    val b = newBuilder
    var i = 0
    var these = repr
    while (!these.isEmpty && i < n) {
      i += 1
      b += these.head
      these = these.tail
    }
    b.result
  }
<<<<<<< HEAD
  
  override /*TraversableLike*/ 
=======

  override /*TraversableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def drop(n: Int): Repr = {
    var these: Repr = repr
    var count = n
    while (!these.isEmpty && count > 0) {
      these = these.tail
      count -= 1
    }
    // !!! This line should actually be something like:
    //   newBuilder ++= these result
    // since we are in collection.*, not immutable.*.
    // However making that change will pessimize all the
    // immutable linear seqs (like list) which surely expect
    // drop to share.  (Or at least it would penalize List if
    // it didn't override drop.  It would be a lot better if
    // the leaf collections didn't override so many methods.)
    //
    // Upshot: MutableList is broken and passes part of the
    // original list as the result of drop.
    these
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
=======
  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def dropRight(n: Int): Repr = {
    val b = newBuilder
    var these = this
    var lead = this drop n
    while (!lead.isEmpty) {
      b += these.head
      these = these.tail
      lead = lead.tail
    }
    b.result
  }

<<<<<<< HEAD
  override /*IterableLike*/ 
=======
  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def slice(from: Int, until: Int): Repr = {
    var these: Repr = repr
    var count = from max 0
    if (until <= count)
      return newBuilder.result

    val b = newBuilder
    var sliceElems = until - count
    while (these.nonEmpty && count > 0) {
      these = these.tail
      count -= 1
    }
    while (these.nonEmpty && sliceElems > 0) {
      sliceElems -= 1
      b += these.head
      these = these.tail
    }
<<<<<<< HEAD
    b.result    
  }

  override /*IterableLike*/ 
=======
    b.result
  }

  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def takeWhile(p: A => Boolean): Repr = {
    val b = newBuilder
    var these = this
    while (!these.isEmpty && p(these.head)) {
      b += these.head
      these = these.tail
    }
    b.result
  }

<<<<<<< HEAD
  override /*TraversableLike*/ 
=======
  override /*TraversableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def span(p: A => Boolean): (Repr, Repr) = {
    var these: Repr = repr
    val b = newBuilder
    while (!these.isEmpty && p(these.head)) {
      b += these.head
      these = these.tail
    }
    (b.result, these)
<<<<<<< HEAD
  }  

  override /*IterableLike*/ 
=======
  }

  override /*IterableLike*/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def sameElements[B >: A](that: GenIterable[B]): Boolean = that match {
    case that1: LinearSeq[_] =>
      var these = this
      var those = that1
      while (!these.isEmpty && !those.isEmpty && these.head == those.head) {
        these = these.tail
        those = those.tail
      }
      these.isEmpty && those.isEmpty
    case _ =>
      super.sameElements(that)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override /*SeqLike*/
  def lengthCompare(len: Int): Int =  {
    var i = 0
    var these = self
    while (!these.isEmpty && i <= len) {
      i += 1
      these = these.tail
    }
    i - len
  }

  override /*SeqLike*/
  def isDefinedAt(x: Int): Boolean = x >= 0 && lengthCompare(x) > 0

  override /*SeqLike*/
  def segmentLength(p: A => Boolean, from: Int): Int = {
    var i = 0
    var these = this drop from
    while (!these.isEmpty && p(these.head)) {
      i += 1
      these = these.tail
    }
    i
  }

  override /*SeqLike*/
  def indexWhere(p: A => Boolean, from: Int): Int = {
    var i = from
    var these = this drop from
    while (these.nonEmpty) {
      if (p(these.head))
        return i
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      i += 1
      these = these.tail
    }
    -1
  }

  override /*SeqLike*/
  def lastIndexWhere(p: A => Boolean, end: Int): Int = {
    var i = 0
    var these = this
    var last = -1
    while (!these.isEmpty && i <= end) {
      if (p(these.head)) last = i
      these = these.tail
      i += 1
    }
    last
  }
}
