object HashSpeedTest {
<<<<<<< HEAD
  import System.{nanoTime => now}
=======
  
  import System.{ nanoTime => now }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  def time[A](f: => A) = {
    val t0 = now
    val ans = f
    (ans, now - t0)
  }
<<<<<<< HEAD
  def ptime[A](f: => A) = {
    val (ans,dt) = time(f)
    printf("Elapsed: %.3f\n",dt*1e-9)
=======
  
  def ptime[A](f: => A) = {
    val (ans, dt) = time(f)
    printf("Elapsed: %.3f\n", dt * 1e-9)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    ans
  }

  object HashHist {
    var enabled = true
<<<<<<< HEAD
    val counts = new collection.mutable.HashMap[Int,Int]
    def add (i: Int) { if (enabled) counts(i) = counts.get(i).getOrElse(0)+1 }
=======
    val counts = new collection.mutable.HashMap[Int, Int]
    def add(i: Int) { if (enabled) counts(i) = counts.get(i).getOrElse(0) + 1 }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def resultAndReset = {
      var s = 0L
      var o = 0L
      var m = 0
      counts.valuesIterator.foreach(i => {
        s += i
<<<<<<< HEAD
        if (i>0) o += 1
        if (i>m) m = i
      })
      counts.clear
      (s,o,m)
    }
  }

  def report(s: String,res: (Long,Long,Int)) {
    println("Hash quality of "+s)
    printf("  %5.2f%% of entries are collisions\n",100*(res._1 - res._2).toDouble/res._1)
    printf("  Max of %d entries mapped to the same value\n",res._3)
  }

  // If you have MurmurHash3 installed, uncomment below (and in main)
  import scala.util.{MurmurHash3 => MH3}
  val justCountString: String => Unit = str => {
    var s,i = 0
    while (i < str.length) { s += str.charAt(i); i += 1 }
    HashHist.add(s)
  }
  val defaultHashString: String => Unit = str => HashHist.add(str.hashCode)
  val murmurHashString: String => Unit = str => HashHist.add(MH3.stringHash(str))
=======
        if (i > 0) o += 1
        if (i > m) m = i
      })
      counts.clear
      (s, o, m)
    }
  }

  def report(s: String, res: (Long, Long, Int)) {
    println("Hash quality of " + s)
    printf("  %5.2f%% of entries are collisions\n", 100 * (res._1 - res._2).toDouble / res._1)
    printf("  Max of %d entries mapped to the same value\n", res._3)
  }

  // If you have MurmurHash3 installed, uncomment below (and in main)
  import scala.util.{ MurmurHash3 => MH3 }
  
  val justCountString: String => Unit = str => {
    var s, i = 0
    while (i < str.length) { s += str.charAt(i); i += 1 }
    HashHist.add(s)
  }
  
  val defaultHashString: String => Unit = str => HashHist.add(str.hashCode)
  
  val murmurHashString: String => Unit = str => HashHist.add(MH3.stringHash(str))
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def makeCharStrings = {
    val a = new Array[Byte](4)
    val buffer = new collection.mutable.ArrayBuffer[String]
    var i: Int = 'A'
    while (i <= 'Z') {
<<<<<<< HEAD
      a(0) = (i&0xFF).toByte
      var j: Int = 'a'
      while (j <= 'z') {
        a(1) = (j&0xFF).toByte
        var k: Int = 'A'
        while (k <= 'z') {
          a(2) = (k&0xFF).toByte
          var l: Int = 'A'
          while (l <= 'z') {
            a(3) = (l&0xFF).toByte
=======
      a(0) = (i & 0xFF).toByte
      var j: Int = 'a'
      while (j <= 'z') {
        a(1) = (j & 0xFF).toByte
        var k: Int = 'A'
        while (k <= 'z') {
          a(2) = (k & 0xFF).toByte
          var l: Int = 'A'
          while (l <= 'z') {
            a(3) = (l & 0xFF).toByte
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            buffer += new String(a)
            l += 1
          }
          k += 1
        }
        j += 1
      }
      i += 1
    }
    buffer.toArray
  }
<<<<<<< HEAD
=======
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def hashCharStrings(ss: Array[String], hash: String => Unit) {
    var i = 0
    while (i < ss.length) {
      hash(ss(i))
      i += 1
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def justCountList: List[List[Int]] => Unit = lli => {
    var s = 0
    lli.foreach(_.foreach(s += _))
    HashHist.add(s)
  }
<<<<<<< HEAD
  def defaultHashList: List[List[Int]] => Unit = lli => HashHist.add(lli.hashCode)
=======
  
  def defaultHashList: List[List[Int]] => Unit = lli => HashHist.add(lli.hashCode)
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def makeBinaryLists = {
    def singleLists(depth: Int): List[List[Int]] = {
      if (depth <= 0) List(Nil)
      else {
<<<<<<< HEAD
        val set = singleLists(depth-1)
        val longest = set filter (_.length == depth-1)
=======
        val set = singleLists(depth - 1)
        val longest = set filter (_.length == depth - 1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        set ::: (longest.map(0 :: _)) ::: (longest.map(1 :: _))
      }
    }
    val buffer = new collection.mutable.ArrayBuffer[List[List[Int]]]
    val blocks = singleLists(4).toArray
    buffer += List(Nil)
    var i = 0
    while (i < blocks.length) {
      val li = blocks(i) :: Nil
      buffer += li
      var j = 0
      while (j < blocks.length) {
        val lj = blocks(j) :: li
        buffer += lj
        var k = 0
        while (k < blocks.length) {
          val lk = blocks(k) :: lj
          buffer += lk
          var l = 0
          while (l < blocks.length) {
            val ll = blocks(l) :: lk
            buffer += ll
            l += 1
          }
          k += 1
        }
        j += 1
      }
      i += 1
    }
    buffer.toArray
  }
<<<<<<< HEAD
=======
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def hashBinaryLists(ls: Array[List[List[Int]]], hash: List[List[Int]] => Unit) {
    var i = 0
    while (i < ls.length) {
      hash(ls(i))
      i += 1
    }
  }

  def justCountSets: Set[Int] => Unit = si => {
    var s = 0
    si.foreach(s += _)
    HashHist.add(s)
  }
<<<<<<< HEAD
  def defaultHashSets: Set[Int] => Unit = si => HashHist.add(si.hashCode)
=======
  
  def defaultHashSets: Set[Int] => Unit = si => HashHist.add(si.hashCode)
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def makeIntSets = {
    def sets(depth: Int): List[Set[Int]] = {
      if (depth <= 0) List(Set.empty[Int])
      else {
<<<<<<< HEAD
        val set = sets(depth-1)
=======
        val set = sets(depth - 1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        set ::: set.map(_ + depth)
      }
    }
    sets(20).toArray
  }
<<<<<<< HEAD
=======
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def hashIntSets(ss: Array[Set[Int]], hash: Set[Int] => Unit) {
    var i = 0
    while (i < ss.length) {
      hash(ss(i))
      i += 1
    }
  }

  def defaultHashTuples: (Product with Serializable) => Unit = p => HashHist.add(p.hashCode)
<<<<<<< HEAD
  def makeNestedTuples = {
    val basic = Array(
      (0,0),
      (0,1),
      (1,0),
      (1,1),
      (0,0,0),
      (0,0,1),
      (0,1,0),
      (1,0,0),
      (0,0,0,0),
      (0,0,0,0,0),
      (false,false),
      (true,false),
      (false,true),
      (true,true),
      (0.7,true,"fish"),
      ((),true,'c',400,9.2,"galactic")
    )
    basic ++
    (for (i <- basic; j <- basic) yield (i,j)) ++
    (for (i <- basic; j <- basic; k <- basic) yield (i,j,k)) ++
    (for (i <- basic; j <- basic; k <- basic) yield ((i,j),k)) ++
    (for (i <- basic; j <- basic; k <- basic) yield (i,(j,k))) ++
    (for (i <- basic; j <- basic; k <- basic; l <- basic) yield (i,j,k,l)) ++
    (for (i <- basic; j <- basic; k <- basic; l <- basic) yield ((i,j),(k,l))) ++
    (for (i <- basic; j <- basic; k <- basic; l <- basic) yield (i,(j,k,l))) ++
    (for (i <- basic; j <- basic; k <- basic; l <- basic; m <- basic) yield (i,j,k,l,m)) ++
    (for (i <- basic; j <- basic; k <- basic; l <- basic; m <- basic) yield (i,(j,(k,(l,m)))))
  }
=======
  
  def makeNestedTuples = {
    val basic = Array(
      (0, 0),
      (0, 1),
      (1, 0),
      (1, 1),
      (0, 0, 0),
      (0, 0, 1),
      (0, 1, 0),
      (1, 0, 0),
      (0, 0, 0, 0),
      (0, 0, 0, 0, 0),
      (false, false),
      (true, false),
      (false, true),
      (true, true),
      (0.7, true, "fish"),
      ((), true, 'c', 400, 9.2, "galactic"))
    basic ++
      (for (i <- basic; j <- basic) yield (i, j)) ++
      (for (i <- basic; j <- basic; k <- basic) yield (i, j, k)) ++
      (for (i <- basic; j <- basic; k <- basic) yield ((i, j), k)) ++
      (for (i <- basic; j <- basic; k <- basic) yield (i, (j, k))) ++
      (for (i <- basic; j <- basic; k <- basic; l <- basic) yield (i, j, k, l)) ++
      (for (i <- basic; j <- basic; k <- basic; l <- basic) yield ((i, j), (k, l))) ++
      (for (i <- basic; j <- basic; k <- basic; l <- basic) yield (i, (j, k, l))) ++
      (for (i <- basic; j <- basic; k <- basic; l <- basic; m <- basic) yield (i, j, k, l, m)) ++
      (for (i <- basic; j <- basic; k <- basic; l <- basic; m <- basic) yield (i, (j, (k, (l, m)))))
  }
  
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def hashNestedTuples(ts: Array[Product with Serializable], hash: (Product with Serializable) => Unit) {
    var i = 0
    while (i < ts.length) {
      hash(ts(i))
      i += 1
    }
  }

<<<<<<< HEAD
  def findSpeed[A](n: Int, h: (Array[A],A=>Unit)=>Unit, aa: Array[A], f: A=>Unit) = {
    (time { for (i <- 1 to n) { h(aa,f) } }._2, aa.length.toLong*n)
  }

  def reportSpeed[A](repeats: Int, xs: List[(String, ()=>(Long,Long))]) {
    val tn = Array.fill(xs.length)((0L,0L))
    for (j <- 1 to repeats) {
      for ((l,i) <- xs zipWithIndex) {
=======
  def findSpeed[A](n: Int, h: (Array[A], A => Unit) => Unit, aa: Array[A], f: A => Unit) = {
    (time { for (i <- 1 to n) { h(aa, f) } }._2, aa.length.toLong * n)
  }

  def reportSpeed[A](repeats: Int, xs: List[(String, () => (Long, Long))]) {
    val tn = Array.fill(xs.length)((0L, 0L))
    for (j <- 1 to repeats) {
      for ((l, i) <- xs zipWithIndex) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val x = l._2()
        tn(i) = (tn(i)._1 + x._1, tn(i)._2 + x._2)
      }
    }
<<<<<<< HEAD
    for (((t,n),(title,_)) <- (tn zip xs)) {
      val rate = (n*1e-6)/(t*1e-9)
      printf("Hash rate for %s: %4.2f million/second\n",title,rate)
=======
    for (((t, n), (title, _)) <- (tn zip xs)) {
      val rate = (n * 1e-6) / (t * 1e-9)
      printf("Hash rate for %s: %4.2f million/second\n", title, rate)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
  }

  def main(args: Array[String]) {
    val bl = makeBinaryLists
    val is = makeIntSets
    val nt = makeNestedTuples
    // Uncomment the following for string stats if MurmurHash3 available
    val cs = makeCharStrings
<<<<<<< HEAD
    report("Java String hash for strings",{ hashCharStrings(cs,defaultHashString); HashHist.resultAndReset })
    report("MurmurHash3 for strings",{ hashCharStrings(cs,murmurHashString); HashHist.resultAndReset })
    HashHist.enabled = false
    reportSpeed(3, List(
       ("Java string hash", () => findSpeed[String](30, (x, y) => hashCharStrings(x, y),cs,defaultHashString)),
       ("MurmurHash3 string hash", () => findSpeed[String](30,(x, y) => hashCharStrings(x, y),cs,murmurHashString))
    ))
    // reportSpeed("Java string hash",30,hashCharStrings.tupled,cs,defaultHashString)
    // reportSpeed("MurmurHash3 string hash",30,hashCharStrings.tupled,cs,murmurHashString)
    HashHist.enabled = true
    report("lists of binary int lists",{ hashBinaryLists(bl,defaultHashList); HashHist.resultAndReset })
    report("small integer sets",{ hashIntSets(is,defaultHashSets); HashHist.resultAndReset })
    report("small nested tuples",{ hashNestedTuples(nt,defaultHashTuples); HashHist.resultAndReset })
    HashHist.enabled = false
    reportSpeed(3,List(
      ("lists of lists of binary ints", () => findSpeed(20,hashBinaryLists,bl,defaultHashList)),
      ("small integer sets", () => findSpeed(10,hashIntSets,is,defaultHashSets)),
      ("small nested tuples", () => findSpeed(5,hashNestedTuples,nt,defaultHashTuples))
    ))
=======
    report("Java String hash for strings", { hashCharStrings(cs, defaultHashString); HashHist.resultAndReset })
    report("MurmurHash3 for strings", { hashCharStrings(cs, murmurHashString); HashHist.resultAndReset })
    HashHist.enabled = false
    reportSpeed(3, List(
      ("Java string hash", () => findSpeed[String](30, (x, y) => hashCharStrings(x, y), cs, defaultHashString)),
      ("MurmurHash3 string hash", () => findSpeed[String](30, (x, y) => hashCharStrings(x, y), cs, murmurHashString))))
    // reportSpeed("Java string hash",30,hashCharStrings.tupled,cs,defaultHashString)
    // reportSpeed("MurmurHash3 string hash",30,hashCharStrings.tupled,cs,murmurHashString)
    HashHist.enabled = true
    report("lists of binary int lists", { hashBinaryLists(bl, defaultHashList); HashHist.resultAndReset })
    report("small integer sets", { hashIntSets(is, defaultHashSets); HashHist.resultAndReset })
    report("small nested tuples", { hashNestedTuples(nt, defaultHashTuples); HashHist.resultAndReset })
    HashHist.enabled = false
    reportSpeed(3, List(
      ("lists of lists of binary ints", () => findSpeed(20, hashBinaryLists, bl, defaultHashList)),
      ("small integer sets", () => findSpeed(10, hashIntSets, is, defaultHashSets)),
      ("small nested tuples", () => findSpeed(5, hashNestedTuples, nt, defaultHashTuples))))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}
