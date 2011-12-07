/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package statement;


/** A statement that returns a relation. */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class Relation extends Statement {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isCompatibleType: (DataType,DataType)=>Boolean =
    ((dt,wdt)=>dt.isSubtypeOf(wdt));

  def typeCheck (relation: result.Relation): Unit = {
      val sameType: Boolean = (
        relation.metadata.length == fieldTypes.length &&
        (relation.metadata.zip(fieldTypes).forall({case Pair(field,expectedType) =>
          isCompatibleType(field.datatype, expectedType)}))
      );
      if (!sameType)
        throw new exception.IncompatibleSchema(fieldTypes,relation.metadata.map(field=>field.datatype));
  }

  def fieldTypes: List[DataType];
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def sqlTypeString: String =
    if (fieldTypes.isEmpty)
      "UNTYPED"
    else
      fieldTypes.map(dt=>dt.sqlString).mkString("RELATION (",", ",")");
<<<<<<< HEAD
  
  /** A SQL-99 compliant string representation of the statement. */
  def sqlString: String;
  
  /** A SQL-99 compliant string representation of the relation sub-
   * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String;
  
=======

  /** A SQL-99 compliant string representation of the statement. */
  def sqlString: String;

  /** A SQL-99 compliant string representation of the relation sub-
   * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Executes the statement on the given database. */
  def execute (database: scala.dbc.Database): scala.dbc.result.Relation = {
    database.executeStatement(this);
  }
<<<<<<< HEAD
  
  def execute (database:scala.dbc.Database, debug:Boolean): scala.dbc.result.Relation = {
    database.executeStatement(this,debug);
  }
  
=======

  def execute (database:scala.dbc.Database, debug:Boolean): scala.dbc.result.Relation = {
    database.executeStatement(this,debug);
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
