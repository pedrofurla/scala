package scala.swing

<<<<<<< HEAD
abstract class SwingApplication extends Reactor {
  def main(args: Array[String]) = Swing.onEDT { startup(args) }
  
  def startup(args: Array[String])
  def quit() { shutdown(); sys.exit(0) }
=======
/** Convenience class with utility methods for GUI applications. */
abstract class SwingApplication extends Reactor {

  /** Initializes the application and runs the given program. */
  def main(args: Array[String]) = Swing.onEDT { startup(args) }

  /** Called before the GUI is created. Override to customize. */
  def startup(args: Array[String])

  /** Finalizes the application by calling `shutdown` and exits.*/
  def quit() { shutdown(); sys.exit(0) }

  /** Called before the application is exited. Override to customize. */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def shutdown() {}
}
