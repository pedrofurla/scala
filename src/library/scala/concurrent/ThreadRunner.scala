/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.concurrent

import java.lang.Thread

/** The `ThreadRunner` trait...
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Philipp Haller
 */
class ThreadRunner extends FutureTaskRunner {

  type Task[T] = () => T
  type Future[T] = () => T

  implicit def functionAsTask[S](fun: () => S): Task[S] = fun
  implicit def futureAsFunction[S](x: Future[S]): () => S = x

  /* If expression computed successfully return it in `Right`,
   * otherwise return exception in `Left`.
   */
  private def tryCatch[A](body: => A): Either[Exception, A] =
    try Right(body) catch {
      case ex: Exception => Left(ex)
    }

  def execute[S](task: Task[S]) {
    val runnable = new Runnable {
      def run() { tryCatch(task()) }
    }
    (new Thread(runnable)).start()
  }

  def submit[S](task: Task[S]): Future[S] = {
    val result = new SyncVar[Either[Exception, S]]
    val runnable = new Runnable {
      def run() { result set tryCatch(task()) }
    }
    (new Thread(runnable)).start()
    () => result.get.fold[S](throw _, identity _)
  }

  def managedBlock(blocker: ManagedBlocker) {
    blocker.block()
  }

  def shutdown() {
    // do nothing
  }

}
