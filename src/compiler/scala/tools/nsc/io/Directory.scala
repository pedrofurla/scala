/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.tools.nsc
package io

object Directory {
  import scala.util.Properties.{ tmpDir, userHome, userDir }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def normalizePath(s: String) = Some(apply(Path(s).normalize))
  def Current: Option[Directory]  = if (userDir == "") None else normalizePath(userDir)
  def Home: Option[Directory]     = if (userHome == "") None else normalizePath(userHome)
  def TmpDir: Option[Directory]   = if (tmpDir == "") None else normalizePath(tmpDir)
<<<<<<< HEAD
    
  def apply(path: Path): Directory = path.toDirectory
  
=======

  def apply(path: Path): Directory = path.toDirectory

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Like File.makeTemp but creates a directory instead
  def makeTemp(prefix: String = Path.randomPrefix, suffix: String = null, dir: JFile = null): Directory = {
    val path = File.makeTemp(prefix, suffix, dir)
    path.delete()
    path.createDirectory()
  }
}
import Path._

/** An abstraction for directories.
 *
 *  @author  Paul Phillips
 *  @since   2.8
 */
<<<<<<< HEAD
class Directory(jfile: JFile) extends Path(jfile) {  
=======
class Directory(jfile: JFile) extends Path(jfile) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toAbsolute: Directory = if (isAbsolute) this else super.toAbsolute.toDirectory
  override def toDirectory: Directory = this
  override def toFile: File = new File(jfile)
  override def isValid = jfile.isDirectory() || !jfile.exists()
<<<<<<< HEAD
  override def normalize: Directory = super.normalize.toDirectory  
  
=======
  override def normalize: Directory = super.normalize.toDirectory

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** An iterator over the contents of this directory.
   */
  def list: Iterator[Path] =
    jfile.listFiles match {
      case null   => Iterator.empty
      case xs     => xs.iterator map Path.apply
    }
<<<<<<< HEAD
  
  def dirs: Iterator[Directory] = list collect { case x: Directory => x }
  def files: Iterator[File] = list collect { case x: File => x }
  
=======

  def dirs: Iterator[Directory] = list collect { case x: Directory => x }
  def files: Iterator[File] = list collect { case x: File => x }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def walkFilter(cond: Path => Boolean): Iterator[Path] =
    list filter cond flatMap (_ walkFilter cond)

  def deepDirs: Iterator[Directory] = Path.onlyDirs(deepList())
  def deepFiles: Iterator[File] = Path.onlyFiles(deepList())
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** If optional depth argument is not given, will recurse
   *  until it runs out of contents.
   */
  def deepList(depth: Int = -1): Iterator[Path] =
    if (depth < 0) list ++ (dirs flatMap (_ deepList (depth)))
    else if (depth == 0) Iterator.empty
    else list ++ (dirs flatMap (_ deepList (depth - 1)))
<<<<<<< HEAD
  
  /** An iterator over the directories underneath this directory,
   *  to the (optionally) given depth.
   */
  def subdirs(depth: Int = 1): Iterator[Directory] = 
=======

  /** An iterator over the directories underneath this directory,
   *  to the (optionally) given depth.
   */
  def subdirs(depth: Int = 1): Iterator[Directory] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    deepList(depth) collect { case x: Directory => x }
}
