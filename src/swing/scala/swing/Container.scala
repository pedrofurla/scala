/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event._
import scala.collection.mutable.Buffer

object Container {
  /**
<<<<<<< HEAD
   * Utility trait for wrapping containers. Provides an immutable 
=======
   * Utility trait for wrapping containers. Provides an immutable
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * implementation of the contents member.
   */
  trait Wrapper extends Container with Publisher {
    override def peer: javax.swing.JComponent
<<<<<<< HEAD
    
    protected val _contents = new Content
    def contents: Seq[Component] = _contents
  
    protected class Content extends BufferWrapper[Component] {
      override def clear() { peer.removeAll() }
      override def remove(n: Int): Component = { 
=======

    protected val _contents = new Content
    def contents: Seq[Component] = _contents

    protected class Content extends BufferWrapper[Component] {
      override def clear() { peer.removeAll() }
      override def remove(n: Int): Component = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val c = peer.getComponent(n)
        peer.remove(n)
        UIElement.cachedWrapper[Component](c)
      }
      protected def insertAt(n: Int, c: Component) { peer.add(c.peer, n) }
      def +=(c: Component): this.type = { peer.add(c.peer) ; this }
      def length = peer.getComponentCount
      def apply(n: Int) = UIElement.cachedWrapper[Component](peer.getComponent(n))
    }
<<<<<<< HEAD
  
    peer.addContainerListener(new java.awt.event.ContainerListener {
      def componentAdded(e: java.awt.event.ContainerEvent) { 
        publish(ComponentAdded(Wrapper.this, 
          UIElement.cachedWrapper[Component](e.getChild.asInstanceOf[javax.swing.JComponent]))) 
      }
      def componentRemoved(e: java.awt.event.ContainerEvent) { 
        publish(ComponentRemoved(Wrapper.this, 
          UIElement.cachedWrapper[Component](e.getChild.asInstanceOf[javax.swing.JComponent]))) 
=======

    peer.addContainerListener(new java.awt.event.ContainerListener {
      def componentAdded(e: java.awt.event.ContainerEvent) {
        publish(ComponentAdded(Wrapper.this,
          UIElement.cachedWrapper[Component](e.getChild.asInstanceOf[javax.swing.JComponent])))
      }
      def componentRemoved(e: java.awt.event.ContainerEvent) {
        publish(ComponentRemoved(Wrapper.this,
          UIElement.cachedWrapper[Component](e.getChild.asInstanceOf[javax.swing.JComponent])))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    })
  }
}

/**
 * The base traits for UI elements that can contain <code>Component</code>s.
<<<<<<< HEAD
 * 
 * @note [Java Swing] This is not the wrapper for java.awt.Container but a trait 
=======
 *
 * @note [Java Swing] This is not the wrapper for java.awt.Container but a trait
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * that extracts a common interface for components, menus, and windows.
 */
trait Container extends UIElement {
  /**
   * The child components of this container.
   */
  def contents: Seq[Component]
}
