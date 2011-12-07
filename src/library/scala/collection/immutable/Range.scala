/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection.immutable

import scala.collection.parallel.immutable.ParRange
import annotation.bridge

/** The `Range` class represents integer values in range
 *  ''[start;end)'' with non-zero step value `step`.
 *  It's a special case of an indexed sequence.
 *  For example:
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  {{{
 *     val r1 = 0 until 10
 *     val r2 = r1.start until r1.end by r1.step + 1
 *     println(r2.length) // = 5
 *  }}}
<<<<<<< HEAD
 *  
 *  @param start      the start of this range.
 *  @param end        the exclusive end of the range.
 *  @param step       the step for the range.
 *  
=======
 *
 *  @param start      the start of this range.
 *  @param end        the exclusive end of the range.
 *  @param step       the step for the range.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Martin Odersky
 *  @author Paul Phillips
 *  @version 2.8
 *  @since   2.5
<<<<<<< HEAD
=======
 *  @see [[http://docs.scala-lang.org/overviews/collections/concrete-immutable-collection-classes.html#ranges "Scala's Collection Library overview"]]
 *  section on `Ranges` for more information.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll Range
 *  @define coll range
 *  @define mayNotTerminateInf
 *  @define willNotTerminateInf
 *  @define doesNotUseBuilders
 *    '''Note:''' this method does not use builders to construct a new range,
 *         and its complexity is O(1).
 */
