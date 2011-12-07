/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.cmd
package gen

class Codegen(args: List[String]) extends {
  val parsed = CodegenSpec(args: _*)
} with CodegenSpec with Instance { }

object Codegen {
  def echo(msg: String) = Console println msg

<<<<<<< HEAD
  def main(args0: Array[String]): Unit = {    
    val runner = new Codegen(args0.toList)
    import runner._
    
=======
  def main(args0: Array[String]): Unit = {
    val runner = new Codegen(args0.toList)
    import runner._

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (args0.isEmpty)
      return println (CodegenSpec.helpMsg)

    val out = outDir getOrElse { return println("--out is required.") }
    val all = genall || (!anyvals && !products)
<<<<<<< HEAD
    
    echo("Generating sources into " + out)
    
=======

    echo("Generating sources into " + out)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (anyvals || all) {
      val av = new AnyVals { }

      av.make() foreach { case (name, code ) =>
        val file = out / (name + ".scala") toFile;
        echo("Writing: " + file)
        file writeAll code
      }
    }
  }
}

