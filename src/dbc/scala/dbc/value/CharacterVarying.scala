/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package value;


/** A SQL-99 value of type character varying string. */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class CharacterVarying extends Value {
<<<<<<< HEAD
  
  override val dataType: datatype.CharacterVarying;
  
=======

  override val dataType: datatype.CharacterVarying;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** An SQL-99 compliant string representation of the value. */
  def sqlString: String = {
    "'" + nativeValue + "'"
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

/** An object offering transformation methods (views) on the value.
  * This object must be visible in an expression to use value auto-
  * conversion. */
@deprecated(DbcIsDeprecated, "2.9.0") object CharacterVarying {
<<<<<<< HEAD
  
  /** A character varying string value as a native string. */
  implicit def characterVaryingToString (obj:value.CharacterVarying): String = obj.nativeValue;
  
=======

  /** A character varying string value as a native string. */
  implicit def characterVaryingToString (obj:value.CharacterVarying): String = obj.nativeValue;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
