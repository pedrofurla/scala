/* NSC -- new Scala compiler
 * Copyright 2006-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package util

import java.net.{ URL, MalformedURLException }
<<<<<<< HEAD
import scala.util.Properties._
=======
import scala.tools.reflect.WrappedProperties.AccessControl
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import nsc.{ Settings, GenericRunnerSettings }
import nsc.util.{ ClassPath, JavaClassPath, ScalaClassLoader }
import nsc.io.{ File, Directory, Path, AbstractFile }
import ClassPath.{ JavaContext, DefaultJavaContext, join, split }
import PartialFunction.condOpt

// Loosely based on the draft specification at:
// https://wiki.scala-lang.org/display/SW/Classpath

object PathResolver {
<<<<<<< HEAD
=======
  // Imports property/environment functions which suppress
  // security exceptions.
  import AccessControl._

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def firstNonEmpty(xs: String*)            = xs find (_ != "") getOrElse ""

  /** Map all classpath elements to absolute paths and reconstruct the classpath.
    */
  def makeAbsolute(cp: String) = ClassPath.map(cp, x => Path(x).toAbsolute.path)

  /** pretty print class path */
  def ppcp(s: String) = split(s) match {
    case Nil      => ""
    case Seq(x)   => x
    case xs       => xs map ("\n" + _) mkString
  }
