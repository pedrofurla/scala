/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */
package scala.reflect.internal.util

/** A common class for lightweight sets.
 */
abstract class Set[T <: AnyRef] {

  def findEntry(x: T): T

  def addEntry(x: T): Unit

  def iterator: Iterator[T]
<<<<<<< HEAD
  
  def foreach[U](f: T => U): Unit = iterator foreach f
  
  def apply(x: T): Boolean = contains(x)
  
=======

  def foreach[U](f: T => U): Unit = iterator foreach f

  def apply(x: T): Boolean = contains(x)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @deprecated("use `iterator` instead", "2.9.0") def elements = iterator

  def contains(x: T): Boolean =
    findEntry(x) ne null

  def toList = iterator.toList

}
