/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package immutable

import generic._

/**
 * A generic trait for immutable maps. Concrete classes have to provide
 * functionality for the abstract methods in `Map`:
 *
 * {{{
 *    def get(key: A): Option[B]
 *    def iterator: Iterator[(A, B)]
 *    def + [B1 >: B](kv: (A, B1)): Map[A, B1]
 *    def -(key: A): Map[A, B]
 * }}}
<<<<<<< HEAD
 * 
 * @since 1
 */
trait Map[A, +B] extends Iterable[(A, B)] 
//                    with GenMap[A, B]
                    with scala.collection.Map[A, B] 
=======
 *
 * @since 1
 */
trait Map[A, +B] extends Iterable[(A, B)]
//                    with GenMap[A, B]
                    with scala.collection.Map[A, B]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                    with MapLike[A, B, Map[A, B]] { self =>

  override def empty: Map[A, B] = Map.empty
  override def toMap[T, U](implicit ev: (A, B) <:< (T, U)): immutable.Map[T, U] =
    self.asInstanceOf[immutable.Map[T, U]]

  override def seq: Map[A, B] = this

  /** The same map with a given default function.
   *  Note: `get`, `contains`, `iterator`, `keys`, etc are not affected by `withDefault`.
<<<<<<< HEAD
   *  
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  Invoking transformer methods (e.g. `map`) will not preserve the default value.
   *
   *  @param d     the function mapping keys to values, used for non-present keys
   *  @return      a wrapper of the map with a default value
   */
<<<<<<< HEAD
  def withDefault[B1 >: B](d: A => B1): immutable.Map[A, B1] = new Map.WithDefault[A, B1](this, d) 
  
  /** The same map with a given default value.
   *  
=======
  def withDefault[B1 >: B](d: A => B1): immutable.Map[A, B1] = new Map.WithDefault[A, B1](this, d)

  /** The same map with a given default value.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  Invoking transformer methods (e.g. `map`) will not preserve the default value.
   *
   *  @param d     the function mapping keys to values, used for non-present keys
   *  @return      a wrapper of the map with a default value
   */
  def withDefaultValue[B1 >: B](d: B1): immutable.Map[A, B1] = new Map.WithDefault[A, B1](this, x => d)

<<<<<<< HEAD
  /** Add a key/value pair to this map. 
=======
  /** Add a key/value pair to this map.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param    key the key
   *  @param    value the value
   *  @return   A new map with the new binding added to this map
   */
  override def updated [B1 >: B](key: A, value: B1): Map[A, B1]
  def + [B1 >: B](kv: (A, B1)): Map[A, B1]
}

/** $factoryInfo
 *  @define Coll immutable.Map
 *  @define coll immutable map
 */
object Map extends ImmutableMapFactory[Map] {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** $mapCanBuildFromInfo */
  implicit def canBuildFrom[A, B]: CanBuildFrom[Coll, (A, B), Map[A, B]] = new MapCanBuildFrom[A, B]

  def empty[A, B]: Map[A, B] = EmptyMap.asInstanceOf[Map[A, B]]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class WithDefault[A, +B](underlying: Map[A, B], d: A => B) extends collection.Map.WithDefault[A, B](underlying, d) with Map[A, B] {
    override def empty = new WithDefault(underlying.empty, d)
    override def updated[B1 >: B](key: A, value: B1): WithDefault[A, B1] = new WithDefault[A, B1](underlying.updated[B1](key, value), d)
    override def + [B1 >: B](kv: (A, B1)): WithDefault[A, B1] = updated(kv._1, kv._2)
    override def - (key: A): WithDefault[A, B] = new WithDefault(underlying - key, d)
<<<<<<< HEAD
    override def withDefault[B1 >: B](d: A => B1): immutable.Map[A, B1] = new WithDefault[A, B1](underlying, d) 
    override def withDefaultValue[B1 >: B](d: B1): immutable.Map[A, B1] = new WithDefault[A, B1](underlying, x => d)
  }
  
