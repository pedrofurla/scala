/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import javax.swing.{JScrollBar, BoundedRangeModel}
import java.awt.event.{AdjustmentListener}

object ScrollBar {
<<<<<<< HEAD
  def wrap(c: JScrollBar): ScrollBar = { 
    val w = UIElement.cachedWrapper[ScrollBar](c)
    if (w != null) w 
=======
  def wrap(c: JScrollBar): ScrollBar = {
    val w = UIElement.cachedWrapper[ScrollBar](c)
    if (w != null) w
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    else new ScrollBar { override lazy val peer = c }
  }
}

class ScrollBar extends Component with Orientable.Wrapper with Adjustable.Wrapper {
	override lazy val peer: JScrollBar = new JScrollBar with SuperMixin

	def valueIsAjusting = peer.getValueIsAdjusting
	def valueIsAjusting_=(b : Boolean) = peer.setValueIsAdjusting(b)

	// TODO: can we find a better interface?
<<<<<<< HEAD
	//def setValues(value: Int = this.value, visible: Int = visibleAmount, 
	//             min: Int = minimum, max: Int = maximum) =    
	//  peer.setValues(value, visible, min, max)

// Not currently needed, requires wrapper for BoundedRangeModel 
=======
	//def setValues(value: Int = this.value, visible: Int = visibleAmount,
	//             min: Int = minimum, max: Int = maximum) =
	//  peer.setValues(value, visible, min, max)

// Not currently needed, requires wrapper for BoundedRangeModel
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
//
//    	    def model = peer.getModel
//    	    def model_=(m : BoundedRangeModel) = peer.setModel(m)
}
