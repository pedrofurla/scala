/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package partest

import nsc.io._
import cmd._

/** This takes advantage of bits of scala goodness to fully define a command
 *  line program with a minimum of duplicated code.  When the specification object
 *  is created, the vals are evaluated in order and each of them side effects
 *  a private accumulator.  What emerges is a full list of the valid unary
 *  and binary arguments, as well as autogenerated help.
 */
trait PartestSpec extends Spec with Meta.StdOpts with Interpolation {
  def referenceSpec       = PartestSpec
  def programInfo         = Spec.Info("partest", "", "scala.tools.partest.Runner")
  private val kind        = new Spec.Accumulator[String]()
  protected def testKinds = kind.get
<<<<<<< HEAD
  
  private implicit val tokenizeString = FromString.ArgumentsFromString    // String => List[String]
  
=======

  private implicit val tokenizeString = FromString.ArgumentsFromString    // String => List[String]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  help("""
    |# Pro Tip! Instant bash completion: `partest --bash` (note backticks)
    |Usage: partest [<options>] [<test> <test> ...]
    |  <test>: a path to a test designator, typically a .scala file or a directory.
    |          Examples: files/pos/test1.scala, files/res/bug785
    |
    |  Test categories:""".stripMargin)

  val isAll         = ("all"                / "run all tests (default, unless no options given)" --?)
                      (kind("pos")          / "Compile files that are expected to build" --?)
                      (kind("neg")          / "Compile files that are expected to fail" --?)
                      (kind("run")          / "Test JVM backend" --?)
                      (kind("jvm")          / "Test JVM backend" --?)
                      (kind("res")          / "Run resident compiler scenarii" --?)
                      (kind("buildmanager") / "Run Build Manager scenarii" --?)
                      (kind("scalacheck")   / "Run Scalacheck tests" --?)
                      (kind("script")       / "Run script files" --?)
                      (kind("shootout")     / "Run shootout tests" --?)
                      (kind("scalap")       / "Run scalap tests" --?)

  heading             ("""Test "smart" categories:""")
  val grepExpr      = "grep"        / "run all tests with a source file containing <expr>"    --|
  val isFailed      = "failed"      / "run all tests which failed on the last run"            --?
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  heading             ("Specifying paths and additional flags, ~ means repository root:")

  val rootDir       = "rootdir"     / "path from ~ to partest"                        defaultTo "test"
  val buildDir      = "builddir"    / "path from ~ to test build"                     defaultTo "build/pack"
  val srcDir        = "srcdir"      / "path from --rootdir to sources"                defaultTo "files"
  val javaOpts      = "javaopts"    / "flags to java on all runs"                  defaultToEnv "JAVA_OPTS"
  val javacOpts     = "javacopts"   / "flags to javac on all runs"                 defaultToEnv "JAVAC_OPTS"
  val scalacOpts    = "scalacopts"  / "flags to scalac on all tests"               defaultToEnv "SCALAC_OPTS"
<<<<<<< HEAD
  
                      "pack"        / ""                                               expandTo ("--builddir", "build/pack")
                      "quick"       / ""                                               expandTo ("--builddir", "build/quick")
  
=======

                      "pack"        / ""                                               expandTo ("--builddir", "build/pack")
                      "quick"       / ""                                               expandTo ("--builddir", "build/quick")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  heading             ("Options influencing output:")
  val isTrace       = "trace"       / "show the individual steps taken by each test" --?
  val isShowDiff    = "show-diff"   / "show diff between log and check file"  --?
  val isShowLog     = "show-log"    / "show log on failures" --?
  val isDryRun      = "dry-run"     / "do not run tests, only show their traces." --?
  val isTerse       = "terse"       / "be less verbose (almost silent except for failures)" --?
  val isVerbose     = "verbose"     / "be more verbose (additive with --trace)" --?
  val isDebug       = "debug"       / "maximum debugging output" --?
  val isAnsi        = "ansi"        / "print output in color" --?
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  heading             ("Other options:")
  val timeout       = "timeout"       / "Overall timeout in seconds"                defaultTo 7200
  val testWarning   = "test-warning"  / "Test warning in seconds"                   defaultTo 90
  val testTimeout   = "test-timeout"  / "Test timeout in seconds"                   defaultTo 900
  val isCleanup     = "cleanup"       / "delete all stale files and dirs before run" --?
  val isNoCleanup   = "nocleanup"     / "do not delete any logfiles or object dirs" --?
  val isStats       = "stats"         / "collect and print statistics about the tests" --?
  val isValidate    = "validate"      / "examine test filesystem for inconsistencies" --?
  val isUpdateCheck = "update-check"  / "overwrite checkFile if diff fails" --?
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                      "version"       / "print version"   --> runAndExit(println(Properties.versionMsg))

  // no help for anything below this line - secret options
  // mostly intended for property configuration.
  val runSets       = ("runsets"  --^) getOrElse Nil
  val isNoAlarms    = "noalarms"  --?
  val isInsideAnt   = "is-in-ant" --?
}

object PartestSpec extends PartestSpec with Property {
  lazy val propMapper = new PropertyMapper(PartestSpec) {
    override def isPassThrough(key: String) = key == "partest.options"
  }
<<<<<<< HEAD
  
  type ThisCommandLine = PartestCommandLine
  class PartestCommandLine(args: List[String]) extends SpecCommandLine(args) {
    override def errorFn(msg: String) = printAndExit("Error: " + msg)
    
    def propertyArgs = PartestSpec.propertyArgs
  }
  
=======

  type ThisCommandLine = PartestCommandLine
  class PartestCommandLine(args: List[String]) extends SpecCommandLine(args) {
    override def errorFn(msg: String) = printAndExit("Error: " + msg)

    def propertyArgs = PartestSpec.propertyArgs
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def creator(args: List[String]): PartestCommandLine = new PartestCommandLine(args)
}
