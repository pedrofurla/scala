<<<<<<< HEAD
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

package scala.collection
package mutable

/** A template trait for mutable maps that allow concurrent access.
<<<<<<< HEAD
 *  
 *  $concurrentmapinfo
 *  
 *  @since 2.8
 * 
 *  @tparam A  the key type of the map
 *  @tparam B  the value type of the map
 *  
=======
 *
 *  $concurrentmapinfo
 *
 *  @since 2.8
 *  @see [[http://docs.scala-lang.org/overviews/collections/concrete-mutable-collection-classes.html#concurrent_maps "Scala's Collection Library overview"]]
 *  section on `Concurrent Maps` for more information.
 *
 *  @tparam A  the key type of the map
 *  @tparam B  the value type of the map
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define Coll ConcurrentMap
 *  @define coll concurrent map
 *  @define concurrentmapinfo
 *  This is a base trait for all Scala concurrent map implementations. It
 *  provides all of the methods a `Map` does, with the difference that all the
 *  changes are atomic. It also describes methods specific to concurrent maps.
 *
 *  '''Note''': The concurrent maps do not accept `'''null'''` for keys or values.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @define atomicop
 *  This is an atomic operation.
 */
trait ConcurrentMap[A, B] extends Map[A, B] {

  /**
   * Associates the given key with a given value, unless the key was already
   * associated with some other value.
<<<<<<< HEAD
   * 
   * $atomicop
   * 
=======
   *
   * $atomicop
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param k   key with which the specified value is to be associated with
   * @param v   value to be associated with the specified key
   * @return    `Some(oldvalue)` if there was a value `oldvalue` previously
   *            associated with the specified key, or `None` if there was no
   *            mapping for the specified key
   */
  def putIfAbsent(k: A, v: B): Option[B]

  /**
   * Removes the entry for the specified key if its currently mapped to the
   * specified value.
<<<<<<< HEAD
   * 
   * $atomicop
   * 
=======
   *
   * $atomicop
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param k   key for which the entry should be removed
   * @param v   value expected to be associated with the specified key if
   *            the removal is to take place
   * @return    `true` if the removal took place, `false` otherwise
   */
  def remove(k: A, v: B): Boolean

  /**
   * Replaces the entry for the given key only if it was previously mapped to
   * a given value.
<<<<<<< HEAD
   * 
   * $atomicop
   * 
=======
   *
   * $atomicop
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param k         key for which the entry should be replaced
   * @param oldvalue  value expected to be associated with the specified key
   *                  if replacing is to happen
   * @param newvalue  value to be associated with the specified key
   * @return          `true` if the entry was replaced, `false` otherwise
   */
  def replace(k: A, oldvalue: B, newvalue: B): Boolean

  /**
   * Replaces the entry for the given key only if it was previously mapped
   * to some value.
<<<<<<< HEAD
   * 
   * $atomicop
   * 
=======
   *
   * $atomicop
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param k   key for which the entry should be replaced
   * @param v   value to be associated with the specified key
   * @return    `Some(v)` if the given key was previously mapped to some value `v`, or `None` otherwise
   */
<<<<<<< HEAD
  def replace(k: A, v: B): Option[B]  
=======
  def replace(k: A, v: B): Option[B]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
