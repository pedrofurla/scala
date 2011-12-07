/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event._
import javax.swing._

/**
<<<<<<< HEAD
 * A two state button with a push button like user interface. 
 * Usually used in tool bars.
 * 
=======
 * A two state button with a push button like user interface.
 * Usually used in tool bars.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JToggleButton
 */
class ToggleButton(text0: String) extends AbstractButton {
  override lazy val peer: JToggleButton = new JToggleButton(text0) with SuperMixin
  def this() = this("")
}
