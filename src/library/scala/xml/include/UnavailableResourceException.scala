/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.xml
package include

/**
 * An `UnavailableResourceException` is thrown when an included document
 * cannot be found or loaded.
 */
<<<<<<< HEAD
class UnavailableResourceException(message: String) 
=======
class UnavailableResourceException(message: String)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
extends XIncludeException(message) {
  def this() = this(null)
}
