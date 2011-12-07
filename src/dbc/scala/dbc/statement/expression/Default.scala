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


case object Default extends Expression {
<<<<<<< HEAD
  
  /** A SQL-99 compliant string representation of the relation sub-
    * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String = "DEFAULT";
  
=======

  /** A SQL-99 compliant string representation of the relation sub-
    * statement. This only has a meaning inside another statement. */
  def sqlInnerString: String = "DEFAULT";

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
