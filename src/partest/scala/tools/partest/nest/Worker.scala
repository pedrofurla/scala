/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author Philipp Haller
 */

<<<<<<< HEAD
// $Id$

=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.partest
package nest

import java.io._
import java.net.URL
import java.util.{ Timer, TimerTask }

<<<<<<< HEAD
import scala.util.Properties.{ isWin }
=======
import scala.tools.nsc.Properties.{ jdkHome, javaHome, propOrElse }
import scala.util.Properties.{ envOrElse, isWin }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.tools.nsc.{ Settings, CompilerCommand, Global }
import scala.tools.nsc.io.{ AbstractFile, PlainFile, Path, Directory, File => SFile }
import scala.tools.nsc.reporters.ConsoleReporter
import scala.tools.nsc.util.{ ClassPath, FakePos, ScalaClassLoader, stackTraceString }
import ClassPath.{ join, split }

import scala.actors.{ Actor, Exit, TIMEOUT }
import scala.actors.Actor._
import scala.tools.scalap.scalax.rules.scalasig.ByteCode
import scala.collection.{ mutable, immutable }
import scala.tools.nsc.interactive.{ BuildManager, RefinedBuildManager }
import scala.sys.process._

case class RunTests(kind: String, files: List[File])
case class Results(results: Map[String, Int])

class LogContext(val file: File, val writers: Option[(StringWriter, PrintWriter)])

object LogContext {
  def apply(file: File, swr: StringWriter, wr: PrintWriter): LogContext = {
    require (file != null)
    new LogContext(file, Some((swr, wr)))
  }
  def apply(file: File): LogContext = new LogContext(file, None)
}

abstract class TestResult {
  def file: File
}
case class Result(override val file: File, context: LogContext) extends TestResult
case class Timeout(override val file: File) extends TestResult

class ScalaCheckFileManager(val origmanager: FileManager) extends FileManager {
  def testRootDir: Directory = origmanager.testRootDir
  def testRootPath: String = origmanager.testRootPath

  var JAVACMD: String = origmanager.JAVACMD
  var JAVAC_CMD: String = origmanager.JAVAC_CMD

  var CLASSPATH: String = join(origmanager.CLASSPATH, PathSettings.scalaCheck.path)
  var LATEST_LIB: String = origmanager.LATEST_LIB
}

object Output {
  def init() {
    System.setOut(outRedirect)
    System.setErr(errRedirect)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  import scala.util.DynamicVariable
  private def out = java.lang.System.out
  private def err = java.lang.System.err
  private val redirVar = new DynamicVariable[Option[PrintStream]](None)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class Redirecter(stream: PrintStream) extends PrintStream(new OutputStream {
    def write(b: Int) = withStream(_ write b)

    private def withStream(f: PrintStream => Unit) = f(redirVar.value getOrElse stream)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def write(b: Array[Byte]) = withStream(_ write b)
    override def write(b: Array[Byte], off: Int, len: Int) = withStream(_.write(b, off, len))
    override def flush = withStream(_.flush)
    override def close = withStream(_.close)
  })
<<<<<<< HEAD
  
  object outRedirect extends Redirecter(out)
  
  object errRedirect extends Redirecter(err)
  
=======

  object outRedirect extends Redirecter(out)

  object errRedirect extends Redirecter(err)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // this supports thread-safe nested output redirects
  def withRedirected[T](newstream: PrintStream)(func: => T): T = {
    // note down old redirect destination
    // this may be None in which case outRedirect and errRedirect print to stdout and stderr
    val saved = redirVar.value
    // set new redirecter
    // this one will redirect both out and err to newstream
    redirVar.value = Some(newstream)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    try func
    finally {
      newstream.flush()
      redirVar.value = saved
    }
  }
<<<<<<< HEAD
}  
=======
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0


class Worker(val fileManager: FileManager, params: TestRunParams) extends Actor {
  import fileManager._
<<<<<<< HEAD
  
  val scalaCheckFileManager = new ScalaCheckFileManager(fileManager)
  var reporter: ConsoleReporter = _
  val timer = new Timer

  val javacCmd = if ((fileManager.JAVAC_CMD.indexOf("${env.JAVA_HOME}") != -1) ||
                     fileManager.JAVAC_CMD.equals("/bin/javac") ||
                     fileManager.JAVAC_CMD.equals("\\bin\\javac")) "javac"
                 else
                   fileManager.JAVAC_CMD

=======

