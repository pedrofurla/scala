/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import javax.swing.JApplet

/** <p>
 *    Clients should implement the ui field. See the <code>SimpleApplet</code>
 *    demo for an example.
 *  </p>
 *  <p>
 *    <b>Note</b>: <code>Applet</code> extends <code>javax.swing.JApplet</code>
 *    to satisfy Java's applet loading mechanism. The usual component wrapping
<<<<<<< HEAD
 *    scheme doesn't  work here. 
=======
 *    scheme doesn't  work here.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  </p>
 *
 *  @see javax.swing.JApplet
 */
abstract class Applet extends JApplet { outer =>
  val ui: UI
<<<<<<< HEAD
  
  override def init() { ui.init() }
  override def start() { ui.start() }
  override def stop() { ui.stop() }
  
=======

  override def init() { ui.init() }
  override def start() { ui.start() }
  override def stop() { ui.stop() }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  abstract class UI extends RootPanel {
    def peer = outer
    override def contents_=(c: Component) {
      super.contents_=(c)
      peer.validate()
    }

    def init()
    def start() {}
    def stop() {}
  }
}

