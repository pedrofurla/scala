/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.reflect

/** A `OptManifest[T]` is an optional [[scala.reflect.Manifest]].
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  It is either a `Manifest` or the value `NoManifest`.
 *
 *  @author Martin Odersky
 */
trait OptManifest[+T] extends Serializable
