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
import input.CharArrayReader.EofCh
import scala.collection.mutable

/** This component provides a standard lexical parser for a simple,
 *  [[http://scala-lang.org Scala]]-like language. It parses keywords and
<<<<<<< HEAD
 *  identifiers, numeric literals (integers), strings, and delimiters. 
 *
 *  To distinguish between identifiers and keywords, it uses a set of
 *  reserved identifiers:  every string contained in `reserved` is returned
 *  as a keyword token. (Note that `=>` is hard-coded as a keyword.) 
=======
 *  identifiers, numeric literals (integers), strings, and delimiters.
 *
 *  To distinguish between identifiers and keywords, it uses a set of
 *  reserved identifiers:  every string contained in `reserved` is returned
 *  as a keyword token. (Note that `=>` is hard-coded as a keyword.)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  Additionally, the kinds of delimiters can be specified by the
 *  `delimiters` set.
 *
 *  Usually this component is used to break character-based input into
 *  bigger tokens, which are then passed to a token-parser {@see
 *  [[scala.util.parsing.combinator.syntactical.TokenParsers]]}.
 *
 * @author Martin Odersky
 * @author Iulian Dragos
<<<<<<< HEAD
 * @author Adriaan Moors 
 */
class StdLexical extends Lexical with StdTokens {
  // see `token` in `Scanners`
  def token: Parser[Token] = 
=======
 * @author Adriaan Moors
 */
class StdLexical extends Lexical with StdTokens {
  // see `token` in `Scanners`
  def token: Parser[Token] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    ( identChar ~ rep( identChar | digit )              ^^ { case first ~ rest => processIdent(first :: rest mkString "") }
    | digit ~ rep( digit )                              ^^ { case first ~ rest => NumericLit(first :: rest mkString "") }
    | '\'' ~ rep( chrExcept('\'', '\n', EofCh) ) ~ '\'' ^^ { case '\'' ~ chars ~ '\'' => StringLit(chars mkString "") }
    | '\"' ~ rep( chrExcept('\"', '\n', EofCh) ) ~ '\"' ^^ { case '\"' ~ chars ~ '\"' => StringLit(chars mkString "") }
    | EofCh                                             ^^^ EOF
<<<<<<< HEAD
    | '\'' ~> failure("unclosed string literal")        
    | '\"' ~> failure("unclosed string literal")        
    | delim                                             
    | failure("illegal character")
    )
  
=======
    | '\'' ~> failure("unclosed string literal")
    | '\"' ~> failure("unclosed string literal")
    | delim
    | failure("illegal character")
    )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns the legal identifier chars, except digits. */
  def identChar = letter | elem('_')

  // see `whitespace in `Scanners`
  def whitespace: Parser[Any] = rep(
      whitespaceChar
    | '/' ~ '*' ~ comment
    | '/' ~ '/' ~ rep( chrExcept(EofCh, '\n') )
    | '/' ~ '*' ~ failure("unclosed comment")
    )

  protected def comment: Parser[Any] = (
      '*' ~ '/'  ^^ { case _ => ' '  }
    | chrExcept(EofCh) ~ comment
    )

  /** The set of reserved identifiers: these will be returned as `Keyword`s. */
  val reserved = new mutable.HashSet[String]

  /** The set of delimiters (ordering does not matter). */
  val delimiters = new mutable.HashSet[String]

<<<<<<< HEAD
  protected def processIdent(name: String) = 
    if (reserved contains name) Keyword(name) else Identifier(name)

  private lazy val _delim: Parser[Token] = {
    // construct parser for delimiters by |'ing together the parsers for the individual delimiters, 
    // starting with the longest one -- otherwise a delimiter D will never be matched if there is
    // another delimiter that is a prefix of D   
    def parseDelim(s: String): Parser[Token] = accept(s.toList) ^^ { x => Keyword(s) }
    
=======
  protected def processIdent(name: String) =
    if (reserved contains name) Keyword(name) else Identifier(name)

  private lazy val _delim: Parser[Token] = {
    // construct parser for delimiters by |'ing together the parsers for the individual delimiters,
    // starting with the longest one -- otherwise a delimiter D will never be matched if there is
    // another delimiter that is a prefix of D
    def parseDelim(s: String): Parser[Token] = accept(s.toList) ^^ { x => Keyword(s) }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val d = new Array[String](delimiters.size)
    delimiters.copyToArray(d, 0)
    scala.util.Sorting.quickSort(d)
    (d.toList map parseDelim).foldRight(failure("no matching delimiter"): Parser[Token])((x, y) => y | x)
  }
  protected def delim: Parser[Token] = _delim
}
