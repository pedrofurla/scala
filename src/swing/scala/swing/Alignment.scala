/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import javax.swing.SwingConstants._

/**
 * Horizontal and vertical alignments. We sacrifice a bit of type-safety
 * for simplicity here.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.SwingConstants
 */
object Alignment extends Enumeration {
  val Left = Value(LEFT)
  val Right = Value(RIGHT)
  val Center = Value(CENTER)
  val Top = Value(TOP)
  val Bottom = Value(BOTTOM)
  //1.6: val Baseline = Value(BASELINE)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Leading = Value(LEADING)
  val Trailing = Value(TRAILING)
}

