/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc
package ast

import java.io.{ OutputStream, PrintWriter, StringWriter, Writer }
import symtab.Flags._
import symtab.SymbolTable

trait TreePrinters extends reflect.internal.TreePrinters { this: Global =>

  import treeInfo.{ IsTrue, IsFalse }
<<<<<<< HEAD
  
  class TreePrinter(out: PrintWriter) extends super.TreePrinter(out) {

    override def print(tree: Tree) {
      printPosition(tree)
      printRaw(
        if (tree.isDef && tree.symbol != NoSymbol && tree.symbol.isInitialized) {
          tree match {
            case ClassDef(_, _, _, impl @ Template(ps, emptyValDef, body)) 
            if (tree.symbol.thisSym != tree.symbol) => 
              ClassDef(tree.symbol, Template(ps, ValDef(tree.symbol.thisSym), body))
            case ClassDef(_, _, _, impl)           => ClassDef(tree.symbol, impl)
            case ModuleDef(_, _, impl)             => ModuleDef(tree.symbol, impl)
            case ValDef(_, _, _, rhs)              => ValDef(tree.symbol, rhs)
            case DefDef(_, _, _, vparamss, _, rhs) => DefDef(tree.symbol, vparamss, rhs)
            case TypeDef(_, _, _, rhs)             => TypeDef(tree.symbol, rhs)
            case _ => tree
          }
        } else tree)
    }

