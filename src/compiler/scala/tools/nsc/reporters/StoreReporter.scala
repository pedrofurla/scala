/* NSC -- new Scala compiler
 * Copyright 2002-2011 LAMP/EPFL
 * @author Martin Odersky
 */

package scala.tools.nsc
package reporters

import scala.collection.mutable
import scala.tools.nsc.util.Position

/**
 * This class implements a Reporter that displays messages on a text
 * console.
 */
class StoreReporter extends Reporter {
  class Info(val pos: Position, val msg: String, val severity: Severity) {
    override def toString() = "pos: " + pos + " " + msg + " " + severity
  }
<<<<<<< HEAD
  val infos = new mutable.HashSet[Info]
=======
  val infos = new mutable.LinkedHashSet[Info]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected def info0(pos: Position, msg: String, severity: Severity, force: Boolean) {
    if (!force) {
      infos += new Info(pos, msg, severity)
      severity.count += 1
    }
  }

  override def reset() {
    super.reset()
    infos.clear()
  }
}
