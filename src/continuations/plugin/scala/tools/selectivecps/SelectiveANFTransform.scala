// $Id$

package scala.tools.selectivecps

import scala.tools.nsc._
import scala.tools.nsc.transform._
import scala.tools.nsc.symtab._
import scala.tools.nsc.plugins._

import scala.tools.nsc.ast._

<<<<<<< HEAD
/** 
=======
/**
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * In methods marked @cps, explicitly name results of calls to other @cps methods
 */
abstract class SelectiveANFTransform extends PluginComponent with Transform with
  TypingTransformers with CPSUtils {
  // inherits abstract value `global` and class `Phase` from Transform

  import global._                  // the global environment
  import definitions._             // standard classes and methods
  import typer.atOwner             // methods to type trees

  /** the following two members override abstract members in Transform */
  val phaseName: String = "selectiveanf"

  protected def newTransformer(unit: CompilationUnit): Transformer =
    new ANFTransformer(unit)


  class ANFTransformer(unit: CompilationUnit) extends TypingTransformer(unit) {

    implicit val _unit = unit // allow code in CPSUtils.scala to report errors
    var cpsAllowed: Boolean = false // detect cps code in places we do not handle (yet)

    override def transform(tree: Tree): Tree = {
      if (!cpsEnabled) return tree

      tree match {

<<<<<<< HEAD
        // Maybe we should further generalize the transform and move it over 
        // to the regular Transformer facility. But then, actual and required cps
        // state would need more complicated (stateful!) tracking. 
        
=======
        // Maybe we should further generalize the transform and move it over
        // to the regular Transformer facility. But then, actual and required cps
        // state would need more complicated (stateful!) tracking.

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // Making the default case use transExpr(tree, None, None) instead of
        // calling super.transform() would be a start, but at the moment,
        // this would cause infinite recursion. But we could remove the
        // ValDef case here.
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case dd @ DefDef(mods, name, tparams, vparamss, tpt, rhs) =>
          log("transforming " + dd.symbol)

          atOwner(dd.symbol) {
            val rhs1 = transExpr(rhs, None, getExternalAnswerTypeAnn(tpt.tpe))
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            log("result "+rhs1)
            log("result is of type "+rhs1.tpe)

            treeCopy.DefDef(dd, mods, name, transformTypeDefs(tparams), transformValDefss(vparamss),
                        transform(tpt), rhs1)
          }

        case ff @ Function(vparams, body) =>
          log("transforming anon function " + ff.symbol)

          atOwner(ff.symbol) {

            //val body1 = transExpr(body, None, getExternalAnswerTypeAnn(body.tpe))

            // need to special case partial functions: if expected type is @cps
            // but all cases are pure, then we would transform
            // { x => x match { case A => ... }} to
            // { x => shiftUnit(x match { case A => ... })}
            // which Uncurry cannot handle (see function6.scala)
<<<<<<< HEAD
            
            val ext = getExternalAnswerTypeAnn(body.tpe)
            
=======

            val ext = getExternalAnswerTypeAnn(body.tpe)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val body1 = body match {
              case Match(selector, cases) if (ext.isDefined && getAnswerTypeAnn(body.tpe).isEmpty) =>
                val cases1 = for {
                  cd @ CaseDef(pat, guard, caseBody) <- cases
<<<<<<< HEAD
                  val caseBody1 = transExpr(body, None, ext)
=======
                  caseBody1 = transExpr(body, None, ext)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                } yield {
                  treeCopy.CaseDef(cd, transform(pat), transform(guard), caseBody1)
                }
                treeCopy.Match(tree, transform(selector), cases1)

              case _ =>
                transExpr(body, None, ext)
            }
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            log("result "+body1)
            log("result is of type "+body1.tpe)

            treeCopy.Function(ff, transformValDefs(vparams), body1)
          }

        case vd @ ValDef(mods, name, tpt, rhs) => // object-level valdefs
          log("transforming valdef " + vd.symbol)

          atOwner(vd.symbol) {

            assert(getExternalAnswerTypeAnn(tpt.tpe) == None)

            val rhs1 = transExpr(rhs, None, None)

            treeCopy.ValDef(vd, mods, name, transform(tpt), rhs1)
          }

        case TypeTree() =>
          // circumvent cpsAllowed here
          super.transform(tree)
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case Apply(_,_) =>
          // this allows reset { ... } in object constructors
          // it's kind of a hack to put it here (see note above)
          transExpr(tree, None, None)
<<<<<<< HEAD
        
        case _ => 
          
=======

        case _ =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          if (hasAnswerTypeAnn(tree.tpe)) {
            if (!cpsAllowed)
              unit.error(tree.pos, "cps code not allowed here / " + tree.getClass + " / " + tree)

            log(tree)
          }

          cpsAllowed = false
<<<<<<< HEAD
          super.transform(tree)            
=======
          super.transform(tree)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }


    def transExpr(tree: Tree, cpsA: CPSInfo, cpsR: CPSInfo): Tree = {
      transTailValue(tree, cpsA, cpsR) match {
        case (Nil, b) => b
        case (a, b) =>
          treeCopy.Block(tree, a,b)
      }
    }


    def transArgList(fun: Tree, args: List[Tree], cpsA: CPSInfo): (List[List[Tree]], List[Tree], CPSInfo) = {
      val formals = fun.tpe.paramTypes
      val overshoot = args.length - formals.length
<<<<<<< HEAD
      
      var spc: CPSInfo = cpsA
      
=======

      var spc: CPSInfo = cpsA

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val (stm,expr) = (for ((a,tp) <- args.zip(formals ::: List.fill(overshoot)(NoType))) yield {
        tp match {
          case TypeRef(_, ByNameParamClass, List(elemtp)) =>
            (Nil, transExpr(a, None, getAnswerTypeAnn(elemtp)))
          case _ =>
            val (valStm, valExpr, valSpc) = transInlineValue(a, spc)
            spc = valSpc
            (valStm, valExpr)
        }
      }).unzip
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      (stm,expr,spc)
    }


    def transValue(tree: Tree, cpsA: CPSInfo, cpsR: CPSInfo): (List[Tree], Tree, CPSInfo) = {
      // return value: (stms, expr, spc), where spc is CPSInfo after stms but *before* expr
      implicit val pos = tree.pos
      tree match {
<<<<<<< HEAD
        case Block(stms, expr) => 
          val (cpsA2, cpsR2) = (cpsA, linearize(cpsA, getAnswerTypeAnn(tree.tpe))) // tbd
//          val (cpsA2, cpsR2) = (None, getAnswerTypeAnn(tree.tpe))
          val (a, b) = transBlock(stms, expr, cpsA2, cpsR2)
          
=======
        case Block(stms, expr) =>
          val (cpsA2, cpsR2) = (cpsA, linearize(cpsA, getAnswerTypeAnn(tree.tpe))) // tbd
//          val (cpsA2, cpsR2) = (None, getAnswerTypeAnn(tree.tpe))
          val (a, b) = transBlock(stms, expr, cpsA2, cpsR2)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          val tree1 = (treeCopy.Block(tree, a, b)) // no updateSynthFlag here!!!

          (Nil, tree1, cpsA)

<<<<<<< HEAD
        case If(cond, thenp, elsep) =>
          
          val (condStats, condVal, spc) = transInlineValue(cond, cpsA)

          val (cpsA2, cpsR2) = (spc, linearize(spc, getAnswerTypeAnn(tree.tpe)))
//          val (cpsA2, cpsR2) = (None, getAnswerTypeAnn(tree.tpe))
          val thenVal = transExpr(thenp, cpsA2, cpsR2)
          val elseVal = transExpr(elsep, cpsA2, cpsR2)
          
          // check that then and else parts agree (not necessary any more, but left as sanity check)
          if (cpsR.isDefined) {
            if (elsep == EmptyTree)
              unit.error(tree.pos, "always need else part in cps code")
          }
          if (hasAnswerTypeAnn(thenVal.tpe) != hasAnswerTypeAnn(elseVal.tpe)) {
            unit.error(tree.pos, "then and else parts must both be cps code or neither of them")
          }

          (condStats, updateSynthFlag(treeCopy.If(tree, condVal, thenVal, elseVal)), spc)

        case Match(selector, cases) =>
        
          val (selStats, selVal, spc) = transInlineValue(selector, cpsA)
          val (cpsA2, cpsR2) = (spc, linearize(spc, getAnswerTypeAnn(tree.tpe)))
//          val (cpsA2, cpsR2) = (None, getAnswerTypeAnn(tree.tpe))

          val caseVals = for {
            cd @ CaseDef(pat, guard, body) <- cases
            val bodyVal = transExpr(body, cpsA2, cpsR2)
          } yield {
            treeCopy.CaseDef(cd, transform(pat), transform(guard), bodyVal)
          }
          
          (selStats, updateSynthFlag(treeCopy.Match(tree, selVal, caseVals)), spc)
=======
          case If(cond, thenp, elsep) =>
            /* possible situations:
            cps before (cpsA)
            cps in condition (spc)  <-- synth flag set if *only* here!
            cps in (one or both) branches */
            val (condStats, condVal, spc) = transInlineValue(cond, cpsA)
            val (cpsA2, cpsR2) = if (hasSynthMarker(tree.tpe))
              (spc, linearize(spc, getAnswerTypeAnn(tree.tpe))) else
              (None, getAnswerTypeAnn(tree.tpe)) // if no cps in condition, branches must conform to tree.tpe directly
            val thenVal = transExpr(thenp, cpsA2, cpsR2)
            val elseVal = transExpr(elsep, cpsA2, cpsR2)

            // check that then and else parts agree (not necessary any more, but left as sanity check)
            if (cpsR.isDefined) {
              if (elsep == EmptyTree)
                unit.error(tree.pos, "always need else part in cps code")
            }
            if (hasAnswerTypeAnn(thenVal.tpe) != hasAnswerTypeAnn(elseVal.tpe)) {
              unit.error(tree.pos, "then and else parts must both be cps code or neither of them")
            }

            (condStats, updateSynthFlag(treeCopy.If(tree, condVal, thenVal, elseVal)), spc)

          case Match(selector, cases) =>

            val (selStats, selVal, spc) = transInlineValue(selector, cpsA)
            val (cpsA2, cpsR2) = if (hasSynthMarker(tree.tpe))
              (spc, linearize(spc, getAnswerTypeAnn(tree.tpe))) else
              (None, getAnswerTypeAnn(tree.tpe))

            val caseVals = for {
              cd @ CaseDef(pat, guard, body) <- cases
              bodyVal = transExpr(body, cpsA2, cpsR2)
            } yield {
              treeCopy.CaseDef(cd, transform(pat), transform(guard), bodyVal)
            }

            (selStats, updateSynthFlag(treeCopy.Match(tree, selVal, caseVals)), spc)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0


        case ldef @ LabelDef(name, params, rhs) =>
          if (hasAnswerTypeAnn(tree.tpe)) {
<<<<<<< HEAD
            val sym = currentOwner.newMethod(tree.pos, name)//unit.fresh.newName(tree.pos, "myloopvar")
                        .setInfo(ldef.symbol.info)
                        .setFlag(Flags.SYNTHETIC)
          
            val rhs1 = new TreeSymSubstituter(List(ldef.symbol), List(sym)).transform(rhs)
            val rhsVal = transExpr(rhs1, None, getAnswerTypeAnn(tree.tpe))
=======
            val sym = currentOwner.newMethod(tree.pos, name)
                        .setInfo(ldef.symbol.info)
                        .setFlag(Flags.SYNTHETIC)

            val rhs1 = new TreeSymSubstituter(List(ldef.symbol), List(sym)).transform(rhs)
            val rhsVal = transExpr(rhs1, None, getAnswerTypeAnn(tree.tpe))
            new ChangeOwnerTraverser(currentOwner, sym) traverse rhsVal
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

            val stm1 = localTyper.typed(DefDef(sym, rhsVal))
            val expr = localTyper.typed(Apply(Ident(sym), List()))

            (List(stm1), expr, cpsA)
          } else {
            val rhsVal = transExpr(rhs, None, None)
            (Nil, updateSynthFlag(treeCopy.LabelDef(tree, name, params, rhsVal)), cpsA)
          }
<<<<<<< HEAD
          

        case Try(block, catches, finalizer) =>
          val blockVal = transExpr(block, cpsA, cpsR)
        
          val catchVals = for {
            cd @ CaseDef(pat, guard, body) <- catches
            val bodyVal = transExpr(body, cpsA, cpsR)
=======


        case Try(block, catches, finalizer) =>
          val blockVal = transExpr(block, cpsA, cpsR)

          val catchVals = for {
            cd @ CaseDef(pat, guard, body) <- catches
            bodyVal = transExpr(body, cpsA, cpsR)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          } yield {
            treeCopy.CaseDef(cd, transform(pat), transform(guard), bodyVal)
          }

          val finallyVal = transExpr(finalizer, None, None) // for now, no cps in finally

          (Nil, updateSynthFlag(treeCopy.Try(tree, blockVal, catchVals, finallyVal)), cpsA)

        case Assign(lhs, rhs) =>
          // allow cps code in rhs only
          val (stms, expr, spc) = transInlineValue(rhs, cpsA)
          (stms, updateSynthFlag(treeCopy.Assign(tree, transform(lhs), expr)), spc)
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case Return(expr0) =>
          val (stms, expr, spc) = transInlineValue(expr0, cpsA)
          (stms, updateSynthFlag(treeCopy.Return(tree, expr)), spc)

        case Throw(expr0) =>
          val (stms, expr, spc) = transInlineValue(expr0, cpsA)
          (stms, updateSynthFlag(treeCopy.Throw(tree, expr)), spc)

        case Typed(expr0, tpt) =>
          // TODO: should x: A @cps[B,C] have a special meaning?
          // type casts used in different ways (see match2.scala, #3199)
          val (stms, expr, spc) = transInlineValue(expr0, cpsA)
          val tpt1 = if (treeInfo.isWildcardStarArg(tree)) tpt else
            treeCopy.TypeTree(tpt).setType(removeAllCPSAnnotations(tpt.tpe))
//        (stms, updateSynthFlag(treeCopy.Typed(tree, expr, tpt1)), spc)
          (stms, treeCopy.Typed(tree, expr, tpt1).setType(removeAllCPSAnnotations(tree.tpe)), spc)
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case TypeApply(fun, args) =>
          val (stms, expr, spc) = transInlineValue(fun, cpsA)
          (stms, updateSynthFlag(treeCopy.TypeApply(tree, expr, args)), spc)

        case Select(qual, name) =>
          val (stms, expr, spc) = transInlineValue(qual, cpsA)
          (stms, updateSynthFlag(treeCopy.Select(tree, expr, name)), spc)

        case Apply(fun, args) =>
          val (funStm, funExpr, funSpc) = transInlineValue(fun, cpsA)
          val (argStm, argExpr, argSpc) = transArgList(fun, args, funSpc)

          (funStm ::: (argStm.flatten), updateSynthFlag(treeCopy.Apply(tree, funExpr, argExpr)),
            argSpc)

        case _ =>
          cpsAllowed = true
          (Nil, transform(tree), cpsA)
      }
    }
<<<<<<< HEAD
    
    def transTailValue(tree: Tree, cpsA: CPSInfo, cpsR: CPSInfo): (List[Tree], Tree) = {
      
=======

    def transTailValue(tree: Tree, cpsA: CPSInfo, cpsR: CPSInfo): (List[Tree], Tree) = {

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val (stms, expr, spc) = transValue(tree, cpsA, cpsR)

      val bot = linearize(spc, getAnswerTypeAnn(expr.tpe))(unit, tree.pos)

      val plainTpe = removeAllCPSAnnotations(expr.tpe)

      if (cpsR.isDefined && !bot.isDefined) {
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (!expr.isEmpty && (expr.tpe.typeSymbol ne NothingClass)) {
          // must convert!
          log("cps type conversion (has: " + cpsA + "/" + spc + "/" + expr.tpe  + ")")
          log("cps type conversion (expected: " + cpsR.get + "): " + expr)
<<<<<<< HEAD
          
          if (!expr.tpe.hasAnnotation(MarkerCPSAdaptPlus))
            unit.warning(tree.pos, "expression " + tree + " is cps-transformed unexpectedly")
          
=======

          if (!hasPlusMarker(expr.tpe))
            unit.warning(tree.pos, "expression " + tree + " is cps-transformed unexpectedly")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          try {
            val Some((a, b)) = cpsR

            val res = localTyper.typed(atPos(tree.pos) {
<<<<<<< HEAD
                    Apply(TypeApply(gen.mkAttributedRef(MethShiftUnit), 
                      List(TypeTree(plainTpe), TypeTree(a), TypeTree(b))), 
=======
                    Apply(TypeApply(gen.mkAttributedRef(MethShiftUnit),
                      List(TypeTree(plainTpe), TypeTree(a), TypeTree(b))),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                       List(expr))
            })
            return (stms, res)

          } catch {
            case ex:TypeError =>
              unit.error(ex.pos, "cannot cps-transform expression " + tree + ": " + ex.msg)
          }
        }

      } else if (!cpsR.isDefined && bot.isDefined) {
        // error!
        log("cps type error: " + expr)
        //println("cps type error: " + expr + "/" + expr.tpe + "/" + getAnswerTypeAnn(expr.tpe))

        println(cpsR + "/" + spc + "/" + bot)

        unit.error(tree.pos, "found cps expression in non-cps position")
      } else {
        // all is well

<<<<<<< HEAD
        if (expr.tpe.hasAnnotation(MarkerCPSAdaptPlus)) {
          unit.warning(tree.pos, "expression " + expr + " of type " + expr.tpe + " is not expected to have a cps type")
          expr.setType(removeAllCPSAnnotations(expr.tpe))
=======
        if (hasPlusMarker(expr.tpe)) {
          unit.warning(tree.pos, "expression " + expr + " of type " + expr.tpe + " is not expected to have a cps type")
          expr modifyType removeAllCPSAnnotations
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        }

        // TODO: sanity check that types agree
      }

      (stms, expr)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def transInlineValue(tree: Tree, cpsA: CPSInfo): (List[Tree], Tree, CPSInfo) = {

      val (stms, expr, spc) = transValue(tree, cpsA, None) // never required to be cps

      getAnswerTypeAnn(expr.tpe) match {
        case spcVal @ Some(_) =>

          val valueTpe = removeAllCPSAnnotations(expr.tpe)

          val sym = currentOwner.newValue(tree.pos, unit.fresh.newName("tmp"))
                      .setInfo(valueTpe)
                      .setFlag(Flags.SYNTHETIC)
                      .setAnnotations(List(AnnotationInfo(MarkerCPSSym.tpe, Nil, Nil)))

<<<<<<< HEAD
=======
          new ChangeOwnerTraverser(currentOwner, sym) traverse expr

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          (stms ::: List(ValDef(sym, expr) setType(NoType)),
             Ident(sym) setType(valueTpe) setPos(tree.pos), linearize(spc, spcVal)(unit, tree.pos))

        case _ =>
          (stms, expr, spc)
      }

    }



    def transInlineStm(stm: Tree, cpsA: CPSInfo):  (List[Tree], CPSInfo) = {
      stm match {

        // TODO: what about DefDefs?
        // TODO: relation to top-level val def?
        // TODO: what about lazy vals?

        case tree @ ValDef(mods, name, tpt, rhs) =>
          val (stms, anfRhs, spc) = atOwner(tree.symbol) { transValue(rhs, cpsA, None) }
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          val tv = new ChangeOwnerTraverser(tree.symbol, currentOwner)
          stms.foreach(tv.traverse(_))

          // TODO: symbol might already have annotation. Should check conformance
          // TODO: better yet: do without annotations on symbols
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          val spcVal = getAnswerTypeAnn(anfRhs.tpe)
          if (spcVal.isDefined) {
              tree.symbol.setAnnotations(List(AnnotationInfo(MarkerCPSSym.tpe, Nil, Nil)))
          }
<<<<<<< HEAD
          
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          (stms:::List(treeCopy.ValDef(tree, mods, name, tpt, anfRhs)), linearize(spc, spcVal)(unit, tree.pos))

        case _ =>
          val (headStms, headExpr, headSpc) = transInlineValue(stm, cpsA)
          val valSpc = getAnswerTypeAnn(headExpr.tpe)
          (headStms:::List(headExpr), linearize(headSpc, valSpc)(unit, stm.pos))
      }
    }

    def transBlock(stms: List[Tree], expr: Tree, cpsA: CPSInfo, cpsR: CPSInfo): (List[Tree], Tree) = {
      stms match {
        case Nil =>
          transTailValue(expr, cpsA, cpsR)

        case stm::rest =>
          var (rest2, expr2) = (rest, expr)
          val (headStms, headSpc) = transInlineStm(stm, cpsA)
          val (restStms, restExpr) = transBlock(rest2, expr2, headSpc, cpsR)
          (headStms:::restStms, restExpr)
       }
    }


  }
}
