/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author Philipp Haller
 */

package scala.tools
package partest

import nsc.io._

object Runner {
  def main(args: Array[String]) {
    val runner = Partest(args: _*)
    import runner._

    if (args.isEmpty) return println(helpMsg)
    if (isValidate) return validateAll()
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    printConfigBanner()

    if (isCleanup)
      cleanupAll()

    val result    = launchTestSuite()
    val exitCode  = result.exitCode
    val message   = "\n" + result + "\n"
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (exitCode == 0) success(message)
    else failure(message)

    if (isStats)
      showTestStatistics()

    System exit exitCode
  }
}