  val scalaCheckFileManager = new ScalaCheckFileManager(fileManager)
  var reporter: ConsoleReporter = _
  val timer    = new Timer
  val javaCmd  = propOrElse("partest.javacmd", Path(javaHome) / "bin" / "java" path)
  val javacCmd = propOrElse("partest.javac_cmd", Path(jdkHome) / "bin" / "javac" path)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  def cancelTimerTask() = if (currentTimerTask != null) currentTimerTask.cancel()
  def updateTimerTask(body: => Unit) = {
    cancelTimerTask()
    currentTimerTask = new KickableTimerTask(body)
<<<<<<< HEAD
    timer.schedule(currentTimerTask, fileManager.oneTestTimeout)    
=======
    timer.schedule(currentTimerTask, fileManager.oneTestTimeout)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  class KickableTimerTask(body: => Unit) extends TimerTask {
    def run() = body
    def kick() = {
      cancel()
      body
<<<<<<< HEAD
    }    
=======
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  /** Formerly deeper inside, these next few things are now promoted outside so
   *  I can see what they're doing when the world comes to a premature stop.
   */
  private var filesRemaining: List[File] = Nil
  private val toDelete       = new mutable.HashSet[File]
  private val status         = new mutable.HashMap[String, Int]

  private var currentTimerTask: KickableTimerTask = _
  private var currentFileStart: Long = System.currentTimeMillis
  private var currentTestFile: File = _
  private var kind: String = ""
  private def fileBase = basename(currentTestFile.getName)
<<<<<<< HEAD
  
  private def compareFiles(f1: File, f2: File): String =
    try fileManager.compareFiles(f1, f2)
    catch { case t => t.toString }
  
  // maps canonical file names to the test result (0: OK, 1: FAILED, 2: TIMOUT)
  private def updateStatus(key: String, num: Int) = status(key) = num
  
=======

  private def compareFiles(f1: File, f2: File): String =
    try fileManager.compareFiles(f1, f2)
    catch { case t => t.toString }

  // maps canonical file names to the test result (0: OK, 1: FAILED, 2: TIMOUT)
  private def updateStatus(key: String, num: Int) = status(key) = num

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def cleanup() {
    // keep output directories under debug
    if (!isPartestDebug)
      toDelete foreach (_.deleteRecursively())

    toDelete.clear()
  }
  sys addShutdownHook cleanup()
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def resetAll() {
    cancelTimerTask()
    filesRemaining = Nil
    cleanup()
    status.clear()
    currentTestFile = null
    currentTimerTask = null
  }

  def currentFileElapsed = (System.currentTimeMillis - currentFileStart) / 1000
  def forceTimeout() = {
    println("Let's see what them threads are doing before I kill that test.")
    sys.allThreads foreach { t =>
      println(t)
      t.getStackTrace foreach println
      println("")
    }
    currentTimerTask.kick()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** This does something about absolute paths and file separator
   *  chars before diffing.
   */
  //
  private def replaceSlashes(dir: File, s: String): String = {
    val base = (dir.getAbsolutePath + File.separator).replace('\\', '/')
    s.replace('\\', '/').replaceAll("""\Q%s\E""" format base, "")
  }

<<<<<<< HEAD
  private def currentFileString = {    
=======
  private def currentFileString = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    "Current test file is: %s\n  Started: %s (%s seconds ago)\n  Current time: %s".format(
      currentTestFile,
      new java.util.Date(currentFileStart),
      currentFileElapsed,
      new java.util.Date()
    )
  }
  private def getNextFile(): File = {
    if (filesRemaining.isEmpty) {
      currentTestFile = null
    }
    else {
      currentTestFile = filesRemaining.head
      filesRemaining = filesRemaining.tail
      currentFileStart = System.currentTimeMillis
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    currentTestFile
  }

  override def toString = (
    ">> Partest Worker in state " + getState + ":\n" +
    currentFileString + "\n" +
<<<<<<< HEAD
    "There are " + filesRemaining.size + " files remaining:\n" + 
=======
    "There are " + filesRemaining.size + " files remaining:\n" +
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    "\nstatus hashmap contains " + status.size + " entries:\n" +
    status.toList.map(x => "  " + x._1 + " -> " + x._2).sorted.mkString("\n") + "\n"
  )

<<<<<<< HEAD
  def workerError(msg: String): Unit = reporter.error(
=======
  private def workerError(msg: String): Unit = reporter.error(
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    FakePos("scalac"),
    msg + "\n  scalac -help  gives more information"
  )

  def act() {
    react {
      case RunTests(testKind, files) =>
        val master = sender
        kind = testKind
<<<<<<< HEAD
        runTests(files) { results => 
=======
        runTests(files) { results =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          master ! Results(results.toMap)
          resetAll()
        }
    }
  }
<<<<<<< HEAD
  
  def printInfoStart(file: File, printer: PrintWriter) {
=======

  private def printInfoStart(file: File, printer: PrintWriter) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    NestUI.outline("testing: ", printer)
    val filesdir = file.getAbsoluteFile.getParentFile.getParentFile
    val testdir = filesdir.getParentFile
    val totalWidth = 56
    val name = {
      // 1. try with [...]/files/run/test.scala
<<<<<<< HEAD
      val name = file.getAbsolutePath drop testdir.getAbsolutePath.length      
=======
      val name = file.getAbsolutePath drop testdir.getAbsolutePath.length
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (name.length <= totalWidth) name
      // 2. try with [...]/run/test.scala
      else file.getAbsolutePath drop filesdir.getAbsolutePath.length
    }
    NestUI.normal("[...]%s%s".format(name, " " * (totalWidth - name.length)), printer)
  }

<<<<<<< HEAD
  def printInfoEnd(success: Boolean, printer: PrintWriter) {
=======
  private def printInfoEnd(success: Boolean, printer: PrintWriter) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    NestUI.normal("[", printer)
    if (success) NestUI.success("  OK  ", printer)
    else NestUI.failure("FAILED", printer)
    NestUI.normal("]\n", printer)
  }

<<<<<<< HEAD
  def printInfoTimeout(printer: PrintWriter) {
=======
  private def printInfoTimeout(printer: PrintWriter) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    NestUI.normal("[", printer)
    NestUI.failure("TIMOUT", printer)
    NestUI.normal("]\n", printer)
  }

<<<<<<< HEAD
  def createLogFile(file: File) = fileManager.getLogFile(file, kind)

  def createOutputDir(dir: File): File = {
=======
  private def createLogFile(file: File) = fileManager.getLogFile(file, kind)

  private def createOutputDir(dir: File): File = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val outDir = Path(dir) / Directory("%s-%s.obj".format(fileBase, kind))
    outDir.createDirectory()
    toDelete += outDir.jfile
    outDir.jfile
  }

<<<<<<< HEAD
  def javac(outDir: File, files: List[File], output: File): Boolean = {
    // compile using command-line javac compiler
    val cmd = "%s -d %s -classpath %s %s".format(
      javacCmd,
      outDir.getAbsolutePath,
      join(outDir.toString, CLASSPATH),
      files mkString " "
    )
    
    try runCommand(cmd, output)
    catch exHandler(output, "javac command '" + cmd + "' failed:\n")
=======
  private def javac(outDir: File, files: List[File], output: File): Boolean = {
    // compile using command-line javac compiler
    val args = Seq(
      javacCmd,
      "-d",
      outDir.getAbsolutePath,
      "-classpath",
      join(outDir.toString, CLASSPATH)
    ) ++ files.map("" + _)

    try runCommand(args, output)
    catch exHandler(output, "javac command failed:\n" + args.map("  " + _ + "\n").mkString + "\n")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  /** Runs command redirecting standard out and
   *  error out to output file.
   */
<<<<<<< HEAD
  def runCommand(command: String, outFile: File): Boolean = {
    NestUI.verbose("running command:\n"+command)
    (command #> outFile !) == 0
  }

  def execTest(outDir: File, logFile: File, classpathPrefix: String = ""): Boolean = {
=======
  private def runCommand(args: Seq[String], outFile: File): Boolean = {
    NestUI.verbose("running command:\n"+args.map("  " + _ + "\n").mkString)
    (Process(args) #> outFile !) == 0
  }

  private def execTest(outDir: File, logFile: File, classpathPrefix: String = ""): Boolean = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // check whether there is a ".javaopts" file
    val argsFile  = new File(logFile.getParentFile, fileBase + ".javaopts")
    val argString = file2String(argsFile)
    if (argString != "")
      NestUI.verbose("Found javaopts file '%s', using options: '%s'".format(argsFile, argString))

<<<<<<< HEAD
=======
    val testFullPath = {
      val d = new File(logFile.getParentFile, fileBase)
      if (d.isDirectory) d.getAbsolutePath
      else {
        val f = new File(logFile.getParentFile, fileBase + ".scala")
        if (f.isFile) f.getAbsolutePath
        else ""
      }
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // Note! As this currently functions, JAVA_OPTS must precede argString
    // because when an option is repeated to java only the last one wins.
    // That means until now all the .javaopts files were being ignored because
    // they all attempt to change options which are also defined in
    // partest.java_opts, leading to debug output like:
    //
    // debug: Found javaopts file 'files/shootout/message.scala-2.javaopts', using options: '-Xss32k'
    // debug: java -Xss32k -Xss2m -Xms256M -Xmx1024M -classpath [...]
    val extras = if (isPartestDebug) List("-Dpartest.debug=true") else Nil
    val propertyOptions = List(
<<<<<<< HEAD
=======
      "-Dfile.encoding=UTF-8",
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      "-Djava.library.path="+logFile.getParentFile.getAbsolutePath,
      "-Dpartest.output="+outDir.getAbsolutePath,
      "-Dpartest.lib="+LATEST_LIB,
      "-Dpartest.cwd="+outDir.getParent,
<<<<<<< HEAD
      "-Dpartest.testname="+fileBase,
      "-Djavacmd="+JAVACMD,
      "-Djavaccmd="+javacCmd,
      "-Duser.language=en -Duser.country=US"
    ) ++ extras

    val classpath = if (classpathPrefix != "") join(classpathPrefix, CLASSPATH) else CLASSPATH
    val cmd = ( 
      List(
        JAVACMD,
        JAVA_OPTS,
        argString,
        "-classpath " + join(outDir.toString, classpath)
      ) ++ propertyOptions ++ List(
=======
      "-Dpartest.test-path="+testFullPath,
      "-Dpartest.testname="+fileBase,
      "-Djavacmd="+javaCmd,
      "-Djavaccmd="+javacCmd,
      "-Duser.language=en",
      "-Duser.country=US"
    ) ++ extras

    val classpath = if (classpathPrefix != "") join(classpathPrefix, CLASSPATH) else CLASSPATH
    val cmd = javaCmd +: (
      (JAVA_OPTS.split(' ') ++ argString.split(' ')).map(_.trim).filter(_ != "") ++ Seq(
        "-classpath",
        join(outDir.toString, classpath)
      ) ++ propertyOptions ++ Seq(
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        "scala.tools.nsc.MainGenericRunner",
        "-usejavacp",
        "Test",
        "jvm"
      )
<<<<<<< HEAD
    ) mkString " "
=======
    )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    runCommand(cmd, logFile)
  }

<<<<<<< HEAD
  def getCheckFilePath(dir: File, suffix: String = "") = {
    def chkFile(s: String) = (Directory(dir) / "%s%s.check".format(fileBase, s)).toFile
    
    if (chkFile("").isFile || suffix == "") chkFile("")
    else chkFile("-" + suffix)
  }
  def getCheckFile(dir: File) = Some(getCheckFilePath(dir, kind)) filter (_.canRead)

  def compareOutput(dir: File, logFile: File): String = {
=======
  private def getCheckFilePath(dir: File, suffix: String = "") = {
    def chkFile(s: String) = (Directory(dir) / "%s%s.check".format(fileBase, s)).toFile

    if (chkFile("").isFile || suffix == "") chkFile("")
    else chkFile("-" + suffix)
  }
  private def getCheckFile(dir: File) = Some(getCheckFilePath(dir, kind)) filter (_.canRead)

  private def compareOutput(dir: File, logFile: File): String = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val checkFile = getCheckFilePath(dir, kind)
    // if check file exists, compare with log file
    val diff =
      if (checkFile.canRead) compareFiles(logFile, checkFile.jfile)
      else file2String(logFile)

    if (diff != "" && fileManager.updateCheck) {
      NestUI.verbose("output differs from log file: updating checkfile\n")
      val toWrite = if (checkFile.exists) checkFile else getCheckFilePath(dir, "")
      toWrite writeAll file2String(logFile)
      ""
    }
    else diff
  }

<<<<<<< HEAD
  def isJava(f: File) = SFile(f) hasExtension "java"
  def isScala(f: File) = SFile(f) hasExtension "scala"
  def isJavaOrScala(f: File) = isJava(f) || isScala(f)
  
  def outputLogFile(logFile: File) {
=======
  @inline private def isJava(f: File) = SFile(f) hasExtension "java"
  @inline private def isScala(f: File) = SFile(f) hasExtension "scala"
  @inline private def isJavaOrScala(f: File) = isJava(f) || isScala(f)

  private def outputLogFile(logFile: File) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val lines = SFile(logFile).lines
    if (lines.nonEmpty) {
      NestUI.normal("Log file '" + logFile + "': \n")
      lines foreach (x => NestUI.normal(x + "\n"))
    }
  }
<<<<<<< HEAD
  def logStackTrace(logFile: File, t: Throwable, msg: String): Boolean = {
=======
  private def logStackTrace(logFile: File, t: Throwable, msg: String): Boolean = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    SFile(logFile).writeAll(msg, stackTraceString(t))
    outputLogFile(logFile) // if running the test threw an exception, output log file
    false
  }
<<<<<<< HEAD
  
  def exHandler(logFile: File): PartialFunction[Throwable, Boolean] = exHandler(logFile, "")
  def exHandler(logFile: File, msg: String): PartialFunction[Throwable, Boolean] = {
=======

  private def exHandler(logFile: File): PartialFunction[Throwable, Boolean] =
    exHandler(logFile, "")
  private def exHandler(logFile: File, msg: String): PartialFunction[Throwable, Boolean] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    case e: Exception => logStackTrace(logFile, e, msg)
  }

  /** Runs a list of tests.
   *
   * @param files The list of test files
   */
<<<<<<< HEAD
  def runTests(files: List[File])(topcont: Map[String, Int] => Unit) {
    val compileMgr = new CompileManager(fileManager)
    if (kind == "scalacheck") fileManager.CLASSPATH += File.pathSeparator + PathSettings.scalaCheck
    
=======
  private def runTests(files: List[File])(topcont: Map[String, Int] => Unit) {
    val compileMgr = new CompileManager(fileManager)
    // if (kind == "scalacheck")
    fileManager.CLASSPATH += File.pathSeparator + PathSettings.scalaCheck
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    filesRemaining = files

    // You don't default "succeeded" to true.
    var succeeded = false
    var done = filesRemaining.isEmpty
    var errors = 0
    var diff = ""
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def initNextTest() = {
      val swr = new StringWriter
      val wr  = new PrintWriter(swr, true)
      diff    = ""
<<<<<<< HEAD
      
      ((swr, wr))
    }
    
=======

      ((swr, wr))
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def fail(what: Any) = {
      NestUI.verbose("scalac: compilation of "+what+" failed\n")
      false
    }
    def diffCheck(latestDiff: String) = {
      diff = latestDiff
      succeeded = diff == ""
      succeeded
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def timed[T](body: => T): (T, Long) = {
      val t1 = System.currentTimeMillis
      val result = body
      val t2 = System.currentTimeMillis
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      (result, t2 - t1)
    }

    /** 1. Creates log file and output directory.
     *  2. Runs script function, providing log file and output directory as arguments.
     */
    def runInContext(file: File, script: (File, File) => Boolean): LogContext = {
      // When option "--failed" is provided, execute test only if log file is present
      // (which means it failed before)
      val logFile = createLogFile(file)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (fileManager.failed && !logFile.canRead)
        LogContext(logFile)
      else {
        val (swr, wr) = initNextTest()
        printInfoStart(file, wr)

        NestUI.verbose(this+" running test "+fileBase)
        val dir = file.getParentFile
        val outDir = createOutputDir(dir)
        NestUI.verbose("output directory: "+outDir)

        // run test-specific code
        succeeded = try {
          if (isPartestDebug) {
            val (result, millis) = timed(script(logFile, outDir))
            fileManager.recordTestTiming(file.getPath, millis)
            result
          }
          else script(logFile, outDir)
        }
        catch exHandler(logFile)

        LogContext(logFile, swr, wr)
      }
    }

    def compileFilesIn(dir: File, logFile: File, outDir: File): Boolean = {
      val testFiles = dir.listFiles.toList filter isJavaOrScala

      def isInGroup(f: File, num: Int) = SFile(f).stripExtension endsWith ("_" + num)
      val groups = (0 to 9).toList map (num => testFiles filter (f => isInGroup(f, num)))
      val noGroupSuffix = testFiles filterNot (groups.flatten contains)

      def compileGroup(g: List[File]): Boolean = {
        val (scalaFiles, javaFiles) = g partition isScala
        val allFiles = javaFiles ++ scalaFiles
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // scala+java, then java, then scala
        (scalaFiles.isEmpty || compileMgr.shouldCompile(outDir, allFiles, kind, logFile) || fail(g)) && {
          (javaFiles.isEmpty || javac(outDir, javaFiles, logFile)) && {
            (scalaFiles.isEmpty || compileMgr.shouldCompile(outDir, scalaFiles, kind, logFile) || fail(scalaFiles))
          }
        }
      }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      (noGroupSuffix.isEmpty || compileGroup(noGroupSuffix)) && (groups forall compileGroup)
    }

    def failCompileFilesIn(dir: File, logFile: File, outDir: File): Boolean = {
      val testFiles   = dir.listFiles.toList
      val sourceFiles = testFiles filter isJavaOrScala
<<<<<<< HEAD
      
      sourceFiles.isEmpty || compileMgr.shouldFailCompile(outDir, sourceFiles, kind, logFile) || fail(testFiles filter isScala)
    }
    
=======

      sourceFiles.isEmpty || compileMgr.shouldFailCompile(outDir, sourceFiles, kind, logFile) || fail(testFiles filter isScala)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def runTestCommon(file: File, expectFailure: Boolean)(
      onSuccess: (File, File) => Boolean,
      onFail: (File, File) => Unit = (_, _) => ()): LogContext =
    {
      runInContext(file, (logFile: File, outDir: File) => {
        val result =
          if (file.isDirectory) {
            if (expectFailure) failCompileFilesIn(file, logFile, outDir)
            else compileFilesIn(file, logFile, outDir)
          }
          else {
            if (expectFailure) compileMgr.shouldFailCompile(List(file), kind, logFile)
            else compileMgr.shouldCompile(List(file), kind, logFile)
          }
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (result) onSuccess(logFile, outDir)
        else { onFail(logFile, outDir) ; false }
      })
    }

    def runJvmTest(file: File): LogContext =
      runTestCommon(file, expectFailure = false)((logFile, outDir) => {
        val dir      = file.getParentFile

        execTest(outDir, logFile) && diffCheck(compareOutput(dir, logFile))
      })
<<<<<<< HEAD
    
    def runSpecializedTest(file: File): LogContext =
      runTestCommon(file, expectFailure = false)((logFile, outDir) => {
        val dir       = file.getParentFile
        
=======

    // Apache Ant 1.6 or newer
    def ant(args: Seq[String], output: File): Boolean = {
      val antDir = Directory(envOrElse("ANT_HOME", "/opt/ant/"))
      val antLibDir = Directory(antDir / "lib")
      val antLauncherPath = SFile(antLibDir / "ant-launcher.jar").path
      val antOptions =
        if (NestUI._verbose) List("-verbose", "-noinput")
        else List("-noinput")
      val cmd = javaCmd +: (
        JAVA_OPTS.split(' ').map(_.trim).filter(_ != "") ++ Seq(
          "-classpath",
          antLauncherPath,
          "org.apache.tools.ant.launch.Launcher"
        ) ++ antOptions ++ args
      )

      try runCommand(cmd, output)
      catch exHandler(output, "ant command '" + cmd + "' failed:\n")
    }

    def runAntTest(file: File): LogContext = {
      val logFile = createLogFile(file)
      if (!fileManager.failed || logFile.canRead) {
        val (swr, wr) = initNextTest()
        printInfoStart(file, wr)

        NestUI.verbose(this+" running test "+fileBase)

        try {
          val binary = "-Dbinary="+(
            if      (fileManager.LATEST_LIB endsWith "build/quick/classes/library") "quick"
            else if (fileManager.LATEST_LIB endsWith "build/pack/lib/scala-library.jar") "pack"
            else if (fileManager.LATEST_LIB endsWith "dists/latest/lib/scala-library.jar/") "latest"
            else "installed"
          )
          val args = Array(binary, "-logfile", logFile.path, "-file", file.path)
          NestUI.verbose("ant "+args.mkString(" "))
          succeeded = ant(args, logFile)
          diffCheck(compareOutput(file.getParentFile, logFile))
        }
        catch { // *catch-all*
          case e: Exception =>
            NestUI.verbose("caught "+e)
            succeeded = false
        }

        LogContext(logFile, swr, wr)
      } else
        LogContext(logFile)
    }

    def runSpecializedTest(file: File): LogContext =
      runTestCommon(file, expectFailure = false)((logFile, outDir) => {
        val dir       = file.getParentFile

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // adding the instrumented library to the classpath
        execTest(outDir, logFile, PathSettings.srcSpecLib.toString) &&
        diffCheck(compareOutput(dir, logFile))
      })

    def processSingleFile(file: File): LogContext = kind match {
      case "scalacheck" =>
        val succFn: (File, File) => Boolean = { (logFile, outDir) =>
          NestUI.verbose("compilation of "+file+" succeeded\n")
<<<<<<< HEAD
          
          val outURL    = outDir.getAbsoluteFile.toURI.toURL
          val logWriter = new PrintStream(new FileOutputStream(logFile), true)
          
=======

          val outURL    = outDir.getAbsoluteFile.toURI.toURL
          val logWriter = new PrintStream(new FileOutputStream(logFile), true)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          Output.withRedirected(logWriter) {
            // this classloader is test specific: its parent contains library classes and others
            ScalaClassLoader.fromURLs(List(outURL), params.scalaCheckParentClassLoader).run("Test", Nil)
          }
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          NestUI.verbose(file2String(logFile))
          // obviously this must be improved upon
          val lines = SFile(logFile).lines map (_.trim) filterNot (_ == "") toBuffer;
          if (lines forall (x => !x.startsWith("!"))) {
            NestUI.verbose("test for '" + file + "' success: " + succeeded)
            true
          }
          else {
            NestUI.normal("ScalaCheck test failed. Output:\n")
            lines foreach (x => NestUI.normal(x + "\n"))
<<<<<<< HEAD
            false            
=======
            false
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          }
        }
        runTestCommon(file, expectFailure = false)(
          succFn,
          (logFile, outDir) => outputLogFile(logFile)
        )

      case "pos" =>
        runTestCommon(file, expectFailure = false)(
          (logFile, outDir) => true,
          (_, _) => ()
        )

<<<<<<< HEAD
      case "neg" =>      
=======
      case "neg" =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        runTestCommon(file, expectFailure = true)((logFile, outDir) => {
          // compare log file to check file
          val dir      = file.getParentFile

          // diff is contents of logFile
<<<<<<< HEAD
=======
          fileManager.mapFile(logFile, replaceSlashes(dir, _))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          diffCheck(compareOutput(dir, logFile))
        })

      case "run" | "jvm" =>
        runJvmTest(file)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      case "specialized" =>
        runSpecializedTest(file)

      case "presentation" =>
        runJvmTest(file) // for the moment, it's exactly the same as for a run test
<<<<<<< HEAD
      
=======

      case "ant" =>
        runAntTest(file)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      case "buildmanager" =>
        val logFile = createLogFile(file)
        if (!fileManager.failed || logFile.canRead) {
          val (swr, wr) = initNextTest()
          printInfoStart(file, wr)
          val (outDir, testFile, changesDir) = (
            if (!file.isDirectory)
              (null, null, null)
            else {
              NestUI.verbose(this+" running test "+fileBase)
              val outDir = createOutputDir(file)
              val testFile = new File(file, fileBase + ".test")
              val changesDir = new File(file, fileBase + ".changes")
<<<<<<< HEAD
              
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              if (changesDir.isFile || !testFile.isFile) {
                // if changes exists then it has to be a dir
                if (!testFile.isFile) NestUI.verbose("invalid build manager test file")
                if (changesDir.isFile) NestUI.verbose("invalid build manager changes directory")
                (null, null, null)
              }
              else {
                copyTestFiles(file, outDir)
                NestUI.verbose("outDir:  "+outDir)
                NestUI.verbose("logFile: "+logFile)
                (outDir, testFile, changesDir)
              }
            }
          )
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          if (outDir != null) {
            // Pre-conditions satisfied
            try {
              val sourcepath = outDir.getAbsolutePath+File.separator

              // configure input/output files
              val logWriter = new PrintStream(new FileOutputStream(logFile), true)
              val testReader = new BufferedReader(new FileReader(testFile))
              val logConsoleWriter = new PrintWriter(logWriter, true)

              // create proper settings for the compiler
              val settings = new Settings(workerError)
              settings.outdir.value = outDir.getAbsoluteFile.getAbsolutePath
              settings.sourcepath.value = sourcepath
              settings.classpath.value = fileManager.CLASSPATH
              settings.Ybuildmanagerdebug.value = true

              // simulate Build Manager loop
              val prompt = "builder > "
              reporter = new ConsoleReporter(settings, scala.Console.in, logConsoleWriter)
              val bM: BuildManager =
                  new RefinedBuildManager(settings) {
                    override protected def newCompiler(settings: Settings) =
<<<<<<< HEAD
                        new BuilderGlobal(settings, reporter) 
                  }
              
=======
                        new BuilderGlobal(settings, reporter)
                  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              def testCompile(line: String): Boolean = {
                NestUI.verbose("compiling " + line)
                val args = (line split ' ').toList
                val command = new CompilerCommand(args, settings)
                command.ok && {
                  bM.update(filesToSet(settings.sourcepath.value, command.files), Set.empty)
                  !reporter.hasErrors
                }
              }
<<<<<<< HEAD
              
              val updateFiles = (line: String) => {
                NestUI.verbose("updating " + line)
                val res = 
=======

              val updateFiles = (line: String) => {
                NestUI.verbose("updating " + line)
                val res =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                  ((line split ' ').toList).forall(u => {
                    (u split "=>").toList match {
                        case origFileName::(newFileName::Nil) =>
                          val newFile = new File(changesDir, newFileName)
                          if (newFile.isFile) {
                            val v = overwriteFileWith(new File(outDir, origFileName), newFile)
                            if (!v)
                              NestUI.verbose("'update' operation on " + u + " failed")
                            v
                          } else {
<<<<<<< HEAD
                            NestUI.verbose("File " + newFile + " is invalid") 
=======
                            NestUI.verbose("File " + newFile + " is invalid")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                            false
                          }
                        case a =>
                          NestUI.verbose("Other =: " + a)
                          false
                    }
                  })
                NestUI.verbose("updating " + (if (res) "succeeded" else "failed"))
                res
              }

              def loop(): Boolean = {
                testReader.readLine() match {
                  case null | ""    =>
                    NestUI.verbose("finished")
                    true
                  case s if s startsWith ">>update "  =>
                    updateFiles(s stripPrefix ">>update ") && loop()
                  case s if s startsWith ">>compile " =>
                    val files = s stripPrefix ">>compile "
                    logWriter.println(prompt + files)
                    // In the end, it can finish with an error
                    if (testCompile(files)) loop()
                    else {
                      val t = testReader.readLine()
                      (t == null) || (t == "")
                    }
                  case s =>
                    NestUI.verbose("wrong command in test file: " + s)
                    false
                }
              }
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              Output.withRedirected(logWriter) {
                try loop()
                finally testReader.close()
              }
              fileManager.mapFile(logFile, replaceSlashes(new File(sourcepath), _))
              diffCheck(compareOutput(file, logFile))
            }
            LogContext(logFile, swr, wr)
<<<<<<< HEAD
          } else 
=======
          } else
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            LogContext(logFile)
        } else
          LogContext(logFile)

      case "res" => {
          // simulate resident compiler loop
          val prompt = "\nnsc> "

          // when option "--failed" is provided
          // execute test only if log file is present
          // (which means it failed before)
          val logFile = createLogFile(file)
          if (!fileManager.failed || logFile.canRead) {
            val (swr, wr) = initNextTest()
            printInfoStart(file, wr)

            NestUI.verbose(this+" running test "+fileBase)
            val dir = file.getParentFile
            val outDir = createOutputDir(dir)
            val resFile = new File(dir, fileBase + ".res")
            NestUI.verbose("outDir:  "+outDir)
            NestUI.verbose("logFile: "+logFile)
            //NestUI.verbose("logFileErr: "+logFileErr)
            NestUI.verbose("resFile: "+resFile)

            // run compiler in resident mode
            // $SCALAC -d "$os_dstbase".obj -Xresident -sourcepath . "$@"
            val sourcedir  = logFile.getParentFile.getAbsoluteFile
            val sourcepath = sourcedir.getAbsolutePath+File.separator
            NestUI.verbose("sourcepath: "+sourcepath)

<<<<<<< HEAD
            val argString =
              "-d "+outDir.getAbsoluteFile.getPath+
              " -Xresident"+
              " -sourcepath "+sourcepath
            val argList = argString split ' ' toList
=======
            val argList = List(
              "-d", outDir.getAbsoluteFile.getPath,
              "-Xresident",
              "-sourcepath", sourcepath)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

            // configure input/output files
            val logOut    = new FileOutputStream(logFile)
            val logWriter = new PrintStream(logOut, true)
            val resReader = new BufferedReader(new FileReader(resFile))
            val logConsoleWriter = new PrintWriter(new OutputStreamWriter(logOut), true)

            // create compiler
            val settings = new Settings(workerError)
            settings.sourcepath.value = sourcepath
            settings.classpath.value = fileManager.CLASSPATH
            reporter = new ConsoleReporter(settings, scala.Console.in, logConsoleWriter)
            val command = new CompilerCommand(argList, settings)
            object compiler extends Global(command.settings, reporter)

            val resCompile = (line: String) => {
              NestUI.verbose("compiling "+line)
              val cmdArgs = (line split ' ').toList map (fs => new File(dir, fs).getAbsolutePath)
              NestUI.verbose("cmdArgs: "+cmdArgs)
              val sett = new Settings(workerError)
              sett.sourcepath.value = sourcepath
              val command = new CompilerCommand(cmdArgs, sett)
              command.ok && {
                (new compiler.Run) compile command.files
                !reporter.hasErrors
              }
            }

            def loop(action: String => Boolean): Boolean = {
              logWriter.print(prompt)
              resReader.readLine() match {
                case null | ""  => logWriter.flush() ; true
                case line       => action(line) && loop(action)
              }
            }
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            Output.withRedirected(logWriter) {
              try loop(resCompile)
              finally resReader.close()
            }
            fileManager.mapFile(logFile, replaceSlashes(dir, _))
            diffCheck(compareOutput(dir, logFile))
            LogContext(logFile, swr, wr)
          } else
            LogContext(logFile)
        }

<<<<<<< HEAD
      case "shootout" => {
          // when option "--failed" is provided
          // execute test only if log file is present
          // (which means it failed before)
          val logFile = createLogFile(file)
          if (!fileManager.failed || logFile.canRead) {
            val (swr, wr) = initNextTest()
            printInfoStart(file, wr)

            NestUI.verbose(this+" running test "+fileBase)
            val dir = file.getParentFile
            val outDir = createOutputDir(dir)

            // 2. define file {outDir}/test.scala that contains code to compile/run
            val testFile = new File(outDir, "test.scala")
            NestUI.verbose("outDir:   "+outDir)
            NestUI.verbose("logFile:  "+logFile)
            NestUI.verbose("testFile: "+testFile)

            // 3. cat {test}.scala.runner {test}.scala > testFile
            val runnerFile = new File(dir, fileBase+".scala.runner")
            val bodyFile   = new File(dir, fileBase+".scala")
            SFile(testFile).writeAll(
              file2String(runnerFile),
              file2String(bodyFile)
            )

            // 4. compile testFile
            val ok = compileMgr.shouldCompile(List(testFile), kind, logFile)
            NestUI.verbose("compilation of " + testFile + (if (ok) "succeeded" else "failed"))
            if (ok) {
              execTest(outDir, logFile) && {
                NestUI.verbose(this+" finished running "+fileBase)
                diffCheck(compareOutput(dir, logFile))
              }
            }

            LogContext(logFile, swr, wr)
          }
          else
            LogContext(logFile)
        }
=======
      case "shootout" =>
        // when option "--failed" is provided
        // execute test only if log file is present
        // (which means it failed before)
        val logFile = createLogFile(file)
        if (!fileManager.failed || logFile.canRead) {
          val (swr, wr) = initNextTest()
          printInfoStart(file, wr)

          NestUI.verbose(this+" running test "+fileBase)
          val dir = file.getParentFile
          val outDir = createOutputDir(dir)

          // 2. define file {outDir}/test.scala that contains code to compile/run
          val testFile = new File(outDir, "test.scala")
          NestUI.verbose("outDir:   "+outDir)
          NestUI.verbose("logFile:  "+logFile)
          NestUI.verbose("testFile: "+testFile)

          // 3. cat {test}.scala.runner {test}.scala > testFile
          val runnerFile = new File(dir, fileBase+".scala.runner")
          val bodyFile   = new File(dir, fileBase+".scala")
          SFile(testFile).writeAll(
            file2String(runnerFile),
            file2String(bodyFile)
          )

          // 4. compile testFile
          val ok = compileMgr.shouldCompile(List(testFile), kind, logFile)
          NestUI.verbose("compilation of " + testFile + (if (ok) "succeeded" else "failed"))
          if (ok) {
            execTest(outDir, logFile) && {
              NestUI.verbose(this+" finished running "+fileBase)
              diffCheck(compareOutput(dir, logFile))
            }
          }

          LogContext(logFile, swr, wr)
        }
        else
          LogContext(logFile)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

      case "scalap" =>
        runInContext(file, (logFile: File, outDir: File) => {
          val sourceDir = Directory(if (file.isFile) file.getParent else file)
          val sources   = sourceDir.files filter (_ hasExtension "scala") map (_.jfile) toList
          val results   = sourceDir.files filter (_.name == "result.test") map (_.jfile) toList
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          if (sources.length != 1 || results.length != 1) {
            NestUI.warning("Misconfigured scalap test directory: " + sourceDir + " \n")
            false
          }
          else {
            val resFile = results.head
            // 2. Compile source file
            if (!compileMgr.shouldCompile(outDir, sources, kind, logFile)) {
              NestUI.normal("compilerMgr failed to compile %s to %s".format(sources mkString ", ", outDir))
              false
            }
            else {
              // 3. Decompile file and compare results
              val isPackageObject = sourceDir.name startsWith "package"
              val className       = sourceDir.name.capitalize + (if (!isPackageObject) "" else ".package")
              val url             = outDir.toURI.toURL
              val loader          = ScalaClassLoader.fromURLs(List(url), this.getClass.getClassLoader)
              val clazz           = loader.loadClass(className)

              val byteCode = ByteCode.forClass(clazz)
              val result   = scala.tools.scalap.Main.decompileScala(byteCode.bytes, isPackageObject)

              SFile(logFile) writeAll result
              diffCheck(compareFiles(logFile, resFile))
            }
          }
        })

<<<<<<< HEAD
      case "script" => {
          // when option "--failed" is provided
          // execute test only if log file is present
          // (which means it failed before)
          val logFile = createLogFile(file)
          if (!fileManager.failed || logFile.canRead) {
            val (swr, wr) = initNextTest()
            printInfoStart(file, wr)

            NestUI.verbose(this+" running test "+fileBase)

            // check whether there is an args file
            val argsFile = new File(file.getParentFile, fileBase+".args")
            NestUI.verbose("argsFile: "+argsFile)
            val argString = file2String(argsFile)

            try {
              val cmdString =
                if (isWin) {
                  val batchFile = new File(file.getParentFile, fileBase+".bat")
                  NestUI.verbose("batchFile: "+batchFile)
                  batchFile.getAbsolutePath
                }
                else file.getAbsolutePath

              succeeded = ((cmdString+argString) #> logFile !) == 0
              diffCheck(compareOutput(file.getParentFile, logFile))
            }
            catch { // *catch-all*
              case e: Exception =>
                NestUI.verbose("caught "+e)
                succeeded = false
            }

            LogContext(logFile, swr, wr)
          } else
            LogContext(logFile)
      }
=======
      case "script" =>
        // when option "--failed" is provided
        // execute test only if log file is present
        // (which means it failed before)
        val logFile = createLogFile(file)
        if (!fileManager.failed || logFile.canRead) {
          val (swr, wr) = initNextTest()
          printInfoStart(file, wr)

          NestUI.verbose(this+" running test "+fileBase)

          // check whether there is an args file
          val argsFile = new File(file.getParentFile, fileBase+".args")
          NestUI.verbose("argsFile: "+argsFile)
          val argString = file2String(argsFile)

          try {
            val cmdString =
              if (isWin) {
                val batchFile = new File(file.getParentFile, fileBase+".bat")
                NestUI.verbose("batchFile: "+batchFile)
                batchFile.getAbsolutePath
              }
              else file.getAbsolutePath

            succeeded = ((cmdString+argString) #> logFile !) == 0
            diffCheck(compareOutput(file.getParentFile, logFile))
          }
          catch { // *catch-all*
            case e: Exception =>
              NestUI.verbose("caught "+e)
              succeeded = false
          }

          LogContext(logFile, swr, wr)
        } else
          LogContext(logFile)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    def reportAll(results: Map[String, Int], cont: Map[String, Int] => Unit) {
      timer.cancel()
      cont(results)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    object TestState {
      val Ok = 0
      val Fail = 1
      val Timeout = 2
    }

    def reportResult(state: Int, logFile: File, writers: Option[(StringWriter, PrintWriter)]) {
      val isGood    = state == TestState.Ok
      val isFail    = state == TestState.Fail
      val isTimeout = state == TestState.Timeout
      val hasLog    = logFile != null
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (isGood) {
        // add logfile from deletion list if test passed
        if (hasLog)
          toDelete += logFile
      }
      else {
        errors += 1
        NestUI.verbose("incremented errors: "+errors)
      }

      writers foreach { case (swr, wr) =>
        if (isTimeout) printInfoTimeout(wr)
        else printInfoEnd(isGood, wr)
        wr.flush()
        swr.flush()
        NestUI.normal(swr.toString)
        if (isFail) {
          if ((fileManager.showDiff || isPartestDebug) && diff != "")
            NestUI.normal(diff)
          else if (fileManager.showLog)
            showLog(logFile)
        }
      }
      cleanup()
    }
<<<<<<< HEAD
    
    def finish() = {
=======

    def finish() {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      done = true
      cancelTimerTask()
      reportAll(status.toMap, topcont)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    Actor.loopWhile(!done) {
      val parent = self

      actor {
        val testFile = getNextFile()
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (testFile == null)
          finish()
        else {
          updateTimerTask(parent ! Timeout(testFile))

<<<<<<< HEAD
          val context = 
=======
          val context =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            try processSingleFile(testFile)
            catch {
              case t =>
                succeeded = false
                try {
                  val logFile = createLogFile(testFile)
                  logStackTrace(logFile, t, "Possible compiler crash during test of: " + testFile + "\n")
                  LogContext(logFile)
                }
                catch {
                  case t => LogContext(null)
                }
            }
          parent ! Result(testFile, context)
        }
      }

      react {
        case Timeout(file) =>
          updateStatus(file.getAbsolutePath, TestState.Timeout)
          val swr = new StringWriter
          val wr = new PrintWriter(swr, true)
          printInfoStart(file, wr)
          reportResult(
            TestState.Timeout,
            null,
            Some((swr, wr))
          )
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case Result(file, logs) =>
          val state = if (succeeded) TestState.Ok else TestState.Fail
          updateStatus(file.getAbsolutePath, state)
          reportResult(
            state,
            logs.file,
            logs.writers
          )
      }
    }
  }
<<<<<<< HEAD
  
  private def filesToSet(pre: String, fs: List[String]): Set[AbstractFile] =
    fs flatMap (s => Option(AbstractFile getFile (pre + s))) toSet
  
  private def copyTestFiles(testDir: File, destDir: File) {
    val invalidExts = List("changes", "svn", "obj")
    testDir.listFiles.toList filter (
            f => (isJavaOrScala(f) && f.isFile) || 
=======

  private def filesToSet(pre: String, fs: List[String]): Set[AbstractFile] =
    fs flatMap (s => Option(AbstractFile getFile (pre + s))) toSet

  private def copyTestFiles(testDir: File, destDir: File) {
    val invalidExts = List("changes", "svn", "obj")
    testDir.listFiles.toList filter (
            f => (isJavaOrScala(f) && f.isFile) ||
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                 (f.isDirectory && !(invalidExts.contains(SFile(f).extension)))) foreach
      { f => fileManager.copyFile(f, destDir) }
  }

<<<<<<< HEAD
  def showLog(logFile: File) {
=======
  private def showLog(logFile: File) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    file2String(logFile) match {
      case "" if logFile.canRead  => ()
      case ""                     => NestUI.failure("Couldn't open log file: " + logFile + "\n")
      case s                      => NestUI.normal(s)
    }
  }
}