  private object EmptyMap extends Map[Any, Nothing] with Serializable {
=======
    override def withDefault[B1 >: B](d: A => B1): immutable.Map[A, B1] = new WithDefault[A, B1](underlying, d)
    override def withDefaultValue[B1 >: B](d: B1): immutable.Map[A, B1] = new WithDefault[A, B1](underlying, x => d)
  }

  private object EmptyMap extends AbstractMap[Any, Nothing] with Map[Any, Nothing] with Serializable {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def size: Int = 0
    def get(key: Any): Option[Nothing] = None
    def iterator: Iterator[(Any, Nothing)] = Iterator.empty
    override def updated [B1] (key: Any, value: B1): Map[Any, B1] = new Map1(key, value)
    def + [B1](kv: (Any, B1)): Map[Any, B1] = updated(kv._1, kv._2)
    def - (key: Any): Map[Any, Nothing] = this
  }

<<<<<<< HEAD
  @deprecated("use `Map.empty` instead", "2.8.0")
  class EmptyMap[A,B] extends Map[A,B] with Serializable {
    override def size: Int = 0
    def get(key: A): Option[B] = None
    def iterator: Iterator[(A, B)] = Iterator.empty
    override def updated [B1] (key: A, value: B1): Map[A, B1] = new Map1(key, value)
    def + [B1](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
    def - (key: A): Map[A, B] = this
  }
  
  class Map1[A, +B](key1: A, value1: B) extends Map[A, B] with Serializable {
    override def size = 1
    def get(key: A): Option[B] = 
      if (key == key1) Some(value1) else None
    def iterator = Iterator((key1, value1))
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] = 
      if (key == key1) new Map1(key1, value)
      else new Map2(key1, value1, key, value)
    def + [B1 >: B](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
    def - (key: A): Map[A, B] = 
=======
  class Map1[A, +B](key1: A, value1: B) extends AbstractMap[A, B] with Map[A, B] with Serializable {
    override def size = 1
    def get(key: A): Option[B] =
      if (key == key1) Some(value1) else None
    def iterator = Iterator((key1, value1))
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] =
      if (key == key1) new Map1(key1, value)
      else new Map2(key1, value1, key, value)
    def + [B1 >: B](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
    def - (key: A): Map[A, B] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1) Map.empty else this
    override def foreach[U](f: ((A, B)) =>  U): Unit = {
      f((key1, value1))
    }
  }

<<<<<<< HEAD
  class Map2[A, +B](key1: A, value1: B, key2: A, value2: B) extends Map[A, B] with Serializable {
    override def size = 2
    def get(key: A): Option[B] = 
      if (key == key1) Some(value1) 
      else if (key == key2) Some(value2)
      else None
    def iterator = Iterator((key1, value1), (key2, value2))
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] = 
=======
  class Map2[A, +B](key1: A, value1: B, key2: A, value2: B) extends AbstractMap[A, B] with Map[A, B] with Serializable {
    override def size = 2
    def get(key: A): Option[B] =
      if (key == key1) Some(value1)
      else if (key == key2) Some(value2)
      else None
    def iterator = Iterator((key1, value1), (key2, value2))
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1) new Map2(key1, value, key2, value2)
      else if (key == key2) new Map2(key1, value1, key2, value)
      else new Map3(key1, value1, key2, value2, key, value)
    def + [B1 >: B](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
<<<<<<< HEAD
    def - (key: A): Map[A, B] = 
=======
    def - (key: A): Map[A, B] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1) new Map1(key2, value2)
      else if (key == key2) new Map1(key1, value1)
      else this
    override def foreach[U](f: ((A, B)) =>  U): Unit = {
      f((key1, value1)); f((key2, value2))
    }
  }

<<<<<<< HEAD
  class Map3[A, +B](key1: A, value1: B, key2: A, value2: B, key3: A, value3: B) extends Map[A, B] with Serializable {
    override def size = 3
    def get(key: A): Option[B] = 
      if (key == key1) Some(value1) 
=======
  class Map3[A, +B](key1: A, value1: B, key2: A, value2: B, key3: A, value3: B) extends AbstractMap[A, B] with Map[A, B] with Serializable {
    override def size = 3
    def get(key: A): Option[B] =
      if (key == key1) Some(value1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      else if (key == key2) Some(value2)
      else if (key == key3) Some(value3)
      else None
    def iterator = Iterator((key1, value1), (key2, value2), (key3, value3))
<<<<<<< HEAD
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] = 
=======
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1)      new Map3(key1, value, key2, value2, key3, value3)
      else if (key == key2) new Map3(key1, value1, key2, value, key3, value3)
      else if (key == key3) new Map3(key1, value1, key2, value2, key3, value)
      else new Map4(key1, value1, key2, value2, key3, value3, key, value)
    def + [B1 >: B](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
<<<<<<< HEAD
    def - (key: A): Map[A, B] = 
=======
    def - (key: A): Map[A, B] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1)      new Map2(key2, value2, key3, value3)
      else if (key == key2) new Map2(key1, value1, key3, value3)
      else if (key == key3) new Map2(key1, value1, key2, value2)
      else this
    override def foreach[U](f: ((A, B)) =>  U): Unit = {
      f((key1, value1)); f((key2, value2)); f((key3, value3))
    }
  }
<<<<<<< HEAD
 
