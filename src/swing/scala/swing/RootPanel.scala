/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

/**
<<<<<<< HEAD
 * The root of a component hierarchy. Contains at most one component. 
 *
 * @see javax.swing.RootPaneContainer
 */
trait RootPanel extends Container { 
  def peer: java.awt.Component with javax.swing.RootPaneContainer
  
  /**
   * At most one component.
   */
  def contents: Seq[Component] = 
=======
 * The root of a component hierarchy. Contains at most one component.
 *
 * @see javax.swing.RootPaneContainer
 */
trait RootPanel extends Container {
  def peer: java.awt.Component with javax.swing.RootPaneContainer

  /**
   * At most one component.
   */
  def contents: Seq[Component] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (peer.getContentPane.getComponentCount == 0) Nil
    else {
      val c = peer.getContentPane.getComponent(0).asInstanceOf[javax.swing.JComponent]
      List(UIElement.cachedWrapper[Component](c))
    }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def contents_=(c: Component) {
    if (peer.getContentPane.getComponentCount > 0) {
      val old = peer.getContentPane.getComponent(0)
      peer.getContentPane.remove(old)
    }
    peer.getContentPane.add(c.peer)
  }
}
