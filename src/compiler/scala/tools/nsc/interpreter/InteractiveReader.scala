/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Stepan Koltsov
 */

package scala.tools.nsc
package interpreter

import java.io.IOException
import java.nio.channels.ClosedByInterruptException
import scala.util.control.Exception._
import session.History
import InteractiveReader._
import Properties.isMac

/** Reads lines from an input stream */
trait InteractiveReader {
  val interactive: Boolean
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def init(): Unit
  def reset(): Unit

  def history: History
  def completion: Completion
  def keyBindings: List[KeyBinding]
  def eraseLine(): Unit
  def redrawLine(): Unit
  def currentLine: String

  def readYesOrNo(prompt: String, alt: => Boolean): Boolean = readOneKey(prompt) match {
    case 'y'  => true
    case 'n'  => false
    case _    => alt
  }
  def readAssumingNo(prompt: String)  = readYesOrNo(prompt, false)
  def readAssumingYes(prompt: String) = readYesOrNo(prompt, true)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected def readOneLine(prompt: String): String
  protected def readOneKey(prompt: String): Int

  def readLine(prompt: String): String =
    // hack necessary for OSX jvm suspension because read calls are not restarted after SIGTSTP
    if (isMac) restartSysCalls(readOneLine(prompt), reset())
    else readOneLine(prompt)
}

object InteractiveReader {
  val msgEINTR = "Interrupted system call"
  def restartSysCalls[R](body: => R, reset: => Unit): R =
    try body catch {
      case e: IOException if e.getMessage == msgEINTR => reset ; body
    }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply(): InteractiveReader = SimpleReader()
  @deprecated("Use `apply` instead.", "2.9.0")
  def createDefault(): InteractiveReader = apply()
}

