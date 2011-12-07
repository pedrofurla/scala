/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
<<<<<<< HEAD
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
=======
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.collection
package generic

import mutable.Builder

<<<<<<< HEAD




=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
/** This class represents companions of classes which require the ordered trait
 *  for their element types.
 *
 *  @author Aleksandar Prokopec
<<<<<<< HEAD
 */
abstract class GenericOrderedCompanion[+CC[X] <: Traversable[X]] {
  type Coll = CC[_]
  
  def newBuilder[A](implicit ord: Ordering[A]): Builder[A, CC[A]]
  
  def empty[A: Ordering]: CC[A] = newBuilder[A].result
  
=======
 *  @since 2.8
 */
abstract class GenericOrderedCompanion[+CC[X] <: Traversable[X]] {
  type Coll = CC[_]

  def newBuilder[A](implicit ord: Ordering[A]): Builder[A, CC[A]]

  def empty[A: Ordering]: CC[A] = newBuilder[A].result

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply[A](elems: A*)(implicit ord: Ordering[A]): CC[A] = {
    val b = newBuilder[A]
    b ++= elems
    b.result
  }
}

