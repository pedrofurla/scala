/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package interpreter

import scala.collection.{ mutable, immutable }
import scala.PartialFunction.cond
<<<<<<< HEAD
import scala.reflect.NameTransformer
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.reflect.internal.Chars

trait ReplStrings {
  // Longest common prefix
  def longestCommonPrefix(xs: List[String]): String = {
    if (xs.isEmpty || xs.contains("")) ""
    else xs.head.head match {
      case ch =>
        if (xs.tail forall (_.head == ch)) "" + ch + longestCommonPrefix(xs map (_.tail))
        else ""
    }
  }
  /** Convert a string into code that can recreate the string.
   *  This requires replacing all special characters by escape
   *  codes. It does not add the surrounding " marks.  */
<<<<<<< HEAD
  def string2code(str: String): String = {    
=======
  def string2code(str: String): String = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val res = new StringBuilder
    for (c <- str) c match {
      case '"' | '\'' | '\\'  => res += '\\' ; res += c
      case _ if c.isControl   => res ++= Chars.char2uescape(c)
      case _                  => res += c
    }
    res.toString
  }

  def string2codeQuoted(str: String) =
    "\"" + string2code(str) + "\""

<<<<<<< HEAD
  def any2stringOf(x: Any, maxlen: Int) = 
=======
  def any2stringOf(x: Any, maxlen: Int) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    "scala.runtime.ScalaRunTime.replStringOf(%s, %s)".format(x, maxlen)

  def words(s: String) = s.trim split "\\s+" filterNot (_ == "") toList
  def isQuoted(s: String) = (s.length >= 2) && (s.head == s.last) && ("\"'" contains s.head)
}
