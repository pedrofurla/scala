/* NSC -- new Scala compiler
 * Copyright 2009-2011 Scala Solutions and LAMP/EPFL
 * @author Martin Odersky
 * @author Iulian Dragos
 */
package scala.tools.nsc.interactive

/** A presentation compiler thread. This is a lightweight class, delegating most
<<<<<<< HEAD
 *  of its functionality to the compiler instance. 
 *  
 */
final class PresentationCompilerThread(var compiler: Global, name: String = "") 
  extends Thread("Scala Presentation Compiler [" + name + "]") {
  
=======
 *  of its functionality to the compiler instance.
 *
 */
final class PresentationCompilerThread(var compiler: Global, name: String = "")
  extends Thread("Scala Presentation Compiler [" + name + "]") {

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The presentation compiler loop.
   */
  override def run() {
    compiler.debugLog("starting new runner thread")
    while (compiler ne null) try {
      compiler.checkNoResponsesOutstanding()
      compiler.log.logreplay("wait for more work", { compiler.scheduler.waitForMoreWork(); true })
      compiler.pollForWork(compiler.NoPosition)
      while (compiler.isOutOfDate) {
        try {
          compiler.backgroundCompile()
        } catch {
          case ex: FreshRunReq =>
            compiler.debugLog("fresh run req caught, starting new pass")
        }
        compiler.log.flush()
      }
    } catch {
<<<<<<< HEAD
      case ex @ ShutdownReq => 
=======
      case ex @ ShutdownReq =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        compiler.debugLog("exiting presentation compiler")
        compiler.log.close()

        // make sure we don't keep around stale instances
        compiler = null
      case ex =>
        compiler.log.flush()
<<<<<<< HEAD
        
        ex match {
          case ex: FreshRunReq =>   
            compiler.debugLog("fresh run req caught outside presentation compiler loop; ignored") // This shouldn't be reported
          case _ : Global#ValidateException => // This will have been reported elsewhere
            compiler.debugLog("validate exception caught outside presentation compiler loop; ignored") 
=======

        ex match {
          case ex: FreshRunReq =>
            compiler.debugLog("fresh run req caught outside presentation compiler loop; ignored") // This shouldn't be reported
          case _ : Global#ValidateException => // This will have been reported elsewhere
            compiler.debugLog("validate exception caught outside presentation compiler loop; ignored")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          case _ => ex.printStackTrace(); compiler.informIDE("Fatal Error: "+ex)
        }
    }
  }
}
