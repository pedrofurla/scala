/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
<<<<<<< HEAD
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
=======
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package generic

<<<<<<< HEAD
=======
/** A template for companion objects of `ClassManifestTraversable` and
 *  subclasses thereof.
 *
 *  @define coll collection
 *  @define Coll Traversable
 *  @define genericCanBuildFromInfo
 *    The standard `CanBuildFrom` instance for $Coll objects.
 *    @author Aleksandar Prokopec
 *    @since 2.8
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
abstract class ClassManifestTraversableFactory[CC[X] <: Traversable[X] with GenericClassManifestTraversableTemplate[X, CC]]
              extends GenericClassManifestCompanion[CC] {

  class GenericCanBuildFrom[A](implicit manif: ClassManifest[A]) extends CanBuildFrom[CC[_], A, CC[A]] {
    def apply(from: CC[_]) = from.genericClassManifestBuilder[A]
    def apply = newBuilder[A]
  }
}
