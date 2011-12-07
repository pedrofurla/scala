package scala.swing
package test

import swing._
import swing.event._

object Dialogs extends SimpleSwingApplication {
  import TabbedPane._
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val label = new Label("No Result yet")
  lazy val tabs = new TabbedPane {
    pages += new Page("File", new GridBagPanel { grid =>
      import GridBagPanel._
      val buttonText = new TextField("Click Me")
<<<<<<< HEAD
      
      val c = new Constraints
      c.fill = Fill.Horizontal
      c.grid = (1,1)
    
      val chooser = new FileChooser
      layout(new Button(Action("Open") {
        chooser.showOpenDialog(grid)                   
      })) = c
      
      c.grid = (1,2)
      layout(new Button(Action("Save") {
        chooser.showSaveDialog(grid)                 
      })) = c
      
      c.grid = (1,3)
      layout(new Button(Action("Custom") { 
        chooser.showDialog(grid, buttonText.text)
      })) = c
      
      c.grid = (2,3)
      layout(new Label("  with Text  ")) = c
      
      c.grid = (3,3)
      c.ipadx = 50
      layout(buttonText) = c
      
=======

      val c = new Constraints
      c.fill = Fill.Horizontal
      c.grid = (1,1)

      val chooser = new FileChooser
      layout(new Button(Action("Open") {
        chooser.showOpenDialog(grid)
      })) = c

      c.grid = (1,2)
      layout(new Button(Action("Save") {
        chooser.showSaveDialog(grid)
      })) = c

      c.grid = (1,3)
      layout(new Button(Action("Custom") {
        chooser.showDialog(grid, buttonText.text)
      })) = c

      c.grid = (2,3)
      layout(new Label("  with Text  ")) = c

      c.grid = (3,3)
      c.ipadx = 50
      layout(buttonText) = c

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      border = Swing.EmptyBorder(5, 5, 5, 5)
    })
    pages += new Page("Simple Modal Dialogs", new BorderPanel {
      import BorderPanel._
      val mutex = new ButtonGroup
      val ok = new RadioButton("OK (in the L&F's words)")
      val ynlf = new RadioButton("Yes/No (in the L&F's words)")
      val ynp = new RadioButton("Yes/No (in the programmer's words)")
      val yncp = new RadioButton("Yes/No/Cancel (in the programmer's words)")
      val radios = List(ok, ynlf, ynp, yncp)
<<<<<<< HEAD
      mutex.buttons ++= radios 
      mutex.select(ok)      
=======
      mutex.buttons ++= radios
      mutex.select(ok)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val buttons = new BoxPanel(Orientation.Vertical) {
        contents ++= radios
      }
      layout(buttons) = Position.North
      layout(new Button(Action("Show It!") {
        import Dialog._
        mutex.selected.get match {
<<<<<<< HEAD
          case `ok` => 
            showMessage(buttons, "Eggs aren't supposed to be green.")
          case `ynlf` => 
            label.text = showConfirmation(buttons, 
=======
          case `ok` =>
            showMessage(buttons, "Eggs aren't supposed to be green.")
          case `ynlf` =>
            label.text = showConfirmation(buttons,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                             "Would you like green eggs and ham?",
                             "An Inane Question") match {
              case Result.Yes => "Ewww!"
              case Result.No => "Me neither!"
              case _ => "Come on -- tell me!"
          }
<<<<<<< HEAD
          case `ynp` => 
=======
          case `ynp` =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val options = List("Yes, please",
                               "No, thanks",
                               "No eggs, no ham!")
            label.text = showOptions(buttons,
                        "Would you like some green eggs to go with that ham?",
                        "A Silly Question",
<<<<<<< HEAD
                        entries = options, 
=======
                        entries = options,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                        initial = 2) match {
              case Result.Yes => "You're kidding!"
              case Result.No => "I don't like them, either."
              case _ => "Come on -- 'fess up!"
            }
<<<<<<< HEAD
          case `yncp` => 
=======
          case `yncp` =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val options = List("Yes, please",
                               "No, thanks",
                               "No eggs, no ham!")
            label.text = showOptions(buttons,
                        message = "Would you like some green eggs to go with that ham?",
                        title = "A Silly Question",
<<<<<<< HEAD
                        entries = options, 
=======
                        entries = options,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                        initial = 2) match {
              case Result.Yes => "Here you go: green eggs and ham!"
              case Result.No => "OK, just the ham, then."
              case Result.Cancel => "Well, I'm certainly not going to eat them!"
              case _ => "Please tell me what you want!"
            }
<<<<<<< HEAD
        }            
=======
        }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      })) = Position.South
    })
    pages += new Page("More Dialogs", new BorderPanel {
      import BorderPanel._
      val mutex = new ButtonGroup
      val pick = new RadioButton("Pick one of several choices")
      val enter = new RadioButton("Enter some text")
      val custom = new RadioButton("Custom")
      val customUndec = new RadioButton("Custom undecorated")
      val custom2 = new RadioButton("2 custom dialogs")
      val radios = List(pick, enter, custom, customUndec, custom2)
      mutex.buttons ++= radios
<<<<<<< HEAD
      mutex.select(pick)      
=======
      mutex.select(pick)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val buttons = new BoxPanel(Orientation.Vertical) {
        contents ++= radios
      }
      layout(buttons) = Position.North
      layout(new Button(Action("Show It!") {
        import Dialog._
        mutex.selected.get match {
<<<<<<< HEAD
          case `pick` => 
=======
          case `pick` =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val possibilities = List("ham", "spam", "yam")
            val s = showInput(buttons,
                      "Complete the sentence:\n\"Green eggs and...\"",
                      "Customized Dialog",
<<<<<<< HEAD
                      Message.Plain, 
=======
                      Message.Plain,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                      Swing.EmptyIcon,
                      possibilities, "ham")

            //If a string was returned, say so.
            label.text = if ((s != None) && (s.get.length > 0))
              "Green eggs and... " + s.get + "!"
            else
              "Come on, finish the sentence!"
<<<<<<< HEAD
          case `enter` => 
            val s = showInput(buttons,
                      "Complete the sentence:\n\"Green eggs and...\"",
                      "Customized Dialog",
                      Message.Plain, 
=======
          case `enter` =>
            val s = showInput(buttons,
                      "Complete the sentence:\n\"Green eggs and...\"",
                      "Customized Dialog",
                      Message.Plain,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                      Swing.EmptyIcon,
                      Nil, "ham")

            //If a string was returned, say so.
            label.text = if ((s != None) && (s.get.length > 0))
              "Green eggs and... " + s.get + "!"
            else
              "Come on, finish the sentence!"
          case `custom` =>
            val dialog = new Dialog(top)
            dialog.open()
            dialog.contents = Button("Close Me!") { dialog.close() }
          case `customUndec` =>
            val dialog = new Dialog with RichWindow.Undecorated
            dialog.open()
            dialog.contents = Button("Close Me!") { dialog.close() }
          case `custom2` =>
            val d1 = new Dialog
            val d2 = new Dialog(d1)
            d1.open()
            d2.open()
            d1.contents = Button("Close Me! I am the owner and will automatically close the other one") { d1.close() }
            d2.contents = Button("Close Me!") { d2.close() }
<<<<<<< HEAD
        }            
      })) = Position.South
    })
  }
  
=======
        }
      })) = Position.South
    })
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val ui: Panel = new BorderPanel {
    layout(tabs) = BorderPanel.Position.Center
    layout(label) = BorderPanel.Position.South
  }
<<<<<<< HEAD
   
  
  lazy val top = new MainFrame { 
=======


  lazy val top = new MainFrame {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    title = "Dialog Demo"
    contents = ui
  }
}

