/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * Author: Paul Phillips
 */

package scala.tools.nsc
package matching

import transform.ExplicitOuter
import PartialFunction._

trait PatternBindings extends ast.TreeDSL
<<<<<<< HEAD
{ 
  self: ExplicitOuter with ParallelMatching =>
  
=======
{
  self: ExplicitOuter with ParallelMatching =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  import global.{ typer => _, _ }
  import definitions.{ EqualsPatternClass }
  import CODE._
  import Debug._
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** EqualsPattern **/
  def isEquals(tpe: Type)             = cond(tpe) { case TypeRef(_, EqualsPatternClass, _) => true }
  def mkEqualsRef(tpe: Type)          = typeRef(NoPrefix, EqualsPatternClass, List(tpe))
  def decodedEqualsType(tpe: Type)    = condOpt(tpe) { case TypeRef(_, EqualsPatternClass, List(arg)) => arg } getOrElse (tpe)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // A subtype test which creates fresh existentials for type
  // parameters on the right hand side.
  def matches(arg1: Type, arg2: Type) = decodedEqualsType(arg1) matchesPattern decodedEqualsType(arg2)

  // For spotting duplicate unapplies
  def isEquivalentTree(t1: Tree, t2: Tree) = (t1.symbol == t2.symbol) && (t1 equalsStructure t2)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Reproduce the Bind trees wrapping oldTree around newTree
  def moveBindings(oldTree: Tree, newTree: Tree): Tree = oldTree match {
    case b @ Bind(x, body)  => Bind(b.symbol, moveBindings(body, newTree))
    case _                  => newTree
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // used as argument to `EqualsPatternClass`
  case class PseudoType(o: Tree) extends SimpleTypeProxy {
    override def underlying: Type = o.tpe
    override def safeToString: String = "PseudoType("+o+")"
  }

  // If the given pattern contains alternatives, return it as a list of patterns.
  // Makes typed copies of any bindings found so all alternatives point to final state.
  def extractBindings(p: Pattern): List[Pattern] =
    toPats(_extractBindings(p.boundTree, identity))
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def _extractBindings(p: Tree, prevBindings: Tree => Tree): List[Tree] = {
    def newPrev(b: Bind) = (x: Tree) => treeCopy.Bind(b, b.name, x) setType x.tpe

    p match {
      case b @ Bind(_, body)  => _extractBindings(body, newPrev(b))
      case Alternative(ps)    => ps map prevBindings
    }
  }
<<<<<<< HEAD
  
  trait PatternBindingLogic {
    self: Pattern =>
    
=======

  trait PatternBindingLogic {
    self: Pattern =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // This is for traversing the pattern tree - pattern types which might have
    // bound variables beneath them return a list of said patterns for flatMapping.
    def subpatternsForVars: List[Pattern] = Nil

    // The outermost Bind(x1, Bind(x2, ...)) surrounding the tree.
    private var _boundTree: Tree = tree
    def boundTree = _boundTree
    def setBound(x: Bind): Pattern = {
      _boundTree = x
      this
    }
    def boundVariables = strip(boundTree)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // If a tree has bindings, boundTree looks something like
    //   Bind(v3, Bind(v2, Bind(v1, tree)))
    // This takes the given tree and creates a new pattern
    //   using the same bindings.
    def rebindTo(t: Tree): Pattern = Pattern(moveBindings(boundTree, t))

    // Wrap this pattern's bindings around (_: Type)
    def rebindToType(tpe: Type, ascription: Type = null): Pattern = {
      val aType = if (ascription == null) tpe else ascription
      rebindTo(Typed(WILD(tpe), TypeTree(aType)) setType tpe)
    }
<<<<<<< HEAD
    
    // Wrap them around _
    def rebindToEmpty(tpe: Type): Pattern =
      rebindTo(Typed(EmptyTree, TypeTree(tpe)) setType tpe)
    
    // Wrap them around a singleton type for an EqualsPattern check.
    def rebindToEqualsCheck(): Pattern =
      rebindToType(equalsCheck)
    
=======

    // Wrap them around _
    def rebindToEmpty(tpe: Type): Pattern =
      rebindTo(Typed(EmptyTree, TypeTree(tpe)) setType tpe)

    // Wrap them around a singleton type for an EqualsPattern check.
    def rebindToEqualsCheck(): Pattern =
      rebindToType(equalsCheck)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // Like rebindToEqualsCheck, but subtly different.  Not trying to be
    // mysterious -- I haven't sorted it all out yet.
    def rebindToObjectCheck(): Pattern =
      rebindToType(mkEqualsRef(sufficientType), sufficientType)
<<<<<<< HEAD
       
    /** Helpers **/    
=======

    /** Helpers **/
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def wrapBindings(vs: List[Symbol], pat: Tree): Tree = vs match {
      case Nil      => pat
      case x :: xs  => Bind(x, wrapBindings(xs, pat)) setType pat.tpe
    }
    private def strip(t: Tree): List[Symbol] = t match {
      case b @ Bind(_, pat) => b.symbol :: strip(pat)
      case _                => Nil
    }
    private def deepstrip(t: Tree): List[Symbol] =
      treeCollect(t, { case x: Bind => x.symbol })
  }

  case class Binding(pvar: Symbol, tvar: Symbol) {
    override def toString() = pvar.name + " -> " + tvar.name
  }

  class Bindings(private val vlist: List[Binding]) {
    // if (!vlist.isEmpty)
    //   traceCategory("Bindings", this.toString)

    def get() = vlist
    def toMap = vlist map (x => (x.pvar, x.tvar)) toMap
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def add(vs: Iterable[Symbol], tvar: Symbol): Bindings = {
      val newBindings = vs.toList map (v => Binding(v, tvar))
      new Bindings(newBindings ++ vlist)
    }

<<<<<<< HEAD
    override def toString() = 
=======
    override def toString() =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (vlist.isEmpty) "<none>"
      else vlist.mkString(", ")
  }

  val NoBinding: Bindings = new Bindings(Nil)
}