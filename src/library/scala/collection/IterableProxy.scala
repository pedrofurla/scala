/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

import generic._

/** This trait implements a proxy for iterable objects. It forwards all calls
 *  to a different iterable object.
 *
 *  @author  Martin Odersky
 *  @version 2.8
 *  @since   2.8
 */
<<<<<<< HEAD
trait IterableProxy[+A] extends Iterable[A] with IterableProxyLike[A, Iterable[A]] 
=======
trait IterableProxy[+A] extends Iterable[A] with IterableProxyLike[A, Iterable[A]]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
