/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

object Scrollable {
  trait Wrapper extends Scrollable {
    protected def scrollablePeer: javax.swing.Scrollable
    def preferredViewportSize = scrollablePeer.getPreferredScrollableViewportSize
<<<<<<< HEAD
    
    def tracksViewportHeight: Boolean = scrollablePeer.getScrollableTracksViewportHeight
    def tracksViewportWidth: Boolean = scrollablePeer.getScrollableTracksViewportWidth
    
    def blockIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int =
      scrollablePeer.getScrollableBlockIncrement(visibleRect, orientation.id, direction)
    
=======

    def tracksViewportHeight: Boolean = scrollablePeer.getScrollableTracksViewportHeight
    def tracksViewportWidth: Boolean = scrollablePeer.getScrollableTracksViewportWidth

    def blockIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int =
      scrollablePeer.getScrollableBlockIncrement(visibleRect, orientation.id, direction)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def unitIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int =
      scrollablePeer.getScrollableUnitIncrement(visibleRect, orientation.id, direction)
  }
}

/**
 * A component that is specially suitable for being placed inside a
<<<<<<< HEAD
 * <code>ScrollPane</code>. 
 * 
=======
 * <code>ScrollPane</code>.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.Scrollable
 */
trait Scrollable extends Component {
  def preferredViewportSize: Dimension
<<<<<<< HEAD
  
  def tracksViewportHeight: Boolean
  def tracksViewportWidth: Boolean
  
  def blockIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int  
=======

  def tracksViewportHeight: Boolean
  def tracksViewportWidth: Boolean

  def blockIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def unitIncrement(visibleRect: Rectangle, orientation: Orientation.Value, direction: Int): Int
}
