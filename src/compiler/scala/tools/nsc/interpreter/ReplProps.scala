/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.nsc
package interpreter

import scala.sys._

class ReplProps {
  private def bool(name: String) = BooleanProp.keyExists(name)
<<<<<<< HEAD
  
  val jlineDebug = bool("scala.tools.jline.internal.Log.debug")
  val jlineTrace = bool("scala.tools.jline.internal.Log.trace")
=======

  val jlineDebug = bool("scala.tools.jline.internal.Log.debug")
  val jlineTrace = bool("scala.tools.jline.internal.Log.trace")
  val noThreads  = bool("scala.repl.no-threads")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  val info  = bool("scala.repl.info")
  val debug = bool("scala.repl.debug")
  val trace = bool("scala.repl.trace")
  val power = bool("scala.repl.power")

  val replInitCode  = Prop[JFile]("scala.repl.initcode")
  val powerInitCode = Prop[JFile]("scala.repl.power.initcode")
  val powerBanner   = Prop[JFile]("scala.repl.power.banner")
}
<<<<<<< HEAD
=======

object ReplPropsKludge {
  // !!! short term binary compatibility hack for 2.9.1 to put this
  // here - needed a not previously existing object.
  def noThreadCreation(settings: Settings) = replProps.noThreads || settings.Yreplsync.value
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
