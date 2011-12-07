/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

import generic.{ CanBuildFrom => CBF, _ }

/** A template trait for all iterable collections which may possibly
 *  have their operations implemented in parallel.
 *
 *  This trait contains abstract methods and methods that can be implemented
 *  directly in terms of other methods.
 *
 *  @define Coll GenIterable
 *  @define coll general iterable collection
<<<<<<< HEAD
 *  
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 *  @define zipthatinfo the class of the returned collection. Where possible, `That` is 
=======
 *
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 *  @define zipthatinfo the class of the returned collection. Where possible, `That` is
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    the same class as the current collection class `Repr`, but this
 *    depends on the element type `(A1, B)` being admissible for that class,
 *    which means that an implicit instance of type `CanBuildFrom[Repr, (A1, B), That]`.
 *    is found.
 *  @define zipbfinfo  an implicit value of class `CanBuildFrom` which determines the
 *    result class `That` from the current representation type `Repr`
<<<<<<< HEAD
 *    and the new element type `(A1, B)`. 
=======
 *    and the new element type `(A1, B)`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define iterableInfo
 *    This is a base trait for all Scala collections that define an `iterator`
 *    method to step through one-by-one the collection's elements.
 */
trait GenIterableLike[+A, +Repr] extends GenTraversableLike[A, Repr] {
<<<<<<< HEAD
  
  def iterator: Iterator[A]
  
=======

  def iterator: Iterator[A]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Checks if the other iterable collection contains the same elements in the same order as this $coll.
   *
   *  $orderDependent
   *  $willNotTerminateInf
   *
   *  @param that  the collection to compare with.
<<<<<<< HEAD
   *  @tparam B    the type of the elements of collection `that`. 
   *  @return `true`, if both collections contain the same elements in the same order, `false` otherwise.
   *
   *  @usecase  def sameElements(that: GenIterable[A]): Boolean
   * 
=======
   *  @tparam B    the type of the elements of collection `that`.
   *  @return `true`, if both collections contain the same elements in the same order, `false` otherwise.
   *
   *  @usecase  def sameElements(that: GenIterable[A]): Boolean
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param that  the collection to compare with.
   *  @return `true`, if both collections contain the same elements in the same order, `false` otherwise.
   */
  def sameElements[A1 >: A](that: GenIterable[A1]): Boolean
<<<<<<< HEAD
  
  /** Returns a $coll formed from this $coll and another iterable collection
   *  by combining corresponding elements in pairs.
   *  If one of the two collections is longer than the other, its remaining elements are ignored.
   * 
   *  $orderDependent
   *  
=======

  /** Returns a $coll formed from this $coll and another iterable collection
   *  by combining corresponding elements in pairs.
   *  If one of the two collections is longer than the other, its remaining elements are ignored.
   *
   *  $orderDependent
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param   that  The iterable providing the second half of each result pair
   *  @tparam  A1    the type of the first half of the returned pairs (this is always a supertype
   *                 of the collection's element type `A`).
   *  @tparam  B     the type of the second half of the returned pairs
   *  @tparam  That  $zipthatinfo
   *  @param   bf    $zipbfinfo
   *  @return        a new collection of type `That` containing pairs consisting of
   *                 corresponding elements of this $coll and `that`. The length
   *                 of the returned collection is the minimum of the lengths of this $coll and `that`.
   *
   *  @usecase def zip[B](that: GenIterable[B]): $Coll[(A, B)]
   *
   *  @param   that  The iterable providing the second half of each result pair
   *  @tparam  B     the type of the second half of the returned pairs
   *  @return        a new $coll containing pairs consisting of
   *                 corresponding elements of this $coll and `that`. The length
   *                 of the returned collection is the minimum of the lengths of this $coll and `that`.
   */
  def zip[A1 >: A, B, That](that: GenIterable[B])(implicit bf: CBF[Repr, (A1, B), That]): That
<<<<<<< HEAD
  
  /** Zips this $coll with its indices.
   *
   *  $orderDependent
   * 
   *  @tparam  A1    the type of the first half of the returned pairs (this is always a supertype
   *                 of the collection's element type `A`).
   *  @tparam  That  the class of the returned collection. Where possible, `That` is 
=======

  /** Zips this $coll with its indices.
   *
   *  $orderDependent
   *
   *  @tparam  A1    the type of the first half of the returned pairs (this is always a supertype
   *                 of the collection's element type `A`).
   *  @tparam  That  the class of the returned collection. Where possible, `That` is
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *    the same class as the current collection class `Repr`, but this
   *    depends on the element type `(A1, Int)` being admissible for that class,
   *    which means that an implicit instance of type `CanBuildFrom[Repr, (A1, Int), That]`.
   *    is found.
   *  @tparam  bf    an implicit value of class `CanBuildFrom` which determines the
   *    result class `That` from the current representation type `Repr`
   *    and the new element type `(A1, Int)`.
   *  @return        A new collection of type `That` containing pairs consisting of all elements of this
   *                 $coll paired with their index. Indices start at `0`.
   *
   *  @usecase def zipWithIndex: $Coll[(A, Int)]
   *
   *  @return        A new $coll containing pairs consisting of all elements of this
   *                 $coll paired with their index. Indices start at `0`.
   *  @example
   *    `List("a", "b", "c").zipWithIndex = List(("a", 0), ("b", 1), ("c", 2))`
<<<<<<< HEAD
   *  
   */
  def zipWithIndex[A1 >: A, That](implicit bf: CBF[Repr, (A1, Int), That]): That
  
=======
   *
   */
  def zipWithIndex[A1 >: A, That](implicit bf: CBF[Repr, (A1, Int), That]): That

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns a $coll formed from this $coll and another iterable collection
   *  by combining corresponding elements in pairs.
   *  If one of the two collections is shorter than the other,
   *  placeholder elements are used to extend the shorter collection to the length of the longer.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $orderDependent
   *
   *  @param that     the iterable providing the second half of each result pair
   *  @param thisElem the element to be used to fill up the result if this $coll is shorter than `that`.
   *  @param thatElem the element to be used to fill up the result if `that` is shorter than this $coll.
   *  @return        a new collection of type `That` containing pairs consisting of
   *                 corresponding elements of this $coll and `that`. The length
   *                 of the returned collection is the maximum of the lengths of this $coll and `that`.
   *                 If this $coll is shorter than `that`, `thisElem` values are used to pad the result.
   *                 If `that` is shorter than this $coll, `thatElem` values are used to pad the result.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @usecase def zipAll[B](that: Iterable[B], thisElem: A, thatElem: B): $Coll[(A, B)]
   *
   *  @param   that  The iterable providing the second half of each result pair
   *  @param thisElem the element to be used to fill up the result if this $coll is shorter than `that`.
   *  @param thatElem the element to be used to fill up the result if `that` is shorter than this $coll.
   *  @tparam  B     the type of the second half of the returned pairs
   *  @return        a new $coll containing pairs consisting of
   *                 corresponding elements of this $coll and `that`. The length
   *                 of the returned collection is the maximum of the lengths of this $coll and `that`.
   *                 If this $coll is shorter than `that`, `thisElem` values are used to pad the result.
   *                 If `that` is shorter than this $coll, `thatElem` values are used to pad the result.
   */
  def zipAll[B, A1 >: A, That](that: GenIterable[B], thisElem: A1, thatElem: B)(implicit bf: CBF[Repr, (A1, B), That]): That
<<<<<<< HEAD
  
  def isEmpty = iterator.isEmpty
  
=======

  def isEmpty = iterator.isEmpty

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def head = iterator.next
}
