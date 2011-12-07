package scala.swing
package test

import swing._
import event._

object CelsiusConverter2 extends SimpleSwingApplication {
<<<<<<< HEAD
  def newField = new TextField { 
=======
  def newField = new TextField {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    text = "0"
    columns = 5
    horizontalAlignment = Alignment.Right
  }
  val celsius = newField
  val fahrenheit = newField
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  listenTo(fahrenheit, celsius)
  reactions += {
    case EditDone(`fahrenheit`) =>
      val f = Integer.parseInt(fahrenheit.text)
      val c = (f - 32) * 5 / 9
      celsius.text = c.toString
    case EditDone(`celsius`) =>
      val c = Integer.parseInt(celsius.text)
      val f = c * 9 / 5 + 32
      fahrenheit.text = f.toString
  }
<<<<<<< HEAD
  
  lazy val ui = new FlowPanel(celsius, new Label(" Celsius  =  "), 
=======

  lazy val ui = new FlowPanel(celsius, new Label(" Celsius  =  "),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                              fahrenheit, new Label(" Fahrenheit")) {
    border = Swing.EmptyBorder(15, 10, 10, 10)
  }
  def top = new MainFrame {
    title = "Convert Celsius / Fahrenheit"
   	contents = ui
  }
}

