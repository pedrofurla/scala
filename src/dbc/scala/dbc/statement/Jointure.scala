/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package statement


/** A jointure between two relations. */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class Jointure extends Relation {
<<<<<<< HEAD
  
  /** The relation on the left part of the join. */
  def leftRelation: Relation
  
  /** The relation on the right part of the join. */
  def rightRelation: Relation
  
  /** The type of the jointure. */
  def joinType: JoinType
  
  /** The condition on which the jointure needs be done. */
  def joinCondition: Option[Expression]
  
  /** A SQL-99 compliant string representation of the relation statement. */
  def sqlString: String = "SELECT * FROM " + sqlInnerString
  
=======

  /** The relation on the left part of the join. */
  def leftRelation: Relation

  /** The relation on the right part of the join. */
  def rightRelation: Relation

  /** The type of the jointure. */
  def joinType: JoinType

  /** The condition on which the jointure needs be done. */
  def joinCondition: Option[Expression]

  /** A SQL-99 compliant string representation of the relation statement. */
  def sqlString: String = "SELECT * FROM " + sqlInnerString

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A SQL-99 compliant string representation of the relation sub-
   *  statement. This only has a meaning inside a query.
   */
  def sqlInnerString: String =
    leftRelation.sqlInnerString + " " +
    joinType.sqlString + " " +
    rightRelation.sqlInnerString +
    (joinCondition match {
      case Some(jc) => jc.sqlString
      case None => ""
    })
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
