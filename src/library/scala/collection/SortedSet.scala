/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection
import generic._

/** A sorted set.
 *
 *  @author Sean McDirmid
 *  @author Martin Odersky
 *  @version 2.8
 *  @since   2.4
 */
trait SortedSet[A] extends Set[A] with SortedSetLike[A, SortedSet[A]] {
  /** Needs to be overridden in subclasses. */
  override def empty: SortedSet[A] = SortedSet.empty[A]
}

/**
 * @since 2.8
 */
object SortedSet extends SortedSetFactory[SortedSet] {
  def empty[A](implicit ord: Ordering[A]): immutable.SortedSet[A] = immutable.SortedSet.empty[A](ord)
<<<<<<< HEAD
  implicit def canBuildFrom[A](implicit ord: Ordering[A]): CanBuildFrom[Coll, A, SortedSet[A]] = new SortedSetCanBuildFrom[A]
=======
  def canBuildFrom[A](implicit ord: Ordering[A]): CanBuildFrom[Coll, A, SortedSet[A]] = newCanBuildFrom[A]
  // Force a declaration here so that BitSet's (which does not inherit from SortedSetFactory) can be more specific
  override implicit def newCanBuildFrom[A](implicit ord : Ordering[A]) : CanBuildFrom[Coll, A, SortedSet[A]] = super.newCanBuildFrom
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
