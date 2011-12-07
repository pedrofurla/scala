/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Alexander Spoon
 */

package scala.tools.nsc
package interpreter

import Predef.{ println => _, _ }
import java.io.{ BufferedReader, FileReader }
import java.util.concurrent.locks.ReentrantLock
import scala.sys.process.Process
import session._
import scala.tools.util.{ Signallable, Javap }
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.concurrent.ops
import util.{ ClassPath, Exceptional, stringFromWriter, stringFromStream }
import interpreter._
<<<<<<< HEAD
import io.{ File, Sources }
import scala.reflect.NameTransformer._
=======
import io.{ File, Sources, Directory }
import scala.reflect.NameTransformer._
import util.ScalaClassLoader
import ScalaClassLoader._
import scala.tools.util._
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/** The Scala interactive shell.  It provides a read-eval-print loop
 *  around the Interpreter class.
 *  After instantiation, clients should call the main() method.
 *
 *  If no in0 is specified, then input will come from the console, and
 *  the class will attempt to provide input editing feature such as
 *  input history.
 *
 *  @author Moez A. Abdel-Gawad
 *  @author  Lex Spoon
 *  @version 1.2
 */
class ILoop(in0: Option[BufferedReader], protected val out: JPrintWriter)
                extends AnyRef
                   with LoopCommands
                   with ILoopInit
{
  def this(in0: BufferedReader, out: JPrintWriter) = this(Some(in0), out)
  def this() = this(None, new JPrintWriter(Console.out, true))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  var in: InteractiveReader = _   // the input stream from which commands come
  var settings: Settings = _
  var intp: IMain = _

  override def echoCommandMessage(msg: String): Unit =
    intp.reporter.printMessage(msg)
<<<<<<< HEAD
    
  def isAsync = !settings.Yreplsync.value
  lazy val power = Power(this)
  
  // TODO
  // object opt extends AestheticSettings
  // 
  @deprecated("Use `intp` instead.", "2.9.0")
  def interpreter = intp
  
  @deprecated("Use `intp` instead.", "2.9.0")
  def interpreter_= (i: Interpreter): Unit = intp = i
  
=======

  def isAsync = !settings.Yreplsync.value
  lazy val power = Power(this)

  // TODO
  // object opt extends AestheticSettings
  //
  @deprecated("Use `intp` instead.", "2.9.0")
  def interpreter = intp

  @deprecated("Use `intp` instead.", "2.9.0")
  def interpreter_= (i: Interpreter): Unit = intp = i

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def history = in.history

  /** The context class loader at the time this object was created */
  protected val originalClassLoader = Thread.currentThread.getContextClassLoader

  // Install a signal handler so we can be prodded.
  private val signallable =
<<<<<<< HEAD
    if (isReplDebug) Signallable("Dump repl state.")(dumpCommand())
    else null
    
=======
    if (isReplDebug)
      Signallable("Dump repl state.")(dumpCommand())
    else null

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // classpath entries added via :cp
  var addedClasspath: String = ""

  /** A reverse list of commands to replay if the user requests a :replay */
  var replayCommandStack: List[String] = Nil

  /** A list of commands to replay if the user requests a :replay */
  def replayCommands = replayCommandStack.reverse

  /** Record a command for replay should the user request a :replay */
  def addReplay(cmd: String) = replayCommandStack ::= cmd

<<<<<<< HEAD
  /** Close the interpreter and set the var to null. */
  def closeInterpreter() {
    if (intp ne null) {
      intp.close
      intp = null
      removeSigIntHandler()
      Thread.currentThread.setContextClassLoader(originalClassLoader)
    }
  }
  
  class ILoopInterpreter extends IMain(settings, out) {
    override lazy val formatting = new Formatting {
      def prompt = ILoop.this.prompt
    }
    override protected def createLineManager() = new Line.Manager {
=======
  def savingReplayStack[T](body: => T): T = {
    val saved = replayCommandStack
    try body
    finally replayCommandStack = saved
  }
  def savingReader[T](body: => T): T = {
    val saved = in
    try body
    finally in = saved
  }

  /** Close the interpreter and set the var to null. */
  def closeInterpreter() {
    if (intp ne null) {
      intp.close()
      intp = null
      removeSigIntHandler()
    }
  }

  class ILoopInterpreter extends IMain(settings, out) {
    outer =>

    private class ThreadStoppingLineManager extends Line.Manager(parentClassLoader) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      override def onRunaway(line: Line[_]): Unit = {
        val template = """
          |// She's gone rogue, captain! Have to take her out!
          |// Calling Thread.stop on runaway %s with offending code:
          |// scala> %s""".stripMargin
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        echo(template.format(line.thread, line.code))
        // XXX no way to suppress the deprecation warning
        line.thread.stop()
        in.redrawLine()
      }
    }
<<<<<<< HEAD
=======
    override lazy val formatting = new Formatting {
      def prompt = ILoop.this.prompt
    }
    override protected def createLineManager(): Line.Manager =
      new ThreadStoppingLineManager

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override protected def parentClassLoader =
      settings.explicitParentLoader.getOrElse( classOf[ILoop].getClassLoader )
  }

  /** Create a new interpreter. */
  def createInterpreter() {
    if (addedClasspath != "")
      settings.classpath append addedClasspath
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    intp = new ILoopInterpreter
  }

  /** print a friendly help message */
  def helpCommand(line: String): Result = {
    if (line == "") helpSummary()
    else uniqueCommand(line) match {
      case Some(lc) => echo("\n" + lc.longHelp)
      case _        => ambiguousError(line)
    }
  }
  private def helpSummary() = {
    val usageWidth  = commands map (_.usageMsg.length) max
    val formatStr   = "%-" + usageWidth + "s %s %s"
<<<<<<< HEAD
    
    echo("All commands can be abbreviated, e.g. :he instead of :help.")
    echo("Those marked with a * have more detailed help, e.g. :help imports.\n")
    
=======

    echo("All commands can be abbreviated, e.g. :he instead of :help.")
    echo("Those marked with a * have more detailed help, e.g. :help imports.\n")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    commands foreach { cmd =>
      val star = if (cmd.hasLongHelp) "*" else " "
      echo(formatStr.format(cmd.usageMsg, star, cmd.help))
    }
  }
  private def ambiguousError(cmd: String): Result = {
    matchingCommands(cmd) match {
      case Nil  => echo(cmd + ": no such command.  Type :help for help.")
      case xs   => echo(cmd + " is ambiguous: did you mean " + xs.map(":" + _.name).mkString(" or ") + "?")
    }
<<<<<<< HEAD
    Result(true, None)    
=======
    Result(true, None)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
  private def matchingCommands(cmd: String) = commands filter (_.name startsWith cmd)
  private def uniqueCommand(cmd: String): Option[LoopCommand] = {
    // this lets us add commands willy-nilly and only requires enough command to disambiguate
    matchingCommands(cmd) match {
      case List(x)  => Some(x)
      // exact match OK even if otherwise appears ambiguous
      case xs       => xs find (_.name == cmd)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Show the history */
  lazy val historyCommand = new LoopCommand("history", "show the history (optional num is commands to show)") {
    override def usage = "[num]"
    def defaultLines = 20
<<<<<<< HEAD
    
    def apply(line: String): Result = {
      if (history eq NoHistory)
        return "No history available."
      
=======

    def apply(line: String): Result = {
      if (history eq NoHistory)
        return "No history available."

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val xs      = words(line)
      val current = history.index
      val count   = try xs.head.toInt catch { case _: Exception => defaultLines }
      val lines   = history.asStrings takeRight count
      val offset  = current - lines.size + 1

      for ((line, index) <- lines.zipWithIndex)
        echo("%3d  %s".format(index + offset, line))
    }
  }

  // When you know you are most likely breaking into the middle
  // of a line being typed.  This softens the blow.
  protected def echoAndRefresh(msg: String) = {
    echo("\n" + msg)
    in.redrawLine()
  }
  protected def echo(msg: String) = {
    out println msg
    out.flush()
  }
  protected def echoNoNL(msg: String) = {
    out print msg
    out.flush()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Search the history */
  def searchHistory(_cmdline: String) {
    val cmdline = _cmdline.toLowerCase
    val offset  = history.index - history.size + 1
<<<<<<< HEAD
    
    for ((line, index) <- history.asStrings.zipWithIndex ; if line.toLowerCase contains cmdline)
      echo("%d %s".format(index + offset, line))
  }
  
=======

    for ((line, index) <- history.asStrings.zipWithIndex ; if line.toLowerCase contains cmdline)
      echo("%d %s".format(index + offset, line))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private var currentPrompt = Properties.shellPromptString
  def setPrompt(prompt: String) = currentPrompt = prompt
  /** Prompt to print when awaiting input */
  def prompt = currentPrompt
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  import LoopCommand.{ cmd, nullary }

  /** Standard commands **/
  lazy val standardCommands = List(
    cmd("cp", "<path>", "add a jar or directory to the classpath", addClasspath),
    cmd("help", "[command]", "print this summary or command-specific help", helpCommand),
    historyCommand,
    cmd("h?", "<string>", "search the history", searchHistory),
    cmd("imports", "[name name ...]", "show import history, identifying sources of names", importsCommand),
    cmd("implicits", "[-v]", "show the implicits in scope", implicitsCommand),
    cmd("javap", "<path|class>", "disassemble a file or class name", javapCommand),
    nullary("keybindings", "show how ctrl-[A-Z] and other keys are bound", keybindingsCommand),
    cmd("load", "<path>", "load and interpret a Scala file", loadCommand),
    nullary("paste", "enter paste mode: all input up to ctrl-D compiled together", pasteCommand),
    nullary("power", "enable power user mode", powerCmd),
    nullary("quit", "exit the interpreter", () => Result(false, None)),
    nullary("replay", "reset execution and replay all previous commands", replay),
<<<<<<< HEAD
    shCommand,
    nullary("silent", "disable/enable automatic printing of results", verbosity),
    cmd("type", "<expr>", "display the type of an expression without evaluating it", typeCommand)
  )
  
=======
    nullary("reset", "reset the repl to its initial state, forgetting all session entries", resetCommand),
    // nullary("reset", "reset the interpreter, forgetting session values but retaining session types", replay),
    shCommand,
    nullary("silent", "disable/enable automatic printing of results", verbosity),
    cmd("type", "<expr>", "display the type of an expression without evaluating it", typeCommand),
    nullary("warnings", "show the suppressed warnings from the most recent line which had any", warningsCommand)
  )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Power user commands */
  lazy val powerCommands: List[LoopCommand] = List(
    nullary("dump", "displays a view of the interpreter's internal state", dumpCommand),
    cmd("phase", "<phase>", "set the implicit phase for power commands", phaseCommand),
    cmd("wrap", "<method>", "name of method to wrap around each repl line", wrapCommand) withLongHelp ("""
      |:wrap
      |:wrap clear
      |:wrap <method>
      |
      |Installs a wrapper around each line entered into the repl.
      |Currently it must be the simple name of an existing method
      |with the specific signature shown in the following example.
      |
      |def timed[T](body: => T): T = {
      |  val start = System.nanoTime
      |  try body
      |  finally println((System.nanoTime - start) + " nanos elapsed.")
      |}
      |:wrap timed
      |
      |If given no argument, :wrap names the wrapper installed.
      |An argument of clear will remove the wrapper if any is active.
      |Note that wrappers do not compose (a new one replaces the old
      |one) and also that the :phase command uses the same machinery,
<<<<<<< HEAD
      |so setting :wrap will clear any :phase setting.       
    """.stripMargin.trim)
  )
  
=======
      |so setting :wrap will clear any :phase setting.
    """.stripMargin.trim)
  )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def dumpCommand(): Result = {
    echo("" + power)
    history.asStrings takeRight 30 foreach echo
    in.redrawLine()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private val typeTransforms = List(
    "scala.collection.immutable." -> "immutable.",
    "scala.collection.mutable."   -> "mutable.",
    "scala.collection.generic."   -> "generic.",
    "java.lang."                  -> "jl.",
    "scala.runtime."              -> "runtime."
  )
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def importsCommand(line: String): Result = {
    val tokens    = words(line)
    val handlers  = intp.languageWildcardHandlers ++ intp.importHandlers
    val isVerbose = tokens contains "-v"

    handlers.filterNot(_.importedSymbols.isEmpty).zipWithIndex foreach {
      case (handler, idx) =>
        val (types, terms) = handler.importedSymbols partition (_.name.isTypeName)
        val imps           = handler.implicitSymbols
        val found          = tokens filter (handler importsSymbolNamed _)
        val typeMsg        = if (types.isEmpty) "" else types.size + " types"
        val termMsg        = if (terms.isEmpty) "" else terms.size + " terms"
        val implicitMsg    = if (imps.isEmpty) "" else imps.size + " are implicit"
        val foundMsg       = if (found.isEmpty) "" else found.mkString(" // imports: ", ", ", "")
        val statsMsg       = List(typeMsg, termMsg, implicitMsg) filterNot (_ == "") mkString ("(", ", ", ")")
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        intp.reporter.printMessage("%2d) %-30s %s%s".format(
          idx + 1,
          handler.importString,
          statsMsg,
          foundMsg
        ))
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def implicitsCommand(line: String): Result = {
    val intp = ILoop.this.intp
    import intp._
    import global.Symbol
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def p(x: Any) = intp.reporter.printMessage("" + x)

    // If an argument is given, only show a source with that
    // in its name somewhere.
    val args     = line split "\\s+"
    val filtered = intp.implicitSymbolsBySource filter {
      case (source, syms) =>
        (args contains "-v") || {
          if (line == "") (source.fullName.toString != "scala.Predef")
          else (args exists (source.name.toString contains _))
        }
    }
<<<<<<< HEAD
    
    if (filtered.isEmpty)
      return "No implicits have been imported other than those in Predef."
      
    filtered foreach {
      case (source, syms) =>
        p("/* " + syms.size + " implicit members imported from " + source.fullName + " */")
        
=======

    if (filtered.isEmpty)
      return "No implicits have been imported other than those in Predef."

    filtered foreach {
      case (source, syms) =>
        p("/* " + syms.size + " implicit members imported from " + source.fullName + " */")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // This groups the members by where the symbol is defined
        val byOwner = syms groupBy (_.owner)
        val sortedOwners = byOwner.toList sortBy { case (owner, _) => intp.afterTyper(source.info.baseClasses indexOf owner) }

        sortedOwners foreach {
          case (owner, members) =>
            // Within each owner, we cluster results based on the final result type
            // if there are more than a couple, and sort each cluster based on name.
            // This is really just trying to make the 100 or so implicits imported
            // by default into something readable.
            val memberGroups: List[List[Symbol]] = {
              val groups = members groupBy (_.tpe.finalResultType) toList
              val (big, small) = groups partition (_._2.size > 3)
              val xss = (
                (big sortBy (_._1.toString) map (_._2)) :+
                (small flatMap (_._2))
              )

              xss map (xs => xs sortBy (_.name.toString))
            }
<<<<<<< HEAD
          
            val ownerMessage = if (owner == source) " defined in " else " inherited from "            
            p("  /* " + members.size + ownerMessage + owner.fullName + " */")
            
=======

            val ownerMessage = if (owner == source) " defined in " else " inherited from "
            p("  /* " + members.size + ownerMessage + owner.fullName + " */")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            memberGroups foreach { group =>
              group foreach (s => p("  " + intp.symbolDefString(s)))
              p("")
            }
        }
        p("")
    }
  }
<<<<<<< HEAD
  
  protected def newJavap() = new Javap(intp.classLoader, new IMain.ReplStrippingWriter(intp)) {
=======

  private def addToolsJarToLoader() = {
    val javaHome = Directory(sys.env("JAVA_HOME"))
    val tools    = javaHome / "lib" / "tools.jar"
    if (tools.isFile) {
      echo("Found tools.jar, adding for use by javap.")
      ScalaClassLoader.fromURLs(Seq(tools.toURL), intp.classLoader)
    }
    else intp.classLoader
  }

  protected def newJavap() = new JavapClass(addToolsJarToLoader(), new IMain.ReplStrippingWriter(intp)) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def tryClass(path: String): Array[Byte] = {
      val hd :: rest = path split '.' toList;
      // If there are dots in the name, the first segment is the
      // key to finding it.
      if (rest.nonEmpty) {
        intp optFlatName hd match {
          case Some(flat) =>
            val clazz = flat :: rest mkString NAME_JOIN_STRING
            val bytes = super.tryClass(clazz)
            if (bytes.nonEmpty) bytes
            else super.tryClass(clazz + MODULE_SUFFIX_STRING)
          case _          => super.tryClass(path)
        }
      }
      else {
        // Look for Foo first, then Foo$, but if Foo$ is given explicitly,
        // we have to drop the $ to find object Foo, then tack it back onto
        // the end of the flattened name.
        def className  = intp flatName path
        def moduleName = (intp flatName path.stripSuffix(MODULE_SUFFIX_STRING)) + MODULE_SUFFIX_STRING

        val bytes = super.tryClass(className)
        if (bytes.nonEmpty) bytes
        else super.tryClass(moduleName)
      }
    }
  }
<<<<<<< HEAD
  private lazy val javap =
    try newJavap()
    catch { case _: Exception => null }
  
=======
  private lazy val javap = substituteAndLog[Javap]("javap", NoJavap)(newJavap())

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Still todo: modules.
  private def typeCommand(line: String): Result = {
    if (line.trim == "") ":type <expression>"
    else intp.typeOfExpression(line, false) match {
      case Some(tp) => intp.afterTyper(tp.toString)
      case _        => "" // the error message was already printed
    }
  }
<<<<<<< HEAD
  
=======
  private def warningsCommand(): Result = {
    intp.lastWarnings foreach { case (pos, msg) => intp.reporter.warning(pos, msg) }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def javapCommand(line: String): Result = {
    if (javap == null)
      return ":javap unavailable on this platform."
    if (line == "")
      return ":javap [-lcsvp] [path1 path2 ...]"
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    javap(words(line)) foreach { res =>
      if (res.isError) return "Failed: " + res.value
      else res.show()
    }
  }
  private def keybindingsCommand(): Result = {
    if (in.keyBindings.isEmpty) "Key bindings unavailable."
    else {
      echo("Reading jline properties for default key bindings.")
      echo("Accuracy not guaranteed: treat this as a guideline only.\n")
      in.keyBindings foreach (x => echo ("" + x))
    }
  }
  private def wrapCommand(line: String): Result = {
    def failMsg = "Argument to :wrap must be the name of a method with signature [T](=> T): T"
    val intp = ILoop.this.intp
    val g: intp.global.type = intp.global
    import g._

    words(line) match {
      case Nil            =>
        intp.executionWrapper match {
          case ""   => "No execution wrapper is set."
          case s    => "Current execution wrapper: " + s
        }
      case "clear" :: Nil =>
        intp.executionWrapper match {
          case ""   => "No execution wrapper is set."
          case s    => intp.clearExecutionWrapper() ; "Cleared execution wrapper."
        }
      case wrapper :: Nil =>
        intp.typeOfExpression(wrapper) match {
          case Some(PolyType(List(targ), MethodType(List(arg), restpe))) =>
            intp setExecutionWrapper intp.pathToTerm(wrapper)
            "Set wrapper to '" + wrapper + "'"
          case Some(x) =>
            failMsg + "\nFound: " + x
          case _ =>
            failMsg + "\nFound: <unknown>"
        }
      case _ => failMsg
    }
  }

  private def pathToPhaseWrapper = intp.pathToTerm("$r") + ".phased.atCurrent"
  private def phaseCommand(name: String): Result = {
    // This line crashes us in TreeGen:
    //
    //   if (intp.power.phased set name) "..."
    //
    // Exception in thread "main" java.lang.AssertionError: assertion failed: ._7.type
    //  at scala.Predef$.assert(Predef.scala:99)
    //  at scala.tools.nsc.ast.TreeGen.mkAttributedQualifier(TreeGen.scala:69)
    //  at scala.tools.nsc.ast.TreeGen.mkAttributedQualifier(TreeGen.scala:44)
    //  at scala.tools.nsc.ast.TreeGen.mkAttributedRef(TreeGen.scala:101)
    //  at scala.tools.nsc.ast.TreeGen.mkAttributedStableRef(TreeGen.scala:143)
    //
    // But it works like so, type annotated.
    val phased: Phased = power.phased
    import phased.NoPhaseName

    if (name == "clear") {
      phased.set(NoPhaseName)
      intp.clearExecutionWrapper()
      "Cleared active phase."
    }
    else if (name == "") phased.get match {
      case NoPhaseName => "Usage: :phase <expr> (e.g. typer, erasure.next, erasure+3)"
      case ph          => "Active phase is '%s'.  (To clear, :phase clear)".format(phased.get)
    }
    else {
      val what = phased.parse(name)
<<<<<<< HEAD
      if (what.isEmpty || !phased.set(what)) 
=======
      if (what.isEmpty || !phased.set(what))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        "'" + name + "' does not appear to represent a valid phase."
      else {
        intp.setExecutionWrapper(pathToPhaseWrapper)
        val activeMessage =
          if (what.toString.length == name.length) "" + what
          else "%s (%s)".format(what, name)
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        "Active phase is now: " + activeMessage
      }
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Available commands */
  def commands: List[LoopCommand] = standardCommands ++ (
    if (isReplPower) powerCommands else Nil
  )
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val replayQuestionMessage =
    """|That entry seems to have slain the compiler.  Shall I replay
       |your session? I can re-run each line except the last one.
       |[y/n]
    """.trim.stripMargin

<<<<<<< HEAD
  private val crashRecovery: PartialFunction[Throwable, Unit] = {
=======
  private val crashRecovery: PartialFunction[Throwable, Boolean] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    case ex: Throwable =>
      if (settings.YrichExes.value) {
        val sources = implicitly[Sources]
        echo("\n" + ex.getMessage)
        echo(
          if (isReplDebug) "[searching " + sources.path + " for exception contexts...]"
          else "[searching for exception contexts...]"
        )
      }
      echo(intp.global.throwableAsString(ex))

      ex match {
        case _: NoSuchMethodError | _: NoClassDefFoundError =>
          echo("\nUnrecoverable error.")
          throw ex
        case _  =>
          def fn(): Boolean =
            try in.readYesOrNo(replayQuestionMessage, { echo("\nYou must enter y or n.") ; fn() })
            catch { case _: RuntimeException => false }

          if (fn()) replay()
          else echo("\nAbandoning crashed session.")
      }
<<<<<<< HEAD
=======
      true
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  /** The main read-eval-print loop for the repl.  It calls
   *  command() for each line of input, and stops when
   *  command() returns false.
   */
  def loop() {
    def readOneLine() = {
      out.flush()
      in readLine prompt
    }
    // return false if repl should exit
    def processLine(line: String): Boolean = {
      if (isAsync) {
        awaitInitialized()
        runThunks()
      }
      if (line eq null) false               // assume null means EOF
      else command(line) match {
        case Result(false, _)           => false
        case Result(_, Some(finalLine)) => addReplay(finalLine) ; true
        case _                          => true
      }
    }
<<<<<<< HEAD

    while (true) {
      try if (!processLine(readOneLine())) return
      catch crashRecovery
    }
  }

  /** interpret all lines from a specified file */
  def interpretAllFrom(file: File) {    
    val oldIn = in
    val oldReplay = replayCommandStack
    
    try file applyReader { reader =>
      in = SimpleReader(reader, out, false)
      echo("Loading " + file + "...")
      loop()
    }
    finally {
      in = oldIn
      replayCommandStack = oldReplay
    }
  }

  /** create a new interpreter and replay all commands so far */
  def replay() {
    closeInterpreter()
    createInterpreter()
    for (cmd <- replayCommands) {
=======
    def innerLoop() {
      if ( try processLine(readOneLine()) catch crashRecovery )
        innerLoop()
    }
    innerLoop()
  }

  /** interpret all lines from a specified file */
  def interpretAllFrom(file: File) {
    savingReader {
      savingReplayStack {
        file applyReader { reader =>
          in = SimpleReader(reader, out, false)
          echo("Loading " + file + "...")
          loop()
        }
      }
    }
  }

  /** create a new interpreter and replay the given commands */
  def replay() {
    reset()
    if (replayCommandStack.isEmpty)
      echo("Nothing to replay.")
    else for (cmd <- replayCommands) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      echo("Replaying: " + cmd)  // flush because maybe cmd will have its own output
      command(cmd)
      echo("")
    }
  }
<<<<<<< HEAD
  
=======
  def resetCommand() {
    echo("Resetting interpreter state.")
    if (replayCommandStack.nonEmpty) {
      echo("Forgetting this session history:\n")
      replayCommands foreach echo
      echo("")
      replayCommandStack = Nil
    }
    if (intp.namedDefinedTerms.nonEmpty)
      echo("Forgetting all expression results and named terms: " + intp.namedDefinedTerms.mkString(", "))
    if (intp.definedTypes.nonEmpty)
      echo("Forgetting defined types: " + intp.definedTypes.mkString(", "))

    reset()
  }
  def reset() {
    intp.reset()
    unleashAndSetPhase()
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** fork a shell and run a command */
  lazy val shCommand = new LoopCommand("sh", "run a shell command (result is implicitly => List[String])") {
    override def usage = "<command line>"
    def apply(line: String): Result = line match {
      case ""   => showUsage()
<<<<<<< HEAD
      case _    => 
=======
      case _    =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val toRun = classOf[ProcessResult].getName + "(" + string2codeQuoted(line) + ")"
        intp interpret toRun
        ()
    }
  }
<<<<<<< HEAD
  
  def withFile(filename: String)(action: File => Unit) {
    val f = File(filename)
    
    if (f.exists) action(f)
    else echo("That file does not exist")
  }
  
=======

  def withFile(filename: String)(action: File => Unit) {
    val f = File(filename)

    if (f.exists) action(f)
    else echo("That file does not exist")
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def loadCommand(arg: String) = {
    var shouldReplay: Option[String] = None
    withFile(arg)(f => {
      interpretAllFrom(f)
      shouldReplay = Some(":load " + arg)
    })
    Result(true, shouldReplay)
  }

  def addClasspath(arg: String): Unit = {
    val f = File(arg).normalize
    if (f.exists) {
      addedClasspath = ClassPath.join(addedClasspath, f.path)
      val totalClasspath = ClassPath.join(settings.classpath.value, addedClasspath)
      echo("Added '%s'.  Your new classpath is:\n\"%s\"".format(f.path, totalClasspath))
      replay()
    }
    else echo("The path '" + f + "' doesn't seem to exist.")
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def powerCmd(): Result = {
    if (isReplPower) "Already in power mode."
    else enablePowerMode(false)
  }
  def enablePowerMode(isDuringInit: Boolean) = {
    replProps.power setValue true
<<<<<<< HEAD
    power.unleash()
    intp.beSilentDuring(phaseCommand("typer"))
    if (isDuringInit) asyncMessage(power.banner)
    else echo(power.banner)
  }
  
=======
    unleashAndSetPhase()
    asyncEcho(isDuringInit, power.banner)
  }
  private def unleashAndSetPhase() {
    if (isReplPower) {
      power.unleash()
      // Set the phase to "typer"
      intp beSilentDuring phaseCommand("typer")
    }
  }

  def asyncEcho(async: Boolean, msg: => String) {
    if (async) asyncMessage(msg)
    else echo(msg)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def verbosity() = {
    val old = intp.printResults
    intp.printResults = !old
    echo("Switched " + (if (old) "off" else "on") + " result printing.")
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Run one command submitted by the user.  Two values are returned:
    * (1) whether to keep running, (2) the line to record for replay,
    * if any. */
  def command(line: String): Result = {
    if (line startsWith ":") {
      val cmd = line.tail takeWhile (x => !x.isWhitespace)
      uniqueCommand(cmd) match {
        case Some(lc) => lc(line.tail stripPrefix cmd dropWhile (_.isWhitespace))
        case _        => ambiguousError(cmd)
      }
    }
    else if (intp.global == null) Result(false, None)  // Notice failure to create compiler
    else Result(true, interpretStartingWith(line))
  }
<<<<<<< HEAD
  
  private def readWhile(cond: String => Boolean) = {
    Iterator continually in.readLine("") takeWhile (x => x != null && cond(x))
  }
  
=======

  private def readWhile(cond: String => Boolean) = {
    Iterator continually in.readLine("") takeWhile (x => x != null && cond(x))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def pasteCommand(): Result = {
    echo("// Entering paste mode (ctrl-D to finish)\n")
    val code = readWhile(_ => true) mkString "\n"
    echo("\n// Exiting paste mode, now interpreting.\n")
    intp interpret code
    ()
  }
<<<<<<< HEAD
    
  private object paste extends Pasted {
    val ContinueString = "     | "
    val PromptString   = "scala> "
    
=======

  private object paste extends Pasted {
    val ContinueString = "     | "
    val PromptString   = "scala> "

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def interpret(line: String): Unit = {
      echo(line.trim)
      intp interpret line
      echo("")
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def transcript(start: String) = {
      echo("\n// Detected repl transcript paste: ctrl-D to finish.\n")
      apply(Iterator(start) ++ readWhile(_.trim != PromptString.trim))
    }
  }
  import paste.{ ContinueString, PromptString }

  /** Interpret expressions starting with the first line.
    * Read lines until a complete compilation unit is available
    * or until a syntax error has been seen.  If a full unit is
    * read, go ahead and interpret it.  Return the full string
    * to be recorded for replay, if any.
    */
  def interpretStartingWith(code: String): Option[String] = {
    // signal completion non-completion input has been received
    in.completion.resetVerbosity()
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def reallyInterpret = {
      val reallyResult = intp.interpret(code)
      (reallyResult, reallyResult match {
        case IR.Error       => None
        case IR.Success     => Some(code)
        case IR.Incomplete  =>
          if (in.interactive && code.endsWith("\n\n")) {
            echo("You typed two blank lines.  Starting a new command.")
            None
<<<<<<< HEAD
          } 
=======
          }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          else in.readLine(ContinueString) match {
            case null =>
              // we know compilation is going to fail since we're at EOF and the
              // parser thinks the input is still incomplete, but since this is
              // a file being read non-interactively we want to fail.  So we send
              // it straight to the compiler for the nice error message.
              intp.compileString(code)
              None

            case line => interpretStartingWith(code + "\n" + line)
          }
      })
    }
<<<<<<< HEAD
    
    /** Here we place ourselves between the user and the interpreter and examine
     *  the input they are ostensibly submitting.  We intervene in several cases:
     * 
=======

    /** Here we place ourselves between the user and the interpreter and examine
     *  the input they are ostensibly submitting.  We intervene in several cases:
     *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *  1) If the line starts with "scala> " it is assumed to be an interpreter paste.
     *  2) If the line starts with "." (but not ".." or "./") it is treated as an invocation
     *     on the previous result.
     *  3) If the Completion object's execute returns Some(_), we inject that value
     *     and avoid the interpreter, as it's likely not valid scala code.
     */
    if (code == "") None
    else if (!paste.running && code.trim.startsWith(PromptString)) {
      paste.transcript(code)
      None
    }
    else if (Completion.looksLikeInvocation(code) && intp.mostRecentVar != "") {
      interpretStartingWith(intp.mostRecentVar + code)
    }
    else {
      def runCompletion = in.completion execute code map (intp bindValue _)
      /** Due to my accidentally letting file completion execution sneak ahead
       *  of actual parsing this now operates in such a way that the scala
       *  interpretation always wins.  However to avoid losing useful file
       *  completion I let it fail and then check the others.  So if you
       *  type /tmp it will echo a failure and then give you a Directory object.
       *  It's not pretty: maybe I'll implement the silence bits I need to avoid
       *  echoing the failure.
       */
      if (intp isParseable code) {
        val (code, result) = reallyInterpret
        if (power != null && code == IR.Error)
          runCompletion
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        result
      }
      else runCompletion match {
        case Some(_)  => None // completion hit: avoid the latent error
        case _        => reallyInterpret._2  // trigger the latent error
      }
    }
  }

  // runs :load `file` on any files passed via -i
  def loadFiles(settings: Settings) = settings match {
    case settings: GenericRunnerSettings =>
      for (filename <- settings.loadfiles.value) {
        val cmd = ":load " + filename
        command(cmd)
        addReplay(cmd)
        echo("")
      }
    case _ =>
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Tries to create a JLineReader, falling back to SimpleReader:
   *  unless settings or properties are such that it should start
   *  with SimpleReader.
   */
  def chooseReader(settings: Settings): InteractiveReader = {
    if (settings.Xnojline.value || Properties.isEmacsShell)
      SimpleReader()
    else try new JLineReader(
      if (settings.noCompletion.value) NoCompletion
      else new JLineCompletion(intp)
    )
    catch {
      case ex @ (_: Exception | _: NoClassDefFoundError) =>
        echo("Failed to created JLineReader: " + ex + "\nFalling back to SimpleReader.")
        SimpleReader()
    }
  }
<<<<<<< HEAD
  def process(settings: Settings): Boolean = {
    this.settings = settings
    createInterpreter()
    
=======
  def process(settings: Settings): Boolean = savingContextLoader {
    this.settings = settings
    createInterpreter()

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // sets in to some kind of reader depending on environmental cues
    in = in0 match {
      case Some(reader) => SimpleReader(reader, out, true)
      case None         =>
        // some post-initialization
        chooseReader(settings) match {
          case x: JLineReader => addThunk(x.consoleReader.postInit) ; x
          case x              => x
        }
    }

    loadFiles(settings)
    // it is broken on startup; go ahead and exit
    if (intp.reporter.hasErrors)
      return false
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // This is about the illusion of snappiness.  We call initialize()
    // which spins off a separate thread, then print the prompt and try
    // our best to look ready.  The interlocking lazy vals tend to
    // inter-deadlock, so we break the cycle with a single asynchronous
    // message to an actor.
    if (isAsync) {
      intp initialize initializedCallback()
      createAsyncListener() // listens for signal to run postInitialization
    }
    else {
      intp.initializeSynchronous()
      postInitialization()
    }
    printWelcome()

    try loop()
    catch AbstractOrMissingHandler()
    finally closeInterpreter()

    true
  }

  /** process command-line arguments and do as they request */
  def process(args: Array[String]): Boolean = {
    val command = new CommandLine(args.toList, msg => echo("scala: " + msg))
    def neededHelp(): String =
      (if (command.settings.help.value) command.usageMsg + "\n" else "") +
      (if (command.settings.Xhelp.value) command.xusageMsg + "\n" else "")
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // if they asked for no help and command is valid, we call the real main
    neededHelp() match {
      case ""     => command.ok && process(command.settings)
      case help   => echoNoNL(help) ; true
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @deprecated("Use `process` instead", "2.9.0")
  def main(args: Array[String]): Unit = {
    if (isReplDebug)
      System.out.println(new java.util.Date)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    process(args)
  }
  @deprecated("Use `process` instead", "2.9.0")
  def main(settings: Settings): Unit = process(settings)
}

object ILoop {
  implicit def loopToInterpreter(repl: ILoop): IMain = repl.intp
  private def echo(msg: String) = Console println msg

  // Designed primarily for use by test code: take a String with a
  // bunch of code, and prints out a transcript of what it would look
  // like if you'd just typed it into the repl.
  def runForTranscript(code: String, settings: Settings): String = {
    import java.io.{ BufferedReader, StringReader, OutputStreamWriter }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    stringFromStream { ostream =>
      Console.withOut(ostream) {
        val output = new JPrintWriter(new OutputStreamWriter(ostream), true) {
          override def write(str: String) = {
            // completely skip continuation lines
            if (str forall (ch => ch.isWhitespace || ch == '|')) ()
            // print a newline on empty scala prompts
            else if ((str contains '\n') && (str.trim == "scala> ")) super.write("\n")
            else super.write(str)
          }
        }
        val input = new BufferedReader(new StringReader(code)) {
          override def readLine(): String = {
            val s = super.readLine()
            // helping out by printing the line being interpreted.
            if (s != null)
              output.println(s)
            s
          }
        }
        val repl = new ILoop(input, output)
        if (settings.classpath.isDefault)
          settings.classpath.value = sys.props("java.class.path")

        repl process settings
      }
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Creates an interpreter loop with default settings and feeds
   *  the given code to it as input.
   */
  def run(code: String, sets: Settings = new Settings): String = {
    import java.io.{ BufferedReader, StringReader, OutputStreamWriter }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    stringFromStream { ostream =>
      Console.withOut(ostream) {
        val input    = new BufferedReader(new StringReader(code))
        val output   = new JPrintWriter(new OutputStreamWriter(ostream), true)
        val repl     = new ILoop(input, output)
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (sets.classpath.isDefault)
          sets.classpath.value = sys.props("java.class.path")

        repl process sets
      }
    }
  }
  def run(lines: List[String]): String = run(lines map (_ + "\n") mkString)

  // provide the enclosing type T
  // in order to set up the interpreter's classpath and parent class loader properly
  def breakIf[T: Manifest](assertion: => Boolean, args: NamedParam*): Unit =
    if (assertion) break[T](args.toList)

  // start a repl, binding supplied args
<<<<<<< HEAD
  def break[T: Manifest](args: List[NamedParam]): Unit = {
=======
  def break[T: Manifest](args: List[NamedParam]): Unit = savingContextLoader {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val msg = if (args.isEmpty) "" else "  Binding " + args.size + " value%s.".format(
      if (args.size == 1) "" else "s"
    )
    echo("Debug repl starting." + msg)
    val repl = new ILoop {
      override def prompt = "\ndebug> "
    }
    repl.settings = new Settings(echo)
    repl.settings.embeddedDefaults[T]
    repl.createInterpreter()
    repl.in = new JLineReader(new JLineCompletion(repl))

    // rebind exit so people don't accidentally call sys.exit by way of predef
    repl.quietRun("""def exit = println("Type :quit to resume program execution.")""")
    args foreach (p => repl.bind(p.name, p.tpe, p.value))
    repl.loop()

    echo("\nDebug repl exiting.")
    repl.closeInterpreter()
<<<<<<< HEAD
  }  
=======
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}