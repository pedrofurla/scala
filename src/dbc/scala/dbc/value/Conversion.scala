/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package value;

import java.math._;

@deprecated(DbcIsDeprecated, "2.9.0") object  Conversion {
<<<<<<< HEAD
  
  class Illegal (msg:String) extends Exception(msg);
  
=======

  class Illegal (msg:String) extends Exception(msg);

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view1 (value:Value): Byte = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to byte: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view2 (value:Value): Short = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      v.nativeValue.toShort
    } else if (value.dataType.nativeTypeId == DataType.SHORT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Short]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to short: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view3 (value:Value): Int = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      v.nativeValue.toInt
    } else if (value.dataType.nativeTypeId == DataType.SHORT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Short]];
      v.nativeValue.toInt
    } else if (value.dataType.nativeTypeId == DataType.INT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Int]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to int: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view4 (value:Value): Long = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      v.nativeValue.toLong
    } else if (value.dataType.nativeTypeId == DataType.SHORT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Short]];
      v.nativeValue.toLong
    } else if (value.dataType.nativeTypeId == DataType.INT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Int]];
      v.nativeValue.toLong
    } else if (value.dataType.nativeTypeId == DataType.LONG) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Long]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to long: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view5 (value:Value): BigInteger = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      new BigInteger(v.nativeValue.toString(),10)
    } else if (value.dataType.nativeTypeId == DataType.SHORT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Short]];
      new BigInteger(v.nativeValue.toString(),10)
    } else if (value.dataType.nativeTypeId == DataType.INT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Int]];
      new BigInteger(v.nativeValue.toString(),10)
    } else if (value.dataType.nativeTypeId == DataType.LONG) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Long]];
      new BigInteger(v.nativeValue.toString(),10)
    } else if (value.dataType.nativeTypeId == DataType.BIG_INTEGER) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[BigInteger]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to big integer: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view6 (value:Value): BigDecimal = {
    if (value.dataType.nativeTypeId == DataType.BYTE) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Byte]];
      new BigDecimal(v.nativeValue.toString())
    } else if (value.dataType.nativeTypeId == DataType.SHORT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Short]];
      new BigDecimal(v.nativeValue.toString())
    } else if (value.dataType.nativeTypeId == DataType.INT) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Int]];
      new BigDecimal(v.nativeValue.toString())
    } else if (value.dataType.nativeTypeId == DataType.LONG) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[Long]];
      new BigDecimal(v.nativeValue.toString())
    } else if (value.dataType.nativeTypeId == DataType.BIG_INTEGER) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[BigInteger]];
      new BigDecimal(v.nativeValue)
    } else if (value.dataType.nativeTypeId == DataType.BIG_DECIMAL) {
      val v = value.asInstanceOf[dbc.value.ExactNumeric[BigDecimal]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to big decimal: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view7 (value:Value): Float = {
    if (value.dataType.nativeTypeId == DataType.FLOAT) {
      val v = value.asInstanceOf[dbc.value.ApproximateNumeric[Float]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to float: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view8 (value:Value): Double = {
    if (value.dataType.nativeTypeId == DataType.FLOAT) {
      val v = value.asInstanceOf[dbc.value.ApproximateNumeric[Float]];
      v.nativeValue.toFloat
    } else if (value.dataType.nativeTypeId == DataType.DOUBLE) {
      val v = value.asInstanceOf[dbc.value.ApproximateNumeric[Double]];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to double: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view9 (value:Value): scala.Boolean = {
    if (value.dataType.nativeTypeId == DataType.BOOLEAN) {
      val v = value.asInstanceOf[dbc.value.Boolean];
      v.nativeValue
    } else {
      throw new Illegal("Cannot convert value to boolean: "+value)
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def view10 (value:Value): String = value match {
    case v:dbc.value.Character => v.nativeValue;
    case v:dbc.value.CharacterLargeObject => v.nativeValue;
    case v:dbc.value.CharacterVarying => v.nativeValue;
    case _ => throw new Illegal("Cannot convert value to string")
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
