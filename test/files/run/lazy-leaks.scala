class Lazy(f: => Int) {
     lazy val get: Int = f
}

object Test extends App
{
     val buffer = new scala.collection.mutable.ListBuffer[Lazy]

     // This test requires 4 Mb of RAM if Lazy is discarding thunks
     // It consumes 4 Gb of RAM if Lazy is not discarding thunks

<<<<<<< HEAD
     for (val idx <- Iterator.range(0, 1024)) {
=======
     for (idx <- Iterator.range(0, 1024)) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
         val data = new Array[Int](1024*1024)
         val lz: Lazy = new Lazy(data.length)
         buffer += lz
         lz.get
     }
}
