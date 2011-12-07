/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect
package internal

<<<<<<< HEAD
import java.io.{ PrintWriter, StringWriter }
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import Flags._
import api.Modifier

trait Trees extends api.Trees { self: SymbolTable =>
<<<<<<< HEAD
   
  // --- modifiers implementation ---------------------------------------
 
=======

  // --- modifiers implementation ---------------------------------------

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** @param privateWithin the qualifier for a private (a type name)
   *    or tpnme.EMPTY, if none is given.
   *  @param annotations the annotations for the definition.
   *    '''Note:''' the typechecker drops these annotations,
<<<<<<< HEAD
   *    use the AnnotationInfo's (Symbol.annotations) in later phases. 
   */  
  case class Modifiers(flags: Long, 
                       privateWithin: Name, 
                       annotations: List[Tree]) extends AbsModifiers with HasFlags {
    
    var positions: Map[Long, Position] = Map()
    
    def setPositions(poss: Map[Long, Position]): this.type = {
      positions = poss; this
    }
 
=======
   *    use the AnnotationInfo's (Symbol.annotations) in later phases.
   */
  case class Modifiers(flags: Long,
                       privateWithin: Name,
                       annotations: List[Tree]) extends AbsModifiers with HasFlags {

    var positions: Map[Long, Position] = Map()

    def setPositions(poss: Map[Long, Position]): this.type = {
      positions = poss; this
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /* Abstract types from HasFlags. */
    type FlagsType          = Long
    type AccessBoundaryType = Name
    type AnnotationType     = Tree

<<<<<<< HEAD
=======
    def hasAnnotationNamed(name: TypeName) = {
      annotations exists {
        case Apply(Select(New(Ident(`name`)), _), _)     => true
        case Apply(Select(New(Select(_, `name`)), _), _) => true
        case _                                           => false
      }
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def hasAccessBoundary = privateWithin != tpnme.EMPTY
    def hasAllFlags(mask: Long): Boolean = (flags & mask) == mask
    def hasFlag(flag: Long) = (flag & flags) != 0L
    def hasFlagsToString(mask: Long): String = flagsToString(
      flags & mask,
      if (hasAccessBoundary) privateWithin.toString else ""
    )
<<<<<<< HEAD
=======
    def defaultFlagString = hasFlagsToString(-1L)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def & (flag: Long): Modifiers = {
      val flags1 = flags & flag
      if (flags1 == flags) this
      else Modifiers(flags1, privateWithin, annotations) setPositions positions
    }
    def &~ (flag: Long): Modifiers = {
      val flags1 = flags & (~flag)
      if (flags1 == flags) this
      else Modifiers(flags1, privateWithin, annotations) setPositions positions
    }
    def | (flag: Long): Modifiers = {
      val flags1 = flags | flag
      if (flags1 == flags) this
      else Modifiers(flags1, privateWithin, annotations) setPositions positions
    }
    def withAnnotations(annots: List[Tree]) =
      if (annots.isEmpty) this
      else copy(annotations = annotations ::: annots) setPositions positions
<<<<<<< HEAD
 
    def withPosition(flag: Long, position: Position) = 
      copy() setPositions positions + (flag -> position)
      
    override def hasModifier(mod: Modifier.Value) = 
      hasFlag(flagOfModifier(mod))
    override def allModifiers: Set[Modifier.Value] = 
      Modifier.values filter hasModifier
    override def mapAnnotations(f: List[Tree] => List[Tree]): Modifiers = 
      Modifiers(flags, privateWithin, f(annotations)) setPositions positions
    
    override def toString = "Modifiers(%s, %s, %s)".format(hasFlagsToString(-1L), annotations mkString ", ", positions)
=======

    def withPosition(flag: Long, position: Position) =
      copy() setPositions positions + (flag -> position)

    override def hasModifier(mod: Modifier.Value) =
      hasFlag(flagOfModifier(mod))
    override def allModifiers: Set[Modifier.Value] =
      Modifier.values filter hasModifier
    override def mapAnnotations(f: List[Tree] => List[Tree]): Modifiers =
      Modifiers(flags, privateWithin, f(annotations)) setPositions positions

    override def toString = "Modifiers(%s, %s, %s)".format(defaultFlagString, annotations mkString ", ", positions)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  def Modifiers(flags: Long, privateWithin: Name): Modifiers = Modifiers(flags, privateWithin, List())
  def Modifiers(flags: Long): Modifiers = Modifiers(flags, tpnme.EMPTY)
<<<<<<< HEAD
 
  def Modifiers(mods: Set[Modifier.Value], 
=======

  def Modifiers(mods: Set[Modifier.Value],
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                privateWithin: Name,
                annotations: List[Tree]): Modifiers = {
    val flagSet = mods map flagOfModifier
    Modifiers((0L /: flagSet)(_ | _), privateWithin, annotations)
  }

  lazy val NoMods = Modifiers(0)
<<<<<<< HEAD
 
  // --- extension methods --------------------------------------------------------
  
  override def show(tree: Tree): String = {
    val buffer = new StringWriter()
    val printer = newTreePrinter(new PrintWriter(buffer))
    printer.print(tree)
    printer.flush()
    buffer.toString     
  }
  
  implicit def treeOps(tree: Tree): TreeOps = new TreeOps(tree)
   
=======

  // --- extension methods --------------------------------------------------------

  implicit def treeOps(tree: Tree): TreeOps = new TreeOps(tree)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class TreeOps(tree: Tree) {
    def isErroneous = (tree.tpe ne null) && tree.tpe.isErroneous
    def isTyped     = (tree.tpe ne null) && !tree.tpe.isErroneous

<<<<<<< HEAD
    /** If `pf` is defined for a given subtree, call super.traverse(pf(tree)),
     *  otherwise super.traverse(tree).
     */
    def foreachPartial(pf: PartialFunction[Tree, Tree]) { 
      new ForeachPartialTreeTraverser(pf).traverse(tree) 
    }
   
=======
    /** Sets the tree's type to the result of the given function.
     *  If the type is null, it remains null - the function is not called.
     */
    def modifyType(f: Type => Type): Tree =
      if (tree.tpe eq null) tree
      else tree setType f(tree.tpe)

    /** If `pf` is defined for a given subtree, call super.traverse(pf(tree)),
     *  otherwise super.traverse(tree).
     */
    def foreachPartial(pf: PartialFunction[Tree, Tree]) {
      new ForeachPartialTreeTraverser(pf).traverse(tree)
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def changeOwner(pairs: (Symbol, Symbol)*): Tree = {
      pairs.foldLeft(tree) { case (t, (oldOwner, newOwner)) =>
        new ChangeOwnerTraverser(oldOwner, newOwner) apply t
      }
    }

    def shallowDuplicate: Tree = new ShallowDuplicator(tree) transform tree
<<<<<<< HEAD
    def shortClass: String = this.getClass.getName split "[.$]" last
    
=======
    def shortClass: String = tree.getClass.getName split "[.$]" last
    /** When you want to know a little more than the class, but a lot
     *  less than the whole tree.
     */
    def summaryString: String = tree match {
      case Select(qual, name) => qual.summaryString + "." + name.decode
      case Ident(name)        => name.longString
      case Literal(const)     => "Literal(" + const + ")"
      case t: DefTree         => t.shortClass + " `" + t.name.decode + "`"
      case t: RefTree         => t.shortClass + " `" + t.name.longString + "`"
      case t                  =>
        t.shortClass + (
          if (t.symbol != null && t.symbol != NoSymbol) " " + t.symbol
          else ""
        )
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  // ---- values and creators ---------------------------------------

  /** @param sym       the class symbol
   *  @return          the implementation template
   */
  def ClassDef(sym: Symbol, impl: Template): ClassDef =
    atPos(sym.pos) {
      ClassDef(Modifiers(sym.flags),
               sym.name.toTypeName,
               sym.typeParams map TypeDef,
               impl) setSymbol sym
    }

  /**
   *  @param sym       the class symbol
   *  @param impl      the implementation template
   */
  def ModuleDef(sym: Symbol, impl: Template): ModuleDef =
    atPos(sym.pos) {
      ModuleDef(Modifiers(sym.flags), sym.name, impl) setSymbol sym
    }

  def ValDef(sym: Symbol, rhs: Tree): ValDef =
    atPos(sym.pos) {
<<<<<<< HEAD
      ValDef(Modifiers(sym.flags), sym.name, 
             TypeTree(sym.tpe) setPos focusPos(sym.pos), 
=======
      ValDef(Modifiers(sym.flags), sym.name,
             TypeTree(sym.tpe) setPos focusPos(sym.pos),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
             rhs) setSymbol sym
    }

  def ValDef(sym: Symbol): ValDef = ValDef(sym, EmptyTree)

  object emptyValDef extends ValDef(Modifiers(PRIVATE), nme.WILDCARD, TypeTree(NoType), EmptyTree) {
    override def isEmpty = true
    super.setPos(NoPosition)
    override def setPos(pos: Position) = { assert(false); this }
  }

  def DefDef(sym: Symbol, mods: Modifiers, vparamss: List[List[ValDef]], rhs: Tree): DefDef =
    atPos(sym.pos) {
      assert(sym != NoSymbol)
      DefDef(Modifiers(sym.flags),
             sym.name,
             sym.typeParams map TypeDef,
             vparamss,
             TypeTree(sym.tpe.finalResultType) setPos focusPos(sym.pos),
             rhs) setSymbol sym
    }

  def DefDef(sym: Symbol, vparamss: List[List[ValDef]], rhs: Tree): DefDef =
    DefDef(sym, Modifiers(sym.flags), vparamss, rhs)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def DefDef(sym: Symbol, mods: Modifiers, rhs: Tree): DefDef =
    DefDef(sym, mods, sym.paramss map (_.map(ValDef)), rhs)

  def DefDef(sym: Symbol, rhs: Tree): DefDef =
    DefDef(sym, Modifiers(sym.flags), rhs)

  def DefDef(sym: Symbol, rhs: List[List[Symbol]] => Tree): DefDef = {
    DefDef(sym, rhs(sym.info.paramss))
  }

  /** A TypeDef node which defines given `sym` with given tight hand side `rhs`. */
  def TypeDef(sym: Symbol, rhs: Tree): TypeDef =
    atPos(sym.pos) {
      TypeDef(Modifiers(sym.flags), sym.name.toTypeName, sym.typeParams map TypeDef, rhs) setSymbol sym
    }

  /** A TypeDef node which defines abstract type or type parameter for given `sym` */
<<<<<<< HEAD
  def TypeDef(sym: Symbol): TypeDef = 
=======
  def TypeDef(sym: Symbol): TypeDef =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    TypeDef(sym, TypeBoundsTree(TypeTree(sym.info.bounds.lo), TypeTree(sym.info.bounds.hi)))

  def LabelDef(sym: Symbol, params: List[Symbol], rhs: Tree): LabelDef =
    atPos(sym.pos) {
      LabelDef(sym.name, params map Ident, rhs) setSymbol sym
    }


  /** casedef shorthand */
  def CaseDef(pat: Tree, body: Tree): CaseDef = CaseDef(pat, EmptyTree, body)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def Bind(sym: Symbol, body: Tree): Bind =
    Bind(sym.name, body) setSymbol sym


  /** Factory method for object creation `new tpt(args_1)...(args_n)`
   *  A `New(t, as)` is expanded to: `(new t).<init>(as)`
   */
  def New(tpt: Tree, argss: List[List[Tree]]): Tree = {
    assert(!argss.isEmpty)
    val superRef: Tree = Select(New(tpt), nme.CONSTRUCTOR)
    (superRef /: argss) (Apply)
  }
  /** 0-1 argument list new, based on a symbol.
   */
  def New(sym: Symbol, args: Tree*): Tree =
    if (args.isEmpty) New(TypeTree(sym.tpe))
    else New(TypeTree(sym.tpe), List(args.toList))
<<<<<<< HEAD
  
  def Apply(sym: Symbol, args: Tree*): Tree =
    Apply(Ident(sym), args.toList)
  
=======

  def Apply(sym: Symbol, args: Tree*): Tree =
    Apply(Ident(sym), args.toList)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def Super(sym: Symbol, mix: TypeName): Tree = Super(This(sym), mix)

  def This(sym: Symbol): Tree = This(sym.name.toTypeName) setSymbol sym

<<<<<<< HEAD
  def Select(qualifier: Tree, sym: Symbol): Select =
    Select(qualifier, sym.name) setSymbol sym

  def Ident(sym: Symbol): Ident =
    Ident(sym.name) setSymbol sym

  /** Block factory that flattens directly nested blocks. 
=======
  /** Block factory that flattens directly nested blocks.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  def Block(stats: Tree*): Block = stats match {
    case Seq(b @ Block(_, _)) => b
    case Seq(stat) => Block(stats.toList, Literal(Constant(())))
    case Seq(_, rest @ _*) => Block(stats.init.toList, stats.last)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // --- specific traversers and transformers

  protected[scala] def duplicateTree(tree: Tree): Tree = duplicator transform tree

  private lazy val duplicator = new Transformer {
    override val treeCopy = newStrictTreeCopier
    override def transform(t: Tree) = {
      val t1 = super.transform(t)
      if ((t1 ne t) && isRangePos(t1.pos)) t1 setPos focusPos(t.pos)
      t1
    }
  }

  private object posAssigner extends Traverser {
    var pos: Position = _
    override def traverse(t: Tree) {
      if (t != EmptyTree && t.pos == NoPosition) {
        t.setPos(pos)
<<<<<<< HEAD
        super.traverse(t)
=======
        super.traverse(t) // TODO: bug? shouldn't the traverse be outside of the if?
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }

  def atPos[T <: Tree](pos: Position)(tree: T): T = {
    posAssigner.pos = pos
    posAssigner.traverse(tree)
    tree
  }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class ForeachPartialTreeTraverser(pf: PartialFunction[Tree, Tree]) extends Traverser {
    override def traverse(tree: Tree) {
      val t = if (pf isDefinedAt tree) pf(tree) else tree
      super.traverse(t)
    }
  }

  class ChangeOwnerTraverser(val oldowner: Symbol, val newowner: Symbol) extends Traverser {
    def changeOwner(tree: Tree) = {
      if ((tree.isDef || tree.isInstanceOf[Function]) &&
          tree.symbol != NoSymbol && tree.symbol.owner == oldowner)
        tree.symbol.owner = newowner
    }
    override def traverse(tree: Tree) {
      changeOwner(tree)
      super.traverse(tree)
    }
<<<<<<< HEAD
  } 
  
=======
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private class ShallowDuplicator(orig: Tree) extends Transformer {
    override val treeCopy = newStrictTreeCopier
    override def transform(tree: Tree) =
      if (tree eq orig) super.transform(tree)
      else tree
  }
  // Create a readable string describing a substitution.
  private def substituterString(fromStr: String, toStr: String, from: List[Any], to: List[Any]): String = {
    "subst[%s, %s](%s)".format(fromStr, toStr, (from, to).zipped map (_ + " -> " + _) mkString ", ")
  }

<<<<<<< HEAD
=======
  // NOTE: if symbols in `from` occur multiple times in the `tree` passed to `transform`,
  // the resulting Tree will be a graph, not a tree... this breaks all sorts of stuff,
  // notably concerning the mutable aspects of Trees (such as setting their .tpe)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class TreeSubstituter(from: List[Symbol], to: List[Tree]) extends Transformer {
    override def transform(tree: Tree): Tree = tree match {
      case Ident(_) =>
        def subst(from: List[Symbol], to: List[Tree]): Tree =
          if (from.isEmpty) tree
<<<<<<< HEAD
          else if (tree.symbol == from.head) to.head
=======
          else if (tree.symbol == from.head) to.head.shallowDuplicate // TODO: does it ever make sense *not* to perform a shallowDuplicate on `to.head`?
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          else subst(from.tail, to.tail);
        subst(from, to)
      case _ =>
        super.transform(tree)
    }
    override def toString = substituterString("Symbol", "Tree", from, to)
  }

<<<<<<< HEAD
  class TreeTypeSubstituter(val from: List[Symbol], val to: List[Type]) extends Traverser {
    val typeSubst = new SubstTypeMap(from, to)
    def fromContains = typeSubst.fromContains
    def isEmpty = from.isEmpty && to.isEmpty
    
    override def traverse(tree: Tree) {
      if (tree.tpe ne null) tree.tpe = typeSubst(tree.tpe)
      if (tree.isDef) {
        val sym = tree.symbol
        val info1 = typeSubst(sym.info)
        if (info1 ne sym.info) sym.setInfo(info1)
      }
      super.traverse(tree)
    }
    override def apply[T <: Tree](tree: T): T = super.apply(tree.duplicate)
=======
  class TypeMapTreeSubstituter(val typeMap: TypeMap) extends Traverser {
    override def traverse(tree: Tree) {
      if (tree.tpe ne null)
        tree.tpe = typeMap(tree.tpe)
      if (tree.isDef)
        tree.symbol modifyInfo typeMap

      super.traverse(tree)
    }
    override def apply[T <: Tree](tree: T): T = super.apply(tree.duplicate)
  }

  class TreeTypeSubstituter(val from: List[Symbol], val to: List[Type]) extends TypeMapTreeSubstituter(new SubstTypeMap(from, to)) {
    def isEmpty = from.isEmpty && to.isEmpty
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString() = "TreeTypeSubstituter("+from+","+to+")"
  }

  lazy val EmptyTreeTypeSubstituter = new TreeTypeSubstituter(List(), List())

<<<<<<< HEAD
  class TreeSymSubstTraverser(val from: List[Symbol], val to: List[Symbol]) extends Traverser {
    val subst = new SubstSymMap(from, to)
    override def traverse(tree: Tree) {
      if (tree.tpe ne null) tree.tpe = subst(tree.tpe)
      if (tree.isDef) {
        val sym = tree.symbol
        val info1 = subst(sym.info)
        if (info1 ne sym.info) sym.setInfo(info1)
      }
      super.traverse(tree)
    }
    override def apply[T <: Tree](tree: T): T = super.apply(tree.duplicate)
=======
  class TreeSymSubstTraverser(val from: List[Symbol], val to: List[Symbol]) extends TypeMapTreeSubstituter(new SubstSymMap(from, to)) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString() = "TreeSymSubstTraverser/" + substituterString("Symbol", "Symbol", from, to)
  }

  /** Substitute symbols in `from` with symbols in `to`. Returns a new
   *  tree using the new symbols and whose Ident and Select nodes are
<<<<<<< HEAD
   *  name-consistent with the new symbols. 
=======
   *  name-consistent with the new symbols.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   */
  class TreeSymSubstituter(from: List[Symbol], to: List[Symbol]) extends Transformer {
    val symSubst = new SubstSymMap(from, to)
    override def transform(tree: Tree): Tree = {
      def subst(from: List[Symbol], to: List[Symbol]) {
        if (!from.isEmpty)
          if (tree.symbol == from.head) tree setSymbol to.head
          else subst(from.tail, to.tail)
      }

      if (tree.tpe ne null) tree.tpe = symSubst(tree.tpe)
      if (tree.hasSymbol) {
        subst(from, to)
        tree match {
          case Ident(name0) if tree.symbol != NoSymbol =>
            treeCopy.Ident(tree, tree.symbol.name)
          case Select(qual, name0) =>
            treeCopy.Select(tree, transform(qual), tree.symbol.name)
          case _ =>
            super.transform(tree)
        }
      } else
        super.transform(tree)
    }
    def apply[T <: Tree](tree: T): T = transform(tree).asInstanceOf[T]
    override def toString() = "TreeSymSubstituter/" + substituterString("Symbol", "Symbol", from, to)
  }
}
