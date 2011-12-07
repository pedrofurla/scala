/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.collection

import generic._

/** A common base class for mutable and immutable bitsets.
 *  $bitsetinfo
 */
<<<<<<< HEAD
trait BitSet extends Set[Int] 
=======
trait BitSet extends SortedSet[Int]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                with BitSetLike[BitSet] {
  override def empty: BitSet = BitSet.empty
}

/** $factoryInfo
 *  @define coll bitset
 *  @define Coll BitSet
 */
object BitSet extends BitSetFactory[BitSet] {
<<<<<<< HEAD
  val empty: BitSet = immutable.BitSet.empty  
  def newBuilder = immutable.BitSet.newBuilder
  
=======
  val empty: BitSet = immutable.BitSet.empty
  def newBuilder = immutable.BitSet.newBuilder

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** $canBuildFromInfo */
  implicit def canBuildFrom: CanBuildFrom[BitSet, Int, BitSet] = bitsetCanBuildFrom
}

