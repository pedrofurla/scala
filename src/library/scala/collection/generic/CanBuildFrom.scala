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
import scala.annotation.implicitNotFound

<<<<<<< HEAD
/** A base trait for builder factories.      
=======
/** A base trait for builder factories.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 *  @tparam From  the type of the underlying collection that requests
 *                a builder to be created.
 *  @tparam Elem  the element type of the collection to be created.
 *  @tparam To    the type of the collection to be created.
 *
<<<<<<< HEAD
 *  @see Builder 
=======
 *  @see Builder
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Martin Odersky
 *  @author Adriaan Moors
 *  @since 2.8
 */
@implicitNotFound(msg = "Cannot construct a collection of type ${To} with elements of type ${Elem} based on a collection of type ${From}.")
trait CanBuildFrom[-From, -Elem, +To] {

  /** Creates a new builder on request of a collection.
   *  @param from  the collection requesting the builder to be created.
   *  @return a builder for collections of type `To` with element type `Elem`.
   *          The collections framework usually arranges things so
   *          that the created builder will build the same kind of collection
   *          as `from`.
   */
  def apply(from: From): Builder[Elem, To]

  /** Creates a new builder from scratch.
   *
   *  @return a builder for collections of type `To` with element type `Elem`.
   *  @see scala.collection.breakOut
   */
  def apply(): Builder[Elem, To]
}
