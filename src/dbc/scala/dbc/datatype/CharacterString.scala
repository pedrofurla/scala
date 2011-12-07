/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package datatype;


/** A type category for all SQL types that store strings of characters. */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class CharacterString extends String {
<<<<<<< HEAD
  
  type NativeType = java.lang.String;
  val nativeTypeId = DataType.STRING;
  
  /** The name of the character set in which the string is encoded. */
  def encoding: Option[java.lang.String] = None;
  
=======

  type NativeType = java.lang.String;
  val nativeTypeId = DataType.STRING;

  /** The name of the character set in which the string is encoded. */
  def encoding: Option[java.lang.String] = None;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
