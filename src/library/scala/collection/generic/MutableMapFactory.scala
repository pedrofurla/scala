/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package generic

import mutable.Builder

/** A template for companion objects of `mutable.Map` and subclasses thereof.
 *    @author Martin Odersky
 *    @version 2.8
 *    @since 2.8
 */
<<<<<<< HEAD
abstract class MutableMapFactory[CC[A, B] <: mutable.Map[A, B] with mutable.MapLike[A, B, CC[A, B]]] 
=======
abstract class MutableMapFactory[CC[A, B] <: mutable.Map[A, B] with mutable.MapLike[A, B, CC[A, B]]]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  extends MapFactory[CC] {

  /** The default builder for $Coll objects.
   *  @tparam A      the type of the keys
   *  @tparam B      the type of the associated values
   */
  override def newBuilder[A, B]: Builder[(A, B), CC[A, B]] = empty[A, B]
}
