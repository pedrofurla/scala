<<<<<<< HEAD
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala

/**
 * Contains the base traits and objects needed to use and extend Scala's collection library.
 *
 * == Guide ==
 *
<<<<<<< HEAD
 * A detailed guide for the collections library is available 
=======
 * A detailed guide for the collections library is available
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * at [[http://www.scala-lang.org/docu/files/collections-api]].
 *
 * == Using Collections ==
 *
<<<<<<< HEAD
 * It is convienient to treat all collections as either 
 * a [[scala.collection.Traversable]] or [[scala.collection.Iterable]], as 
=======
 * It is convienient to treat all collections as either
 * a [[scala.collection.Traversable]] or [[scala.collection.Iterable]], as
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * these traits define the vast majority of operations
 * on a collection.
 *
 * Collections can, of course, be treated as specifically as needed, and
<<<<<<< HEAD
 * the library is designed to ensure that 
=======
 * the library is designed to ensure that
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * the methods that transform collections will return a collection of the same
 * type: {{{
 * scala> val array = Array(1,2,3,4,5,6)
 * array: Array[Int] = Array(1, 2, 3, 4, 5, 6)
<<<<<<< HEAD
 * 
 * scala> array map { _.toString }
 * res0: Array[java.lang.String] = Array(1, 2, 3, 4, 5, 6)
 * 
 * scala> val list = List(1,2,3,4,5,6)
 * list: List[Int] = List(1, 2, 3, 4, 5, 6)
 * 
 * scala> list map { _.toString }
 * res1: List[java.lang.String] = List(1, 2, 3, 4, 5, 6)
 * 
 * }}}
 * 
 * == Creating Collections ==
 *
 * The most common way to create a collection is to use the companion objects as factories.
 * Of these, the three most common 
 * are [[scala.collection.immutable.Seq]], [[scala.collection.immutable.Set]], and [[scala.collection.immutable.Map]].  Their 
=======
 *
 * scala> array map { _.toString }
 * res0: Array[java.lang.String] = Array(1, 2, 3, 4, 5, 6)
 *
 * scala> val list = List(1,2,3,4,5,6)
 * list: List[Int] = List(1, 2, 3, 4, 5, 6)
 *
 * scala> list map { _.toString }
 * res1: List[java.lang.String] = List(1, 2, 3, 4, 5, 6)
 *
 * }}}
 *
 * == Creating Collections ==
 *
 * The most common way to create a collection is to use the companion objects as factories.
 * Of these, the three most common
 * are [[scala.collection.immutable.Seq]], [[scala.collection.immutable.Set]], and [[scala.collection.immutable.Map]].  Their
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * companion objects are all available
 * as type aliases the either the [[scala]] package or in `scala.Predef`, and can be used
 * like so:
 * {{{
 * scala> val seq = Seq(1,2,3,4,1)
 * seq: Seq[Int] = List(1, 2, 3, 4, 1)
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * scala> val set = Set(1,2,3,4,1)
 * set: scala.collection.immutable.Set[Int] = Set(1, 2, 3, 4)
 *
 * scala> val map = Map(1 -> "one",2 -> "two", 3 -> "three",2 -> "too")
 * map: scala.collection.immutable.Map[Int,java.lang.String] = Map((1,one), (2,too), (3,three))
 * }}}
 *
 * It is also typical to use the [[scala.collection.immutable]] collections over those
 * in [[scala.collection.mutable]]; The types aliased in the [[scala]] package and
 * the `scala.Predef` object are the immutable versions.
 *
 * Also note that the collections library was carefully designed to include several implementations of
 * each of the three basic collection types. These implementations have specific performance
<<<<<<< HEAD
 * characteristics which are described 
=======
 * characteristics which are described
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * in [[http://www.scala-lang.org/docu/files/collections-api the guide]].
 *
 * === Converting between Java Collections ===
 *
 * The `JavaConversions` object provides implicit defs that will allow mostly seamless integration
 * between Java Collections-based APIs and the Scala collections library.
 *
 */
package object collection {
  import scala.collection.generic.CanBuildFrom
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Provides a CanBuildFrom instance that builds a specific target collection (`To')
   *  irrespective of the original collection (`From').
   */
  def breakOut[From, T, To](implicit b: CanBuildFrom[Nothing, T, To]): CanBuildFrom[From, T, To] =
    // can't just return b because the argument to apply could be cast to From in b
    new CanBuildFrom[From, T, To] {
      def apply(from: From) = b.apply()
      def apply()           = b.apply()
    }
}

package collection {
  /** Collection internal utility functions.
   */
  private[collection] object DebugUtils {
    def unsupported(msg: String)     = throw new UnsupportedOperationException(msg)
    def noSuchElement(msg: String)   = throw new NoSuchElementException(msg)
    def indexOutOfBounds(index: Int) = throw new IndexOutOfBoundsException(index.toString)
    def illegalArgument(msg: String) = throw new IllegalArgumentException(msg)
<<<<<<< HEAD
  
    def buildString(closure: (Any => Unit) => Unit): String = {
      var output = ""
      closure(output += _ + "\n")
    
      output
    }
  
=======

    def buildString(closure: (Any => Unit) => Unit): String = {
      var output = ""
      closure(output += _ + "\n")

      output
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def arrayString[T](array: Array[T], from: Int, until: Int): String = {
      array.slice(from, until) map {
        case null => "n/a"
        case x    => "" + x
      } mkString " | "
    }
  }
}
