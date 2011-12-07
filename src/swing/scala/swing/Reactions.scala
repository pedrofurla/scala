/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event.Event
import scala.collection.mutable.{Buffer, ListBuffer}

object Reactions {
  import scala.ref._
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class Impl extends Reactions {
    private val parts: Buffer[Reaction] = new ListBuffer[Reaction]
    def isDefinedAt(e: Event) = parts.exists(_ isDefinedAt e)
    def += (r: Reaction): this.type = { parts += r; this }
    def -= (r: Reaction): this.type = { parts -= r; this }
    def apply(e: Event) {
      for (p <- parts) if (p isDefinedAt e) p(e)
    }
  }
<<<<<<< HEAD
  
  type Reaction = PartialFunction[Event, Unit]
  
=======

  type Reaction = PartialFunction[Event, Unit]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * A Reaction implementing this trait is strongly referenced in the reaction list
   */
  trait StronglyReferenced
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class Wrapper(listener: Any)(r: Reaction) extends Reaction with StronglyReferenced with Proxy {
    def self = listener
    def isDefinedAt(e: Event) = r.isDefinedAt(e)
    def apply(e: Event) { r(e) }
  }
}

/**
 * Used by reactors to let clients register custom event reactions.
 */
abstract class Reactions extends Reactions.Reaction {
  /**
   * Add a reaction.
   */
  def += (r: Reactions.Reaction): this.type

  /**
   * Remove the given reaction.
   */
  def -= (r: Reactions.Reaction): this.type
}
