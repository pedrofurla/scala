/* NSC -- new Scala compiler
 * Copyright 2002-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools.nsc
package interpreter

import reporters._
import IMain._

<<<<<<< HEAD
class ReplReporter(intp: IMain) extends ConsoleReporter(intp.settings, null, new ReplStrippingWriter(intp)) {
=======
class ReplReporter(intp: IMain) extends ConsoleReporter(intp.settings, Console.in, new ReplStrippingWriter(intp)) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def printMessage(msg: String) {
    // Avoiding deadlock if the compiler starts logging before
    // the lazy val is complete.
    if (intp.isInitializeComplete) {
      if (intp.totalSilence) ()
      else super.printMessage(msg)
    }
    else Console.println("[init] " + msg)
  }
<<<<<<< HEAD
=======

  override def displayPrompt() {
    if (intp.totalSilence) ()
    else super.displayPrompt()
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
