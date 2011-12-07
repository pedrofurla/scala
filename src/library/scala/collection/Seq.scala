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

import generic._
import mutable.Builder

/** A base trait for sequences.
 *  $seqInfo
 */
<<<<<<< HEAD
trait Seq[+A] extends PartialFunction[Int, A] 
                      with Iterable[A] 
=======
trait Seq[+A] extends PartialFunction[Int, A]
                      with Iterable[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                      with GenSeq[A]
                      with GenericTraversableTemplate[A, Seq]
                      with SeqLike[A, Seq[A]] {
  override def companion: GenericCompanion[Seq] = Seq
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def seq: Seq[A] = this
}

/** $factoryInfo
<<<<<<< HEAD
 *  The current default implementation of a $Coll is a `Vector`.
=======
 *  The current default implementation of a $Coll is a `List`.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define coll sequence
 *  @define Coll Seq
 */
object Seq extends SeqFactory[Seq] {
<<<<<<< HEAD

  private[collection] val hashSeed = "Seq".hashCode
  
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** $genericCanBuildFromInfo */
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, Seq[A]] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]

  def newBuilder[A]: Builder[A, Seq[A]] = immutable.Seq.newBuilder[A]
<<<<<<< HEAD
  
  @deprecated("use View instead", "2.8.0")
  type Projection[A] = SeqView[A, Coll] 
  
  @deprecated("use Seq(value) instead", "2.8.0")
  def singleton[A](value: A) = Seq(value) 
}

=======
}

/** Explicit instantiation of the `Seq` trait to reduce class file size in subclasses. */
private[scala] abstract class AbstractSeq[+A] extends AbstractIterable[A] with Seq[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
