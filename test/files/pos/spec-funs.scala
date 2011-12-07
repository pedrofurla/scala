trait AbsFunction1[@specialized -T, @specialized +U] {
  def apply(x: T): U
}

final class IntTest {

  val niters = 10000

  def transF(xs: Array[Int], f: AbsFunction1[Int, Int]) = {
    var i = 0
    var s = 0
    while (i < xs.length) {
      xs(i) = f(xs(i)) + 1
      i += 1
    }
  }

  def run() {
    val xs = new Array[Int](10000)
    val f = new AbsFunction1[Int, Int] {
      def apply(x: Int): Int = x * x
    }    
    for (j <- 0 until niters) {
      transF(xs, f)
    }
    var acc = 0
    for (i <- 0 until xs.length) acc += xs(i)
    println(acc)
  }
}

final class ClosureTest {

  val niters = 10000

  def transF(xs: Array[Int], f: Int => Int) = {
    var i = 0
    var s = 0
    while (i < xs.length) {
      xs(i) = f.apply(xs(i)) + 1
      i += 1
    }
  }

  def run() {
    val xs = new Array[Int](10000)
//    val f = (x: Int) => x * x
    for (j <- 0 until niters) {
      transF(xs, x => x * x)
    }
    var acc = 0
    for (i <- 0 until xs.length) acc += xs(i)
    println(acc)
  }
}

<<<<<<< HEAD
object TestInt extends testing.Benchmark {
  def run() = (new IntTest).run()
}

object TestClosure extends testing.Benchmark {
=======
object TestInt extends scala.testing.Benchmark {
  def run() = (new IntTest).run()
}

object TestClosure extends scala.testing.Benchmark {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def run() = (new ClosureTest).run()
}
