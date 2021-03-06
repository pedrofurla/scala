/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml

import parsing.NoBindingFactoryAdapter
import factory.XMLLoader
import java.io.{ File, FileDescriptor, FileInputStream, FileOutputStream }
import java.io.{ InputStream, Reader, StringReader, Writer }
import java.nio.channels.Channels
import scala.util.control.Exception.ultimately

object Source
{
  def fromFile(file: File)              = new InputSource(new FileInputStream(file))
  def fromFile(fd: FileDescriptor)      = new InputSource(new FileInputStream(fd))
  def fromFile(name: String)            = new InputSource(new FileInputStream(name))

  def fromInputStream(is: InputStream)  = new InputSource(is)
  def fromReader(reader: Reader)        = new InputSource(reader)
  def fromSysId(sysID: String)          = new InputSource(sysID)
  def fromString(string: String)        = fromReader(new StringReader(string))
}
import Source._

/** The object `XML` provides constants, and functions to load
 *  and save XML elements. Use this when data binding is not desired, i.e.
 *  when XML is handled using `Symbol` nodes.
 *
 *  @author  Burak Emir
 *  @version 1.0, 25/04/2005
 */
object XML extends XMLLoader[Elem]
<<<<<<< HEAD
{  
=======
{
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val xml       = "xml"
  val xmlns     = "xmlns"
  val namespace = "http://www.w3.org/XML/1998/namespace"
  val preserve  = "preserve"
  val space     = "space"
  val lang      = "lang"
  val encoding  = "ISO-8859-1"
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns an XMLLoader whose load* methods will use the supplied SAXParser. */
  def withSAXParser(p: SAXParser): XMLLoader[Elem] =
    new XMLLoader[Elem] { override val parser: SAXParser = p }

<<<<<<< HEAD
  @deprecated("Use save() instead", "2.8.0")
  final def saveFull(filename: String, node: Node, xmlDecl: Boolean, doctype: dtd.DocType): Unit = 
    save(filename, node, encoding, xmlDecl, doctype)
    
  @deprecated("Use save() instead", "2.8.0")
  final def saveFull(filename: String, node: Node, enc: String, xmlDecl: Boolean, doctype: dtd.DocType): Unit = 
    save(filename, node, enc, xmlDecl, doctype)
  
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Saves a node to a file with given filename using given encoding
   *  optionally with xmldecl and doctype declaration.
   *
   *  @param filename the filename
   *  @param node     the xml node we want to write
   *  @param enc      encoding to use
   *  @param xmlDecl  if true, write xml declaration
   *  @param doctype  if not null, write doctype declaration
   */
  final def save(
    filename: String,
    node: Node,
    enc: String = encoding,
    xmlDecl: Boolean = false,
    doctype: dtd.DocType = null
<<<<<<< HEAD
    ): Unit = 
  {
    val fos = new FileOutputStream(filename)
    val w = Channels.newWriter(fos.getChannel(), enc)
    
=======
    ): Unit =
  {
    val fos = new FileOutputStream(filename)
    val w = Channels.newWriter(fos.getChannel(), enc)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    ultimately(w.close())(
      write(w, node, enc, xmlDecl, doctype)
    )
  }

  /** Writes the given node using writer, optionally with xml decl and doctype.
   *  It's the caller's responsibility to close the writer.
   *
<<<<<<< HEAD
   *  @param w        the writer 
=======
   *  @param w        the writer
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @param node     the xml node we want to write
   *  @param enc      the string to be used in `xmlDecl`
   *  @param xmlDecl  if true, write xml declaration
   *  @param doctype  if not null, write doctype declaration
   */
  final def write(w: java.io.Writer, node: Node, enc: String, xmlDecl: Boolean, doctype: dtd.DocType) {
    /* TODO: optimize by giving writer parameter to toXML*/
    if (xmlDecl) w.write("<?xml version='1.0' encoding='" + enc + "'?>\n")
    if (doctype ne null) w.write( doctype.toString() + "\n")
    w.write(Utility.toXML(node).toString)
  }
}
