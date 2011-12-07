/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection.generic


import java.util.concurrent.atomic.AtomicInteger





/**
 * A message interface serves as a unique interface to the
 * part of the collection capable of receiving messages from
 * a different task.
<<<<<<< HEAD
 * 
 * One example of use of this is the `find` method, which can use the
 * signalling interface to inform worker threads that an element has
 * been found and no further search is necessary.
 * 
 * @author prokopec
 * 
=======
 *
 * One example of use of this is the `find` method, which can use the
 * signalling interface to inform worker threads that an element has
 * been found and no further search is necessary.
 *
 * @author prokopec
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @define abortflag
 * Abort flag being true means that a worker can abort and produce whatever result,
 * since its result will not affect the final result of computation. An example
 * of operations using this are `find`, `forall` and `exists` methods.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @define indexflag
 * The index flag holds an integer which carries some operation-specific meaning. For
 * instance, `takeWhile` operation sets the index flag to the position of the element
 * where the predicate fails. Other workers may check this index against the indices
 * they are working on and return if this index is smaller than their index. Examples
 * of operations using this are `takeWhile`, `dropWhile`, `span` and `indexOf`.
 */
trait Signalling {
  /**
   * Checks whether an abort signal has been issued.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * $abortflag
   * @return the state of the abort
   */
  def isAborted: Boolean
<<<<<<< HEAD
  
  /**
   * Sends an abort signal to other workers.
   * 
   * $abortflag
   */
  def abort(): Unit
  
  /**
   * Returns the value of the index flag.
   * 
=======

  /**
   * Sends an abort signal to other workers.
   *
   * $abortflag
   */
  def abort(): Unit

  /**
   * Returns the value of the index flag.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * $indexflag
   * @return the value of the index flag
   */
  def indexFlag: Int
<<<<<<< HEAD
  
  /**
   * Sets the value of the index flag.
   * 
=======

  /**
   * Sets the value of the index flag.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * $indexflag
   * @param f the value to which the index flag is set.
   */
  def setIndexFlag(f: Int)
<<<<<<< HEAD
  
  /**
   * Sets the value of the index flag if argument is greater than current value.
   * This method does this atomically.
   * 
=======

  /**
   * Sets the value of the index flag if argument is greater than current value.
   * This method does this atomically.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * $indexflag
   * @param f the value to which the index flag is set
   */
  def setIndexFlagIfGreater(f: Int)
<<<<<<< HEAD
  
  /**
   * Sets the value of the index flag if argument is lesser than current value.
   * This method does this atomically.
   * 
=======

  /**
   * Sets the value of the index flag if argument is lesser than current value.
   * This method does this atomically.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * $indexflag
   * @param f the value to which the index flag is set
   */
  def setIndexFlagIfLesser(f: Int)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * A read only tag specific to the signalling object. It is used to give
   * specific workers information on the part of the collection being operated on.
   */
  def tag: Int
}


/**
 * This signalling implementation returns default values and ignores received signals.
 */
class DefaultSignalling extends Signalling with VolatileAbort {
  def indexFlag = -1
  def setIndexFlag(f: Int) {}
  def setIndexFlagIfGreater(f: Int) {}
  def setIndexFlagIfLesser(f: Int) {}
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def tag = -1
}


/**
 * An object that returns default values and ignores received signals.
 */
object IdleSignalling extends DefaultSignalling


/**
 * A mixin trait that implements abort flag behaviour using volatile variables.
 */
trait VolatileAbort extends Signalling {
  @volatile private var abortflag = false
  override def isAborted = abortflag
  override def abort() = abortflag = true
}


/**
 * A mixin trait that implements index flag behaviour using atomic integers.
 * The `setIndex` operation is wait-free, while conditional set operations `setIndexIfGreater`
 * and `setIndexIfLesser` are lock-free and support only monotonic changes.
 */
trait AtomicIndexFlag extends Signalling {
  private val intflag: AtomicInteger = new AtomicInteger(-1)
  abstract override def indexFlag = intflag.get
  abstract override def setIndexFlag(f: Int) = intflag.set(f)
  abstract override def setIndexFlagIfGreater(f: Int) = {
    var loop = true
    do {
      val old = intflag.get
      if (f <= old) loop = false
      else if (intflag.compareAndSet(old, f)) loop = false
    } while (loop);
  }
  abstract override def setIndexFlagIfLesser(f: Int) = {
    var loop = true
    do {
      val old = intflag.get
      if (f >= old) loop = false
      else if (intflag.compareAndSet(old, f)) loop = false
    } while (loop);
  }
}


/**
 * An implementation of the signalling interface using delegates.
 */
trait DelegatedSignalling extends Signalling {
  /**
   * A delegate that method calls are redirected to.
   */
  var signalDelegate: Signalling
<<<<<<< HEAD
  
  def isAborted = signalDelegate.isAborted
  def abort() = signalDelegate.abort
  
=======

  def isAborted = signalDelegate.isAborted
  def abort() = signalDelegate.abort

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def indexFlag = signalDelegate.indexFlag
  def setIndexFlag(f: Int) = signalDelegate.setIndexFlag(f)
  def setIndexFlagIfGreater(f: Int) = signalDelegate.setIndexFlagIfGreater(f)
  def setIndexFlagIfLesser(f: Int) = signalDelegate.setIndexFlagIfLesser(f)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def tag = signalDelegate.tag
}


/**
 * Class implementing delegated signalling.
 */
class DelegatedContext(var signalDelegate: Signalling) extends DelegatedSignalling


/**
 * Class implementing delegated signalling, but having its own distinct `tag`.
 */
class TaggedDelegatedContext(deleg: Signalling, override val tag: Int) extends DelegatedContext(deleg)











