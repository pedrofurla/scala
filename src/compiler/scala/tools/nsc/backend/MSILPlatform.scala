/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package backend

<<<<<<< HEAD
import ch.epfl.lamp.compiler.msil.{ Type => MSILType }
import util.MsilClassPath
import msil.GenMSIL

trait MSILPlatform extends Platform[MSILType] {
  import global._
  import definitions.{ ComparatorClass, BoxedNumberClass, getMember, getClass }

  if (settings.verbose.value)
    inform("[AssemRefs = " + settings.assemrefs.value + "]")
  
=======
import ch.epfl.lamp.compiler.{ msil => msillib }
import util.{ ClassPath, MsilClassPath }
import msil.GenMSIL
import io.{ AbstractFile, MsilFile }

trait MSILPlatform extends Platform {
  import global._
  import definitions.{ ComparatorClass, BoxedNumberClass, getMember }

  type BinaryRepr = MsilFile

  if (settings.verbose.value)
    inform("[AssemRefs = " + settings.assemrefs.value + "]")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // phaseName = "msil"
  object genMSIL extends {
    val global: MSILPlatform.this.global.type = MSILPlatform.this.global
    val runsAfter = List[String]("dce")
    val runsRightAfter = None
  } with GenMSIL
<<<<<<< HEAD
  
  lazy val classPath = MsilClassPath.fromSettings(settings)
  def rootLoader = new loaders.NamespaceLoader(classPath)
  
  def platformPhases = List(
    genMSIL   // generate .msil files
  )  
  
  lazy val externalEquals = getMember(ComparatorClass.companionModule, nme.equals_)
  def isMaybeBoxed(sym: Symbol) = sym isNonBottomSubClass BoxedNumberClass
=======

  lazy val classPath = MsilClassPath.fromSettings(settings)
  def rootLoader = new loaders.PackageLoader(classPath.asInstanceOf[ClassPath[platform.BinaryRepr]])
    // See discussion in JavaPlatForm for why we need a cast here.

  def platformPhases = List(
    genMSIL   // generate .msil files
  )

  lazy val externalEquals = getMember(ComparatorClass.companionModule, nme.equals_)
  def isMaybeBoxed(sym: Symbol) = sym isNonBottomSubClass BoxedNumberClass

  def newClassLoader(bin: MsilFile): loaders.SymbolLoader =  new loaders.MsilFileLoader(bin)

  /**
   * Tells whether a class should be loaded and entered into the package
   * scope. On .NET, this method returns `false` for all synthetic classes
   * (anonymous classes, implementation classes, module classes), their
   * symtab is encoded in the pickle of another class.
   */
  def doLoad(cls: ClassPath[BinaryRepr]#ClassRep): Boolean = {
    if (cls.binary.isDefined) {
      val typ = cls.binary.get.msilType
      if (typ.IsDefined(loaders.clrTypes.SCALA_SYMTAB_ATTR, false)) {
        val attrs = typ.GetCustomAttributes(loaders.clrTypes.SCALA_SYMTAB_ATTR, false)
        assert(attrs.length == 1, attrs.length)
        val a = attrs(0).asInstanceOf[msillib.Attribute]
        // symtab_constr takes a byte array argument (the pickle), i.e. typ has a pickle.
        // otherwise, symtab_default_constr was used, which marks typ as scala-synthetic.
        a.getConstructor() == loaders.clrTypes.SYMTAB_CONSTR
      } else true // always load non-scala types
    } else true // always load source
  }

  def needCompile(bin: MsilFile, src: AbstractFile) =
    false // always use compiled file on .net
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
