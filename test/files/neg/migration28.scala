object Test {
  import scala.collection.mutable._
  
<<<<<<< HEAD
  val s = new Stack[Int]
  s ++= List(1,2,3)
  s map (_ + 1)
  s foreach (_ => ())
=======
  List(1,2,3,4,5).scanRight(0)(_+_)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  
  def main(args: Array[String]): Unit = {
    
  }
}
