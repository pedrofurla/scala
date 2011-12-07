object Test extends App {
<<<<<<< HEAD
  println((1 to 100000).toList.sort(_<_).length)
  println(List(1, 5, 10, 3, 2).toList.sort(_<_))
  println(List(1, 5, 10, 3, 2).toList.sort(_>_))
  println(List(10).toList.sort(_<_))
  println(List(10,9).toList.sort(_<_))
  println(List[Int]().toList.sort(_<_))
=======
  println((1 to 100000).toList.sortWith(_<_).length)
  println(List(1, 5, 10, 3, 2).toList.sortWith(_<_))
  println(List(1, 5, 10, 3, 2).toList.sortWith(_>_))
  println(List(10).toList.sortWith(_<_))
  println(List(10,9).toList.sortWith(_<_))
  println(List[Int]().toList.sortWith(_<_))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

