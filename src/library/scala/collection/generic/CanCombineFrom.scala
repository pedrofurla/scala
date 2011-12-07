/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
<<<<<<< HEAD
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
=======
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package generic

import scala.collection.parallel._

<<<<<<< HEAD
/**
 * A base trait for parallel builder factories.
 * 
 * @tparam From   the type of the underlying collection that requests a builder to be created
 * @tparam Elem   the element type of the collection to be created
 * @tparam To     the type of the collection to be created
=======
/** A base trait for parallel builder factories.
 *
 *  @tparam From  the type of the underlying collection that requests a
 *                builder to be created.
 *  @tparam Elem  the element type of the collection to be created.
 *  @tparam To    the type of the collection to be created.
 *  @since 2.8
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait CanCombineFrom[-From, -Elem, +To] extends CanBuildFrom[From, Elem, To] with Parallel {
  def apply(from: From): Combiner[Elem, To]
  def apply(): Combiner[Elem, To]
}

<<<<<<< HEAD







=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
