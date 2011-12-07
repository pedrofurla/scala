/** Test the Scala implementation of classes <code>scala.BigInt</code>
* and <code>scala.BigDecimal</code>.
*
*  @author Stephane Micheloud
*/
object Test {
  def main(args: Array[String]) {
    Test_BigInt.runTest()
    Test_BigDecimal.runTest()
  }
}

object Test_BigInt {
  def runTest() {
    import BigInt._

    val x: BigInt = 1
    val y = x + 1
    val z = 1 + y
    println("int_add_bigint = " + (1+y, y+1))
    println("int_sub_bigint = " + (1-y,-(y-1)))
    println("int_mul_bigint = " + (2*x*y, y*x*2))
    println("z <= 3 = " + (z <= 3))
    println("3 < z = " + (3 < z))
  }
}

object Test_BigDecimal {
  def runTest() {
    import scala.BigDecimal, BigDecimal._

    val xi: BigDecimal = 1
    val xd: BigDecimal = 1.0
    val xf: BigDecimal = BigDecimal(1.0f)
    val xs: BigDecimal = BigDecimal("1.0")
    val xbi: BigDecimal = BigDecimal(scala.BigInt(1))

    val x: BigDecimal = 1
    val y = x + 1
    val z = 1 + y
    println("z <= 3 = " + (z <= 3))
    println("3 < z = " + (3 < z))

<<<<<<< HEAD
    val a: BigDecimal= Math.MAX_LONG
    val b: BigDecimal = 1
    val c = a - b
    println("c > MAX_LONG = " + (c > Math.MAX_LONG))
    println("c <= MAX_LONG = " + (c <= Math.MAX_LONG))
=======
    val a: BigDecimal= Long.MaxValue
    val b: BigDecimal = 1
    val c = a - b
    println("c > MAX_LONG = " + (c > Long.MaxValue))
    println("c <= MAX_LONG = " + (c <= Long.MaxValue))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

