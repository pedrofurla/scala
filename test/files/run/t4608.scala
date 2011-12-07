<<<<<<< HEAD



=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
// #4608
object Test {
  
  def main(args: Array[String]) {
    ((1 to 100) sliding 10).toList.par.map{_.map{i => i * i}}.flatten
  }
  
}
