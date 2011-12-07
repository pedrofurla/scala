/*                     __                                               *\
**     ________ ___   / /  ___     Scala Ant Tasks                      **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD

package scala.tools.ant
package sabbus

import java.io.File
import java.io.FileWriter
=======
package scala.tools.ant
package sabbus

import java.io.{ File, FileWriter }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import org.apache.tools.ant.Project
import org.apache.tools.ant.taskdefs.Java
import org.apache.tools.ant.util.{ GlobPatternMapper, SourceFileScanner }
import scala.tools.nsc.io
import scala.tools.nsc.util.ScalaClassLoader

<<<<<<< HEAD
class ScalacFork extends ScalaMatchingTask with ScalacShared with TaskArgs {
  private def originOfThis: String =
    ScalaClassLoader.originOfClass(classOf[ScalacFork]) map (_.toString) getOrElse "<unknown>"
  
=======
/** An Ant task to compile with the new Scala compiler (NSC).
 *
 *  This task can take the following parameters as attributes:
 *  - `srcdir` (mandatory),
 *  - `failonerror`,
 *  - `timeout`,
 *  - `jvmargs`,
 *  - `argfile`,
 *  - `params`.
 *
 *  It also takes the following parameters as nested elements:
 *  - `src` (for `srcdir`),
 *  - `classpath`,
 *  - `sourcepath`,
 *  - `bootclasspath`,
 *  - `extdirs`,
 *  - `compilerarg`.
 *
 *  @author Gilles Dubochet
 */
class ScalacFork extends ScalaMatchingTask with ScalacShared with TaskArgs {

  private def originOfThis: String =
    ScalaClassLoader.originOfClass(classOf[ScalacFork]) map (_.toString) getOrElse "<unknown>"

  /** Sets the `srcdir` attribute. Used by [[http://ant.apache.org Ant]].
   *  @param input The value of `sourceDir`. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setSrcdir(input: File) {
    sourceDir = Some(input)
  }

<<<<<<< HEAD
=======
  /** Sets the `failonerror` attribute. Used by [[http://ant.apache.org Ant]].
   *  @param input The value of `failOnError`. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setFailOnError(input: Boolean) {
    failOnError = input
  }

<<<<<<< HEAD
=======
  /** Sets the `timeout` attribute. Used by [[http://ant.apache.org Ant]].
   *  @param input The value of `timeout`. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setTimeout(input: Long) {
    timeout = Some(input)
  }

<<<<<<< HEAD
=======
  /** Sets the `jvmargs` attribute. Used by [[http://ant.apache.org Ant]].
   *  @param input The value of `jvmArgs`. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setJvmArgs(input: String) {
    jvmArgs = Some(input)
  }

<<<<<<< HEAD
=======
  /** Sets the `argfile` attribute. Used by [[http://ant.apache.org Ant]].
   *  @param input The value of `argfile`. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setArgfile(input: File) {
    argfile = Some(input)
  }

  private var sourceDir: Option[File] = None
  private var failOnError: Boolean = true
  private var timeout: Option[Long] = None
  private var jvmArgs: Option[String] = None
  private var argfile: Option[File] = None
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def createMapper() = {
    val mapper = new GlobPatternMapper()
    val extension = if (isMSIL) "*.msil" else "*.class"
    mapper setTo extension
    mapper setFrom "*.scala"
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    mapper
  }

  override def execute() {
    def plural(x: Int) = if (x > 1) "s" else ""
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    log("Executing ant task scalacfork, origin: %s".format(originOfThis), Project.MSG_VERBOSE)

    val compilerPath = this.compilerPath getOrElse sys.error("Mandatory attribute 'compilerpath' is not set.")
    val sourceDir = this.sourceDir getOrElse sys.error("Mandatory attribute 'srcdir' is not set.")
    val destinationDir = this.destinationDir getOrElse sys.error("Mandatory attribute 'destdir' is not set.")
<<<<<<< HEAD
    
    val settings = new Settings
    settings.d = destinationDir
    
    compTarget foreach (settings.target = _)
    compilationPath foreach (settings.classpath = _)
    sourcePath foreach (settings.sourcepath = _)
    params foreach (settings.more = _)
    
    if (isMSIL)
      settings.sourcedir = sourceDir
    
    val mapper = createMapper()
    
=======

    val settings = new Settings
    settings.d = destinationDir

    compTarget foreach (settings.target = _)
    compilationPath foreach (settings.classpath = _)
    sourcePath foreach (settings.sourcepath = _)
    settings.extraParams = extraArgsFlat

    if (isMSIL)
      settings.sourcedir = sourceDir

    val mapper = createMapper()

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val includedFiles: Array[File] =
      new SourceFileScanner(this).restrict(
        getDirectoryScanner(sourceDir).getIncludedFiles,
        sourceDir,
        destinationDir,
        mapper
      ) map (x => new File(sourceDir, x))
<<<<<<< HEAD
    
    /** Nothing to do. */
    if (includedFiles.isEmpty && argfile.isEmpty)
      return
      
    if (includedFiles.nonEmpty)
      log("Compiling %d file%s to %s".format(includedFiles.size, plural(includedFiles.size), destinationDir))
    
    argfile foreach (x => log("Using argfile file: @" + x))
    
=======

    /** Nothing to do. */
    if (includedFiles.isEmpty && argfile.isEmpty)
      return

    if (includedFiles.nonEmpty)
      log("Compiling %d file%s to %s".format(includedFiles.size, plural(includedFiles.size), destinationDir))

    argfile foreach (x => log("Using argfile file: @" + x))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val java = new Java(this)  // set this as owner
    java setFork true
    // using 'setLine' creates multiple arguments out of a space-separated string
    jvmArgs foreach (java.createJvmarg() setLine _)
    timeout foreach (java setTimeout _)
<<<<<<< HEAD
    
    java setClasspath compilerPath
    java setClassname MainClass

    // dump the arguments to a file and do "java @file"
    val tempArgFile = io.File.makeTemp("scalacfork")
    val tokens = settings.toArgs ++ (includedFiles map (_.getPath))
    tempArgFile writeAll (tokens mkString " ")
    
=======

    java setClasspath compilerPath
    java setClassname MainClass

    // Encode scalac/javac args for use in a file to be read back via "@file.txt"
    def encodeScalacArgsFile(t: Traversable[String]) = t map { s =>
      if(s.find(c => c <= ' ' || "\"'\\".contains(c)).isDefined)
        "\"" + s.flatMap(c => (if(c == '"' || c == '\\') "\\" else "") + c ) + "\""
      else s
    } mkString "\n"

    // dump the arguments to a file and do "java @file"
    val tempArgFile = io.File.makeTemp("scalacfork")
    val tokens = settings.toArgs ++ (includedFiles map (_.getPath))
    tempArgFile writeAll encodeScalacArgsFile(tokens)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val paths = List(Some(tempArgFile.toAbsolute.path), argfile).flatten map (_.toString)
    val res = execWithArgFiles(java, paths)

    if (failOnError && res != 0)
      sys.error("Compilation failed because of an internal compiler error;"+
            " see the error output for details.")
  }
}
