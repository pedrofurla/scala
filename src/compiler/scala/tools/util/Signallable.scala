/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
package scala.tools
package util

=======

package scala.tools
package util

import java.security.AccessControlException

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
/** A class for things which are signallable.
 */
abstract class Signallable[T] private (val signal: String, val description: String) {
  private var last: Option[T] = None
<<<<<<< HEAD
  private def lastString = last filterNot (_ == ()) map (_.toString) getOrElse ""
  
  /** The most recent result from the signal handler. */
  def lastResult: Option[T] = last
  
  /** Method to be executed when the associated signal is received. */
  def onSignal(): T
  
  // todo:
  // def unregister(): Boolean
  
=======
  private def lastString = last match {
    case Some(())   => ""
    case Some(x)    => "" + x
    case _          => ""
  }

  /** The most recent result from the signal handler. */
  def lastResult: Option[T] = last

  /** Method to be executed when the associated signal is received. */
  def onSignal(): T

  // todo:
  // def unregister(): Boolean

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def toString =  "  SIG(%s) => %s%s".format(
    signal, description, if (lastString == "") "" else " (" + lastString + ")"
  )
}

object Signallable {
  /** Same as the other apply, but an open signal is found for you.
   */
<<<<<<< HEAD
  def apply[T](description: String)(body: => T): Signallable[T] =
    apply(SignalManager.findOpenSignal().name, description)(body)
    
=======
  def apply[T](description: String)(body: => T): Signallable[T] = wrap {
    apply(SignalManager.findOpenSignal().name, description)(body)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Given a signal name, a description, and a handler body, this
   *  registers a signal handler and returns the Signallable instance.
   *  The signal handler registry is thereafter available by calling
   *  SignalManager.info(), or sending SIGINFO to the manager will
   *  dump it to console.
   */
<<<<<<< HEAD
  def apply[T](signal: String, description: String)(body: => T): Signallable[T] = {
    val result = new Signallable[T](signal, description) {
      def onSignal(): T = {
=======
  def apply[T](signal: String, description: String)(body: => T): Signallable[T] = wrap {
    val result = create[T](signal, description, body)
    SignalManager.public(signal, description)(result.onSignal())
    result
  }

  private def wrap[T](body: => Signallable[T]): Signallable[T] =
    try body catch { case _: AccessControlException => null }

  private def create[T](signal: String, description: String, body: => T): Signallable[T] =
    new Signallable[T](signal, description) {
      def onSignal = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val result = body
        last = Some(result)
        result
      }
    }
<<<<<<< HEAD
    SignalManager.public(signal, description)(result.onSignal())
    result
  }
}

=======
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
