/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.collection
package generic

/**
 * @since 2.8
 */
<<<<<<< HEAD
trait GenericSetTemplate[A, +CC[X] <: GenSet[X]] extends GenericTraversableTemplate[A, CC] { 
=======
trait GenericSetTemplate[A, +CC[X] <: GenSet[X]] extends GenericTraversableTemplate[A, CC] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def empty: CC[A] = companion.empty[A]
}

