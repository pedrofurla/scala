/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.swing

import event._

import java.awt.Graphics
import java.awt.event._
import javax.swing.JComponent
import javax.swing.border.Border

/**
 * Utility methods, mostly for wrapping components.
 */
object Component {
  /**
   * Wraps a given Java Swing Component into a new wrapper.
   */
  def wrap(c: JComponent): Component = {
    val w = UIElement.cachedWrapper[Component](c)
<<<<<<< HEAD
    if (w != null) w 
=======
    if (w != null) w
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    else new Component { override lazy val peer = c }
  }
}

/**
 * Base class for all UI elements that can be displayed in a window.
<<<<<<< HEAD
 * Components are publishers that fire the following event classes: 
 * ComponentEvent, FocusEvent, FontChanged, ForegroundChanged, BackgroundChanged.
 * 
 * @note [Java Swing] Unlike in Java Swing, not all components are also containers.
 *
 * @see javax.swing.JComponent
 * @see http://java.sun.com/products/jfc/tsc/articles/painting/ for the component 
=======
 * Components are publishers that fire the following event classes:
 * ComponentEvent, FocusEvent, FontChanged, ForegroundChanged, BackgroundChanged.
 *
 * @note [Java Swing] Unlike in Java Swing, not all components are also containers.
 *
 * @see javax.swing.JComponent
 * @see http://java.sun.com/products/jfc/tsc/articles/painting/ for the component
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * painting mechanism
 */
abstract class Component extends UIElement {
  override lazy val peer: javax.swing.JComponent = new javax.swing.JComponent with SuperMixin {}
  var initP: JComponent = null
<<<<<<< HEAD
  
  /**
   * This trait is used to redirect certain calls from the peer to the wrapper 
=======

  /**
   * This trait is used to redirect certain calls from the peer to the wrapper
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * and back. Useful to expose methods that can be customized by overriding.
   */
  protected trait SuperMixin extends JComponent {
    override def paintComponent(g: Graphics) {
      Component.this.paintComponent(g.asInstanceOf[Graphics2D])
    }
    def __super__paintComponent(g: Graphics) {
      super.paintComponent(g)
    }
    override def paintBorder(g: Graphics) {
      Component.this.paintBorder(g.asInstanceOf[Graphics2D])
    }
    def __super__paintBorder(g: Graphics) {
      super.paintBorder(g)
    }
    override def paintChildren(g: Graphics) {
      Component.this.paintChildren(g.asInstanceOf[Graphics2D])
    }
    def __super__paintChildren(g: Graphics) {
      super.paintChildren(g)
    }
<<<<<<< HEAD
    
    override def paint(g: Graphics) {
      Component.this.paint(g.asInstanceOf[Graphics2D])
    }
    def __super__paint(g: Graphics) { 
      super.paint(g)
    }
  }
  
  def name: String = peer.getName
  def name_=(s: String) = peer.setName(s)
  
  /**
   * Used by certain layout managers, e.g., BoxLayout or OverlayLayout to 
=======

    override def paint(g: Graphics) {
      Component.this.paint(g.asInstanceOf[Graphics2D])
    }
    def __super__paint(g: Graphics) {
      super.paint(g)
    }
  }

  def name: String = peer.getName
  def name_=(s: String) = peer.setName(s)

  /**
   * Used by certain layout managers, e.g., BoxLayout or OverlayLayout to
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * align components relative to each other.
   */
  def xLayoutAlignment: Double = peer.getAlignmentX
  def xLayoutAlignment_=(x: Double) = peer.setAlignmentX(x.toFloat)
  def yLayoutAlignment: Double = peer.getAlignmentY
  def yLayoutAlignment_=(y: Double) = peer.setAlignmentY(y.toFloat)
<<<<<<< HEAD
  
  def border: Border = peer.getBorder
  def border_=(b: Border) { peer.setBorder(b) }
  
  def opaque: Boolean = peer.isOpaque
  def opaque_=(b: Boolean) = peer.setOpaque(b)
  
  def enabled: Boolean = peer.isEnabled
  def enabled_=(b: Boolean) = peer.setEnabled(b)
  
  def tooltip: String = peer.getToolTipText
  def tooltip_=(t: String) = peer.setToolTipText(t)
  
  def inputVerifier: Component => Boolean = { a =>
    Option(peer.getInputVerifier) forall (_ verify a.peer)
  }
  def inputVerifier_=(v: Component => Boolean) { 
=======

  def border: Border = peer.getBorder
  def border_=(b: Border) { peer.setBorder(b) }

  def opaque: Boolean = peer.isOpaque
  def opaque_=(b: Boolean) = peer.setOpaque(b)

  def enabled: Boolean = peer.isEnabled
  def enabled_=(b: Boolean) = peer.setEnabled(b)

  def tooltip: String = peer.getToolTipText
  def tooltip_=(t: String) = peer.setToolTipText(t)

  def inputVerifier: Component => Boolean = { a =>
    Option(peer.getInputVerifier) forall (_ verify a.peer)
  }
  def inputVerifier_=(v: Component => Boolean) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    peer.setInputVerifier(new javax.swing.InputVerifier {
      def verify(c: javax.swing.JComponent) = v(UIElement.cachedWrapper[Component](c))
    })
  }

