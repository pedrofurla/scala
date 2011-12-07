/*
  File: LinkedQueue.java

  Originally written by Doug Lea and released into the public domain.
  This may be used for any purposes whatsoever without acknowledgment.
  Thanks for the assistance and support of Sun Microsystems Labs,
  and everyone contributing, testing, and using this code.

  History:
  Date       Who                What
  11Jun1998  dl               Create public version
  25aug1998  dl               added peek
  10dec1998  dl               added isEmpty
  10oct1999  dl               lock on node object to ensure visibility
*/

package scala.actors;

/**
 * A linked list based channel implementation.
 * The algorithm avoids contention between puts
<<<<<<< HEAD
 * and takes when the queue is not empty. 
 * Normally a put and a take can proceed simultaneously. 
=======
 * and takes when the queue is not empty.
 * Normally a put and a take can proceed simultaneously.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * (Although it does not allow multiple concurrent puts or takes.)
 * This class tends to perform more efficently than
 * other Channel implementations in producer/consumer
 * applications.
 * <p>[<a href="http://gee.cs.oswego.edu/dl/classes/EDU/oswego/cs/dl/util/concurrent/intro.html"> Introduction to this package. </a>]
 **/

public class LinkedQueue {


<<<<<<< HEAD
  /** 
   * Dummy header node of list. The first actual node, if it exists, is always 
   * at head_.next. After each take, the old first node becomes the head.
   **/
  protected LinkedNode head_;         
=======
  /**
   * Dummy header node of list. The first actual node, if it exists, is always
   * at head_.next. After each take, the old first node becomes the head.
   **/
  protected LinkedNode head_;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /**
   * Helper monitor for managing access to last node.
   **/
<<<<<<< HEAD
  protected final Object putLock_ = new Object(); 

  /** 
   * The last node of list. Put() appends to list, so modifies last_
   **/
  protected LinkedNode last_;         
=======
  protected final Object putLock_ = new Object();

  /**
   * The last node of list. Put() appends to list, so modifies last_
   **/
  protected LinkedNode last_;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /**
   * The number of threads waiting for a take.
   * Notifications are provided in put only if greater than zero.
   * The bookkeeping is worth it here since in reasonably balanced
   * usages, the notifications will hardly ever be necessary, so
   * the call overhead to notify can be eliminated.
   **/
<<<<<<< HEAD
  protected int waitingForTake_ = 0;  

  public LinkedQueue() {
    head_ = new LinkedNode(null); 
=======
  protected int waitingForTake_ = 0;

  public LinkedQueue() {
    head_ = new LinkedNode(null);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    last_ = head_;
  }

  /** Main mechanics for put/offer **/
<<<<<<< HEAD
  protected void insert(Object x) { 
=======
  protected void insert(Object x) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    synchronized(putLock_) {
      LinkedNode p = new LinkedNode(x);
      synchronized(last_) {
        last_.next = p;
        last_ = p;
      }
      if (waitingForTake_ > 0)
        putLock_.notify();
    }
  }

  /** Main mechanics for take/poll **/
  protected synchronized Object extract() {
    synchronized(head_) {
      Object x = null;
      LinkedNode first = head_.next;
      if (first != null) {
        x = first.value;
        first.value = null;
<<<<<<< HEAD
        head_ = first; 
=======
        head_ = first;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
      return x;
    }
  }


  public void put(Object x) throws InterruptedException {
    if (x == null) throw new IllegalArgumentException();
    if (Thread.interrupted()) throw new InterruptedException();
<<<<<<< HEAD
    insert(x); 
  }

  public boolean offer(Object x, long msecs) throws InterruptedException { 
    if (x == null) throw new IllegalArgumentException();
    if (Thread.interrupted()) throw new InterruptedException();
    insert(x); 
=======
    insert(x);
  }

  public boolean offer(Object x, long msecs) throws InterruptedException {
    if (x == null) throw new IllegalArgumentException();
    if (Thread.interrupted()) throw new InterruptedException();
    insert(x);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    return true;
  }

  public Object take() throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    // try to extract. If fail, then enter wait-based retry loop
    Object x = extract();
    if (x != null)
      return x;
<<<<<<< HEAD
    else { 
=======
    else {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      synchronized(putLock_) {
        try {
          ++waitingForTake_;
          for (;;) {
            x = extract();
            if (x != null) {
              --waitingForTake_;
              return x;
            }
            else {
<<<<<<< HEAD
              putLock_.wait(); 
            }
          }
        }
        catch(InterruptedException ex) { 
          --waitingForTake_; 
          putLock_.notify();
          throw ex; 
=======
              putLock_.wait();
            }
          }
        }
        catch(InterruptedException ex) {
          --waitingForTake_;
          putLock_.notify();
          throw ex;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        }
      }
    }
  }

  public Object peek() {
    synchronized(head_) {
      LinkedNode first = head_.next;
<<<<<<< HEAD
      if (first != null) 
        return first.value;
      else 
        return null;
    }
  }    
=======
      if (first != null)
        return first.value;
      else
        return null;
    }
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0


  public boolean isEmpty() {
    synchronized(head_) {
      return head_.next == null;
    }
<<<<<<< HEAD
  }    
=======
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  public Object poll(long msecs) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    Object x = extract();
<<<<<<< HEAD
    if (x != null) 
=======
    if (x != null)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      return x;
    else {
      synchronized(putLock_) {
        try {
          long waitTime = msecs;
          long start = (msecs <= 0)? 0 : System.currentTimeMillis();
          ++waitingForTake_;
          for (;;) {
            x = extract();
            if (x != null || waitTime <= 0) {
              --waitingForTake_;
              return x;
            }
            else {
<<<<<<< HEAD
              putLock_.wait(waitTime); 
=======
              putLock_.wait(waitTime);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              waitTime = msecs - (System.currentTimeMillis() - start);
            }
          }
        }
<<<<<<< HEAD
        catch(InterruptedException ex) { 
          --waitingForTake_; 
          putLock_.notify();
          throw ex; 
=======
        catch(InterruptedException ex) {
          --waitingForTake_;
          putLock_.notify();
          throw ex;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        }
      }
    }
  }
}


