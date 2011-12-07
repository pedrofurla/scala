/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.collection

import generic._

/** A base trait for all sets, mutable as well as immutable.
 *
 * $setNote
 * '''Implementation note:''' If your additions and mutations return the same kind of set as the set
 *       you are defining, you should inherit from `SetLike` as well.
<<<<<<< HEAD
 * $setTags 
=======
 * $setTags
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * @since 1.0
 * @author Matthias Zenger
 */
<<<<<<< HEAD
trait Set[A] extends (A => Boolean) 
                with Iterable[A] 
=======
trait Set[A] extends (A => Boolean)
                with Iterable[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                with GenSet[A]
                with GenericSetTemplate[A, Set]
                with SetLike[A, Set[A]] {
  override def companion: GenericCompanion[Set] = Set
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def seq: Set[A] = this
}

/** $factoryInfo
 *  The current default implementation of a $Coll is one of `EmptySet`, `Set1`, `Set2`, `Set3`, `Set4` in
 *  class `immutable.Set` for sets of sizes up to 4, and a `immutable.HashSet` for sets of larger sizes.
 *  @define coll set
 *  @define Coll Set
 */
object Set extends SetFactory[Set] {
<<<<<<< HEAD
  private[collection] val hashSeed = "Set".hashCode

=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newBuilder[A] = immutable.Set.newBuilder[A]
  override def empty[A]: Set[A] = immutable.Set.empty[A]
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, Set[A]] = setCanBuildFrom[A]
}
<<<<<<< HEAD
=======

/** Explicit instantiation of the `Set` trait to reduce class file size in subclasses. */
private[scala] abstract class AbstractSet[A] extends AbstractIterable[A] with Set[A]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
