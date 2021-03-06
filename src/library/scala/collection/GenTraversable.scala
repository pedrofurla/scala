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
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Martin Odersky
 *  @author Aleksandar Prokopec
 *  @since 2.9
 */
trait GenTraversable[+A]
<<<<<<< HEAD
extends GenTraversableLike[A, GenTraversable[A]] 
=======
extends GenTraversableLike[A, GenTraversable[A]]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   with GenTraversableOnce[A]
   with GenericTraversableTemplate[A, GenTraversable]
{
  def seq: Traversable[A]
  def companion: GenericCompanion[GenTraversable] = GenTraversable
}


object GenTraversable extends GenTraversableFactory[GenTraversable] {
  implicit def canBuildFrom[A] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]
  def newBuilder[A] = Traversable.newBuilder
}


