/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 */

package scala.tools
package partest

import io._
import nsc.io._
import Properties._

trait Config {
  universe: Universe =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val src    = absolutize(srcDir).toDirectory
  lazy val build  = new TestBuild()

  def javaHomeEnv = envOrElse("JAVA_HOME", null)
  def javaCmd     = envOrElse("JAVACMD", "java")
  def javacCmd    = Option(javaHomeEnv) map (x => Path(x) / "bin" / "javac" path) getOrElse "javac"
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Values related to actors.  The timeouts are in seconds.  On a dry
   *  run we only allocate one worker so the output isn't interspersed.
   */
  def workerTimeout   = 3600                                          // 1 hour, probably overly generous
  def numWorkers      = if (isDryRun) 1 else propOrElse("partest.actors", "8").toInt
  def expectedErrors  = propOrElse("partest.errors", "0").toInt
  def poolSize        = (wrapAccessControl(propOrNone("actors.corePoolSize")) getOrElse "16").toInt
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def allScalaFiles = src.deepFiles filter (_ hasExtension "scala")
  def allObjDirs    = src.deepDirs  filter (_ hasExtension "obj")
  def allLogFiles   = src.deepFiles filter (_ hasExtension "log")
  def allClassFiles = src.deepFiles filter (_ hasExtension "class")
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class TestBuild() extends BuildContribution {
    import nsc.util.ClassPath

    /** Scala core libs.
     */
    val library       = pathForComponent("library")
    val compiler      = pathForComponent("compiler")
    val partest       = pathForComponent("partest")
    val scalap        = pathForComponent("scalap", "%s.jar")

    /** Scala supplementary libs - these are not all needed for all build targets,
     *  and some of them are copied inside other jars in later targets.  However quick
     *  for instance cannot be run without some of these.
     */
    val fjbg          = pathForLibrary("fjbg")
    val msil          = pathForLibrary("msil")
    val forkjoin      = pathForLibrary("forkjoin")
    val scalacheck    = pathForLibrary("scalacheck")
<<<<<<< HEAD
    
    /** Other interesting paths.
     */
    val scalaBin      = testBuildDir / "bin"
    
=======

    /** Other interesting paths.
     */
    val scalaBin      = testBuildDir / "bin"

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** A hack for now to get quick running.
     */
    def needsForkJoin = {
      val loader    = nsc.util.ScalaClassLoader.fromURLs(List(library.toURL))
      val fjMarker  = "scala.concurrent.forkjoin.ForkJoinTask"
      val clazz     = loader.tryToLoadClass(fjMarker)
<<<<<<< HEAD
      
      if (clazz.isDefined) debug("Loaded ForkJoinTask OK, don't need jar.")
      else debug("Could not load ForkJoinTask, putting jar on classpath.")
      
      clazz.isEmpty
    }
    lazy val forkJoinPath: List[Path] = if (needsForkJoin) List(forkjoin) else Nil
    
    /** Internal **/
    private def repo  = partestDir.parent.normalize
    
=======

      if (clazz.isDefined) debug("Loaded ForkJoinTask OK, don't need jar.")
      else debug("Could not load ForkJoinTask, putting jar on classpath.")

      clazz.isEmpty
    }
    lazy val forkJoinPath: List[Path] = if (needsForkJoin) List(forkjoin) else Nil

    /** Internal **/
    private def repo  = partestDir.parent.normalize

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def pathForComponent(what: String, jarFormat: String = "scala-%s.jar"): Path = {
      def asDir = testBuildDir / "classes" / what
      def asJar = testBuildDir / "lib" / jarFormat.format(what)

      if (asDir.isDirectory) asDir
      else if (asJar.isFile) asJar
      else ""
    }
    private def pathForLibrary(what: String) = File(repo / "lib" / (what + ".jar"))
  }
<<<<<<< HEAD
  
  def printConfigBanner() = {
    debug("Java VM started with arguments: '%s'" format fromArgs(Process.javaVmArguments))
    debug("System Properties:\n" + util.allPropertiesString())
    
=======

  def printConfigBanner() = {
    debug("Java VM started with arguments: '%s'" format fromArgs(Process.javaVmArguments))
    debug("System Properties:\n" + util.allPropertiesString())

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    normal(configBanner())
  }

  /** Treat an access control failure as None. */
  private def wrapAccessControl[T](body: => Option[T]): Option[T] =
    try body catch { case _: java.security.AccessControlException => None }

  private def configBanner() = {
    val javaBin  = Path(javaHome) / "bin"
    val javaInfoString = "%s (build %s, %s)".format(javaVmName, javaVmVersion, javaVmInfo)

    List(
      "Scala compiler classes in: " + testBuildDir,
      "Scala version is:          " + nsc.Properties.versionMsg,
      "Scalac options are:        " + universe.scalacOpts,
      "Java binaries in:          " + javaBin,
      "Java runtime is:           " + javaInfoString,
      "Java runtime options:      " + (Process.javaVmArguments mkString " "),
      "Javac options are:         " + universe.javacOpts,
      "Java options are:          " + universe.javaOpts,
      "Source directory is:       " + src,
      "Selected categories:       " + (selectedCategories mkString " "),
      ""
    ) mkString "\n"
  }
}
