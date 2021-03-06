/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package mutable

import script._

/** This class is typically used as a mixin. It adds a subscription
 *  mechanism to the `Set` class into which this abstract
 *  class is mixed in. Class `ObservableSet` publishes
 *  events of the type `Message`.
 *
 *  @author  Matthias Zenger
 *  @version 1.0, 08/07/2003
 *  @since   1
 */
trait ObservableSet[A] extends Set[A] with Publisher[Message[A] with Undoable]
<<<<<<< HEAD
{ 
=======
{
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  type Pub <: ObservableSet[A]

  abstract override def +=(elem: A): this.type = {
    if (!contains(elem)) {
      super.+=(elem)
      publish(new Include(elem) with Undoable { def undo = -=(elem) })
    }
    this
  }

  abstract override def -=(elem: A): this.type = {
    if (contains(elem)) {
      super.-=(elem)
      publish(new Remove(elem) with Undoable { def undo = +=(elem) })
    }
    this
  }

  abstract override def clear(): Unit = {
    super.clear
<<<<<<< HEAD
    publish(new Reset with Undoable { 
      def undo(): Unit = throw new UnsupportedOperationException("cannot undo") 
=======
    publish(new Reset with Undoable {
      def undo(): Unit = throw new UnsupportedOperationException("cannot undo")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    })
  }
}
