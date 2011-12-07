/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools.nsc
package interpreter

import java.lang.reflect
import java.util.concurrent.ConcurrentHashMap
import util.ScalaClassLoader
<<<<<<< HEAD
import ScalaClassLoader.getSystemLoader
=======
import ScalaClassLoader.appLoader
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.reflect.NameTransformer._

object ByteCode {
  /** Until I figure out why I can't get scalap onto the classpath such
   *  that the compiler will bootstrap, we have to use reflection.
   */
  private lazy val DECODER: Option[AnyRef] =
<<<<<<< HEAD
    for (clazz <- getSystemLoader.tryToLoadClass[AnyRef]("scala.tools.scalap.Decode$")) yield
      clazz.getField(MODULE_INSTANCE_NAME).get(null)
  
  private def decoderMethod(name: String, args: JClass*): Option[reflect.Method] = {
    for (decoder <- DECODER ; m <- Option(decoder.getClass.getMethod(name, args: _*))) yield m
  }   
=======
    for (clazz <- appLoader.tryToLoadClass[AnyRef]("scala.tools.scalap.Decode$")) yield
      clazz.getField(MODULE_INSTANCE_NAME).get(null)

  private def decoderMethod(name: String, args: JClass*): Option[reflect.Method] = {
    for (decoder <- DECODER ; m <- Option(decoder.getClass.getMethod(name, args: _*))) yield m
  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  private lazy val aliasMap = {
    for (module <- DECODER ; method <- decoderMethod("typeAliases", classOf[String])) yield
      method.invoke(module, _: String).asInstanceOf[Option[Map[String, String]]]
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Scala sig bytes.
   */
  def scalaSigBytesForPath(path: String) =
    for {
      module <- DECODER
      method <- decoderMethod("scalaSigAnnotationBytes", classOf[String])
      names <- method.invoke(module, path).asInstanceOf[Option[Array[Byte]]]
    }
<<<<<<< HEAD
    yield names  
  
=======
    yield names

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Attempts to retrieve case parameter names for given class name.
   */
  def caseParamNamesForPath(path: String) =
    for {
      module <- DECODER
      method <- decoderMethod("caseParamNames", classOf[String])
      names <- method.invoke(module, path).asInstanceOf[Option[List[String]]]
    }
    yield names

  def aliasesForPackage(pkg: String) = aliasMap flatMap (_(pkg))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Attempts to find type aliases in package objects.
   */
  def aliasForType(path: String): Option[String] = {
    val (pkg, name) = (path lastIndexOf '.') match {
      case -1   => return None
      case idx  => (path take idx, path drop (idx + 1))
    }
    aliasesForPackage(pkg) flatMap (_ get name)
  }
}
