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
import java.awt.event._

/**
 * A text component that allows multiline text input and display.
 *
 * @see javax.swing.JTextArea
 */
<<<<<<< HEAD
class TextArea(text0: String, rows0: Int, columns0: Int) extends TextComponent 
=======
class TextArea(text0: String, rows0: Int, columns0: Int) extends TextComponent
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    with TextComponent.HasColumns with TextComponent.HasRows {
  override lazy val peer: JTextArea = new JTextArea(text0, rows0, columns0) with SuperMixin
  def this(text: String) = this(text, 0, 0)
  def this(rows: Int, columns: Int) = this("", rows, columns)
  def this() = this("", 0, 0)
<<<<<<< HEAD
  
  // TODO: we could make contents StringBuilder-like
  def append(t: String) { peer.append(t) }
    
=======

  // TODO: we could make contents StringBuilder-like
  def append(t: String) { peer.append(t) }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def rows: Int = peer.getRows
  def rows_=(n: Int) = peer.setRows(n)
  def columns: Int = peer.getColumns
  def columns_=(n: Int) = peer.setColumns(n)
<<<<<<< HEAD
  
  def tabSize: Int = peer.getTabSize
  def tabSize_=(n: Int) = peer.setTabSize(n)
  def lineCount: Int = peer.getLineCount
  
=======

  def tabSize: Int = peer.getTabSize
  def tabSize_=(n: Int) = peer.setTabSize(n)
  def lineCount: Int = peer.getLineCount

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def lineWrap: Boolean = peer.getLineWrap
  def lineWrap_=(w: Boolean) = peer.setLineWrap(w)
  def wordWrap: Boolean = peer.getWrapStyleWord
  def wordWrap_=(w: Boolean) = peer.setWrapStyleWord(w)
  def charWrap: Boolean = !peer.getWrapStyleWord
  def charWrap_=(w: Boolean) = peer.setWrapStyleWord(!w)
}
