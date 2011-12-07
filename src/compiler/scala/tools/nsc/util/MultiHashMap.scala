package scala.tools.nsc.util

import scala.collection.{ mutable, immutable }

/** A hashmap with set-valued values, and an empty set as default value
 */
class MultiHashMap[K, V] extends mutable.HashMap[K, immutable.Set[V]] {
<<<<<<< HEAD
  override def default(key: K): immutable.Set[V] = Set() 
=======
  override def default(key: K): immutable.Set[V] = Set()
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