@SerialVersionUID(7618862778670199309L)
class Range(val start: Int, val end: Int, val step: Int)
<<<<<<< HEAD
extends IndexedSeq[Int]
=======
extends collection.AbstractSeq[Int]
   with IndexedSeq[Int]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   with collection.CustomParallelizable[Int, ParRange]
   with Serializable
{
  override def par = new ParRange(this)

  // This member is designed to enforce conditions:
  //   (step != 0) && (length <= Int.MaxValue),
  // but cannot be evaluated eagerly because we have a pattern where ranges
  // are constructed like:    "x to y by z"
  // The "x to y" piece should not trigger an exception. So the calculation
  // is delayed, which means it will not fail fast for those cases where failing
  // was correct.
  private lazy val numRangeElements: Int = Range.count(start, end, step, isInclusive)

  protected def copy(start: Int, end: Int, step: Int): Range = new Range(start, end, step)

  /** Create a new range with the `start` and `end` values of this range and
   *  a new `step`.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return a new range with a different step
   */
  def by(step: Int): Range = copy(start, end, step)

  def isInclusive = false

  @inline final override def foreach[@specialized(Unit) U](f: Int => U) {
    if (length > 0) {
      val last = this.last
      var i = start
      while (i != last) {
        f(i)
        i += step
      }
      f(i)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def length: Int = numRangeElements
  override lazy val last: Int =
    if (length == 0) Nil.last
    else locationAfterN(length - 1)
<<<<<<< HEAD
  
  final override def isEmpty = length == 0 
=======

  final override def isEmpty = length == 0
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  @inline
  final def apply(idx: Int): Int = {
    if (idx < 0 || idx >= length) throw new IndexOutOfBoundsException(idx.toString)
    locationAfterN(idx)
  }

  /** Creates a new range containing the first `n` elements of this range.
<<<<<<< HEAD
   *  
   *  $doesNotUseBuilders
   *  
=======
   *
   *  $doesNotUseBuilders
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n  the number of elements to take.
   *  @return   a new range consisting of `n` first elements.
   */
  final override def take(n: Int): Range = (
    if (n <= 0 || length == 0) newEmptyRange(start)
    else if (n >= length) this
    else new Range.Inclusive(start, locationAfterN(n - 1), step)
  )

  /** Creates a new range containing all the elements of this range except the first `n` elements.
<<<<<<< HEAD
   *  
   *  $doesNotUseBuilders
   *  
=======
   *
   *  $doesNotUseBuilders
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n  the number of elements to drop.
   *  @return   a new range consisting of all the elements of this range except `n` first elements.
   */
  final override def drop(n: Int): Range = (
    if (n <= 0 || length == 0) this
    else if (n >= length) newEmptyRange(end)
    else copy(locationAfterN(n), end, step)
  )
<<<<<<< HEAD
  
  /** Creates a new range containing all the elements of this range except the last one.
   *  
   *  $doesNotUseBuilders
   *  
=======

  /** Creates a new range containing all the elements of this range except the last one.
   *
   *  $doesNotUseBuilders
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return  a new range consisting of all the elements of this range except the last one.
   */
  final override def init: Range = {
    if (isEmpty)
      Nil.init
<<<<<<< HEAD
      
    dropRight(1)
  }
  
  /** Creates a new range containing all the elements of this range except the first one.
   *  
   *  $doesNotUseBuilders
   *  
=======

    dropRight(1)
  }

  /** Creates a new range containing all the elements of this range except the first one.
   *
   *  $doesNotUseBuilders
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return  a new range consisting of all the elements of this range except the first one.
   */
  final override def tail: Range = {
    if (isEmpty)
      Nil.tail
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    drop(1)
  }

  // Counts how many elements from the start meet the given test.
  private def skipCount(p: Int => Boolean): Int = {
    var current = start
    var counted = 0
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    while (counted < length && p(current)) {
      counted += 1
      current += step
    }
    counted
  }
  // Tests whether a number is within the endpoints, without testing
  // whether it is a member of the sequence (i.e. when step > 1.)
  private def isWithinBoundaries(elem: Int) = (length > 0) && (
    (step > 0 && start <= elem && elem <= last ) ||
    (step < 0 &&  last <= elem && elem <= start)
  )
  // Methods like apply throw exceptions on invalid n, but methods like take/drop
  // are forgiving: therefore the checks are with the methods.
  private def locationAfterN(n: Int) = start + (step * n)

  // When one drops everything.  Can't ever have unchecked operations
  // like "end + 1" or "end - 1" because ranges involving Int.{ MinValue, MaxValue }
  // will overflow.  This creates an exclusive range where start == end
  // based on the given value.
  private def newEmptyRange(value: Int) = new Range(value, value, step)
<<<<<<< HEAD
  
  final override def takeWhile(p: Int => Boolean): Range = take(skipCount(p))
  final override def dropWhile(p: Int => Boolean): Range = drop(skipCount(p))  
  final override def span(p: Int => Boolean): (Range, Range) = splitAt(skipCount(p))
  
  /** Creates a pair of new ranges, first consisting of elements before `n`, and the second
   *  of elements after `n`.
   *  
   *  $doesNotUseBuilders
   */
  final override def splitAt(n: Int) = (take(n), drop(n))
  
  /** Creates a new range consisting of the `length - n` last elements of the range.
   *  
   *  $doesNotUseBuilders
   */
  final override def takeRight(n: Int): Range = drop(length - n)
  
  /** Creates a new range consisting of the initial `length - n` elements of the range.
   *  
   *  $doesNotUseBuilders
   */
  final override def dropRight(n: Int): Range = take(length - n)
  
  /** Returns the reverse of this range.
   *  
=======

  final override def takeWhile(p: Int => Boolean): Range = take(skipCount(p))
  final override def dropWhile(p: Int => Boolean): Range = drop(skipCount(p))
  final override def span(p: Int => Boolean): (Range, Range) = splitAt(skipCount(p))

  /** Creates a pair of new ranges, first consisting of elements before `n`, and the second
   *  of elements after `n`.
   *
   *  $doesNotUseBuilders
   */
  final override def splitAt(n: Int) = (take(n), drop(n))

  /** Creates a new range consisting of the `length - n` last elements of the range.
   *
   *  $doesNotUseBuilders
   */
  final override def takeRight(n: Int): Range = drop(length - n)

  /** Creates a new range consisting of the initial `length - n` elements of the range.
   *
   *  $doesNotUseBuilders
   */
  final override def dropRight(n: Int): Range = take(length - n)

  /** Returns the reverse of this range.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $doesNotUseBuilders
   */
  final override def reverse: Range =
    if (length > 0) new Range.Inclusive(last, start, -step)
    else this

  /** Make range inclusive.
   */
<<<<<<< HEAD
  def inclusive = 
=======
  def inclusive =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (isInclusive) this
    else new Range.Inclusive(start, end, step)

  final def contains(x: Int) = isWithinBoundaries(x) && ((x - start) % step == 0)
<<<<<<< HEAD
  
  override def toIterable = this
  
  override def toSeq = this
  
=======

  final override def sum[B >: Int](implicit num: Numeric[B]): Int = {
    val len = length
    if (len == 0) 0
    else if (len == 1) head
    else (len.toLong * (head + last) / 2).toInt
  }

  override def toIterable = this

  override def toSeq = this

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def equals(other: Any) = other match {
    case x: Range =>
      (x canEqual this) && (length == x.length) && (
        (length == 0) ||                      // all empty sequences are equal
        (start == x.start && last == x.last)  // same length and same endpoints implies equality
      )
<<<<<<< HEAD
    case _ => 
=======
    case _ =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      super.equals(other)
  }
  /** Note: hashCode can't be overridden without breaking Seq's
   *  equals contract.
   */

  override def toString() = {
    val endStr = if (length > Range.MAX_PRINT) ", ... )" else ")"
    take(Range.MAX_PRINT).mkString("Range(", ", ", endStr)
  }
}

/** A companion object for the `Range` class.
 */
object Range {
  private[immutable] val MAX_PRINT = 512  // some arbitrary value

  /** Counts in "Long arithmetic" so we can recognize overflow.
   */
  def count(start: Int, end: Int, step: Int): Int =
    count(start, end, step, false)

  def count(start: Int, end: Int, step: Int, isInclusive: Boolean): Int = {
    // faster path for the common counting range
    if (start >= 0 && end > start && end < scala.Int.MaxValue && step == 1)
      (end - start) + ( if (isInclusive) 1 else 0 )
    else
      NumericRange.count[Long](start, end, step, isInclusive)
  }

  class Inclusive(start: Int, end: Int, step: Int) extends Range(start, end, step) {
//    override def par = new ParRange(this)
    override def isInclusive = true
    override protected def copy(start: Int, end: Int, step: Int): Range = new Inclusive(start, end, step)
  }

  /** Make a range from `start` until `end` (exclusive) with given step value.
   * @note step != 0
   */
  def apply(start: Int, end: Int, step: Int): Range = new Range(start, end, step)

  /** Make an range from `start` to `end` inclusive with step value 1.
   */
<<<<<<< HEAD
  def apply(start: Int, end: Int): Range = new Range(start, end, 1) 
=======
  def apply(start: Int, end: Int): Range = new Range(start, end, 1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** Make an inclusive range from start to end with given step value.
   * @note step != 0
   */
  @inline def inclusive(start: Int, end: Int, step: Int): Range.Inclusive = new Inclusive(start, end, step)

  /** Make an inclusive range from start to end with step value 1.
   */
<<<<<<< HEAD
  @inline def inclusive(start: Int, end: Int): Range.Inclusive = new Inclusive(start, end, 1) 
=======
  @inline def inclusive(start: Int, end: Int): Range.Inclusive = new Inclusive(start, end, 1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  // BigInt and Long are straightforward generic ranges.
  object BigInt {
    def apply(start: BigInt, end: BigInt, step: BigInt) = NumericRange(start, end, step)
    def inclusive(start: BigInt, end: BigInt, step: BigInt) = NumericRange.inclusive(start, end, step)
  }

  object Long {
    def apply(start: Long, end: Long, step: Long) = NumericRange(start, end, step)
    def inclusive(start: Long, end: Long, step: Long) = NumericRange.inclusive(start, end, step)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // BigDecimal uses an alternative implementation of Numeric in which
  // it pretends to be Integral[T] instead of Fractional[T].  See Numeric for
  // details.  The intention is for it to throw an exception anytime
  // imprecision or surprises might result from anything, although this may
  // not yet be fully implemented.
  object BigDecimal {
    implicit val bigDecAsIntegral = scala.math.Numeric.BigDecimalAsIfIntegral
<<<<<<< HEAD
    
    def apply(start: BigDecimal, end: BigDecimal, step: BigDecimal) =
      NumericRange(start, end, step)
    def inclusive(start: BigDecimal, end: BigDecimal, step: BigDecimal) = 
=======

    def apply(start: BigDecimal, end: BigDecimal, step: BigDecimal) =
      NumericRange(start, end, step)
    def inclusive(start: BigDecimal, end: BigDecimal, step: BigDecimal) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      NumericRange.inclusive(start, end, step)
  }

  // Double works by using a BigDecimal under the hood for precise
  // stepping, but mapping the sequence values back to doubles with
  // .doubleValue.  This constructs the BigDecimals by way of the
  // String constructor (valueOf) instead of the Double one, which
  // is necessary to keep 0.3d at 0.3 as opposed to
  // 0.299999999999999988897769753748434595763683319091796875 or so.
  object Double {
    implicit val bigDecAsIntegral = scala.math.Numeric.BigDecimalAsIfIntegral
    implicit val doubleAsIntegral = scala.math.Numeric.DoubleAsIfIntegral
    def toBD(x: Double): BigDecimal = scala.math.BigDecimal valueOf x
<<<<<<< HEAD
    
    def apply(start: Double, end: Double, step: Double) =
      BigDecimal(toBD(start), toBD(end), toBD(step)) mapRange (_.doubleValue)
    
    def inclusive(start: Double, end: Double, step: Double) =
      BigDecimal.inclusive(toBD(start), toBD(end), toBD(step)) mapRange (_.doubleValue)
  }
  
=======

    def apply(start: Double, end: Double, step: Double) =
      BigDecimal(toBD(start), toBD(end), toBD(step)) mapRange (_.doubleValue)

    def inclusive(start: Double, end: Double, step: Double) =
      BigDecimal.inclusive(toBD(start), toBD(end), toBD(step)) mapRange (_.doubleValue)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // As there is no appealing default step size for not-really-integral ranges,
  // we offer a partially constructed object.
  class Partial[T, U](f: T => U) {
    def by(x: T): U = f(x)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Illustrating genericity with Int Range, which should have the same behavior
  // as the original Range class.  However we leave the original Range
  // indefinitely, for performance and because the compiler seems to bootstrap
  // off it and won't do so with our parameterized version without modifications.
  object Int {
    def apply(start: Int, end: Int, step: Int) = NumericRange(start, end, step)
    def inclusive(start: Int, end: Int, step: Int) = NumericRange.inclusive(start, end, step)
  }

  @deprecated("use Range instead", "2.9.0")
  trait ByOne extends Range {
//    @bridge override def foreach[@specialized(Unit) U](f: Int => U) =
//      super.foreach(f)
  }
}
