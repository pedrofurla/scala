package scala.swing
package test
<<<<<<< HEAD
import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleGUIApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}

/**
 * Dragging the mouse draws a simple graph
 * 
=======

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel}
import scala.swing.event._
import java.awt.{Color, Graphics2D, Point, geom}

/**
 * Dragging the mouse draws a simple graph
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @author Frank Teubler, Ingo Maier
 */
object LinePainting extends SimpleSwingApplication {
  lazy val ui = new Panel {
    background = Color.white
    preferredSize = (200,200)

    focusable = true
    listenTo(mouse.clicks, mouse.moves, keys)
<<<<<<< HEAD
    
    reactions += {
      case e: MousePressed  => 
=======

    reactions += {
      case e: MousePressed  =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        moveTo(e.point)
        requestFocusInWindow()
      case e: MouseDragged  => lineTo(e.point)
      case e: MouseReleased => lineTo(e.point)
<<<<<<< HEAD
      case KeyTyped(_,'c',_,_) => 
=======
      case KeyTyped(_,'c',_,_) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        path = new geom.GeneralPath
        repaint()
      case _: FocusLost => repaint()
    }

    /* records the dragging */
    var path = new geom.GeneralPath

    def lineTo(p: Point) { path.lineTo(p.x, p.y); repaint() }
    def moveTo(p: Point) { path.moveTo(p.x, p.y); repaint() }

    override def paintComponent(g: Graphics2D) = {
      super.paintComponent(g)
      g.setColor(new Color(100,100,100))
<<<<<<< HEAD
      g.drawString("Press left mouse button and drag to paint." + 
=======
      g.drawString("Press left mouse button and drag to paint." +
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                   (if(hasFocus) " Press 'c' to clear." else ""), 10, size.height-10)
      g.setColor(Color.black)
      g.draw(path)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def top = new MainFrame {
    title = "Simple Line Painting Demo"
    contents = ui
  }
}
