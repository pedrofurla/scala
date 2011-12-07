/* NSC -- new Scala compiler
 * Copyright 2009-2011 Scala Solutions and LAMP/EPFL
 * @author Martin Odersky
 */
package scala.tools.nsc
package interactive

import scala.concurrent.SyncVar
import scala.tools.nsc.util._
import scala.tools.nsc.symtab._
import scala.tools.nsc.ast._
import scala.tools.nsc.reporters._
import scala.tools.nsc.io._
import scala.tools.nsc.scratchpad.{Executor, SourceInserter}
<<<<<<< HEAD
=======
import scala.tools.nsc.interpreter.AbstractFileClassLoader
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import java.io.{File, FileWriter}

/** Interface of interactive compiler to a client such as an IDE
 */
object REPL {

  val versionMsg = "Scala compiler " +
    Properties.versionString + " -- " +
    Properties.copyrightString

  val prompt = "> "

  var reporter: ConsoleReporter = _

  private def replError(msg: String) {
    reporter.error(/*new Position */FakePos("scalac"),
                   msg + "\n  scalac -help  gives more information")
  }

  def process(args: Array[String]) {
    val settings = new Settings(replError)
    reporter = new ConsoleReporter(settings)
    val command = new CompilerCommand(args.toList, settings)
    if (command.settings.version.value)
      reporter.info(null, versionMsg, true)
    else {
      try {
        object compiler extends Global(command.settings, reporter) {
//          printTypings = true
<<<<<<< HEAD
        } 
=======
        }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (reporter.hasErrors) {
          reporter.flush()
          return
        }
        if (command.shouldStopWithInfo) {
          reporter.info(null, command.getInfoMessage(compiler), true)
        } else {
          run(compiler)
        }
      } catch {
        case ex @ FatalError(msg) =>
          if (true || command.settings.debug.value) // !!!
            ex.printStackTrace();
        reporter.error(null, "fatal error: " + msg)
      }
    }
  }

  def main(args: Array[String]) {
    process(args)
    /*sys.*/exit(if (reporter.hasErrors) 1 else 0)// Don't use sys yet as this has to run on 2.8.2 also.
  }

  def loop(action: (String) => Unit) {
    Console.print(prompt)
    try {
      val line = Console.readLine
      if (line.length() > 0) {
        action(line)
      }
      loop(action)
    }
    catch {
      case _: java.io.EOFException => //nop
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Commands:
   *
   *  reload file1 ... fileN
   *  typeat file off1 off2?
   *  complete file off1 off2?
   */
  def run(comp: Global) {
    val reloadResult = new Response[Unit]
    val typeatResult = new Response[comp.Tree]
    val completeResult = new Response[List[comp.Member]]
    val typedResult = new Response[comp.Tree]
    val structureResult = new Response[comp.Tree]
    val instrumentedResult = new Response[(String, Array[Char])]

    def makePos(file: String, off1: String, off2: String) = {
      val source = toSourceFile(file)
      comp.rangePos(source, off1.toInt, off1.toInt, off2.toInt)
    }

    def doTypeAt(pos: Position) {
      comp.askTypeAt(pos, typeatResult)
      show(typeatResult)
    }

    def doComplete(pos: Position) {
      comp.askTypeCompletion(pos, completeResult)
      show(completeResult)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def doTypedTree(file: String) {
      comp.askType(toSourceFile(file), true, typedResult)
      show(typedResult)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def doStructure(file: String) {
      comp.askParsedEntered(toSourceFile(file), false, structureResult)
      show(structureResult)
    }
<<<<<<< HEAD
      
    /** This is the method for implement worksheet functionality
     */
    def instrument(source: SourceFile, line: Int): Option[Array[Char]] = {
      // strip right hand side comment column and any trailing spaces from all lines
      val strippedSource = new BatchSourceFile(source.file, SourceInserter.stripRight(source.content))
      comp.askReload(List(strippedSource), reloadResult)
      // todo: Display strippedSource in place of source
      comp.askInstrumented(strippedSource, line, instrumentedResult)
      using(instrumentedResult) { case (iFullName, iContents) => 
        // iFullName: The full name of the first top-level object in source
        // iContents: An Array[Char] containing the instrumented source
        // Create a file from iContents so that it can be compiled
        // The name here is <simple name of object>+"$instrumented.scala", but
        // it could be anything.
        val iSimpleName = iFullName drop ((iFullName lastIndexOf '.') + 1)
        val iSourceName = iSimpleName + "$instrumented.scala"
        val ifile = new FileWriter(iSourceName)
        ifile.write(iContents)
        ifile.close()
        println("compiling "+iSourceName)
        // Compile instrumented source
        scala.tools.nsc.Main.process(Array(iSourceName, "-verbose", "-d", "/classes"))
        // Run instrumented source, inserting all output into stripped source
        println("running "+iSimpleName)
        val si = new SourceInserter(strippedSource.content)
        Executor.execute(iSimpleName, si)
        println("done") 
        si.currentContents
      }
    }
    
    loop { line =>
      (line split " ").toList match {
        case "reload" :: args => 
=======

    /** Write instrumented source file to disk.
     *  @param iFullName  The full name of the first top-level object in source
     *  @param iContents  An Array[Char] containing the instrumented source
     *  @return The name of the instrumented source file
     */
    def writeInstrumented(iFullName: String, iContents: Array[Char]): String = {
      val iSimpleName = iFullName drop ((iFullName lastIndexOf '.') + 1)
      val iSourceName = iSimpleName + "$instrumented.scala"
      val ifile = new FileWriter(iSourceName)
      ifile.write(iContents)
      ifile.close()
      iSourceName
    }

    /** Compile instrumented source file
     *  @param iSourceName The name of the instrumented source file
     *  @param arguments   Further argumenrs to pass to the compiler
     *  @return Optionallu, if no -d option is given, the virtual directory
     *          contained the generated bytecode classes
     */
    def compileInstrumented(iSourceName: String, arguments: List[String]): Option[AbstractFile] = {
      println("compiling "+iSourceName)
      val command = new CompilerCommand(iSourceName :: arguments, reporter.error(scala.tools.nsc.util.NoPosition, _))
      val virtualDirectoryOpt =
        if (arguments contains "-d")
          None
        else {
          val vdir = new VirtualDirectory("(memory)", None)
          command.settings.outputDirs setSingleOutput vdir
          Some(vdir)
        }
      val compiler = new scala.tools.nsc.Global(command.settings, reporter)
      val run = new compiler.Run()
      println("compiling: "+command.files)
      run compile command.files
      virtualDirectoryOpt
    }

    /** Run instrumented bytecode file
     *  @param vdir       Optionally, the virtual directory containing the generated bytecode classes
     *  @param iFullName  The full name of the generated object
     *  @param stripped   The contents original source file without any right hand column comments.
     *  @return The generated file content containing original source in the left column
     *          and outputs in the right column
     */
    def runInstrumented(vdirOpt: Option[AbstractFile], iFullName: String, stripped: Array[Char]): Array[Char] = {
      val defaultClassLoader = getClass.getClassLoader
      val classLoader = vdirOpt match {
        case Some(vdir) => new AbstractFileClassLoader(vdir, defaultClassLoader)
        case None => defaultClassLoader
      }
      println("running "+iFullName)
      val si = new SourceInserter(stripped)
      Executor.execute(iFullName, si, classLoader)
      println("done")
      si.currentContents
    }

    /** The method for implementing worksheet functionality.
     *  @param arguments  a file name, followed by optional command line arguments that are passed
     *                    to the compiler that processes the instrumented source.
     *  @param line       A line number that controls uop to which line results should be produced
     *                    If line = -1, results are produced for all expressions in the worksheet.
     *  @return           The generated file content containing original source in the left column
     *                    and outputs in the right column, or None if the presentation compiler
     *                    does not respond to askInstrumented.
     */
    def instrument(arguments: List[String], line: Int): Option[Array[Char]] = {
      val source = toSourceFile(arguments.head)
      // strip right hand side comment column and any trailing spaces from all lines
      val strippedSource = new BatchSourceFile(source.file, SourceInserter.stripRight(source.content))
      comp.askReload(List(strippedSource), reloadResult)
      comp.askInstrumented(strippedSource, line, instrumentedResult)
      using(instrumentedResult) {
        case (iFullName, iContents) =>
          val iSourceName = writeInstrumented(iFullName, iContents)
          val vdirOpt = compileInstrumented(iSourceName, arguments.tail)
          runInstrumented(vdirOpt, iFullName, strippedSource.content)
      }
    }

    loop { line =>
      (line split " ").toList match {
        case "reload" :: args =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          comp.askReload(args map toSourceFile, reloadResult)
          show(reloadResult)
        case "reloadAndAskType" :: file :: millis :: Nil =>
          comp.askReload(List(toSourceFile(file)), reloadResult)
          Thread.sleep(millis.toInt)
          println("ask type now")
          comp.askType(toSourceFile(file), false, typedResult)
          typedResult.get
        case List("typed", file) =>
          doTypedTree(file)
        case List("typeat", file, off1, off2) =>
          doTypeAt(makePos(file, off1, off2))
        case List("typeat", file, off1) =>
          doTypeAt(makePos(file, off1, off1))
        case List("complete", file, off1, off2) =>
          doComplete(makePos(file, off1, off2))
        case List("complete", file, off1) =>
          doComplete(makePos(file, off1, off1))
<<<<<<< HEAD
        case List("instrument", file) =>
          println(instrument(toSourceFile(file), -1).map(_.mkString))
        case List("instrument", file, line) =>
          println(instrument(toSourceFile(file), line.toInt).map(_.mkString))
=======
        case "instrument" :: arguments =>
          println(instrument(arguments, -1).map(_.mkString))
        case "instrumentTo" :: line :: arguments =>
          println(instrument(arguments, line.toInt).map(_.mkString))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case List("quit") =>
          comp.askShutdown()
          exit(1) // Don't use sys yet as this has to run on 2.8.2 also.
        case List("structure", file) =>
          doStructure(file)
        case _ =>
<<<<<<< HEAD
          println("unrecongized command")
=======
          print("""Available commands:
                  | reload <file_1> ... <file_n>
                  | reloadAndAskType <file> <sleep-ms>
                  | typed <file>
                  | typeat <file> <start-pos> <end-pos>
                  | typeat <file> <pos>
                  | complete <file> <start-pos> <end-pos>
                  | compile <file> <pos>
                  | instrument <file> <arg>*
                  | instrumentTo <line-num> <file> <arg>*
                  | structure <file>
                  | quit
                  |""".stripMargin)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }

  def toSourceFile(name: String) = new BatchSourceFile(new PlainFile(new java.io.File(name)))

  def using[T, U](svar: Response[T])(op: T => U): Option[U] = {
    val res = svar.get match {
      case Left(result) => Some(op(result))
      case Right(exc) => exc.printStackTrace; println("ERROR: "+exc); None
    }
    svar.clear()
    res
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def show[T](svar: Response[T]) = using(svar)(res => println("==> "+res))
}
