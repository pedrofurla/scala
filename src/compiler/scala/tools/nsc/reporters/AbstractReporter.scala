/* NSC -- new Scala compiler
 * Copyright 2002-2011 LAMP/EPFL
 * @author Martin Odersky
 */

package scala.tools.nsc
package reporters

import scala.collection.mutable
import scala.tools.nsc.Settings
import scala.tools.nsc.util.Position

/**
 * This reporter implements filtering.
 */
abstract class AbstractReporter extends Reporter {
  val settings: Settings
  def display(pos: Position, msg: String, severity: Severity): Unit
  def displayPrompt(): Unit
<<<<<<< HEAD
  
  private val positions = new mutable.HashMap[Position, Severity]
  
=======

  private val positions = new mutable.HashMap[Position, Severity]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def reset() {
    super.reset
    positions.clear
  }

  private def isVerbose   = settings.verbose.value
  private def noWarnings  = settings.nowarnings.value
  private def isPromptSet = settings.prompt.value

  protected def info0(pos: Position, msg: String, _severity: Severity, force: Boolean) {
<<<<<<< HEAD
    val severity = 
      if (settings.fatalWarnings.value && _severity == WARNING) ERROR
      else _severity
    
=======
    val severity =
      if (settings.fatalWarnings.value && _severity == WARNING) ERROR
      else _severity

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (severity == INFO) {
      if (isVerbose || force)
        display(pos, msg, severity)
    }
    else {
      val hidden = testAndLog(pos, severity)
      if (severity == WARNING && noWarnings) ()
      else {
<<<<<<< HEAD
        if (!hidden || isPromptSet) display(pos, msg, severity)
        if (isPromptSet) displayPrompt
=======
        if (!hidden || isPromptSet)
          display(pos, msg, severity)
        else if (settings.debug.value)
          display(pos, "[ suppressed ] " + msg, severity)

        if (isPromptSet)
          displayPrompt
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }

  /** Logs a position and returns true if it was already logged.
   *  @note  Two positions are considered identical for logging if they have the same point.
   */
  private def testAndLog(pos: Position, severity: Severity): Boolean =
<<<<<<< HEAD
    pos != null && pos.isDefined && { 
=======
    pos != null && pos.isDefined && {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val fpos = pos.focus
      (positions get fpos) match {
        case Some(level) if level >= severity => true
        case _                                => positions += (fpos -> severity) ; false
      }
    }
}
