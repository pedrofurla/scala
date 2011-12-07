/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package mutable

import generic._
import scala.reflect.ClassManifest

/** A builder class for arrays.
<<<<<<< HEAD
 *  
 *  @tparam A         type of elements that can be added to this builder.
 *  @param manifest   class manifest for objects of type `A`.
 *  
=======
 *
 *  @tparam A         type of elements that can be added to this builder.
 *  @param manifest   class manifest for objects of type `A`.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @since 2.8
 */
class WrappedArrayBuilder[A](manifest: ClassManifest[A]) extends Builder[A, WrappedArray[A]] {

  private var elems: WrappedArray[A] = _
  private var capacity: Int = 0
  private var size: Int = 0

  private def mkArray(size: Int): WrappedArray[A] = {
    val newelems = manifest.newWrappedArray(size)
    if (this.size > 0) Array.copy(elems.array, 0, newelems.array, 0, this.size)
    newelems
  }

  private def resize(size: Int) {
    elems = mkArray(size)
    capacity = size
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def sizeHint(size: Int) {
    if (capacity < size) resize(size)
  }

  private def ensureSize(size: Int) {
    if (capacity < size) {
      var newsize = if (capacity == 0) 16 else capacity * 2
      while (newsize < size) newsize *= 2
      resize(newsize)
    }
<<<<<<< HEAD
  } 
  
=======
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def +=(elem: A): this.type = {
    ensureSize(size + 1)
    elems(size) = elem
    size += 1
    this
  }

  def clear() {
    size = 0
  }

  def result() = {
<<<<<<< HEAD
    if (capacity != 0 && capacity == size) elems 
=======
    if (capacity != 0 && capacity == size) elems
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    else mkArray(size)
  }

  // todo: add ++=
}
