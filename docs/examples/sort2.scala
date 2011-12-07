package examples

object sort2 {

  def sort(a: List[Int]): List[Int] = {
    if (a.length < 2)
      a
    else {
      val pivot = a(a.length / 2)
      def lePivot(x: Int) = x < pivot
      def gtPivot(x: Int) = x > pivot
      def eqPivot(x: Int) = x == pivot
      sort(a filter lePivot) :::
<<<<<<< HEAD
           sort(a filter eqPivot) :::
=======
           (a filter eqPivot) :::
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
           sort(a filter gtPivot)
    }
  }

  def main(args: Array[String]) {
<<<<<<< HEAD
    val xs = List(6, 2, 8, 5, 1)
=======
    val xs = List(6, 2, 8, 5, 1, 8)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    println(xs)
    println(sort(xs))
  }

}
