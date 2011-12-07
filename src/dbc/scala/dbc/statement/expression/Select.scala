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


@deprecated(DbcIsDeprecated, "2.9.0") abstract class Select extends Expression {

  /** The actual select statement */
  def selectStatement: statement.Select;

  /** A SQL-99 compliant string representation of the expression. */
  override def sqlString: String = selectStatement.sqlString;
<<<<<<< HEAD
  
  /** A SQL-99 compliant string representation of the relation sub-
   * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String = "("+selectStatement.sqlString+")";
  
=======

  /** A SQL-99 compliant string representation of the relation sub-
   * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String = "("+selectStatement.sqlString+")";

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