<<<<<<< HEAD
  
  /** Values found solely by inspecting environment or property variables.
   */
  object Environment {
    private def searchForBootClasspath = {
      import scala.collection.JavaConversions._
      System.getProperties find (_._1 endsWith ".boot.class.path") map (_._2) getOrElse ""
    }
=======

  /** Values found solely by inspecting environment or property variables.
   */
  object Environment {
    private def searchForBootClasspath = (
      systemProperties find (_._1 endsWith ".boot.class.path") map (_._2) getOrElse ""
    )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    /** Environment variables which java pays attention to so it
     *  seems we do as well.
     */
    def classPathEnv        =  envOrElse("CLASSPATH", "")
    def sourcePathEnv       =  envOrElse("SOURCEPATH", "")
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def javaBootClassPath   = propOrElse("sun.boot.class.path", searchForBootClasspath)
    def javaExtDirs         = propOrEmpty("java.ext.dirs")
    def scalaHome           = propOrEmpty("scala.home")
    def scalaExtDirs        = propOrEmpty("scala.ext.dirs")

    /** The java classpath and whether to use it. */
    def javaUserClassPath   = propOrElse("java.class.path", "")
    def useJavaClassPath    = propOrFalse("scala.usejavacp")
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString = """
      |object Environment {
      |  scalaHome          = %s (useJavaClassPath = %s)
      |  javaBootClassPath  = <%d chars>
      |  javaExtDirs        = %s
      |  javaUserClassPath  = %s
      |  scalaExtDirs       = %s
      |}""".trim.stripMargin.format(
        scalaHome, useJavaClassPath,
        javaBootClassPath.length,
        ppcp(javaExtDirs),
        ppcp(javaUserClassPath),
        ppcp(scalaExtDirs)
      )
  }
<<<<<<< HEAD
  
  /** Default values based on those in Environment as interpreted according
   *  to the path resolution specification.
   */
  object Defaults {    
    /* Against my better judgment, giving in to martin here and allowing
     * CLASSPATH as the default if no -cp is given.  Only if there is no
     * command line option or environment variable is "." used.
     */
    def scalaUserClassPath  = firstNonEmpty(Environment.classPathEnv, ".")
    def scalaSourcePath     = Environment.sourcePathEnv

=======

  /** Default values based on those in Environment as interpreted according
   *  to the path resolution specification.
   */
  object Defaults {
    def scalaSourcePath   = Environment.sourcePathEnv
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def javaBootClassPath = Environment.javaBootClassPath
    def javaUserClassPath = Environment.javaUserClassPath
    def javaExtDirs       = Environment.javaExtDirs
    def useJavaClassPath  = Environment.useJavaClassPath

    def scalaHome         = Environment.scalaHome
    def scalaHomeDir      = Directory(scalaHome)
    def scalaHomeExists   = scalaHomeDir.isDirectory
    def scalaLibDir       = Directory(scalaHomeDir / "lib")
    def scalaClassesDir   = Directory(scalaHomeDir / "classes")
<<<<<<< HEAD
    
    def scalaLibAsJar     = File(scalaLibDir / "scala-library.jar")
    def scalaLibAsDir     = Directory(scalaClassesDir / "library")
    
=======

    def scalaLibAsJar     = File(scalaLibDir / "scala-library.jar")
    def scalaLibAsDir     = Directory(scalaClassesDir / "library")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def scalaLibDirFound: Option[Directory] =
      if (scalaLibAsJar.isFile) Some(scalaLibDir)
      else if (scalaLibAsDir.isDirectory) Some(scalaClassesDir)
      else None
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def scalaLibFound =
      if (scalaLibAsJar.isFile) scalaLibAsJar.path
      else if (scalaLibAsDir.isDirectory) scalaLibAsDir.path
      else ""
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // XXX It must be time for someone to figure out what all these things
    // are intended to do.  This is disabled here because it was causing all
    // the scala jars to end up on the classpath twice: one on the boot
    // classpath as set up by the runner (or regular classpath under -nobootcp)
    // and then again here.
    def scalaBootClassPath  = ""
    // scalaLibDirFound match {
    //   case Some(dir) if scalaHomeExists =>
    //     val paths = ClassPath expandDir dir.path
    //     join(paths: _*)
    //   case _                            => ""
    // }

    def scalaExtDirs = Environment.scalaExtDirs

    def scalaPluginPath = (scalaHomeDir / "misc" / "scala-devel" / "plugins").path

    override def toString = """
      |object Defaults {
      |  scalaHome            = %s
      |  javaBootClassPath    = %s
      |  scalaLibDirFound     = %s
      |  scalaLibFound        = %s
      |  scalaBootClassPath   = %s
      |  scalaPluginPath      = %s
      |}""".trim.stripMargin.format(
        scalaHome,
        ppcp(javaBootClassPath),
        scalaLibDirFound, scalaLibFound,
        ppcp(scalaBootClassPath), ppcp(scalaPluginPath)
      )
  }

  def fromPathString(path: String, context: JavaContext = DefaultJavaContext): JavaClassPath = {
    val s = new Settings()
    s.classpath.value = path
    new PathResolver(s, context) result
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** With no arguments, show the interesting values in Environment and Defaults.
   *  If there are arguments, show those in Calculated as if those options had been
   *  given to a scala runner.
   */
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println(Environment)
      println(Defaults)
    }
    else {
      val settings = new Settings()
      val rest = settings.processArguments(args.toList, false)._2
      val pr = new PathResolver(settings)
      println(" COMMAND: 'scala %s'".format(args.mkString(" ")))
      println("RESIDUAL: 'scala %s'\n".format(rest.mkString(" ")))
      pr.result.show
    }
  }
}
import PathResolver.{ Defaults, Environment, firstNonEmpty, ppcp }

class PathResolver(settings: Settings, context: JavaContext) {
  def this(settings: Settings) = this(settings, if (settings.inline.value) new JavaContext else DefaultJavaContext)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def cmdLineOrElse(name: String, alt: String) = {
    (commandLineFor(name) match {
      case Some("") => None
      case x        => x
    }) getOrElse alt
  }

