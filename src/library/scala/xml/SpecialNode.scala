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

/** `SpecialNode` is a special XML node which represents either text
 *  `(PCDATA)`, a comment, a `PI`, or an entity ref.
<<<<<<< HEAD
 *  
 *  SpecialNodes also play the role of XMLEvents for pull-parsing.
 *
 *  @author Burak Emir
 */
abstract class SpecialNode extends Node with pull.XMLEvent
{
=======
 *
 *  `SpecialNode`s also play the role of [[scala.xml.pull.XMLEvent]]s for
 *  pull-parsing.
 *
 *  @author Burak Emir
 */
abstract class SpecialNode extends Node with pull.XMLEvent {

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** always empty */
  final override def attributes = Null

  /** always Node.EmptyNamespace */
  final override def namespace = null

  /** always empty */
  final def child = Nil

<<<<<<< HEAD
  /** Append string representation to the given stringbuffer argument. */
=======
  /** Append string representation to the given string buffer argument. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def buildString(sb: StringBuilder): StringBuilder
}
