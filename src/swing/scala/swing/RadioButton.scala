/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import javax.swing._

/**
<<<<<<< HEAD
 * A two state button that is usually used in a <code>ButtonGroup</code> 
 * together with other <code>RadioButton</code>s, in order to indicate 
=======
 * A two state button that is usually used in a <code>ButtonGroup</code>
 * together with other <code>RadioButton</code>s, in order to indicate
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * that at most one of them can be selected.
 *
 * @see javax.swing.JRadioButton
 */
class RadioButton(text0: String) extends ToggleButton {
  override lazy val peer: JRadioButton = new JRadioButton(text0) with SuperMixin
  def this() = this("")
}
