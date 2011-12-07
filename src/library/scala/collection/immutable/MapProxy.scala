/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection
package immutable

<<<<<<< HEAD
/** 
 *  This is a simple wrapper class for <a href="Map.html"
 *  target="contentFrame">`scala.collection.immutable.Map`</a>.
 *  
=======
/**
 *  This is a simple wrapper class for <a href="Map.html"
 *  target="contentFrame">`scala.collection.immutable.Map`</a>.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  It is most useful for assembling customized map abstractions
 *  dynamically using object composition and forwarding.
 *
 *  @author  Matthias Zenger, Martin Odersky
 *  @version 2.0, 31/12/2006
 *  @since   2.8
 */
<<<<<<< HEAD
trait MapProxy[A, +B] extends Map[A, B] with MapProxyLike[A, B, Map[A, B]] {  
=======
trait MapProxy[A, +B] extends Map[A, B] with MapProxyLike[A, B, Map[A, B]] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def repr = this
  private def newProxy[B1 >: B](newSelf: Map[A, B1]): MapProxy[A, B1] =
    new MapProxy[A, B1] { val self = newSelf }

  override def empty = newProxy(self.empty)
  override def updated [B1 >: B](key: A, value: B1) = newProxy(self.updated(key, value))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def -(key: A) = newProxy(self - key)
  override def + [B1 >: B](kv: (A, B1)): Map[A, B1] = newProxy(self + kv)
  override def + [B1 >: B](elem1: (A, B1), elem2: (A, B1), elems: (A, B1) *) = newProxy(self.+(elem1, elem2, elems: _*))
  override def ++[B1 >: B](xs: GenTraversableOnce[(A, B1)]) = newProxy(self ++ xs.seq)

  override def keySet: immutable.Set[A] = new SetProxy[A] { val self = MapProxy.this.self.keySet }
  override def filterKeys(p: A => Boolean) = self.filterKeys(p)
  override def mapValues[C](f: B => C) = self.mapValues(f)
}
