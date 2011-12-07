package scala.tools.partest

trait TestUtil {
  /** Given function and block of code, evaluates code block,
   *  calls function with nanoseconds elapsed, and returns block result.
   */
  def timed[T](f: Long => Unit)(body: => T): T = {
    val start = System.nanoTime
    val result = body
    val end = System.nanoTime
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    f(end - start)
    result
  }
  /** Times body and returns (nanos, result).
   */
  def alsoNanos[T](body: => T): (Long, T) = {
    var nanos = 0L
    val result = timed(nanos = _)(body)
<<<<<<< HEAD
  
    (nanos, result)
  }
  def nanos(body: => Unit): Long = alsoNanos(body)._1
  
=======

    (nanos, result)
  }
  def nanos(body: => Unit): Long = alsoNanos(body)._1

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def verifySpeed(body1: => Unit, body2: => Unit, acceptableMultiple: Double) = {
    val t1 = nanos(body1).toDouble
    val t2 = nanos(body2).toDouble
    val mult = if (t1 > t2) t1 / t2 else t2 / t1
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    assert(mult <= acceptableMultiple, "Performance difference too great: multiple = " + mult)
  }
}

object TestUtil extends TestUtil {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}