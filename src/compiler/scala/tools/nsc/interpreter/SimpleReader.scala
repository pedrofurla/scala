/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Stepan Koltsov
 */

package scala.tools.nsc
package interpreter

import java.io.{ BufferedReader }
import session.NoHistory

/** Reads using standard JDK API */
class SimpleReader(
<<<<<<< HEAD
  in: BufferedReader, 
  out: JPrintWriter, 
=======
  in: BufferedReader,
  out: JPrintWriter,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val interactive: Boolean)
extends InteractiveReader
{
  val history = NoHistory
  val completion = NoCompletion
  val keyBindings: List[KeyBinding] = Nil

  def init() = ()
  def reset() = ()
  def eraseLine() = ()
  def redrawLine() = ()
  def currentLine = ""
  def readOneLine(prompt: String): String = {
    if (interactive) {
      out.print(prompt)
      out.flush()
    }
    in.readLine()
  }
  def readOneKey(prompt: String)  = sys.error("No char-based input in SimpleReader")
}

object SimpleReader {
  def defaultIn  = Console.in
  def defaultOut = new JPrintWriter(Console.out)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply(in: BufferedReader = defaultIn, out: JPrintWriter = defaultOut, interactive: Boolean = true): SimpleReader =
    new SimpleReader(in, out, interactive)
}