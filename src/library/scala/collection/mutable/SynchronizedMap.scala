/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package mutable

import annotation.migration

/** This class should be used as a mixin. It synchronizes the `Map`
 *  functions of the class into which it is mixed in.
<<<<<<< HEAD
 *  
 *  @tparam A     type of the keys contained in this map.
 *  @tparam B     type of the values associated with keys.
 *  
=======
 *
 *  @tparam A     type of the keys contained in this map.
 *  @tparam B     type of the values associated with keys.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author  Matthias Zenger, Martin Odersky
 *  @version 2.0, 31/12/2006
 *  @since   1
 *  @define Coll SynchronizedMap
 *  @define coll synchronized map
 */
trait SynchronizedMap[A, B] extends Map[A, B] {

  abstract override def get(key: A): Option[B] = synchronized { super.get(key) }
  abstract override def iterator: Iterator[(A, B)] = synchronized { super.iterator }
  abstract override def += (kv: (A, B)): this.type = synchronized[this.type] { super.+=(kv) }
  abstract override def -= (key: A): this.type = synchronized[this.type] { super.-=(key) }

  override def size: Int = synchronized { super.size }
  override def put(key: A, value: B): Option[B] = synchronized { super.put(key, value) }
  override def update(key: A, value: B): Unit = synchronized { super.update(key, value) }
  override def remove(key: A): Option[B] = synchronized { super.remove(key) }
  override def clear(): Unit = synchronized { super.clear() }
  override def getOrElseUpdate(key: A, default: => B): B = synchronized { super.getOrElseUpdate(key, default) }
  override def transform(f: (A, B) => B): this.type = synchronized[this.type] { super.transform(f) }
  override def retain(p: (A, B) => Boolean): this.type = synchronized[this.type] { super.retain(p) }
  @migration(2, 8, "As of 2.8, values returns Iterable[B] rather than Iterator[B].")
  override def values: collection.Iterable[B] = synchronized { super.values }
  override def valuesIterator: Iterator[B] = synchronized { super.valuesIterator }
  override def clone(): Self = synchronized { super.clone() }
  override def foreach[U](f: ((A, B)) => U) = synchronized { super.foreach(f) }
  override def apply(key: A): B = synchronized { super.apply(key) }
  override def keySet: collection.Set[A] = synchronized { super.keySet }
  @migration(2, 8, "As of 2.8, keys returns Iterable[A] rather than Iterator[A].")
  override def keys: collection.Iterable[A] = synchronized { super.keys }
  override def keysIterator: Iterator[A] = synchronized { super.keysIterator }
  override def isEmpty: Boolean = synchronized { super.isEmpty }
  override def contains(key: A): Boolean = synchronized {super.contains(key) }
  override def isDefinedAt(key: A) = synchronized { super.isDefinedAt(key) }

  // @deprecated("See Map.+ for explanation") override def +(kv: (A, B)): this.type = synchronized[this.type] { super.+(kv) }
  // can't override -, -- same type!
  // @deprecated override def -(key: A): Self = synchronized { super.-(key) }

<<<<<<< HEAD
  // !!! todo: also add all other methods 
=======
  // !!! todo: also add all other methods
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

