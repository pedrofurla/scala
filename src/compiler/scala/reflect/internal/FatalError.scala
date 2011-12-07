/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */
package scala.reflect.internal
<<<<<<< HEAD
case class FatalError(msg: String) extends Throwable(msg)
=======
case class FatalError(msg: String) extends Exception(msg)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
