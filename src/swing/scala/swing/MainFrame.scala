/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event._

/**
<<<<<<< HEAD
 * A frame that can be used for main application windows. Shuts down the 
 * framework and quits the application when closed.
 */
class MainFrame extends Frame {
=======
 * A frame that can be used for main application windows. Shuts down the
 * framework and quits the application when closed.
 */
class MainFrame(gc: java.awt.GraphicsConfiguration = null) extends Frame(gc) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def closeOperation() { sys.exit(0) }
}
