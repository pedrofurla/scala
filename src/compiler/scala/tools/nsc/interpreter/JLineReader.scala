/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Stepan Koltsov
 */

package scala.tools.nsc
package interpreter

import scala.tools.jline.console.ConsoleReader
import scala.tools.jline.console.completer._
import session._
import scala.collection.JavaConverters._
import Completion._
import io.Streamable.slurp

/**
 *  Reads from the console using JLine.
 */
class JLineReader(_completion: => Completion) extends InteractiveReader {
  val interactive = true
  val consoleReader = new JLineConsoleReader()
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val completion = _completion
  lazy val history: JLineHistory = JLineHistory()
  lazy val keyBindings =
    try KeyBinding parse slurp(term.getDefaultBindings)
    catch { case _: Exception => Nil }

  private def term = consoleReader.getTerminal()
  def reset() = term.reset()
  def init()  = term.init()
<<<<<<< HEAD
  
  def scalaToJline(tc: ScalaCompleter): Completer = new Completer {
    def complete(_buf: String, cursor: Int, candidates: JList[CharSequence]): Int = {
      val buf   = if (_buf == null) "" else _buf      
=======

  def scalaToJline(tc: ScalaCompleter): Completer = new Completer {
    def complete(_buf: String, cursor: Int, candidates: JList[CharSequence]): Int = {
      val buf   = if (_buf == null) "" else _buf
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val Candidates(newCursor, newCandidates) = tc.complete(buf, cursor)
      newCandidates foreach (candidates add _)
      newCursor
    }
  }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class JLineConsoleReader extends ConsoleReader with ConsoleReaderHelper {
    // working around protected/trait/java insufficiencies.
    def goBack(num: Int): Unit = back(num)
    def readOneKey(prompt: String) = {
      this.print(prompt)
      this.flush()
      this.readVirtualKey()
    }
    def eraseLine() = consoleReader.resetPromptLine("", "", 0)
    def redrawLineAndFlush(): Unit = { flush() ; drawLine() ; flush() }
    // override def readLine(prompt: String): String

    // A hook for running code after the repl is done initializing.
    lazy val postInit: Unit = {
      this setBellEnabled false
<<<<<<< HEAD
      if (history ne NoHistory)
        this setHistory history
    
=======
      if ((history: History) ne NoHistory)
        this setHistory history

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (completion ne NoCompletion) {
        val argCompletor: ArgumentCompleter =
          new ArgumentCompleter(new JLineDelimiter, scalaToJline(completion.completer()))
        argCompletor setStrict false
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        this addCompleter argCompletor
        this setAutoprintThreshold 400 // max completion candidates without warning
      }
    }
  }
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def currentLine = consoleReader.getCursorBuffer.buffer.toString
  def redrawLine() = consoleReader.redrawLineAndFlush()
  def eraseLine() = consoleReader.eraseLine()
  // Alternate implementation, not sure if/when I need this.
  // def eraseLine() = while (consoleReader.delete()) { }
  def readOneLine(prompt: String) = consoleReader readLine prompt
  def readOneKey(prompt: String)  = consoleReader readOneKey prompt
}
