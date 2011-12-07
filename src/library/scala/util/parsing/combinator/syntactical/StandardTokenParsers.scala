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
<<<<<<< HEAD
import lexical.StdLexical 
=======
import lexical.StdLexical
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/** This component provides primitive parsers for the standard tokens defined in `StdTokens`.
*
* @author Martin Odersky, Adriaan Moors
 */
class StandardTokenParsers extends StdTokenParsers {
  type Tokens = StdTokens
  val lexical = new StdLexical

  //an implicit keyword function that gives a warning when a given word is not in the reserved/delimiters list
<<<<<<< HEAD
  override implicit def keyword(chars : String): Parser[String] = 
=======
  override implicit def keyword(chars : String): Parser[String] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if(lexical.reserved.contains(chars) || lexical.delimiters.contains(chars)) super.keyword(chars)
    else failure("You are trying to parse \""+chars+"\", but it is neither contained in the delimiters list, nor in the reserved keyword list of your lexical object")

}
