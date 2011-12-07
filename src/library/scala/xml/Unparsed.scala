/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.xml

/** An XML node for unparsed content. It will be output verbatim, all bets
 *  are off regarding wellformedness etc.
 *
<<<<<<< HEAD
 * @author Burak Emir
 * @param data content in this node, may not be null.
 */
class Unparsed(data: String) extends Atom[String](data)
{
  if (null == data)
    throw new IllegalArgumentException("tried to construct Unparsed with null")

  /** returns text, with some characters escaped according to XML spec */
  override def buildString(sb: StringBuilder) = sb append data
}

=======
 *  @author Burak Emir
 *  @param data content in this node, may not be null.
 */
class Unparsed(data: String) extends Atom[String](data) {

  /** Returns text, with some characters escaped according to XML
   *  specification.
   */
  override def buildString(sb: StringBuilder): StringBuilder =
    sb append data
}

/** This singleton object contains the `apply`and `unapply` methods for
 *  convenient construction and deconstruction.
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
object Unparsed {
  def apply(data: String) = new Unparsed(data)
  def unapply(x: Unparsed) = Some(x.data)
}
