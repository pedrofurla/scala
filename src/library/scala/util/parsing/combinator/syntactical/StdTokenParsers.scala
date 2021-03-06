/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.util.parsing
package combinator
package syntactical

import token._
import scala.collection.mutable

/** This component provides primitive parsers for the standard tokens defined in `StdTokens`.
*
* @author Martin Odersky, Adriaan Moors
 */
trait StdTokenParsers extends TokenParsers {
  type Tokens <: StdTokens
  import lexical.{Keyword, NumericLit, StringLit, Identifier}

  protected val keywordCache = mutable.HashMap[String, Parser[String]]()

  /** A parser which matches a single keyword token.
   *
<<<<<<< HEAD
   * @param chars    The character string making up the matched keyword. 
   * @return a `Parser` that matches the given string
   */
//  implicit def keyword(chars: String): Parser[String] = accept(Keyword(chars)) ^^ (_.chars)
    implicit def keyword(chars: String): Parser[String] = 
      keywordCache.getOrElseUpdate(chars, accept(Keyword(chars)) ^^ (_.chars))
 
  /** A parser which matches a numeric literal */
  def numericLit: Parser[String] = 
    elem("number", _.isInstanceOf[NumericLit]) ^^ (_.chars)

  /** A parser which matches a string literal */
  def stringLit: Parser[String] = 
    elem("string literal", _.isInstanceOf[StringLit]) ^^ (_.chars)

  /** A parser which matches an identifier */
  def ident: Parser[String] = 
=======
   * @param chars    The character string making up the matched keyword.
   * @return a `Parser` that matches the given string
   */
//  implicit def keyword(chars: String): Parser[String] = accept(Keyword(chars)) ^^ (_.chars)
    implicit def keyword(chars: String): Parser[String] =
      keywordCache.getOrElseUpdate(chars, accept(Keyword(chars)) ^^ (_.chars))

  /** A parser which matches a numeric literal */
  def numericLit: Parser[String] =
    elem("number", _.isInstanceOf[NumericLit]) ^^ (_.chars)

  /** A parser which matches a string literal */
  def stringLit: Parser[String] =
    elem("string literal", _.isInstanceOf[StringLit]) ^^ (_.chars)

  /** A parser which matches an identifier */
  def ident: Parser[String] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    elem("identifier", _.isInstanceOf[Identifier]) ^^ (_.chars)
}


