/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Martin Odersky
 */

package scala.tools.nsc
package typechecker

import symtab.Flags
import symtab.Flags._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/** Synthetic method implementations for case classes and case objects.
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  Added to all case classes/objects:
 *    def productArity: Int
 *    def productElement(n: Int): Any
 *    def productPrefix: String
 *    def productIterator: Iterator[Any]
 *
 *  Selectively added to case classes/objects, unless a non-default
 *  implementation already exists:
 *    def equals(other: Any): Boolean
 *    def hashCode(): Int
 *    def canEqual(other: Any): Boolean
 *    def toString(): String
 *
 *  Special handling:
 *    protected def readResolve(): AnyRef
 */
trait SyntheticMethods extends ast.TreeDSL {
  self: Analyzer =>
<<<<<<< HEAD
  
  import global._                  // the global environment
  import definitions._             // standard classes and methods

  /** Add the synthetic methods to case classes.  Note that a lot of the
   *  complexity herein is a consequence of case classes inheriting from
   *  case classes, which has been deprecated as of Sep 11 2009.  So when
   *  the opportunity for removal arises, this can be simplified.
   */
  def addSyntheticMethods(templ: Template, clazz: Symbol, context: Context): Template = {
    val localTyper = newTyper(
      if (reporter.hasErrors) context makeSilent false else context
    )
    def accessorTypes = clazz.caseFieldAccessors map (_.tpe.finalResultType)
    def accessorLub = global.weakLub(accessorTypes)._1

    def hasOverridingImplementation(meth: Symbol): Boolean = {
      val sym = clazz.info nonPrivateMember meth.name
      def isOverride(s: Symbol) = {
        s != meth && !s.isDeferred && !s.isSynthetic &&
        (clazz.thisType.memberType(s) matches clazz.thisType.memberType(meth))
      }
      sym.alternatives exists isOverride
    }

    def syntheticMethod(name: Name, flags: Int, tpeCons: Symbol => Type) =
      newSyntheticMethod(name, flags | OVERRIDE, tpeCons)

    def newSyntheticMethod(name: Name, flags: Int, tpeCons: Symbol => Type) = {
      val method = clazz.newMethod(clazz.pos.focus, name.toTermName) setFlag flags
      method setInfo tpeCons(method)
      clazz.info.decls.enter(method)
    }
    
    def makeNoArgConstructor(res: Type) =
      (sym: Symbol) => MethodType(Nil, res)
    def makeTypeConstructor(args: List[Type], res: Type) =
      (sym: Symbol) => MethodType(sym newSyntheticValueParams args, res)
    def makeEqualityMethod(name: Name) =
      syntheticMethod(name, 0, makeTypeConstructor(List(AnyClass.tpe), BooleanClass.tpe))

    import CODE._
    
    def newNullaryMethod(name: Name, tpe: Type, body: Tree) = {
      val flags  = if (clazz.tpe.member(name.toTermName) != NoSymbol) OVERRIDE else 0
      val method = clazz.newMethod(clazz.pos.focus, name.toTermName) setFlag flags
      
      method setInfo NullaryMethodType(tpe)
      clazz.info.decls enter method
      
      typer typed (DEF(method) === body)
    }
    def productPrefixMethod            = newNullaryMethod(nme.productPrefix, StringClass.tpe, LIT(clazz.name.decode))
    def productArityMethod(arity: Int) = newNullaryMethod(nme.productArity, IntClass.tpe, LIT(arity))
    def productIteratorMethod = {
      val method       = getMember(ScalaRunTimeModule, "typedProductIterator")
      val iteratorType = typeRef(NoPrefix, IteratorClass, List(accessorLub))
      
      newNullaryMethod(
        nme.productIterator,
        iteratorType,
        gen.mkMethodCall(method, List(accessorLub), List(This(clazz)))
      )
    }

    def projectionMethod(accessor: Symbol, num: Int) = {
      newNullaryMethod(nme.productAccessorName(num), accessor.tpe.resultType, REF(accessor))
    }

    /** Common code for productElement and (currently disabled) productElementName
     */
    def perElementMethod(accs: List[Symbol], methodName: Name, resType: Type, caseFn: Symbol => Tree): Tree = {
      val symToTpe  = makeTypeConstructor(List(IntClass.tpe), resType)
      val method    = syntheticMethod(methodName, 0, symToTpe)
      val arg       = method ARG 0
      val default   = List(DEFAULT ==> THROW(IndexOutOfBoundsExceptionClass, arg))
      val cases     =
        for ((sym, i) <- accs.zipWithIndex) yield
          CASE(LIT(i)) ==> caseFn(sym)

      typer typed {
        DEF(method) === {
          arg MATCH { cases ::: default : _* }
        }
      }
    }
    def productElementMethod(accs: List[Symbol]): Tree =
      perElementMethod(accs, nme.productElement, AnyClass.tpe, x => Ident(x))
  
    // def productElementNameMethod(accs: List[Symbol]): Tree =
    //   perElementMethod(accs, nme.productElementName, StringClass.tpe, x => Literal(x.name.toString))

    def moduleToStringMethod: Tree = {
      val method = syntheticMethod(nme.toString_, FINAL, makeNoArgConstructor(StringClass.tpe))
      typer typed { DEF(method) === LIT(clazz.name.decode) }
    }
    def moduleHashCodeMethod: Tree = {
      val method = syntheticMethod(nme.hashCode_, FINAL, makeNoArgConstructor(IntClass.tpe))
      // The string being used as hashcode basis is also productPrefix.
      val code   = clazz.name.decode.hashCode

      typer typed { DEF(method) === LIT(code) }
    }

    def forwardingMethod(name: Name, targetName: Name): Tree = {
      val target      = getMember(ScalaRunTimeModule, targetName)
      val paramtypes  = target.tpe.paramTypes drop 1
      val method      = syntheticMethod(name, 0, makeTypeConstructor(paramtypes, target.tpe.resultType))
        
      typer typed {
        DEF(method) === {
          Apply(REF(target), This(clazz) :: (method ARGNAMES))
        }
      }
    }

    /** The equality method for case modules:
     *   def equals(that: Any) = this eq that
     */
    def equalsModuleMethod: Tree = {
      val method = makeEqualityMethod(nme.equals_)
      val that   = method ARG 0
      
      localTyper typed {
        DEF(method) === (This(clazz) ANY_EQ that)
      }
    }
    
    /** The canEqual method for case classes.  Note that if we spot
     *  a user-supplied equals implementation, we simply return true
     *  so as not to interfere.
     */
    def canEqualMethod: Tree = {
      val method  = makeEqualityMethod(nme.canEqual_)
      val that    = method ARG 0
      
      typer typed (DEF(method) === (that IS_OBJ clazz.tpe))
    }

    /** The equality method for case classes.  The argument is an Any,
     *  but because of boxing it will always be an Object, so a check
     *  is neither necessary nor useful before the cast.
     *
     *   def equals(that: Any) = 
     *     (this eq that.asInstanceOf[AnyRef]) || 
     *     (that match {
     *       case x @ this.C(this.arg_1, ..., this.arg_n) => x canEqual this  
     *       case _                                       => false
     *     })
     */
    def equalsClassMethod: Tree = {
      val method           = makeEqualityMethod(nme.equals_)
      val that             = method ARG 0
      val constrParamTypes = clazz.primaryConstructor.tpe.paramTypes
      
      // returns (Apply, Bind)
      def makeTrees(acc: Symbol, cpt: Type): (Tree, Bind) = {
        val varName     = context.unit.freshTermName(acc.name + "$")
        val isRepeated  = isRepeatedParamType(cpt)
        val binding     = if (isRepeated) Star(WILD.empty) else WILD.empty
        val eqMethod: Tree  =
          if (isRepeated) gen.mkRuntimeCall(nme.sameElements, List(Ident(varName), Ident(acc)))
          else (Ident(varName) DOT nme.EQ)(Ident(acc))

        (eqMethod, Bind(varName, binding))
      }
      
      // Creates list of parameters and a guard for each
      val (guards, params) = (clazz.caseFieldAccessors, constrParamTypes).zipped map makeTrees unzip

      // Verify with canEqual method before returning true.
      def canEqualCheck() = {
        val that: Tree              = (method ARG 0) AS clazz.tpe
        val canEqualOther: Symbol   = clazz.info nonPrivateMember nme.canEqual_
        
        typer typed {
          (that DOT canEqualOther)(This(clazz))
        }
      }
        
      // Pattern is classname applied to parameters, and guards are all logical and-ed
      val (guard, pat) = (AND(guards: _*), Ident(clazz.name.toTermName) APPLY params)
      
      localTyper typed {
        DEF(method) === {
          (This(clazz) ANY_EQ that) OR (that MATCH(
            (CASE(pat) IF guard)  ==> canEqualCheck()        ,
            DEFAULT               ==> FALSE
          ))
        }
      }
    }

    def newAccessorMethod(tree: Tree): Tree = tree match {
      case DefDef(_, _, _, _, _, rhs) =>
        var newAcc = tree.symbol.cloneSymbol
        newAcc.name = context.unit.freshTermName(tree.symbol.name + "$")
        newAcc setFlag SYNTHETIC resetFlag (ACCESSOR | PARAMACCESSOR | PRIVATE | PROTECTED)
        newAcc.privateWithin = NoSymbol
        newAcc = newAcc.owner.info.decls enter newAcc
        val result = typer typed { DEF(newAcc) === rhs.duplicate }
        log("new accessor method " + result)
        result
    }
    
    def needsReadResolve = (
      // only nested objects inside objects should get readResolve automatically
      // otherwise after de-serialization we get null references for lazy accessors (nested object -> lazy val + class def)
      // since the bitmap gets serialized but the moduleVar not
      clazz.isSerializable && (clazz.owner.isPackageClass || clazz.owner.isModuleClass)
    )

    // A buffer collecting additional methods for the template body
    val ts = new ListBuffer[Tree]

    if (!phase.erasedTypes) try {
      if (clazz.isCase) {
        val isTop     = clazz.ancestors forall (x => !x.isCase)
        val accessors = clazz.caseFieldAccessors
        val arity     = accessors.size

        if (isTop) {
          // If this case class has fields with less than public visibility, their getter at this
          // point also has those permissions.  In that case we create a new, public accessor method
          // with a new name and remove the CASEACCESSOR flag from the existing getter.  This complicates
          // the retrieval of the case field accessors (see def caseFieldAccessors in Symbols.)
          def needsService(s: Symbol) = s.isMethod && s.isCaseAccessor && !s.isPublic
          for (stat <- templ.body ; if stat.isDef && needsService(stat.symbol)) {
            ts += newAccessorMethod(stat)
            stat.symbol resetFlag CASEACCESSOR
          }
        }
        /** The _1, _2, etc. methods to implement ProductN, and an override
         *  of productIterator with a more specific element type.
         *  Only enabled under -Xexperimental.
         */
        def productNMethods = {
          val projectionMethods = (accessors, 1 to arity).zipped map ((accessor, num) =>
            productProj(arity, num) -> (() => projectionMethod(accessor, num))
          )
          projectionMethods :+ (
            Product_iterator -> (() => productIteratorMethod)
          )
        }
        
        // methods for case classes only
        def classMethods = List(
          Object_hashCode -> (() => forwardingMethod(nme.hashCode_, "_" + nme.hashCode_)),
          Object_toString -> (() => forwardingMethod(nme.toString_, "_" + nme.toString_)),
          Object_equals   -> (() => equalsClassMethod)
        ) ++ (
          if (settings.Xexperimental.value) productNMethods
          else Nil
        )

        // methods for case objects only
        def objectMethods = List(
          Object_hashCode -> (() => moduleHashCodeMethod),
          Object_toString -> (() => moduleToStringMethod)
        )
        // methods for both classes and objects
        def everywhereMethods = {
          List(
            Product_productPrefix   -> (() => productPrefixMethod),
            Product_productArity    -> (() => productArityMethod(accessors.length)),
            Product_productElement  -> (() => productElementMethod(accessors)),
            // This is disabled pending a reimplementation which doesn't add any
            // weight to case classes (i.e. inspects the bytecode.)
            // Product_productElementName  -> (() => productElementNameMethod(accessors)),
            Product_canEqual        -> (() => canEqualMethod)
          )
        }

        val methods = (if (clazz.isModuleClass) objectMethods else classMethods) ++ everywhereMethods
        for ((m, impl) <- methods ; if !hasOverridingImplementation(m))
          ts += impl()
      }

      if (clazz.isModuleClass) {
        def hasReadResolve = {
          val sym = clazz.info member nme.readResolve // any member, including private
          sym.isTerm && !sym.isDeferred
        }

        /** If you serialize a singleton and then deserialize it twice,
         *  you will have two instances of your singleton, unless you implement
         *  the readResolve() method (see http://www.javaworld.com/javaworld/
         *  jw-04-2003/jw-0425-designpatterns_p.html)
         */
        if (!hasReadResolve && needsReadResolve){
          // PP: To this day I really can't figure out what this next comment is getting at:
          // the !!! normally means there is something broken, but if so, what is it?
          //
          // !!! the synthetic method "readResolve" should be private, but then it is renamed !!!
          val method = newSyntheticMethod(nme.readResolve, PROTECTED, makeNoArgConstructor(ObjectClass.tpe))
          ts += typer typed (DEF(method) === REF(clazz.sourceModule))
        }
      }
    } catch {
      case ex: TypeError =>
        if (!reporter.hasErrors) throw ex
    }
    
    if (phase.id <= currentRun.typerPhase.id) {
      treeCopy.Template(templ, templ.parents, templ.self, 
        if (ts.isEmpty) templ.body else templ.body ++ ts // avoid copying templ.body if empty
      )
    }
    else templ
=======

  import global._
  import definitions._
  import CODE._

  private object util {
    private type CM[T] = ClassManifest[T]

    lazy val IteratorModule = getModule("scala.collection.Iterator")
    lazy val Iterator_apply = getMember(IteratorModule, nme.apply)
    def iteratorOfType(tp: Type) = appliedType(IteratorClass.typeConstructor, List(tp))

    def ValOrDefDef(sym: Symbol, body: Tree) =
      if (sym.isLazy) ValDef(sym, body)
      else DefDef(sym, body)

    /** To avoid unchecked warnings on polymorphic classes.
     */
    def clazzTypeToTest(clazz: Symbol) = clazz.tpe.normalize match {
      case TypeRef(_, sym, args) if args.nonEmpty => ExistentialType(sym.typeParams, clazz.tpe)
      case tp                                     => tp
    }

    def makeMethodPublic(method: Symbol): Symbol = (
      method setPrivateWithin NoSymbol resetFlag AccessFlags
    )

    def methodArg(method: Symbol, idx: Int): Tree = Ident(method.paramss.head(idx))

    private def applyTypeInternal(manifests: List[CM[_]]): Type = {
      val symbols = manifests map manifestToSymbol
      val container :: args = symbols
      val tparams = container.typeConstructor.typeParams

      // Overly conservative at present - if manifests were more usable
      // this could do a lot more.
      require(symbols forall (_ ne NoSymbol), "Must find all manifests: " + symbols)
      require(container.owner.isPackageClass, "Container must be a top-level class in a package: " + container)
      require(tparams.size == args.size, "Arguments must match type constructor arity: " + tparams + ", " + args)
      require(args forall (_.typeConstructor.typeParams.isEmpty), "Arguments must be unparameterized: " + args)

      typeRef(container.typeConstructor.prefix, container, args map (_.tpe))
    }

    def manifestToSymbol(m: CM[_]): Symbol = m match {
      case x: scala.reflect.AnyValManifest[_] => definitions.getClass("scala." + x)
      case _                                  => getClassIfDefined(m.erasure.getName)
    }
    def companionType[T](implicit m: CM[T]) =
      getModule(m.erasure.getName).tpe

    // Use these like `applyType[List, Int]` or `applyType[Map, Int, String]`
    def applyType[M](implicit m1: CM[M]): Type =
      applyTypeInternal(List(m1))

    def applyType[M[X1], X1](implicit m1: CM[M[_]], m2: CM[X1]): Type =
      applyTypeInternal(List(m1, m2))

    def applyType[M[X1, X2], X1, X2](implicit m1: CM[M[_,_]], m2: CM[X1], m3: CM[X2]): Type =
      applyTypeInternal(List(m1, m2, m3))

    def applyType[M[X1, X2, X3], X1, X2, X3](implicit m1: CM[M[_,_,_]], m2: CM[X1], m3: CM[X2], m4: CM[X3]): Type =
      applyTypeInternal(List(m1, m2, m3, m4))
  }
  import util._

  class MethodSynthesis(val clazz: Symbol, localTyper: Typer) {
    private def isOverride(method: Symbol) =
      clazzMember(method.name).alternatives exists (sym => (sym != method) && !sym.isDeferred)

    private def setMethodFlags(method: Symbol): Symbol = {
      val overrideFlag = if (isOverride(method)) OVERRIDE else 0L

      method setFlag (overrideFlag | SYNTHETIC) resetFlag DEFERRED
    }

    private def finishMethod(method: Symbol, f: Symbol => Tree): Tree = {
      setMethodFlags(method)
      clazz.info.decls enter method
      logResult("finishMethod")(localTyper typed ValOrDefDef(method, f(method)))
    }

    private def createInternal(name: Name, f: Symbol => Tree, info: Type): Tree = {
      val m = clazz.newMethod(clazz.pos.focus, name.toTermName)
      m setInfo info
      finishMethod(m, f)
    }
    private def createInternal(name: Name, f: Symbol => Tree, infoFn: Symbol => Type): Tree = {
      val m = clazz.newMethod(clazz.pos.focus, name.toTermName)
      m setInfo infoFn(m)
      finishMethod(m, f)
    }
    private def cloneInternal(original: Symbol, f: Symbol => Tree, name: Name): Tree = {
      val m = original.cloneSymbol(clazz) setPos clazz.pos.focus
      m.name = name
      finishMethod(m, f)
    }

    private def cloneInternal(original: Symbol, f: Symbol => Tree): Tree =
      cloneInternal(original, f, original.name)

    def clazzMember(name: Name) = clazz.info nonPrivateMember name match {
      case NoSymbol => log("In " + clazz + ", " + name + " not found: " + clazz.info) ; NoSymbol
      case sym      => sym
    }
    def typeInClazz(sym: Symbol) = clazz.thisType memberType sym

    /** Function argument takes the newly created method symbol of
     *  the same type as `name` in clazz, and returns the tree to be
     *  added to the template.
     */
    def overrideMethod(name: Name)(f: Symbol => Tree): Tree =
      overrideMethod(clazzMember(name))(f)

    def overrideMethod(original: Symbol)(f: Symbol => Tree): Tree =
      cloneInternal(original, sym => f(sym setFlag OVERRIDE))

    def deriveMethod(original: Symbol, nameFn: Name => Name)(f: Symbol => Tree): Tree =
      cloneInternal(original, f, nameFn(original.name))

    def createMethod(name: Name, paramTypes: List[Type], returnType: Type)(f: Symbol => Tree): Tree =
      createInternal(name, f, (m: Symbol) => MethodType(m newSyntheticValueParams paramTypes, returnType))

    def createMethod(name: Name, returnType: Type)(f: Symbol => Tree): Tree =
      createInternal(name, f, NullaryMethodType(returnType))

    def createMethod(original: Symbol)(f: Symbol => Tree): Tree =
      createInternal(original.name, f, original.info)

    def forwardMethod(original: Symbol, newMethod: Symbol)(transformArgs: List[Tree] => List[Tree]): Tree =
      createMethod(original)(m => gen.mkMethodCall(newMethod, transformArgs(m.paramss.head map Ident)))

    def createSwitchMethod(name: Name, range: Seq[Int], returnType: Type)(f: Int => Tree) = {
      createMethod(name, List(IntClass.tpe), returnType) { m =>
        val arg0    = methodArg(m, 0)
        val default = DEFAULT ==> THROW(IndexOutOfBoundsExceptionClass, arg0)
        val cases   = range.map(num => CASE(LIT(num)) ==> f(num)).toList :+ default

        Match(arg0, cases)
      }
    }

    // def foo() = constant
    def constantMethod(name: Name, value: Any): Tree = {
      val constant = Constant(value)
      createMethod(name, Nil, constant.tpe)(_ => Literal(constant))
    }
    // def foo = constant
    def constantNullary(name: Name, value: Any): Tree = {
      val constant = Constant(value)
      createMethod(name, constant.tpe)(_ => Literal(constant))
    }
  }

  /** Add the synthetic methods to case classes.
   */
  def addSyntheticMethods(templ: Template, clazz0: Symbol, context: Context): Template = {
    if (phase.erasedTypes)
      return templ

    val synthesizer = new MethodSynthesis(
      clazz0,
      newTyper( if (reporter.hasErrors) context makeSilent false else context )
    )
    import synthesizer._

    val originalAccessors = clazz.caseFieldAccessors
    // private ones will have been renamed -- make sure they are entered
    // in the original order.
    def accessors = clazz.caseFieldAccessors sortBy { acc =>
      originalAccessors indexWhere { orig =>
        (acc.name == orig.name) || (acc.name startsWith (orig.name + "$").toTermName)
      }
    }
    val arity = accessors.size
    // If this is ProductN[T1, T2, ...], accessorLub is the lub of T1, T2, ..., .
    // !!! Hidden behind -Xexperimental due to bummer type inference bugs.
    // Refining from Iterator[Any] leads to types like
    //
    //    Option[Int] { def productIterator: Iterator[String] }
    //
    // appearing legitimately, but this breaks invariant places
    // like Manifests and Arrays which are not robust and infer things
    // which they shouldn't.
    val accessorLub  = (
      if (opt.experimental)
        global.weakLub(accessors map (_.tpe.finalResultType))._1 match {
          case RefinedType(parents, decls) if !decls.isEmpty => intersectionType(parents)
          case tp                                            => tp
        }
      else AnyClass.tpe
    )

    def forwardToRuntime(method: Symbol): Tree =
      forwardMethod(method, getMember(ScalaRunTimeModule, "_" + method.name toTermName))(This(clazz) :: _)

    // Any member, including private
    def hasConcreteImpl(name: Name) =
      clazz.info.member(name).alternatives exists (m => !m.isDeferred && !m.isSynthetic)

    def hasOverridingImplementation(meth: Symbol) = {
      val sym = clazz.info nonPrivateMember meth.name
      sym.alternatives filterNot (_ eq meth) exists { m0 =>
        !m0.isDeferred && !m0.isSynthetic && (typeInClazz(m0) matches typeInClazz(meth))
      }
    }
    def readConstantValue[T](name: String, default: T = null.asInstanceOf[T]): T = {
      clazzMember(name.toTermName).info match {
        case NullaryMethodType(ConstantType(Constant(value))) => value.asInstanceOf[T]
        case _                                                => default
      }
    }
    def productIteratorMethod = {
      createMethod(nme.productIterator, iteratorOfType(accessorLub))(_ =>
        gen.mkMethodCall(ScalaRunTimeModule, "typedProductIterator", List(accessorLub), List(This(clazz)))
      )
    }
    def projectionMethod(accessor: Symbol, num: Int) = {
      createMethod(nme.productAccessorName(num), accessor.tpe.resultType)(_ => REF(accessor))
    }

    /** Common code for productElement and (currently disabled) productElementName
     */
    def perElementMethod(name: Name, returnType: Type)(caseFn: Symbol => Tree): Tree =
      createSwitchMethod(name, accessors.indices, returnType)(idx => caseFn(accessors(idx)))

    // def productElementNameMethod = perElementMethod(nme.productElementName, StringClass.tpe)(x => LIT(x.name.toString))

    /** The canEqual method for case classes.
     *    def canEqual(that: Any) = that.isInstanceOf[This]
     */
    def canEqualMethod: Tree = {
      createMethod(nme.canEqual_, List(AnyClass.tpe), BooleanClass.tpe)(m =>
        methodArg(m, 0) IS_OBJ clazzTypeToTest(clazz)
      )
    }

    /** The equality method for case classes.
     *  0 args:
     *    def equals(that: Any) = that.isInstanceOf[this.C] && that.asInstanceOf[this.C].canEqual(this)
     *  1+ args:
     *    def equals(that: Any) = (this eq that.asInstanceOf[AnyRef]) || {
     *      (that.isInstanceOf[this.C]) && {
     *        val x$1 = that.asInstanceOf[this.C]
     *        (this.arg_1 == x$1.arg_1) && (this.arg_2 == x$1.arg_2) && ... && (x$1 canEqual this)
     *       }
     *    }
     */
    def equalsClassMethod: Tree = createMethod(nme.equals_, List(AnyClass.tpe), BooleanClass.tpe) { m =>
      val arg0      = methodArg(m, 0)
      val thatTest  = gen.mkIsInstanceOf(arg0, clazzTypeToTest(clazz), true, false)
      val thatCast  = gen.mkCast(arg0, clazz.tpe)

      def argsBody: Tree = {
        val otherName = context.unit.freshTermName(clazz.name + "$")
        val otherSym  = m.newValue(m.pos, otherName) setInfo clazz.tpe setFlag SYNTHETIC
        val pairwise  = accessors map (acc => fn(Select(This(clazz), acc), acc.tpe member nme.EQ, Select(Ident(otherSym), acc)))
        val canEq     = gen.mkMethodCall(otherSym, nme.canEqual_, Nil, List(This(clazz)))
        def block     = Block(ValDef(otherSym, thatCast), AND(pairwise :+ canEq: _*))

        (This(clazz) ANY_EQ arg0) OR {
          thatTest AND Block(
            ValDef(otherSym, thatCast),
            AND(pairwise :+ canEq: _*)
          )
        }
      }
      if (accessors.isEmpty)
        thatTest AND ((thatCast DOT nme.canEqual_)(This(clazz)))
      else
        argsBody
    }

    /** The _1, _2, etc. methods to implement ProductN.
     */
    def productNMethods = {
      val accs = accessors.toIndexedSeq
      1 to arity map (num => productProj(arity, num) -> (() => projectionMethod(accs(num - 1), num)))
    }

    // methods for both classes and objects
    def productMethods = {
      List(
        Product_productPrefix   -> (() => constantNullary(nme.productPrefix, clazz.name.decode)),
        Product_productArity    -> (() => constantNullary(nme.productArity, arity)),
        Product_productElement  -> (() => perElementMethod(nme.productElement, accessorLub)(Ident)),
        Product_iterator        -> (() => productIteratorMethod),
        Product_canEqual        -> (() => canEqualMethod)
        // This is disabled pending a reimplementation which doesn't add any
        // weight to case classes (i.e. inspects the bytecode.)
        // Product_productElementName  -> (() => productElementNameMethod(accessors)),
      )
    }

    def caseClassMethods = productMethods ++ productNMethods ++ Seq(
      Object_hashCode -> (() => forwardToRuntime(Object_hashCode)),
      Object_toString -> (() => forwardToRuntime(Object_toString)),
      Object_equals   -> (() => equalsClassMethod)
    )

    def caseObjectMethods = productMethods ++ Seq(
      Object_hashCode -> (() => constantMethod(nme.hashCode_, clazz.name.decode.hashCode)),
      Object_toString -> (() => constantMethod(nme.toString_, clazz.name.decode))
      // Not needed, as reference equality is the default.
      // Object_equals   -> (() => createMethod(Object_equals)(m => This(clazz) ANY_EQ methodArg(m, 0)))
    )

    /** If you serialize a singleton and then deserialize it twice,
     *  you will have two instances of your singleton unless you implement
     *  readResolve.  Here it is implemented for all objects which have
     *  no implementation and which are marked serializable (which is true
     *  for all case objects.)
     */
    def needsReadResolve = (
         clazz.isModuleClass
      && clazz.isSerializable
      && !hasConcreteImpl(nme.readResolve)
    )

    def synthesize(): List[Tree] = {
      val methods = (
        if (!clazz.isCase) Nil
        else if (clazz.isModuleClass) caseObjectMethods
        else caseClassMethods
      )
      def impls = for ((m, impl) <- methods ; if !hasOverridingImplementation(m)) yield impl()
      def extras = (
        if (needsReadResolve) {
          // Aha, I finally decoded the original comment.
          // This method should be generated as private, but apparently if it is, then
          // it is name mangled afterward.  (Wonder why that is.) So it's only protected.
          // For sure special methods like "readResolve" should not be mangled.
          List(createMethod(nme.readResolve, Nil, ObjectClass.tpe)(m => { m setFlag PRIVATE ; REF(clazz.sourceModule) }))
        }
        else Nil
      )

      try impls ++ extras
      catch { case _: TypeError if reporter.hasErrors => Nil }
    }

    /** If this case class has any less than public accessors,
     *  adds new accessors at the correct locations to preserve ordering.
     *  Note that this must be done before the other method synthesis
     *  because synthesized methods need refer to the new symbols.
     *  Care must also be taken to preserve the case accessor order.
     */
    def caseTemplateBody(): List[Tree] = {
      val lb = ListBuffer[Tree]()
      def isRewrite(sym: Symbol) = sym.isCaseAccessorMethod && !sym.isPublic

      for (ddef @ DefDef(_, _, _, _, _, _) <- templ.body ; if isRewrite(ddef.symbol)) {
        val original = ddef.symbol
        val newAcc = deriveMethod(ddef.symbol, name => context.unit.freshTermName(name + "$")) { newAcc =>
          makeMethodPublic(newAcc)
          newAcc resetFlag (ACCESSOR | PARAMACCESSOR)
          ddef.rhs.duplicate
        }
        ddef.symbol resetFlag CASEACCESSOR
        lb += logResult("case accessor new")(newAcc)
      }

      lb ++= templ.body ++= synthesize() toList
    }

    if (phase.id > currentRun.typerPhase.id) templ
    else treeCopy.Template(templ, templ.parents, templ.self,
      if (clazz.isCase) caseTemplateBody()
      else synthesize() match {
        case Nil  => templ.body // avoiding unnecessary copy
        case ms   => templ.body ++ ms
      }
    )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}
