/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools.nsc
package interpreter

/** Files having to do with the state of a repl session:
 *  lines of text entered, types and terms defined, etc.
 */
package object session {
  type JIterator[T]       = java.util.Iterator[T]
  type JListIterator[T]   = java.util.ListIterator[T]

  type JEntry             = scala.tools.jline.console.history.History.Entry
  type JHistory           = scala.tools.jline.console.history.History
  type JMemoryHistory     = scala.tools.jline.console.history.MemoryHistory
  type JPersistentHistory = scala.tools.jline.console.history.PersistentHistory
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private[interpreter] implicit def charSequenceFix(x: CharSequence): String = x.toString
}
