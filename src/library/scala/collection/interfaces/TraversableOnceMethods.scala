/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package interfaces

trait TraversableOnceMethods[+A] {
  self: TraversableOnce[A] =>
<<<<<<< HEAD
  
  def foreach[U](f: A => U): Unit
  def size: Int
  protected[this] def reversed: TraversableOnce[A]
  
=======

  def foreach[U](f: A => U): Unit
  def size: Int
  protected[this] def reversed: TraversableOnce[A]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // tests
  def hasDefiniteSize: Boolean
  def isEmpty: Boolean
  def isTraversableAgain: Boolean
  def nonEmpty: Boolean
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // applying a predicate
  def collectFirst[B](pf: PartialFunction[A, B]): Option[B]
  def count(p: A => Boolean): Int
  def exists(p: A => Boolean): Boolean
  def find(p: A => Boolean): Option[A]
  def forall(p: A => Boolean): Boolean
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // folds
  def /:[B](z: B)(op: (B, A) => B): B
  def :\[B](z: B)(op: (A, B) => B): B
  def foldLeft[B](z: B)(op: (B, A) => B): B
  def foldRight[B](z: B)(op: (A, B) => B): B
  def reduceLeftOption[B >: A](op: (B, A) => B): Option[B]
  def reduceLeft[B >: A](op: (B, A) => B): B
  def reduceRightOption[B >: A](op: (A, B) => B): Option[B]
  def reduceRight[B >: A](op: (A, B) => B): B

  // copies
  def copyToArray[B >: A](xs: Array[B]): Unit
  def copyToArray[B >: A](xs: Array[B], start: Int): Unit
  def copyToArray[B >: A](xs: Array[B], start: Int, len: Int): Unit
  def copyToBuffer[B >: A](dest: mutable.Buffer[B]): Unit
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // conversions
  def toArray[B >: A : ClassManifest]: Array[B]
  def toBuffer[B >: A]: mutable.Buffer[B]
  def toIndexedSeq[B >: A]: immutable.IndexedSeq[B]
  def toIterable: Iterable[A]
  def toIterator: Iterator[A]
  def toList: List[A]
  def toMap[T, U](implicit ev: A <:< (T, U)): immutable.Map[T, U]
  def toSeq: Seq[A]
  def toSet[B >: A]: immutable.Set[B]
  def toStream: Stream[A]
  def toTraversable: Traversable[A]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // type-constrained folds
  def maxBy[B](f: A => B)(implicit cmp: Ordering[B]): A
  def max[B >: A](implicit cmp: Ordering[B]): A
  def minBy[B](f: A => B)(implicit cmp: Ordering[B]): A
  def min[B >: A](implicit cmp: Ordering[B]): A
  def product[B >: A](implicit num: Numeric[B]): B
  def sum[B >: A](implicit num: Numeric[B]): B
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // strings
  def mkString(start: String, sep: String, end: String): String
  def mkString(sep: String): String
  def mkString: String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def addString(buf: StringBuilder, start: String, sep: String, end: String): StringBuilder
  def addString(buf: StringBuilder, sep: String): StringBuilder
  def addString(buf: StringBuilder): StringBuilder
}
