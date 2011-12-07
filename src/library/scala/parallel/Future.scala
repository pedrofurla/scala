<<<<<<< HEAD
package scala.parallel







/** A future is a function without parameters that will block the caller if the
 *  parallel computation associated with the function is not completed.
 *  
 *  @since 2.9
 */
trait Future[@specialized +R] extends (() => R) {
  /** Returns a result once the parallel computation completes. If the computation
   *  produced an exception, an exception is forwarded.
   *  
   *  '''Note:''' creating a circular dependency between futures by calling this method will
   *  result in a deadlock.
   *  
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.parallel

/** A future is a function without parameters that will block the caller if
 *  the parallel computation associated with the function is not completed.
 *
 *  @since 2.9
 */
trait Future[@specialized +R] extends (() => R) {
  /** Returns a result once the parallel computation completes. If the
   *  computation produced an exception, an exception is forwarded.
   *
   *  '''Note:''' creating a circular dependency between futures by calling
   *  this method will result in a deadlock.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @tparam R   the type of the result
   *  @return     the result
   *  @throws     the exception that was thrown during a parallel computation
   */
  def apply(): R
<<<<<<< HEAD
  
  /** Returns `true` if the parallel computation is completed.
   *  
=======

  /** Returns `true` if the parallel computation is completed.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  @return     `true` if the parallel computation is completed, `false` otherwise
   */
  def isDone(): Boolean
}

<<<<<<< HEAD


=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