  /*def verifyOnTraversal: (Component, Component) => Boolean = { a =>
    peer.getInputVerifier().verify(a.peer)
  }
<<<<<<< HEAD
  def verifyOnTraversal_=(v: (Component, Component) => Boolean) { 
=======
  def verifyOnTraversal_=(v: (Component, Component) => Boolean) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    peer.setInputVerifier(new javax.swing.InputVerifier {
      def verify(c: javax.swing.JComponent) = v(UIElement.cachedWrapper[Component](c))
    })
  }*/
<<<<<<< HEAD
  
  

  @deprecated("Use mouse instead", "2.8.0") lazy val Mouse = mouse
  
  /**
   * Contains publishers for various mouse events. They are separated for 
=======
  /**
   * Contains publishers for various mouse events. They are separated for
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * efficiency reasons.
   */
  object mouse {
    /**
     * Publishes clicks, presses and releases.
     */
<<<<<<< HEAD
    val clicks: Publisher = new Publisher {
      peer.addMouseListener(new MouseListener {
        def mouseEntered(e: java.awt.event.MouseEvent) { }
        def mouseExited(e: java.awt.event.MouseEvent) { }
        def mouseClicked(e: java.awt.event.MouseEvent) { 
          publish(new MouseClicked(e))
        }
        def mousePressed(e: java.awt.event.MouseEvent) { 
          publish(new MousePressed(e))
        }
        def mouseReleased(e: java.awt.event.MouseEvent) { 
          publish(new MouseReleased(e))
        }
      })
=======
    val clicks: Publisher = new LazyPublisher {
      lazy val l = new MouseListener {
        def mouseEntered(e: java.awt.event.MouseEvent) {}
        def mouseExited(e: java.awt.event.MouseEvent) {}
        def mouseClicked(e: java.awt.event.MouseEvent) {
          publish(new MouseClicked(e))
        }
        def mousePressed(e: java.awt.event.MouseEvent) {
          publish(new MousePressed(e))
        }
        def mouseReleased(e: java.awt.event.MouseEvent) {
          publish(new MouseReleased(e))
        }
      }

      def onFirstSubscribe() = peer.addMouseListener(l)
      def onLastUnsubscribe() = peer.removeMouseListener(l)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
    /**
     * Publishes enters, exits, moves, and drags.
     */
<<<<<<< HEAD
    val moves: Publisher = new Publisher {
      peer.addMouseListener(new MouseListener {
        def mouseEntered(e: java.awt.event.MouseEvent) { 
=======
    val moves: Publisher = new LazyPublisher {
      lazy val mouseListener = new MouseListener {
        def mouseEntered(e: java.awt.event.MouseEvent) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          publish(new MouseEntered(e))
        }
        def mouseExited(e: java.awt.event.MouseEvent) {
          publish(new MouseExited(e))
        }
        def mouseClicked(e: java.awt.event.MouseEvent) {}
<<<<<<< HEAD
        def mousePressed(e: java.awt.event.MouseEvent) { }
        def mouseReleased(e: java.awt.event.MouseEvent) { }
      })
      peer.addMouseMotionListener(new MouseMotionListener {
        def mouseMoved(e: java.awt.event.MouseEvent) { 
          publish(new MouseMoved(e))
        }
        def mouseDragged(e: java.awt.event.MouseEvent) { 
          publish(new MouseDragged(e))
        }
      })
=======
        def mousePressed(e: java.awt.event.MouseEvent) {}
        def mouseReleased(e: java.awt.event.MouseEvent) {}
      }

      lazy val mouseMotionListener = new MouseMotionListener {
        def mouseMoved(e: java.awt.event.MouseEvent) {
          publish(new MouseMoved(e))
        }
        def mouseDragged(e: java.awt.event.MouseEvent) {
          publish(new MouseDragged(e))
        }
      }
      def onFirstSubscribe() {
        peer.addMouseListener(mouseListener)
        peer.addMouseMotionListener(mouseMotionListener)
      }
      def onLastUnsubscribe() {
        peer.removeMouseListener(mouseListener)
        peer.removeMouseMotionListener(mouseMotionListener)
      }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
    /**
     * Publishes mouse wheel moves.
     */
    val wheel: Publisher = new LazyPublisher {
<<<<<<< HEAD
      // We need to subscribe lazily and unsubscribe, since components in scroll panes capture 
      // mouse wheel events if there is a listener installed. See ticket #1442.
      lazy val l = new MouseWheelListener {
          def mouseWheelMoved(e: java.awt.event.MouseWheelEvent) { 
            publish(new MouseWheelMoved(e)) }
        }
=======
      // We need to subscribe lazily and unsubscribe, since components in scroll panes capture
      // mouse wheel events if there is a listener installed. See ticket #1442.
      lazy val l = new MouseWheelListener {
        def mouseWheelMoved(e: java.awt.event.MouseWheelEvent) {
          publish(new MouseWheelMoved(e))
        }
      }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def onFirstSubscribe() = peer.addMouseWheelListener(l)
      def onLastUnsubscribe() = peer.removeMouseWheelListener(l)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  object keys extends Publisher {
    peer.addKeyListener(new KeyListener {
      def keyPressed(e: java.awt.event.KeyEvent) { publish(new KeyPressed(e)) }
      def keyReleased(e: java.awt.event.KeyEvent) { publish(new KeyReleased(e)) }
      def keyTyped(e: java.awt.event.KeyEvent) { publish(new KeyTyped(e)) }
    })
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def focusable: Boolean = peer.isFocusable
  def focusable_=(b: Boolean) = peer.setFocusable(b)
  def requestFocus() = peer.requestFocus()
  def requestFocusInWindow() = peer.requestFocusInWindow()
  def hasFocus: Boolean = peer.isFocusOwner
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected override def onFirstSubscribe() {
    super.onFirstSubscribe
    // TODO: deprecated, remove after 2.8
    peer.addComponentListener(new java.awt.event.ComponentListener {
<<<<<<< HEAD
      def componentHidden(e: java.awt.event.ComponentEvent) { 
        publish(UIElementHidden(Component.this)) 
      }
      def componentShown(e: java.awt.event.ComponentEvent) { 
        publish(UIElementShown(Component.this)) 
      }
      def componentMoved(e: java.awt.event.ComponentEvent) { 
        publish(UIElementMoved(Component.this)) 
      }
      def componentResized(e: java.awt.event.ComponentEvent) { 
        publish(UIElementResized(Component.this)) 
=======
      def componentHidden(e: java.awt.event.ComponentEvent) {
        publish(UIElementHidden(Component.this))
      }
      def componentShown(e: java.awt.event.ComponentEvent) {
        publish(UIElementShown(Component.this))
      }
      def componentMoved(e: java.awt.event.ComponentEvent) {
        publish(UIElementMoved(Component.this))
      }
      def componentResized(e: java.awt.event.ComponentEvent) {
        publish(UIElementResized(Component.this))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    })

    peer.addFocusListener(new java.awt.event.FocusListener {
      def other(e: java.awt.event.FocusEvent) = e.getOppositeComponent match {
        case c: JComponent => Some(UIElement.cachedWrapper[Component](c))
        case _ => None
      }
<<<<<<< HEAD
    
      def focusGained(e: java.awt.event.FocusEvent) { 
        publish(FocusGained(Component.this, other(e), e.isTemporary)) 
      }
      def focusLost(e: java.awt.event.FocusEvent) {
        publish(FocusLost(Component.this, other(e), e.isTemporary)) 
      }
    })
  
    peer.addPropertyChangeListener(new java.beans.PropertyChangeListener {
      def propertyChange(e: java.beans.PropertyChangeEvent) { 
=======

      def focusGained(e: java.awt.event.FocusEvent) {
        publish(FocusGained(Component.this, other(e), e.isTemporary))
      }
      def focusLost(e: java.awt.event.FocusEvent) {
        publish(FocusLost(Component.this, other(e), e.isTemporary))
      }
    })

    peer.addPropertyChangeListener(new java.beans.PropertyChangeListener {
      def propertyChange(e: java.beans.PropertyChangeEvent) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        e.getPropertyName match {
          case "font" => publish(FontChanged(Component.this))
          case "background" => publish(BackgroundChanged(Component.this))
          case "foreground" => publish(ForegroundChanged(Component.this))
          case _ =>
          /*case "focusable" =>
          case "focusTraversalKeysEnabled" =>
          case "forwardFocusTraversalKeys" =>
          case "backwardFocusTraversalKeys" =>
          case "upCycleFocusTraversalKeys" =>
          case "downCycleFocusTraversalKeys" =>
          case "focusTraversalPolicy" =>
          case "focusCycleRoot" =>*/
        }
      }
    })
  }
<<<<<<< HEAD
  
  def revalidate() { peer.revalidate() }
  
=======

  def revalidate() { peer.revalidate() }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * For custom painting, users should usually override this method.
   */
  protected def paintComponent(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paintComponent(g)
<<<<<<< HEAD
      case _ => 
    }
  }
  
  protected def paintBorder(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paintBorder(g)
      case _ => 
    }
  }
  
  protected def paintChildren(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paintChildren(g)
      case _ => 
    }
  }
  
=======
      case _ =>
    }
  }

  protected def paintBorder(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paintBorder(g)
      case _ =>
    }
  }

  protected def paintChildren(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paintChildren(g)
      case _ =>
    }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def paint(g: Graphics2D) {
    peer match {
      case peer: SuperMixin => peer.__super__paint(g)
      case _ => peer.paint(g)
    }
  }
<<<<<<< HEAD
   
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toString = "scala.swing wrapper " + peer.toString
}
