/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package mutable

/** A trait for cloneable collections.
<<<<<<< HEAD
 *  
 *  @since 2.8
 *  
=======
 *
 *  @since 2.8
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @tparam A    Type of the elements contained in the collection, covariant and with reference types as upperbound.
 */
@cloneable
trait Cloneable[+A <: AnyRef] {
  // !!! why doesn't this extend java.lang.Cloneable?
  //     because neither did @serializable, then we changed it to Serializable
  override def clone: A = super.clone().asInstanceOf[A]
}
