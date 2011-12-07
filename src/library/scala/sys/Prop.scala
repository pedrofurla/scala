/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.sys

/** A lightweight interface wrapping a property contained in some
 *  unspecified map.  Generally it'll be the system properties but this
 *  is not a requirement.
 *
 *  See `scala.sys.SystemProperties` for an example usage.
 *
 *  @author Paul Phillips
 *  @version 2.9
 *  @since   2.9
 */
trait Prop[+T] {
  /** The full name of the property, e.g. "java.awt.headless".
   */
  def key: String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** If the key exists in the properties map, converts the value
   *  to type `T` using valueFn.  As yet no validation is performed:
   *  it will throw an exception on a failed conversion.
   *  @return   the converted value, or `zero` if not in the map
   */
  def value: T

  /** True if the key exists in the properties map.  Note that this
   *  is not sufficient for a Boolean property to be considered true.
   *  @return   whether the map contains the key
   */
  def isSet: Boolean
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Sets the property.
   *
   *  @param    the new string value
   *  @return   the old value, or null if it was unset.
   */
  def set(newValue: String): String
<<<<<<< HEAD
  
  /** Sets the property with a value of the represented type.
   */
  def setValue[T1 >: T](value: T1): T
  
=======

  /** Sets the property with a value of the represented type.
   */
  def setValue[T1 >: T](value: T1): T

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Gets the current string value if any.  Will not return null: use
   *  `isSet` to test for existence.
   *  @return   the current string value if any, else the empty string
   */
  def get: String
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Some(value) if the property is set, None otherwise.
   */
  def option: Option[T]

  /** Removes the property from the underlying map.
   */
  def clear(): Unit
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A value of type `T` for use when the property is unset.
   *  The default implementation delivers null for reference types
   *  and 0/0.0/false for non-reference types.
   */
  protected def zero: T
}

<<<<<<< HEAD
object Prop {  
=======
object Prop {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A creator of property instances.  For any type `T`, if an implicit
   *  parameter of type Creator[T] is in scope, a Prop[T] can be created
   *  via this object's apply method.
   */
  @annotation.implicitNotFound("No implicit property creator available for type ${T}.")
  trait Creator[+T] {
    /** Creates a Prop[T] of this type based on the given key. */
    def apply(key: String): Prop[T]
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit object FileProp extends CreatorImpl[java.io.File](s => new java.io.File(s))
  implicit object StringProp extends CreatorImpl[String](s => s)
  implicit object IntProp extends CreatorImpl[Int](_.toInt)
  implicit object DoubleProp extends CreatorImpl[Double](_.toDouble)

  def apply[T: Creator](key: String): Prop[T] = implicitly[Creator[T]] apply key
}