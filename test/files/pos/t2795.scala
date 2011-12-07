<<<<<<< HEAD
package bug1
=======
package t1
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

trait Element[T] {
}

trait Config {
  type T <: Element[T]
  implicit val m: ClassManifest[T]
  // XXX Following works fine:
  // type T <: Element[_]
}

trait Transform { self: Config =>
  def processBlock(block: Array[T]): Unit = {
    var X = new Array[T](1)
  }
}
