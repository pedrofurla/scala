/*                     __                                               *\
**     ________ ___   / /  ___     Scala Parallel Testing               **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.tools
package partest
package category

import nsc.io._

trait Runner {
  self: Universe =>

  /** Shootout.
   */
  object Shootout extends DirBasedCategory("shootout") {
    lazy val testSequence: TestSequence = List(compile, run, diff)
<<<<<<< HEAD
    
    override def denotesTest(p: Path)       = isScala(p) && runner(p).isFile
    override def createTest(location: Path) = new ShootoutTest(location.toFile)
    
=======

    override def denotesTest(p: Path)       = isScala(p) && runner(p).isFile
    override def createTest(location: Path) = new ShootoutTest(location.toFile)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    class ShootoutTest(val location: File) extends TestEntity {
      val category = Shootout
      // The files in shootout are very free form, so acknowledge anything close.
      override def acknowledges(p: Path) =
        (p.parent.normalize isSame Shootout.root) && (p.name startsWith label)
<<<<<<< HEAD
      
      private def generated     = File(outDir / "test.scala")
      private def runnerFile    = runner(location)
      override def sourceFiles  = List(generated)
      
=======

      private def generated     = File(outDir / "test.scala")
      private def runnerFile    = runner(location)
      override def sourceFiles  = List(generated)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      override def compile() = {
        trace("generate %s from %s, %s".format(tracePath(generated), tracePath(location), tracePath(runnerFile)))
        // generate source file (even on dry run, we need the path)
        generated.writeAll(location.slurp(), runnerFile.slurp())
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // compile generated file
        super.compile()
      }
    }

    private[Shootout] def runner(p: Path) = p addExtension "runner" toFile
  }
<<<<<<< HEAD
  
  object Scalacheck extends DirBasedCategory("scalacheck") {
    lazy val testSequence: TestSequence = List(compile, run)
    override def createTest(location: Path) = new ScalacheckTest(location)
    
    class ScalacheckTest(val location: Path) extends TestEntity {
      val category = Scalacheck
      
=======

  object Scalacheck extends DirBasedCategory("scalacheck") {
    lazy val testSequence: TestSequence = List(compile, run)
    override def createTest(location: Path) = new ScalacheckTest(location)

    class ScalacheckTest(val location: Path) extends TestEntity {
      val category = Scalacheck

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      import build.{ scalacheck, forkjoin }
      import org.scalacheck.Properties
      import org.scalacheck.Test.{ checkProperties, defaultParams, Result }

      override def classpathPaths = super.classpathPaths ::: List(scalacheck, forkjoin)
      private def arrayURLs = Array(scalacheck, outDir) map (_.toURL)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      /** For reasons I'm not entirely clear on, I've written all this
       *  to avoid a source dependency on scalacheck.
       */
      class ScalacheckClassLoader extends PartestClassLoader(arrayURLs, this.getClass.getClassLoader) {
        type ScalacheckResult = { def passed: Boolean }

        def propCallback(name: String, passed: Int, discarded: Int): Unit = ()
        def testCallback(name: String, result: AnyRef): Unit = ()
<<<<<<< HEAD
        
        val test    = singleton("Test$")
        val params  = apply[AnyRef]("org.scalacheck.Test$", "defaultParams")()
        val result  = apply[Seq[(String, AnyRef)]]("org.scalacheck.Test$", "checkProperties")(test, params, propCallback _, testCallback _)
        
        def allResults() = 
          for ((prop, res) <- result) yield {
            ScalacheckTest.this.trace("%s: %s".format(prop, res))            
            res.asInstanceOf[ScalacheckResult].passed
          }
          
=======

        val test    = singleton("Test$")
        val params  = apply[AnyRef]("org.scalacheck.Test$", "defaultParams")()
        val result  = apply[Seq[(String, AnyRef)]]("org.scalacheck.Test$", "checkProperties")(test, params, propCallback _, testCallback _)

        def allResults() =
          for ((prop, res) <- result) yield {
            ScalacheckTest.this.trace("%s: %s".format(prop, res))
            res.asInstanceOf[ScalacheckResult].passed
          }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        def check() = allResults forall (_ == true)
      }

      override def run() = {
        trace("scalacheck runs via classloader with: %s".format(arrayURLs mkString ", "))
        isDryRun || (new ScalacheckClassLoader check)
      }
    }
  }
<<<<<<< HEAD
  
  object Script extends DirBasedCategory("script") {
    val testSequence: TestSequence = List(exec, diff)    
    override def createTest(location: Path) = new ScriptTest(location)
    
=======

  object Script extends DirBasedCategory("script") {
    val testSequence: TestSequence = List(exec, diff)
    override def createTest(location: Path) = new ScriptTest(location)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    class ScriptTest(val location: Path) extends TestEntity {
      val category    = Script
      val scriptFile  = if (location.isDirectory) location / (label + ".scala") else location
      val argsFile    = withExtension("args").toFile
      def batFile     = scriptFile changeExtension "bat"
      def script      = if (Properties.isWin) batFile else scriptFile

      override def acknowledges(p: Path) = super.acknowledges(p) || (List(argsFile, batFile) exists (_ isSame p))
      override def execCwd = Some(sourcesDir)
      override def argumentsToExec = script.path :: safeArgs(argsFile)
    }
  }
}