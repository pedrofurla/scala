/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package generic

import mutable.{ Builder, GrowingBuilder }

abstract class MutableSetFactory[CC[X] <: mutable.Set[X] with mutable.SetLike[X, CC[X]]]
  extends SetFactory[CC] {
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newBuilder[A]: Builder[A, CC[A]] = new GrowingBuilder[A, CC[A]](empty[A])
}
