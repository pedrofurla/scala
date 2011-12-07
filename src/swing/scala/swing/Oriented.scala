/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

object Oriented {
  trait Wrapper extends Oriented {
    def peer: OrientedMixin
<<<<<<< HEAD
    
    /*
     * Need to revert to structural type, since scroll bars are oriented 
=======

    /*
     * Need to revert to structural type, since scroll bars are oriented
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * and these are created by scroll panes. Shouldn't be a bootleneck.
     */
    protected type OrientedMixin = {
      def getOrientation(): Int
<<<<<<< HEAD
      def setOrientation(n: Int) 
=======
      def setOrientation(n: Int)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
    def orientation: Orientation.Value = Orientation(peer.getOrientation)
  }
}

/**
 * Something that can have an orientation.
 */
trait Oriented {
  def orientation: Orientation.Value
}
