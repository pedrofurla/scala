/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 */

package scala.tools.nsc
package repl

import java.io.{ IOException, File => JFile }
import io.{ Path, File, Directory }
import java.nio.channels.ClosedByInterruptException
import jline.{ ConsoleReader, ArgumentCompletor, History => JHistory }

object Reader {
  /** Reads using standard JDK API */
  class Simple(
    in: BufferedReader, 
    out: PrintWriter, 
    val interactive: Boolean)
  extends Interactive {
    def this() = this(Console.in, new PrintWriter(Console.out), true)
    def this(in: File, out: PrintWriter, interactive: Boolean) = this(in.bufferedReader(), out, interactive)

    def close() = in.close()
    def readOneLine(prompt: String): String = {
      if (interactive) {
        out.print(prompt)
        out.flush()
      }
      in.readLine()
    }
  }
  
  /** Reads from the console using JLine */
  class JLine(interpreter: Interpreter) extends Interactive {
    def this() = this(null)

    val interactive = true
    lazy val consoleReader = new ReplConsoleReader init()
    override lazy val history = Some(History(consoleReader))
    override lazy val completion = Option(interpreter) map (x => new Completion(x))

    class ReplConsoleReader() extends ConsoleReader() {
      def init(): this.type = {
        this setHistory (History().jhistory)
        this setBellEnabled false 
        completion foreach { c =>
          this addCompletor c.jline
          this setAutoprintThreshhold 250
        }
        this
      }
    }      

    def readOneLine(prompt: String) = consoleReader readLine prompt
  }
  
  /** Reads lines from an input stream */
  trait Interactive {
    import Interactive._

    protected def readOneLine(prompt: String): String
    def interactive: Boolean

    def readLine(prompt: String): String =
      try readOneLine(prompt)
      catch {
        case e: ClosedByInterruptException          => println("Closed by interrupt!") ; System.exit(-1) ; error("")
        case e: IOException if restartSystemCall(e) => readLine(prompt)
      }

    // override if history is available
    def history: Option[History] = None
    def historyList = history map (_.asList) getOrElse Nil

    // override if completion is available
    def completion: Option[Completion] = None

    // hack necessary for OSX jvm suspension because read calls are not restarted after SIGTSTP
    private def restartSystemCall(e: Exception): Boolean =
      Properties.isMac && (e.getMessage == msgEINTR)
  }

  object Interactive {
    val msgEINTR = "Interrupted system call"

    def createDefault(): Interactive = createDefault(null)

    /** Create an interactive reader.  Uses <code>Reader.JLine</code> if the
     *  library is available, but otherwise uses a <code>Reader.Simple</code>. 
     */
    def createDefault(interpreter: Interpreter): Interactive =
      try new JLine(interpreter)
      catch { case _: Exception | _: NoClassDefFoundError => new Simple }
  }
}

