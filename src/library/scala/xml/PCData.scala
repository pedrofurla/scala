<<<<<<< HEAD

package scala.xml

/** This class (which is not used by all XML parsers, but always used by the XHTML one) 
 *  represents parseable character data, which appeared as CDATA sections in the input 
 *  and is to be preserved as CDATA section in the output.
 */
case class PCData(_data: String) extends Atom[String](_data) {
  if (null == data)
    throw new IllegalArgumentException("tried to construct PCData with null")
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml

/** This class (which is not used by all XML parsers, but always used by the
 *  XHTML one) represents parseable character data, which appeared as CDATA
 *  sections in the input and is to be preserved as CDATA section in the output.
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
class PCData(data: String) extends Atom[String](data) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** Returns text, with some characters escaped according to the XML
   *  specification.
   *
<<<<<<< HEAD
   *  @param  sb ...
   *  @return ...
   */
  override def buildString(sb: StringBuilder) =
    sb append "<![CDATA[%s]]>".format(data)
}
=======
   *  @param  sb the input string buffer associated to some XML element
   *  @return the input string buffer with the formatted CDATA section
   */
  override def buildString(sb: StringBuilder): StringBuilder =
    sb append "<![CDATA[%s]]>".format(data)
}

/** This singleton object contains the `apply`and `unapply` methods for
 *  convenient construction and deconstruction.
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
object PCData {
  def apply(data: String) = new PCData(data)
  def unapply(other: Any): Option[String] = other match {
    case x: PCData => Some(x.data)
    case _         => None
  }
}

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
