/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel
package mutable



import collection.generic._
import collection.mutable.Builder
import collection.mutable.Cloneable
<<<<<<< HEAD
=======
import collection.generic.Growable
import collection.generic.Shrinkable
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0



/** A template trait for mutable parallel maps. This trait is to be mixed in
 *  with concrete parallel maps to override the representation type.
<<<<<<< HEAD
 *  
 *  $sideeffects
 *  
 *  @tparam K    the key type of the map
 *  @tparam V    the value type of the map
 *  
=======
 *
 *  $sideeffects
 *
 *  @tparam K    the key type of the map
 *  @tparam V    the value type of the map
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait ParMapLike[K,
                 V,
                 +Repr <: ParMapLike[K, V, Repr, Sequential] with ParMap[K, V],
                 +Sequential <: collection.mutable.Map[K, V] with collection.mutable.MapLike[K, V, Sequential]]
extends collection.GenMapLike[K, V, Repr]
<<<<<<< HEAD
   with collection.parallel.ParMapLike[K, V, Repr, Sequential] 
   with Cloneable[Repr] {
  
  // note: should not override toMap
  
  def put(key: K, value: V): Option[V]
  
  def +=(kv: (K, V)): this.type
  
  def -=(key: K): this.type
  
  def +[U >: V](kv: (K, U)) = this.clone().asInstanceOf[ParMap[K, U]] += kv
  
  def -(key: K) = this.clone() -= key
  
  def clear(): Unit
  
}
   
=======
   with collection.parallel.ParMapLike[K, V, Repr, Sequential]
   with Growable[(K, V)]
   with Shrinkable[K]
   with Cloneable[Repr]
{
  // note: should not override toMap

  def put(key: K, value: V): Option[V]

  def +=(kv: (K, V)): this.type

  def -=(key: K): this.type

  def +[U >: V](kv: (K, U)) = this.clone().asInstanceOf[ParMap[K, U]] += kv

  def -(key: K) = this.clone() -= key

  def clear(): Unit

}

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
