/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.swing

import scala.collection.mutable
import mutable.Buffer
import event.Event

/** <p>
<<<<<<< HEAD
 *    Notifies registered reactions when an event is published. Publishers are 
 *    also reactors and listen to themselves per default as a convenience.
 *  </p>
 *  <p>
 *    In order to reduce memory leaks, reactions are weakly referenced by default, 
 *    unless they implement <code>Reactions.StronglyReferenced</code>. That way, 
 *    the lifetime of reactions are more easily bound to the registering object, 
 *    which are reactors in common client code and hold strong references to their 
 *    reactions. As a result, reactors can be garbage collected even though they 
=======
 *    Notifies registered reactions when an event is published. Publishers are
 *    also reactors and listen to themselves per default as a convenience.
 *  </p>
 *  <p>
 *    In order to reduce memory leaks, reactions are weakly referenced by default,
 *    unless they implement <code>Reactions.StronglyReferenced</code>. That way,
 *    the lifetime of reactions are more easily bound to the registering object,
 *    which are reactors in common client code and hold strong references to their
 *    reactions. As a result, reactors can be garbage collected even though they
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    still have reactions registered at some publisher, but not vice versa
 *    since reactors (strongly) reference publishers they are interested in.
 *  </p>
 */
trait Publisher extends Reactor {
  import Reactions._
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected val listeners = new RefSet[Reaction] {
    import scala.ref._
    val underlying = new mutable.HashSet[Reference[Reaction]]
    protected def Ref(a: Reaction) = a match {
      case a: StronglyReferenced => new StrongReference[Reaction](a) with super.Ref[Reaction]
      case _ => new WeakReference[Reaction](a, referenceQueue) with super.Ref[Reaction]
    }
  }

  private[swing] def subscribe(listener: Reaction) { listeners += listener }
  private[swing] def unsubscribe(listener: Reaction) { listeners -= listener }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * Notify all registered reactions.
   */
  def publish(e: Event) { for (l <- listeners) l(e) }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  listenTo(this)
}

/**
<<<<<<< HEAD
 * A publisher that subscribes itself to an underlying event source not before the first 
=======
 * A publisher that subscribes itself to an underlying event source not before the first
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * reaction is installed. Can unsubscribe itself when the last reaction is uninstalled.
 */
private[swing] trait LazyPublisher extends Publisher {
  import Reactions._
<<<<<<< HEAD
  
  protected def onFirstSubscribe()
  protected def onLastUnsubscribe()
  
  override def subscribe(listener: Reaction) { 
    if(listeners.size == 1) onFirstSubscribe() 
    super.subscribe(listener) 
  }
  override def unsubscribe(listener: Reaction) { 
    super.unsubscribe(listener) 
=======

  protected def onFirstSubscribe()
  protected def onLastUnsubscribe()

  override def subscribe(listener: Reaction) {
    if(listeners.size == 1) onFirstSubscribe()
    super.subscribe(listener)
  }
  override def unsubscribe(listener: Reaction) {
    super.unsubscribe(listener)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if(listeners.size == 1) onLastUnsubscribe()
  }
}



import scala.ref._

private[swing] trait SingleRefCollection[+A <: AnyRef] extends Iterable[A] { self =>

  trait Ref[+A <: AnyRef] extends Reference[A] {
    override def hashCode() = get match {
      case Some(x)  => x.##
      case _        => 0
    }
    override def equals(that: Any) = that match {
<<<<<<< HEAD
      case that: ReferenceWrapper[_] => 
=======
      case that: ReferenceWrapper[_] =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val v1 = this.get
        val v2 = that.get
        v1 == v2
      case _ => false
    }
  }

  //type Ref <: Reference[A] // TODO: could use higher kinded types, but currently crashes
  protected[this] def Ref(a: A): Ref[A]
  protected[this] val referenceQueue = new ReferenceQueue[A]
<<<<<<< HEAD
  
  protected val underlying: Iterable[Reference[A]]
      
  def purgeReferences() { 
    var ref = referenceQueue.poll
    while (ref != None) { 
=======

  protected val underlying: Iterable[Reference[A]]

  def purgeReferences() {
    var ref = referenceQueue.poll
    while (ref != None) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      removeReference(ref.get.asInstanceOf[Reference[A]])
      ref = referenceQueue.poll
    }
  }
<<<<<<< HEAD
  
  protected[this] def removeReference(ref: Reference[A])
  
=======

  protected[this] def removeReference(ref: Reference[A])

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def iterator = new Iterator[A] {
    private val elems = self.underlying.iterator
    private var hd: A = _
    private var ahead: Boolean = false
    private def skip(): Unit =
      while (!ahead && elems.hasNext) {
<<<<<<< HEAD
        // make sure we have a reference to the next element, 
        // otherwise it might be garbage collected
        val next = elems.next.get
        ahead = next != None 
=======
        // make sure we have a reference to the next element,
        // otherwise it might be garbage collected
        val next = elems.next.get
        ahead = next != None
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (ahead) hd = next.get
      }
    def hasNext: Boolean = { skip; ahead }
    def next(): A =
      if (hasNext) { ahead = false; hd }
      else throw new NoSuchElementException("next on empty iterator")
  }
}

private[swing] class StrongReference[+T <: AnyRef](value: T) extends Reference[T] {
    private[this] var ref: Option[T] = Some(value)
    def isValid: Boolean = ref != None
    def apply(): T = ref.get
    def get : Option[T] = ref
    override def toString = get.map(_.toString).getOrElse("<deleted>")
    def clear() { ref = None }
    def enqueue(): Boolean = false
    def isEnqueued(): Boolean = false
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
abstract class RefBuffer[A <: AnyRef] extends Buffer[A] with SingleRefCollection[A] { self =>
  protected val underlying: Buffer[Reference[A]]

  def +=(el: A): this.type = { purgeReferences(); underlying += Ref(el); this }
  def +=:(el: A) = { purgeReferences(); Ref(el) +=: underlying; this }
  def remove(el: A) { underlying -= Ref(el); purgeReferences(); }
  def remove(n: Int) = { val el = apply(n); remove(el); el }
  def insertAll(n: Int, iter: Iterable[A]) {
    purgeReferences()
    underlying.insertAll(n, iter.view.map(Ref(_)))
  }
  def update(n: Int, el: A) { purgeReferences(); underlying(n) = Ref(el) }
<<<<<<< HEAD
  def apply(n: Int) = { 
=======
  def apply(n: Int) = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    purgeReferences()
    var el = underlying(n).get
    while (el == None) {
      purgeReferences(); el = underlying(n).get
    }
    el.get
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def length = { purgeReferences(); underlying.length }
  def clear() { underlying.clear(); purgeReferences() }

  protected[this] def removeReference(ref: Reference[A]) { underlying -= ref }
}
<<<<<<< HEAD
 
private[swing] abstract class RefSet[A <: AnyRef] extends mutable.Set[A] with SingleRefCollection[A] { self =>
  protected val underlying: mutable.Set[Reference[A]]
  
=======

private[swing] abstract class RefSet[A <: AnyRef] extends mutable.Set[A] with SingleRefCollection[A] { self =>
  protected val underlying: mutable.Set[Reference[A]]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def -=(el: A): this.type = { underlying -= Ref(el); purgeReferences(); this }
  def +=(el: A): this.type = { purgeReferences(); underlying += Ref(el); this }
  def contains(el: A): Boolean = { purgeReferences(); underlying.contains(Ref(el)) }
  override def size = { purgeReferences(); underlying.size }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected[this] def removeReference(ref: Reference[A]) { underlying -= ref }
}
