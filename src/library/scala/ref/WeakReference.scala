/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.ref

/**
 *  @author Sean McDirmid
 */
class WeakReference[+T <: AnyRef](value: T, queue: ReferenceQueue[T]) extends ReferenceWrapper[T] {
  def this(value: T) = this(value, null)
<<<<<<< HEAD
  val underlying: java.lang.ref.WeakReference[_ <: T] = 
=======
  val underlying: java.lang.ref.WeakReference[_ <: T] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new WeakReferenceWithWrapper[T](value, queue, this)
}

/**
 *  @author Philipp Haller
 */
private class WeakReferenceWithWrapper[T <: AnyRef](value: T, queue: ReferenceQueue[T], val wrapper: WeakReference[T])
  extends java.lang.ref.WeakReference[T](value, if (queue == null) null else queue.underlying.asInstanceOf[java.lang.ref.ReferenceQueue[T]]) with ReferenceWithWrapper[T]
