/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package statement


@deprecated(DbcIsDeprecated, "2.9.0") abstract class DerivedColumn {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The value for the column. This value can be of any type but must be
   *  calculated from fields that appear in a relation that takes part
   *  in the query.
   */
  def valueExpression: Expression
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A new name for this field. This name must be unique for the query in
   *  which the column takes part.
   */
  def asClause: Option[String]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A SQL-99 compliant string representation of the derived column
   *  sub-statement. This only has a meaning inside a select statement.
   */
  def sqlString: String =
    valueExpression.sqlInnerString +
    (asClause match {
      case None => ""
      case Some(ac) => " AS " + ac
    })
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
