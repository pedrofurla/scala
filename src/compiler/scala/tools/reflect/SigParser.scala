/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools
package reflect

import java.lang.reflect.{ GenericSignatureFormatError, Method }

/** The usual reflection song and dance to avoid referencing
 *  any sun.* classes.
 */
class SigParser {
  val SunSignatureParser = "sun.reflect.generics.parser.SignatureParser"
  private lazy val makeMethod: Method =
    try Class.forName(SunSignatureParser) getMethod "make"
    catch { case t => null }

  def make() = makeMethod.invoke(null).asInstanceOf[SignatureParserInterface]
<<<<<<< HEAD
  
  private def wrap(op: => Any) =
    try   { op ; true }
    catch { case _: GenericSignatureFormatError => false }
  
=======

  private def wrap(op: => Any) =
    try   { op ; true }
    catch { case _: GenericSignatureFormatError => false }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isParserAvailable = makeMethod != null
  def verifyClass(s: String)  = isParserAvailable && wrap(make() parseClassSig s)
  def verifyMethod(s: String) = isParserAvailable && wrap(make() parseMethodSig s)
  def verifyType(s: String)   = isParserAvailable && wrap(make() parseTypeSig s)

  type ClassSignature <: AnyRef
  type MethodTypeSignature <: AnyRef
  type TypeSignature <: AnyRef
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  type SignatureParserInterface = {
    def isParserAvailable: Boolean
    def parseClassSig(s: String): ClassSignature
    def parseMethodSig(s: String): MethodTypeSignature
    def parseTypeSig(s: String): TypeSignature
  }
}
object SigParser extends SigParser { }