  private def commandLineFor(s: String): Option[String] = condOpt(s) {
    case "javabootclasspath"  => settings.javabootclasspath.value
    case "javaextdirs"        => settings.javaextdirs.value
    case "bootclasspath"      => settings.bootclasspath.value
    case "extdirs"            => settings.extdirs.value
    case "classpath" | "cp"   => settings.classpath.value
    case "sourcepath"         => settings.sourcepath.value
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Calculated values based on any given command line options, falling back on
   *  those in Defaults.
   */
  object Calculated {
<<<<<<< HEAD
    def scalaHome           = Defaults.scalaHome    
=======
    def scalaHome           = Defaults.scalaHome
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def useJavaClassPath    = settings.usejavacp.value || Defaults.useJavaClassPath
    def javaBootClassPath   = cmdLineOrElse("javabootclasspath", Defaults.javaBootClassPath)
    def javaExtDirs         = cmdLineOrElse("javaextdirs", Defaults.javaExtDirs)
    def javaUserClassPath   = if (useJavaClassPath) Defaults.javaUserClassPath else ""
    def scalaBootClassPath  = cmdLineOrElse("bootclasspath", Defaults.scalaBootClassPath)
    def scalaExtDirs        = cmdLineOrElse("extdirs", Defaults.scalaExtDirs)
<<<<<<< HEAD
    def userClassPath       = cmdLineOrElse("classpath", Defaults.scalaUserClassPath)
    def sourcePath          = cmdLineOrElse("sourcepath", Defaults.scalaSourcePath)
    
=======
    def sourcePath          = cmdLineOrElse("sourcepath", Defaults.scalaSourcePath)

    /** Against my better judgment, giving in to martin here and allowing
     *  CLASSPATH to be used automatically.  So for the user-specified part
     *  of the classpath:
     *
     *  - If -classpath or -cp is given, it is that
     *  - Otherwise, if CLASSPATH is set, it is that
     *  - If neither of those, then "." is used.
     */
    def userClassPath = (
      if (!settings.classpath.isDefault)
        settings.classpath.value
      else sys.env.getOrElse("CLASSPATH", ".")
    )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    import context._

    // Assemble the elements!
    def basis = List[Traversable[ClassPath[AbstractFile]]](
      classesInPath(javaBootClassPath),             // 1. The Java bootstrap class path.
      contentsOfDirsInPath(javaExtDirs),            // 2. The Java extension class path.
      classesInExpandedPath(javaUserClassPath),     // 3. The Java application class path.
      classesInPath(scalaBootClassPath),            // 4. The Scala boot class path.
      contentsOfDirsInPath(scalaExtDirs),           // 5. The Scala extension class path.
      classesInExpandedPath(userClassPath),         // 6. The Scala application class path.
      sourcesInPath(sourcePath)                     // 7. The Scala source path.
    )
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    lazy val containers = basis.flatten.distinct

    override def toString = """
      |object Calculated {
      |  scalaHome            = %s
      |  javaBootClassPath    = %s
      |  javaExtDirs          = %s
      |  javaUserClassPath    = %s
      |    useJavaClassPath   = %s
      |  scalaBootClassPath   = %s
      |  scalaExtDirs         = %s
      |  userClassPath        = %s
      |  sourcePath           = %s
      |}""".trim.stripMargin.format(
        scalaHome,
        ppcp(javaBootClassPath), ppcp(javaExtDirs), ppcp(javaUserClassPath),
        useJavaClassPath,
        ppcp(scalaBootClassPath), ppcp(scalaExtDirs), ppcp(userClassPath),
        ppcp(sourcePath)
      )
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def containers = Calculated.containers

  lazy val result = {
    val cp = new JavaClassPath(containers.toIndexedSeq, context)
    if (settings.Ylogcp.value) {
      Console.println("Classpath built from " + settings.toConciseString)
      Console.println("Defaults: " + PathResolver.Defaults)
      Console.println("Calculated: " + Calculated)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val xs = (Calculated.basis drop 2).flatten.distinct
      println("After java boot/extdirs classpath has %d entries:" format xs.size)
      xs foreach (x => println("  " + x))
    }
    cp
  }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def asURLs = result.asURLs
}
