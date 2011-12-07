/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect
package internal

import java.io.{ OutputStream, PrintWriter, StringWriter, Writer }
import Flags._

<<<<<<< HEAD
trait TreePrinters { self: SymbolTable =>
=======
trait TreePrinters extends api.TreePrinters { self: SymbolTable =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  //nsc import treeInfo.{ IsTrue, IsFalse }

  final val showOuterTests = false
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Adds backticks if the name is a scala keyword. */
  def quotedName(name: Name, decode: Boolean): String = {
    val s = if (decode) name.decode else name.toString
    val term = name.toTermName
    if (nme.keywords(term) && term != nme.USCOREkw) "`%s`" format s
    else s
  }
  def quotedName(name: Name): String = quotedName(name, false)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Turns a path into a String, introducing backquotes
   *  as necessary.
   */
  def backquotedPath(t: Tree): String = t match {
    case Select(qual, name) => "%s.%s".format(backquotedPath(qual), quotedName(name))
    case Ident(name)        => quotedName(name)
    case _                  => t.toString
  }
<<<<<<< HEAD
  
  class TreePrinter(out: PrintWriter) {
=======

  class TreePrinter(out: PrintWriter) extends super.TreePrinter {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    protected var indentMargin = 0
    protected val indentStep = 2
    protected var indentString = "                                        " // 40

<<<<<<< HEAD
    def flush() = out.flush()

    def indent() = indentMargin += indentStep
    def undent() = indentMargin -= indentStep    
    
    protected def doPrintPositions = settings.Xprintpos.value
=======
    typesPrinted = settings.printtypes.value
    uniqueIds = settings.uniqid.value
    protected def doPrintPositions = settings.Xprintpos.value

    def indent() = indentMargin += indentStep
    def undent() = indentMargin -= indentStep

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def printPosition(tree: Tree) = if (doPrintPositions) print(showPos(tree.pos))

    def println() {
      out.println()
      while (indentMargin > indentString.length())
        indentString += indentString
      if (indentMargin > 0)
        out.write(indentString, 0, indentMargin)
    }

    def printSeq[a](ls: List[a])(printelem: a => Unit)(printsep: => Unit) {
      ls match {
        case List() =>
        case List(x) => printelem(x)
        case x :: rest => printelem(x); printsep; printSeq(rest)(printelem)(printsep)
      }
    }

    def printColumn(ts: List[Tree], start: String, sep: String, end: String) {
      print(start); indent; println()
<<<<<<< HEAD
      printSeq(ts){print}{print(sep); println()}; undent; println(); print(end)
    }

    def printRow(ts: List[Tree], start: String, sep: String, end: String) {
      print(start); printSeq(ts){print}{print(sep)}; print(end)
=======
      printSeq(ts){print(_)}{print(sep); println()}; undent; println(); print(end)
    }

