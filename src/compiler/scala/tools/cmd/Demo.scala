/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package cmd

/** A sample command specification for illustrative purposes.
 *  First take advantage of the meta-options:
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    // this command creates an executable runner script "demo"
 *    % scala scala.tools.cmd.Demo --self-update demo
 *
 *    // this one creates and sources a completion file - note backticks
 *    % `./demo --bash`
 *
 *    // and now you have a runner with working completion
 *    % ./demo --<tab>
 *       --action           --defint           --int
 *       --bash             --defstr           --str
 *       --defenv           --self-update      --unary
 *
 *  The normal option configuration is plausibly self-explanatory.
 */
trait DemoSpec extends Spec with Meta.StdOpts with Interpolation {
  lazy val referenceSpec  = DemoSpec
  lazy val programInfo    = Spec.Info("demo", "Usage: demo [<options>]", "scala.tools.cmd.Demo")

  help("""Usage: demo [<options>]""")
  heading("Unary options:")

  val optIsUnary      = "unary"         / "a unary option"              --?  ;
  ("action" / "a body which may be run") --> println("Hello, I am the --action body.")
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  heading("Binary options:")
  val optopt          = "str"       / "an optional String"        --|
  val optoptInt       = ("int"      / "an optional Int") .        --^[Int]
  val optEnv          = "defenv"    / "an optional String"        defaultToEnv  "PATH"
  val optDefault      = "defstr"    / "an optional String"        defaultTo     "default"
  val optDefaultInt   = "defint"    / "an optional Int"           defaultTo     -1
  val optExpand       = "alias"     / "an option which expands"   expandTo      ("--int", "15")
}

object DemoSpec extends DemoSpec with Property {
  lazy val propMapper = new PropertyMapper(DemoSpec)

  type ThisCommandLine = SpecCommandLine
  def creator(args: List[String]) =
    new SpecCommandLine(args) {
      override def errorFn(msg: String) = { println("Error: " + msg) ; sys.exit(0) }
    }
}

class Demo(args: List[String]) extends {
  val parsed = DemoSpec(args: _*)
} with DemoSpec with Instance {
  import java.lang.reflect._

  def helpMsg = DemoSpec.helpMsg
  def demoSpecMethods = this.getClass.getMethods.toList
<<<<<<< HEAD
  private def isDemo(m: Method) = (m.getName startsWith "opt") && !(m.getName contains "$") && (m.getParameterTypes.isEmpty)  
=======
  private def isDemo(m: Method) = (m.getName startsWith "opt") && !(m.getName contains "$") && (m.getParameterTypes.isEmpty)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  def demoString(ms: List[Method]) = {
    val longest   = ms map (_.getName.length) max
    val formatStr = "    %-" + longest + "s: %s"
    val xs        = ms map (m => formatStr.format(m.getName, m.invoke(this)))
<<<<<<< HEAD
    
    xs mkString ("Demo(\n  ", "\n  ", "\n)\n")
  }
    
=======

    xs mkString ("Demo(\n  ", "\n  ", "\n)\n")
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toString = demoString(demoSpecMethods filter isDemo)
}

object Demo {
  def main(args: Array[String]): Unit = {
    val runner = new Demo(args.toList)

    if (args.isEmpty)
      println(runner.helpMsg)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    println(runner)
  }
}
