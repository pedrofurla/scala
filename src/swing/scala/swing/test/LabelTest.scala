package scala.swing
package test

import scala.swing._
import scala.swing.event._

object LabelTest extends SimpleSwingApplication {
  def top = new MainFrame{
<<<<<<< HEAD
    contents = new Label { 
=======
    contents = new Label {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      text = "Hello"
      import java.awt.event._
      listenTo(mouse.clicks)
      reactions += {
<<<<<<< HEAD
        case MousePressed(_,_,_,_,_) => 
          println("Mouse pressed2") 
=======
        case MousePressed(_,_,_,_,_) =>
          println("Mouse pressed2")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }
}

