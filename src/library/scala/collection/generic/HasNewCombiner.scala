/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
<<<<<<< HEAD
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
=======
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.generic

<<<<<<< HEAD


import scala.collection.parallel.Combiner



=======
import scala.collection.parallel.Combiner

/**
 *  @since 2.8
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
trait HasNewCombiner[+T, +Repr] {
  protected[this] def newCombiner: Combiner[T, Repr]
}

<<<<<<< HEAD















=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
