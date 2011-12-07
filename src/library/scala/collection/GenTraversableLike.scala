/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection


import generic._
import annotation.migration


/** A template trait for all traversable collections upon which operations
 *  may be implemented in parallel.
 *
<<<<<<< HEAD
 *  @define thatinfo the class of the returned collection. Where possible, `That` is 
=======
 *  @define thatinfo the class of the returned collection. Where possible, `That` is
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    the same class as the current collection class `Repr`, but this
 *    depends on the element type `B` being admissible for that class,
 *    which means that an implicit instance of type `CanBuildFrom[Repr, B, That]`
 *    is found.
 *  @define bfinfo an implicit value of class `CanBuildFrom` which determines
 *    the result class `That` from the current representation type `Repr` and
 *    and the new element type `B`.
 *  @define orderDependent
<<<<<<< HEAD
 * 
 *    Note: might return different results for different runs, unless the
 *    underlying collection type is ordered.
 *  @define orderDependentFold
 * 
=======
 *
 *    Note: might return different results for different runs, unless the
 *    underlying collection type is ordered.
 *  @define orderDependentFold
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    Note: might return different results for different runs, unless the
 *    underlying collection type is ordered.
 *    or the operator is associative and commutative.
 *  @define mayNotTerminateInf
 *
 *    Note: may not terminate for infinite-sized collections.
 *  @define willNotTerminateInf
 *
 *    Note: will not terminate for infinite-sized collections.
 *
 *  @define traversableInfo
 *  This is a base trait of all kinds of Scala collections.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll GenTraversable
 *  @define coll general collection
 *  @define collectExample
 *  @tparam A    the collection element type.
 *  @tparam Repr the actual type of the element container.
 *
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait GenTraversableLike[+A, +Repr] extends GenTraversableOnce[A] with Parallelizable[A, parallel.ParIterable[A]] {
<<<<<<< HEAD
  
  def repr: Repr
  
  def size: Int
  
  def head: A
  
=======

  def repr: Repr

  def size: Int

  def head: A

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Tests whether this $coll can be repeatedly traversed.
   *  @return   `true`
   */
  final def isTraversableAgain = true
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
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
  def tail: Repr = {
    if (isEmpty) throw new UnsupportedOperationException("empty.tail")
    drop(1)
  }