    def print(unit: CompilationUnit) {
      print("// Scala source: " + unit.source + "\n")
      if (unit.body == null) print("<null>")
      else { print(unit.body); println() }

      println()
      flush()
=======

  class TreePrinter(out: PrintWriter) extends super.TreePrinter(out) {

    override def print(args: Any*): Unit = args foreach {
      case tree: Tree =>
        printPosition(tree)
        printTree(
            if (tree.isDef && tree.symbol != NoSymbol && tree.symbol.isInitialized) {
              tree match {
                case ClassDef(_, _, _, impl @ Template(ps, emptyValDef, body))
                if (tree.symbol.thisSym != tree.symbol) =>
                  ClassDef(tree.symbol, Template(ps, ValDef(tree.symbol.thisSym), body))
                case ClassDef(_, _, _, impl)           => ClassDef(tree.symbol, impl)
                case ModuleDef(_, _, impl)             => ModuleDef(tree.symbol, impl)
                case ValDef(_, _, _, rhs)              => ValDef(tree.symbol, rhs)
                case DefDef(_, _, _, vparamss, _, rhs) => DefDef(tree.symbol, vparamss, rhs)
                case TypeDef(_, _, _, rhs)             => TypeDef(tree.symbol, rhs)
                case _ => tree
              }
            } else tree)
      case unit: CompilationUnit =>
        print("// Scala source: " + unit.source + "\n")
        if (unit.body == null) print("<null>")
        else { print(unit.body); println() }
        println()
        out.flush()
      case arg =>
        super.print(arg)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
  }

  // overflow cases missing from TreePrinter in reflect.api
<<<<<<< HEAD
  override def xprintRaw(treePrinter: super.TreePrinter, tree: Tree) = tree match {
=======
  override def xprintTree(treePrinter: super.TreePrinter, tree: Tree) = tree match {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    case DocDef(comment, definition) =>
      treePrinter.print(comment.raw)
      treePrinter.println()
      treePrinter.print(definition)

    case AssignOrNamedArg(lhs, rhs) =>
<<<<<<< HEAD
      treePrinter.print(lhs)
      treePrinter.print(" = ")
      treePrinter.print(rhs)

    case TypeTreeWithDeferredRefCheck() =>
      treePrinter.print("<tree with deferred refcheck>")
      
    case SelectFromArray(qualifier, name, _) =>
      treePrinter.print(qualifier)
      treePrinter.print(".<arr>")
      treePrinter.print(treePrinter.symName(tree, name))  
      
    case _ =>
      super.xprintRaw(treePrinter, tree)
  }  
  
  /** A tree printer which is stingier about vertical whitespace and unnecessary
   *  punctuation than the standard one.
   */
  class CompactTreePrinter(out: PrintWriter) extends TreePrinter(out) {    
    override def printRow(ts: List[Tree], start: String, sep: String, end: String) {
      print(start)
      printSeq(ts)(print)(print(sep))
      print(end)
    }
    
=======
      treePrinter.print(lhs, " = ", rhs)

    case TypeTreeWithDeferredRefCheck() =>
      treePrinter.print("<tree with deferred refcheck>")

    case SelectFromArray(qualifier, name, _) =>
      treePrinter.print(qualifier, ".<arr>", treePrinter.symName(tree, name))

    case _ =>
      super.xprintTree(treePrinter, tree)
  }

  /** A tree printer which is stingier about vertical whitespace and unnecessary
   *  punctuation than the standard one.
   */
  class CompactTreePrinter(out: PrintWriter) extends TreePrinter(out) {
    override def printRow(ts: List[Tree], start: String, sep: String, end: String) {
      print(start)
      printSeq(ts)(print(_))(print(sep))
      print(end)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // drill down through Blocks and pull out the real statements.
    def allStatements(t: Tree): List[Tree] = t match {
      case Block(stmts, expr) => (stmts flatMap allStatements) ::: List(expr)
      case _                  => List(t)
    }

    def printLogicalOr(t1: (Tree, Boolean), t2: (Tree, Boolean)) =
      printLogicalOp(t1, t2, "||")

    def printLogicalAnd(t1: (Tree, Boolean), t2: (Tree, Boolean)) =
      printLogicalOp(t1, t2, "&&")

    def printLogicalOp(t1: (Tree, Boolean), t2: (Tree, Boolean), op: String) = {
      def maybenot(tvalue: Boolean) = if (tvalue) "" else "!"
<<<<<<< HEAD
      
      print("%s(" format maybenot(t1._2))
      printRaw(t1._1)
      print(") %s %s(".format(op, maybenot(t2._2)))
      printRaw(t2._1)
      print(")")
    }

    override def printRaw(tree: Tree): Unit = {
      // routing supercalls through this for debugging ease
      def s() = super.printRaw(tree)
=======

      print("%s(" format maybenot(t1._2))
      printTree(t1._1)
      print(") %s %s(".format(op, maybenot(t2._2)))
      printTree(t2._1)
      print(")")
    }

    override def printTree(tree: Tree): Unit = {
      // routing supercalls through this for debugging ease
      def s() = super.printTree(tree)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

      tree match {
        // labels used for jumps - does not map to valid scala code
        case LabelDef(name, params, rhs) =>
          print("labeldef %s(%s) = ".format(name, params mkString ","))
<<<<<<< HEAD
          printRaw(rhs)
          
        case Ident(name) =>
          print(decodedSymName(tree, name))
                  
=======
          printTree(rhs)

        case Ident(name) =>
          print(decodedSymName(tree, name))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // target.method(arg) ==> target method arg
        case Apply(Select(target, method), List(arg)) =>
          if (method.decode.toString == "||")
            printLogicalOr(target -> true, arg -> true)
          else if (method.decode.toString == "&&")
            printLogicalAnd(target -> true, arg -> true)
          else (target, arg) match {
<<<<<<< HEAD
            case (_: Ident, _: Literal | _: Ident)  => 
              printRaw(target)
              print(" ")
              printRaw(Ident(method))
              print(" ")
              printRaw(arg)
=======
            case (_: Ident, _: Literal | _: Ident)  =>
              printTree(target)
              print(" ")
              printTree(Ident(method))
              print(" ")
              printTree(arg)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            case _                        => s()
          }

        // target.unary_! ==> !target
        case Select(qualifier, name) if (name.decode startsWith "unary_") =>
          print(name.decode drop 6)
<<<<<<< HEAD
          printRaw(qualifier)

        case Select(qualifier, name) =>
          printRaw(qualifier)
=======
          printTree(qualifier)

        case Select(qualifier, name) =>
          printTree(qualifier)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          print(".")
          print(quotedName(name, true))

        // target.toString() ==> target.toString
<<<<<<< HEAD
        case Apply(fn, Nil)   => printRaw(fn)

        // if a Block only continues one actual statement, just print it.
        case Block(stats, expr) =>          
          allStatements(tree) match {
            case List(x)            => printRaw(x)
=======
        case Apply(fn, Nil)   => printTree(fn)

        // if a Block only continues one actual statement, just print it.
        case Block(stats, expr) =>
          allStatements(tree) match {
            case List(x)            => printTree(x)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            case xs                 => s()
          }

        // We get a lot of this stuff
<<<<<<< HEAD
        case If( IsTrue(), x, _)        => printRaw(x)
        case If(IsFalse(), _, x)        => printRaw(x)
=======
        case If( IsTrue(), x, _)        => printTree(x)
        case If(IsFalse(), _, x)        => printTree(x)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case If(cond,  IsTrue(), elsep)   =>  printLogicalOr(cond -> true, elsep -> true)
        case If(cond, IsFalse(), elsep)   => printLogicalAnd(cond -> false, elsep -> true)
        case If(cond,  thenp, IsTrue())   =>  printLogicalOr(cond -> false, thenp -> true)
        case If(cond,  thenp, IsFalse())  => printLogicalAnd(cond -> true, thenp -> true)

        // If thenp or elsep has only one statement, it doesn't need more than one line.
        case If(cond, thenp, elsep) =>
          def ifIndented(x: Tree) = {
<<<<<<< HEAD
            indent ; println() ; printRaw(x) ; undent
          }
        
          val List(thenStmts, elseStmts) = List(thenp, elsep) map allStatements
          print("if ("); print(cond); print(") ")
          
          thenStmts match {
            case List(x: If)  => ifIndented(x)
            case List(x)      => printRaw(x)
            case _            => printRaw(thenp)
          }
          
          if (elseStmts.nonEmpty) {
            print(" else")
            indent ; println() 
            elseStmts match {
              case List(x)  => printRaw(x)
              case _        => printRaw(elsep)
=======
            indent ; println() ; printTree(x) ; undent
          }

          val List(thenStmts, elseStmts) = List(thenp, elsep) map allStatements
          print("if ("); print(cond); print(") ")

          thenStmts match {
            case List(x: If)  => ifIndented(x)
            case List(x)      => printTree(x)
            case _            => printTree(thenp)
          }

          if (elseStmts.nonEmpty) {
            print(" else")
            indent ; println()
            elseStmts match {
              case List(x)  => printTree(x)
              case _        => printTree(elsep)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            }
            undent ; println()
          }
        case _        => s()
      }
<<<<<<< HEAD
    }      
  }
  
=======
    }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** This must guarantee not to force any evaluation, so we can learn
   *  a little bit about trees in the midst of compilation without altering
   *  the natural course of events.
   */
  class SafeTreePrinter(out: PrintWriter) extends TreePrinter(out) {
<<<<<<< HEAD
    override def print(tree: Tree) {
      printPosition(tree)
      printRaw(tree)
    }
    private def default(t: Tree) = t.getClass.getName.reverse.takeWhile(_ != '.').reverse
    private def params(trees: List[Tree]): String = trees map safe mkString ", "
    
=======

    private def default(t: Tree) = t.getClass.getName.reverse.takeWhile(_ != '.').reverse
    private def params(trees: List[Tree]): String = trees map safe mkString ", "

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def safe(name: Name): String = name.decode
    private def safe(tree: Tree): String = tree match {
      case Apply(fn, args)        => "%s(%s)".format(safe(fn), params(args))
      case Select(qual, name)     => safe(qual) + "." + safe(name)
      case This(qual)             => safe(qual) + ".this"
      case Ident(name)            => safe(name)
      case Literal(value)         => value.stringValue
      case _                      => "(?: %s)".format(default(tree))
    }
<<<<<<< HEAD
    
    override def printRaw(tree: Tree) { print(safe(tree)) }
  }
      
=======

    override def printTree(tree: Tree) { print(safe(tree)) }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class TreeMatchTemplate {
    // non-trees defined in Trees
    //
    // case class ImportSelector(name: Name, namePos: Int, rename: Name, renamePos: Int)
    // case class Modifiers(flags: Long, privateWithin: Name, annotations: List[Tree], positions: Map[Long, Position])
    //
    def apply(t: Tree): Unit = t match {
      // eliminated by typer
      case Annotated(annot, arg)  =>
      case AssignOrNamedArg(lhs, rhs) =>
      case DocDef(comment, definition) =>
      case Import(expr, selectors) =>
<<<<<<< HEAD
      
      // eliminated by refchecks
      case ModuleDef(mods, name, impl) =>
      case TypeTreeWithDeferredRefCheck() =>
      
      // eliminated by erasure
      case TypeDef(mods, name, tparams, rhs) =>
      case Typed(expr, tpt) =>
      
      // eliminated by cleanup
      case ApplyDynamic(qual, args) =>
      
=======

      // eliminated by refchecks
      case ModuleDef(mods, name, impl) =>
      case TypeTreeWithDeferredRefCheck() =>

      // eliminated by erasure
      case TypeDef(mods, name, tparams, rhs) =>
      case Typed(expr, tpt) =>

      // eliminated by cleanup
      case ApplyDynamic(qual, args) =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // eliminated by explicitouter
      case Alternative(trees) =>
      case Bind(name, body) =>
      case CaseDef(pat, guard, body) =>
      case Star(elem) =>
      case UnApply(fun, args) =>
<<<<<<< HEAD
      
      // eliminated by lambdalift
      case Function(vparams, body) =>
      
=======

      // eliminated by lambdalift
      case Function(vparams, body) =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // eliminated by uncurry
      case AppliedTypeTree(tpt, args) =>
      case CompoundTypeTree(templ) =>
      case ExistentialTypeTree(tpt, whereClauses) =>
      case SelectFromTypeTree(qual, selector) =>
      case SingletonTypeTree(ref) =>
      case TypeBoundsTree(lo, hi) =>
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // survivors
      case Apply(fun, args) =>
      case ArrayValue(elemtpt, trees) =>
      case Assign(lhs, rhs) =>
      case Block(stats, expr) =>
      case ClassDef(mods, name, tparams, impl) =>
      case DefDef(mods, name, tparams, vparamss, tpt, rhs)  =>
      case EmptyTree =>
      case Ident(name) =>
      case If(cond, thenp, elsep) =>
      case LabelDef(name, params, rhs) =>
      case Literal(value) =>
      case Match(selector, cases) =>
      case New(tpt) =>
      case PackageDef(pid, stats) =>
      case Return(expr) =>
      case Select(qualifier, selector) =>
      case Super(qual, mix) =>
      case Template(parents, self, body) =>
      case This(qual) =>
      case Throw(expr) =>
      case Try(block, catches, finalizer) =>
      case TypeApply(fun, args) =>
      case TypeTree() =>
      case ValDef(mods, name, tpt, rhs) =>
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // missing from the Trees comment
      case Parens(args) =>                          // only used during parsing
      case SelectFromArray(qual, name, erasure) =>  // only used during erasure
    }
  }
<<<<<<< HEAD
  
  private def asStringInternal(t: Tree, f: PrintWriter => TreePrinter): String = {
    val buffer = new StringWriter()
    val printer = f(new PrintWriter(buffer))
    printer.print(t)
    printer.flush()
    buffer.toString
  }    
  def asString(t: Tree): String = asStringInternal(t, newStandardTreePrinter)
  def asCompactString(t: Tree): String = asStringInternal(t, newCompactTreePrinter)
=======

  def asString(t: Tree): String = show(t, newStandardTreePrinter)
  def asCompactString(t: Tree): String = show(t, newCompactTreePrinter)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  def newStandardTreePrinter(writer: PrintWriter): TreePrinter = new TreePrinter(writer)
  def newStandardTreePrinter(stream: OutputStream): TreePrinter = newStandardTreePrinter(new PrintWriter(stream))
  def newStandardTreePrinter(): TreePrinter = newStandardTreePrinter(new PrintWriter(ConsoleWriter))
<<<<<<< HEAD
  
  def newCompactTreePrinter(writer: PrintWriter): CompactTreePrinter = new CompactTreePrinter(writer)
  def newCompactTreePrinter(stream: OutputStream): CompactTreePrinter = newCompactTreePrinter(new PrintWriter(stream))
  def newCompactTreePrinter(): CompactTreePrinter = newCompactTreePrinter(new PrintWriter(ConsoleWriter))
  
  override def newTreePrinter(writer: PrintWriter): TreePrinter = 
=======

  def newCompactTreePrinter(writer: PrintWriter): CompactTreePrinter = new CompactTreePrinter(writer)
  def newCompactTreePrinter(stream: OutputStream): CompactTreePrinter = newCompactTreePrinter(new PrintWriter(stream))
  def newCompactTreePrinter(): CompactTreePrinter = newCompactTreePrinter(new PrintWriter(ConsoleWriter))

  override def newTreePrinter(writer: PrintWriter): TreePrinter =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    if (settings.Ycompacttrees.value) newCompactTreePrinter(writer)
    else newStandardTreePrinter(writer)
  override def newTreePrinter(stream: OutputStream): TreePrinter = newTreePrinter(new PrintWriter(stream))
  override def newTreePrinter(): TreePrinter = newTreePrinter(new PrintWriter(ConsoleWriter))
}
