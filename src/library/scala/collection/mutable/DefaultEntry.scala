/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package mutable

/** Class used internally for default map model.
 *  @since 2.3
 */
final class DefaultEntry[A, B](val key: A, var value: B)
      extends HashEntry[A, DefaultEntry[A, B]] with Serializable
{
  override def toString = chainString
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def chainString = {
    "(kv: " + key + ", " + value + ")" + (if (next != null) " -> " + next.toString else "")
  }
}
