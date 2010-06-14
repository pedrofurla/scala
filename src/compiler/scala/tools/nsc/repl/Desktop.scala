/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools.nsc
package repl

import io.Path
import java.awt.{ Desktop => JDesktop }
import java.net.URI

object Desktop {
  def desktop = apply()
  def apply() = JDesktop.getDesktop()
  def isSupported = JDesktop.isDesktopSupported()
  
  def browse[T <% URI](uri: T)      = desktop.browse(uri)
  def edit[T <% Path](file: Path)   = desktop.edit(file.jfile)
  def mail[T <% URI](uri: T = null) = if (uri == null) desktop.mail() else desktop.mail(uri)
  def open[T <% Path](file: Path)   = desktop.open(file.jfile)
  def print[T <% Path](file: Path)  = desktop.print(file.jfile)
}

// XXX
// import java.awt.{ Toolkit, HeadlessException }
// import java.awt.datatransfer.{ DataFlavor, StringSelection }
// 
// object o {
//   private def clip() = Toolkit.getDefaultToolkit.getSystemClipboard
//   val sf = DataFlavor.stringFlavor
//   
//   def getClipboard(): String = {
//     val t = clip.getContents(null)
//     if (t != null && t.isDataFlavorSupported(sf)) t.getTransferData(sf).asInstanceOf[String]
//     else ""
//   }
//   
//   def setClipboard(s: String) = clip.setContents(new StringSelection(s), null)
// }
//

// Verifying lib and compiler versions match
//
// object Scratch {
//   val loader = ClassLoader.getSystemClassLoader
// 
//   def getProperty(file: String, key: String): Option[String] = {
//     val props = new java.util.Properties()
//     val in = loader.getResourceAsStream(file)
//     props load in
//     val res = props get key
//     if (res == null) None else Some(res.asInstanceOf[String])
//   }
// 
//   def verifyVersionsMatch(): Boolean = {
//     val c = getProperty("compiler.properties", "version.number") getOrElse (return true)
//     val l = getProperty("library.properties", "version.number") getOrElse (return true)
// 
//     assert(c == l, "compiler/library version mismatch %s vs %s".format(c, l))
//     true
//   }
// }
// 
