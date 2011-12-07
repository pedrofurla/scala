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




=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import collection.mutable.Builder
import collection.parallel.Combiner
import collection.parallel.ParSet
import collection.parallel.ParSetLike

<<<<<<< HEAD





=======
/**
 *  @author Aleksandar Prokopec
 *  @since 2.8
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
abstract class ParSetFactory[CC[X] <: ParSet[X] with ParSetLike[X, CC[X], _] with GenericParTemplate[X, CC]]
  extends GenSetFactory[CC]
     with GenericParCompanion[CC]
{
  def newBuilder[A]: Combiner[A, CC[A]] = newCombiner[A]
<<<<<<< HEAD
  
  def newCombiner[A]: Combiner[A, CC[A]]
  
=======

  def newCombiner[A]: Combiner[A, CC[A]]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class GenericCanCombineFrom[A] extends CanCombineFrom[CC[_], A, CC[A]] {
    override def apply(from: Coll) = from.genericCombiner[A]
    override def apply() = newCombiner[A]
  }
}

<<<<<<< HEAD




=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
