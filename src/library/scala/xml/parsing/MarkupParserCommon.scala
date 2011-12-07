/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml
package parsing

import scala.io.Source
import scala.xml.dtd._
import scala.annotation.switch
import Utility.Escapes.{ pairs => unescape }

import Utility.SU

/** This is not a public trait - it contains common code shared
 *  between the library level XML parser and the compiler's.
 *  All members should be accessed through those.
 */
private[scala] trait MarkupParserCommon extends TokenTests {
  protected def unreachable = sys.error("Cannot be reached.")
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // type HandleType       // MarkupHandler, SymbolicXMLBuilder
  type InputType        // Source, CharArrayReader
  type PositionType     // Int, Position
  type ElementType      // NodeSeq, Tree
  type NamespaceType    // NamespaceBinding, Any
  type AttributesType   // (MetaData, NamespaceBinding), mutable.Map[String, Tree]

  def mkAttributes(name: String, pscope: NamespaceType): AttributesType
  def mkProcInstr(position: PositionType, name: String, text: String): ElementType

  /** parse a start or empty tag.
<<<<<<< HEAD
   *  [40] STag         ::= '<' Name { S Attribute } [S] 
   *  [44] EmptyElemTag ::= '<' Name { S Attribute } [S] 
=======
   *  [40] STag         ::= '<' Name { S Attribute } [S]
   *  [44] EmptyElemTag ::= '<' Name { S Attribute } [S]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  protected def xTag(pscope: NamespaceType): (String, AttributesType) = {
    val name = xName
    xSpaceOpt
<<<<<<< HEAD
     
    (name, mkAttributes(name, pscope))
  }
  
=======

    (name, mkAttributes(name, pscope))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** '<?' ProcInstr ::= Name [S ({Char} - ({Char}'>?' {Char})]'?>'
   *
   * see [15]
   */
  def xProcInstr: ElementType = {
    val n = xName
    xSpaceOpt
    xTakeUntil(mkProcInstr(_, n, _), () => tmppos, "?>")
  }

  /** attribute value, terminated by either ' or ". value may not contain <.
   *  @param endch either ' or "
   */
  def xAttributeValue(endCh: Char): String = {
<<<<<<< HEAD
    val buf = new StringBuilder      
=======
    val buf = new StringBuilder
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    while (ch != endCh) {
      // well-formedness constraint
      if (ch == '<') return errorAndResult("'<' not allowed in attrib value", "")
      else if (ch == SU) truncatedError("")
      else buf append ch_returning_nextch
    }
    ch_returning_nextch
    // @todo: normalize attribute value
    buf.toString
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def xAttributeValue(): String = {
    val str = xAttributeValue(ch_returning_nextch)
    // well-formedness constraint
    normalizeAttributeValue(str)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def takeUntilChar(it: Iterator[Char], end: Char): String = {
    val buf = new StringBuilder
    while (it.hasNext) it.next match {
      case `end`  => return buf.toString
      case ch     => buf append ch
    }
    sys.error("Expected '%s'".format(end))
  }

  /** [42]  '<' xmlEndTag ::=  '<' '/' Name S? '>'
   */
  def xEndTag(startName: String) {
    xToken('/')
    if (xName != startName)
      errorNoEnd(startName)

    xSpaceOpt
    xToken('>')
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** actually, Name ::= (Letter | '_' | ':') (NameChar)*  but starting with ':' cannot happen
   *  Name ::= (Letter | '_') (NameChar)*
   *
   *  see  [5] of XML 1.0 specification
   *
   *  pre-condition:  ch != ':' // assured by definition of XMLSTART token
   *  post-condition: name does neither start, nor end in ':'
   */
  def xName: String = {
    if (ch == SU)
      truncatedError("")
    else if (!isNameStart(ch))
      return errorAndResult("name expected, but char '%s' cannot start a name" format ch, "")
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val buf = new StringBuilder

    do buf append ch_returning_nextch
    while (isNameChar(ch))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (buf.last == ':') {
      reportSyntaxError( "name cannot end in ':'" )
      buf.toString dropRight 1
    }
    else buf.toString
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def attr_unescape(s: String) = s match {
    case "lt"     => "<"
    case "gt"     => ">"
    case "amp"    => "&"
    case "apos"   => "'"
    case "quot"   => "\""
    case "quote"  => "\""
    case _        => "&" + s + ";"
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Replaces only character references right now.
   *  see spec 3.3.3
   */
  private def normalizeAttributeValue(attval: String): String = {
    val buf = new StringBuilder
    val it = attval.iterator.buffered
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    while (it.hasNext) buf append (it.next match {
      case ' ' | '\t' | '\n' | '\r' => " "
      case '&' if it.head == '#'    => it.next ; xCharRef(it)
      case '&'                      => attr_unescape(takeUntilChar(it, ';'))
      case c                        => c
    })
<<<<<<< HEAD
    
    buf.toString
  }  
  
=======

    buf.toString
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** CharRef ::= "&#" '0'..'9' {'0'..'9'} ";"
   *            | "&#x" '0'..'9'|'A'..'F'|'a'..'f' { hexdigit } ";"
   *
   * see [66]
   */
  def xCharRef(ch: () => Char, nextch: () => Unit): String =
    Utility.parseCharRef(ch, nextch, reportSyntaxError _, truncatedError _)

  def xCharRef(it: Iterator[Char]): String = {
    var c = it.next
    Utility.parseCharRef(() => c, () => { c = it.next }, reportSyntaxError _, truncatedError _)
  }

  def xCharRef: String = xCharRef(() => ch, () => nextch)
<<<<<<< HEAD
  
  /** Create a lookahead reader which does not influence the input */
  def lookahead(): BufferedIterator[Char]
  
=======

  /** Create a lookahead reader which does not influence the input */
  def lookahead(): BufferedIterator[Char]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The library and compiler parsers had the interesting distinction of
   *  different behavior for nextch (a function for which there are a total
   *  of two plausible behaviors, so we know the design space was fully
   *  explored.) One of them returned the value of nextch before the increment
   *  and one of them the new value.  So to unify code we have to at least
   *  temporarily abstract over the nextchs.
   */
  def ch: Char
  def nextch(): Unit
<<<<<<< HEAD
  def ch_returning_nextch: Char
=======
  protected def ch_returning_nextch: Char
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def eof: Boolean

  // def handle: HandleType
  var tmppos: PositionType

  def xHandleError(that: Char, msg: String): Unit
  def reportSyntaxError(str: String): Unit
  def reportSyntaxError(pos: Int, str: String): Unit
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def truncatedError(msg: String): Nothing
  def errorNoEnd(tag: String): Nothing

  protected def errorAndResult[T](msg: String, x: T): T = {
    reportSyntaxError(msg)
    x
  }

  def xToken(that: Char) {
    if (ch == that) nextch
    else xHandleError(that, "'%s' expected instead of '%s'".format(that, ch))
  }
  def xToken(that: Seq[Char]) { that foreach xToken }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** scan [S] '=' [S]*/
  def xEQ() = { xSpaceOpt; xToken('='); xSpaceOpt }

  /** skip optional space S? */
  def xSpaceOpt() = while (isSpace(ch) && !eof) nextch

  /** scan [3] S ::= (#x20 | #x9 | #xD | #xA)+ */
  def xSpace() =
    if (isSpace(ch)) { nextch; xSpaceOpt }
    else xHandleError(ch, "whitespace expected")
<<<<<<< HEAD
    
  /** Apply a function and return the passed value */
  def returning[T](x: T)(f: T => Unit): T = { f(x) ; x }
 
=======

  /** Apply a function and return the passed value */
  def returning[T](x: T)(f: T => Unit): T = { f(x); x }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Execute body with a variable saved and restored after execution */
  def saving[A, B](getter: A, setter: A => Unit)(body: => B): B = {
    val saved = getter
    try body
    finally setter(saved)
<<<<<<< HEAD
  }  
    
=======
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Take characters from input stream until given String "until"
   *  is seen.  Once seen, the accumulated characters are passed
   *  along with the current Position to the supplied handler function.
   */
  protected def xTakeUntil[T](
    handler: (PositionType, String) => T,
    positioner: () => PositionType,
    until: String): T =
  {
    val sb = new StringBuilder
    val head = until.head
    val rest = until.tail
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    while (true) {
      if (ch == head && peek(rest))
        return handler(positioner(), sb.toString)
      else if (ch == SU)
        truncatedError("")  // throws TruncatedXMLControl in compiler
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      sb append ch
      nextch
    }
    unreachable
  }

  /** Create a non-destructive lookahead reader and see if the head
   *  of the input would match the given String.  If yes, return true
   *  and drop the entire String from input; if no, return false
   *  and leave input unchanged.
   */
  private def peek(lookingFor: String): Boolean =
    (lookahead() take lookingFor.length sameElements lookingFor.iterator) && {
      // drop the chars from the real reader (all lookahead + orig)
      (0 to lookingFor.length) foreach (_ => nextch)
      true
    }
}
