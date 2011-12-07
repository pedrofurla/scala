/* NSC -- new Scala compiler
 * Copyright 2009-2011 Scala Solutions and LAMP/EPFL
 * @author Martin Odersky
 */
<<<<<<< HEAD
package scala.tools.nsc.interactive
package tests

import scala.tools.nsc.Settings
import scala.tools.nsc.reporters.StoreReporter
import scala.tools.nsc.util.{BatchSourceFile, SourceFile, Position}
import scala.tools.nsc.io._

import scala.collection.{immutable, mutable}

/** A base class for writing interactive compiler tests.
 * 
 *  This class tries to cover common functionality needed when testing the presentation
 *  compiler: instantiation source files, reloading, creating positions, instantiating 
 *  the presentation compiler, random stress testing.
 *  
 *  By default, this class loads all classes found under `src/`. They are found in 
 *  `sourceFiles`. Positions can be created using `pos(file, line, col)`. The presentation
 *  compiler is available through `compiler`.
 *  
 *  It is easy to test member completion and type at a given position. Source
 *  files are searched for /markers/. By default, the completion marker is `/*!*/` and the 
 *  typedAt marker is `/*?*/`. Place these markers in your source files, and call `completionTests`
 *  and `typedAtTests` to print the results at all these positions. Sources are reloaded by `reloadSources`
 *  (blocking call). All ask operations are placed on the work queue without waiting for each one to
 *  complete before asking the next. After all asks, it waits for each response in turn and prints the result.
 *  The default timout is 5 seconds per operation.
 *  
 *  The same mechanism can be used for custom operations. Use `askAllSources(marker)(f)(g)`. Give your custom
 *  marker, and provide the two functions: one for creating the request, and the second for processing the
 *  response, if it didn't time out and there was no error.
 *  
 *  @see   Check existing tests under test/files/presentation
 * 
 * @author Iulian Dragos
 */
abstract class InteractiveTest {
  
  val completionMarker = "/*!*/"
  val typedAtMarker = "/*?*/"
  val TIMEOUT = 10000 // timeout in milliseconds
  
  val settings = new Settings
  val reporter= new StoreReporter
  
  /** The root directory for this test suite, usually the test kind ("test/files/presentation"). */
  val outDir = Path(Option(System.getProperty("partest.cwd")).getOrElse("."))
  
  /** The base directory for this test, usually a subdirectory of "test/files/presentation/" */
  val baseDir = Option(System.getProperty("partest.testname")).map(outDir / _).getOrElse(Path("."))

  /** If there's a file ending in .opts, read it and parse it for cmd line arguments. */
  val argsString = {
    val optsFile = outDir / "%s.opts".format(System.getProperty("partest.testname"))
    val str = try File(optsFile).slurp() catch {
      case e: java.io.IOException => ""
    }
    str.lines.filter(!_.startsWith("#")).mkString(" ")
  }

  /** Prepare the settings object. Load the .opts file and adjust all paths from the 
   *  Unix-like syntax to the platform specific syntax. This is necessary so that a
   *  single .opts file can be used on all platforms.
   *  
   *  @note Bootclasspath is treated specially. If there is a -bootclasspath option in
   *        the file, the 'usejavacp' setting is set to false. This ensures that the
   *        bootclasspath takes precedence over the scala-library used to run the current
   *        test.
   */
  def prepareSettings() {
    import java.io.File._
    def adjustPaths(paths: settings.PathSetting*) {
      for (p <- paths if argsString.contains(p.name)) p.value = p.value.map {
        case '/' => separatorChar
        case ':' => pathSeparatorChar 
        case c => c
      }
    }
      
    // need this so that the classpath comes from what partest 
    // instead of scala.home
    settings.usejavacp.value = !argsString.contains("-bootclasspath")

    // pass any options coming from outside
    settings.processArgumentString(argsString) match {
      case (false, rest) =>
        println("error processing arguments (unprocessed: %s)".format(rest))
      case _ => ()
    }
    adjustPaths(settings.bootclasspath, settings.classpath, settings.javabootclasspath, settings.sourcepath)
  }

  protected def printClassPath() {
    println("\toutDir: %s".format(outDir.path))
    println("\tbaseDir: %s".format(baseDir.path))
    println("\targsString: %s".format(argsString))
    println("\tbootClassPath: %s".format(settings.bootclasspath.value))
    println("\tverbose: %b".format(settings.verbose.value))
  }
  
  lazy val compiler = {
    prepareSettings()
    new Global(settings, reporter)
  }

  def sources(filename: String*): Seq[SourceFile] =
    for (f <- filename) yield
      source(if (f.startsWith("/")) Path(f) else baseDir / f)

  def source(file: Path) = new BatchSourceFile(AbstractFile.getFile(file.toFile))
  def source(filename: String): SourceFile = new BatchSourceFile(AbstractFile.getFile(filename)) 
  
  def pos(file: SourceFile, line: Int, col: Int): Position = 
    file.position(line, col)
  
  def filesInDir(dir: Path): Iterator[Path]  = {
    dir.toDirectory.list.filter(_.isFile)
  }

  /** Where source files are placed. */
  val sourceDir = "src"
  
  /** All .scala files below "src" directory. */
  lazy val sourceFiles: Array[SourceFile] = 
    filesInDir(baseDir / sourceDir).filter(_.extension == "scala").map(source).toArray
  
  /** All positions of the given string in all source files. */
  def allPositionsOf(sources: Seq[SourceFile] = sourceFiles, str: String): immutable.Map[SourceFile, Seq[Position]] = {
    (for (s <- sources; p <- positionsOf(s, str)) yield p).groupBy(_.source) 
  }
  
  /** Return all positions of the given str in the given source file. */
  def positionsOf(source: SourceFile, str: String): Seq[Position] = {
    val buf = new mutable.ListBuffer[Position]
    var pos = source.content.indexOfSlice(str)
    while (pos >= 0) {
//      buf += compiler.rangePos(source, pos - 1, pos - 1, pos - 1)
      buf += source.position(pos - 1) // we need the position before the first character of this marker
      pos = source.content.indexOfSlice(str, pos + 1) 
    }
    buf.toList
  }
  
  /** Should askAllSources wait for each ask to finish before issueing the next? */
  val synchronousRequests = true

  /** Perform an operation on all sources at all positions that match the given
   *  marker string. For instance, askAllSources("/*!*/")(askTypeAt)(println) would
   *  ask the tyep at all positions marked with /*!*/ and println the result.
   */
  def askAllSourcesAsync[T](marker: String)(askAt: Position => Response[T])(f: (Position, T) => Unit) {
    val positions = allPositionsOf(str = marker).valuesIterator.toList.flatten
    val responses = for (pos <- positions) yield askAt(pos)

    for ((pos, r) <- positions zip responses) withResponse(pos, r)(f)
  }

  /** Synchronous version of askAllSources. Each position is treated in turn, waiting for the
   *  response before going to the next one. 
   */
  def askAllSourcesSync[T](marker: String)(askAt: Position => Response[T])(f: (Position, T) => Unit) {
    val positions = allPositionsOf(str = marker).valuesIterator.toList.flatten
    for (pos <- positions) withResponse(pos, askAt(pos))(f)
  }
  
  def askAllSources[T] = if (synchronousRequests) askAllSourcesSync[T] _ else askAllSourcesAsync[T] _
  
  /** Return the filename:line:col version of this position. */
  def showPos(pos: Position): String = 
    "%s:%d:%d".format(pos.source.file.name, pos.line, pos.column)
  
  protected def withResponse[T](pos: Position, response: Response[T])(f: (Position, T) => Unit) {
    response.get(TIMEOUT) match {
      case Some(Left(t)) =>
        f(pos, t)
      case None =>
        println("TIMEOUT: " + showPos(pos))
      case Some(r) =>
        println("ERROR: " + r)
    }
  }
  
  /** Ask completion for all marked positions in all sources.
   *  A completion position is marked with /*!*/. 
   */
  def completionTests() {
    askAllSources(completionMarker) { pos => 
      println("askTypeCompletion at " + pos.source.file.name + ((pos.line, pos.column)))
      val r = new Response[List[compiler.Member]]
      compiler.askTypeCompletion(pos, r)
      r
    } { (pos, members) =>
      println("\n" + "=" * 80)
      println("[response] aksTypeCompletion at " + (pos.line, pos.column))
      // we skip getClass because it changed signature between 1.5 and 1.6, so there is no
      // universal check file that we can provide for this to work
      println("retreived %d members".format(members.size))
      compiler ask { () =>
        println(members.sortBy(_.sym.name.toString).filterNot(_.sym.name.toString == "getClass").mkString("\n"))
      }
    }
  }
  
  /** Ask for typedAt for all marker positions in all sources.
   */
  def typeAtTests() {
    askAllSources(typedAtMarker) { pos =>
      println("askTypeAt at " + pos.source.file.name + ((pos.line, pos.column)))
      val r = new Response[compiler.Tree]
      compiler.askTypeAt(pos, r)
      r
    } { (pos, tree) =>
      println("[response] askTypeAt at " + (pos.line, pos.column))
      compiler.ask(() => println(tree))
    }
  }

  /** Reload the given source files and wait for them to be reloaded. */
  def reloadSources(sources: Seq[SourceFile] = sourceFiles) {
//    println("basedir: " + baseDir.path)
//    println("sourcedir: " + (baseDir / sourceDir).path)
    println("reload: " + sourceFiles.mkString("", ", ", ""))
    val reload = new Response[Unit]
    compiler.askReload(sourceFiles.toList, reload)
    reload.get
  }
    
  def runTest(): Unit = {
    if (runRandomTests) randomTests(20, sourceFiles)
    completionTests()
    typeAtTests()
  }
  
  /** Perform n random tests with random changes. */
  def randomTests(n: Int, files: Array[SourceFile]) {
    val tester = new Tester(n, files, settings)
    tester.run()
  }

  val runRandomTests = false
  
  def main(args: Array[String]) {
    reloadSources()
    runTest
    compiler.askShutdown()
  }
}

=======
package scala.tools.nsc
package interactive
package tests

import core._

import java.io.File.pathSeparatorChar
import java.io.File.separatorChar

import scala.annotation.migration
import scala.tools.nsc.util.Position
import scala.tools.nsc.util.SourceFile

import collection.mutable.ListBuffer

/** A base class for writing interactive compiler tests.
 *
 *  This class tries to cover common functionality needed when testing the presentation
 *  compiler: instantiation source files, reloading, creating positions, instantiating
 *  the presentation compiler, random stress testing.
 *
 *  By default, this class loads all scala and java classes found under `src/`, going
 *  recursively into subfolders. Loaded classes are found in `sourceFiles`. trait `TestResources`
 *  The presentation compiler is available through `compiler`.
 *
 *  It is easy to test member completion, type and hyperlinking at a given position. Source
 *  files are searched for `TextMarkers`. By default, the completion marker is `/*!*/`, the
 *  typedAt marker is `/*?*/` and the hyperlinking marker is `/*#*/`. Place these markers in
 *  your source files, and the test framework will automatically pick them up and test the
 *  corresponding actions. Sources are reloaded by `askReload(sourceFiles)` (blocking
 *  call). All ask operations are placed on the work queue without waiting for each one to
 *  complete before asking the next. After all asks, it waits for each response in turn and
 *  prints the result. The default timeout is 1 second per operation.
 *
 *  To define a custom operation you have to:
 *
 *  	(1) Define a new marker by extending `TestMarker`
 *  	(2) Provide an implementation for the operation you want to check by extending `PresentationCompilerTestDef`
 *  	(3) Add the class defined in (1) to the set of executed test actions by calling `++` on `InteractiveTest`.
 *
 *  Then you can simply use the new defined `marker` in your test sources and the testing
 *  framework will automatically pick it up.
 *
 *  @see   Check existing tests under test/files/presentation
 *
 *  @author Iulian Dragos
 *  @author Mirco Dotta
 */
abstract class InteractiveTest
  extends AskParse
  with AskShutdown
  with AskReload
  with AskLoadedTyped
  with PresentationCompilerInstance
  with CoreTestDefs
  with InteractiveTestSettings { self =>

  protected val runRandomTests = false

  /** Should askAllSources wait for each ask to finish before issuing the next? */
  override protected val synchronousRequests = true

  /** The core set of test actions that are executed during each test run are
   *  `CompletionAction`, `TypeAction` and `HyperlinkAction`.
   *  Override this member if you need to change the default set of executed test actions.
   */
  protected lazy val testActions: ListBuffer[PresentationCompilerTestDef] = {
    ListBuffer(new CompletionAction(compiler), new TypeAction(compiler), new HyperlinkAction(compiler))
  }

  /** Add new presentation compiler actions to test. Presentation compiler's test
   *  need to extends trait `PresentationCompilerTestDef`.
   */
  protected def ++(tests: PresentationCompilerTestDef*) {
    testActions ++= tests
  }

  /** Test's entry point */
  def main(args: Array[String]) {
    loadSources()
    runTests()
    shutdown()
  }

  /** Load all sources before executing the test. */
  private def loadSources() {
    // ask the presentation compiler to track all sources. We do
    // not wait for the file to be entirely typed because we do want
    // to exercise the presentation compiler on scoped type requests.
    askReload(sourceFiles)
    // make sure all sources are parsed before running the test. This
    // is because test may depend on the sources having been parsed at
    // least once
    askParse(sourceFiles)
  }

  /** Run all defined `PresentationCompilerTestDef` */
  protected def runTests() {
    //TODO: integrate random tests!, i.e.: if (runRandomTests) randomTests(20, sourceFiles)
    testActions.foreach(_.runTest())
  }

  /** Perform n random tests with random changes. */
  private def randomTests(n: Int, files: Array[SourceFile]) {
    val tester = new Tester(n, files, settings) {
      override val compiler = self.compiler
      override val reporter = compilerReporter
    }
    tester.run()
  }

  /** shutdown the presentation compiler. */
  protected def shutdown() {
    askShutdown()

    // this is actually needed to force exit on test completion.
    // Note: May be a bug on either the testing framework or (less likely)
    //           the presentation compiler
    sys.exit(0)
  }
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
