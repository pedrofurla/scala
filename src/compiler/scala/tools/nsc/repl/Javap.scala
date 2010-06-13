/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc
package repl

import sun.tools.javap._
import java.io.ByteArrayInputStream
import util.returning

object Javap {
  // private val fieldNames    = List("showDisassembled", "showLineAndLocal", "showVerbose", "showInternalSigs")
  private val fieldNames    = List("showDisassembled", "showVerbose", "showInternalSigs")
  
  def newEnv = {
    val env = new JavapEnvironment()
    fieldNames foreach { name =>
      val x = classOf[JavapEnvironment] getDeclaredField name
      x setAccessible true
      x.set(env, true)
    }
    env
  }
  
  def apply(name: String): Wrapper = apply(name, ScalaClassLoader.getSystemLoader())
  def apply(name: String, cl: ScalaClassLoader): Wrapper = new Wrapper(name, cl.getBytesForClass(name))
  
  class Wrapper(val className: String, val bytes: Array[Byte]) {
    def mkStream  = new ByteArrayInputStream(bytes)  
    val pw        = new PrintWriter(System.out, true)
    val printer   = new JavapPrinter(mkStream, pw, newEnv)
    import printer._
    
    val cdata       = new ClassData(mkStream)
    def methods     = cdata.getMethods.toList
    def attrs       = cdata.getAttributes.toList
    def inners      = cdata.getInnerClasses.toList
    def sourceName  = strip(cdata.getSourceName())
    
    def showFields()        = printfields()
    def showHeader()        = printclassHeader()
    def showInnerClasses()  = printInnerClasses()
    def showMethods()       = printMethods()
    def showPool()          = printcp()
    
    def show(): Unit = {
      showHeader()
      showPool()
      showMethods()
      showFields()
    }
    
    def show(filt: MethodData => Boolean): Unit = {
      methods filter filt foreach (printer printMethodAttributes _)
    }
    
    private def strip(s: String): String =
      if (s.nonEmpty && s.head == '"' && s.last == '"') s drop 1 dropRight 1 else s
    
    override def toString = "Javap(%s / %s bytes)".format(className, bytes.size)
  }
}
import Javap._

trait Javap {
  self: Interpreter =>
  
  def isClassfileFor(x: AbstractFile, s: String) = x.name endsWith ("$" + s + ".class")
  
  def findVirtualClassFile(s: String) =
    virtualDirectory.toList filter (isClassfileFor(_, s)) sortBy (_.lastModified) lastOption

  def bytesForClassName(s: String): Array[Byte] = {
    val bytes = classLoader.getBytesForClass(s)
    if (bytes.nonEmpty) bytes
    else findVirtualClassFile(s) map (_.toByteArray) getOrElse Array()
  }
  def nonEmptyBytes(s: String) = {
    val bytes = bytesForClassName(s)
    if (bytes.nonEmpty) Some(bytes) else {
      println("Unable to find '%s'.".format(s))
      None
    }
  }
  
  def javapWrapper(className: String): Option[Wrapper] =
    nonEmptyBytes(className) map (xs => new Wrapper(className, xs))

  def javap(className: String): Unit  =
    javapWrapper(className) foreach (_.show())

  def javap(className: String, methodName: String): Unit =
    javap(className, _.getName == methodName)    

  def javap(className: String, f: MethodData => Boolean): Unit =
    javapWrapper(className) foreach (_ show f)
}