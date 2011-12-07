/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.xml
package pull

/** An XML event for pull parsing.  All events received during
 * parsing will be one of the subclasses of this trait.
 */
trait XMLEvent

<<<<<<< HEAD
/** 
=======
/**
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * An Element's start tag was encountered.
 * @param pre prefix, if any, on the element.  This is the `xs` in `<xs:string>foo</xs:string>`.
 * @param label the name of the element, not including the prefix
 * @param attrs any attributes on the element
 */
<<<<<<< HEAD
case class EvElemStart(pre: String, label: String, attrs: MetaData, scope: NamespaceBinding) extends XMLEvent 

/** 
=======
case class EvElemStart(pre: String, label: String, attrs: MetaData, scope: NamespaceBinding) extends XMLEvent

/**
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * An Element's end tag was encountered.
 * @param pre prefix, if any, on the element.  This is the `xs` in `<xs:string>foo</xs:string>`.
 * @param label the name of the element, not including the prefix
 */
<<<<<<< HEAD
case class EvElemEnd(pre: String, label: String) extends XMLEvent 

/** 
=======
case class EvElemEnd(pre: String, label: String) extends XMLEvent

/**
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * A text node was encountered.
 * @param text the text that was found
 */
case class EvText(text: String) extends XMLEvent

/** An entity reference was encountered.
<<<<<<< HEAD
 * @param the name of the entity, e.g. `gt` when encountering the entity `&gt;` 
 */
case class EvEntityRef(entity: String) extends XMLEvent

/** 
 * A processing instruction was encountered.
 * @param target the "PITarget" of the processing instruction.  For the instruction `<?foo bar="baz"?>`, the target would 
=======
 * @param the name of the entity, e.g. `gt` when encountering the entity `&gt;`
 */
case class EvEntityRef(entity: String) extends XMLEvent

/**
 * A processing instruction was encountered.
 * @param target the "PITarget" of the processing instruction.  For the instruction `<?foo bar="baz"?>`, the target would
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * be `foo`
 * @param text the remainder of the instruction.  For the instruction `<?foo bar="baz"?>`, the text would
 * be `bar="baz"`
 * @see [[http://www.w3.org/TR/REC-xml/#sec-pi]]
 */
case class EvProcInstr(target: String, text: String) extends XMLEvent

<<<<<<< HEAD
/** 
 * A comment was encountered 
=======
/**
 * A comment was encountered
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @param text the text of the comment
 */
case class EvComment(text: String) extends XMLEvent
