<<<<<<< HEAD
import scala.collection.generic._
import scala.collection._

object Test {
  def collect[A, Res](r: Traversable[A])(implicit bf: CanBuild[A, Res]) = {
    val b = bf()
    for (a <- r) b += a
=======
import scala.collection.generic.CanBuildFrom
import scala.collection._

object Test {
  def collect[A, Res](r: Traversable[A])(implicit bf: generic.CanBuild[A, Res]) = {
    val b: collection.mutable.Builder[A, Res] = bf()
    r foreach ((a: A) => b += a)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    b.result
  }
  
  collect[Int, Vector[Int]](List(1,2,3,4)) 
  collect[Char, String](List('1','2','3','4'))
  collect[Char, Array[Char]](List('1','2','3','4'))  
}