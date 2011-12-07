/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 */

package scala.tools
package partest
package category

import nsc.io._
import nsc.reporters._
import nsc.{ Settings, CompilerCommand }
import scala.tools.nsc.interactive.RefinedBuildManager
import util.copyPath

trait Compiler {
  self: Universe =>

  /** Resident Compiler.
   *  $SCALAC -d dir.obj -Xresident -sourcepath . "$@"
   */
  object Res extends DirBasedCategory("res") {
    lazy val testSequence: TestSequence = List(checkFileRequired, compile, diff)
<<<<<<< HEAD
    
    override def denotesTest(p: Path)       = p.isDirectory && resFile(p).isFile
    override def createTest(location: Path) = new ResidentTest(location.toDirectory)
        
    override def createSettings(entity: TestEntity): TestSettings =    
=======

    override def denotesTest(p: Path)       = p.isDirectory && resFile(p).isFile
    override def createTest(location: Path) = new ResidentTest(location.toDirectory)

    override def createSettings(entity: TestEntity): TestSettings =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      returning(super.createSettings(entity)) { settings =>
        settings.resident.value   = true
        settings.sourcepath.value = entity.sourcesDir.path
      }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    class ResidentTest(val location: Directory) extends TestEntity {
      val category = Res
      override def sourcesDir     = categoryDir

<<<<<<< HEAD
      override def acknowledges(p: Path) = 
        super.acknowledges(p) || (resFile(location) isSame p)
      
      private def residentCompilerCommands = safeLines(resFile(location))
      private def compileResident(global: PartestGlobal, lines: List[String]) = {        
=======
      override def acknowledges(p: Path) =
        super.acknowledges(p) || (resFile(location) isSame p)

      private def residentCompilerCommands = safeLines(resFile(location))
      private def compileResident(global: PartestGlobal, lines: List[String]) = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        def printPrompt = global inform "nsc> "
        val results =
          lines map { line =>
            printPrompt
            trace("compile " + line)
            isDryRun || global.partestCompile(toArgs(line) map (categoryDir / _ path), false)
          }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        printPrompt

        /** Note - some res tests are really "neg" style tests, so we can't
         *  use the return value of the compile.  The diff catches failures.
         */
        true  // results forall (_ == true)
      }

      override def compile() = compileResident(newGlobal(Nil)._1, residentCompilerCommands)
    }
    private[Res] def resFile(p: Path) = p.toFile addExtension "res"
  }

  object BuildManager extends DirBasedCategory("buildmanager") {
    lazy val testSequence: TestSequence = List(checkFileRequired, compile, diff)
    override def denotesTest(p: Path) = p.isDirectory && testFile(p).isFile
    override def createTest(location: Path) = new BuildManagerTest(location.toDirectory)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def createSettings(entity: TestEntity): TestSettings =
      returning[TestSettings](super.createSettings(entity)) { settings =>
        settings.Ybuildmanagerdebug.value = true
        settings.sourcepath.value = entity.sourcesDir.path
      }
<<<<<<< HEAD
    
    class PartestBuildManager(settings: Settings, val reporter: ConsoleReporter) extends RefinedBuildManager(settings) {
      def errorFn(msg: String) = Console println msg
      
      override protected def newCompiler(newSettings: Settings) =  
        new BuilderGlobal(newSettings, reporter)
      
      private def filesToSet(pre: String, fs: List[String]): Set[AbstractFile] =
        fs flatMap (s => Option(AbstractFile getFile (Path(settings.sourcepath.value) / s path))) toSet
      
      def buildManagerCompile(line: String): Boolean = {
        val prompt = "builder > "     
        reporter printMessage (prompt + line)
        val command = new CompilerCommand(toArgs(line), settings)
        val files   = filesToSet(settings.sourcepath.value, command.files)
        
=======

    class PartestBuildManager(settings: Settings, val reporter: ConsoleReporter) extends RefinedBuildManager(settings) {
      def errorFn(msg: String) = Console println msg

      override protected def newCompiler(newSettings: Settings) =
        new BuilderGlobal(newSettings, reporter)

      private def filesToSet(pre: String, fs: List[String]): Set[AbstractFile] =
        fs flatMap (s => Option(AbstractFile getFile (Path(settings.sourcepath.value) / s path))) toSet

      def buildManagerCompile(line: String): Boolean = {
        val prompt = "builder > "
        reporter printMessage (prompt + line)
        val command = new CompilerCommand(toArgs(line), settings)
        val files   = filesToSet(settings.sourcepath.value, command.files)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        update(files, Set.empty)
        true
      }
    }

    private[BuildManager] def testFile(p: Path) = (p / p.name addExtension "test").toFile

    class BuildManagerTest(val location: Directory) extends TestEntity {
      val category = BuildManager
<<<<<<< HEAD
      
      override def sourcesDir     = outDir
      override def sourceFiles    = Path onlyFiles (location walkFilter (_ != changesDir) filter isJavaOrScala toList)
      override def checkFile      = File(location / location.name addExtension "check")
      
      override def acknowledges(p: Path) = super.acknowledges(p) || (p isSame testFile(location))
      
      def buildManagerCommands  = safeLines(testFile(location))
      def changesDir            = Directory(location / (location.name + ".changes"))
      
      override def compile() = {
        val settings = createSettings(this)
        val pbm      = new PartestBuildManager(settings, newReporter(settings))
        
=======

      override def sourcesDir     = outDir
      override def sourceFiles    = Path onlyFiles (location walkFilter (_ != changesDir) filter isJavaOrScala toList)
      override def checkFile      = File(location / location.name addExtension "check")

      override def acknowledges(p: Path) = super.acknowledges(p) || (p isSame testFile(location))

      def buildManagerCommands  = safeLines(testFile(location))
      def changesDir            = Directory(location / (location.name + ".changes"))

      override def compile() = {
        val settings = createSettings(this)
        val pbm      = new PartestBuildManager(settings, newReporter(settings))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // copy files
        for (source <- sourceFiles) {
          val target = outDir / (location.normalize relativize source)
          copyPath(source, target.toFile)
        }
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        def runUpdate(line: String) = {
          val Array(srcName, replacement) = line split "=>"
          copyPath(File(changesDir / replacement), File(outDir / srcName))
        }
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        def sendCommand(line: String): Boolean = {
          val compileRegex  = """^>>compile (.*)$""".r
          val updateRegex   = """^>>update\s+(.*)""".r
          trace("send: " + (line drop 2))
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          isDryRun || (line match {
            case compileRegex(xs)   => pbm.buildManagerCompile(xs)
            case updateRegex(line)  => runUpdate(line)
          })
        }
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // send each line to the build manager
        buildManagerCommands forall sendCommand
      }
    }
  }
}

