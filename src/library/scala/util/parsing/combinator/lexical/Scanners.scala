/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.util.parsing
package combinator
package lexical

import token._
import input._

/** This component provides core functionality for lexical parsers.
 *
 *  See its subclasses [[scala.util.parsing.combinator.lexical.Lexical]] and -- most interestingly
 *  [[scala.util.parsing.combinator.lexical.StdLexical]], for more functionality.
 *
<<<<<<< HEAD
 *  @author Martin Odersky, Adriaan Moors 
=======
 *  @author Martin Odersky, Adriaan Moors
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait Scanners extends Parsers {
  type Elem = Char
  type Token
<<<<<<< HEAD
  
  /** This token is produced by a scanner `Scanner` when scanning failed. */
  def errorToken(msg: String): Token
  
  /** A parser that produces a token (from a stream of characters). */
  def token: Parser[Token]
  
=======

  /** This token is produced by a scanner `Scanner` when scanning failed. */
  def errorToken(msg: String): Token

  /** A parser that produces a token (from a stream of characters). */
  def token: Parser[Token]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A parser for white-space -- its result will be discarded. */
  def whitespace: Parser[Any]

  /** `Scanner` is essentially¹ a parser that produces `Token`s
   *  from a stream of characters. The tokens it produces are typically
   *  passed to parsers in `TokenParsers`.
   *
   *  @note ¹ `Scanner` is really a `Reader` of `Token`s
   */
  class Scanner(in: Reader[Char]) extends Reader[Token] {
    /** Convenience constructor (makes a character reader out of the given string) */
    def this(in: String) = this(new CharArrayReader(in.toCharArray()))
    private val (tok, rest1, rest2) = whitespace(in) match {
<<<<<<< HEAD
      case Success(_, in1) => 
=======
      case Success(_, in1) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        token(in1) match {
          case Success(tok, in2) => (tok, in1, in2)
          case ns: NoSuccess => (errorToken(ns.msg), ns.next, skip(ns.next))
        }
      case ns: NoSuccess => (errorToken(ns.msg), ns.next, skip(ns.next))
    }
    private def skip(in: Reader[Char]) = if (in.atEnd) in else in.rest

    override def source: java.lang.CharSequence = in.source
    override def offset: Int = in.offset
    def first = tok
    def rest = new Scanner(rest2)
    def pos = rest1.pos
    def atEnd = in.atEnd || (whitespace(in) match { case Success(_, in1) => in1.atEnd case _ => false })
  }
}

