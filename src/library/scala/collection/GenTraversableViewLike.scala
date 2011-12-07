/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection


import generic._
import mutable.{ Builder, ArrayBuffer }
import TraversableView.NoBuilder
import annotation.migration



<<<<<<< HEAD
trait GenTraversableViewLike[+A, 
                             +Coll, 
=======
trait GenTraversableViewLike[+A,
                             +Coll,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                             +This <: GenTraversableView[A, Coll] with GenTraversableViewLike[A, Coll, This]]
extends GenTraversable[A] with GenTraversableLike[A, This] {
self =>

  def force[B >: A, That](implicit bf: CanBuildFrom[Coll, B, That]): That
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected def underlying: Coll
  protected[this] def viewIdentifier: String
  protected[this] def viewIdString: String
  def viewToString = stringPrefix + viewIdString + "(...)"
<<<<<<< HEAD
  
  /** The implementation base trait of this view.
   *  This trait and all its subtraits has to be re-implemented for each
   *  ViewLike class. 
   */
  trait Transformed[+B] extends GenTraversableView[B, Coll] {
    def foreach[U](f: B => U): Unit
    
=======

  /** The implementation base trait of this view.
   *  This trait and all its subtraits has to be re-implemented for each
   *  ViewLike class.
   */
  trait Transformed[+B] extends GenTraversableView[B, Coll] {
    def foreach[U](f: B => U): Unit

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    lazy val underlying = self.underlying
    final override protected[this] def viewIdString = self.viewIdString + viewIdentifier
    override def stringPrefix = self.stringPrefix
    override def toString = viewToString
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait EmptyView extends Transformed[Nothing] {
    final override def isEmpty = true
    final override def foreach[U](f: Nothing => U): Unit = ()
  }
<<<<<<< HEAD
  
  /** A fall back which forces everything into a vector and then applies an operation
   *  on it. Used for those operations which do not naturally lend themselves to a view
   */ 
=======

  /** A fall back which forces everything into a vector and then applies an operation
   *  on it. Used for those operations which do not naturally lend themselves to a view
   */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait Forced[B] extends Transformed[B] {
    protected[this] val forced: GenSeq[B]
    def foreach[U](f: B => U) = forced foreach f
    final override protected[this] def viewIdentifier = "C"
  }

  trait Sliced extends Transformed[A] {
    protected[this] val endpoints: SliceInterval
    protected[this] def from  = endpoints.from
    protected[this] def until = endpoints.until
    // protected def newSliced(_endpoints: SliceInterval): Transformed[A] =
    //   self.newSliced(endpoints.recalculate(_endpoints))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def foreach[U](f: A => U) {
      var index = 0
      for (x <- self) {
        if (from <= index) {
          if (until <= index) return
          f(x)
        }
        index += 1
      }
    }
    final override protected[this] def viewIdentifier = "S"
  }

  trait Mapped[B] extends Transformed[B] {
    protected[this] val mapping: A => B
    def foreach[U](f: B => U) {
      for (x <- self)
        f(mapping(x))
    }
    final override protected[this] def viewIdentifier = "M"
  }

  trait FlatMapped[B] extends Transformed[B] {
    protected[this] val mapping: A => GenTraversableOnce[B]
    def foreach[U](f: B => U) {
      for (x <- self)
        for (y <- mapping(x).seq)
          f(y)
    }
    final override protected[this] def viewIdentifier = "N"
  }

  trait Appended[B >: A] extends Transformed[B] {
    protected[this] val rest: GenTraversable[B]
    def foreach[U](f: B => U) {
      self foreach f
      rest foreach f
    }
    final override protected[this] def viewIdentifier = "A"
  }

  trait Filtered extends Transformed[A] {
<<<<<<< HEAD
    protected[this] val pred: A => Boolean 
=======
    protected[this] val pred: A => Boolean
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def foreach[U](f: A => U) {
      for (x <- self)
        if (pred(x)) f(x)
    }
    final override protected[this] def viewIdentifier = "F"
  }

  trait TakenWhile extends Transformed[A] {
<<<<<<< HEAD
    protected[this] val pred: A => Boolean 
=======
    protected[this] val pred: A => Boolean
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def foreach[U](f: A => U) {
      for (x <- self) {
        if (!pred(x)) return
        f(x)
      }
    }
    final override protected[this] def viewIdentifier = "T"
  }

  trait DroppedWhile extends Transformed[A] {
<<<<<<< HEAD
    protected[this] val pred: A => Boolean 
=======
    protected[this] val pred: A => Boolean
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def foreach[U](f: A => U) {
      var go = false
      for (x <- self) {
        if (!go && !pred(x)) go = true
        if (go) f(x)
      }
    }
    final override protected[this] def viewIdentifier = "D"
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}


