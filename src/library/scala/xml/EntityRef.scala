/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml

/** The class `EntityRef` implements an XML node for entity references.
 *
 * @author  Burak Emir
 * @version 1.0
 * @param   text the text contained in this node.
 */
case class EntityRef(entityName: String) extends SpecialNode {
  final override def doCollectNamespaces = false
  final override def doTransform         = false
  def label = "#ENTITY"

  override def text = entityName match {
    case "lt"   => "<"
    case "gt"   => ">"
    case "amp"  => "&"
    case "apos" => "'"
    case "quot" => "\""
    case _      => Utility.sbToString(buildString)
  }

  /** Appends `"&amp; entityName;"` to this string buffer.
   *
   *  @param  sb the string buffer.
   *  @return the modified string buffer `sb`.
   */
<<<<<<< HEAD
  override def buildString(sb: StringBuilder) = 
=======
  override def buildString(sb: StringBuilder) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    sb.append("&").append(entityName).append(";")

}
