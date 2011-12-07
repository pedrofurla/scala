package scala.swing

import scala.actors._

object SwingWorker {
<<<<<<< HEAD
  
}

abstract class SwingWorker extends Actor {  
  def queue() {
    
  }
  
  def done() {
    
  }
  
=======

}

abstract class SwingWorker extends Actor {
  def queue() {

  }

  def done() {

  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private var _cancelled = false
  def cancelled: Boolean = _cancelled
  def cancelled_=(b: Boolean) { _cancelled = b }
}