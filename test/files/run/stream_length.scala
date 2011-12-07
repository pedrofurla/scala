

object Test {
  def walk(depth: Int, bias: String): Stream[String] = {
    if (depth == 0)
      Stream(bias)
    else {
<<<<<<< HEAD
      Stream.concat(Stream.range(1, 100).map((x: Int) => walk(depth-1, bias + x)))
=======
      (Stream.iterate(1, 99)(_+1).map((x: Int) => walk(depth-1, bias + x))).flatten
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
  }

  def main(args: Array[String]) {
    println("Length: " + walk(3, "---").length)
  }
}
