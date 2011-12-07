/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package generic

import mutable.Builder

/** This class represents companions of classes which require ClassManifests
 *  for their element types.
 *
 *  @author Aleksandar Prokopec
 */
abstract class GenericClassManifestCompanion[+CC[X] <: Traversable[X]] {
  type Coll = CC[_]
<<<<<<< HEAD
  
  def newBuilder[A](implicit ord: ClassManifest[A]): Builder[A, CC[A]]
  
  def empty[A: ClassManifest]: CC[A] = newBuilder[A].result
  
=======

  def newBuilder[A](implicit ord: ClassManifest[A]): Builder[A, CC[A]]

  def empty[A: ClassManifest]: CC[A] = newBuilder[A].result

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply[A](elems: A*)(implicit ord: ClassManifest[A]): CC[A] = {
    val b = newBuilder[A]
    b ++= elems
    b.result
  }
}
