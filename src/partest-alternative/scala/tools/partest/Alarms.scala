/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools
package partest

import java.util.{ Timer, TimerTask }

trait Alarms {
  self: Universe =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def interruptMeIn[T](debugMsg: String, seconds: Int)(body: => T): Option[T] = {
    val thisThread  = currentThread
    val alarm       = new SimpleAlarm(seconds * 1000) set thisThread.interrupt()
    debug("interruptMeIn(%d) '%s'".format(seconds, debugMsg))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    try     { Some(body) }
    catch   { case _: InterruptedException => debug("Received interrupted exception.") ; None }
    finally { debug("Cancelling interruptMeIn '%s'" format debugMsg) ; alarm.cancel() ; Thread.interrupted() }
  }

  case class AlarmerAction(secs: Int, action: () => Unit) extends Runnable {
    override def run() = action()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Set any number of alarms up with tuples of the form:
   *    seconds to alarm -> Function0[Unit] to execute
   */
  class Alarmer(alarms: AlarmerAction*) {
    import java.util.concurrent._
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val exec = Executors.newSingleThreadScheduledExecutor()
    alarms foreach (x => exec.schedule(x, x.secs, TimeUnit.SECONDS))
    exec.shutdown()

    def cancelAll() = exec.shutdownNow()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class SimpleAlarm(timeout: Long) {
    private val alarm = new Timer

    /** Start a timer, running the given body if it goes off.
     */
    def set(body: => Unit) = returning(new TimerTask { def run() = body })(alarm.schedule(_, timeout))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Cancel the timer.
     */
    def cancel() = alarm.cancel()
  }
<<<<<<< HEAD
  
  trait TestAlarms {
    test: TestEntity =>
    
=======

  trait TestAlarms {
    test: TestEntity =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def warning1 = AlarmerAction(testWarning, () => warning(
      """|I've been waiting %s seconds for this to complete:
         |  %s
         |It may be stuck, or if not, it should be broken into smaller tests.
         |""".stripMargin.format(testWarning, test))
    )
    private def warning2 = AlarmerAction(testWarning * 2, () => warning(
      """|Now I've been waiting %s seconds for this to complete:
         |  %s
         |If partest seems hung it would be a good place to look.
         |""".stripMargin.format(testWarning * 2, test))
    )
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def startAlarms(onTimeout: => Unit) =
      if (isNoAlarms) new Alarmer() // for alarm debugging
      else new Alarmer(Seq(warning1, warning2, AlarmerAction(testTimeout, () => onTimeout)): _*)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Thread.setDefaultUncaughtExceptionHandler(new UncaughtException)
  // class UncaughtException extends Thread.UncaughtExceptionHandler {
  //   def uncaughtException(t: Thread, e: Throwable) {
  //     Console.println("Uncaught in %s: %s".format(t, e))
  //   }
  // }
<<<<<<< HEAD
  // 
=======
  //
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // lazy val logger = File("/tmp/partest.log").bufferedWriter()
  // def flog(msg: String) = logger synchronized {
  //   logger write (msg + "\n")
  //   logger.flush()
  // }
}
