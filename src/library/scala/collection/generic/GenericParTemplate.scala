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

package scala.collection.generic

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.collection.parallel.Combiner
import scala.collection.parallel.ParIterable
import scala.collection.parallel.ParMap
import scala.collection.parallel.TaskSupport

<<<<<<< HEAD

import annotation.unchecked.uncheckedVariance






/** A template trait for collections having a companion.
 *  
 *  @tparam A    the element type of the collection
 *  @tparam CC   the type constructor representing the collection class
 *  @since 2.8
 *  @author prokopec
=======
import annotation.unchecked.uncheckedVariance

/** A template trait for collections having a companion.
 *
 *  @tparam A    the element type of the collection
 *  @tparam CC   the type constructor representing the collection class
 *  @author Aleksandar Prokopec
 *  @since 2.8
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait GenericParTemplate[+A, +CC[X] <: ParIterable[X]]
extends GenericTraversableTemplate[A, CC]
   with HasNewCombiner[A, CC[A] @uncheckedVariance]
{
  def companion: GenericCompanion[CC] with GenericParCompanion[CC]
<<<<<<< HEAD
  
  protected[this] override def newBuilder: collection.mutable.Builder[A, CC[A]] = newCombiner
  
=======

  protected[this] override def newBuilder: collection.mutable.Builder[A, CC[A]] = newCombiner

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected[this] override def newCombiner: Combiner[A, CC[A]] = {
    val cb = companion.newCombiner[A]
    cb
  }
<<<<<<< HEAD
  
  override def genericBuilder[B]: Combiner[B, CC[B]] = genericCombiner[B]
  
=======

  override def genericBuilder[B]: Combiner[B, CC[B]] = genericCombiner[B]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def genericCombiner[B]: Combiner[B, CC[B]] = {
    val cb = companion.newCombiner[B]
    cb
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}


trait GenericParMapTemplate[K, +V, +CC[X, Y] <: ParMap[X, Y]] extends GenericParTemplate[(K, V), ParIterable]
{
  protected[this] override def newCombiner: Combiner[(K, V), CC[K, V]] = {
    val cb = mapCompanion.newCombiner[K, V]
    cb
  }
<<<<<<< HEAD
  
  def mapCompanion: GenericParMapCompanion[CC]
  
=======

  def mapCompanion: GenericParMapCompanion[CC]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def genericMapCombiner[P, Q]: Combiner[(P, Q), CC[P, Q]] = {
    val cb = mapCompanion.newCombiner[P, Q]
    cb
  }
}

<<<<<<< HEAD





=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
