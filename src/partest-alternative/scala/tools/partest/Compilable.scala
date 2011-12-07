/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 */

package scala.tools
package partest

import scala.tools.nsc.io._
import scala.tools.nsc.{ Global, Settings, CompilerCommand, FatalError }
import scala.tools.nsc.util.{ ClassPath }
import scala.tools.nsc.reporters.{ Reporter, ConsoleReporter }

trait PartestCompilation {
  self: Universe =>
<<<<<<< HEAD
  
  trait CompileExecSupport extends ExecSupport {
    self: TestEntity =>
  
=======

  trait CompileExecSupport extends ExecSupport {
    self: TestEntity =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def javacpArg   = "-classpath " + testClasspath
    def scalacpArg  = "-usejavacp"

    /** Not used, requires tools.jar.
     */
    // def javacInternal(args: List[String]) = {
    //   import com.sun.tools.javac.Main
    //   Main.compile(args.toArray, logWriter)
    // }

    def javac(args: List[String]): Boolean = {
      val allArgString = fromArgs(javacpArg :: javacOpts :: args)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // javac -d outdir -classpath <basepath> <files>
      val cmd = "%s -d %s %s".format(javacCmd, outDir, allArgString)
      def traceMsg =
        if (isVerbose) cmd
        else "%s -d %s %s".format(tracePath(Path(javacCmd)), tracePath(outDir), fromArgs(args))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      trace(traceMsg)

      isDryRun || execAndLog(cmd)
    }
<<<<<<< HEAD
    
    def scalac(args: List[String]): Boolean = {
      val allArgs = assembleScalacArgs(args)      
=======

    def scalac(args: List[String]): Boolean = {
      val allArgs = assembleScalacArgs(args)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val (global, files) = newGlobal(allArgs)
      def nonFileArgs = if (isVerbose) global.settings.recreateArgs else assembleScalacArgs(Nil)
      def traceArgs   = fromArgs(nonFileArgs ++ (files map tracePath))
      def traceMsg    = "scalac " + traceArgs
<<<<<<< HEAD
    
      trace(traceMsg)
      isDryRun || global.partestCompile(files, true)
    }
      
=======

      trace(traceMsg)
      isDryRun || global.partestCompile(files, true)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Actually running the test, post compilation.
     *  Normally args will be List("Test", "jvm"), main class and arg to it.
     */
    def runScala(args: List[String]): Boolean = {
      val scalaRunnerClass = "scala.tools.nsc.MainGenericRunner"

      // java $JAVA_OPTS <javaopts> -classpath <cp>
      val javaCmdAndOptions = javaCmd +: assembleJavaArgs(List(javacpArg))
      // MainGenericRunner -usejavacp <scalacopts> Test jvm
      val scalaCmdAndOptions = List(scalaRunnerClass, scalacpArg) ++ assembleScalacArgs(args)
      // Assembled
      val cmd = fromArgs(javaCmdAndOptions ++ createPropertyString() ++ scalaCmdAndOptions)
<<<<<<< HEAD
      
      def traceMsg = if (isVerbose) cmd else fromArgs(javaCmd :: args)
      trace("runScala: " + traceMsg)
    
      isDryRun || execAndLog(cmd)
    }
    
    def newReporter(settings: Settings) = new ConsoleReporter(settings, Console.in, logWriter)

    class PartestGlobal(settings: Settings, val creporter: ConsoleReporter) extends Global(settings, creporter) {    
=======

      def traceMsg = if (isVerbose) cmd else fromArgs(javaCmd :: args)
      trace("runScala: " + traceMsg)

      isDryRun || execAndLog(cmd)
    }

    def newReporter(settings: Settings) = new ConsoleReporter(settings, Console.in, logWriter)

    class PartestGlobal(settings: Settings, val creporter: ConsoleReporter) extends Global(settings, creporter) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def partestCompile(files: List[String], printSummary: Boolean): Boolean = {
        try   { new Run compile files }
        catch {
          case FatalError(msg)    => creporter.error(null, "fatal error: " + msg)
          case ae: AssertionError => creporter.error(null, ""+ae)
          case te: TypeError      => creporter.error(null, ""+te)
          case ex                 =>
            creporter.error(null, ""+ex)
            throw ex
        }

        if (printSummary)
          creporter.printSummary
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        creporter.flush()
        !creporter.hasErrors
      }
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def newGlobal(args: List[String]): (PartestGlobal, List[String]) = {
      val settings  = category createSettings self
      val command   = new CompilerCommand(args, settings)
      val reporter  = newReporter(settings)
<<<<<<< HEAD
    
      if (!command.ok)
        debug("Error parsing arguments: '%s'".format(args mkString ", "))
    
=======

      if (!command.ok)
        debug("Error parsing arguments: '%s'".format(args mkString ", "))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      (new PartestGlobal(command.settings, reporter), command.files)
    }
  }
}
