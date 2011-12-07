/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.util.parsing.input

<<<<<<< HEAD
/** A trait for objects that have a source position. 
=======
/** A trait for objects that have a source position.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * @author Martin Odersky, Adriaan Moors
 */
trait Positional {

  /** The source position of this object, initially set to undefined. */
  var pos: Position = NoPosition

  /** If current source position is undefined, update it with given position `newpos`
<<<<<<< HEAD
   *  @return  the object itself 
=======
   *  @return  the object itself
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def setPos(newpos: Position): this.type = {
    if (pos eq NoPosition) pos = newpos
    this
  }
}
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

