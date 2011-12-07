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

package scala.collection
package generic

import mutable.Builder
import annotation.unchecked.uncheckedVariance

/** This trait represents collections classes which require class
 *  manifests for their element types.
 *
 *  @author Aleksandar Prokopec
<<<<<<< HEAD
=======
 *  @since 2.8
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait GenericClassManifestTraversableTemplate[+A, +CC[X] <: Traversable[X]] extends HasNewBuilder[A, CC[A] @uncheckedVariance] {
  implicit protected[this] val manifest: ClassManifest[A]
  def classManifestCompanion: GenericClassManifestCompanion[CC]
  def genericClassManifestBuilder[B](implicit man: ClassManifest[B]): Builder[B, CC[B]] = classManifestCompanion.newBuilder[B]
}
