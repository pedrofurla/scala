object Test extends App {
  val p = new Proxy {
    def self = 2 
  }
  println(p equals 1)
  println(p equals 2)
  println(p equals 3)
  println(p equals null)
<<<<<<< HEAD
=======
  
  case class Bippy(a: String) extends Proxy {
    def self = a
  }
  
  val label = Bippy("bippy!")
  println(label == label)
  println(label == "bippy!")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
