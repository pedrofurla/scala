package scala.reflect
package internal
package transform

import Flags._

trait UnCurry {
<<<<<<< HEAD
  
  val global: SymbolTable
  import global._
  import definitions._
  
  private def expandAlias(tp: Type): Type = if (!tp.isHigherKinded) tp.normalize else tp
  
  private def isUnboundedGeneric(tp: Type) = tp match {
    case t @ TypeRef(_, sym, _) => sym.isAbstractType && !(t <:< AnyRefClass.tpe) 
    case _ => false
  }

  protected val uncurry: TypeMap = new TypeMap {
=======

  val global: SymbolTable
  import global._
  import definitions._

  private def expandAlias(tp: Type): Type = if (!tp.isHigherKinded) tp.normalize else tp

  private def isUnboundedGeneric(tp: Type) = tp match {
    case t @ TypeRef(_, sym, _) => sym.isAbstractType && !(t <:< AnyRefClass.tpe)
    case _ => false
  }

  val uncurry: TypeMap = new TypeMap {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def apply(tp0: Type): Type = {
      // tp0.typeSymbolDirect.initialize
      val tp = expandAlias(tp0)
      tp match {
        case MethodType(params, MethodType(params1, restpe)) =>
          apply(MethodType(params ::: params1, restpe))
        case MethodType(params, ExistentialType(tparams, restpe @ MethodType(_, _))) =>
<<<<<<< HEAD
          assert(false, "unexpected curried method types with intervening existential") 
=======
          assert(false, "unexpected curried method types with intervening existential")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          tp0
        case MethodType(h :: t, restpe) if h.isImplicit =>
          apply(MethodType(h.cloneSymbol.resetFlag(IMPLICIT) :: t, restpe))
        case NullaryMethodType(restpe) =>
          apply(MethodType(List(), restpe))
        case TypeRef(pre, ByNameParamClass, List(arg)) =>
          apply(functionType(List(), arg))
        case TypeRef(pre, RepeatedParamClass, args) =>
          apply(appliedType(SeqClass.typeConstructor, args))
        case TypeRef(pre, JavaRepeatedParamClass, args) =>
          apply(arrayType(
            if (isUnboundedGeneric(args.head)) ObjectClass.tpe else args.head))
        case _ =>
          expandAlias(mapOver(tp))
      }
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private val uncurryType = new TypeMap {
    def apply(tp0: Type): Type = {
      val tp = expandAlias(tp0)
      tp match {
        case ClassInfoType(parents, decls, clazz) =>
          val parents1 = parents mapConserve uncurry
          if (parents1 eq parents) tp
          else ClassInfoType(parents1, decls, clazz) // @MAT normalize in decls??
        case PolyType(_, _) =>
          mapOver(tp)
        case _ =>
          tp
      }
    }
  }

<<<<<<< HEAD
  /** - return symbol's transformed type, 
=======
  /** - return symbol's transformed type,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *  - if symbol is a def parameter with transformed type T, return () => T
   *
   * @MAT: starting with this phase, the info of every symbol will be normalized
   */
<<<<<<< HEAD
  def transformInfo(sym: Symbol, tp: Type): Type = 
=======
  def transformInfo(sym: Symbol, tp: Type): Type =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (sym.isType) uncurryType(tp) else uncurry(tp)
}