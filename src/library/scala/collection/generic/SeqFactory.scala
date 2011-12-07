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

/** A template for companion objects of Seq and subclasses thereof.
 *
 *  @since 2.8
 */
abstract class SeqFactory[CC[X] <: Seq[X] with GenericTraversableTemplate[X, CC]]
extends GenSeqFactory[CC] with TraversableFactory[CC] {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** This method is called in a pattern match { case Seq(...) => }.
   *
   *  @param x the selector value
   *  @return  sequence wrapped in an option, if this is a Seq, otherwise none
   */
  def unapplySeq[A](x: CC[A]): Some[CC[A]] = Some(x)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

