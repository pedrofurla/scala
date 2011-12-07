/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

import generic._
<<<<<<< HEAD
import mutable.{ Builder, ListBuffer }
=======
import mutable.{ Builder }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import annotation.{tailrec, migration, bridge}
import annotation.unchecked.{ uncheckedVariance => uV }
import parallel.ParIterable

/** A template trait for traversable collections of type `Traversable[A]`.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  $traversableInfo
 *  @define mutability
 *  @define traversableInfo
 *  This is a base trait of all kinds of $mutability Scala collections. It
 *  implements the behavior common to all collections, in terms of a method
 *  `foreach` with signature:
 * {{{
 *     def foreach[U](f: Elem => U): Unit
 * }}}
<<<<<<< HEAD
 *  Collection classes mixing in this trait provide a concrete 
=======
 *  Collection classes mixing in this trait provide a concrete
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  `foreach` method which traverses all the
 *  elements contained in the collection, applying a given function to each.
 *  They also need to provide a method `newBuilder`
 *  which creates a builder for collections of the same kind.
<<<<<<< HEAD
 *  
 *  A traversable class might or might not have two properties: strictness
 *  and orderedness. Neither is represented as a type.
 *  
=======
 *
 *  A traversable class might or might not have two properties: strictness
 *  and orderedness. Neither is represented as a type.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  The instances of a strict collection class have all their elements
 *  computed before they can be used as values. By contrast, instances of
 *  a non-strict collection class may defer computation of some of their
 *  elements until after the instance is available as a value.
 *  A typical example of a non-strict collection class is a
 *  [[scala.collection.immutable/Stream]].
 *  A more general class of examples are `TraversableViews`.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  If a collection is an instance of an ordered collection class, traversing
 *  its elements with `foreach` will always visit elements in the
 *  same order, even for different runs of the program. If the class is not
 *  ordered, `foreach` can visit elements in different orders for
 *  different runs (but it will keep the same order in the same run).'
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  A typical example of a collection class which is not ordered is a
 *  `HashMap` of objects. The traversal order for hash maps will
 *  depend on the hash codes of its elements, and these hash codes might
 *  differ from one run to the next. By contrast, a `LinkedHashMap`
 *  is ordered because it's `foreach` method visits elements in the
 *  order they were inserted into the `HashMap`.
 *
 *  @author Martin Odersky
 *  @version 2.8
 *  @since   2.8
 *  @tparam A    the element type of the collection
 *  @tparam Repr the type of the actual collection containing the elements.
 *
 *  @define Coll Traversable
 *  @define coll traversable collection
 */
