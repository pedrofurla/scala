/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.nsc
package interpreter

import util.stringFromWriter

trait Formatting {
  def prompt: String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def spaces(code: String): String = {
    /** Heuristic to avoid indenting and thereby corrupting """-strings and XML literals. */
    val tokens = List("\"\"\"", "</", "/>")
    val noIndent = (code contains "\n") && (tokens exists code.contains)

    if (noIndent) ""
    else prompt drop 1 map (_ => ' ')
  }
  /** Indent some code by the width of the scala> prompt.
   *  This way, compiler error messages read better.
   */
  def indentCode(code: String) = {
    val indent = spaces(code)
    stringFromWriter(str =>
      for (line <- code.lines) {
        str print indent
        str print (line + "\n")
        str.flush()
      }
    )
  }
}