    def printRow(ts: List[Tree], start: String, sep: String, end: String) {
      print(start); printSeq(ts){print(_)}{print(sep)}; print(end)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    def printRow(ts: List[Tree], sep: String) { printRow(ts, "", sep, "") }

    def printTypeParams(ts: List[TypeDef]) {
      if (!ts.isEmpty) {
        print("["); printSeq(ts){ t =>
          printAnnotations(t)
          printParam(t)
        }{print(", ")}; print("]")
      }
    }

    def printValueParams(ts: List[ValDef]) {
      print("(")
      if (!ts.isEmpty) printFlags(ts.head.mods.flags & IMPLICIT, "")
      printSeq(ts){printParam}{print(", ")}
      print(")")
    }

    def printParam(tree: Tree) {
      tree match {
        case ValDef(mods, name, tp, rhs) =>
          printPosition(tree)
          printAnnotations(tree)
          print(symName(tree, name)); printOpt(": ", tp); printOpt(" = ", rhs)
        case TypeDef(mods, name, tparams, rhs) =>
          printPosition(tree)
          print(symName(tree, name))
          printTypeParams(tparams); print(rhs)
      }
    }

    def printBlock(tree: Tree) {
      tree match {
        case Block(_, _) =>
          print(tree)
        case _ =>
          printColumn(List(tree), "{", ";", "}")
      }
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def symFn[T](tree: Tree, f: Symbol => T, orElse: => T): T = tree.symbol match {
      case null | NoSymbol  => orElse
      case sym              => f(sym)
    }
    private def ifSym(tree: Tree, p: Symbol => Boolean) = symFn(tree, p, false)
<<<<<<< HEAD
    
    private def symNameInternal(tree: Tree, name: Name, decoded: Boolean): String = {
      def nameFn(sym: Symbol) = {
        val prefix = if (sym.isMixinConstructor) "/*%s*/".format(quotedName(sym.owner.name, decoded)) else ""
        prefix + tree.symbol.nameString
      }
      symFn(tree, nameFn, quotedName(name, decoded))
    }
    
=======

    private def symNameInternal(tree: Tree, name: Name, decoded: Boolean): String = {
      def nameFn(sym: Symbol) = {
        val prefix = if (sym.isMixinConstructor) "/*%s*/".format(quotedName(sym.owner.name, decoded)) else ""
        val suffix = if (uniqueIds) "#"+sym.id else ""
        prefix + tree.symbol.decodedName + suffix
      }
      symFn(tree, nameFn, quotedName(name, decoded))
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def decodedSymName(tree: Tree, name: Name) = symNameInternal(tree, name, true)
    def symName(tree: Tree, name: Name) = symNameInternal(tree, name, false)

    def printOpt(prefix: String, tree: Tree) {
<<<<<<< HEAD
      if (!tree.isEmpty) { print(prefix); print(tree) }
=======
      if (!tree.isEmpty) { print(prefix, tree) }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    def printModifiers(tree: Tree, mods: Modifiers): Unit = printFlags(
       if (tree.symbol == NoSymbol) mods.flags else tree.symbol.flags, "" + (
         if (tree.symbol == NoSymbol) mods.privateWithin
         else if (tree.symbol.hasAccessBoundary) tree.symbol.privateWithin.name
         else ""
      )
    )

    def printFlags(flags: Long, privateWithin: String) {
      var mask: Long = if (settings.debug.value) -1L else PrintableFlags
      val s = flagsToString(flags & mask, privateWithin)
      if (s != "") print(s + " ")
    }

    def printAnnotations(tree: Tree) {
<<<<<<< HEAD
      val annots =
        if (tree.symbol.hasAssignedAnnotations) tree.symbol.annotations
        else tree.asInstanceOf[MemberDef].mods.annotations
      
      annots foreach (annot => print("@"+annot+" "))
    }

    def print(str: String) { out.print(str) }
    def print(name: Name) { print(quotedName(name)) }

    private var currentOwner: Symbol = NoSymbol
    private var selectorType: Type = NoType

    def printRaw(tree: Tree) {
=======
      val annots = tree.symbol.annotations match {
        case Nil  => tree.asInstanceOf[MemberDef].mods.annotations
        case anns => anns
      }
      annots foreach (annot => print("@"+annot+" "))
    }

    private var currentOwner: Symbol = NoSymbol
    private var selectorType: Type = NoType

    def printTree(tree: Tree) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      tree match {
        case EmptyTree =>
          print("<empty>")

        case ClassDef(mods, name, tparams, impl) =>
          printAnnotations(tree)
          printModifiers(tree, mods)
          val word =
            if (mods.hasTraitFlag) "trait"
            else if (ifSym(tree, _.isModuleClass)) "object"
            else "class"
<<<<<<< HEAD
            
          print(word + " " + symName(tree, name))
          printTypeParams(tparams)
          print(if (mods.isDeferred) " <: " else " extends "); print(impl)

        case PackageDef(packaged, stats) =>
          printAnnotations(tree)
          print("package "); print(packaged); printColumn(stats, " {", ";", "}")

        case ModuleDef(mods, name, impl) =>
          printAnnotations(tree)
          printModifiers(tree, mods); print("object " + symName(tree, name))
          print(" extends "); print(impl)
=======

          print(word, " ", symName(tree, name))
          printTypeParams(tparams)
          print(if (mods.isDeferred) " <: " else " extends ", impl)

        case PackageDef(packaged, stats) =>
          printAnnotations(tree)
          print("package ", packaged); printColumn(stats, " {", ";", "}")

        case ModuleDef(mods, name, impl) =>
          printAnnotations(tree)
          printModifiers(tree, mods);
          print("object " + symName(tree, name), " extends ", impl)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case ValDef(mods, name, tp, rhs) =>
          printAnnotations(tree)
          printModifiers(tree, mods)
<<<<<<< HEAD
          print(if (mods.isMutable) "var " else "val ")
          print(symName(tree, name))
          printOpt(": ", tp)
          if (!mods.isDeferred) {
            print(" = ")
            if (rhs.isEmpty) print("_") else print(rhs)
          }
=======
          print(if (mods.isMutable) "var " else "val ", symName(tree, name))
          printOpt(": ", tp)
          if (!mods.isDeferred)
            print(" = ", if (rhs.isEmpty) "_" else rhs)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case DefDef(mods, name, tparams, vparamss, tp, rhs) =>
          printAnnotations(tree)
          printModifiers(tree, mods)
          print("def " + symName(tree, name))
          printTypeParams(tparams); vparamss foreach printValueParams
          printOpt(": ", tp); printOpt(" = ", rhs)

        case TypeDef(mods, name, tparams, rhs) =>
          if (mods hasFlag (PARAM | DEFERRED)) {
            printAnnotations(tree)
            printModifiers(tree, mods); print("type "); printParam(tree)
          } else {
            printAnnotations(tree)
            printModifiers(tree, mods); print("type " + symName(tree, name))
            printTypeParams(tparams); printOpt(" = ", rhs)
          }

        case LabelDef(name, params, rhs) =>
          print(symName(tree, name)); printRow(params, "(", ",", ")"); printBlock(rhs)

        case Import(expr, selectors) =>
          // Is this selector remapping a name (i.e, {name1 => name2})
          def isNotRemap(s: ImportSelector) : Boolean = (s.name == nme.WILDCARD || s.name == s.rename)
          def selectorToString(s: ImportSelector): String = {
            val from = quotedName(s.name)
            if (isNotRemap(s)) from
            else from + "=>" + quotedName(s.rename)
          }
<<<<<<< HEAD
          print("import "); print(backquotedPath(expr))
          print(".")
          selectors match {
            case List(s) => 
              // If there is just one selector and it is not remapping a name, no braces are needed
              if (isNotRemap(s)) {
                print(selectorToString(s)) 
              } else { 
                print("{"); print(selectorToString(s)); print("}")
              }
              // If there is more than one selector braces are always needed
            case many =>        
=======
          print("import ", backquotedPath(expr), ".")
          selectors match {
            case List(s) =>
              // If there is just one selector and it is not remapping a name, no braces are needed
              if (isNotRemap(s)) print(selectorToString(s))
              else print("{", selectorToString(s), "}")
              // If there is more than one selector braces are always needed
            case many =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              print(many.map(selectorToString).mkString("{", ", ", "}"))
          }

       case Template(parents, self, body) =>
          val currentOwner1 = currentOwner
          if (tree.symbol != NoSymbol) currentOwner = tree.symbol.owner
          printRow(parents, " with ")
          if (!body.isEmpty) {
            if (self.name != nme.WILDCARD) {
<<<<<<< HEAD
              print(" { "); print(self.name); printOpt(": ", self.tpt); print(" => ") 
            } else if (!self.tpt.isEmpty) {
              print(" { _ : "); print(self.tpt); print(" => ") 
=======
              print(" { ", self.name); printOpt(": ", self.tpt); print(" => ")
            } else if (!self.tpt.isEmpty) {
              print(" { _ : ", self.tpt, " => ")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            } else {
              print(" {")
            }
            printColumn(body, "", ";", "}")
          }
          currentOwner = currentOwner1

        case Block(stats, expr) =>
          printColumn(stats ::: List(expr), "{", ";", "}")

        case Match(selector, cases) =>
          val selectorType1 = selectorType
          selectorType = selector.tpe
          print(selector); printColumn(cases, " match {", "", "}")
          selectorType = selectorType1

        case CaseDef(pat, guard, body) =>
          print("case ")
          def patConstr(pat: Tree): Tree = pat match {
            case Apply(fn, args) => patConstr(fn)
            case _ => pat
          }
          if (showOuterTests &&
              needsOuterTest(
                patConstr(pat).tpe.finalResultType, selectorType, currentOwner))
            print("???")
          print(pat); printOpt(" if ", guard)
<<<<<<< HEAD
          print(" => "); print(body)
=======
          print(" => ", body)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case Alternative(trees) =>
          printRow(trees, "(", "| ", ")")

        case Star(elem) =>
<<<<<<< HEAD
          print("("); print(elem); print(")*")

        case Bind(name, t) =>
          print("("); print(symName(tree, name)); print(" @ "); print(t); print(")")

        case UnApply(fun, args) =>
          print(fun); print(" <unapply> "); printRow(args, "(", ", ", ")")

        case ArrayValue(elemtpt, trees) =>
          print("Array["); print(elemtpt); printRow(trees, "]{", ", ", "}")

        case Function(vparams, body) =>
          print("("); printValueParams(vparams); print(" => "); print(body); print(")")
          if (settings.uniqid.value && tree.symbol != null) print("#"+tree.symbol.id)

        case Assign(lhs, rhs) =>
          print(lhs); print(" = "); print(rhs)

        case If(cond, thenp, elsep) =>
          print("if ("); print(cond); print(")"); indent; println()
=======
          print("(", elem, ")*")

        case Bind(name, t) =>
          print("(", symName(tree, name), " @ ", t, ")")

        case UnApply(fun, args) =>
          print(fun, " <unapply> "); printRow(args, "(", ", ", ")")

        case ArrayValue(elemtpt, trees) =>
          print("Array[", elemtpt); printRow(trees, "]{", ", ", "}")

        case Function(vparams, body) =>
          print("("); printValueParams(vparams); print(" => ", body, ")")
          if (uniqueIds && tree.symbol != null) print("#"+tree.symbol.id)

        case Assign(lhs, rhs) =>
          print(lhs, " = ", rhs)

        case If(cond, thenp, elsep) =>
          print("if (", cond, ")"); indent; println()
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          print(thenp); undent
          if (!elsep.isEmpty) {
            println(); print("else"); indent; println(); print(elsep); undent
          }

        case Return(expr) =>
<<<<<<< HEAD
          print("return "); print(expr)
=======
          print("return ", expr)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case Try(block, catches, finalizer) =>
          print("try "); printBlock(block)
          if (!catches.isEmpty) printColumn(catches, " catch {", "", "}")
          printOpt(" finally ", finalizer)

        case Throw(expr) =>
<<<<<<< HEAD
          print("throw "); print(expr)

        case New(tpe) =>
          print("new "); print(tpe)

        case Typed(expr, tp) =>
          print("("); print(expr); print(": "); print(tp); print(")")
=======
          print("throw ", expr)

        case New(tpe) =>
          print("new ", tpe)

        case Typed(expr, tp) =>
          print("(", expr, ": ", tp, ")")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case TypeApply(fun, targs) =>
          print(fun); printRow(targs, "[", ", ", "]")

        case Apply(fun, vargs) =>
          print(fun); printRow(vargs, "(", ", ", ")")

        case ApplyDynamic(qual, vargs) =>
<<<<<<< HEAD
          print("<apply-dynamic>("); print(qual); print("#"); print(tree.symbol.nameString)
=======
          print("<apply-dynamic>(", qual, "#", tree.symbol.nameString)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          printRow(vargs, ", (", ", ", "))")

        case Super(This(qual), mix) =>
          if (!qual.isEmpty || tree.symbol != NoSymbol) print(symName(tree, qual) + ".")
          print("super")
          if (!mix.isEmpty)
            print("[" + mix + "]")

        case Super(qual, mix) =>
<<<<<<< HEAD
          print(qual)
          print(".super")
          if (!mix.isEmpty)
            print("[" + mix + "]")
            
=======
          print(qual, ".super")
          if (!mix.isEmpty)
            print("[" + mix + "]")

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        case This(qual) =>
          if (!qual.isEmpty) print(symName(tree, qual) + ".")
          print("this")

        case Select(qual @ New(tpe), name) if (!settings.debug.value) =>
          print(qual)

        case Select(qualifier, name) =>
<<<<<<< HEAD
          print(backquotedPath(qualifier)); print("."); print(symName(tree, name))
=======
          print(backquotedPath(qualifier), ".", symName(tree, name))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case Ident(name) =>
          print(symName(tree, name))

        case Literal(x) =>
          print(x.escapedStringValue)

        case tt: TypeTree =>
<<<<<<< HEAD
          if ((tree.tpe eq null) || (settings.Xprintpos.value && tt.original != null)) {
            if (tt.original != null) { print("<type: "); print(tt.original); print(">") }
            else print("<type ?>")
          } else if ((tree.tpe.typeSymbol ne null) && tree.tpe.typeSymbol.isAnonymousClass) {
            print(tree.tpe.typeSymbol.toString())
          } else {
            print(tree.tpe.toString())
=======
          if ((tree.tpe eq null) || (doPrintPositions && tt.original != null)) {
            if (tt.original != null) print("<type: ", tt.original, ">")
            else print("<type ?>")
          } else if ((tree.tpe.typeSymbol ne null) && tree.tpe.typeSymbol.isAnonymousClass) {
            print(tree.tpe.typeSymbol.toString)
          } else {
            print(tree.tpe.toString)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          }

        case Annotated(Apply(Select(New(tpt), nme.CONSTRUCTOR), args), tree) =>
          def printAnnot() {
<<<<<<< HEAD
            print("@"); print(tpt)
            if (!args.isEmpty)
              printRow(args, "(", ",", ")")
          }
          if (tree.isType) { print(tree); print(" "); printAnnot() }
          else { print(tree); print(": "); printAnnot() }
          
        case SingletonTypeTree(ref) =>
          print(ref); print(".type")

        case SelectFromTypeTree(qualifier, selector) =>
          print(qualifier); print("#"); print(symName(tree, selector))
=======
            print("@", tpt)
            if (!args.isEmpty)
              printRow(args, "(", ",", ")")
          }
          print(tree, if (tree.isType) " " else ": ")
          printAnnot()

        case SingletonTypeTree(ref) =>
          print(ref, ".type")

        case SelectFromTypeTree(qualifier, selector) =>
          print(qualifier, "#", symName(tree, selector))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

        case CompoundTypeTree(templ) =>
          print(templ)

        case AppliedTypeTree(tp, args) =>
          print(tp); printRow(args, "[", ", ", "]")

        case TypeBoundsTree(lo, hi) =>
          printOpt(" >: ", lo); printOpt(" <: ", hi)

        case ExistentialTypeTree(tpt, whereClauses) =>
<<<<<<< HEAD
          print(tpt); 
=======
          print(tpt);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          printColumn(whereClauses, " forSome { ", ";", "}")

// SelectFromArray is no longer visible in reflect.internal.
// eliminated until we figure out what we will do with both TreePrinters and
// SelectFromArray.
//          case SelectFromArray(qualifier, name, _) =>
//          print(qualifier); print(".<arr>"); print(symName(tree, name))


<<<<<<< HEAD
        case tree => 
          xprintRaw(this, tree)
      }
      if (settings.printtypes.value && tree.isTerm && !tree.isEmpty) {
        print("{"); print(if (tree.tpe eq null) "<null>" else tree.tpe.toString()); print("}")
      }
    }
    
    def print(tree: Tree) {
      printPosition(tree)
      printRaw(
        //nsc if (tree.isDef && tree.symbol != NoSymbol && tree.symbol.isInitialized) {
        //nsc  tree match {
        //nsc    case ClassDef(_, _, _, impl @ Template(ps, emptyValDef, body)) 
        //nsc    if (tree.symbol.thisSym != tree.symbol) => 
        //nsc      ClassDef(tree.symbol, Template(ps, ValDef(tree.symbol.thisSym), body))
        //nsc    case ClassDef(_, _, _, impl)           => ClassDef(tree.symbol, impl)
        //nsc    case ModuleDef(_, _, impl)             => ModuleDef(tree.symbol, impl)
        //nsc     case ValDef(_, _, _, rhs)              => ValDef(tree.symbol, rhs)
        //nsc     case DefDef(_, _, _, vparamss, _, rhs) => DefDef(tree.symbol, vparamss, rhs)
        //nsc     case TypeDef(_, _, _, rhs)             => TypeDef(tree.symbol, rhs)
        //nsc     case _ => tree
        //nsc   }
        //nsc } else 
          tree)
    }
  }

  def xprintRaw(treePrinter: TreePrinter, tree: Tree) = 
    treePrinter.print(tree.productPrefix+tree.productIterator.mkString("(", ", ", ")"))
  
=======
        case tree =>
          xprintTree(this, tree)
      }
      if (typesPrinted && tree.isTerm && !tree.isEmpty) {
        print("{", if (tree.tpe eq null) "<null>" else tree.tpe.toString, "}")
      }
    }

    def print(args: Any*): Unit = args foreach {
      case tree: Tree =>
        printPosition(tree)
        printTree(tree)
      case name: Name =>
        print(quotedName(name))
      case arg =>
        out.print(arg.toString)
    }
  }

  /** Hook for extensions */
  def xprintTree(treePrinter: TreePrinter, tree: Tree) =
    treePrinter.print(tree.productPrefix+tree.productIterator.mkString("(", ", ", ")"))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newTreePrinter(writer: PrintWriter): TreePrinter = new TreePrinter(writer)
  def newTreePrinter(stream: OutputStream): TreePrinter = newTreePrinter(new PrintWriter(stream))
  def newTreePrinter(): TreePrinter = newTreePrinter(new PrintWriter(ConsoleWriter))

  /** A writer that writes to the current Console and
   * is sensitive to replacement of the Console's
   * output stream.
   */
  object ConsoleWriter extends Writer {
    override def write(str: String) { Console.print(str) }
<<<<<<< HEAD
    
    def write(cbuf: Array[Char], off: Int, len: Int) {
      write(new String(cbuf, off, len))
    }
    
=======

    def write(cbuf: Array[Char], off: Int, len: Int) {
      write(new String(cbuf, off, len))
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def close = { /* do nothing */ }
    def flush = { /* do nothing */ }
  }
}
