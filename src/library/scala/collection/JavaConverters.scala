/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://www.scala-lang.org/           **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection

/** A collection of decorators that allow to convert between
 *  Scala and Java collections using `asScala` and `asJava` methods.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  The following conversions are supported via `asJava`, `asScala`
 *
 *  - `scala.collection.Iterable` <=> `java.lang.Iterable`
 *  - `scala.collection.Iterator` <=> `java.util.Iterator`
 *  - `scala.collection.mutable.Buffer` <=> `java.util.List`
 *  - `scala.collection.mutable.Set` <=> `java.util.Set`
 *  - `scala.collection.mutable.Map` <=> `java.util.Map`
 *  - `scala.collection.mutable.ConcurrentMap` <=> `java.util.concurrent.ConcurrentMap`
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  In all cases, converting from a source type to a target type and back
 *  again will return the original source object, e.g.
 *  {{{
 *    import scala.collection.JavaConverters._
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *    val sl = new scala.collection.mutable.ListBuffer[Int]
 *    val jl : java.util.List[Int] = sl.asJava
 *    val sl2 : scala.collection.mutable.Buffer[Int] = jl.asScala
 *    assert(sl eq sl2)
 *  }}}
 *  The following conversions also are supported, but the
 *  direction Scala to Java is done my a more specifically named method:
 *  `asJavaCollection`, `asJavaEnumeration`, `asJavaDictionary`.
<<<<<<< HEAD
 *  
 *  - `scala.collection.Iterable` <=> `java.util.Collection`
 *  - `scala.collection.Iterator` <=> `java.util.Enumeration`
 *  - `scala.collection.mutable.Map` <=> `java.util.Dictionary`
 *  
 *  In addition, the following one way conversions are provided via `asJava`:
 *  
=======
 *
 *  - `scala.collection.Iterable` <=> `java.util.Collection`
 *  - `scala.collection.Iterator` <=> `java.util.Enumeration`
 *  - `scala.collection.mutable.Map` <=> `java.util.Dictionary`
 *
 *  In addition, the following one way conversions are provided via `asJava`:
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  - `scala.collection.Seq` => `java.util.List`
 *  - `scala.collection.mutable.Seq` => `java.util.List`
 *  - `scala.collection.Set` => `java.util.Set`
 *  - `scala.collection.Map` => `java.util.Map`
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author Martin Odersky
 *  @since  2.8.1
 */
object JavaConverters {
  import java.{ lang => jl, util => ju }
  import java.util.{ concurrent => juc }
  import JavaConversions._

  // TODO: I cleaned all this documentation up in JavaConversions, but the
  // documentation in here is basically the pre-cleaned-up version with minor
  // additions.  Would be nice to have in one place.

  // Conversion decorator classes

  /** Generic class containing the `asJava` converter method */
  class AsJava[C](op: => C) {
    /** Converts a Scala collection to the corresponding Java collection */
    def asJava: C = op
  }

  /** Generic class containing the `asScala` converter method */
  class AsScala[C](op: => C) {
    /** Converts a Java collection to the corresponding Scala collection */
    def asScala: C = op
  }

  /** Generic class containing the `asJavaCollection` converter method */
  class AsJavaCollection[A](i: Iterable[A]) {
    /** Converts a Scala `Iterable` to a Java `Collection` */
    def asJavaCollection: ju.Collection[A] = JavaConversions.asJavaCollection(i)
  }

  /** Generic class containing the `asJavaEnumeration` converter method */
  class AsJavaEnumeration[A](i: Iterator[A]) {
    /** Converts a Scala `Iterator` to a Java `Enumeration` */
    def asJavaEnumeration: ju.Enumeration[A] = JavaConversions.asJavaEnumeration(i)
  }

  /** Generic class containing the `asJavaDictionary` converter method */
  class AsJavaDictionary[A, B](m : mutable.Map[A, B]) {
    /** Converts a Scala `Map` to a Java `Dictionary` */
    def asJavaDictionary: ju.Dictionary[A, B] = JavaConversions.asJavaDictionary(m)
  }

  // Scala => Java

