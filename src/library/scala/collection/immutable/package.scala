<<<<<<< HEAD
package scala.collection









package object immutable {
  
  trait RangeUtils[+Repr <: RangeUtils[Repr]] {
    
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

package immutable {
  /** It looks like once upon a time this was used by ParRange, but
   *  since December 2010 in r23721 it is not used by anything.  We
   *  should not have public API traits with seductive names like
   *  "RangeUtils" which are neither documented nor used.
   */
  @deprecated("this class will be removed", "2.10.0")
  trait RangeUtils[+Repr <: RangeUtils[Repr]] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def start: Int
    def end: Int
    def step: Int
    def inclusive: Boolean
    def create(_start: Int, _end: Int, _step: Int, _inclusive: Boolean): Repr
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private final def inclusiveLast: Int = {
      val size = end.toLong - start.toLong
      (size / step.toLong * step.toLong + start.toLong).toInt
    }
<<<<<<< HEAD
    
    final def _last: Int = if (!inclusive) {
      if (step == 1 || step == -1) end - step
      else {
        val inclast = inclusiveLast
        if ((end.toLong - start.toLong) % step == 0) inclast - step else inclast
      }
    } else {
      if (step == 1 || step == -1) end
      else inclusiveLast
    }
    
=======

    final def _last: Int = (
      if (!inclusive) {
        if (step == 1 || step == -1) end - step
        else {
          val inclast = inclusiveLast
          if ((end.toLong - start.toLong) % step == 0) inclast - step else inclast
        }
      }
      else if (step == 1 || step == -1) end
      else inclusiveLast
    )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    final def _foreach[U](f: Int => U) = if (_length > 0) {
      var i = start
      val last = _last
      while (i != last) {
        f(i)
        i += step
      }
    }
<<<<<<< HEAD
    
    final def _length: Int = if (!inclusive) {
      if (end > start == step > 0 && start != end) {
        (_last.toLong - start.toLong) / step.toLong + 1
      } else 0
    }.toInt else {
      if (end > start == step > 0 || start == end) {
        (_last.toLong - start.toLong) / step.toLong + 1
      } else 0
    }.toInt
    
=======

    final def _length: Int = (
      if (!inclusive) {
        if (end > start == step > 0 && start != end) {
          (_last.toLong - start.toLong) / step.toLong + 1
        } else 0
      }.toInt
      else {
        if (end > start == step > 0 || start == end) {
          (_last.toLong - start.toLong) / step.toLong + 1
        } else 0
      }.toInt
    )

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    final def _apply(idx: Int): Int = {
      if (idx < 0 || idx >= _length) throw new IndexOutOfBoundsException(idx.toString)
      start + idx * step
    }
<<<<<<< HEAD
    
    private def locationAfterN(n: Int) = if (n > 0) {
      if (step > 0) ((start.toLong + step.toLong * n.toLong) min _last.toLong).toInt
      else ((start.toLong + step.toLong * n.toLong) max _last.toLong).toInt
    } else start
    
    final def _take(n: Int) = if (n > 0 && _length > 0) {
      create(start, locationAfterN(n), step, true)
    } else create(start, start, step, false)
    
    final def _drop(n: Int) = create(locationAfterN(n), end, step, inclusive)
    
    final def _slice(from: Int, until: Int) = _drop(from)._take(until - from)
    
  }
  
}





=======

    private def locationAfterN(n: Int) = (
      if (n > 0) {
        if (step > 0)
          math.min(start.toLong + step.toLong * n.toLong, _last.toLong).toInt
        else
          math.max(start.toLong + step.toLong * n.toLong, _last.toLong).toInt
      }
      else start
    )

    final def _take(n: Int) = (
      if (n > 0 && _length > 0)
        create(start, locationAfterN(n), step, true)
      else
        create(start, start, step, false)
    )

    final def _drop(n: Int)                 = create(locationAfterN(n), end, step, inclusive)
    final def _slice(from: Int, until: Int) = _drop(from)._take(until - from)
  }
}

package object immutable {
  /** Nothing left after I promoted RangeUtils to the package. */
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
