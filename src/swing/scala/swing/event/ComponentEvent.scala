/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing
package event

trait ComponentEvent extends UIEvent {
  val source: Component
}
<<<<<<< HEAD

@deprecated("Use UIElementMoved instead.", "2.8.0") 
case class ComponentMoved(source: Component) extends ComponentEvent
@deprecated("Use UIElementResized instead.", "2.8.0") 
case class ComponentResized(source: Component) extends ComponentEvent
@deprecated("Use UIElementShown instead.", "2.8.0") 
case class ComponentShown(source: Component) extends ComponentEvent
@deprecated("Use UIElementHidden instead.", "2.8.0") 
case class ComponentHidden(source: Component) extends ComponentEvent
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
