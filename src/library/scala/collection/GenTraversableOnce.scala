/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

/** A template trait for all traversable-once objects which may be
 *  traversed in parallel.
 *
 *  Methods in this trait are either abstract or can be implemented in terms
 *  of other methods.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll GenTraversableOnce
 *  @define coll collection or iterator
 *  @define possiblyparinfo
 *  This trait may possibly have operations implemented in parallel.
 *  @define undefinedorder
 *  The order in which operations are performed on elements is unspecified
 *  and may be nondeterministic.
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
 *    underlying collection type is ordered or the operator is associative
 *    and commutative.
 *  @define mayNotTerminateInf
 *
 *    Note: may not terminate for infinite-sized collections.
 *  @define willNotTerminateInf
 *
 *    Note: will not terminate for infinite-sized collections.
 *
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait GenTraversableOnce[+A] {

  def foreach[U](f: A => U): Unit

  def hasDefiniteSize: Boolean

  def seq: TraversableOnce[A]

  /** The size of this $coll.
   *
   *  $willNotTerminateInf
   *
   *  @return    the number of elements in this $coll.
   */
  def size: Int

  /** Tests whether the $coll is empty.
   *
   *  @return    `true` if the $coll contains no elements, `false` otherwise.
   */
  def isEmpty: Boolean

  /** Tests whether the $coll is not empty.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return    `true` if the $coll contains at least one element, `false` otherwise.
   */
  def nonEmpty: Boolean

  /** Tests whether this $coll can be repeatedly traversed.  Always
   *  true for Traversables and false for Iterators unless overridden.
   *
   *  @return   `true` if it is repeatedly traversable, `false` otherwise.
   */
  def isTraversableAgain: Boolean

  /** Reduces the elements of this $coll using the specified associative binary operator.
<<<<<<< HEAD
   *  
   *  $undefinedorder
   *  
=======
   *
   *  $undefinedorder
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam A1      A type parameter for the binary operator, a supertype of `A`.
   *  @param op       A binary operator that must be associative.
   *  @return         The result of applying reduce operator `op` between all the elements if the $coll is nonempty.
   *  @throws UnsupportedOperationException
   *  if this $coll is empty.
   */
  def reduce[A1 >: A](op: (A1, A1) => A1): A1

  /** Reduces the elements of this $coll, if any, using the specified
   *  associative binary operator.
<<<<<<< HEAD
   *  
   *  $undefinedorder
   *  
   *  @tparam A1     A type parameter for the binary operator, a supertype of `A`.
   *  @param op      A binary operator that must be associative.
   *  @return        An option value containing result of applying reduce operator `op` between all
   *                 the elements if the collection is nonempty, and `None` otherwise. 
=======
   *
   *  $undefinedorder
   *
   *  @tparam A1     A type parameter for the binary operator, a supertype of `A`.
   *  @param op      A binary operator that must be associative.
   *  @return        An option value containing result of applying reduce operator `op` between all
   *                 the elements if the collection is nonempty, and `None` otherwise.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def reduceOption[A1 >: A](op: (A1, A1) => A1): Option[A1]

  /** Folds the elements of this $coll using the specified associative
   *  binary operator.
   *
   *  $undefinedorder
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam A1     a type parameter for the binary operator, a supertype of `A`.
   *  @param z       a neutral element for the fold operation; may be added to the result
   *                 an arbitrary number of times, and must not change the result (e.g., `Nil` for list concatenation,
   *                 0 for addition, or 1 for multiplication.)
   *  @param op      a binary operator that must be associative
   *  @return        the result of applying fold operator `op` between all the elements and `z`
   */
  def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1

  /** A syntactic sugar for out of order folding. See `fold`.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * Example:
   * {{{
   *      scala> val a = LinkedList(1,2,3,4)
   *      a: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2, 3, 4)
   *
   *      scala> val b = (a /:\ 5)(_+_)
   *      b: Int = 15
   * }}}*/
  def /:\[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = fold(z)(op)

  /** Applies a binary operator to a start value and all elements of this $coll,
   *  going left to right.
   *
   *  Note: `/:` is alternate syntax for `foldLeft`; `z /: xs` is the same as
   *  `xs foldLeft z`.
   *
   *  Examples:
   *
   *  Note that the folding function used to compute b is equivalent to that used to compute c.
   *  {{{
   *      scala> val a = LinkedList(1,2,3,4)
   *      a: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2, 3, 4)
   *
   *      scala> val b = (5 /: a)(_+_)
   *      b: Int = 15
   *
   *      scala> val c = (5 /: a)((x,y) => x + y)
   *      c: Int = 15
   *  }}}

   *  $willNotTerminateInf
   *  $orderDependentFold
   *
   *  @param   z    the start value.
   *  @param   op   the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  the result of inserting `op` between consecutive elements of this $coll,
   *           going left to right with the start value `z` on the left:
   *           {{{
   *             op(...op(op(z, x1), x2), ..., xn)
   *           }}}
   *           where `x,,1,,, ..., x,,n,,` are the elements of this $coll.
   */
  def /:[B](z: B)(op: (B, A) => B): B

  /** Applies a binary operator to all elements of this $coll and a start value,
   *  going right to left.
   *
   *  Note: `:\` is alternate syntax for `foldRight`; `xs :\ z` is the same as
   *  `xs foldRight z`.
   *  $willNotTerminateInf
   *  $orderDependentFold
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  Examples:
   *
   *  Note that the folding function used to compute b is equivalent to that used to compute c.
   *  {{{
   *      scala> val a = LinkedList(1,2,3,4)
   *      a: scala.collection.mutable.LinkedList[Int] = LinkedList(1, 2, 3, 4)
   *
   *      scala> val b = (a :\ 5)(_+_)
   *      b: Int = 15
   *
   *      scala> val c = (a :\ 5)((x,y) => x + y)
   *      c: Int = 15
   *
   *  }}}
   *
   *  @param   z    the start value
   *  @param   op   the binary operator
   *  @tparam  B    the result type of the binary operator.
   *  @return  the result of inserting `op` between consecutive elements of this $coll,
   *           going right to left with the start value `z` on the right:
   *           {{{
   *             op(x1, op(x2, ... op(xn, z)...))
   *           }}}
   *           where `x,,1,,, ..., x,,n,,` are the elements of this $coll.
   */
  def :\[B](z: B)(op: (A, B) => B): B

  /** Applies a binary operator to a start value and all elements of this $coll,
   *  going left to right.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $willNotTerminateInf
   *  $orderDependentFold
   *
   *  @param   z    the start value.
   *  @param   op   the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  the result of inserting `op` between consecutive elements of this $coll,
   *           going left to right with the start value `z` on the left:
   *           {{{
   *             op(...op(z, x1), x2, ..., xn)
   *           }}}
   *           where `x,,1,,, ..., x,,n,,` are the elements of this $coll.
   */
  def foldLeft[B](z: B)(op: (B, A) => B): B

  /** Applies a binary operator to all elements of this $coll and a start value,
   *  going right to left.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $willNotTerminateInf
   *  $orderDependentFold
   *  @param   z    the start value.
   *  @param   op   the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  the result of inserting `op` between consecutive elements of this $coll,
   *           going right to left with the start value `z` on the right:
   *           {{{
   *             op(x1, op(x2, ... op(xn, z)...))
   *           }}}
   *           where `x,,1,,, ..., x,,n,,` are the elements of this $coll.
   */
  def foldRight[B](z: B)(op: (A, B) => B): B

  /** Aggregates the results of applying an operator to subsequent elements.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  This is a more general form of `fold` and `reduce`. It has similar
   *  semantics, but does not require the result to be a supertype of the
   *  element type. It traverses the elements in different partitions
   *  sequentially, using `seqop` to update the result, and then applies
   *  `combop` to results from different partitions. The implementation of
   *  this operation may operate on an arbitrary number of collection
   *  partitions, so `combop` may be invoked an arbitrary number of times.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  For example, one might want to process some elements and then produce
   *  a `Set`. In this case, `seqop` would process an element and append it
   *  to the list, while `combop` would concatenate two lists from different
   *  partitions together. The initial value `z` would be an empty set.
   *  {{{
   *    pc.aggregate(Set[Int]())(_ += process(_), _ ++ _)
   *  }}}
<<<<<<< HEAD
   *  
   *  Another example is calculating geometric mean from a collection of doubles
   *  (one would typically require big doubles for this).
   *  
=======
   *
   *  Another example is calculating geometric mean from a collection of doubles
   *  (one would typically require big doubles for this).
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam B        the type of accumulated results
   *  @param z         the initial value for the accumulated result of the partition - this
   *                   will typically be the neutral element for the `seqop` operator (e.g.
   *                   `Nil` for list concatenation or `0` for summation)
   *  @param seqop     an operator used to accumulate results within a partition
   *  @param combop    an associative operator used to combine results from different partitions
   */
  def aggregate[B](z: B)(seqop: (B, A) => B, combop: (B, B) => B): B
<<<<<<< HEAD
  
  /** Applies a binary operator to all elements of this $coll, going right to left.
   *  $willNotTerminateInf
   *  $orderDependentFold
   * 
=======

  /** Applies a binary operator to all elements of this $coll, going right to left.
   *  $willNotTerminateInf
   *  $orderDependentFold
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param  op    the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  the result of inserting `op` between consecutive elements of this $coll,
   *           going right to left:
   *           {{{
   *             op(x,,1,,, op(x,,2,,, ..., op(x,,n-1,,, x,,n,,)...))
   *           }}}
   *           where `x,,1,,, ..., x,,n,,` are the elements of this $coll.
   *  @throws `UnsupportedOperationException` if this $coll is empty.
   */
  def reduceRight[B >: A](op: (A, B) => B): B
<<<<<<< HEAD
  
  /** Optionally applies a binary operator to all elements of this $coll, going left to right.
   *  $willNotTerminateInf
   *  $orderDependentFold
   *  
=======

  /** Optionally applies a binary operator to all elements of this $coll, going left to right.
   *  $willNotTerminateInf
   *  $orderDependentFold
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param  op    the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  an option value containing the result of `reduceLeft(op)` is this $coll is nonempty,
   *           `None` otherwise.
   */
  def reduceLeftOption[B >: A](op: (B, A) => B): Option[B]

  /** Optionally applies a binary operator to all elements of this $coll, going
   *  right to left.
   *  $willNotTerminateInf
   *  $orderDependentFold
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param  op    the binary operator.
   *  @tparam  B    the result type of the binary operator.
   *  @return  an option value containing the result of `reduceRight(op)` is this $coll is nonempty,
   *           `None` otherwise.
   */
  def reduceRightOption[B >: A](op: (A, B) => B): Option[B]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Counts the number of elements in the $coll which satisfy a predicate.
   *
   *  @param p     the predicate  used to test elements.
   *  @return      the number of elements satisfying the predicate `p`.
   */
  def count(p: A => Boolean): Int
<<<<<<< HEAD
  
  /** Sums up the elements of this collection.
   * 
   *   @param   num  an implicit parameter defining a set of numeric operations
   *                 which includes the `+` operator to be used in forming the sum.
   *   @tparam  B    the result type of the `+` operator.  
   *   @return       the sum of all elements of this $coll with respect to the `+` operator in `num`.
   *
   *   @usecase def sum: A
   * 
   *   @return       the sum of all elements in this $coll of numbers of type `Int`.
   *   Instead of `Int`, any other type `T` with an implicit `Numeric[T]` implementation 
   *   can be used as element type of the $coll and as result type of `sum`. 
   *   Examples of such types are: `Long`, `Float`, `Double`, `BigInt`.
   * 
   */
  def sum[A1 >: A](implicit num: Numeric[A1]): A1
  
  /** Multiplies up the elements of this collection.
   * 
   *   @param   num  an implicit parameter defining a set of numeric operations
   *                 which includes the `*` operator to be used in forming the product.
   *   @tparam  B    the result type of the `*` operator.  
   *   @return       the product of all elements of this $coll with respect to the `*` operator in `num`.
   *
   *   @usecase def product: A
   * 
   *   @return       the product of all elements in this $coll of numbers of type `Int`.
   *   Instead of `Int`, any other type `T` with an implicit `Numeric[T]` implementation 
   *   can be used as element type of the $coll and as result type of `product`. 
   *   Examples of such types are: `Long`, `Float`, `Double`, `BigInt`.
   */
  def product[A1 >: A](implicit num: Numeric[A1]): A1
  
  /** Finds the smallest element.
   *
   *  @param    cmp   An ordering to be used for comparing elements.
   *  @tparam   B     The type over which the ordering is defined.   
   *  @return   the smallest element of this $coll with respect to the ordering `cmp`.
   *
   *  @usecase def min: A
   *  @return   the smallest element of this $coll  
   */
  def min[A1 >: A](implicit ord: Ordering[A1]): A
  
  /** Finds the largest element.
   *
   *  @param    cmp   An ordering to be used for comparing elements.
   *  @tparam   B     The type over which the ordering is defined.   
   *  @return   the largest element of this $coll with respect to the ordering `cmp`.
   *
   *  @usecase def max: A
   *  @return   the largest element of this $coll. 
   */
  def max[A1 >: A](implicit ord: Ordering[A1]): A
  
  def maxBy[B](f: A => B)(implicit cmp: Ordering[B]): A
  
  def minBy[B](f: A => B)(implicit cmp: Ordering[B]): A
  
  def forall(pred: A => Boolean): Boolean

  def exists(pred: A => Boolean): Boolean
  
  /** Finds the first element of the $coll satisfying a predicate, if any.
   * 
=======

  /** Sums up the elements of this collection.
   *
   *   @param   num  an implicit parameter defining a set of numeric operations
   *                 which includes the `+` operator to be used in forming the sum.
   *   @tparam  B    the result type of the `+` operator.
   *   @return       the sum of all elements of this $coll with respect to the `+` operator in `num`.
   *
   *   @usecase def sum: A
   *
   *   @return       the sum of all elements in this $coll of numbers of type `Int`.
   *   Instead of `Int`, any other type `T` with an implicit `Numeric[T]` implementation
   *   can be used as element type of the $coll and as result type of `sum`.
   *   Examples of such types are: `Long`, `Float`, `Double`, `BigInt`.
   *
   */
  def sum[A1 >: A](implicit num: Numeric[A1]): A1

  /** Multiplies up the elements of this collection.
   *
   *   @param   num  an implicit parameter defining a set of numeric operations
   *                 which includes the `*` operator to be used in forming the product.
   *   @tparam  B    the result type of the `*` operator.
   *   @return       the product of all elements of this $coll with respect to the `*` operator in `num`.
   *
   *   @usecase def product: A
   *
   *   @return       the product of all elements in this $coll of numbers of type `Int`.
   *   Instead of `Int`, any other type `T` with an implicit `Numeric[T]` implementation
   *   can be used as element type of the $coll and as result type of `product`.
   *   Examples of such types are: `Long`, `Float`, `Double`, `BigInt`.
   */
  def product[A1 >: A](implicit num: Numeric[A1]): A1

  /** Finds the smallest element.
   *
   *  @param    cmp   An ordering to be used for comparing elements.
   *  @tparam   B     The type over which the ordering is defined.
   *  @return   the smallest element of this $coll with respect to the ordering `cmp`.
   *
   *  @usecase def min: A
   *  @return   the smallest element of this $coll
   */
  def min[A1 >: A](implicit ord: Ordering[A1]): A

  /** Finds the largest element.
   *
   *  @param    cmp   An ordering to be used for comparing elements.
   *  @tparam   B     The type over which the ordering is defined.
   *  @return   the largest element of this $coll with respect to the ordering `cmp`.
   *
   *  @usecase def max: A
   *  @return   the largest element of this $coll.
   */
  def max[A1 >: A](implicit ord: Ordering[A1]): A

  def maxBy[B](f: A => B)(implicit cmp: Ordering[B]): A

  def minBy[B](f: A => B)(implicit cmp: Ordering[B]): A

  def forall(pred: A => Boolean): Boolean

  def exists(pred: A => Boolean): Boolean

  /** Finds the first element of the $coll satisfying a predicate, if any.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  $mayNotTerminateInf
   *  $orderDependent
   *
   *  @param p    the predicate used to test elements.
   *  @return     an option value containing the first element in the $coll
   *              that satisfies `p`, or `None` if none exists.
   */
  def find(pred: A => Boolean): Option[A]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Copies values of this $coll to an array.
   *  Fills the given array `xs` with values of this $coll.
   *  Copying will stop once either the end of the current $coll is reached,
   *  or the end of the array is reached.
   *
   *  $willNotTerminateInf
<<<<<<< HEAD
   * 
   *  @param  xs     the array to fill.
   *  @tparam B      the type of the elements of the array. 
   * 
   *  @usecase def copyToArray(xs: Array[A]): Unit
   */
  def copyToArray[B >: A](xs: Array[B]): Unit
  
=======
   *
   *  @param  xs     the array to fill.
   *  @tparam B      the type of the elements of the array.
   *
   *  @usecase def copyToArray(xs: Array[A]): Unit
   */
  def copyToArray[B >: A](xs: Array[B]): Unit

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Copies values of this $coll to an array.
   *  Fills the given array `xs` with values of this $coll, beginning at index `start`.
   *  Copying will stop once either the end of the current $coll is reached,
   *  or the end of the array is reached.
   *
   *  $willNotTerminateInf
<<<<<<< HEAD
   * 
   *  @param  xs     the array to fill.
   *  @param  start  the starting index.
   *  @tparam B      the type of the elements of the array. 
=======
   *
   *  @param  xs     the array to fill.
   *  @param  start  the starting index.
   *  @tparam B      the type of the elements of the array.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   *  @usecase def copyToArray(xs: Array[A], start: Int): Unit
   */
  def copyToArray[B >: A](xs: Array[B], start: Int): Unit
<<<<<<< HEAD
  
  def copyToArray[B >: A](xs: Array[B], start: Int, len: Int): Unit
  
=======

  def copyToArray[B >: A](xs: Array[B], start: Int, len: Int): Unit

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Displays all elements of this $coll in a string using start, end, and
   *  separator strings.
   *
   *  @param start the starting string.
   *  @param sep   the separator string.
   *  @param end   the ending string.
   *  @return      a string representation of this $coll. The resulting string
   *               begins with the string `start` and ends with the string
   *               `end`. Inside, the string representations (w.r.t. the method
   *               `toString`) of all elements of this $coll are separated by
   *               the string `sep`.
   *
   *  @example  `List(1, 2, 3).mkString("(", "; ", ")") = "(1; 2; 3)"`
   */
  def mkString(start: String, sep: String, end: String): String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Displays all elements of this $coll in a string using a separator string.
   *
   *  @param sep   the separator string.
   *  @return      a string representation of this $coll. In the resulting string
   *               the string representations (w.r.t. the method `toString`)
   *               of all elements of this $coll are separated by the string `sep`.
   *
   *  @example  `List(1, 2, 3).mkString("|") = "1|2|3"`
   */
  def mkString(sep: String): String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Displays all elements of this $coll in a string.
   *
   *  @return a string representation of this $coll. In the resulting string
   *          the string representations (w.r.t. the method `toString`)
   *          of all elements of this $coll follow each other without any
   *          separator string.
   */
  def mkString: String
<<<<<<< HEAD
  
  /** Converts this $coll to an array.
   *  $willNotTerminateInf
   * 
=======

  /** Converts this $coll to an array.
   *  $willNotTerminateInf
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam B the type of the elements of the array. A `ClassManifest` for
   *            this type must be available.
   *  @return   an array containing all elements of this $coll.
   *
   *  @usecase def toArray: Array[A]
   *  @return  an array containing all elements of this $coll.
   *           A `ClassManifest` must be available for the element type of this $coll.
   */
  def toArray[A1 >: A: ClassManifest]: Array[A1]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to a list.
   *  $willNotTerminateInf
   *  @return a list containing all elements of this $coll.
   */
  def toList: List[A]
<<<<<<< HEAD
  
  /** Converts this $coll to an indexed sequence.
   *  $willNotTerminateInf
   *  @return an indexed sequence containing all elements of this $coll.
   */	
  def toIndexedSeq[A1 >: A]: immutable.IndexedSeq[A1]
  
=======

  /** Converts this $coll to an indexed sequence.
   *  $willNotTerminateInf
   *  @return an indexed sequence containing all elements of this $coll.
   */
  def toIndexedSeq[A1 >: A]: immutable.IndexedSeq[A1]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to a stream.
   *  $willNotTerminateInf
   *  @return a stream containing all elements of this $coll.
   */
  def toStream: Stream[A]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns an Iterator over the elements in this $coll.  Will return
   *  the same Iterator if this instance is already an Iterator.
   *  $willNotTerminateInf
   *  @return an Iterator containing all elements of this $coll.
<<<<<<< HEAD
   */	
  def toIterator: Iterator[A]
  
  /** Converts this $coll to a mutable buffer.
   *  $willNotTerminateInf
   *  @return a buffer containing all elements of this $coll.
   */	
  def toBuffer[A1 >: A]: collection.mutable.Buffer[A1]
  
=======
   */
  def toIterator: Iterator[A]

  /** Converts this $coll to a mutable buffer.
   *  $willNotTerminateInf
   *  @return a buffer containing all elements of this $coll.
   */
  def toBuffer[A1 >: A]: collection.mutable.Buffer[A1]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to an unspecified Traversable.  Will return
   *  the same collection if this instance is already Traversable.
   *  $willNotTerminateInf
   *  @return a Traversable containing all elements of this $coll.
<<<<<<< HEAD
   */	
  def toTraversable: GenTraversable[A]
  
=======
   */
  def toTraversable: GenTraversable[A]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to an iterable collection.  Note that
   *  the choice of target `Iterable` is lazy in this default implementation
   *  as this `TraversableOnce` may be lazy and unevaluated (i.e. it may
   *  be an iterator which is only traversable once).
   *
<<<<<<< HEAD
   *  $willNotTerminateInf 
   *  @return an `Iterable` containing all elements of this $coll.
   */	
  def toIterable: GenIterable[A]
  
  /** Converts this $coll to a sequence. As with `toIterable`, it's lazy
   *  in this default implementation, as this `TraversableOnce` may be
   *  lazy and unevaluated.
   *  
   *  $willNotTerminateInf
   *  @return a sequence containing all elements of this $coll.
   */	
  def toSeq: GenSeq[A]
  
=======
   *  $willNotTerminateInf
   *  @return an `Iterable` containing all elements of this $coll.
   */
  def toIterable: GenIterable[A]

  /** Converts this $coll to a sequence. As with `toIterable`, it's lazy
   *  in this default implementation, as this `TraversableOnce` may be
   *  lazy and unevaluated.
   *
   *  $willNotTerminateInf
   *  @return a sequence containing all elements of this $coll.
   */
  def toSeq: GenSeq[A]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to a set.
   *  $willNotTerminateInf
   *  @return      a set containing all elements of this $coll.
   */
  def toSet[A1 >: A]: GenSet[A1]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Converts this $coll to a map.  This method is unavailable unless
   *  the elements are members of Tuple2, each ((T, U)) becoming a key-value
   *  pair in the map.  Duplicate keys will be overwritten by later keys:
   *  if this is an unordered collection, which key is in the resulting map
   *  is undefined.
   *  $willNotTerminateInf
   *  @return    a map containing all elements of this $coll.
   *  @usecase   def toMap[T, U]: Map[T, U]
   *  @return    a map of type `immutable.Map[T, U]`
   *             containing all key/value pairs of type `(T, U)` of this $coll.
   */
  def toMap[K, V](implicit ev: A <:< (K, V)): GenMap[K, V]
}