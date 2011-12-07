/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc;


/** A SQL-99 value of any type. */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class Value {
<<<<<<< HEAD
  
  /** The SQL-99 type of the value. */
  val dataType: DataType;
  
  type NativeType = dataType.type#NativeType;
  
  val nativeValue: NativeType;
  
  /** A SQL-99 compliant string representation of the value. */
  def sqlString: String;
  
=======

  /** The SQL-99 type of the value. */
  val dataType: DataType;

  type NativeType = dataType.type#NativeType;

  val nativeValue: NativeType;

  /** A SQL-99 compliant string representation of the value. */
  def sqlString: String;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
