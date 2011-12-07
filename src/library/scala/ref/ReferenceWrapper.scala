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
trait ReferenceWrapper[+T <: AnyRef] extends Reference[T] with Proxy {
  val underlying: java.lang.ref.Reference[_ <: T]
<<<<<<< HEAD
  override def get = {
    val ret = underlying.get
    if (ret eq null) None else Some(ret)
  }
=======
  override def get = Option(underlying.get)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply() = {
    val ret = underlying.get
    if (ret eq null) throw new NoSuchElementException
    ret
  }
<<<<<<< HEAD
  def clear = underlying.clear
  def enqueue = underlying.enqueue
  def isEnqueued = underlying.isEnqueued
  
=======
  def clear() = underlying.clear()
  def enqueue = underlying.enqueue
  def isEnqueued = underlying.isEnqueued
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def self = underlying
}

/**
 *  @author Philipp Haller
 */
private trait ReferenceWithWrapper[T <: AnyRef] {
  val wrapper: ReferenceWrapper[T]
}
