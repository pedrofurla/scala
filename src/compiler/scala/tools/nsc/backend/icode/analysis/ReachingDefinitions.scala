/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */


package scala.tools.nsc
package backend.icode
package analysis

import scala.collection.{ mutable, immutable }
import immutable.ListSet

/** Compute reaching definitions. We are only interested in reaching
 *  definitions for local variables, since values on the stack
 *  behave as-if in SSA form: the closest instruction which produces a value
 *  on the stack is a reaching definition.
 */
abstract class ReachingDefinitions {
  val global: Global
  import global._
  import icodes._

  /** The lattice for reaching definitions. Elements are
   *  a triple (local variable, basic block, index of instruction of that basic block)
   */
  object rdefLattice extends SemiLattice {
    type Definition = (Local, BasicBlock, Int)
<<<<<<< HEAD
    type Elem = IState[Set[Definition], Stack]
    type StackPos = Set[(BasicBlock, Int)]
    type Stack = List[StackPos]
    
=======
    type Elem       = IState[ListSet[Definition], Stack]
    type StackPos   = ListSet[(BasicBlock, Int)]
    type Stack      = List[StackPos]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def referenceEqualSet(name: String) = new ListSet[Definition] with ReferenceEquality {
      override def toString = "<" + name + ">"
    }

<<<<<<< HEAD
    val top: Elem = IState(referenceEqualSet("top"), Nil)
    val bottom: Elem = IState(referenceEqualSet("bottom"), Nil)

    /** The least upper bound is set inclusion for locals, and pairwise set inclusion for stacks. */
    def lub2(exceptional: Boolean)(a: Elem, b: Elem): Elem = 
      if (bottom == a) b
      else if (bottom == b) a
      else {
        val locals = a.vars ++ b.vars
        val stack =
          if (a.stack.isEmpty) b.stack
          else if (b.stack.isEmpty) a.stack
          else (a.stack, b.stack).zipped map (_ ++ _)
        
        IState(locals, stack)
        
        // val res = IState(locals, stack)
        // Console.println("\tlub2: " + a + ", " + b)
        // Console.println("\tis: " + res)
        // if (res._1 eq bottom._1) (new ListSet[Definition], Nil)
        // else res
        // res
      }
=======
    val top: Elem    = IState(referenceEqualSet("top"), Nil)
    val bottom: Elem = IState(referenceEqualSet("bottom"), Nil)

