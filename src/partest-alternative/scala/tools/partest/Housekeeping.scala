/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 */

package scala.tools
package partest

import scala.util.control.Exception.catching
import util._
import nsc.io._
import Process.runtime
import Properties._

/** An agglomeration of code which is low on thrills.  Hopefully
 *  it operates so quietly in the background that you never have to
 *  look at this file.
 */
trait Housekeeping {
  self: Universe =>

  /** Orderly shutdown on ctrl-C. */
  @volatile private var _shuttingDown = false
  protected def setShuttingDown() = {
    /** Whatever we want to do as shutdown begins goes here. */
    if (!_shuttingDown) {
      warning("Received shutdown signal, partest is cleaning up...\n")
      _shuttingDown = true
    }
  }
  def isShuttingDown = _shuttingDown
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Execute some code with a shutdown hook in place.  This is
   *  motivated by the desire not to leave the filesystem full of
   *  junk when someone ctrl-Cs a test run.
   */
  def withShutdownHook[T](hook: => Unit)(body: => T): Option[T] =
    /** Java doesn't like it if you keep adding and removing shutdown
     *  hooks after shutdown has begun, so we trap the failure.
     */
    catching(classOf[IllegalStateException]) opt {
<<<<<<< HEAD
      val t = new Thread() { 
        override def run() = {
          setShuttingDown()
          hook 
        }
      }
      runtime addShutdownHook t
      
=======
      val t = new Thread() {
        override def run() = {
          setShuttingDown()
          hook
        }
      }
      runtime addShutdownHook t

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      try body
      finally runtime removeShutdownHook t
    }

  /** Search for a directory, possibly given only a name, by starting
   *  at the current dir and walking upward looking for it at each level.
   */
  protected def searchForDir(name: String): Directory = {
<<<<<<< HEAD
    val result = Path(name) ifDirectory (x => x.normalize) orElse {    
      val cwd = Directory.Current getOrElse error("user.dir property not set")
      val dirs = cwd :: cwd.parents map (_ / name)
    
      Path onlyDirs dirs map (_.normalize) headOption
    }
    
    result getOrElse error("Fatal: could not find directory '%s'" format name)
  }
  
=======
    val result = Path(name) ifDirectory (x => x.normalize) orElse {
      val cwd = Directory.Current getOrElse error("user.dir property not set")
      val dirs = cwd :: cwd.parents map (_ / name)

      Path onlyDirs dirs map (_.normalize) headOption
    }

    result getOrElse error("Fatal: could not find directory '%s'" format name)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Paths we ignore for most purposes.
   */
  def ignorePath(x: Path) = {
    (x.name startsWith ".") ||
    (x.isDirectory && ((x.name == "lib") || x.hasExtension("obj", "svn")))
  }
  /** Make a possibly relative path absolute using partestDir as the base.
   */
  def absolutize(path: String) = Path(path) toAbsoluteWithRoot partestDir
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Go on a deleting binge.
   */
  def cleanupAll() {
    if (isNoCleanup)
      return
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val (dirCount, fileCount) = (cleanupObjDirs(), cleanupLogs() + cleanupJunk())
    if (dirCount + fileCount > 0)
      normal("Cleaned up %d directories and %d files.\n".format(dirCount, fileCount))
  }

  def cleanupObjDirs()  = countTrue(allObjDirs collect { case x if x.exists => x.deleteRecursively() })
  def cleanupJunk()     = countTrue(allClassFiles collect { case x if x.exists => x.delete() })
  def cleanupLogs()     = countTrue(allLogFiles collect { case x if x.exists => x.delete() })
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Look through every file in the partest directory and ask around
   *  to make sure someone knows him.  Complain about strangers.
   */
  def validateAll() {
    def denotesTest(p: Path)  = allCategories exists (_ denotesTest p)
    def isMSILcheck(p: Path)  = p.name endsWith "-msil.check"
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def analyzeCategory(cat: DirBasedCategory) = {
      val allTests    = cat.enumerate
      val otherPaths  = cat.root walkFilter (x => !ignorePath(x)) filterNot (cat denotesTest _) filterNot isMSILcheck toList
      val count       = otherPaths.size
<<<<<<< HEAD
      
      println("Validating %d non-test paths in %s.".format(count, cat.kind))
      
=======

      println("Validating %d non-test paths in %s.".format(count, cat.kind))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      for (path <- otherPaths) {
        (allTests find (_ acknowledges path)) match {
          case Some(test)   => if (isVerbose) println("  OK: '%s' is claimed by '%s'".format(path, test.label))
          case _            => println(">> Unknown path '%s'" format path)
        }
      }
    }
<<<<<<< HEAD
    
    allCategories collect { case x: DirBasedCategory => analyzeCategory(x) }
  }
  
=======

    allCategories collect { case x: DirBasedCategory => analyzeCategory(x) }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait TestHousekeeping {
    self: TestEntity =>

    /** Calculating derived files.  Given a test like
<<<<<<< HEAD
     *    files/run/foo.scala  or  files/run/foo/ 
=======
     *    files/run/foo.scala  or  files/run/foo/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *  This creates paths like foo.check, foo.flags, etc.
     */
    def withExtension(extension: String) = categoryDir / "%s.%s".format(label, extension)

    /** True for a path if this test acknowledges it belongs to this test.
     *  Overridden by some categories.
     */
    def acknowledges(path: Path): Boolean = {
      val loc = location.normalize
      val knownPaths = List(scalaOptsFile, javaOptsFile, commandFile, logFile, checkFile) ++ jarsInTestDir
      def isContainedSource = location.isDirectory && isJavaOrScala(path) && (path.normalize startsWith loc)
<<<<<<< HEAD
      
      (knownPaths exists (_ isSame path)) || isContainedSource
    }
    
=======

      (knownPaths exists (_ isSame path)) || isContainedSource
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** This test "responds to" this String.  This could mean anything -- it's a
     *  way of specifying ad-hoc collections of tests to exercise only a subset of tests.
     *  At present it looks for the given String in all the test sources.
     */
    def respondsToString(str: String) = containsString(str)
    def containsString(str: String)   = {
      debug("Checking %s for \"%s\"".format(sourceFiles mkString ", ", str))
      sourceFiles map safeSlurp exists (_ contains str)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def possiblyTimed[T](body: => T): T = {
      if (isStats) timed(recordTestTiming(label, _))(body)
      else body
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def prepareForTestRun() = {
      // make sure we have a clean slate
      deleteLog(force = true)
      if (outDir.exists)
        outDir.deleteRecursively()
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // recreate object dir
      outDir createDirectory true
    }
    def deleteOutDir() = outDir.deleteRecursively()
    def deleteShutdownHook() = { debug("Shutdown hook deleting " + outDir) ; deleteOutDir() }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    protected def runWrappers[T](body: => T): Option[T] = {
      prepareForTestRun()

      withShutdownHook(deleteShutdownHook()) {
        loggingOutAndErr {
          val result = possiblyTimed { body }
          if (!isNoCleanup)
            deleteOutDir()

          result
        }
      }
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString = location.path
    override def equals(other: Any) = other match {
      case x: TestEntity  => location.normalize == x.location.normalize
      case _              => false
    }
    override def hashCode = location.normalize.hashCode
  }

  private def countTrue(f: => Iterator[Boolean]) = f filter (_ == true) length
}