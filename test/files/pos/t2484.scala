class Admin extends javax.swing.JApplet {
  val jScrollPane = new javax.swing.JScrollPane (null, 0, 0)
<<<<<<< HEAD
  def bug2484: Unit = {
=======
  def t2484: Unit = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    scala.concurrent.ops.spawn {jScrollPane.synchronized {
      def someFunction () = {}
      //scala.concurrent.ops.spawn {someFunction ()}
      jScrollPane.addComponentListener (new java.awt.event.ComponentAdapter {override def componentShown (e: java.awt.event.ComponentEvent) = {
        someFunction (); jScrollPane.removeComponentListener (this)}})
    }}
  }
}
// t2630.scala
object Test {
  def meh(xs: List[Any]) {
    xs map { x =>  (new AnyRef {}) }
  }
}