  /**
   * Adds an `asJava` method that implicitly converts a Scala `Iterator` to a
   * Java `Iterator`. The returned Java `Iterator` is backed by the provided Scala
   * `Iterator` and any side-effects of using it via the Java interface will
   * be visible via the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Iterator` was previously obtained from an implicit or explicit
   * call of `asIterator(java.util.Iterator)` then the original Java `Iterator`
   * will be returned by the `asJava` method.
   * 
   * @param i The `Iterator` to be converted.
   * @return An object with an `asJava` method that returns a Java `Iterator` view of the argument. 
   */
  implicit def asJavaIteratorConverter[A](i : Iterator[A]): AsJava[ju.Iterator[A]] = 
=======
   *
   * If the Scala `Iterator` was previously obtained from an implicit or explicit
   * call of `asIterator(java.util.Iterator)` then the original Java `Iterator`
   * will be returned by the `asJava` method.
   *
   * @param i The `Iterator` to be converted.
   * @return An object with an `asJava` method that returns a Java `Iterator` view of the argument.
   */
  implicit def asJavaIteratorConverter[A](i : Iterator[A]): AsJava[ju.Iterator[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJava(asJavaIterator(i))

  /**
   * Adds an `asJavaEnumeration` method that implicitly converts a Scala
   * `Iterator` to a Java `Enumeration`. The returned Java `Enumeration` is
   * backed by the provided Scala `Iterator` and any side-effects of using
   * it via the Java interface will be visible via the Scala interface and
   * vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Iterator` was previously obtained from an implicit or
   * explicit call of `asIterator(java.util.Enumeration)` then the
   * original Java `Enumeration` will be returned.
   * 
=======
   *
   * If the Scala `Iterator` was previously obtained from an implicit or
   * explicit call of `asIterator(java.util.Enumeration)` then the
   * original Java `Enumeration` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Iterator` to be converted.
   * @return An object with an `asJavaEnumeration` method that returns a Java
   *         `Enumeration` view of the argument.
   */
<<<<<<< HEAD
  implicit def asJavaEnumerationConverter[A](i : Iterator[A]): AsJavaEnumeration[A] = 
=======
  implicit def asJavaEnumerationConverter[A](i : Iterator[A]): AsJavaEnumeration[A] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJavaEnumeration(i)

  /**
   * Adds an `asJava` method that implicitly converts a Scala `Iterable` to
   * a Java `Iterable`.
   *
   * The returned Java `Iterable` is backed by the provided Scala `Iterable`
   * and any side-effects of using it via the Java interface will be visible
   * via the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Iterable` was previously obtained from an implicit or
   * explicit call of `asIterable(java.lang.Iterable)` then the original
   * Java `Iterable` will be returned.
   * 
=======
   *
   * If the Scala `Iterable` was previously obtained from an implicit or
   * explicit call of `asIterable(java.lang.Iterable)` then the original
   * Java `Iterable` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Iterable` to be converted.
   * @return An object with an `asJavaCollection` method that returns a Java
   *         `Iterable` view of the argument.
   */
<<<<<<< HEAD
  implicit def asJavaIterableConverter[A](i : Iterable[A]): AsJava[jl.Iterable[A]] = 
=======
  implicit def asJavaIterableConverter[A](i : Iterable[A]): AsJava[jl.Iterable[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJava(asJavaIterable(i))

  /**
   * Adds an `asJavaCollection` method that implicitly converts a Scala
   * `Iterable` to an immutable Java `Collection`.
<<<<<<< HEAD
   * 
   * If the Scala `Iterable` was previously obtained from an implicit or
   * explicit call of `asSizedIterable(java.util.Collection)` then the
   * original Java `Collection` will be returned.
   * 
=======
   *
   * If the Scala `Iterable` was previously obtained from an implicit or
   * explicit call of `asSizedIterable(java.util.Collection)` then the
   * original Java `Collection` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `SizedIterable` to be converted.
   * @return An object with an `asJava` method that returns a Java
   *         `Collection` view of the argument.
   */
<<<<<<< HEAD
  implicit def asJavaCollectionConverter[A](i : Iterable[A]): AsJavaCollection[A] = 
=======
  implicit def asJavaCollectionConverter[A](i : Iterable[A]): AsJavaCollection[A] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJavaCollection(i)

  /**
   * Adds an `asJava` method that implicitly converts a Scala mutable `Buffer`
   * to a Java `List`.
   *
   * The returned Java `List` is backed by the provided Scala `Buffer` and any
   * side-effects of using it via the Java interface will be visible via the
   * Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Buffer` was previously obtained from an implicit or explicit
   * call of `asBuffer(java.util.List)` then the original Java `List` will be
   * returned.
   * 
=======
   *
   * If the Scala `Buffer` was previously obtained from an implicit or explicit
   * call of `asBuffer(java.util.List)` then the original Java `List` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param b The `Buffer` to be converted.
   * @return An object with an `asJava` method that returns a Java `List` view
   *         of the argument.
   */
<<<<<<< HEAD
  implicit def bufferAsJavaListConverter[A](b : mutable.Buffer[A]): AsJava[ju.List[A]] = 
    new AsJava(bufferAsJavaList(b))
    
=======
  implicit def bufferAsJavaListConverter[A](b : mutable.Buffer[A]): AsJava[ju.List[A]] =
    new AsJava(bufferAsJavaList(b))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * Adds an `asJava` method that implicitly converts a Scala mutable `Seq`
   * to a Java `List`.
   *
   * The returned Java `List` is backed by the provided Scala `Seq` and any
   * side-effects of using it via the Java interface will be visible via the
   * Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Seq` was previously obtained from an implicit or explicit
   * call of `asSeq(java.util.List)` then the original Java `List` will be
   * returned.
   * 
=======
   *
   * If the Scala `Seq` was previously obtained from an implicit or explicit
   * call of `asSeq(java.util.List)` then the original Java `List` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param b The `Seq` to be converted.
   * @return An object with an `asJava` method that returns a Java `List`
   *         view of the argument.
   */
<<<<<<< HEAD
  implicit def mutableSeqAsJavaListConverter[A](b : mutable.Seq[A]): AsJava[ju.List[A]] = 
    new AsJava(mutableSeqAsJavaList(b))
    
=======
  implicit def mutableSeqAsJavaListConverter[A](b : mutable.Seq[A]): AsJava[ju.List[A]] =
    new AsJava(mutableSeqAsJavaList(b))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * Adds an `asJava` method that implicitly converts a Scala `Seq` to a
   * Java `List`.
   *
   * The returned Java `List` is backed by the provided Scala `Seq` and any
   * side-effects of using it via the Java interface will be visible via the
   * Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Seq` was previously obtained from an implicit or explicit
   * call of `asSeq(java.util.List)` then the original Java `List` will be
   * returned.
   * 
=======
   *
   * If the Scala `Seq` was previously obtained from an implicit or explicit
   * call of `asSeq(java.util.List)` then the original Java `List` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param b The `Seq` to be converted.
   * @return An object with an `asJava` method that returns a Java `List`
   *         view of the argument.
   */
<<<<<<< HEAD
  implicit def seqAsJavaListConverter[A](b : Seq[A]): AsJava[ju.List[A]] = 
    new AsJava(seqAsJavaList(b))
    
=======
  implicit def seqAsJavaListConverter[A](b : Seq[A]): AsJava[ju.List[A]] =
    new AsJava(seqAsJavaList(b))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @deprecated("Use bufferAsJavaListConverter instead", "2.9.0")
  def asJavaListConverter[A](b : mutable.Buffer[A]): AsJava[ju.List[A]] = bufferAsJavaListConverter(b)
  @deprecated("Use mutableSeqAsJavaListConverter instead", "2.9.0")
  def asJavaListConverter[A](b : mutable.Seq[A]): AsJava[ju.List[A]] = mutableSeqAsJavaListConverter(b)
  @deprecated("Use seqAsJavaListConverter instead", "2.9.0")
  def asJavaListConverter[A](b : Seq[A]): AsJava[ju.List[A]] = seqAsJavaListConverter(b)

  /**
   * Adds an `asJava` method that implicitly converts a Scala mutable `Set`>
   * to a Java `Set`.
   *
   * The returned Java `Set` is backed by the provided Scala `Set` and any
   * side-effects of using it via the Java interface will be visible via
   * the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Set` was previously obtained from an implicit or explicit
   * call of `asSet(java.util.Set)` then the original Java `Set` will be
   * returned.
   * 
=======
   *
   * If the Scala `Set` was previously obtained from an implicit or explicit
   * call of `asSet(java.util.Set)` then the original Java `Set` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param s The `Set` to be converted.
   * @return An object with an `asJava` method that returns a Java `Set` view
   *         of the argument.
   */
  implicit def mutableSetAsJavaSetConverter[A](s : mutable.Set[A]): AsJava[ju.Set[A]] =
    new AsJava(mutableSetAsJavaSet(s))

  @deprecated("Use mutableSetAsJavaSetConverter instead", "2.9.0")
  def asJavaSetConverter[A](s : mutable.Set[A]): AsJava[ju.Set[A]] = mutableSetAsJavaSetConverter(s)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /**
   * Adds an `asJava` method that implicitly converts a Scala `Set` to a
   * Java `Set`.
   *
   * The returned Java `Set` is backed by the provided Scala `Set` and any
   * side-effects of using it via the Java interface will be visible via
   * the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Set` was previously obtained from an implicit or explicit
   * call of `asSet(java.util.Set)` then the original Java `Set` will be
   * returned.
   * 
=======
   *
   * If the Scala `Set` was previously obtained from an implicit or explicit
   * call of `asSet(java.util.Set)` then the original Java `Set` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param s The `Set` to be converted.
   * @return An object with an `asJava` method that returns a Java `Set` view
   *         of the argument.
   */
<<<<<<< HEAD
  implicit def setAsJavaSetConverter[A](s : Set[A]): AsJava[ju.Set[A]] = 
=======
  implicit def setAsJavaSetConverter[A](s : Set[A]): AsJava[ju.Set[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJava(setAsJavaSet(s))

  @deprecated("Use setAsJavaSetConverter instead", "2.9.0")
  def asJavaSetConverter[A](s : Set[A]): AsJava[ju.Set[A]] = setAsJavaSetConverter(s)

  /**
   * Adds an `asJava` method that implicitly converts a Scala mutable `Map`
   * to a Java `Map`.
   *
   * The returned Java `Map` is backed by the provided Scala `Map` and any
   * side-effects of using it via the Java interface will be visible via the
   * Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Map` was previously obtained from an implicit or explicit
   * call of `asMap(java.util.Map)` then the original Java `Map` will be
   * returned.
   * 
=======
   *
   * If the Scala `Map` was previously obtained from an implicit or explicit
   * call of `asMap(java.util.Map)` then the original Java `Map` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Map` to be converted.
   * @return An object with an `asJava` method that returns a Java `Map` view
   *         of the argument.
   */
<<<<<<< HEAD
  implicit def mutableMapAsJavaMapConverter[A, B](m : mutable.Map[A, B]): AsJava[ju.Map[A, B]] = 
    new AsJava(mutableMapAsJavaMap(m))
    
=======
  implicit def mutableMapAsJavaMapConverter[A, B](m : mutable.Map[A, B]): AsJava[ju.Map[A, B]] =
    new AsJava(mutableMapAsJavaMap(m))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @deprecated("use mutableMapAsJavaMapConverter instead", "2.9.0")
  def asJavaMapConverter[A, B](m : mutable.Map[A, B]): AsJava[ju.Map[A, B]] = mutableMapAsJavaMapConverter(m)

  /**
   * Adds an `asJavaDictionary` method that implicitly converts a Scala
   * mutable `Map` to a Java `Dictionary`.
   *
   * The returned Java `Dictionary` is backed by the provided Scala
   * `Dictionary` and any side-effects of using it via the Java interface
   * will be visible via the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Dictionary` was previously obtained from an implicit or
   * explicit call of `asMap(java.util.Dictionary)` then the original
   * Java `Dictionary` will be returned.
   * 
=======
   *
   * If the Scala `Dictionary` was previously obtained from an implicit or
   * explicit call of `asMap(java.util.Dictionary)` then the original
   * Java `Dictionary` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Map` to be converted.
   * @return An object with an `asJavaDictionary` method that returns a
   *         Java `Dictionary` view of the argument.
   */
<<<<<<< HEAD
  implicit def asJavaDictionaryConverter[A, B](m : mutable.Map[A, B]): AsJavaDictionary[A, B] = 
=======
  implicit def asJavaDictionaryConverter[A, B](m : mutable.Map[A, B]): AsJavaDictionary[A, B] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJavaDictionary(m)

  /**
   * Adds an `asJava` method that implicitly converts a Scala `Map` to
   * a Java `Map`.
   *
   * The returned Java `Map` is backed by the provided Scala `Map` and any
   * side-effects of using it via the Java interface will be visible via
   * the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `Map` was previously obtained from an implicit or explicit
   * call of `asMap(java.util.Map)` then the original Java `Map` will be
   * returned.
   * 
=======
   *
   * If the Scala `Map` was previously obtained from an implicit or explicit
   * call of `asMap(java.util.Map)` then the original Java `Map` will be
   * returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Map` to be converted.
   * @return An object with an `asJava` method that returns a Java `Map` view
   *         of the argument.
   */
<<<<<<< HEAD
  implicit def mapAsJavaMapConverter[A, B](m : Map[A, B]): AsJava[ju.Map[A, B]] = 
    new AsJava(mapAsJavaMap(m))
  
=======
  implicit def mapAsJavaMapConverter[A, B](m : Map[A, B]): AsJava[ju.Map[A, B]] =
    new AsJava(mapAsJavaMap(m))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @deprecated("Use mapAsJavaMapConverter instead", "2.9.0")
  def asJavaMapConverter[A, B](m : Map[A, B]): AsJava[ju.Map[A, B]] = mapAsJavaMapConverter(m)

  /**
   * Adds an `asJava` method that implicitly converts a Scala mutable
   * `ConcurrentMap` to a Java `ConcurrentMap`.
   *
   * The returned Java `ConcurrentMap` is backed by the provided Scala
   * `ConcurrentMap` and any side-effects of using it via the Java interface
   * will be visible via the Scala interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Scala `ConcurrentMap` was previously obtained from an implicit or
   * explicit call of `asConcurrentMap(java.util.concurrect.ConcurrentMap)`
   * then the original Java `ConcurrentMap` will be returned.
   * 
=======
   *
   * If the Scala `ConcurrentMap` was previously obtained from an implicit or
   * explicit call of `asConcurrentMap(java.util.concurrect.ConcurrentMap)`
   * then the original Java `ConcurrentMap` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `ConcurrentMap` to be converted.
   * @return An object with an `asJava` method that returns a Java
   *         `ConcurrentMap` view of the argument.
   */
<<<<<<< HEAD
  implicit def asJavaConcurrentMapConverter[A, B](m: mutable.ConcurrentMap[A, B]): AsJava[juc.ConcurrentMap[A, B]] = 
=======
  implicit def asJavaConcurrentMapConverter[A, B](m: mutable.ConcurrentMap[A, B]): AsJava[juc.ConcurrentMap[A, B]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsJava(asJavaConcurrentMap(m))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Iterator` to
   * a Scala `Iterator`.
   *
   * The returned Scala `Iterator` is backed by the provided Java `Iterator`
   * and any side-effects of using it via the Scala interface will be visible
   * via the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `Iterator` was previously obtained from an implicit or
   * explicit call of `asIterator(scala.collection.Iterator)` then the
   * original Scala `Iterator` will be returned.
   * 
=======
   *
   * If the Java `Iterator` was previously obtained from an implicit or
   * explicit call of `asIterator(scala.collection.Iterator)` then the
   * original Scala `Iterator` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Iterator` to be converted.
   * @return An object with an `asScala` method that returns a Scala
   *         `Iterator` view of the argument.
   */
<<<<<<< HEAD
  implicit def asScalaIteratorConverter[A](i : ju.Iterator[A]): AsScala[Iterator[A]] = 
=======
  implicit def asScalaIteratorConverter[A](i : ju.Iterator[A]): AsScala[Iterator[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(asScalaIterator(i))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Enumeration`
   * to a Scala `Iterator`.
   *
   * The returned Scala `Iterator` is backed by the provided Java
   * `Enumeration` and any side-effects of using it via the Scala interface
   * will be visible via the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `Enumeration` was previously obtained from an implicit or
   * explicit call of `asEnumeration(scala.collection.Iterator)` then the
   * original Scala `Iterator` will be returned.
   * 
=======
   *
   * If the Java `Enumeration` was previously obtained from an implicit or
   * explicit call of `asEnumeration(scala.collection.Iterator)` then the
   * original Scala `Iterator` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Enumeration` to be converted.
   * @return An object with an `asScala` method that returns a Scala
   *         `Iterator` view of the argument.
   */
<<<<<<< HEAD
  implicit def enumerationAsScalaIteratorConverter[A](i : ju.Enumeration[A]): AsScala[Iterator[A]] = 
=======
  implicit def enumerationAsScalaIteratorConverter[A](i : ju.Enumeration[A]): AsScala[Iterator[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(enumerationAsScalaIterator(i))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Iterable` to
   * a Scala `Iterable`.
   *
   * The returned Scala `Iterable` is backed by the provided Java `Iterable`
   * and any side-effects of using it via the Scala interface will be visible
   * via the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `Iterable` was previously obtained from an implicit or
   * explicit call of `asIterable(scala.collection.Iterable)` then the original
   * Scala `Iterable` will be returned.
   * 
=======
   *
   * If the Java `Iterable` was previously obtained from an implicit or
   * explicit call of `asIterable(scala.collection.Iterable)` then the original
   * Scala `Iterable` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Iterable` to be converted.
   * @return An object with an `asScala` method that returns a Scala `Iterable`
   *         view of the argument.
   */
<<<<<<< HEAD
  implicit def iterableAsScalaIterableConverter[A](i : jl.Iterable[A]): AsScala[Iterable[A]] = 
=======
  implicit def iterableAsScalaIterableConverter[A](i : jl.Iterable[A]): AsScala[Iterable[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(iterableAsScalaIterable(i))

  @deprecated("Use iterableAsScalaIterableConverter instead", "2.9.0")
  def asScalaIterableConverter[A](i : jl.Iterable[A]): AsScala[Iterable[A]] = iterableAsScalaIterableConverter(i)

  /**
   * Adds an `asScala` method that implicitly converts a Java `Collection` to
   * an Scala `Iterable`.
<<<<<<< HEAD
   * 
   * If the Java `Collection` was previously obtained from an implicit or
   * explicit call of `asCollection(scala.collection.SizedIterable)` then
   * the original Scala `SizedIterable` will be returned.
   * 
=======
   *
   * If the Java `Collection` was previously obtained from an implicit or
   * explicit call of `asCollection(scala.collection.SizedIterable)` then
   * the original Scala `SizedIterable` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param i The `Collection` to be converted.
   * @return An object with an `asScala` method that returns a Scala
   *        `SizedIterable` view of the argument.
   */
<<<<<<< HEAD
  implicit def collectionAsScalaIterableConverter[A](i : ju.Collection[A]): AsScala[Iterable[A]] = 
=======
  implicit def collectionAsScalaIterableConverter[A](i : ju.Collection[A]): AsScala[Iterable[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(collectionAsScalaIterable(i))

  @deprecated("Use collectionAsScalaIterableConverter instead", "2.9.0")
  def asScalaIterableConverter[A](i : ju.Collection[A]): AsScala[Iterable[A]] = collectionAsScalaIterableConverter(i)

  /**
   * Adds an `asScala` method that implicitly converts a Java `List` to a
   * Scala mutable `Buffer`.
   *
   * The returned Scala `Buffer` is backed by the provided Java `List` and
   * any side-effects of using it via the Scala interface will be visible via
   * the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `List` was previously obtained from an implicit or explicit
   * call of `asList(scala.collection.mutable.Buffer)` then the original
   * Scala `Buffer` will be returned.
   * 
=======
   *
   * If the Java `List` was previously obtained from an implicit or explicit
   * call of `asList(scala.collection.mutable.Buffer)` then the original
   * Scala `Buffer` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param l The `List` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *        `Buffer` view of the argument.
   */
<<<<<<< HEAD
  implicit def asScalaBufferConverter[A](l : ju.List[A]): AsScala[mutable.Buffer[A]] = 
=======
  implicit def asScalaBufferConverter[A](l : ju.List[A]): AsScala[mutable.Buffer[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(asScalaBuffer(l))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Set` to a
   * Scala mutable `Set`.
   *
   * The returned Scala `Set` is backed by the provided Java `Set` and any
   * side-effects of using it via the Scala interface will be visible via
   * the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `Set` was previously obtained from an implicit or explicit
   * call of `asSet(scala.collection.mutable.Set)` then the original
   * Scala `Set` will be returned.
   * 
=======
   *
   * If the Java `Set` was previously obtained from an implicit or explicit
   * call of `asSet(scala.collection.mutable.Set)` then the original
   * Scala `Set` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param s The `Set` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *         `Set` view of the argument.
   */
<<<<<<< HEAD
  implicit def asScalaSetConverter[A](s : ju.Set[A]): AsScala[mutable.Set[A]] = 
=======
  implicit def asScalaSetConverter[A](s : ju.Set[A]): AsScala[mutable.Set[A]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(asScalaSet(s))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Map` to a Scala
   * mutable `Map`. The returned Scala `Map` is backed by the provided Java
   * `Map` and any side-effects of using it via the Scala interface will
   * be visible via the Java interface and vice versa.
<<<<<<< HEAD
   * 
   * If the Java `Map` was previously obtained from an implicit or explicit
   * call of `asMap(scala.collection.mutable.Map)` then the original
   * Scala `Map` will be returned.
   * 
=======
   *
   * If the Java `Map` was previously obtained from an implicit or explicit
   * call of `asMap(scala.collection.mutable.Map)` then the original
   * Scala `Map` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Map` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *         `Map` view of the argument.
   */
<<<<<<< HEAD
  implicit def mapAsScalaMapConverter[A, B](m : ju.Map[A, B]): AsScala[mutable.Map[A, B]] = 
=======
  implicit def mapAsScalaMapConverter[A, B](m : ju.Map[A, B]): AsScala[mutable.Map[A, B]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(mapAsScalaMap(m))

  @deprecated("Use mapAsScalaMapConverter instead", "2.9.0")
  def asScalaMapConverter[A, B](m : ju.Map[A, B]): AsScala[mutable.Map[A, B]] = mapAsScalaMapConverter(m)

  /**
   * Adds an `asScala` method that implicitly converts a Java `ConcurrentMap`
   * to a Scala mutable `ConcurrentMap`. The returned Scala `ConcurrentMap` is
   * backed by the provided Java `ConcurrentMap` and any side-effects of using
   * it via the Scala interface will be visible via the Java interface and
   * vice versa.
<<<<<<< HEAD
   * 
   * If the Java `ConcurrentMap` was previously obtained from an implicit or
   * explicit call of `asConcurrentMap(scala.collection.mutable.ConcurrentMap)`
   * then the original Scala `ConcurrentMap` will be returned.
   * 
=======
   *
   * If the Java `ConcurrentMap` was previously obtained from an implicit or
   * explicit call of `asConcurrentMap(scala.collection.mutable.ConcurrentMap)`
   * then the original Scala `ConcurrentMap` will be returned.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `ConcurrentMap` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *         `ConcurrentMap` view of the argument.
   */
<<<<<<< HEAD
  implicit def asScalaConcurrentMapConverter[A, B](m: juc.ConcurrentMap[A, B]): AsScala[mutable.ConcurrentMap[A, B]] = 
=======
  implicit def asScalaConcurrentMapConverter[A, B](m: juc.ConcurrentMap[A, B]): AsScala[mutable.ConcurrentMap[A, B]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(asScalaConcurrentMap(m))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Dictionary`
   * to a Scala mutable `Map[String, String]`. The returned Scala
   * `Map[String, String]` is backed by the provided Java `Dictionary` and
   * any side-effects of using it via the Scala interface will be visible via
   * the Java interface and vice versa.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Dictionary` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *         `Map[String, String]` view of the argument.
   */
  implicit def dictionaryAsScalaMapConverter[A, B](p: ju.Dictionary[A, B]): AsScala[mutable.Map[A, B]] =
    new AsScala(dictionaryAsScalaMap(p))

  /**
   * Adds an `asScala` method that implicitly converts a Java `Properties`
   * to a Scala mutable `Map[String, String]`. The returned Scala
   * `Map[String, String]` is backed by the provided Java `Properties` and
   * any side-effects of using it via the Scala interface will be visible via
   * the Java interface and vice versa.
<<<<<<< HEAD
   * 
=======
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @param m The `Properties` to be converted.
   * @return An object with an `asScala` method that returns a Scala mutable
   *         `Map[String, String]` view of the argument.
   */
<<<<<<< HEAD
  implicit def propertiesAsScalaMapConverter(p: ju.Properties): AsScala[mutable.Map[String, String]] = 
=======
  implicit def propertiesAsScalaMapConverter(p: ju.Properties): AsScala[mutable.Map[String, String]] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new AsScala(propertiesAsScalaMap(p))

  @deprecated("Use propertiesAsScalaMapConverter instead", "2.9.0")
  def asScalaMapConverter(p: ju.Properties): AsScala[mutable.Map[String, String]] =
    propertiesAsScalaMapConverter(p)

}
