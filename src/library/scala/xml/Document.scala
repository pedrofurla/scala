/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml

/** A document information item (according to InfoSet spec). The comments
 *  are copied from the Infoset spec, only augmented with some information
 *  on the Scala types for definitions that might have no value.
 *  Also plays the role of an `XMLEvent` for pull parsing.
 *
 *  @author  Burak Emir
 *  @version 1.0, 26/04/2005
 */
@SerialVersionUID(-2289320563321795109L)
class Document extends NodeSeq with pull.XMLEvent with Serializable {

  /** An ordered list of child information items, in document
   *  order. The list contains exactly one element information item. The
   *  list also contains one processing instruction information item for
   *  each processing instruction outside the document element, and one
   *  comment information item for each comment outside the document
   *  element. Processing instructions and comments within the DTD are
   *  excluded. If there is a document type declaration, the list also
<<<<<<< HEAD
   *  contains a document type declaration information item.  
   */ 
=======
   *  contains a document type declaration information item.
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  var children: Seq[Node] = _

  /** The element information item corresponding to the document element. */
  var docElem: Node = _

  /** The dtd that comes with the document, if any */
  var dtd: scala.xml.dtd.DTD = _

<<<<<<< HEAD
  /** An unordered set of notation information items, one for each notation 
   *  declared in the DTD. If any notation is multiply declared, this property 
   *  has no value.
   */
  def notations: Seq[scala.xml.dtd.NotationDecl] = 
    dtd.notations

  /** An unordered set of unparsed entity information items, one for each 
   *  unparsed entity declared in the DTD.
   */
  def unparsedEntities: Seq[scala.xml.dtd.EntityDecl] = 
=======
  /** An unordered set of notation information items, one for each notation
   *  declared in the DTD. If any notation is multiply declared, this property
   *  has no value.
   */
  def notations: Seq[scala.xml.dtd.NotationDecl] =
    dtd.notations

  /** An unordered set of unparsed entity information items, one for each
   *  unparsed entity declared in the DTD.
   */
  def unparsedEntities: Seq[scala.xml.dtd.EntityDecl] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    dtd.unparsedEntities

  /** The base URI of the document entity. */
  var baseURI: String = _

<<<<<<< HEAD
  /** The name of the character encoding scheme in which the document entity 
=======
  /** The name of the character encoding scheme in which the document entity
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  is expressed.
   */
  var encoding: Option[String] = _

  /** An indication of the standalone status of the document, either
   *  true or false. This property is derived from the optional standalone
   *  document declaration in the XML declaration at the beginning of the
   *  document entity, and has no value (`None`) if there is no
<<<<<<< HEAD
   *  standalone document declaration. 
=======
   *  standalone document declaration.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  var standAlone: Option[Boolean] = _

  /** A string representing the XML version of the document. This
   *  property is derived from the XML declaration optionally present at
   *  the beginning of the document entity, and has no value (`None`)
<<<<<<< HEAD
   *  if there is no XML declaration. 
   */ 
=======
   *  if there is no XML declaration.
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  var version: Option[String] = _

  /** 9. This property is not strictly speaking part of the infoset of
   *  the document. Rather it is an indication of whether the processor
   *  has read the complete DTD. Its value is a boolean. If it is false,
   *  then certain properties (indicated in their descriptions below) may
<<<<<<< HEAD
   *  be unknown. If it is true, those properties are never unknown.  
=======
   *  be unknown. If it is true, those properties are never unknown.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  var allDeclarationsProcessed = false

  // methods for NodeSeq

  def theSeq: Seq[Node] = this.docElem
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def canEqual(other: Any) = other match {
    case _: Document  => true
    case _            => false
  }
}
