/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package statement
package expression;


@deprecated(DbcIsDeprecated, "2.9.0") case class TypeCast (
  expression: Expression,
  castType: DataType
) extends Expression {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A SQL-99 compliant string representation of the relation sub-
    * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String = {
    "CAST (" + expression.sqlInnerString + " AS " + castType.sqlString + ")";
  }
<<<<<<< HEAD
  
  /** The expression that will be casted. */
  //def expression: Expression;
  
=======

  /** The expression that will be casted. */
  //def expression: Expression;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The type to which to cast. */
  //def castType: scala.dbc.datatype.DataType;
}
