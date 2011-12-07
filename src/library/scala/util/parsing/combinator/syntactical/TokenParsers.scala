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

/** This is the core component for token-based parsers.
 *
 *  @author Martin Odersky
 *  @author Adriaan Moors
 */
trait TokenParsers extends Parsers {
  /** `Tokens` is the abstract type of the `Token`s consumed by the parsers in this component. */
  type Tokens <: token.Tokens
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** `lexical` is the component responsible for consuming some basic kind of
   *  input (usually character-based) and turning it into the tokens
   *  understood by these parsers.
   */
  val lexical: Tokens

  /** The input-type for these parsers*/
  type Elem = lexical.Token
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}


