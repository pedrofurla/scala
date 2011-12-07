/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package value;


@deprecated(DbcIsDeprecated, "2.9.0") abstract class ApproximateNumeric [Type] extends Value {
<<<<<<< HEAD
  
  val dataType: datatype.ApproximateNumeric[Type];
  
  def sqlString = nativeValue.toString();
  
  }

@deprecated(DbcIsDeprecated, "2.9.0") object ApproximateNumeric {
  
  implicit def approximateNumericToFloar (obj:value.ApproximateNumeric[Float]): Float = obj.nativeValue;
  implicit def approximateNumericToDouble (obj:value.ApproximateNumeric[Double]): Double = obj.nativeValue;
  
=======

  val dataType: datatype.ApproximateNumeric[Type];

  def sqlString = nativeValue.toString();

  }

@deprecated(DbcIsDeprecated, "2.9.0") object ApproximateNumeric {

  implicit def approximateNumericToFloar (obj:value.ApproximateNumeric[Float]): Float = obj.nativeValue;
  implicit def approximateNumericToDouble (obj:value.ApproximateNumeric[Double]): Double = obj.nativeValue;

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
