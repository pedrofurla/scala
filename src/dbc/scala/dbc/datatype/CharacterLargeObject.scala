/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package datatype;


/** A SQL type for an unbounded length string of characters with arbitrary
  * character set. */
@deprecated(DbcIsDeprecated, "2.9.0") class CharacterLargeObject extends CharacterString {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isEquivalent (datatype:DataType) = datatype match {
    case dt:CharacterLargeObject => {
      encoding == dt.encoding
    }
    case _ => false
  }
<<<<<<< HEAD
  
  def isSubtypeOf (datatype:DataType) = isEquivalent(datatype);
  
  /** A SQL-99 compliant string representation of the type. */
  override def sqlString: java.lang.String = "CHARACTER LARGE OBJECT";
  
=======

  def isSubtypeOf (datatype:DataType) = isEquivalent(datatype);

  /** A SQL-99 compliant string representation of the type. */
  override def sqlString: java.lang.String = "CHARACTER LARGE OBJECT";

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
