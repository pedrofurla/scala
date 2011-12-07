// $Id$

package scala.tools.selectivecps

import scala.tools.nsc.Global

trait CPSUtils {
  val global: Global
  import global._
  import definitions._

<<<<<<< HEAD
  var cpsEnabled = false
  val verbose: Boolean = System.getProperty("cpsVerbose", "false") == "true"
  def vprintln(x: =>Any): Unit = if (verbose) println(x)

  lazy val MarkerCPSSym = definitions.getClass("scala.util.continuations.cpsSym")
  lazy val MarkerCPSTypes = definitions.getClass("scala.util.continuations.cpsParam")
  lazy val MarkerCPSSynth = definitions.getClass("scala.util.continuations.cpsSynth")

  lazy val MarkerCPSAdaptPlus = definitions.getClass("scala.util.continuations.cpsPlus")
  lazy val MarkerCPSAdaptMinus = definitions.getClass("scala.util.continuations.cpsMinus")


  lazy val Context = definitions.getClass("scala.util.continuations.ControlContext")

  lazy val ModCPS = definitions.getModule("scala.util.continuations")
  lazy val MethShiftUnit = definitions.getMember(ModCPS, "shiftUnit")
  lazy val MethShiftUnitR = definitions.getMember(ModCPS, "shiftUnitR")
  lazy val MethShift = definitions.getMember(ModCPS, "shift")
  lazy val MethShiftR = definitions.getMember(ModCPS, "shiftR")
  lazy val MethReify = definitions.getMember(ModCPS, "reify")
  lazy val MethReifyR = definitions.getMember(ModCPS, "reifyR")

=======
  var cpsEnabled = true
  val verbose: Boolean = System.getProperty("cpsVerbose", "false") == "true"
  def vprintln(x: =>Any): Unit = if (verbose) println(x)

  lazy val MarkerCPSSym        = definitions.getClass("scala.util.continuations.cpsSym")
  lazy val MarkerCPSTypes      = definitions.getClass("scala.util.continuations.cpsParam")
  lazy val MarkerCPSSynth      = definitions.getClass("scala.util.continuations.cpsSynth")
  lazy val MarkerCPSAdaptPlus  = definitions.getClass("scala.util.continuations.cpsPlus")
  lazy val MarkerCPSAdaptMinus = definitions.getClass("scala.util.continuations.cpsMinus")

  lazy val Context = definitions.getClass("scala.util.continuations.ControlContext")
  lazy val ModCPS = definitions.getModule("scala.util.continuations")

  lazy val MethShiftUnit  = definitions.getMember(ModCPS, "shiftUnit")
  lazy val MethShiftUnitR = definitions.getMember(ModCPS, "shiftUnitR")
  lazy val MethShift      = definitions.getMember(ModCPS, "shift")
  lazy val MethShiftR     = definitions.getMember(ModCPS, "shiftR")
  lazy val MethReify      = definitions.getMember(ModCPS, "reify")
  lazy val MethReifyR     = definitions.getMember(ModCPS, "reifyR")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  lazy val allCPSAnnotations = List(MarkerCPSSym, MarkerCPSTypes, MarkerCPSSynth,
    MarkerCPSAdaptPlus, MarkerCPSAdaptMinus)

<<<<<<< HEAD
  // annotation checker
  
  def filterAttribs(tpe:Type, cls:Symbol) = 
    tpe.annotations.filter(_.atp.typeSymbol == cls)

  def removeAttribs(tpe:Type, cls:Symbol*) =
    tpe.withoutAnnotations.withAnnotations(tpe.annotations.filterNot(cls contains _.atp.typeSymbol))

  def removeAllCPSAnnotations(tpe: Type) = removeAttribs(tpe, allCPSAnnotations:_*)

