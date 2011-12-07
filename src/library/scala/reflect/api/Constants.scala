/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect
package api

import java.lang.Integer.toOctalString
import annotation.switch

trait Constants {
  self: Universe =>

  abstract class AbsConstant {
    val value: Any
    def tpe: Type
    def isNaN: Boolean

    def booleanValue: Boolean
    def byteValue: Byte
    def shortValue: Short
    def charValue: Char
    def intValue: Int
    def longValue: Long
    def floatValue: Float
    def doubleValue: Double
    def stringValue: String
    def typeValue: Type
    def symbolValue: Symbol

    def convertTo(pt: Type): Constant
  }

<<<<<<< HEAD
  type Constant <: AbsConstant 
  
  val Constant: ConstantExtractor
  
=======
  type Constant <: AbsConstant

  val Constant: ConstantExtractor

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  abstract class ConstantExtractor {
    def apply(const: Any): Constant
    def unapply(arg: Constant): Option[Any]
  }
}
