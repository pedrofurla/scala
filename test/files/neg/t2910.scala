object Junk {
  def f(l: List[String]): List[String] = {
    val ret = l.map({ case MyMatch(id) => id })
    val MyMatch = "(\\d+)".r
    ret
  }

  def test2() {
    println(s.length)
    val z = 0
    lazy val s = "abc"
  }
    
  def test4() {
    lazy val x = {
      x
      val z = 0
      lazy val x = 12
      z
    }
  }
}

// updated forwards.scala for lazy vals
object Test {
  lazy val f: Int = x
  val x: Int = f

  {
    lazy val f: Int = x
    val x: Int = f
<<<<<<< HEAD
=======
    println(x)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
  {
    lazy val f: Int = g
    var x: Int = f
    lazy val g: Int = x
  }     
}
