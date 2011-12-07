/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

import generic._


/** A trait for all traversable collections which may possibly
 *  have their operations implemented in parallel.
 *
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait GenMap[A, +B]
extends GenMapLike[A, B, GenMap[A, B]]
   with GenIterable[(A, B)]
{
  def seq: Map[A, B]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def updated [B1 >: B](key: A, value: B1): GenMap[A, B1]
}


object GenMap extends GenMapFactory[GenMap] {
  def empty[A, B]: immutable.Map[A, B] = immutable.Map.empty
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** $mapCanBuildFromInfo */
  implicit def canBuildFrom[A, B]: CanBuildFrom[Coll, (A, B), GenMap[A, B]] = new MapCanBuildFrom[A, B]
}


