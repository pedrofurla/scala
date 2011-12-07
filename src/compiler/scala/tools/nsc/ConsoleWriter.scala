/* NSC -- new Scala compiler
 * Copyright 2006-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc

import java.io.Writer

/** A Writer that writes onto the Scala Console.
 *
 *  @author  Lex Spoon
 *  @version 1.0
 */
class ConsoleWriter extends Writer {
  def close = flush
<<<<<<< HEAD
  
  def flush = Console.flush
  
=======

  def flush = Console.flush

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def write(cbuf: Array[Char], off: Int, len: Int) {
    if (len > 0)
      write(new String(cbuf.slice(off, off+len)))
  }

  override def write(str: String) { Console.print(str) }
}
