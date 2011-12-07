package scala.swing
package test

<<<<<<< HEAD
import scala.swing._ 
import scala.swing.event._ 
=======
import scala.swing._
import scala.swing.event._
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

object CountButton extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "My Frame"
    contents = new GridPanel(2, 2) {
      hGap = 3
      vGap = 3
      val button = new Button {
        text = "Press Me!"
      }
      contents += button
<<<<<<< HEAD
      val label = new Label { 
        text = "No button clicks registered" 
      }
      contents += label
      
      listenTo(button) 
      var nclicks = 0 
      reactions += { 
        case ButtonClicked(b) => 
          nclicks += 1 
          label.text = "Number of button clicks: "+nclicks 
=======
      val label = new Label {
        text = "No button clicks registered"
      }
      contents += label

      listenTo(button)
      var nclicks = 0
      reactions += {
        case ButtonClicked(b) =>
          nclicks += 1
          label.text = "Number of button clicks: "+nclicks
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }
}
