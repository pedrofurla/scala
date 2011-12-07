 /* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Iulian Dragos
 */

package scala.tools.nsc
package backend.opt

import scala.tools.nsc.backend.icode.analysis.LubException
import scala.tools.nsc.symtab._

/**
 *  @author Iulian Dragos
 */
abstract class ClosureElimination extends SubComponent {
  import global._
  import icodes._
  import icodes.opcodes._

  val phaseName = "closelim"

  /** Create a new phase */
  override def newPhase(p: Phase) = new ClosureEliminationPhase(p)

  /** A simple peephole optimizer. */
  val peephole = new PeepholeOpt {

    def peep(bb: BasicBlock, i1: Instruction, i2: Instruction) = (i1, i2) match {
<<<<<<< HEAD
      case (CONSTANT(c), DROP(_)) => 
=======
      case (CONSTANT(c), DROP(_)) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (c.tag == UnitTag) Some(List(i2)) else Some(Nil)

      case (LOAD_LOCAL(x), STORE_LOCAL(y)) =>
        if (x eq y) Some(Nil) else None

      case (STORE_LOCAL(x), LOAD_LOCAL(y)) if (x == y) =>
        var liveOut = liveness.out(bb)
        if (!liveOut(x)) {
          log("store/load to a dead local? " + x)
          val instrs = bb.getArray
          var idx = instrs.length - 1
          while (idx > 0 && (instrs(idx) ne i2)) {
            liveOut = liveness.interpret(liveOut, instrs(idx))
            idx -= 1
          }
          if (!liveOut(x)) {
            log("removing dead store/load " + x)
            Some(Nil)
          } else None
        } else
          Some(List(DUP(x.kind), STORE_LOCAL(x)))

      case (LOAD_LOCAL(_), DROP(_)) | (DUP(_), DROP(_)) =>
        Some(Nil)

      case (BOX(t1), UNBOX(t2)) if (t1 == t2) =>
        Some(Nil)

      case (LOAD_FIELD(sym, isStatic), DROP(_)) if !sym.hasAnnotation(definitions.VolatileAttr) =>
        if (isStatic)
          Some(Nil)
        else
          Some(DROP(REFERENCE(definitions.ObjectClass)) :: Nil)

      case _ => None
    }
  }

  /** The closure elimination phase.
   */
  class ClosureEliminationPhase(prev: Phase) extends ICodePhase(prev) {

    def name = phaseName
    val closser = new ClosureElim

