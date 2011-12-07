/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect
package internal

<<<<<<< HEAD
class MissingRequirementError(val req: String) extends FatalError(req + " not found.")

object MissingRequirementError {
  def unapply(x: Throwable) = x match {
    case x: MissingRequirementError => Some(x.req)
    case _                          => None
=======
class MissingRequirementError private (msg: String) extends FatalError(msg) {
  import MissingRequirementError.suffix
  def req: String = if (msg endsWith suffix) msg dropRight suffix.length else msg
}

object MissingRequirementError {
  private val suffix = " not found."
  def signal(msg: String): Nothing = throw new MissingRequirementError(msg)
  def notFound(req: String): Nothing = signal(req + suffix)
  def unapply(x: Throwable): Option[String] = x match {
    case x: MissingRequirementError => Some(x.req)
    case _ => None
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}


