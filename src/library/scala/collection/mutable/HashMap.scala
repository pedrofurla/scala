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
import scala.collection.parallel.mutable.ParHashMap

/** This class implements mutable maps using a hashtable.
<<<<<<< HEAD
 *  
 *  @since 1
 *  
 *  @tparam A    the type of the keys contained in this hash map.
 *  @tparam B    the type of the values assigned to keys in this hash map.
 *  
 *  @define Coll mutable.HashMap
 *  @define coll mutable hash map
 *  @define thatinfo the class of the returned collection. In the standard library configuration,
 *    `That` is always `HashMap[A, B]` if the elements contained in the resulting collection are 
=======
 *
 *  @since 1
 *  @see [[http://docs.scala-lang.org/overviews/collections/concrete-mutable-collection-classes.html#hash_tables "Scala's Collection Library overview"]]
 *  section on `Hash Tables` for more information.
 *
 *  @tparam A    the type of the keys contained in this hash map.
 *  @tparam B    the type of the values assigned to keys in this hash map.
 *
 *  @define Coll mutable.HashMap
 *  @define coll mutable hash map
 *  @define thatinfo the class of the returned collection. In the standard library configuration,
 *    `That` is always `HashMap[A, B]` if the elements contained in the resulting collection are
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    pairs of type `(A, B)`. This is because an implicit of type `CanBuildFrom[HashMap, (A, B), HashMap[A, B]]`
 *    is defined in object `HashMap`. Otherwise, `That` resolves to the most specific type that doesn't have
 *    to contain pairs of type `(A, B)`, which is `Iterable`.
 *  @define bfinfo an implicit value of class `CanBuildFrom` which determines the
 *    result class `That` from the current representation type `Repr`
 *    and the new element type `B`. This is usually the `canBuildFrom` value
 *    defined in object `HashMap`.
 *  @define mayNotTerminateInf
 *  @define willNotTerminateInf
 */
@SerialVersionUID(1L)
class HashMap[A, B] private[collection] (contents: HashTable.Contents[A, DefaultEntry[A, B]])
<<<<<<< HEAD
extends Map[A, B] 
   with MapLike[A, B, HashMap[A, B]] 
=======
extends AbstractMap[A, B]
   with Map[A, B]
   with MapLike[A, B, HashMap[A, B]]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   with HashTable[A, DefaultEntry[A, B]]
   with CustomParallelizable[(A, B), ParHashMap[A, B]]
   with Serializable
{
  initWithContents(contents)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  type Entry = DefaultEntry[A, B]

  override def empty: HashMap[A, B] = HashMap.empty[A, B]
  override def clear() = clearTable()
  override def size: Int = tableSize
<<<<<<< HEAD
  
  def this() = this(null)
  
  override def par = new ParHashMap[A, B](hashTableContents)
  
=======

  def this() = this(null)

  override def par = new ParHashMap[A, B](hashTableContents)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // contains and apply overridden to avoid option allocations.
  override def contains(key: A) = findEntry(key) != null
  override def apply(key: A): B = {
    val result = findEntry(key)
    if (result == null) default(key)
    else result.value
  }

  def get(key: A): Option[B] = {
    val e = findEntry(key)
    if (e == null) None
    else Some(e.value)
  }

  override def put(key: A, value: B): Option[B] = {
    val e = findEntry(key)
    if (e == null) { addEntry(new Entry(key, value)); None }
    else { val v = e.value; e.value = value; Some(v) }
  }

  override def update(key: A, value: B): Unit = put(key, value)

  override def remove(key: A): Option[B] = {
    val e = removeEntry(key)
    if (e ne null) Some(e.value)
    else None
  }

<<<<<<< HEAD
  def += (kv: (A, B)): this.type = { 
=======
  def += (kv: (A, B)): this.type = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val e = findEntry(kv._1)
    if (e == null) addEntry(new Entry(kv._1, kv._2))
    else e.value = kv._2
    this
  }

  def -=(key: A): this.type = { removeEntry(key); this }

  def iterator = entriesIterator map {e => (e.key, e.value)}
<<<<<<< HEAD
  
  override def foreach[C](f: ((A, B)) => C): Unit = foreachEntry(e => f(e.key, e.value))
  
=======

  override def foreach[C](f: ((A, B)) => C): Unit = foreachEntry(e => f(e.key, e.value))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /* Override to avoid tuple allocation in foreach */
  override def keySet: collection.Set[A] = new DefaultKeySet {
    override def foreach[C](f: A => C) = foreachEntry(e => f(e.key))
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /* Override to avoid tuple allocation in foreach */
  override def values: collection.Iterable[B] = new DefaultValuesIterable {
    override def foreach[C](f: B => C) = foreachEntry(e => f(e.value))
  }
<<<<<<< HEAD
  
  /* Override to avoid tuple allocation */
  override def keysIterator: Iterator[A] = new Iterator[A] {
=======

  /* Override to avoid tuple allocation */
  override def keysIterator: Iterator[A] = new AbstractIterator[A] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val iter    = entriesIterator
    def hasNext = iter.hasNext
    def next()  = iter.next.key
  }
<<<<<<< HEAD
  
  /* Override to avoid tuple allocation */
  override def valuesIterator: Iterator[B] = new Iterator[B] {
=======

  /* Override to avoid tuple allocation */
  override def valuesIterator: Iterator[B] = new AbstractIterator[B] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val iter    = entriesIterator
    def hasNext = iter.hasNext
    def next()  = iter.next.value
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Toggles whether a size map is used to track hash map statistics.
   */
  def useSizeMap(t: Boolean) = if (t) {
    if (!isSizeMapDefined) sizeMapInitAndRebuild
  } else sizeMapDisable
<<<<<<< HEAD
  
  private def writeObject(out: java.io.ObjectOutputStream) {
    serializeTo(out, _.value)
  }
  
  private def readObject(in: java.io.ObjectInputStream) {
    init[B](in, new Entry(_, _))
  }
  
=======

  private def writeObject(out: java.io.ObjectOutputStream) {
    serializeTo(out, _.value)
  }

  private def readObject(in: java.io.ObjectInputStream) {
    init[B](in, new Entry(_, _))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

/** $factoryInfo
 *  @define Coll mutable.HashMap
 *  @define coll mutable hash map
 */
object HashMap extends MutableMapFactory[HashMap] {
  implicit def canBuildFrom[A, B]: CanBuildFrom[Coll, (A, B), HashMap[A, B]] = new MapCanBuildFrom[A, B]
  def empty[A, B]: HashMap[A, B] = new HashMap[A, B]
}
