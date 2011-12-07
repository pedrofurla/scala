/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.util.parsing.input

/** `Position` is the base trait for objects describing a position in a ``document''.
 *
 *  It provides functionality for:
 *   - generating a visual representation of this position (`longString`);
 *   - comparing two positions (`<`).
 *
 *  To use this class for a concrete kind of ``document'', implement the `lineContents` method.
 *
 * @author Martin Odersky
 * @author Adriaan Moors
 */
trait Position {

  /** The line number referred to by the position; line numbers start at 1. */
  def line: Int

  /** The column number referred to by the position; column numbers start at 1. */
  def column: Int

  /** The contents of the line numbered `lnum` (must not contain a new-line character).
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param lnum a 1-based integer index into the `document`
   * @return the line at `lnum` (not including a newline)
   */
  protected def lineContents: String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns a string representation of the `Position`, of the form `line.column`. */
  override def toString = ""+line+"."+column

  /** Returns a more ``visual'' representation of this position.
   *  More precisely, the resulting string consists of two lines:
   *   1. the line in the document referred to by this position
   *   2. a caret indicating the column
   *
   *  Example:
   *  {{{
   *    List(this, is, a, line, from, the, document)
   *                 ^
   *  }}}
   */
  def longString = lineContents+"\n"+lineContents.take(column-1).map{x => if (x == '\t') x else ' ' } + "^"

  /** Compare this position to another, by first comparing their line numbers,
   * and then -- if necessary -- using the columns to break a tie.
   *
   * @param `that` a `Position` to compare to this `Position`
   * @return true if this position's line number or (in case of equal line numbers)
   *         column is smaller than the corresponding components of `that`
   */
  def <(that: Position) = {
<<<<<<< HEAD
    this.line < that.line || 
=======
    this.line < that.line ||
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    this.line == that.line && this.column < that.column
  }
}