<<<<<<< HEAD
trait TraversableLike[+A, +Repr] extends HasNewBuilder[A, Repr] 
=======
trait TraversableLike[+A, +Repr] extends HasNewBuilder[A, Repr]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                                    with FilterMonadic[A, Repr]
                                    with TraversableOnce[A]
                                    with GenTraversableLike[A, Repr]
                                    with Parallelizable[A, ParIterable[A]]
{
  self =>

  import Traversable.breaks._

  /** The type implementing this traversable */
  protected type Self = Repr

  /** The collection of type $coll underlying this `TraversableLike` object.
   *  By default this is implemented as the `TraversableLike` object itself,
   *  but this can be overridden.
   */
  def repr: Repr = this.asInstanceOf[Repr]

  /** The underlying collection seen as an instance of `$Coll`.
   *  By default this is implemented as the current collection object itself,
   *  but this can be overridden.
   */
  protected[this] def thisCollection: Traversable[A] = this.asInstanceOf[Traversable[A]]

  /** A conversion from collections of type `Repr` to `$Coll` objects.
   *  By default this is implemented as just a cast, but this can be overridden.
   */
  protected[this] def toCollection(repr: Repr): Traversable[A] = repr.asInstanceOf[Traversable[A]]

  /** Creates a new builder for this collection type.
   */
  protected[this] def newBuilder: Builder[A, Repr]

  protected[this] def parCombiner = ParIterable.newCombiner[A]

  /** Applies a function `f` to all elements of this $coll.
<<<<<<< HEAD
   *  
   *    Note: this method underlies the implementation of most other bulk operations.
   *    It's important to implement this method in an efficient way.
   *  
   *
   *  @param  f   the function that is applied for its side-effect to every element.
   *              The result of function `f` is discarded.
   *              
   *  @tparam  U  the type parameter describing the result of function `f`. 
=======
   *
   *    Note: this method underlies the implementation of most other bulk operations.
   *    It's important to implement this method in an efficient way.
   *
   *
   *  @param  f   the function that is applied for its side-effect to every element.
   *              The result of function `f` is discarded.
   *
   *  @tparam  U  the type parameter describing the result of function `f`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *              This result will always be ignored. Typically `U` is `Unit`,
   *              but this is not necessary.
   *
   *  @usecase def foreach(f: A => Unit): Unit
   */
  def foreach[U](f: A => U): Unit

  /** Tests whether this $coll is empty.
   *
   *  @return    `true` if the $coll contain no elements, `false` otherwise.
   */
  def isEmpty: Boolean = {
    var result = true
    breakable {
      for (x <- this) {
        result = false
        break
      }
    }
    result
  }

  /** Tests whether this $coll is known to have a finite size.
   *  All strict collections are known to have finite size. For a non-strict
   *  collection such as `Stream`, the predicate returns `'''true'''` if all
   *  elements have been computed. It returns `'''false'''` if the stream is
   *  not yet evaluated to the end.
   *
<<<<<<< HEAD
   *  Note: many collection methods will not work on collections of infinite sizes. 
=======
   *  Note: many collection methods will not work on collections of infinite sizes.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  @return  `'''true'''` if this collection is known to have finite size,
   *           `'''false'''` otherwise.
   */
  def hasDefiniteSize = true

  def ++[B >: A, That](that: GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
    if (that.isInstanceOf[IndexedSeqLike[_, _]]) b.sizeHint(this, that.seq.size)
    b ++= thisCollection
    b ++= that.seq
    b.result
  }

  @bridge
  def ++[B >: A, That](that: TraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That =
    ++(that: GenTraversableOnce[B])(bf)

  /** As with `++`, returns a new collection containing the elements from the left operand followed by the
   *  elements from the right operand.
<<<<<<< HEAD
   *  It differs from `++` in that the right operand determines the type of
   *  the resulting collection rather than the left one.
=======
   *
   *  It differs from `++` in that the right operand determines the type of
   *  the resulting collection rather than the left one.
   *  Mnemonic: the COLon is on the side of the new COLlection type.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  Example:
   *  {{{
   *     scala> val x = List(1)
   *     x: List[Int] = List(1)
   *
   *     scala> val y = LinkedList(2)
   *     y: scala.collection.mutable.LinkedList[Int] = LinkedList(2)
   *
   *     scala> val z = x ++: y
   *     z: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2)
   *  }}}
   *  @param that   the traversable to append.
<<<<<<< HEAD
   *  @tparam B     the element type of the returned collection. 
=======
   *  @tparam B     the element type of the returned collection.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` which contains all elements
   *                of this $coll followed by all elements of `that`.
<<<<<<< HEAD
   * 
   *  @usecase def ++:[B](that: TraversableOnce[B]): $Coll[B]
   *  
=======
   *
   *  @usecase def ++:[B](that: TraversableOnce[B]): $Coll[B]
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return       a new $coll which contains all elements of this $coll
   *                followed by all elements of `that`.
   */
  def ++:[B >: A, That](that: TraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
    if (that.isInstanceOf[IndexedSeqLike[_, _]]) b.sizeHint(this, that.size)
    b ++= that
    b ++= thisCollection
    b.result
  }

<<<<<<< HEAD
  /** As with `++`, returns a new collection containing the elements from the left operand followed by the
   *  elements from the right operand.
   *  It differs from `++` in that the right operand determines the type of
   *  the resulting collection rather than the left one.
=======
  /** As with `++`, returns a new collection containing the elements from the
   *  left operand followed by the elements from the right operand.
   *
   *  It differs from `++` in that the right operand determines the type of
   *  the resulting collection rather than the left one.
   *  Mnemonic: the COLon is on the side of the new COLlection type.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  Example:
   *  {{{
   *     scala> val x = List(1)
   *     x: List[Int] = List(1)
   *
   *     scala> val y = LinkedList(2)
   *     y: scala.collection.mutable.LinkedList[Int] = LinkedList(2)
   *
   *     scala> val z = x ++: y
   *     z: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2)
   *  }}}
   *
   * This overload exists because: for the implementation of `++:` we should
   *  reuse that of `++` because many collections override it with more
   *  efficient versions.
   *
   *  Since `TraversableOnce` has no `++` method, we have to implement that
   *  directly, but `Traversable` and down can use the overload.
   *
   *  @param that   the traversable to append.
   *  @tparam B     the element type of the returned collection.
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` which contains all elements
   *                of this $coll followed by all elements of `that`.
<<<<<<< HEAD
   *
   *  @return       a new $coll which contains all elements of this $coll
   *                followed by all elements of `that`.
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def ++:[B >: A, That](that: Traversable[B])(implicit bf: CanBuildFrom[Repr, B, That]): That =
    (that ++ seq)(breakOut)

  def map[B, That](f: A => B)(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
<<<<<<< HEAD
    b.sizeHint(this) 
=======
    b.sizeHint(this)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    for (x <- this) b += f(x)
    b.result
  }

  def flatMap[B, That](f: A => GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
    for (x <- this) b ++= f(x).seq
    b.result
  }

  /** Selects all elements of this $coll which satisfy a predicate.
   *
   *  @param p     the predicate used to test elements.
   *  @return      a new $coll consisting of all elements of this $coll that satisfy the given
   *               predicate `p`. The order of the elements is preserved.
   */
  def filter(p: A => Boolean): Repr = {
    val b = newBuilder
<<<<<<< HEAD
    for (x <- this) 
=======
    for (x <- this)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (p(x)) b += x
    b.result
  }

  /** Selects all elements of this $coll which do not satisfy a predicate.
   *
   *  @param p     the predicate used to test elements.
   *  @return      a new $coll consisting of all elements of this $coll that do not satisfy the given
   *               predicate `p`. The order of the elements is preserved.
   */
  def filterNot(p: A => Boolean): Repr = filter(!p(_))

  def collect[B, That](pf: PartialFunction[A, B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
    for (x <- this) if (pf.isDefinedAt(x)) b += pf(x)
    b.result
  }

  /** Builds a new collection by applying an option-valued function to all
   *  elements of this $coll on which the function is defined.
   *
   *  @param f      the option-valued function which filters and maps the $coll.
   *  @tparam B     the element type of the returned collection.
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` resulting from applying the option-valued function
   *                `f` to each element and collecting all defined results.
   *                The order of the elements is preserved.
   *
   *  @usecase def filterMap[B](f: A => Option[B]): $Coll[B]
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param pf     the partial function which filters and maps the $coll.
   *  @return       a new $coll resulting from applying the given option-valued function
   *                `f` to each element and collecting all defined results.
   *                The order of the elements is preserved.
  def filterMap[B, That](f: A => Option[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
<<<<<<< HEAD
    for (x <- this) 
=======
    for (x <- this)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      f(x) match {
        case Some(y) => b += y
        case _ =>
      }
    b.result
  }
   */

  /** Partitions this $coll in two ${coll}s according to a predicate.
   *
   *  @param p the predicate on which to partition.
<<<<<<< HEAD
   *  @return  a pair of ${coll}s: the first $coll consists of all elements that 
=======
   *  @return  a pair of ${coll}s: the first $coll consists of all elements that
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *           satisfy the predicate `p` and the second $coll consists of all elements
   *           that don't. The relative order of the elements in the resulting ${coll}s
   *           is the same as in the original $coll.
   */
  def partition(p: A => Boolean): (Repr, Repr) = {
    val l, r = newBuilder
    for (x <- this) (if (p(x)) l else r) += x
    (l.result, r.result)
  }

  def groupBy[K](f: A => K): immutable.Map[K, Repr] = {
    val m = mutable.Map.empty[K, Builder[A, Repr]]
    for (elem <- this) {
      val key = f(elem)
      val bldr = m.getOrElseUpdate(key, newBuilder)
      bldr += elem
    }
    val b = immutable.Map.newBuilder[K, Repr]
    for ((k, v) <- m)
      b += ((k, v.result))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    b.result
  }

  /** Tests whether a predicate holds for all elements of this $coll.
   *
   *  $mayNotTerminateInf
   *
   *  @param   p     the predicate used to test elements.
   *  @return        `true` if the given predicate `p` holds for all elements
   *                 of this $coll, otherwise `false`.
   */
  def forall(p: A => Boolean): Boolean = {
    var result = true
    breakable {
      for (x <- this)
        if (!p(x)) { result = false; break }
    }
    result
  }

  /** Tests whether a predicate holds for some of the elements of this $coll.
   *
   *  $mayNotTerminateInf
   *
   *  @param   p     the predicate used to test elements.
   *  @return        `true` if the given predicate `p` holds for some of the
   *                 elements of this $coll, otherwise `false`.
   */
  def exists(p: A => Boolean): Boolean = {
    var result = false
    breakable {
      for (x <- this)
        if (p(x)) { result = true; break }
    }
    result
  }

  /** Finds the first element of the $coll satisfying a predicate, if any.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $mayNotTerminateInf
   *  $orderDependent
   *
   *  @param p    the predicate used to test elements.
   *  @return     an option value containing the first element in the $coll
   *              that satisfies `p`, or `None` if none exists.
   */
  def find(p: A => Boolean): Option[A] = {
    var result: Option[A] = None
    breakable {
      for (x <- this)
        if (p(x)) { result = Some(x); break }
    }
    result
  }

  def scan[B >: A, That](z: B)(op: (B, B) => B)(implicit cbf: CanBuildFrom[Repr, B, That]): That = scanLeft(z)(op)

  def scanLeft[B, That](z: B)(op: (B, A) => B)(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    val b = bf(repr)
    b.sizeHint(this, 1)
    var acc = z
    b += acc
    for (x <- this) { acc = op(acc, x); b += acc }
    b.result
  }

  @migration(2, 9,
    "This scanRight definition has changed in 2.9.\n" +
    "The previous behavior can be reproduced with scanRight.reverse."
  )
  def scanRight[B, That](z: B)(op: (A, B) => B)(implicit bf: CanBuildFrom[Repr, B, That]): That = {
    var scanned = List(z)
    var acc = z
    for (x <- reversed) {
      acc = op(x, acc)
      scanned ::= acc
    }
    val b = bf(repr)
    for (elem <- scanned) b += elem
    b.result
  }

  /** Selects the first element of this $coll.
   *  $orderDependent
   *  @return  the first element of this $coll.
   *  @throws `NoSuchElementException` if the $coll is empty.
   */
  def head: A = {
    var result: () => A = () => throw new NoSuchElementException
    breakable {
      for (x <- this) {
        result = () => x
        break
      }
    }
    result()
  }

  /** Optionally selects the first element.
   *  $orderDependent
<<<<<<< HEAD
   *  @return  the first element of this $coll if it is nonempty, `None` if it is empty.
=======
   *  @return  the first element of this $coll if it is nonempty,
   *           `None` if it is empty.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def headOption: Option[A] = if (isEmpty) None else Some(head)

  /** Selects all elements except the first.
   *  $orderDependent
   *  @return  a $coll consisting of all elements of this $coll
   *           except the first one.
   *  @throws `UnsupportedOperationException` if the $coll is empty.
<<<<<<< HEAD
   */ 
=======
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def tail: Repr = {
    if (isEmpty) throw new UnsupportedOperationException("empty.tail")
    drop(1)
  }

  /** Selects the last element.
    * $orderDependent
    * @return The last element of this $coll.
    * @throws NoSuchElementException If the $coll is empty.
    */
  def last: A = {
    var lst = head
    for (x <- this)
      lst = x
    lst
  }

  /** Optionally selects the last element.
   *  $orderDependent
<<<<<<< HEAD
   *  @return  the last element of this $coll$ if it is nonempty, `None` if it is empty.
=======
   *  @return  the last element of this $coll$ if it is nonempty,
   *           `None` if it is empty.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def lastOption: Option[A] = if (isEmpty) None else Some(last)

  /** Selects all elements except the last.
   *  $orderDependent
   *  @return  a $coll consisting of all elements of this $coll
   *           except the last one.
   *  @throws `UnsupportedOperationException` if the $coll is empty.
   */
  def init: Repr = {
    if (isEmpty) throw new UnsupportedOperationException("empty.init")
    var lst = head
    var follow = false
    val b = newBuilder
    b.sizeHint(this, -1)
    for (x <- this.seq) {
      if (follow) b += lst
      else follow = true
      lst = x
    }
    b.result
  }

  def take(n: Int): Repr = slice(0, n)

<<<<<<< HEAD
  def drop(n: Int): Repr = 
=======
  def drop(n: Int): Repr =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (n <= 0) {
      val b = newBuilder
      b.sizeHint(this)
      b ++= thisCollection result
    }
    else sliceWithKnownDelta(n, Int.MaxValue, -n)

<<<<<<< HEAD
  def slice(from: Int, until: Int): Repr = sliceWithKnownBound(math.max(from, 0), until)
=======
  def slice(from: Int, until: Int): Repr =
    sliceWithKnownBound(math.max(from, 0), until)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  // Precondition: from >= 0, until > 0, builder already configured for building.
  private[this] def sliceInternal(from: Int, until: Int, b: Builder[A, Repr]): Repr = {
    var i = 0
    breakable {
      for (x <- this.seq) {
        if (i >= from) b += x
        i += 1
        if (i >= until) break
      }
    }
    b.result
  }
  // Precondition: from >= 0
  private[scala] def sliceWithKnownDelta(from: Int, until: Int, delta: Int): Repr = {
    val b = newBuilder
    if (until <= from) b.result
    else {
      b.sizeHint(this, delta)
      sliceInternal(from, until, b)
    }
  }
  // Precondition: from >= 0
  private[scala] def sliceWithKnownBound(from: Int, until: Int): Repr = {
    val b = newBuilder
    if (until <= from) b.result
    else {
<<<<<<< HEAD
      b.sizeHintBounded(until - from, this)      
=======
      b.sizeHintBounded(until - from, this)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      sliceInternal(from, until, b)
    }
  }

  def takeWhile(p: A => Boolean): Repr = {
    val b = newBuilder
    breakable {
      for (x <- this) {
        if (!p(x)) break
        b += x
      }
    }
    b.result
  }

  def dropWhile(p: A => Boolean): Repr = {
    val b = newBuilder
    var go = false
    for (x <- this) {
      if (!p(x)) go = true
      if (go) b += x
    }
    b.result
  }

  def span(p: A => Boolean): (Repr, Repr) = {
    val l, r = newBuilder
    var toLeft = true
    for (x <- this) {
      toLeft = toLeft && p(x)
      (if (toLeft) l else r) += x
    }
    (l.result, r.result)
  }

  def splitAt(n: Int): (Repr, Repr) = {
    val l, r = newBuilder
    l.sizeHintBounded(n, this)
    if (n >= 0) r.sizeHint(this, -n)
    var i = 0
    for (x <- this) {
      (if (i < n) l else r) += x
      i += 1
    }
    (l.result, r.result)
  }

  /** Iterates over the tails of this $coll. The first value will be this
   *  $coll and the final one will be an empty $coll, with the intervening
   *  values the results of successive applications of `tail`.
   *
   *  @return   an iterator over all the tails of this $coll
   *  @example  `List(1,2,3).tails = Iterator(List(1,2,3), List(2,3), List(3), Nil)`
<<<<<<< HEAD
   */  
=======
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def tails: Iterator[Repr] = iterateUntilEmpty(_.tail)

  /** Iterates over the inits of this $coll. The first value will be this
   *  $coll and the final one will be an empty $coll, with the intervening
   *  values the results of successive applications of `init`.
   *
   *  @return  an iterator over all the inits of this $coll
   *  @example  `List(1,2,3).inits = Iterator(List(1,2,3), List(1,2), List(1), Nil)`
   */
  def inits: Iterator[Repr] = iterateUntilEmpty(_.init)

  /** Copies elements of this $coll to an array.
   *  Fills the given array `xs` with at most `len` elements of
   *  this $coll, starting at position `start`.
   *  Copying will stop once either the end of the current $coll is reached,
   *  or the end of the array is reached, or `len` elements have been copied.
   *
   *  $willNotTerminateInf
<<<<<<< HEAD
   * 
   *  @param  xs     the array to fill.
   *  @param  start  the starting index.
   *  @param  len    the maximal number of elements to copy.
   *  @tparam B      the type of the elements of the array. 
   * 
=======
   *
   *  @param  xs     the array to fill.
   *  @param  start  the starting index.
   *  @param  len    the maximal number of elements to copy.
   *  @tparam B      the type of the elements of the array.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  @usecase def copyToArray(xs: Array[A], start: Int, len: Int): Unit
   */
  def copyToArray[B >: A](xs: Array[B], start: Int, len: Int) {
    var i = start
    val end = (start + len) min xs.length
    breakable {
      for (x <- this) {
        if (i >= end) break
        xs(i) = x
        i += 1
      }
    }
  }

  def toTraversable: Traversable[A] = thisCollection
  def toIterator: Iterator[A] = toStream.iterator
  def toStream: Stream[A] = toBuffer.toStream

  /** Converts this $coll to a string.
   *
   *  @return   a string representation of this collection. By default this
<<<<<<< HEAD
   *            string consists of the `stringPrefix` of this $coll,
   *            followed by all elements separated by commas and enclosed in parentheses.
=======
   *            string consists of the `stringPrefix` of this $coll, followed
   *            by all elements separated by commas and enclosed in parentheses.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  override def toString = mkString(stringPrefix + "(", ", ", ")")

  /** Defines the prefix of this object's `toString` representation.
   *
   *  @return  a string representation which starts the result of `toString`
   *           applied to this $coll. By default the string prefix is the
   *           simple name of the collection class $coll.
   */
  def stringPrefix : String = {
<<<<<<< HEAD
    var string = repr.asInstanceOf[AnyRef].getClass.getName
=======
    var string = repr.getClass.getName
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val idx1 = string.lastIndexOf('.' : Int)
    if (idx1 != -1) string = string.substring(idx1 + 1)
    val idx2 = string.indexOf('$')
    if (idx2 != -1) string = string.substring(0, idx2)
    string
  }

  /** Creates a non-strict view of this $coll.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return a non-strict view of this $coll.
   */
  def view = new TraversableView[A, Repr] {
    protected lazy val underlying = self.repr
    override def foreach[U](f: A => U) = self foreach f
  }

  /** Creates a non-strict view of a slice of this $coll.
   *
   *  Note: the difference between `view` and `slice` is that `view` produces
   *        a view of the current $coll, whereas `slice` produces a new $coll.
<<<<<<< HEAD
   * 
   *  Note: `view(from, to)` is equivalent to `view.slice(from, to)`
   *  $orderDependent
   * 
=======
   *
   *  Note: `view(from, to)` is equivalent to `view.slice(from, to)`
   *  $orderDependent
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param from   the index of the first element of the view
   *  @param until  the index of the element following the view
   *  @return a non-strict view of a slice of this $coll, starting at index `from`
   *  and extending up to (but not including) index `until`.
   */
  def view(from: Int, until: Int): TraversableView[A, Repr] = view.slice(from, until)

  /** Creates a non-strict filter of this $coll.
   *
   *  Note: the difference between `c filter p` and `c withFilter p` is that
   *        the former creates a new collection, whereas the latter only
   *        restricts the domain of subsequent `map`, `flatMap`, `foreach`,
   *        and `withFilter` operations.
   *  $orderDependent
<<<<<<< HEAD
   * 
   *  @param p   the predicate used to test elements.
   *  @return    an object of class `WithFilter`, which supports
   *             `map`, `flatMap`, `foreach`, and `withFilter` operations.
   *             All these operations apply to those elements of this $coll which
   *             satisfy the predicate `p`.
=======
   *
   *  @param p   the predicate used to test elements.
   *  @return    an object of class `WithFilter`, which supports
   *             `map`, `flatMap`, `foreach`, and `withFilter` operations.
   *             All these operations apply to those elements of this $coll
   *             which satisfy the predicate `p`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def withFilter(p: A => Boolean): FilterMonadic[A, Repr] = new WithFilter(p)

  /** A class supporting filtered operations. Instances of this class are
   *  returned by method `withFilter`.
   */
  class WithFilter(p: A => Boolean) extends FilterMonadic[A, Repr] {

    /** Builds a new collection by applying a function to all elements of the
     *  outer $coll containing this `WithFilter` instance that satisfy predicate `p`.
     *
     *  @param f      the function to apply to each element.
     *  @tparam B     the element type of the returned collection.
     *  @tparam That  $thatinfo
     *  @param bf     $bfinfo
     *  @return       a new collection of type `That` resulting from applying
     *                the given function `f` to each element of the outer $coll
     *                that satisfies predicate `p` and collecting the results.
     *
<<<<<<< HEAD
     *  @usecase def map[B](f: A => B): $Coll[B] 
     *  
=======
     *  @usecase def map[B](f: A => B): $Coll[B]
     *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *  @return       a new $coll resulting from applying the given function
     *                `f` to each element of the outer $coll that satisfies
     *                predicate `p` and collecting the results.
     */
    def map[B, That](f: A => B)(implicit bf: CanBuildFrom[Repr, B, That]): That = {
      val b = bf(repr)
<<<<<<< HEAD
      for (x <- self) 
=======
      for (x <- self)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (p(x)) b += f(x)
      b.result
    }

    /** Builds a new collection by applying a function to all elements of the
     *  outer $coll containing this `WithFilter` instance that satisfy
<<<<<<< HEAD
     *  predicate `p` and concatenating the results. 
=======
     *  predicate `p` and concatenating the results.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *
     *  @param f      the function to apply to each element.
     *  @tparam B     the element type of the returned collection.
     *  @tparam That  $thatinfo
     *  @param bf     $bfinfo
     *  @return       a new collection of type `That` resulting from applying
     *                the given collection-valued function `f` to each element
     *                of the outer $coll that satisfies predicate `p` and
     *                concatenating the results.
     *
     *  @usecase def flatMap[B](f: A => TraversableOnce[B]): $Coll[B]
<<<<<<< HEAD
     * 
     *  @return       a new $coll resulting from applying the given collection-valued function
     *                `f` to each element of the outer $coll that satisfies predicate `p` and concatenating the results.
     */
    def flatMap[B, That](f: A => GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
      val b = bf(repr)
      for (x <- self) 
=======
     *
     *  @return       a new $coll resulting from applying the given
     *                collection-valued function `f` to each element of the
     *                outer $coll that satisfies predicate `p` and concatenating
     *                the results.
     */
    def flatMap[B, That](f: A => GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That = {
      val b = bf(repr)
      for (x <- self)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (p(x)) b ++= f(x).seq
      b.result
    }

    /** Applies a function `f` to all elements of the outer $coll containing
     *  this `WithFilter` instance that satisfy predicate `p`.
     *
     *  @param  f   the function that is applied for its side-effect to every element.
     *              The result of function `f` is discarded.
<<<<<<< HEAD
     *              
     *  @tparam  U  the type parameter describing the result of function `f`. 
=======
     *
     *  @tparam  U  the type parameter describing the result of function `f`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *              This result will always be ignored. Typically `U` is `Unit`,
     *              but this is not necessary.
     *
     *  @usecase def foreach(f: A => Unit): Unit
<<<<<<< HEAD
     */   
    def foreach[U](f: A => U): Unit = 
      for (x <- self) 
=======
     */
    def foreach[U](f: A => U): Unit =
      for (x <- self)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (p(x)) f(x)

    /** Further refines the filter for this $coll.
     *
     *  @param q   the predicate used to test elements.
     *  @return    an object of class `WithFilter`, which supports
     *             `map`, `flatMap`, `foreach`, and `withFilter` operations.
     *             All these operations apply to those elements of this $coll which
     *             satisfy the predicate `q` in addition to the predicate `p`.
     */
<<<<<<< HEAD
    def withFilter(q: A => Boolean): WithFilter = 
=======
    def withFilter(q: A => Boolean): WithFilter =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      new WithFilter(x => p(x) && q(x))
  }

  // A helper for tails and inits.
  private def iterateUntilEmpty(f: Traversable[A @uV] => Traversable[A @uV]): Iterator[Repr] = {
    val it = Iterator.iterate(thisCollection)(f) takeWhile (x => !x.isEmpty)
    it ++ Iterator(Nil) map (newBuilder ++= _ result)
  }
}
