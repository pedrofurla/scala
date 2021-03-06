/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package mutable

import generic._
import annotation.{migration, bridge}
import parallel.mutable.ParMap

/** A template trait for mutable maps.
 *  $mapNote
 *  $mapTags
 *  @since   2.8
 */
trait MapLike[A, B, +This <: MapLike[A, B, This] with Map[A, B]]
  extends scala.collection.MapLike[A, B, This]
<<<<<<< HEAD
     with Builder[(A, B), This] 
=======
     with Builder[(A, B), This]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     with Growable[(A, B)]
     with Shrinkable[A]
     with Cloneable[This]
     with Parallelizable[(A, B), ParMap[A, B]]
{ self =>

  import scala.collection.Traversable

  /** A common implementation of `newBuilder` for all mutable maps
   *    in terms of `empty`.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *    Overrides `MapLike` implementation for better efficiency.
   */
  override protected[this] def newBuilder: Builder[(A, B), This] = empty

  protected[this] override def parCombiner = ParMap.newCombiner[A, B]

  /** Adds a new key/value pair to this map and optionally returns previously bound value.
   *  If the map already contains a
   *  mapping for the key, it will be overridden by the new value.
   *
   * @param key    the key to update
   * @param value  the new value
   * @return an option value containing the value associated with the key
   *         before the `put` operation was executed, or `None` if `key`
   *         was not defined in the map before.
   */
  def put(key: A, value: B): Option[B] = {
    val r = get(key)
    update(key, value)
    r
  }

  /** Adds a new key/value pair to this map.
   *  If the map already contains a
   *  mapping for the key, it will be overridden by the new value.
   *
   *  @param key    The key to update
   *  @param value  The new value
<<<<<<< HEAD
   */    
=======
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def update(key: A, value: B) { this += ((key, value)) }

  /** Adds a new key/value pair to this map.
   *  If the map already contains a
   *  mapping for the key, it will be overridden by the new value.
   *  @param    kv the key/value pair.
   *  @return   the map itself
   */
  def += (kv: (A, B)): this.type

  /** Creates a new map consisting of all key/value pairs of the current map
   *  plus a new pair of a given key and value.
   *
   *  @param key    The key to add
   *  @param value  The new value
   *  @return       A fresh immutable map with the binding from `key` to
   *                `value` added to this map.
   */
  override def updated[B1 >: B](key: A, value: B1): Map[A, B1] = this + ((key, value))

  /** Creates a new map containing a new key/value mapping and all the key/value mappings
   *  of this map.
   *
   *  Mapping `kv` will override existing mappings from this map with the same key.
   *
   *  @param kv    the key/value mapping to be added
   *  @return      a new map containing mappings of this map and the mapping `kv`.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To add an element as a\n"+
    "side effect to an existing map and return that map itself, use +=."
  )
  def + [B1 >: B] (kv: (A, B1)): Map[A, B1] = clone().asInstanceOf[Map[A, B1]] += kv

  /** Creates a new map containing two or more key/value mappings and all the key/value
   *  mappings of this map.
<<<<<<< HEAD
   *  
   *  Specified mappings will override existing mappings from this map with the same keys.
   *  
=======
   *
   *  Specified mappings will override existing mappings from this map with the same keys.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param elem1 the first element to add.
   *  @param elem2 the second element to add.
   *  @param elems the remaining elements to add.
   *  @return      a new map containing mappings of this map and two or more specified mappings.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To add an element as a\n"+
    "side effect to an existing map and return that map itself, use +=."
  )
  override def + [B1 >: B] (elem1: (A, B1), elem2: (A, B1), elems: (A, B1) *): Map[A, B1] =
    clone().asInstanceOf[Map[A, B1]] += elem1 += elem2 ++= elems

  /** Creates a new map containing the key/value mappings provided by the specified traversable object
   *  and all the key/value mappings of this map.
<<<<<<< HEAD
   *  
   *  Note that existing mappings from this map with the same key as those in `xs` will be overriden.
   *  
=======
   *
   *  Note that existing mappings from this map with the same key as those in `xs` will be overriden.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param xs     the traversable object.
   *  @return       a new map containing mappings of this map and those provided by `xs`.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To add the elements as a\n"+
    "side effect to an existing map and return that map itself, use ++=."
  )
  override def ++[B1 >: B](xs: GenTraversableOnce[(A, B1)]): Map[A, B1] =
    clone().asInstanceOf[Map[A, B1]] ++= xs.seq

  @bridge def ++[B1 >: B](xs: TraversableOnce[(A, B1)]): Map[A, B1] = ++(xs: GenTraversableOnce[(A, B1)])

  /** Removes a key from this map, returning the value associated previously
   *  with that key as an option.
   *  @param    key the key to be removed
   *  @return   an option value containing the value associated previously with `key`,
   *            or `None` if `key` was not defined in the map before.
   */
  def remove(key: A): Option[B] = {
    val r = get(key)
    this -= key
    r
  }

  /** Removes a key from this map.
   *  @param    key the key to be removed
   *  @return   the map itself.
   */
  def -= (key: A): this.type

  /** Creates a new map with all the key/value mappings of this map except the key/value mapping
   *  with the specified key.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param    key the key to be removed
   *  @return   a new map with all the mappings of this map except that with a key `key`.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To remove an element as a\n"+
    "side effect to an existing map and return that map itself, use -=."
  )
  override def -(key: A): This = clone() -= key

<<<<<<< HEAD
  /** If given key is defined in this map, remove it and return associated value as an Option.
   *  If key is not present return None.
   *  @param    key the key to be removed
   */
  @deprecated("Use `remove` instead", "2.8.0")
  def removeKey(key: A): Option[B] = remove(key)

=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Removes all bindings from the map. After this operation has completed,
   *  the map will be empty.
   */
  def clear() { keysIterator foreach -= }
<<<<<<< HEAD
  
  /** If given key is already in this map, returns associated value.
   *  
=======

  /** If given key is already in this map, returns associated value.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  Otherwise, computes value from given expression `op`, stores with key
   *  in map and returns that value.
   *  @param  key the key to test
   *  @param  op  the computation yielding the value to associate with `key`, if
   *              `key` is previously unbound.
   *  @return     the value associated with key (either previously or as a result
   *              of executing the method).
<<<<<<< HEAD
   */  
=======
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def getOrElseUpdate(key: A, op: => B): B =
    get(key) match {
      case Some(v) => v
      case None => val d = op; this(key) = d; d
    }

  /** Applies a transformation function to all values contained in this map.
   *  The transformation function produces new values from existing keys
   *  associated values.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param f  the transformation to apply
   * @return   the map itself.
   */
  def transform(f: (A, B) => B): this.type = {
    this.iterator foreach {
      case (key, value) => update(key, f(key, value))
    }
    this
  }

  /** Retains only those mappings for which the predicate
   *  `p` returns `true`.
   *
<<<<<<< HEAD
   * @param p  The test predicate  
=======
   * @param p  The test predicate
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def retain(p: (A, B) => Boolean): this.type = {
    for ((k, v) <- this.seq ; if !p(k, v))
      this -= k

    this
  }

  override def clone(): This = empty ++= repr

  /** The result when this map is used as a builder
   *  @return  the map representation itself.
   */
  def result: This = repr

  /** Creates a new map with all the key/value mappings of this map except mappings with keys
   *  equal to any of the two or more specified keys.
   *
   *  @param elem1 the first element to remove.
   *  @param elem2 the second element to remove.
   *  @param elems the remaining elements to remove.
   *  @return      a new map containing all the mappings of this map except mappings
   *               with a key equal to `elem1`, `elem2` or any of `elems`.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To remove an element as a\n"+
    "side effect to an existing map and return that map itself, use -=."
  )
  override def -(elem1: A, elem2: A, elems: A*): This =
    clone() -= elem1 -= elem2 --= elems

  /** Creates a new map with all the key/value mappings of this map except mappings with keys
   *  equal to any of those provided by the specified traversable object.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param xs       the traversable object.
   *  @return         a new map with all the key/value mappings of this map except mappings
   *                  with a key equal to a key from `xs`.
   */
  @migration(2, 8,
    "As of 2.8, this operation creates a new map.  To remove the elements as a\n"+
    "side effect to an existing map and return that map itself, use --=."
<<<<<<< HEAD
  )  
=======
  )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def --(xs: GenTraversableOnce[A]): This = clone() --= xs.seq

  @bridge def --(xs: TraversableOnce[A]): This =  --(xs: GenTraversableOnce[A])
}
