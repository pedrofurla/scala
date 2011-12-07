/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.swing
package model

// Dummy to keep ant from recompiling on every run.
trait Matrix { }

/*trait Matrix[A] extends Function2[Int, Int, A] {
<<<<<<< HEAD
  
  val width: Int
  val height: Int
  
  assert(width > 0 && height > 0)
  
  private val delegate = new Array[A](width * height)
  
  override def apply(col: Int, row: Int): A =
    delegate(col * height + row)
    
  def apply(coord: (Int, Int)): A = 
    apply(coord._1, coord._2)
  
  def col(index: Int): Matrix.FlatSeq[A] =
    new Matrix.SubArray[A](delegate, index * height, height)
  
  def row(index: Int): Matrix.FlatSeq[A] =
    new Matrix.SparseArray[A](delegate, index, height)
  
  def update(xpos: Int, ypos: Int, elem: A) {
    delegate(xpos % width * height + ypos % height) = elem
  }
  
  def update(coord: (Int, Int), elem: A) {
    update(coord._1, coord._2, elem)
  }
  
=======

  val width: Int
  val height: Int

  assert(width > 0 && height > 0)

  private val delegate = new Array[A](width * height)

  override def apply(col: Int, row: Int): A =
    delegate(col * height + row)

  def apply(coord: (Int, Int)): A =
    apply(coord._1, coord._2)

  def col(index: Int): Matrix.FlatSeq[A] =
    new Matrix.SubArray[A](delegate, index * height, height)

  def row(index: Int): Matrix.FlatSeq[A] =
    new Matrix.SparseArray[A](delegate, index, height)

  def update(xpos: Int, ypos: Int, elem: A) {
    delegate(xpos % width * height + ypos % height) = elem
  }

  def update(coord: (Int, Int), elem: A) {
    update(coord._1, coord._2, elem)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def initializeWith(f: (Int, Int) => A): this.type = {
    for (index <- 0 until (width * height))
      delegate(index) = f(index / height, index % height)
    this
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def initializeTo(v: => A): this.type = {
    for (index <- 0 until (width * height))
      delegate(index) = v
    this
  }
<<<<<<< HEAD
  
  def size: (Int, Int) = (width, height)
  
=======

  def size: (Int, Int) = (width, height)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A flattened view of the matrix. The flattening is done on columns i.e.
    * the first values of the flattened sequence are the cells of the first
    * column. As this is a view of the matrix, any change to the matrix will
    * also be visible in the flattened array, and vice-versa. */
  def flat: Array[A] = delegate
<<<<<<< HEAD
  
}

object Matrix {
  
=======

}

object Matrix {

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply[A](columns: Int, rows: Int) = new Matrix[A] {
    val width = columns
    val height = rows
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply[A](default: (Int, Int) => A, columns: Int, rows: Int) = new Matrix[A] {
    val width = columns
    val height = rows
    initializeWith(default)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply[A](default: => A, columns: Int, rows: Int) = new Matrix[A] {
    val width = columns
    val height = rows
    initializeTo(default)
  }
<<<<<<< HEAD
  
  trait FlatSeq[A] extends RandomAccessSeq[A] {
    def        update (index: Int, elem: A): Unit
  }
  
=======

  trait FlatSeq[A] extends RandomAccessSeq[A] {
    def        update (index: Int, elem: A): Unit
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private class SubArray[A](delegate: Array[A], start: Int, val length: Int) extends FlatSeq[A] {
    def apply(index: Int): A =
      if (index < length)
        delegate(index + start)
      else throw new IndexOutOfBoundsException
    def update(index: Int, elem: A): Unit =
      if (index < length)
        delegate(index + start) = elem
      else throw new IndexOutOfBoundsException
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private class SparseArray[A](delegate: Array[A], start: Int, span: Int) extends FlatSeq[A] {
    def apply(index: Int): A = {
      if (index < length)
        delegate((index * span) + start)
      else throw new IndexOutOfBoundsException
    }
    def length: Int = delegate.length / span
    def update(index: Int, elem: A): Unit =
      if (index < length)
        delegate((index * span) + start) = elem
      else throw new IndexOutOfBoundsException
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def MatrixToSeqs[A](matrix: Matrix[A]): Seq[Seq[A]] = {
    val result = new Array[SubArray[A]](matrix.width)
    for (col <- 0 until matrix.width)
      result(col) = new SubArray[A](matrix.delegate, col * matrix.height, matrix.height)
    result
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}*/
