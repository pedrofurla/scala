// $Id$

package scala.tools.selectivecps

import scala.tools.nsc
import scala.tools.nsc.typechecker._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

class SelectiveCPSPlugin(val global: Global) extends Plugin {
  import global._

  val name = "continuations"
  val description = "applies selective cps conversion"
<<<<<<< HEAD
  
  val anfPhase = new SelectiveANFTransform() { 
    val global = SelectiveCPSPlugin.this.global
    val runsAfter = List("pickler") 
  }
  
  val cpsPhase = new SelectiveCPSTransform() { 
    val global = SelectiveCPSPlugin.this.global
    val runsAfter = List("selectiveanf")
  }
  
  
=======

  val anfPhase = new SelectiveANFTransform() {
    val global = SelectiveCPSPlugin.this.global
    val runsAfter = List("pickler")
  }

  val cpsPhase = new SelectiveCPSTransform() {
    val global = SelectiveCPSPlugin.this.global
    val runsAfter = List("selectiveanf")
    override val runsBefore = List("uncurry")
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val components = List[PluginComponent](anfPhase, cpsPhase)

  val checker = new CPSAnnotationChecker {
    val global: SelectiveCPSPlugin.this.global.type = SelectiveCPSPlugin.this.global
  }
  global.addAnnotationChecker(checker.checker)

  global.log("instantiated cps plugin: " + this)

  def setEnabled(flag: Boolean) = {
    checker.cpsEnabled = flag
    anfPhase.cpsEnabled = flag
    cpsPhase.cpsEnabled = flag
  }

  // TODO: require -enabled command-line flag
<<<<<<< HEAD
  
  override def processOptions(options: List[String], error: String => Unit) = {
    var enabled = false
    for (option <- options) {
      if (option == "enable") {
        enabled = true
      } else {
        error("Option not understood: "+option)
      }
=======
  override def processOptions(options: List[String], error: String => Unit) = {
    var enabled = true
    options foreach {
      case "enable"    => enabled = true
      case "disable"   => enabled = false
      case option      => error("Option not understood: "+option)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
    setEnabled(enabled)
  }

<<<<<<< HEAD
  override val optionsHelp: Option[String] =
    Some("  -P:continuations:enable        Enable continuations")
=======
  override val optionsHelp: Option[String] = {
    Some("  -P:continuations:disable        Disable continuations plugin")
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
