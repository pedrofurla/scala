object Test extends App {
  val a = Array(1, 2, 3)
<<<<<<< HEAD
  println(a.deepToString)
=======
  println(a.deep.toString)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  val aaiIncomplete = new Array[Array[Array[Int]]](3)
  println(aaiIncomplete(0))

  val aaiComplete: Array[Array[Int]] = Array.ofDim[Int](3, 3) // new Array[Array[Int]](3, 3)
  println(aaiComplete.deep)
  for (i <- 0 until 3; j <- 0 until 3)
    aaiComplete(i)(j) = i + j
<<<<<<< HEAD
  println(aaiComplete.deepToString)
=======
  println(aaiComplete.deep.toString)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  assert(aaiComplete.last.last == 4)
}