    override def apply(c: IClass): Unit =
      closser analyzeClass c
  }

<<<<<<< HEAD
  /** 
   * Remove references to the environment through fields of a closure object. 
   * This has to be run after an 'apply' method has been inlined, but it still 
=======
  /**
   * Remove references to the environment through fields of a closure object.
   * This has to be run after an 'apply' method has been inlined, but it still
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * references the closure object.
   *
   */
  class ClosureElim {
    def analyzeClass(cls: IClass): Unit = if (settings.Xcloselim.value) {
<<<<<<< HEAD
      cls.methods foreach { m => 
=======
      cls.methods foreach { m =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        analyzeMethod(m)
        peephole(m)
     }}

    val cpp = new copyPropagation.CopyAnalysis

    import copyPropagation._

    /* Some embryonic copy propagation. */
    def analyzeMethod(m: IMethod): Unit = try {if (m.code ne null) {
      log("Analyzing " + m)
      cpp.init(m)
      cpp.run

      for (bb <- linearizer.linearize(m)) {
        var info = cpp.in(bb)
        debuglog("Cpp info at entry to block " + bb + ": " + info)

        for (i <- bb) {
          i match {
            case LOAD_LOCAL(l) if info.bindings isDefinedAt LocalVar(l) =>
              val t = info.getBinding(l)
              t match {
                case Deref(LocalVar(_)) | Deref(This) | Const(_) =>
                  bb.replaceInstruction(i, valueToInstruction(t));
                  log("replaced " + i + " with " + t)

<<<<<<< HEAD
                case _ => 
=======
                case _ =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                  bb.replaceInstruction(i, LOAD_LOCAL(info.getAlias(l)))
                  log("replaced " + i + " with " + info.getAlias(l))

              }

            case LOAD_FIELD(f, false) /* if accessible(f, m.symbol) */ =>
              def replaceFieldAccess(r: Record) {
                val Record(cls, bindings) = r
                info.getFieldNonRecordValue(r, f) match {
                	case Some(v) =>
<<<<<<< HEAD
                		bb.replaceInstruction(i, 
=======
                		bb.replaceInstruction(i,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                				DROP(REFERENCE(cls)) :: valueToInstruction(v) :: Nil);
                		log("Replaced " + i + " with " + info.getFieldNonRecordValue(r, f));
                	case None =>
                }
              }

              info.stack(0) match {
                case r @ Record(_, bindings) if bindings isDefinedAt f =>
                  replaceFieldAccess(r)

                case Deref(LocalVar(l)) =>
                  info.getBinding(l) match {
                    case r @ Record(_, bindings) if bindings isDefinedAt f =>
                      replaceFieldAccess(r)
<<<<<<< HEAD
                    case _ => 
=======
                    case _ =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                  }
                case Deref(Field(r1, f1)) =>
                  info.getFieldValue(r1, f1) match {
                    case Some(r @ Record(_, bindings)) if bindings isDefinedAt f =>
                      replaceFieldAccess(r)
<<<<<<< HEAD
                    case _ => 
                  }

                case _ => 
=======
                    case _ =>
                  }

                case _ =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              }

            case UNBOX(_) =>
              info.stack match {
                case Deref(LocalVar(loc1)) :: _ if info.bindings isDefinedAt LocalVar(loc1) =>
                  val value = info.getBinding(loc1)
                  value match {
                    case Boxed(LocalVar(loc2)) =>
                      bb.replaceInstruction(i, DROP(icodes.ObjectReference) :: valueToInstruction(info.getBinding(loc2)) :: Nil)
                      log("replaced " + i + " with " + info.getBinding(loc2))
                    case _ =>
                      ()
                  }
                case Boxed(LocalVar(loc1)) :: _ =>
                  val loc2 = info.getAlias(loc1)
                  bb.replaceInstruction(i, DROP(icodes.ObjectReference) :: valueToInstruction(Deref(LocalVar(loc2))) :: Nil)
                  log("replaced " + i + " with " + LocalVar(loc2))
                case _ =>
              }

            case _ =>
          }
          info = cpp.interpret(info, i)
        }
      }
    }} catch {
<<<<<<< HEAD
      case e: LubException => 
=======
      case e: LubException =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        Console.println("In method: " + m)
        Console.println(e)
        e.printStackTrace
    }

    /* Partial mapping from values to instructions that load them. */
    def valueToInstruction(v: Value): Instruction = (v: @unchecked) match {
<<<<<<< HEAD
      case Deref(LocalVar(v)) => 
=======
      case Deref(LocalVar(v)) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        LOAD_LOCAL(v)
      case Const(k) =>
        CONSTANT(k)
      case Deref(This) =>
        THIS(definitions.ObjectClass)
      case Boxed(LocalVar(v)) =>
        LOAD_LOCAL(v)
    }

    /** is field 'f' accessible from method 'm'? */
<<<<<<< HEAD
    def accessible(f: Symbol, m: Symbol): Boolean = 
=======
    def accessible(f: Symbol, m: Symbol): Boolean =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      f.isPublic || (f.isProtected && (f.enclosingPackageClass == m.enclosingPackageClass))
  } /* class ClosureElim */


  /** Peephole optimization. */
  abstract class PeepholeOpt {

    private var method: IMethod = null

    /** Concrete implementations will perform their optimizations here */
    def peep(bb: BasicBlock, i1: Instruction, i2: Instruction): Option[List[Instruction]]

    var liveness: global.icodes.liveness.LivenessAnalysis = null

    def apply(m: IMethod): Unit = if (m.code ne null) {
      method = m
      liveness = new global.icodes.liveness.LivenessAnalysis
      liveness.init(m)
      liveness.run
<<<<<<< HEAD
      for (b <- m.code.blocks) 
=======
      for (b <- m.code.blocks)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        transformBlock(b)
    }

    def transformBlock(b: BasicBlock): Unit = if (b.size >= 2) {
      var newInstructions: List[Instruction] = Nil

      newInstructions = b.toList

      var redo = false
      do {
        var h = newInstructions.head
        var t = newInstructions.tail
        var seen: List[Instruction] = Nil
        redo = false

        while (t != Nil) {
          peep(b, h, t.head) match {
            case Some(newInstrs) =>
<<<<<<< HEAD
              newInstructions = seen.reverse ::: newInstrs ::: t.tail;
=======
              newInstructions = seen reverse_::: newInstrs ::: t.tail
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              redo = true
            case None =>
            	()
          }
          seen = h :: seen
          h = t.head
          t = t.tail
        }
      } while (redo);
      b fromList newInstructions
    }
  }

} /* class ClosureElimination */
