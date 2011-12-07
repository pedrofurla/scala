/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc

import settings.MutableSettings

/** A compatibility stub.
 */
class Settings(errorFn: String => Unit) extends MutableSettings(errorFn) {
  def this() = this(Console.println)
<<<<<<< HEAD
=======

  override def withErrorFn(errorFn: String => Unit): Settings = {
    val settings = new Settings(errorFn)
    copyInto(settings)
    settings
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
