/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.nsc
package interpreter

import scala.tools.jline.console.completer.ArgumentCompleter.{ ArgumentDelimiter, ArgumentList }

class JLineDelimiter extends ArgumentDelimiter {
  def toJLine(args: List[String], cursor: Int) = args match {
    case Nil    => new ArgumentList(new Array[String](0), 0, 0, cursor)
    case xs     => new ArgumentList(xs.toArray, xs.size - 1, xs.last.length, cursor)
  }

  def delimit(buffer: CharSequence, cursor: Int) = {
    val p = Parsed(buffer.toString, cursor)
    toJLine(p.args, cursor)
  }
  def isDelimiter(buffer: CharSequence, cursor: Int) = Parsed(buffer.toString, cursor).isDelimiter
}

trait Delimited {
  self: Parsed =>
<<<<<<< HEAD
  
  def delimited: Char => Boolean
  def escapeChars: List[Char] = List('\\')
  def quoteChars: List[(Char, Char)] = List(('\'', '\''), ('"', '"'))
  
  /** Break String into args based on delimiting function.
   */
  protected def toArgs(s: String): List[String] = 
=======

  def delimited: Char => Boolean
  def escapeChars: List[Char] = List('\\')
  def quoteChars: List[(Char, Char)] = List(('\'', '\''), ('"', '"'))

  /** Break String into args based on delimiting function.
   */
  protected def toArgs(s: String): List[String] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (s == "") Nil
    else (s indexWhere isDelimiterChar) match {
      case -1   => List(s)
      case idx  => (s take idx) :: toArgs(s drop (idx + 1))
    }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isDelimiterChar(ch: Char) = delimited(ch)
  def isEscapeChar(ch: Char): Boolean = escapeChars contains ch
  def isQuoteStart(ch: Char): Boolean = quoteChars map (_._1) contains ch
  def isQuoteEnd(ch: Char): Boolean = quoteChars map (_._2) contains ch
}
