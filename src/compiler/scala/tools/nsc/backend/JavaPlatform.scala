/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package backend

import io.AbstractFile
<<<<<<< HEAD
import util.JavaClassPath
import util.ClassPath.{ JavaContext, DefaultJavaContext }
import scala.tools.util.PathResolver

trait JavaPlatform extends Platform[AbstractFile] {
  import global._
  import definitions._
  
  lazy val classPath  = new PathResolver(settings).result
  def rootLoader = new loaders.JavaPackageLoader(classPath)
=======
import util.{ClassPath,JavaClassPath}
import util.ClassPath.{ JavaContext, DefaultJavaContext }
import scala.tools.util.PathResolver

trait JavaPlatform extends Platform {
  import global._
  import definitions._

  type BinaryRepr = AbstractFile

  lazy val classPath  = new PathResolver(settings).result
  def rootLoader = new loaders.PackageLoader(classPath.asInstanceOf[ClassPath[platform.BinaryRepr]])
    // [Martin] Why do we need a cast here?
    // The problem is that we cannot specify at this point that global.platform should be of type JavaPlatform.
    // So we cannot infer that global.platform.BinaryRepr is AbstractFile.
    // Ideally, we should be able to write at the top of the JavaPlatform trait:
    //   val global: Global { val platform: JavaPlatform }
    //   import global._
    // Right now, this does nothing because the concrete definition of platform in Global
    // replaces the tighter abstract definition here. If we had DOT typing rules, the two
    // types would be conjoined and everything would work out. Yet another reason to push for DOT.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  private def depAnalysisPhase =
    if (settings.make.isDefault) Nil
    else List(dependencyAnalysis)

  def platformPhases = List(
    flatten,    // get rid of inner classes
<<<<<<< HEAD
    liftcode,   // generate reified trees
    genJVM      // generate .class files
  ) ++ depAnalysisPhase
  
=======
    genJVM      // generate .class files
  ) ++ depAnalysisPhase

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val externalEquals          = getMember(BoxesRunTimeClass, nme.equals_)
  lazy val externalEqualsNumNum    = getMember(BoxesRunTimeClass, "equalsNumNum")
  lazy val externalEqualsNumChar   = getMember(BoxesRunTimeClass, "equalsNumChar")
  lazy val externalEqualsNumObject = getMember(BoxesRunTimeClass, "equalsNumObject")
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** We could get away with excluding BoxedBooleanClass for the
   *  purpose of equality testing since it need not compare equal
   *  to anything but other booleans, but it should be present in
   *  case this is put to other uses.
   */
  def isMaybeBoxed(sym: Symbol) = {
    (sym == ObjectClass) ||
    (sym == JavaSerializableClass) ||
    (sym == ComparableClass) ||
    (sym isNonBottomSubClass BoxedNumberClass) ||
    (sym isNonBottomSubClass BoxedCharacterClass) ||
    (sym isNonBottomSubClass BoxedBooleanClass)
  }
<<<<<<< HEAD
=======

  def newClassLoader(bin: AbstractFile): loaders.SymbolLoader =
    new loaders.ClassfileLoader(bin)

  def doLoad(cls: ClassPath[BinaryRepr]#ClassRep): Boolean = true

  def needCompile(bin: AbstractFile, src: AbstractFile) =
    src.lastModified >= bin.lastModified
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
