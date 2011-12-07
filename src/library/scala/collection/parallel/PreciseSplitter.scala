/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel


import scala.collection.Seq


/** A precise splitter (or a precise split iterator) can be split into arbitrary number of splitters
<<<<<<< HEAD
 *  that traverse disjoint subsets of arbitrary sizes. 
 *  
 *  Implementors might want to override the parameterless `split` method for efficiency.
 *  
 *  @tparam T     type of the elements this splitter traverses
 *  
=======
 *  that traverse disjoint subsets of arbitrary sizes.
 *
 *  Implementors might want to override the parameterless `split` method for efficiency.
 *
 *  @tparam T     type of the elements this splitter traverses
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @since 2.9
 *  @author Aleksandar Prokopec
 */
trait PreciseSplitter[+T] extends Splitter[T] {
<<<<<<< HEAD
  
  /** Splits the splitter into disjunct views.
   *  
=======

  /** Splits the splitter into disjunct views.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  This overloaded version of the `split` method is specific to precise splitters.
   *  It returns a sequence of splitters, each iterating some subset of the
   *  elements in this splitter. The sizes of the subsplitters in the partition is equal to
   *  the size in the corresponding argument, as long as there are enough elements in this
   *  splitter to split it that way.
<<<<<<< HEAD
   *  
   *  If there aren't enough elements, a zero element splitter is appended for each additional argument.
   *  If there are additional elements, an additional splitter is appended at the end to compensate.
   *  
=======
   *
   *  If there aren't enough elements, a zero element splitter is appended for each additional argument.
   *  If there are additional elements, an additional splitter is appended at the end to compensate.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  For example, say we have a splitter `ps` with 100 elements. Invoking:
   *  {{{
   *    ps.split(50, 25, 25, 10, 5)
   *  }}}
   *  will return a sequence of five splitters, last two views being empty. On the other hand, calling:
   *  {{{
   *    ps.split(50, 40)
   *  }}}
   *  will return a sequence of three splitters, last of them containing ten elements.
<<<<<<< HEAD
   *  
   *  '''Note:''' this method actually invalidates the current splitter.
   *  
   *  Unlike the case with `split` found in splitters, views returned by this method can be empty.
   *  
=======
   *
   *  '''Note:''' this method actually invalidates the current splitter.
   *
   *  Unlike the case with `split` found in splitters, views returned by this method can be empty.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param sizes   the sizes used to split this split iterator into iterators that traverse disjunct subsets
   *  @return        a sequence of disjunct subsequence iterators of this parallel iterator
   */
  def psplit(sizes: Int*): Seq[PreciseSplitter[T]]
<<<<<<< HEAD
  
  def split: Seq[PreciseSplitter[T]]
  
=======

  def split: Seq[PreciseSplitter[T]]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}





