/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

/**
<<<<<<< HEAD
 * A panel that lays out its contents one after the other, 
 * either horizontally or vertically.
 * 
=======
 * A panel that lays out its contents one after the other,
 * either horizontally or vertically.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.BoxLayout
 */
class BoxPanel(orientation: Orientation.Value) extends Panel with SequentialContainer.Wrapper {
  override lazy val peer = {
<<<<<<< HEAD
    val p = new javax.swing.JPanel with SuperMixin 
=======
    val p = new javax.swing.JPanel with SuperMixin
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val l = new javax.swing.BoxLayout(p, orientation.id)
    p.setLayout(l)
    p
  }
}
