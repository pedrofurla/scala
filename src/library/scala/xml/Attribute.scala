/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD

package scala.xml

/** Attribute defines the interface shared by both
 *  PrefixedAttribute and UnprefixedAttribute
 */

=======
package scala.xml

/** This singleton object contains the `apply` and `unapply` methods for
 *  convenient construction and deconstruction.
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
object Attribute {
  def unapply(x: Attribute) = x match {
    case PrefixedAttribute(_, key, value, next) => Some((key, value, next))
    case UnprefixedAttribute(key, value, next)  => Some((key, value, next))
    case _                                      => None
  }

  /** Convenience functions which choose Un/Prefixedness appropriately */
  def apply(key: String, value: Seq[Node], next: MetaData): Attribute =
    new UnprefixedAttribute(key, value, next)
<<<<<<< HEAD
    
  def apply(pre: String, key: String, value: String, next: MetaData): Attribute =
    if (pre == null || pre == "") new UnprefixedAttribute(key, value, next)
    else new PrefixedAttribute(pre, key, value, next)
    
  def apply(pre: String, key: String, value: Seq[Node], next: MetaData): Attribute =
    if (pre == null || pre == "") new UnprefixedAttribute(key, value, next)
    else new PrefixedAttribute(pre, key, value, next)
    
  def apply(pre: Option[String], key: String, value: Seq[Node], next: MetaData): Attribute =
    pre match {
      case None     => new UnprefixedAttribute(key, value, next)
      case Some(p)  => new PrefixedAttribute(p, key, value, next)
    }  
}

=======

  def apply(pre: String, key: String, value: String, next: MetaData): Attribute =
    if (pre == null || pre == "") new UnprefixedAttribute(key, value, next)
    else new PrefixedAttribute(pre, key, value, next)

  def apply(pre: String, key: String, value: Seq[Node], next: MetaData): Attribute =
    if (pre == null || pre == "") new UnprefixedAttribute(key, value, next)
    else new PrefixedAttribute(pre, key, value, next)

  def apply(pre: Option[String], key: String, value: Seq[Node], next: MetaData): Attribute =
    pre match {
      case None    => new UnprefixedAttribute(key, value, next)
      case Some(p) => new PrefixedAttribute(p, key, value, next)
    }
}

/** The `Attribute` trait defines the interface shared by both
 *  [[scala.xml.PrefixedAttribute]] and [[scala.xml.UnprefixedAttribute]].
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
abstract trait Attribute extends MetaData {
  def pre: String        // will be null if unprefixed
  val key: String
  val value: Seq[Node]
  val next: MetaData
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply(key: String): Seq[Node]
  def apply(namespace: String, scope: NamespaceBinding, key: String): Seq[Node]
  def copy(next: MetaData): Attribute

  def remove(key: String) =
    if (!isPrefixed && this.key == key) next
    else copy(next remove key)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def remove(namespace: String, scope: NamespaceBinding, key: String) =
    if (isPrefixed && this.key == key && (scope getURI pre) == namespace) next
    else next.remove(namespace, scope, key)

  def isPrefixed: Boolean = pre != null
<<<<<<< HEAD
  def getNamespace(owner: Node): String
  def wellformed(scope: NamespaceBinding): Boolean = {
    val arg = if (isPrefixed) scope getURI pre else null    
    (next(arg, scope, key) == null) && (next wellformed scope)
  }
  
  /** Appends string representation of only this attribute to stringbuffer.
   */
  def toString1(sb: StringBuilder) {
=======

  def getNamespace(owner: Node): String

  def wellformed(scope: NamespaceBinding): Boolean = {
    val arg = if (isPrefixed) scope getURI pre else null
    (next(arg, scope, key) == null) && (next wellformed scope)
  }

  /** Returns an iterator on attributes */
  override def iterator: Iterator[MetaData] = {
    if (value == null) next.iterator
    else Iterator.single(this) ++ next.iterator
  }

  override def size: Int = {
    if (value == null) next.size
    else 1 + next.size
  }

  /** Appends string representation of only this attribute to stringbuffer.
   */
  protected def toString1(sb: StringBuilder) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (value == null)
      return
    if (isPrefixed)
      sb append pre append ':'
<<<<<<< HEAD
    
    sb append key append '='
    val sb2 = new StringBuilder()
    Utility.sequenceToXML(value, TopScope, sb2, true)    
    Utility.appendQuoted(sb2.toString(), sb)
=======

    sb append key append '='
    val sb2 = new StringBuilder()
    Utility.sequenceToXML(value, TopScope, sb2, true)
    Utility.appendQuoted(sb2.toString, sb)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}
