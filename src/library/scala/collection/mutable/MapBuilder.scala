/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package mutable

/** The canonical builder for immutable maps, working with the map's `+` method
<<<<<<< HEAD
 *  to add new elements. 
 *  Collections are built from their `empty` element using this + method.
 *  
=======
 *  to add new elements.
 *  Collections are built from their `empty` element using this + method.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @tparam A      Type of the keys for the map this builder creates.
 *  @tparam B      Type of the values for the map this builder creates.
 *  @tparam Coll   The type of the actual collection this builder builds.
 *  @param empty   The empty element of the collection.
 *
 *  @since 2.8
 */
<<<<<<< HEAD
class MapBuilder[A, B, Coll <: scala.collection.GenMap[A, B] with scala.collection.GenMapLike[A, B, Coll]](empty: Coll) 
extends Builder[(A, B), Coll] {
  protected var elems: Coll = empty
  def +=(x: (A, B)): this.type = { 
=======
class MapBuilder[A, B, Coll <: scala.collection.GenMap[A, B] with scala.collection.GenMapLike[A, B, Coll]](empty: Coll)
extends Builder[(A, B), Coll] {
  protected var elems: Coll = empty
  def +=(x: (A, B)): this.type = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    elems = (elems + x).asInstanceOf[Coll]
      // the cast is necessary because right now we cannot enforce statically that
      // for every map of type Coll, `+` yields again a Coll. With better support
      // for hk-types we might be able to enforce this in the future, though.
<<<<<<< HEAD
    this 
=======
    this
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
  def clear() { elems = empty }
  def result: Coll = elems
}
