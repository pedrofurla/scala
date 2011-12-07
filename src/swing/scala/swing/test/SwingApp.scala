package scala.swing
package test

import swing._
import swing.event._

object SwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "SwingApp"
    var numclicks = 0
    object label extends Label {
      val prefix = "Number of button clicks: "
<<<<<<< HEAD
      text = prefix + "0  " 
=======
      text = prefix + "0  "
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      listenTo(button)
      reactions += {
        case ButtonClicked(button) =>
          numclicks = numclicks + 1
          text = prefix + numclicks
      }
    }
    object button extends Button {
      text = "I am a button"
    }
    contents = new FlowPanel {
      contents.append(button, label)
      border = Swing.EmptyBorder(5, 5, 5, 5)
    }
  }
}