    /** The least upper bound is set inclusion for locals, and pairwise set inclusion for stacks. */
    def lub2(exceptional: Boolean)(a: Elem, b: Elem): Elem = {
      if (bottom == a) b
      else if (bottom == b) a
      else IState(a.vars ++ b.vars,
        if (a.stack.isEmpty) b.stack
        else if (b.stack.isEmpty) a.stack
        else {
          // !!! These stacks are with some frequency not of the same size.
          // I can't reverse engineer the logic well enough to say whether this
          // indicates a problem.  Even if it doesn't indicate a problem,
          // it'd be nice not to call zip with mismatched sequences because
          // it makes it harder to spot the real problems.
          val result = (a.stack, b.stack).zipped map (_ ++ _)
          if (settings.debug.value && (a.stack.length != b.stack.length))
            debugwarn("Mismatched stacks in ReachingDefinitions#lub2: " + a.stack + ", " + b.stack + ", returning " + result)
          result
        }
      )
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  class ReachingDefinitionsAnalysis extends DataFlowAnalysis[rdefLattice.type] {
    type P = BasicBlock
    val lattice = rdefLattice
<<<<<<< HEAD
    import lattice.Definition
    import lattice.Stack
    import lattice.Elem

    var method: IMethod = _

    val gen: mutable.Map[BasicBlock, Set[Definition]] = new mutable.HashMap()
    val kill: mutable.Map[BasicBlock, Set[Local]]     = new mutable.HashMap()
    val drops: mutable.Map[BasicBlock, Int]           = new mutable.HashMap()
    val outStack: mutable.Map[BasicBlock, Stack]      = new mutable.HashMap()

    def init(m: IMethod) {
      this.method = m
      gen.clear;   kill.clear
      drops.clear; outStack.clear

      for (b <- m.code.blocks.toList;
           (g, k) = genAndKill(b);
           (d, st) = dropsAndGen(b)) {
=======
    import lattice.{ Definition, Stack, Elem, StackPos }
    var method: IMethod = _

    val gen      = mutable.Map[BasicBlock, ListSet[Definition]]()
    val kill     = mutable.Map[BasicBlock, ListSet[Local]]()
    val drops    = mutable.Map[BasicBlock, Int]()
    val outStack = mutable.Map[BasicBlock, Stack]()

    def init(m: IMethod) {
      this.method = m

      gen.clear()
      kill.clear()
      drops.clear()
      outStack.clear()

      for (b <- m.code.blocks.toList; (g, k) = genAndKill(b); (d, st) = dropsAndGen(b)) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        gen  += (b -> g)
        kill += (b -> k)
        drops += (b -> d)
        outStack += (b -> st)
      }

      init {
        worklist ++= m.code.blocks.toList
        m.code.blocks.foreach { b =>
          in(b)  = lattice.bottom
          out(b) = lattice.bottom
<<<<<<< HEAD
        } 
        m.exh foreach { e =>
          in(e.startBlock) = lattice.IState(new ListSet[Definition], List(new ListSet[(BasicBlock, Int)]))
        }
        
      }
    }
    
    import opcodes._
    
    def genAndKill(b: BasicBlock): (Set[Definition], Set[Local]) = {
      var genSet: Set[Definition] = new immutable.HashSet
      var killSet: Set[Local] = new immutable.HashSet
      for ((i, idx) <- b.toList.zipWithIndex) i match {
        case STORE_LOCAL(local) => 
=======
        }
        m.exh foreach { e =>
          in(e.startBlock) = lattice.IState(new ListSet[Definition], List(new StackPos))
        }
      }
    }

    import opcodes._

    def genAndKill(b: BasicBlock): (ListSet[Definition], ListSet[Local]) = {
      var genSet  = ListSet[Definition]()
      var killSet = ListSet[Local]()
      for ((i, idx) <- b.toList.zipWithIndex) i match {
        case STORE_LOCAL(local) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          killSet = killSet + local
          genSet  = updateReachingDefinition(b, idx, genSet)
        case _ => ()
      }
      (genSet, killSet)
    }
<<<<<<< HEAD
    
    private def dropsAndGen(b: BasicBlock): (Int, List[Set[(BasicBlock, Int)]]) = {
      var depth = 0
      var drops = 0
      var stackOut: List[Set[(BasicBlock, Int)]] = Nil
      
=======

    private def dropsAndGen(b: BasicBlock): (Int, Stack) = {
      var depth, drops = 0
      var stackOut: Stack = Nil

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      for ((instr, idx) <- b.toList.zipWithIndex) {
        instr match {
          case LOAD_EXCEPTION(_)            => ()
          case _ if instr.consumed > depth  =>
            drops += (instr.consumed - depth)
            depth = 0
            stackOut = Nil
          case _ =>
            stackOut = stackOut.drop(instr.consumed)
            depth -= instr.consumed
        }
        var prod = instr.produced
<<<<<<< HEAD
        depth = depth + prod
        while (prod > 0) {
          stackOut = collection.immutable.Set((b, idx)) :: stackOut
          prod = prod - 1
=======
        depth += prod
        while (prod > 0) {
          stackOut ::= ListSet((b, idx))
          prod -= 1
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        }
      }
//      Console.println("drops(" + b + ") = " + drops)
//      Console.println("stackout(" + b + ") = " + stackOut)
      (drops, stackOut)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def run() {
      forwardAnalysis(blockTransfer)
      if (settings.debug.value) {
        linearizer.linearize(method).foreach(b => if (b != method.code.startBlock)
          assert(lattice.bottom != in(b),
            "Block " + b + " in " + this.method + " has input equal to bottom -- not visited? " + in(b)
                 + ": bot: " + lattice.bottom
                 + "\nin(b) == bottom: " + (in(b) == lattice.bottom)
                 + "\nbottom == in(b): " + (lattice.bottom == in(b))));
      }
    }

    import opcodes._
    import lattice.IState
<<<<<<< HEAD
    def updateReachingDefinition(b: BasicBlock, idx: Int, rd: Set[Definition]): Set[Definition] = {
=======
    def updateReachingDefinition(b: BasicBlock, idx: Int, rd: ListSet[Definition]): ListSet[Definition] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val STORE_LOCAL(local) = b(idx)
      var tmp = local
      (rd filter { case (l, _, _) => l != tmp }) + ((tmp, b, idx))
    }
<<<<<<< HEAD
    
    private def blockTransfer(b: BasicBlock, in: lattice.Elem): lattice.Elem = {
      var locals: Set[Definition] = (in.vars filter { case (l, _, _) => !kill(b)(l) }) ++ gen(b)
      if (locals eq lattice.bottom.vars) locals = new ListSet[Definition]
      IState(locals, outStack(b) ::: in.stack.drop(drops(b)))
    }
    
=======

    private def blockTransfer(b: BasicBlock, in: lattice.Elem): lattice.Elem = {
      var locals: ListSet[Definition] = (in.vars filter { case (l, _, _) => !kill(b)(l) }) ++ gen(b)
      if (locals eq lattice.bottom.vars) locals = new ListSet[Definition]
      IState(locals, outStack(b) ::: in.stack.drop(drops(b)))
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Return the reaching definitions corresponding to the point after idx. */
    def interpret(b: BasicBlock, idx: Int, in: lattice.Elem): Elem = {
      var locals = in.vars
      var stack  = in.stack
<<<<<<< HEAD
      val instr = b(idx)
=======
      val instr  = b(idx)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      instr match {
        case STORE_LOCAL(l1) =>
          locals = updateReachingDefinition(b, idx, locals)
          stack = stack.drop(instr.consumed)
        case LOAD_EXCEPTION(_) =>
          stack = Nil
        case _ =>
          stack = stack.drop(instr.consumed)
      }

      var prod = instr.produced
      while (prod > 0) {
<<<<<<< HEAD
        stack = collection.immutable.Set((b, idx)) :: stack
=======
        stack ::= ListSet((b, idx))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        prod -= 1
      }

      IState(locals, stack)
    }

    /** Return the instructions that produced the 'm' elements on the stack, below given 'depth'.
     *  for instance, findefs(bb, idx, 1, 1) returns the instructions that might have produced the
<<<<<<< HEAD
     *  value found below the topmost element of the stack. 
     */
    def findDefs(bb: BasicBlock, idx: Int, m: Int, depth: Int): List[(BasicBlock, Int)] = if (idx > 0) {
      assert(bb.closed)
=======
     *  value found below the topmost element of the stack.
     */
    def findDefs(bb: BasicBlock, idx: Int, m: Int, depth: Int): List[(BasicBlock, Int)] = if (idx > 0) {
      assert(bb.closed, bb)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      var instrs = bb.getArray
      var res: List[(BasicBlock, Int)] = Nil
      var i = idx
      var n = m
      var d = depth
      // "I look for who produced the 'n' elements below the 'd' topmost slots of the stack"
      while (n > 0 && i > 0) {
        i -= 1
        val prod = instrs(i).produced
        if (prod > d) {
          res = (bb, i) :: res
          n   = n - (prod - d)
          instrs(i) match {
            case LOAD_EXCEPTION(_)  => ()
            case _                  => d = instrs(i).consumed
          }
        } else {
          d -= prod
          d += instrs(i).consumed
        }
      }
<<<<<<< HEAD
      
      if (n > 0) { 
=======

      if (n > 0) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val stack = this.in(bb).stack
        assert(stack.length >= n, "entry stack is too small, expected: " + n + " found: " + stack)
        stack.drop(d).take(n) foreach { defs =>
          res = defs.toList ::: res
        }
      }
      res
    } else {
      val stack = this.in(bb).stack
      assert(stack.length >= m, "entry stack is too small, expected: " + m + " found: " + stack)
      stack.drop(depth).take(m) flatMap (_.toList)
    }

    /** Return the definitions that produced the topmost 'm' elements on the stack,
     *  and that reach the instruction at index 'idx' in basic block 'bb'.
     */
<<<<<<< HEAD
    def findDefs(bb: BasicBlock, idx: Int, m: Int): List[(BasicBlock, Int)] = 
=======
    def findDefs(bb: BasicBlock, idx: Int, m: Int): List[(BasicBlock, Int)] =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      findDefs(bb, idx, m, 0)

    override def toString: String = {
      method.code.blocks map { b =>
        "  entry(%s) = %s\n".format(b, in(b)) +
        "   exit(%s) = %s\n".format(b, out(b))
      } mkString ("ReachingDefinitions {\n", "\n", "\n}")
    }
  }
}
