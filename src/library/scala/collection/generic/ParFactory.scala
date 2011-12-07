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

import scala.collection.parallel.ParIterable
import scala.collection.parallel.Combiner



/** A template class for companion objects of `ParIterable` and subclasses thereof.
 *  This class extends `TraversableFactory` and provides a set of operations to create `$Coll` objects.
 *  
 *  @define coll parallel collection
 *  @define Coll ParIterable
=======
import scala.collection.parallel.ParIterable
import scala.collection.parallel.Combiner

/** A template class for companion objects of `ParIterable` and subclasses
 *  thereof. This class extends `TraversableFactory` and provides a set of
 *  operations to create `$Coll` objects.
 *
 *  @define coll parallel collection
 *  @define Coll ParIterable
 *  @since 2.8
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
abstract class ParFactory[CC[X] <: ParIterable[X] with GenericParTemplate[X, CC]]
extends GenTraversableFactory[CC]
   with GenericParCompanion[CC] {
<<<<<<< HEAD
  
  //type EPC[T, C] = collection.parallel.EnvironmentPassingCombiner[T, C]
  
  /**
   * A generic implementation of the `CanCombineFrom` trait, which forwards all calls to
   * `apply(from)` to the `genericParBuilder` method of the $coll `from`, and calls to `apply()`
   * to this factory.
=======

  //type EPC[T, C] = collection.parallel.EnvironmentPassingCombiner[T, C]

  /** A generic implementation of the `CanCombineFrom` trait, which forwards
   *  all calls to `apply(from)` to the `genericParBuilder` method of the $coll
   * `from`, and calls to `apply()` to this factory.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  class GenericCanCombineFrom[A] extends GenericCanBuildFrom[A] with CanCombineFrom[CC[_], A, CC[A]] {
    override def apply(from: Coll) = from.genericCombiner
    override def apply() = newBuilder[A]
  }
}





<<<<<<< HEAD







=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
