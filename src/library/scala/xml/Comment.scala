/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml

/** The class `Comment` implements an XML node for comments.
 *
 * @author Burak Emir
 * @param text the text contained in this node, may not contain "--"
 */
<<<<<<< HEAD
case class Comment(commentText: String) extends SpecialNode
{  
=======
case class Comment(commentText: String) extends SpecialNode {

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def label = "#REM"
  override def text = ""
  final override def doCollectNamespaces = false
  final override def doTransform         = false

  if (commentText contains "--")
    throw new IllegalArgumentException("text contains \"--\"")

  /** Appends &quot;<!-- text -->&quot; to this string buffer.
   */
  override def buildString(sb: StringBuilder) =
<<<<<<< HEAD
    sb append ("<!--" + commentText + "-->")
=======
    sb append "<!--" append commentText append "-->"
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
