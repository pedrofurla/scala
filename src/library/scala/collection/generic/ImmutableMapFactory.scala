/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
package generic

/** A template for companion objects of `immutable.Map` and subclasses thereof.
 *    @author Martin Odersky
 *    @version 2.8
 *    @since 2.8
 */
<<<<<<< HEAD
abstract class ImmutableMapFactory[CC[A, +B] <: immutable.Map[A, B] with immutable.MapLike[A, B, CC[A, B]]] extends MapFactory[CC] 
=======
abstract class ImmutableMapFactory[CC[A, +B] <: immutable.Map[A, B] with immutable.MapLike[A, B, CC[A, B]]] extends MapFactory[CC]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
