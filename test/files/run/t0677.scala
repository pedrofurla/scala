object Test extends App {
  class X[T: ClassManifest] { 
<<<<<<< HEAD
    val a = new Array[Array[T]](3,4)
    val b = Array.ofDim[T](3, 4) 
=======
    val a = Array.ofDim[T](3, 4)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
  val x = new X[String]
  x.a(1)(2) = "hello"
  assert(x.a(1)(2) == "hello")
}
