/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


// generated by genprod on Thu Apr 29 17:52:16 CEST 2010  (with extra methods)

package scala

import scala.collection.{TraversableLike, IterableLike, IndexedSeqLike}
import scala.collection.generic.CanBuildFrom




/** Tuple2 is the canonical representation of a @see Product2 
 *  
 */
case class Tuple2[@specialized(Int, Long, Double) +T1, @specialized(Int, Long, Double) +T2](_1:T1,_2:T2)
  extends Product2[T1, T2]
{  
  override def toString() = "(" + _1 + "," + _2 + ")"  
  
  /** Swap the elements of the tuple */
  def swap: Tuple2[T2,T1] = Tuple2(_2, _1)

  def zip[Repr1, El1, El2, To](implicit w1:   T1 => TraversableLike[El1, Repr1],
                                        w2:   T2 => Iterable[El2],
                                        cbf1: CanBuildFrom[Repr1, (El1, El2), To]): To = {
    val coll1: TraversableLike[El1, Repr1] = _1
    val coll2: Iterable[El2] = _2
    val b1 = cbf1(coll1.repr)
    val elems2 = coll2.iterator

    for(el1 <- coll1)
      if(elems2.hasNext)
        b1 += ((el1, elems2.next))

    b1.result
  }

  /** Wraps a tuple in a `Zipped`, which supports 2-ary generalisations of map, flatMap, filter,...
   *
   * @see Zipped
   * $willNotTerminateInf
   */
  def zipped[Repr1, El1, Repr2, El2](implicit w1: T1 => TraversableLike[El1, Repr1], w2: T2 => IterableLike[El2, Repr2]): Zipped[Repr1, El1, Repr2, El2]
    = new Zipped[Repr1, El1, Repr2, El2](_1, _2)

  class Zipped[+Repr1, +El1, +Repr2, +El2](coll1: TraversableLike[El1, Repr1], coll2: IterableLike[El2, Repr2]) { // coll2: IterableLike for filter
    def map[B, To](f: (El1, El2) => B)(implicit cbf: CanBuildFrom[Repr1, B, To]): To = {
      val b = cbf(coll1.repr)
      b.sizeHint(coll1)
      val elems2 = coll2.iterator
      for(el1 <- coll1)
       if(elems2.hasNext)
         b += f(el1, elems2.next)

      b.result
    }

    def flatMap[B, To](f: (El1, El2) => TraversableOnce[B])(implicit cbf: CanBuildFrom[Repr1, B, To]): To = {
      val b = cbf(coll1.repr)
      val elems2 = coll2.iterator

      for(el1 <- coll1)
       if(elems2.hasNext)
         b ++= f(el1, elems2.next)

      b.result
    }

    def filter[To1, To2](f: (El1, El2) => Boolean)(implicit cbf1: CanBuildFrom[Repr1, El1, To1], cbf2: CanBuildFrom[Repr2, El2, To2]): (To1, To2) = {
      val b1 = cbf1(coll1.repr)
      val b2 = cbf2(coll2.repr)
      val elems2 = coll2.iterator

      for(el1 <- coll1) {
        if(elems2.hasNext) {
          val el2 = elems2.next
          if(f(el1, el2)) {
            b1 += el1
            b2 += el2
          }
        }
      }

      (b1.result, b2.result)
    }

    def exists(f: (El1, El2) => Boolean): Boolean = {
      var acc = false
      val elems2 = coll2.iterator

      for(el1 <- coll1)
       if(!acc && elems2.hasNext)
         acc = f(el1, elems2.next)

      acc
    }

    def forall(f: (El1, El2) => Boolean): Boolean = {
      var acc = true
      val elems2 = coll2.iterator

      for(el1 <- coll1)
       if(acc && elems2.hasNext)
         acc = f(el1, elems2.next)

      acc
    }

    def foreach[U](f: (El1, El2) => U): Unit = {
      val elems2 = coll2.iterator

      for(el1 <- coll1)
       if(elems2.hasNext)
         f(el1, elems2.next)
    }
  }

}
