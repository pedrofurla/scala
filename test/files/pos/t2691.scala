object Breakdown {
  def unapplySeq(x: Int): Some[List[String]] = Some(List("", "there")) 
}
object Test {
  42 match {
    case Breakdown("") =>  // needed to trigger bug
    case Breakdown("", who) => println ("hello " + who)
  }
<<<<<<< HEAD
}
=======
}
object Test2 {
  42 match {
    case Breakdown("") =>  // needed to trigger bug
    case Breakdown("foo") =>  // needed to trigger bug
    case Breakdown("", who) => println ("hello " + who)
  }
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