  class Map4[A, +B](key1: A, value1: B, key2: A, value2: B, key3: A, value3: B, key4: A, value4: B) extends Map[A, B] with Serializable {
    override def size = 4
    def get(key: A): Option[B] = 
      if (key == key1) Some(value1) 
=======

  class Map4[A, +B](key1: A, value1: B, key2: A, value2: B, key3: A, value3: B, key4: A, value4: B) extends AbstractMap[A, B] with Map[A, B] with Serializable {
    override def size = 4
    def get(key: A): Option[B] =
      if (key == key1) Some(value1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      else if (key == key2) Some(value2)
      else if (key == key3) Some(value3)
      else if (key == key4) Some(value4)
      else None
    def iterator = Iterator((key1, value1), (key2, value2), (key3, value3), (key4, value4))
<<<<<<< HEAD
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] = 
=======
    override def updated [B1 >: B] (key: A, value: B1): Map[A, B1] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1)      new Map4(key1, value, key2, value2, key3, value3, key4, value4)
      else if (key == key2) new Map4(key1, value1, key2, value, key3, value3, key4, value4)
      else if (key == key3) new Map4(key1, value1, key2, value2, key3, value, key4, value4)
      else if (key == key4) new Map4(key1, value1, key2, value2, key3, value3, key4, value)
      else new HashMap + ((key1, value1), (key2, value2), (key3, value3), (key4, value4), (key, value))
    def + [B1 >: B](kv: (A, B1)): Map[A, B1] = updated(kv._1, kv._2)
<<<<<<< HEAD
    def - (key: A): Map[A, B] = 
=======
    def - (key: A): Map[A, B] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (key == key1)      new Map3(key2, value2, key3, value3, key4, value4)
      else if (key == key2) new Map3(key1, value1, key3, value3, key4, value4)
      else if (key == key3) new Map3(key1, value1, key2, value2, key4, value4)
      else if (key == key4) new Map3(key1, value1, key2, value2, key3, value3)
      else this
    override def foreach[U](f: ((A, B)) =>  U): Unit = {
      f((key1, value1)); f((key2, value2)); f((key3, value3)); f((key4, value4))
    }
  }
}

<<<<<<< HEAD
=======
/** Explicit instantiation of the `Map` trait to reduce class file size in subclasses. */
private[scala] abstract class AbstractMap[A, +B] extends scala.collection.AbstractMap[A, B] with Map[A, B]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
