package scala.tools
package partest
package io

import java.io.{ StringWriter, PrintWriter, Writer }
import scala.tools.nsc.io._
import scala.util.control.ControlThrowable

trait Logging {
  universe: Universe =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class PartestANSIWriter extends ANSIWriter(Console.out) {
    override def colorful: Int = ANSIWriter(universe.isAnsi)
    private def printIf(cond: Boolean, msg: String) =
      if (cond) { outline("debug: ") ; println(msg) }
<<<<<<< HEAD
    
    val verbose = printIf(isVerbose || isDebug, _: String)
    val debug   = printIf(isDebug, _: String)
  }
  
  lazy val NestUI = new PartestANSIWriter()
  
  import NestUI.{ _outline, _success, _failure, _warning, _default }
  
=======

    val verbose = printIf(isVerbose || isDebug, _: String)
    val debug   = printIf(isDebug, _: String)
  }

  lazy val NestUI = new PartestANSIWriter()

  import NestUI.{ _outline, _success, _failure, _warning, _default }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def markOutline(msg: String) = _outline + msg + _default
  def markSuccess(msg: String) = _success + msg + _default
  def markFailure(msg: String) = _failure + msg + _default
  def markWarning(msg: String) = _warning + msg + _default
  def markNormal(msg: String)  = _default + msg

  def outline(msg: String) = NestUI outline msg
  def success(msg: String) = NestUI success msg
  def failure(msg: String) = NestUI failure msg
  def warning(msg: String) = NestUI warning msg
  def  normal(msg: String) = NestUI normal msg

  def verbose(msg: String) = NestUI verbose msg
  def   debug(msg: String) = NestUI debug msg
<<<<<<< HEAD
  
  trait EntityLogging {
    self: TestEntity =>
    
    lazy val logWriter = new LogWriter(logFile)
    
=======

  trait EntityLogging {
    self: TestEntity =>

    lazy val logWriter = new LogWriter(logFile)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Redirect stdout and stderr to logFile, run body, return result.
     */
    def loggingOutAndErr[T](body: => T): T = {
      val log = logFile.printStream(append = true)

      try Console.withOut(log) {
        Console.withErr(log) {
          body
        }
      }
      finally log.close()
    }

    /** What to print in a failure summary.
     */
    def failureMessage() = if (diffOutput != "") diffOutput else safeSlurp(logFile)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** For tracing.  Outputs a line describing the next action.  tracePath
     *  is a path wrapper which prints name or full path depending on verbosity.
     */
    def trace(msg: String) = if (isTrace || isDryRun) System.err.println(">> [%s] %s".format(label, msg))
<<<<<<< HEAD
    
    def tracePath(path: Path): String   = if (isVerbose) path.path else path.name
    def tracePath(path: String): String = tracePath(Path(path))
    
=======

    def tracePath(path: Path): String   = if (isVerbose) path.path else path.name
    def tracePath(path: String): String = tracePath(Path(path))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** v == verbose.
     */
    def vtrace(msg: String)   = if (isVerbose) trace(msg)

    /** Run body, writes result to logFile.  Any throwable is
     *  caught, stringified, and written to the log.
     */
    def loggingResult(body: => String) =
      try returning(true)(_ => logFile writeAll body)
      catch {
        case x: ControlThrowable      => throw x
        case x: InterruptedException  => debug(this + " received interrupt, failing.\n") ; false
<<<<<<< HEAD
        case x: Throwable             => logException(x) 
      }
    
=======
        case x: Throwable             => logException(x)
      }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def throwableToString(x: Throwable): String = {
      val w = new StringWriter
      x.printStackTrace(new PrintWriter(w))
      w.toString
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def warnAndLog(str: String) = {
      warning(toStringTrunc(str, 800))
      logWriter append str
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def warnAndLogException(msg: String, ex: Throwable) =
      warnAndLog(msg + throwableToString(ex))

    def deleteLog(force: Boolean = false) =
      if (universe.isNoCleanup && !force) debug("Not cleaning up " + logFile)
      else logFile.deleteIfExists()

    def onException(x: Throwable)    { logException(x) }
    def logException(x: Throwable) = {
      val msg = throwableToString(x)
      if (!isTerse)
        normal(msg)

      logWriter append msg
      false
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A writer which doesn't create the file until a write comes in.
   */
  class LazilyCreatedWriter(log: File) extends Writer {
    @volatile private var isCreated = false
    private lazy val underlying = {
      isCreated = true
      log.bufferedWriter()
    }

    def flush() = if (isCreated) underlying.flush()
    def close() = if (isCreated) underlying.close()
    def write(chars: Array[Char], off: Int, len: Int) = {
      underlying.write(chars, off, len)
      underlying.flush()
    }
  }

  class LogWriter(log: File) extends PrintWriter(new LazilyCreatedWriter(log), true) {
    override def print(s: String) = {
      super.print(s)
      flush()
    }
  }
}