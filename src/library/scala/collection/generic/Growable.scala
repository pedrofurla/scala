/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
<<<<<<< HEAD
package generic 
=======
package generic
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/** This trait forms part of collections that can be augmented
 *  using a `+=` operator and that can be cleared of all elements using
 *  a `clear` method.
<<<<<<< HEAD
 *        
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author   Martin Odersky
 *  @version 2.8
 *  @since   2.8
 *  @define coll growable collection
 *  @define Coll Growable
 *  @define add  add
 *  @define Add  add
 */
<<<<<<< HEAD
trait Growable[-A] { 
=======
trait Growable[-A] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** ${Add}s a single element to this $coll.
   *
   *  @param elem  the element to $add.
   *  @return the $coll itself
   */
  def +=(elem: A): this.type

  /** ${Add}s two or more elements to this $coll.
   *
   *  @param elem1 the first element to $add.
   *  @param elem2 the second element to $add.
   *  @param elems the remaining elements to $add.
   *  @return the $coll itself
   */
  def +=(elem1: A, elem2: A, elems: A*): this.type = this += elem1 += elem2 ++= elems

  /** ${Add}s all elements produced by a TraversableOnce to this $coll.
   *
   *  @param iter  the TraversableOnce producing the elements to $add.
<<<<<<< HEAD
   *  @return  the $coll itself. 
   */
  def ++=(xs: TraversableOnce[A]): this.type = { xs.seq foreach += ; this } 
=======
   *  @return  the $coll itself.
   */
  def ++=(xs: TraversableOnce[A]): this.type = { xs.seq foreach += ; this }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** Clears the $coll's contents. After this operation, the
   *  $coll is empty.
   */
  def clear()
}
