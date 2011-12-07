/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event._
import javax.swing._
import javax.swing.text._
import javax.swing.event._

object TextComponent {
<<<<<<< HEAD
  trait HasColumns extends TextComponent { 
=======
  trait HasColumns extends TextComponent {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def columns: Int
    def columns_=(n: Int)
  }
  trait HasRows extends TextComponent {
    def rows: Int
    def rows_=(n: Int)
  }
}

/**
 * A component that allows some kind of text input and display.
 *
 * @see javax.swing.JTextComponent
 */
class TextComponent extends Component with Publisher {
  override lazy val peer: JTextComponent = new JTextComponent with SuperMixin {}
  def text: String = peer.getText
  def text_=(t: String) = peer.setText(t)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class Caret extends Publisher {
    def dot: Int = peer.getCaret.getDot
    def dot_=(n: Int) { peer.getCaret.setDot(n) }
    def mark: Int = peer.getCaret.getMark
    def moveDot(n: Int) { peer.getCaret.moveDot(n) }
    def visible: Boolean = peer.getCaret.isVisible
    def visible_=(b: Boolean) { peer.getCaret.setVisible(b) }
    def selectionVisible: Boolean = peer.getCaret.isSelectionVisible
    def selectionVisible_=(b: Boolean) { peer.getCaret.setSelectionVisible(b) }
    def blinkRate: Int = peer.getCaret.getBlinkRate
    def blinkRate_=(n: Int) { peer.getCaret.setBlinkRate(n) }
    def color: Color = peer.getCaretColor
    def color_=(c: Color) = peer.setCaretColor(c)
    def position: Int = peer.getCaretPosition
    def position_=(p: Int) = peer.setCaretPosition(p)
<<<<<<< HEAD
    
    peer.addCaretListener {
      new CaretListener {
        def caretUpdate(e: CaretEvent) { publish(CaretUpdate(TextComponent.this)) } 
      }
    }
  }
  
=======

    peer.addCaretListener {
      new CaretListener {
        def caretUpdate(e: CaretEvent) { publish(CaretUpdate(TextComponent.this)) }
      }
    }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  object caret extends Caret

  def editable: Boolean = peer.isEditable
  def editable_=(x: Boolean) = peer.setEditable(x)
  def cut() { peer.cut() }
  def copy() { peer.copy() }
  def paste() { peer.paste() }
  def selected: String = peer.getSelectedText
<<<<<<< HEAD
  
  def selectAll() { peer.selectAll() }
  
=======

  def selectAll() { peer.selectAll() }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  peer.getDocument.addDocumentListener(new DocumentListener {
    def changedUpdate(e:DocumentEvent) { publish(new ValueChanged(TextComponent.this)) }
    def insertUpdate(e:DocumentEvent) { publish(new ValueChanged(TextComponent.this)) }
    def removeUpdate(e:DocumentEvent) { publish(new ValueChanged(TextComponent.this)) }
  })
}
