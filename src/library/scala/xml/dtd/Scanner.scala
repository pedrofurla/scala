/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.xml
package dtd

<<<<<<< HEAD
/** Scanner for regexps (content models in DTD element declarations) 
=======
/** Scanner for regexps (content models in DTD element declarations)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  todo: cleanup
 */
class Scanner extends Tokens with parsing.TokenTests {

  final val ENDCH = '\u0000'

  var token:Int = END
  var value:String = _
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private var it: Iterator[Char] = null
  private var c: Char = 'z'

  /** initializes the scanner on input s */
  final def initScanner(s: String) {
    value = ""
    it = (s).iterator
    token = 1+END
    next
    nextToken
  }

  /** scans the next token */
  final def nextToken() {
    if (token != END) token = readToken
  }

  // todo: see XML specification... probably isLetter,isDigit is fine
<<<<<<< HEAD
  final def isIdentChar = ( ('a' <= c && c <= 'z') 
                           || ('A' <= c && c <= 'Z'));
  
  final def next() = if (it.hasNext) c = it.next else c = ENDCH

  final def acc(d: Char) { 
=======
  final def isIdentChar = ( ('a' <= c && c <= 'z')
                           || ('A' <= c && c <= 'Z'));

  final def next() = if (it.hasNext) c = it.next else c = ENDCH

  final def acc(d: Char) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (c == d) next else sys.error("expected '"+d+"' found '"+c+"' !");
  }

  final def accS(ds: Seq[Char]) { ds foreach acc }

<<<<<<< HEAD
  final def readToken: Int = 
=======
  final def readToken: Int =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (isSpace(c)) {
      while (isSpace(c)) c = it.next
      S
    } else c match {
      case '('   => next; LPAREN
      case ')'   => next; RPAREN
      case ','   => next; COMMA
      case '*'   => next; STAR
      case '+'   => next; PLUS
      case '?'   => next; OPT
      case '|'   => next; CHOICE
      case '#'   => next; accS( "PCDATA" ); TOKEN_PCDATA
      case ENDCH => END
<<<<<<< HEAD
      case _     => 
        if (isNameStart(c)) name; // NAME 
        else sys.error("unexpected character:" + c)
    }
  
=======
      case _     =>
        if (isNameStart(c)) name; // NAME
        else sys.error("unexpected character:" + c)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  final def name = {
    val sb = new StringBuilder()
    do { sb.append(c); next } while (isNameChar(c));
    value = sb.toString()
    NAME
  }

}
