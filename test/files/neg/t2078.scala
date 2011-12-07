class A[-S](y : S) {
  val f  = new { val x = y }
}

object Test extends App {
  val a = new A(1)
  val b = a : A[Nothing]
<<<<<<< HEAD
  b.f.x
=======
  println(b.f.x)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
