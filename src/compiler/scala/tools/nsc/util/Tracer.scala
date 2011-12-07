/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package util

import java.io.PrintStream
<<<<<<< HEAD

class Tracer(enabled: () => Boolean) {
  def out: PrintStream = System.out
  def intoString(x: Any): String = "" + x
  def stringify(x: Any): String = x match {
    case null                   => "null"
    case x: TraversableOnce[_]  => x map stringify mkString ", "
    case x: Product             => stringify(x.productIterator)
    case x: AnyRef              => intoString(x)
=======
import scala.runtime.ScalaRunTime

class Tracer(enabled: () => Boolean) {
  def out: PrintStream = System.out
  def stringify(x: Any) = ScalaRunTime stringOf x

  // So can pass tuples, lists, whatever as arguments and don't
  // get a lot of extra parens or noisy prefixes.
  def stringifyArgs(x: Any) = {
    x match {
      case x: TraversableOnce[_] => x map stringify mkString ", "
      case x: Product            => x.productIterator map stringify mkString ", "
      case _                     => stringify(x)
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  private val LBRACE = "{"
  private val RBRACE = "}"
  private var indentLevel = 0
<<<<<<< HEAD
  private def ind(s: String) = (" " * (indentLevel * 2)) + s
=======
  private def spaces = " " * (indentLevel * 2)
  private def pblock(result: Any) = {
    p(LBRACE + "\n")
    indented(p(spaces + stringify(result) + "\n"))
    p(spaces + RBRACE + "\n")
  }
  private def passign(name: String, args: String) =
    p(spaces + name + "(" + args + ") = ")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def indented[T](body: => T): T = {
    indentLevel += 1
    try body
    finally indentLevel -= 1
  }
  private def p(s: String) = {
    out.print(s)
    out.flush()
  }
<<<<<<< HEAD
  private def pin[T](x: T): T = {
    p(ind("" + x))
    x
  }
  def apply[T](name: String, args: => Any)(body: => T): T = {
    val result = body
    if (enabled()) {
      // concise output optimization
      val boolResult = result match {
        case x: Boolean => Some(x)
        case _          => None
      }
      p(ind("%s(%s) = %s\n".format(
        name,
        stringify(args),
        boolResult getOrElse LBRACE))
      )
      if (boolResult.isEmpty) {
        indented(pin(result))
        p("\n" + ind(RBRACE))
      }
      result
    }
    else result
=======

  def apply[T](name: String, args: => Any)(body: => T): T = {
    val result = body

    if (enabled()) {
      passign(name, stringifyArgs(args))
      val resultToPrint = result match {
        case Some(x)  => x
        case _        => result
      }
      // concise output optimization
      val isOneliner = resultToPrint match {
        case _: Boolean | _: None.type => true
        case s: String                 => s.length < 40
        case _                         => false
      }
      if (isOneliner) p(stringify(resultToPrint) + "\n")
      else pblock(resultToPrint)
    }

    result
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

object Tracer {
  def apply(enabled: => Boolean): Tracer = new Tracer(() => enabled)
}
