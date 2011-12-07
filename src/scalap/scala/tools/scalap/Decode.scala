/*     ___ ____ ___   __   ___   ___
**    / _// __// _ | / /  / _ | / _ \  Scala classfile decoder
**  __\ \/ /__/ __ |/ /__/ __ |/ ___/  (c) 2003-2011, LAMP/EPFL
** /____/\___/_/ |_/____/_/ |_/_/      http://scala-lang.org/
**
*/

// $Id$

package scala.tools.scalap

import scala.tools.scalap.scalax.rules.scalasig._
import scala.tools.nsc.util.ScalaClassLoader
<<<<<<< HEAD
import scala.tools.nsc.util.ScalaClassLoader.getSystemLoader
=======
import scala.tools.nsc.util.ScalaClassLoader.appLoader
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.reflect.internal.pickling.ByteCodecs

import ClassFileParser.{ ConstValueIndex, Annotation }
import Main.{ SCALA_SIG, SCALA_SIG_ANNOTATION, BYTES_VALUE }

/** Temporary decoder.  This would be better off in the scala.tools.nsc
 *  but right now the compiler won't acknowledge scala.tools.scalap
 *  when it's bootstrapping, so the reference has to go from here to there.
 */
object Decode {
  private def getAliasSymbol(t: Type): Symbol = t match {
    case TypeRefType(_, s, _)   => s
    case PolyType(typeRef, _)   => getAliasSymbol(typeRef)
    case _                      => NoSymbol
  }
<<<<<<< HEAD
  
  /** Return the classfile bytes representing the scala sig classfile attribute.
   *  This has been obsoleted by the switch to annotations.
   */
  def scalaSigBytes(name: String): Option[Array[Byte]] = scalaSigBytes(name, getSystemLoader())
=======

  /** Return the classfile bytes representing the scala sig classfile attribute.
   *  This has been obsoleted by the switch to annotations.
   */
  def scalaSigBytes(name: String): Option[Array[Byte]] = scalaSigBytes(name, appLoader)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def scalaSigBytes(name: String, classLoader: ScalaClassLoader): Option[Array[Byte]] = {
    val bytes = classLoader.classBytes(name)
    val reader = new ByteArrayReader(bytes)
    val cf = new Classfile(reader)
<<<<<<< HEAD
    cf.scalaSigAttribute map (_.data) 
  }
  
  /** Return the bytes representing the annotation
   */
  def scalaSigAnnotationBytes(name: String): Option[Array[Byte]] = scalaSigAnnotationBytes(name, getSystemLoader())
  def scalaSigAnnotationBytes(name: String, classLoader: ScalaClassLoader): Option[Array[Byte]] = {  
=======
    cf.scalaSigAttribute map (_.data)
  }

  /** Return the bytes representing the annotation
   */
  def scalaSigAnnotationBytes(name: String): Option[Array[Byte]] = scalaSigAnnotationBytes(name, appLoader)
  def scalaSigAnnotationBytes(name: String, classLoader: ScalaClassLoader): Option[Array[Byte]] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val bytes     = classLoader.classBytes(name)
    val byteCode  = ByteCode(bytes)
    val classFile = ClassFileParser.parse(byteCode)
    import classFile._
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    classFile annotation SCALA_SIG_ANNOTATION map { case Annotation(_, els) =>
      val bytesElem = els find (x => constant(x.elementNameIndex) == BYTES_VALUE) get
      val _bytes    = bytesElem.elementValue match { case ConstValueIndex(x) => constantWrapped(x) }
      val bytes     = _bytes.asInstanceOf[StringBytesPair].bytes
      val length    = ByteCodecs.decode(bytes)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      bytes take length
    }
  }

  /** private[scala] so nobody gets the idea this is a supported interface.
   */
  private[scala] def caseParamNames(path: String): Option[List[String]] = {
    val (outer, inner) = (path indexOf '$') match {
      case -1   => (path, "")
      case x    => (path take x, path drop (x + 1))
    }
<<<<<<< HEAD
    
    for {
      clazz <- getSystemLoader.tryToLoadClass[AnyRef](outer)
=======

    for {
      clazz <- appLoader.tryToLoadClass[AnyRef](outer)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      ssig <- ScalaSigParser.parse(clazz)
    }
    yield {
      val f: PartialFunction[Symbol, List[String]] =
        if (inner == "") {
          case x: MethodSymbol if x.isCaseAccessor && (x.name endsWith " ") => List(x.name dropRight 1)
        }
        else {
          case x: ClassSymbol if x.name == inner  =>
            val xs = x.children filter (child => child.isCaseAccessor && (child.name endsWith " "))
            xs.toList map (_.name dropRight 1)
        }
<<<<<<< HEAD
      
      (ssig.symbols collect f).flatten toList
    }
  }
  
=======

      (ssig.symbols collect f).flatten toList
    }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Returns a map of Alias -> Type for the given package.
   */
  private[scala] def typeAliases(pkg: String) = {
    for {
<<<<<<< HEAD
      clazz <- getSystemLoader.tryToLoadClass[AnyRef](pkg + ".package")
=======
      clazz <- appLoader.tryToLoadClass[AnyRef](pkg + ".package")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      ssig <- ScalaSigParser.parse(clazz)
    }
    yield {
      val typeAliases = ssig.symbols collect { case x: AliasSymbol => x }
      Map(typeAliases map (x => (x.name, getAliasSymbol(x.infoType).path)): _*)
    }
  }
<<<<<<< HEAD
}  
=======
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

