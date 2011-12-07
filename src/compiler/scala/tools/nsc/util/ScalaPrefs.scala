/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package util

import io.Sources

trait ScalaPrefs {
  def codeSources: Sources
  def exceptionFormatter: Exceptional.Formatter
}

<<<<<<< HEAD
trait LowPriorityScalaPrefs {  
=======
trait LowPriorityScalaPrefs {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit object DefaultScalaPrefs extends ScalaPrefs {
    def codeSources        = Sources.defaultSources
    def exceptionFormatter = Exceptional.Formatter(this)
  }
}

object ScalaPrefs extends LowPriorityScalaPrefs {
  def apply(implicit prefs: ScalaPrefs): ScalaPrefs = prefs
}
