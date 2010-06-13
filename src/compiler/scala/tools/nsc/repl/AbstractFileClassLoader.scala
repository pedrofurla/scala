/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 */

package scala.tools.nsc
package repl

/**
 * A class loader that loads files from an AbstractFile.
 * 
 * @author Lex Spoon
 */
class AbstractFileClassLoader(root: AbstractFile, parent: ClassLoader)
    extends ClassLoader(parent)
    with ScalaClassLoader
{
  override def getBytesForClass(name: String): Array[Byte] = {
    def default   = super.getBytesForClass(name)
    val pathParts = name split "[./]" toList 
    val file      = pathParts.init.foldLeft(root) {
      (f, part) => 
        Option(f.lookupName(part, true)) getOrElse { return default }
    }
    
    file.lookupName(pathParts.last + ".class", false) match {
      case null => default
      case f    => f.toByteArray
    }
  }

  override def findClass(name: String): Class[_] = {
    val bytes = getBytesForClass(name)
    defineClass(name, bytes, 0, bytes.length)
  }
}
