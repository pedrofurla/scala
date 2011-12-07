package scala.swing
package test

import swing._
import event._

/** A GUI app to convert celsius to centigrade
<<<<<<< HEAD
 */    
=======
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
object CelsiusConverter extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Convert Celsius to Fahrenheit"
    val tempCelsius = new TextField
    val celsiusLabel = new Label {
      text = "Celsius"
      border = Swing.EmptyBorder(5, 5, 5, 5)
    }
    val convertButton = new Button {
      text = "Convert"//new javax.swing.ImageIcon("c:\\workspace\\gui\\images\\convert.gif")
      //border = Border.Empty(5, 5, 5, 5)
    }
    val fahrenheitLabel = new Label {
      text = "Fahrenheit     "
      border = Swing.EmptyBorder(5, 5, 5, 5)
      listenTo(convertButton, tempCelsius)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def convert() {
        val c = Integer.parseInt(tempCelsius.text)
        val f = c * 9 / 5 + 32
        text = "<html><font color = red>"+f+"</font> Fahrenheit</html>"
      }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      reactions += {
        case ButtonClicked(_) | EditDone(_) => convert()
      }
    }
    contents = new GridPanel(2,2) {
      contents.append(tempCelsius, celsiusLabel, convertButton, fahrenheitLabel)
      border = Swing.EmptyBorder(10, 10, 10, 10)
    }
    //defaultButton = Some(convertButton)
  }
}