<<<<<<< HEAD
    
  /** Computes a prefix scan of the elements of the collection.
   *
   *  Note: The neutral element `z` may be applied more than once.
   *  
=======

  /** Computes a prefix scan of the elements of the collection.
   *
   *  Note: The neutral element `z` may be applied more than once.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam B         element type of the resulting collection
   *  @tparam That      type of the resulting collection
   *  @param z          neutral element for the operator `op`
   *  @param op         the associative operator for the scan
   *  @param cbf        combiner factory which provides a combiner
   *
   *  @return           a new $coll containing the prefix scan of the elements in this $coll
   */
  def scan[B >: A, That](z: B)(op: (B, B) => B)(implicit cbf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
  /** Produces a collection containing cummulative results of applying the
   *  operator going left to right.
   *  
   *  $willNotTerminateInf
   *  $orderDependent
   *  
=======

  /** Produces a collection containing cummulative results of applying the
   *  operator going left to right.
   *
   *  $willNotTerminateInf
   *  $orderDependent
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam B      the type of the elements in the resulting collection
   *  @tparam That   the actual type of the resulting collection
   *  @param z       the initial value
   *  @param op      the binary operator applied to the intermediate result and the element
   *  @param bf      $bfinfo
   *  @return        collection with intermediate results
   */
  def scanLeft[B, That](z: B)(op: (B, A) => B)(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Produces a collection containing cummulative results of applying the operator going right to left.
   *  The head of the collection is the last cummulative result.
   *  $willNotTerminateInf
   *  $orderDependent
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  Example:
   *  {{{
   *    List(1, 2, 3, 4).scanRight(0)(_ + _) == List(10, 9, 7, 4, 0)
   *  }}}
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam B      the type of the elements in the resulting collection
   *  @tparam That   the actual type of the resulting collection
   *  @param z       the initial value
   *  @param op      the binary operator applied to the intermediate result and the element
   *  @param bf      $bfinfo
   *  @return        collection with intermediate results
   */
  @migration(2, 9,
    "This scanRight definition has changed in 2.9.\n" +
    "The previous behavior can be reproduced with scanRight.reverse."
  )
  def scanRight[B, That](z: B)(op: (A, B) => B)(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
  /** Applies a function `f` to all elements of this $coll.
   *  
   *  @param  f   the function that is applied for its side-effect to every element.
   *              The result of function `f` is discarded.
   *              
   *  @tparam  U  the type parameter describing the result of function `f`. 
=======

  /** Applies a function `f` to all elements of this $coll.
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
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Builds a new collection by applying a function to all elements of this $coll.
   *
   *  @param f      the function to apply to each element.
   *  @tparam B     the element type of the returned collection.
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` resulting from applying the given function
   *                `f` to each element of this $coll and collecting the results.
   *
<<<<<<< HEAD
   *  @usecase def map[B](f: A => B): $Coll[B] 
   *  
=======
   *  @usecase def map[B](f: A => B): $Coll[B]
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return       a new $coll resulting from applying the given function
   *                `f` to each element of this $coll and collecting the results.
   */
  def map[B, That](f: A => B)(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Builds a new collection by applying a partial function to all elements of this $coll
   *  on which the function is defined.
   *
   *  $collectExample
   *
   *  @param pf     the partial function which filters and maps the $coll.
   *  @tparam B     the element type of the returned collection.
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` resulting from applying the partial function
   *                `pf` to each element on which it is defined and collecting the results.
   *                The order of the elements is preserved.
   *
   *  @usecase def collect[B](pf: PartialFunction[A, B]): $Coll[B]
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return       a new $coll resulting from applying the given partial function
   *                `pf` to each element on which it is defined and collecting the results.
   *                The order of the elements is preserved.
   */
  def collect[B, That](pf: PartialFunction[A, B])(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
  /** Builds a new collection by applying a function to all elements of this $coll
   *  and concatenating the results. 
=======

  /** Builds a new collection by applying a function to all elements of this $coll
   *  and concatenating the results.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  @param f      the function to apply to each element.
   *  @tparam B     the element type of the returned collection.
   *  @tparam That  $thatinfo
   *  @param bf     $bfinfo
   *  @return       a new collection of type `That` resulting from applying the given collection-valued function
   *                `f` to each element of this $coll and concatenating the results.
   *
   *  @usecase def flatMap[B](f: A => GenTraversableOnce[B]): $Coll[B]
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return       a new $coll resulting from applying the given collection-valued function
   *                `f` to each element of this $coll and concatenating the results.
   */
  def flatMap[B, That](f: A => GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns a new $coll containing the elements from the left hand operand followed by the elements from the
   *  right hand operand. The element type of the $coll is the most specific superclass encompassing
   *  the element types of the two operands (see example).
   *
   * Example:
   * {{{
   *     scala> val a = LinkedList(1)
   *     a: scala.collection.mutable.LinkedList[Int] = LinkedList(1)
   *
   *     scala> val b = LinkedList(2)
   *     b: scala.collection.mutable.LinkedList[Int] = LinkedList(2)
   *
   *     scala> val c = a ++ b
   *     c: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2)
   *
   *     scala> val d = LinkedList('a')
   *     d: scala.collection.mutable.LinkedList[Char] = LinkedList(a)
   *
   *     scala> val e = c ++ d
   *     e: scala.collection.mutable.LinkedList[AnyVal] = LinkedList(1, 2, a)
   * }}}
   *
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
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @usecase def ++[B](that: GenTraversableOnce[B]): $Coll[B]
   *
   *  @return       a new $coll which contains all elements of this $coll
   *                followed by all elements of `that`.
   */
  def ++[B >: A, That](that: GenTraversableOnce[B])(implicit bf: CanBuildFrom[Repr, B, That]): That
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Selects all elements of this $coll which satisfy a predicate.
   *
   *  @param p     the predicate used to test elements.
   *  @return      a new $coll consisting of all elements of this $coll that satisfy the given
   *               predicate `p`. Their order may not be preserved.
   */
  def filter(pred: A => Boolean): Repr
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Selects all elements of this $coll which do not satisfy a predicate.
   *
   *  @param p     the predicate used to test elements.
   *  @return      a new $coll consisting of all elements of this $coll that do not satisfy the given
   *               predicate `p`. Their order may not be preserved.
   */
  def filterNot(pred: A => Boolean): Repr
<<<<<<< HEAD
  
  /** Partitions this $coll in two ${coll}s according to a predicate.
   *
   *  @param p the predicate on which to partition.
   *  @return  a pair of ${coll}s: the first $coll consists of all elements that 
=======

  /** Partitions this $coll in two ${coll}s according to a predicate.
   *
   *  @param p the predicate on which to partition.
   *  @return  a pair of ${coll}s: the first $coll consists of all elements that
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *           satisfy the predicate `p` and the second $coll consists of all elements
   *           that don't. The relative order of the elements in the resulting ${coll}s
   *           may not be preserved.
   */
  def partition(pred: A => Boolean): (Repr, Repr)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Partitions this $coll into a map of ${coll}s according to some discriminator function.
   *
   *  Note: this method is not re-implemented by views. This means
   *        when applied to a view it will always force the view and
   *        return a new $coll.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param f     the discriminator function.
   *  @tparam K    the type of keys returned by the discriminator function.
   *  @return      A map from keys to ${coll}s such that the following invariant holds:
   *               {{{
   *                 (xs partition f)(k) = xs filter (x => f(x) == k)
   *               }}}
   *               That is, every key `k` is bound to a $coll of those elements `x`
   *               for which `f(x)` equals `k`.
<<<<<<< HEAD
   * 
   */
  def groupBy[K](f: A => K): GenMap[K, Repr]
  
=======
   *
   */
  def groupBy[K](f: A => K): GenMap[K, Repr]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Selects first ''n'' elements.
   *  $orderDependent
   *  @param  n    Tt number of elements to take from this $coll.
   *  @return a $coll consisting only of the first `n` elements of this $coll,
   *          or else the whole $coll, if it has less than `n` elements.
   */
  def take(n: Int): Repr
<<<<<<< HEAD
  
  /** Selects all elements except first ''n'' ones. 
=======

  /** Selects all elements except first ''n'' ones.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $orderDependent
   *  @param  n    the number of elements to drop from this $coll.
   *  @return a $coll consisting of all elements of this $coll except the first `n` ones, or else the
   *          empty $coll, if this $coll has less than `n` elements.
   */
  def drop(n: Int): Repr
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Selects an interval of elements.  The returned collection is made up
   *  of all elements `x` which satisfy the invariant:
   *  {{{
   *    from <= indexOf(x) < until
   *  }}}
   *  $orderDependent
   *
   *  @param from   the lowest index to include from this $coll.
   *  @param until  the highest index to EXCLUDE from this $coll.
   *  @return  a $coll containing the elements greater than or equal to
   *           index `from` extending up to (but not including) index `until`
   *           of this $coll.
   */
  def slice(unc_from: Int, unc_until: Int): Repr
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Splits this $coll into two at a given position.
   *  Note: `c splitAt n` is equivalent to (but possibly more efficient than)
   *         `(c take n, c drop n)`.
   *  $orderDependent
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param n the position at which to split.
   *  @return  a pair of ${coll}s consisting of the first `n`
   *           elements of this $coll, and the other elements.
   */
  def splitAt(n: Int): (Repr, Repr)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Takes longest prefix of elements that satisfy a predicate.
   *  $orderDependent
   *  @param   p  The predicate used to test elements.
   *  @return  the longest prefix of this $coll whose elements all satisfy
   *           the predicate `p`.
   */
  def takeWhile(pred: A => Boolean): Repr
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Splits this $coll into a prefix/suffix pair according to a predicate.
   *
   *  Note: `c span p`  is equivalent to (but possibly more efficient than)
   *  `(c takeWhile p, c dropWhile p)`, provided the evaluation of the
   *  predicate `p` does not cause any side-effects.
   *  $orderDependent
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param p the test predicate
   *  @return  a pair consisting of the longest prefix of this $coll whose
   *           elements all satisfy `p`, and the rest of this $coll.
   */
  def span(pred: A => Boolean): (Repr, Repr)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Drops longest prefix of elements that satisfy a predicate.
   *  $orderDependent
   *  @param   p  The predicate used to test elements.
   *  @return  the longest suffix of this $coll whose first element
   *           does not satisfy the predicate `p`.
   */
  def dropWhile(pred: A => Boolean): Repr
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Defines the prefix of this object's `toString` representation.
   *
   *  @return  a string representation which starts the result of `toString`
   *           applied to this $coll. By default the string prefix is the
   *           simple name of the collection class $coll.
   */
  def stringPrefix: String

}
