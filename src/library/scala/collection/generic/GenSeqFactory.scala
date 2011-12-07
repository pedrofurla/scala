/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0


package scala.collection
package generic

import annotation.bridge

/** A template for companion objects of Seq and subclasses thereof.
 *
 *  @since 2.8
 */
abstract class GenSeqFactory[CC[X] <: GenSeq[X] with GenericTraversableTemplate[X, CC]]
extends GenTraversableFactory[CC] {
<<<<<<< HEAD
  
  @bridge
  def unapplySeq[A](x: GenSeq[A]): Some[GenSeq[A]] = Some(x)
  
=======

  @bridge
  def unapplySeq[A](x: GenSeq[A]): Some[GenSeq[A]] = Some(x)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
