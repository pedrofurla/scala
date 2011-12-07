/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.parallel.mutable

<<<<<<< HEAD



=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.collection.generic.Growable
import scala.collection.generic.Sizing
import scala.collection.mutable.ArrayBuffer
import scala.collection.parallel.Combiner

<<<<<<< HEAD



/** Implements combining contents of two combiners
 *  by postponing the operation until `result` method is called. It chains
 *  the leaf results together instead of evaluating the actual collection.
 *  
=======
/** Implements combining contents of two combiners
 *  by postponing the operation until `result` method is called. It chains
 *  the leaf results together instead of evaluating the actual collection.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @tparam Elem    the type of the elements in the combiner
 *  @tparam To      the type of the collection the combiner produces
 *  @tparam Buff    the type of the buffers that contain leaf results and this combiner chains together
 */
<<<<<<< HEAD
trait LazyCombiner[Elem, +To, Buff <: Growable[Elem] with Sizing] extends Combiner[Elem, To]
{
=======
trait LazyCombiner[Elem, +To, Buff <: Growable[Elem] with Sizing] extends Combiner[Elem, To] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
//self: collection.parallel.EnvironmentPassingCombiner[Elem, To] =>
  val chain: ArrayBuffer[Buff]
  val lastbuff = chain.last
  def +=(elem: Elem) = { lastbuff += elem; this }
  def result: To = allocateAndCopy
<<<<<<< HEAD
  def clear = { chain.clear }
=======
  def clear() = { chain.clear() }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def combine[N <: Elem, NewTo >: To](other: Combiner[N, NewTo]): Combiner[N, NewTo] = if (this ne other) {
    if (other.isInstanceOf[LazyCombiner[_, _, _]]) {
      val that = other.asInstanceOf[LazyCombiner[Elem, To, Buff]]
      newLazyCombiner(chain ++= that.chain)
    } else throw new UnsupportedOperationException("Cannot combine with combiner of different type.")
  } else this
  def size = chain.foldLeft(0)(_ + _.size)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Method that allocates the data structure and copies elements into it using
   *  `size` and `chain` members.
   */
  def allocateAndCopy: To
  def newLazyCombiner(buffchain: ArrayBuffer[Buff]): LazyCombiner[Elem, To, Buff]
}
