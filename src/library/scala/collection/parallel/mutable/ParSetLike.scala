/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package parallel.mutable



import scala.collection.mutable.Set
import scala.collection.mutable.Builder
import scala.collection.mutable.Cloneable
import scala.collection.GenSetLike
<<<<<<< HEAD



=======
import scala.collection.generic.Growable
import scala.collection.generic.Shrinkable
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0



/** A template trait for mutable parallel sets. This trait is mixed in with concrete
 *  parallel sets to override the representation type.
<<<<<<< HEAD
 *  
 *  $sideeffects
 *  
 *  @tparam T    the element type of the set
 *  
=======
 *
 *  $sideeffects
 *
 *  @tparam T    the element type of the set
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait ParSetLike[T,
                 +Repr <: ParSetLike[T, Repr, Sequential] with ParSet[T],
                 +Sequential <: mutable.Set[T] with mutable.SetLike[T, Sequential]]
extends GenSetLike[T, Repr]
   with collection.parallel.ParIterableLike[T, Repr, Sequential]
   with collection.parallel.ParSetLike[T, Repr, Sequential]
<<<<<<< HEAD
   with Cloneable[Repr]
{
self => 
  override def empty: Repr
  
  def +=(elem: T): this.type
  
  def -=(elem: T): this.type
  
  def +(elem: T) = this.clone() += elem
  
  def -(elem: T) = this.clone() -= elem
  
=======
   with Growable[T]
   with Shrinkable[T]
   with Cloneable[Repr]
{
self =>
  override def empty: Repr

  def +=(elem: T): this.type

  def -=(elem: T): this.type

  def +(elem: T) = this.clone() += elem

  def -(elem: T) = this.clone() -= elem

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // note: should not override toSet
}








































