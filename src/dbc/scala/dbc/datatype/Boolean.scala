/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package datatype;


/** The SQL type for a truth value. */
@deprecated(DbcIsDeprecated, "2.9.0") class Boolean extends DataType {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isEquivalent (datatype:DataType) = datatype match {
    case dt:Boolean => true
    case _ => false
  }
<<<<<<< HEAD
  
  def isSubtypeOf (datatype:DataType) = isEquivalent(datatype);
  
  type NativeType = scala.Boolean;
  val nativeTypeId = DataType.BOOLEAN;
  
  /** A SQL-99 compliant string representation of the type. */
  override def sqlString: java.lang.String = "BOOLEAN";
  
=======

  def isSubtypeOf (datatype:DataType) = isEquivalent(datatype);

  type NativeType = scala.Boolean;
  val nativeTypeId = DataType.BOOLEAN;

  /** A SQL-99 compliant string representation of the type. */
  override def sqlString: java.lang.String = "BOOLEAN";

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