  def linearize(ann: List[AnnotationInfo]): AnnotationInfo = {
    ann.reduceLeft { (a, b) =>
      val atp0::atp1::Nil = a.atp.normalize.typeArgs
      val btp0::btp1::Nil = b.atp.normalize.typeArgs
      val (u0,v0) = (atp0, atp1)
      val (u1,v1) = (btp0, btp1)
/*
      val (u0,v0) = (a.atp.typeArgs(0), a.atp.typeArgs(1))
      val (u1,v1) = (b.atp.typeArgs(0), b.atp.typeArgs(1))
      vprintln("check lin " + a + " andThen " + b)
*/
      vprintln("check lin " + a + " andThen " + b)
      if (!(v1 <:< u0))
        throw new TypeError("illegal answer type modification: " + a + " andThen " + b)
      // TODO: improve error message (but it is not very common)
      AnnotationInfo(appliedType(MarkerCPSTypes.tpe, List(u1,v0)),Nil,Nil)
=======
  // TODO - needed? Can these all use the same annotation info?
  protected def newSynthMarker() = newMarker(MarkerCPSSynth)
  protected def newPlusMarker()  = newMarker(MarkerCPSAdaptPlus)
  protected def newMinusMarker() = newMarker(MarkerCPSAdaptMinus)
  protected def newMarker(tpe: Type): AnnotationInfo = AnnotationInfo marker tpe
  protected def newMarker(sym: Symbol): AnnotationInfo = AnnotationInfo marker sym.tpe

  protected def newCpsParamsMarker(tp1: Type, tp2: Type) =
    newMarker(appliedType(MarkerCPSTypes.tpe, List(tp1, tp2)))

  // annotation checker

  protected def annTypes(ann: AnnotationInfo): (Type, Type) = {
    val tp0 :: tp1 :: Nil = ann.atp.normalize.typeArgs
    ((tp0, tp1))
  }
  protected def hasMinusMarker(tpe: Type)   = tpe hasAnnotation MarkerCPSAdaptMinus
  protected def hasPlusMarker(tpe: Type)    = tpe hasAnnotation MarkerCPSAdaptPlus
  protected def hasSynthMarker(tpe: Type)   = tpe hasAnnotation MarkerCPSSynth
  protected def hasCpsParamTypes(tpe: Type) = tpe hasAnnotation MarkerCPSTypes
  protected def cpsParamTypes(tpe: Type)    = tpe getAnnotation MarkerCPSTypes map annTypes

  def filterAttribs(tpe:Type, cls:Symbol) =
    tpe.annotations filter (_ matches cls)

  def removeAttribs(tpe: Type, classes: Symbol*) =
    tpe filterAnnotations (ann => !(classes exists (ann matches _)))

  def removeAllCPSAnnotations(tpe: Type) = removeAttribs(tpe, allCPSAnnotations:_*)

  def cpsParamAnnotation(tpe: Type) = filterAttribs(tpe, MarkerCPSTypes)

  def linearize(ann: List[AnnotationInfo]): AnnotationInfo = {
    ann reduceLeft { (a, b) =>
      val (u0,v0) = annTypes(a)
      val (u1,v1) = annTypes(b)
      // vprintln("check lin " + a + " andThen " + b)

      if (v1 <:< u0)
        newCpsParamsMarker(u1, v0)
      else
        throw new TypeError("illegal answer type modification: " + a + " andThen " + b)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
  }

  // anf transform

  def getExternalAnswerTypeAnn(tp: Type) = {
<<<<<<< HEAD
    tp.annotations.find(a => a.atp.typeSymbol == MarkerCPSTypes) match {
      case Some(AnnotationInfo(atp, _, _)) => 
        val atp0::atp1::Nil = atp.normalize.typeArgs
        Some((atp0, atp1))
      case None => 
        if (tp.hasAnnotation(MarkerCPSAdaptPlus))
          global.warning("trying to instantiate type " + tp + " to unknown cps type")
        None
    }
  }

  def getAnswerTypeAnn(tp: Type) = {
    tp.annotations.find(a => a.atp.typeSymbol == MarkerCPSTypes) match {
      case Some(AnnotationInfo(atp, _, _)) => 
        if (!tp.hasAnnotation(MarkerCPSAdaptPlus)) {//&& !tp.hasAnnotation(MarkerCPSAdaptMinus))
          val atp0::atp1::Nil = atp.normalize.typeArgs
          Some((atp0, atp1))
        } else
          None
      case None => None
    }
  }

  def hasAnswerTypeAnn(tp: Type) = {
    tp.hasAnnotation(MarkerCPSTypes) && !tp.hasAnnotation(MarkerCPSAdaptPlus) /*&&
      !tp.hasAnnotation(MarkerCPSAdaptMinus)*/
  }

  def hasSynthAnn(tp: Type) = {
    tp.annotations.exists(a => a.atp.typeSymbol == MarkerCPSSynth)
  }

  def updateSynthFlag(tree: Tree) = { // remove annotations if *we* added them (@synth present)
    if (hasSynthAnn(tree.tpe)) {
      log("removing annotation from " + tree)
      tree.setType(removeAllCPSAnnotations(tree.tpe))
=======
    cpsParamTypes(tp) orElse {
      if (hasPlusMarker(tp))
        global.warning("trying to instantiate type " + tp + " to unknown cps type")
      None
    }
  }

  def getAnswerTypeAnn(tp: Type): Option[(Type, Type)] =
    cpsParamTypes(tp) filterNot (_ => hasPlusMarker(tp))

  def hasAnswerTypeAnn(tp: Type) =
    hasCpsParamTypes(tp) && !hasPlusMarker(tp)

  def updateSynthFlag(tree: Tree) = { // remove annotations if *we* added them (@synth present)
    if (hasSynthMarker(tree.tpe)) {
      log("removing annotation from " + tree)
      tree modifyType removeAllCPSAnnotations
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    } else
      tree
  }

  type CPSInfo = Option[(Type,Type)]

  def linearize(a: CPSInfo, b: CPSInfo)(implicit unit: CompilationUnit, pos: Position): CPSInfo = {
    (a,b) match {
      case (Some((u0,v0)), Some((u1,v1))) =>
        vprintln("check lin " + a + " andThen " + b)
        if (!(v1 <:< u0)) {
          unit.error(pos,"cannot change answer type in composition of cps expressions " +
          "from " + u1 + " to " + v0 + " because " + v1 + " is not a subtype of " + u0 + ".")
          throw new Exception("check lin " + a + " andThen " + b)
        }
        Some((u1,v0))
      case (Some(_), _) => a
      case (_, Some(_)) => b
      case _ => None
    }
  }
<<<<<<< HEAD
  
  // cps transform

}
=======
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
