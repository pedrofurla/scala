/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import scala.collection.mutable.Buffer

object SequentialContainer {
  /**
   * Utility trait for wrapping sequential containers.
   */
  trait Wrapper extends SequentialContainer with Container.Wrapper {
    override val contents: Buffer[Component] = new Content
    //def contents_=(c: Component*)  { contents.clear(); contents ++= c }
  }
}

/**
<<<<<<< HEAD
 * A container for which a sequential order of children makes sense, such as 
=======
 * A container for which a sequential order of children makes sense, such as
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * flow panels, or menus. Its contents are mutable.
 */
trait SequentialContainer extends Container {
  /**
<<<<<<< HEAD
   * The mutable child components of this container. The order matters and 
=======
   * The mutable child components of this container. The order matters and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * usually indicates the layout of the children.
   */
  override def contents: Buffer[Component]
  //def contents_=(c: Component*)
}
