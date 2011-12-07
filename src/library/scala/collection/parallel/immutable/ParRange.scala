/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel.immutable

import scala.collection.immutable.Range
import scala.collection.parallel.Combiner
import scala.collection.generic.CanCombineFrom
import scala.collection.parallel.IterableSplitter
import scala.collection.Iterator

/** Parallel ranges.
<<<<<<< HEAD
 *  
 *  $paralleliterableinfo
 *  
 *  $sideeffects
 *  
 *  @param range    the sequential range this parallel range was obtained from
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
 *  @param range    the sequential range this parallel range was obtained from
 *
 *  @author Aleksandar Prokopec
 *  @since 2.9
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll immutable.ParRange
 *  @define coll immutable parallel range
 */
@SerialVersionUID(1L)
class ParRange(val range: Range)
extends ParSeq[Int]
   with Serializable
{
self =>
<<<<<<< HEAD
  
  override def seq = range
  
  @inline final def length = range.length
  
  @inline final def apply(idx: Int) = range.apply(idx);
  
  def splitter = new ParRangeIterator with SCPI
  
  type SCPI = SignalContextPassingIterator[ParRangeIterator]
  
=======

  override def seq = range

  @inline final def length = range.length

  @inline final def apply(idx: Int) = range.apply(idx);

  def splitter = new ParRangeIterator with SCPI

  type SCPI = SignalContextPassingIterator[ParRangeIterator]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class ParRangeIterator(range: Range = self.range)
  extends ParIterator {
  me: SignalContextPassingIterator[ParRangeIterator] =>
    override def toString = "ParRangeIterator(over: " + range + ")"
    private var ind = 0
    private val len = range.length
<<<<<<< HEAD
    
    final def remaining = len - ind
    
    final def hasNext = ind < len
    
=======

    final def remaining = len - ind

    final def hasNext = ind < len

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    final def next = if (hasNext) {
      val r = range.apply(ind)
      ind += 1
      r
    } else Iterator.empty.next
<<<<<<< HEAD
    
    private def rangeleft = range.drop(ind)
    
    def dup = new ParRangeIterator(rangeleft) with SCPI
    
=======

    private def rangeleft = range.drop(ind)

    def dup = new ParRangeIterator(rangeleft) with SCPI

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def split = {
      val rleft = rangeleft
      val elemleft = rleft.length
      if (elemleft < 2) Seq(new ParRangeIterator(rleft) with SCPI)
      else Seq(
        new ParRangeIterator(rleft.take(elemleft / 2)) with SCPI,
        new ParRangeIterator(rleft.drop(elemleft / 2)) with SCPI
      )
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def psplit(sizes: Int*) = {
      var rleft = rangeleft
      for (sz <- sizes) yield {
        val fronttaken = rleft.take(sz)
        rleft = rleft.drop(sz)
        new ParRangeIterator(fronttaken) with SCPI
      }
    }
<<<<<<< HEAD
    
    /* accessors */
    
=======

    /* accessors */

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def foreach[U](f: Int => U): Unit = {
      rangeleft.foreach(f)
      ind = len
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def reduce[U >: Int](op: (U, U) => U): U = {
      val r = rangeleft.reduceLeft(op)
      ind = len
      r
    }
<<<<<<< HEAD
    
    /* transformers */
    
=======

    /* transformers */

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def map2combiner[S, That](f: Int => S, cb: Combiner[S, That]): Combiner[S, That] = {
      while (hasNext) {
        cb += f(next)
      }
      cb
    }
<<<<<<< HEAD
  }  
=======
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

object ParRange {
  def apply(start: Int, end: Int, step: Int, inclusive: Boolean) = new ParRange(
    if (inclusive) new Range.Inclusive(start, end, step)
    else new Range(start, end, step)
  )
}
