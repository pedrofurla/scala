/*                     __                                               *\
**     ________ ___   / /  ___     Scala Ant Tasks                      **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.tools.ant.sabbus

import java.io.File

import scala.tools.nsc._
import scala.tools.nsc.reporters.ConsoleReporter

class ForeignCompiler {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private var argsBuffer: Array[String] = null
  def args: Array[String] = argsBuffer
  def args_=(a: Array[String]) {
    argsBuffer = a
    nsc
  }
<<<<<<< HEAD
  
  private val error: (String => Nothing) = { msg => throw new Exception(msg) }
  
  private def settings = new scala.tools.nsc.Settings(error)
  
  private lazy val reporter = new ConsoleReporter(settings)
  
=======

  private val error: (String => Nothing) = { msg => throw new Exception(msg) }

  private def settings = new scala.tools.nsc.Settings(error)

  private lazy val reporter = new ConsoleReporter(settings)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private lazy val nsc: Global = {
    try {
      val command = new CompilerCommand(args.toList, settings)
      new Global(command.settings, reporter)
    }
    catch {
      case ex @ FatalError(msg) =>
        throw new Exception(msg, ex)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def compile(files: Array[File]): Int = {
    val command = new CompilerCommand(files.toList map (_.toString), settings)
    (new nsc.Run) compile command.files
    reporter.ERROR.count << 16 | reporter.WARNING.count
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
