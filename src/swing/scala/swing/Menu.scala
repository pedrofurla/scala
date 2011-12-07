/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.swing

import scala.collection.mutable
import javax.swing._

object MenuBar {
  case object NoMenuBar extends MenuBar
}

/**
 * A menu bar. Each window can contain at most one. Contains a number of menus.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JMenuBar
 */
class MenuBar extends Component with SequentialContainer.Wrapper {
  override lazy val peer: JMenuBar = new JMenuBar with SuperMixin
<<<<<<< HEAD
  
  def menus: mutable.Seq[Menu] = contents.filter(_.isInstanceOf[Menu]).map(_.asInstanceOf[Menu])
  
=======

  def menus: mutable.Seq[Menu] = contents.filter(_.isInstanceOf[Menu]).map(_.asInstanceOf[Menu])

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Not implemented by Swing
  //def helpMenu: Menu = UIElement.cachedWrapper(peer.getHelpMenu)
  //def helpMenu_=(m: Menu) { peer.setHelpMenu(m.peer) }
}

/**
 * A menu item that can be used in a menu.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JMenuItem
 */
class MenuItem(title0: String) extends AbstractButton {
  override lazy val peer: JMenuItem = new JMenuItem(title0)
  def this(a: Action) = {
    this("")
    action = a
  }
}

/**
 * A menu. Contains menu items. Being a menu item itself, menus can be nested.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JMenu
 */
class Menu(title0: String) extends MenuItem(title0) with SequentialContainer.Wrapper { self: Menu =>
  override lazy val peer: JMenu = new JMenu(title0)
}

/**
 * A menu item with a radio button.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JRadioButtonMenuItem
 */
class RadioMenuItem(title0: String) extends MenuItem(title0) {
  override lazy val peer: JRadioButtonMenuItem = new JRadioButtonMenuItem(title0)
}
/**
 * A menu item with a check box.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JCheckBoxMenuItem
 */
class CheckMenuItem(title0: String) extends MenuItem(title0) {
  override lazy val peer: JCheckBoxMenuItem = new JCheckBoxMenuItem(title0)
}
