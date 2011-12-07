/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2010, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala

/** `Nothing` is - together with [[scala.Null]] - at the bottom of Scala's type hierarchy.
<<<<<<< HEAD
 *  
 *  `Nothing` is a subtype of every other type (including [[scala.Null]]); there exist 
=======
 *
 *  `Nothing` is a subtype of every other type (including [[scala.Null]]); there exist
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  ''no instances'' of this type.  Although type `Nothing` is uninhabited, it is
 *  nevertheless useful in several ways.  For instance, the Scala library defines a value
 *  [[scala.collection.immutable.Nil]] of type `List[Nothing]`. Because lists are covariant in Scala,
 *  this makes [[scala.collection.immutable.Nil]] an instance of `List[T]`, for any element of type `T`.
 *
 *  Another usage for Nothing is the return type for methods which never return normally.
 *  One example is method error in [[scala.sys]], which always throws an exception.
 */
sealed trait Nothing

