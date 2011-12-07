/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package value;


@deprecated(DbcIsDeprecated, "2.9.0") abstract class Unknown extends Value {
<<<<<<< HEAD
  
  val dataType: datatype.Unknown;
  
  def sqlString = sys.error("An 'ANY' value cannot be represented.");
  
=======

  val dataType: datatype.Unknown;

  def sqlString = sys.error("An 'ANY' value cannot be represented.");

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

@deprecated(DbcIsDeprecated, "2.9.0") object UnknownType {

  def view (obj:value.Unknown): AnyRef = obj.nativeValue;

}
