/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package immutable

/** This is a simple wrapper class for <a href="Set.html"
 *  target="contentFrame">`scala.collection.immutable.Set`</a>.
<<<<<<< HEAD
 *  
 *  It is most useful for assembling customized set abstractions
 *  dynamically using object composition and forwarding.
 *  
 *  @tparam A    type of the elements contained in this set proxy.
 *  
=======
 *
 *  It is most useful for assembling customized set abstractions
 *  dynamically using object composition and forwarding.
 *
 *  @tparam A    type of the elements contained in this set proxy.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @since 2.8
 */
trait SetProxy[A] extends Set[A] with SetProxyLike[A, Set[A]] {
  override def repr = this
  private def newProxy[B >: A](newSelf: Set[B]): SetProxy[B] =
<<<<<<< HEAD
    new SetProxy[B] { val self = newSelf }
  
=======
    new AbstractSet[B] with SetProxy[B] { val self = newSelf }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def empty = newProxy(self.empty)
  override def + (elem: A) = newProxy(self + elem)
  override def - (elem: A) = newProxy(self - elem)
}
