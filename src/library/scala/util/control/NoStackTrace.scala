/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.util.control

/** A trait for exceptions which, for efficiency reasons, do not
 *  fill in the stack trace.  Stack trace suppression can be disabled
 *  on a global basis via a system property wrapper in
 *  [[scala.sys.SystemProperties]].
 *
 *  @author   Paul Phillips
 *  @since    2.8
 */
trait NoStackTrace extends Throwable {
<<<<<<< HEAD
  override def fillInStackTrace(): Throwable = 
=======
  override def fillInStackTrace(): Throwable =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (NoStackTrace.noSuppression) super.fillInStackTrace()
    else this
}

object NoStackTrace {
  final val noSuppression = sys.SystemProperties.noTraceSupression.value
}
