/*  NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author
 */

package scala.tools.nsc
package transform

import scala.collection.{ mutable, immutable }
import scala.collection.mutable.ListBuffer
import symtab.Flags._
import util.TreeSet

<<<<<<< HEAD
/** This phase converts classes with parameters into Java-like classes with 
 *  fields, which are assigned to from constructors.
 */  
=======
/** This phase converts classes with parameters into Java-like classes with
 *  fields, which are assigned to from constructors.
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
abstract class Constructors extends Transform with ast.TreeDSL {
  import global._
  import definitions._

  /** the following two members override abstract members in Transform */
  val phaseName: String = "constructors"

  protected def newTransformer(unit: CompilationUnit): Transformer =
    new ConstructorTransformer(unit)

  private val guardedCtorStats: mutable.Map[Symbol, List[Tree]] = new mutable.HashMap[Symbol, List[Tree]]
  private val ctorParams: mutable.Map[Symbol, List[Symbol]] = new mutable.HashMap[Symbol, List[Symbol]]

  class ConstructorTransformer(unit: CompilationUnit) extends Transformer {

    def transformClassTemplate(impl: Template): Template = {
      val clazz = impl.symbol.owner  // the transformed class
      val stats = impl.body          // the transformed template body
      val localTyper = typer.atOwner(impl, clazz)

      val specializedFlag: Symbol = clazz.info.decl(nme.SPECIALIZED_INSTANCE)
      val shouldGuard = (specializedFlag != NoSymbol) && !clazz.hasFlag(SPECIALIZED)

      case class ConstrInfo(
        constr: DefDef,               // The primary constructor
        constrParams: List[Symbol],   // ... and its parameters
        constrBody: Block             // ... and its body
      )
      // decompose primary constructor into the three entities above.
      val constrInfo: ConstrInfo = {
        val primary = stats find (_.symbol.isPrimaryConstructor)
        assert(primary.isDefined, "no constructor in template: impl = " + impl)
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val ddef @ DefDef(_, _, _, List(vparams), _, rhs @ Block(_, _)) = primary.get
        ConstrInfo(ddef, vparams map (_.symbol), rhs)
      }
      import constrInfo._

      // The parameter accessor fields which are members of the class
      val paramAccessors = clazz.constrParamAccessors

      // The constructor parameter corresponding to an accessor
<<<<<<< HEAD
      def parameter(acc: Symbol): Symbol = 
=======
      def parameter(acc: Symbol): Symbol =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        parameterNamed(nme.getterName(acc.originalName))

      // The constructor parameter with given name. This means the parameter
      // has given name, or starts with given name, and continues with a `$` afterwards.
      def parameterNamed(name: Name): Symbol = {
        def matchesName(param: Symbol) = param.name == name || param.name.startsWith(name + nme.NAME_JOIN_STRING)
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        (constrParams filter matchesName) match {
          case Nil    => assert(false, name + " not in " + constrParams) ; null
          case p :: _ => p
        }
      }

      var usesSpecializedField: Boolean = false

      // A transformer for expressions that go into the constructor
      val intoConstructorTransformer = new Transformer {
<<<<<<< HEAD
        def isParamRef(sym: Symbol) = 
          sym.isParamAccessor &&
          sym.owner == clazz &&
          !(clazz isSubClass DelayedInitClass) && 
=======
        def isParamRef(sym: Symbol) =
          sym.isParamAccessor &&
          sym.owner == clazz &&
          !(clazz isSubClass DelayedInitClass) &&
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          !(sym.isGetter && sym.accessed.isVariable) &&
          !sym.isSetter
        private def possiblySpecialized(s: Symbol) = specializeTypes.specializedTypeVars(s).nonEmpty
        override def transform(tree: Tree): Tree = tree match {
          case Apply(Select(This(_), _), List()) =>
            // references to parameter accessor methods of own class become references to parameters
<<<<<<< HEAD
            // outer accessors become references to $outer parameter 
=======
            // outer accessors become references to $outer parameter
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            if (isParamRef(tree.symbol) && !possiblySpecialized(tree.symbol))
              gen.mkAttributedIdent(parameter(tree.symbol.accessed)) setPos tree.pos
            else if (tree.symbol.outerSource == clazz && !clazz.isImplClass)
              gen.mkAttributedIdent(parameterNamed(nme.OUTER)) setPos tree.pos
<<<<<<< HEAD
            else 
              super.transform(tree)
          case Select(This(_), _) if (isParamRef(tree.symbol) && !possiblySpecialized(tree.symbol)) => 
=======
            else
              super.transform(tree)
          case Select(This(_), _) if (isParamRef(tree.symbol) && !possiblySpecialized(tree.symbol)) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            // references to parameter accessor field of own class become references to parameters
            gen.mkAttributedIdent(parameter(tree.symbol)) setPos tree.pos
          case Select(_, _) =>
            if (specializeTypes.specializedTypeVars(tree.symbol).nonEmpty)
              usesSpecializedField = true
            super.transform(tree)
          case _ =>
            super.transform(tree)
        }
      }

      // Move tree into constructor, take care of changing owner from `oldowner` to constructor symbol
      def intoConstructor(oldowner: Symbol, tree: Tree) =
        intoConstructorTransformer.transform(
          new ChangeOwnerTraverser(oldowner, constr.symbol)(tree))

      // Should tree be moved in front of super constructor call?
      def canBeMoved(tree: Tree) = tree match {
        case ValDef(mods, _, _, _) => (mods hasFlag PRESUPER | PARAMACCESSOR)
        case _                     => false
      }

      // Create an assignment to class field `to` with rhs `from`
      def mkAssign(to: Symbol, from: Tree): Tree =
        localTyper.typedPos(to.pos) { Assign(Select(This(clazz), to), from) }

<<<<<<< HEAD
      // Create code to copy parameter to parameter accessor field. 
=======
      // Create code to copy parameter to parameter accessor field.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // If parameter is $outer, check that it is not null so that we NPE
      // here instead of at some unknown future $outer access.
      def copyParam(to: Symbol, from: Symbol): Tree = {
        import CODE._
        val result = mkAssign(to, Ident(from))
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (from.name != nme.OUTER) result
        else localTyper.typedPos(to.pos) {
          IF (from OBJ_EQ NULL) THEN THROW(NullPointerExceptionClass) ELSE result
        }
      }

      // The list of definitions that go into class
      val defBuf = new ListBuffer[Tree]
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // The auxiliary constructors, separate from the defBuf since they should
      // follow the primary constructor
      val auxConstructorBuf = new ListBuffer[Tree]

      // The list of statements that go into constructor after and including the superclass constructor call
      val constrStatBuf = new ListBuffer[Tree]

      // The list of early initializer statements that go into constructor before the superclass constructor call
      val constrPrefixBuf = new ListBuffer[Tree]

      // The early initialized field definitions of the class (these are the class members)
      val presupers = treeInfo.preSuperFields(stats)

      // generate code to copy pre-initialized fields
      for (stat <- constrBody.stats) {
        constrStatBuf += stat
        stat match {
          case ValDef(mods, name, _, _) if (mods hasFlag PRESUPER) =>
            // stat is the constructor-local definition of the field value
            val fields = presupers filter (
              vdef => nme.localToGetter(vdef.name) == name)
            assert(fields.length == 1)
            val to = fields.head.symbol
            if (!to.tpe.isInstanceOf[ConstantType])
              constrStatBuf += mkAssign(to, Ident(stat.symbol))
          case _ =>
        }
      }

      // Triage all template definitions to go into defBuf/auxConstructorBuf, constrStatBuf, or constrPrefixBuf.
      for (stat <- stats) stat match {
        case DefDef(mods, name, tparams, vparamss, tpt, rhs) =>
          // methods with constant result type get literals as their body
          // all methods except the primary constructor go into template
          stat.symbol.tpe match {
            case MethodType(List(), tp @ ConstantType(c)) =>
              defBuf += treeCopy.DefDef(
                stat, mods, name, tparams, vparamss, tpt,
                Literal(c) setPos rhs.pos setType tp)
            case _ =>
              if (stat.symbol.isPrimaryConstructor) ()
              else if (stat.symbol.isConstructor) auxConstructorBuf += stat
              else defBuf += stat
          }
        case ValDef(mods, name, tpt, rhs) =>
          // val defs with constant right-hand sides are eliminated.
<<<<<<< HEAD
          // for all other val defs, an empty valdef goes into the template and 
=======
          // for all other val defs, an empty valdef goes into the template and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          // the initializer goes as an assignment into the constructor
          // if the val def is an early initialized or a parameter accessor, it goes
          // before the superclass constructor call, otherwise it goes after.
          // Lazy vals don't get the assignment in the constructor.
          if (!stat.symbol.tpe.isInstanceOf[ConstantType]) {
            if (rhs != EmptyTree && !stat.symbol.isLazy) {
              val rhs1 = intoConstructor(stat.symbol, rhs);
              (if (canBeMoved(stat)) constrPrefixBuf else constrStatBuf) += mkAssign(
                stat.symbol, rhs1)
            }
            defBuf += treeCopy.ValDef(stat, mods, name, tpt, EmptyTree)
          }
        case ClassDef(_, _, _, _) =>
          // classes are treated recursively, and left in the template
          defBuf += new ConstructorTransformer(unit).transform(stat)
        case _ =>
          // all other statements go into the constructor
          constrStatBuf += intoConstructor(impl.symbol, stat)
      }

      // ----------- avoid making fields for symbols that are not accessed --------------

      // A sorted set of symbols that are known to be accessed outside the primary constructor.
      val accessedSyms = new TreeSet[Symbol]((x, y) => x isLess y)

      // a list of outer accessor symbols and their bodies
      var outerAccessors: List[(Symbol, Tree)] = List()

      // Could symbol's definition be omitted, provided it is not accessed?
      // This is the case if the symbol is defined in the current class, and
      // ( the symbol is an object private parameter accessor field, or
      //   the symbol is an outer accessor of a final class which does not override another outer accessor. )
      def maybeOmittable(sym: Symbol) = sym.owner == clazz && (
        sym.isParamAccessor && sym.isPrivateLocal ||
<<<<<<< HEAD
        sym.isOuterAccessor && sym.owner.isFinal && !sym.isOverridingSymbol &&
=======
        sym.isOuterAccessor && sym.owner.isEffectivelyFinal && !sym.isOverridingSymbol &&
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        !(clazz isSubClass DelayedInitClass)
      )

      // Is symbol known to be accessed outside of the primary constructor,
<<<<<<< HEAD
      // or is it a symbol whose definition cannot be omitted anyway? 
=======
      // or is it a symbol whose definition cannot be omitted anyway?
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def mustbeKept(sym: Symbol) = !maybeOmittable(sym) || (accessedSyms contains sym)

      // A traverser to set accessedSyms and outerAccessors
      val accessTraverser = new Traverser {
        override def traverse(tree: Tree) = {
          tree match {
<<<<<<< HEAD
            case DefDef(_, _, _, _, _, body) 
            if (tree.symbol.isOuterAccessor && tree.symbol.owner == clazz && clazz.isFinal) =>
=======
            case DefDef(_, _, _, _, _, body)
            if (tree.symbol.isOuterAccessor && tree.symbol.owner == clazz && clazz.isEffectivelyFinal) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              log("outerAccessors += " + tree.symbol.fullName)
              outerAccessors ::= ((tree.symbol, body))
            case Select(_, _) =>
              if (!mustbeKept(tree.symbol)) {
                log("accessedSyms += " + tree.symbol.fullName)
                accessedSyms addEntry tree.symbol
              }
              super.traverse(tree)
            case _ =>
              super.traverse(tree)
          }
        }
      }

<<<<<<< HEAD
      // first traverse all definitions except outeraccesors 
      // (outeraccessors are avoided in accessTraverser)
      for (stat <- defBuf.iterator ++ auxConstructorBuf.iterator)
        accessTraverser.traverse(stat) 
=======
      // first traverse all definitions except outeraccesors
      // (outeraccessors are avoided in accessTraverser)
      for (stat <- defBuf.iterator ++ auxConstructorBuf.iterator)
        accessTraverser.traverse(stat)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

      // then traverse all bodies of outeraccessors which are accessed themselves
      // note: this relies on the fact that an outer accessor never calls another
      // outer accessor in the same class.
<<<<<<< HEAD
      for ((accSym, accBody) <- outerAccessors) 
=======
      for ((accSym, accBody) <- outerAccessors)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (mustbeKept(accSym)) accessTraverser.traverse(accBody)

      // Conflicting symbol list from parents: see bug #1960.
      // It would be better to mangle the constructor parameter name since
      // it can only be used internally, but I think we need more robust name
      // mangling before we introduce more of it.
      val parentSymbols = Map((for {
        p <- impl.parents
        if p.symbol.isTrait
        sym <- p.symbol.info.nonPrivateMembers
        if sym.isGetter && !sym.isOuterField
      } yield sym.name -> p): _*)

      // Initialize all parameters fields that must be kept.
<<<<<<< HEAD
      val paramInits = 
        for (acc <- paramAccessors if mustbeKept(acc)) yield {          
          if (parentSymbols contains acc.name)
            unit.error(acc.pos, "parameter '%s' requires field but conflicts with %s in '%s'".format(
              acc.name, acc.name, parentSymbols(acc.name)))
          
=======
      val paramInits =
        for (acc <- paramAccessors if mustbeKept(acc)) yield {
          if (parentSymbols contains acc.name)
            unit.error(acc.pos, "parameter '%s' requires field but conflicts with %s in '%s'".format(
              acc.name, acc.name, parentSymbols(acc.name)))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          copyParam(acc, parameter(acc))
        }

      /** Return a single list of statements, merging the generic class constructor with the
       *  specialized stats. The original statements are retyped in the current class, and
       *  assignments to generic fields that have a corresponding specialized assignment in
       *  `specializedStats` are replaced by the specialized assignment.
       */
      def mergeConstructors(genericClazz: Symbol, originalStats: List[Tree], specializedStats: List[Tree]): List[Tree] = {
        val specBuf = new ListBuffer[Tree]
        specBuf ++= specializedStats

        def specializedAssignFor(sym: Symbol): Option[Tree] =
          specializedStats.find {
            case Assign(sel @ Select(This(_), _), rhs) if sel.symbol.hasFlag(SPECIALIZED) =>
              val (generic, _, _) = nme.splitSpecializedName(nme.localToGetter(sel.symbol.name))
              generic == nme.localToGetter(sym.name)
            case _ => false
          }

        /** Rewrite calls to ScalaRunTime.array_update to the proper apply method in scala.Array.
         *  Erasure transforms Array.update to ScalaRunTime.update when the element type is a type
         *  variable, but after specialization this is a concrete primitive type, so it would
         *  be an error to pass it to array_update(.., .., Object).
         */
        def rewriteArrayUpdate(tree: Tree): Tree = {
          val array_update = definitions.ScalaRunTimeModule.info.member("array_update")
          val adapter = new Transformer {
            override def transform(t: Tree): Tree = t match {
              case Apply(fun @ Select(receiver, method), List(xs, idx, v)) if fun.symbol == array_update =>
                localTyper.typed(Apply(gen.mkAttributedSelect(xs, definitions.Array_update), List(idx, v)))
              case _ => super.transform(t)
            }
          }
          adapter.transform(tree)
        }

        log("merging: " + originalStats.mkString("\n") + "\nwith\n" + specializedStats.mkString("\n"))
<<<<<<< HEAD
        val res = for (s <- originalStats; val stat = s.duplicate) yield {
=======
        val res = for (s <- originalStats; stat = s.duplicate) yield {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          log("merge: looking at " + stat)
          val stat1 = stat match {
            case Assign(sel @ Select(This(_), field), _) =>
              specializedAssignFor(sel.symbol).getOrElse(stat)
            case _ => stat
          }
          if (stat1 ne stat) {
            log("replaced " + stat + " with " + stat1)
            specBuf -= stat1
          }

          if (stat1 eq stat) {
            assert(ctorParams(genericClazz).length == constrParams.length)
            // this is just to make private fields public
            (new specializeTypes.ImplementationAdapter(ctorParams(genericClazz), constrParams, null, true))(stat1)

            val stat2 = rewriteArrayUpdate(stat1)
            // statements coming from the original class need retyping in the current context
            debuglog("retyping " + stat2)
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val d = new specializeTypes.Duplicator
            d.retyped(localTyper.context1.asInstanceOf[d.Context],
                      stat2,
                      genericClazz,
                      clazz,
                      Map.empty)
          } else
            stat1
        }
        if (specBuf.nonEmpty)
          println("residual specialized constructor statements: " + specBuf)
        res
      }

      /** Add an 'if' around the statements coming after the super constructor. This
       *  guard is necessary if the code uses specialized fields. A specialized field is
       *  initialized in the subclass constructor, but the accessors are (already) overridden
       *  and pointing to the (empty) fields. To fix this, a class with specialized fields
       *  will not run its constructor statements if the instance is specialized. The specialized
       *  subclass includes a copy of those constructor statements, and runs them. To flag that a class
       *  has specialized fields, and their initialization should be deferred to the subclass, method
       *  'specInstance$' is added in phase specialize.
       */
      def guardSpecializedInitializer(stats: List[Tree]): List[Tree] = if (settings.nospecialization.value) stats else {
        // split the statements in presuper and postsuper
    //    var (prefix, postfix) = stats0.span(tree => !((tree.symbol ne null) && tree.symbol.isConstructor))
      //  if (postfix.nonEmpty) {
        //  prefix = prefix :+ postfix.head
          //postfix = postfix.tail
        //}

        if (usesSpecializedField && shouldGuard && stats.nonEmpty) {
          // save them for duplication in the specialized subclass
          guardedCtorStats(clazz) = stats
          ctorParams(clazz) = constrParams

          val tree =
            If(
              Apply(
                Select(
                  Apply(gen.mkAttributedRef(specializedFlag), List()),
                  definitions.getMember(definitions.BooleanClass, nme.UNARY_!)),
                List()),
              Block(stats, Literal(Constant())),
              EmptyTree)

          List(localTyper.typed(tree))
        } else if (clazz.hasFlag(SPECIALIZED)) {
          // add initialization from its generic class constructor
          val (genericName, _, _) = nme.splitSpecializedName(clazz.name)
          val genericClazz = clazz.owner.info.decl(genericName.toTypeName)
          assert(genericClazz != NoSymbol)

          guardedCtorStats.get(genericClazz) match {
            case Some(stats1) => mergeConstructors(genericClazz, stats1, stats)
            case None => stats
          }
        } else stats
      }
/*
      def isInitDef(stat: Tree) = stat match {
        case dd: DefDef => dd.symbol == delayedInitMethod
        case _ => false
      }
*/
<<<<<<< HEAD
      
      /** Create a getter or a setter and enter into `clazz` scope
       */
      def addAccessor(sym: Symbol, name: TermName, flags: Long) = {
        val m = clazz.newMethod(sym.pos, name)
          .setFlag(flags & ~LOCAL & ~PRIVATE)
        m.privateWithin = clazz
        clazz.info.decls.enter(m)
        m
      }
      
=======

      /** Create a getter or a setter and enter into `clazz` scope
       */
      def addAccessor(sym: Symbol, name: TermName, flags: Long) = {
        val m = (
          clazz.newMethod(sym.pos, name)
            setFlag (flags & ~LOCAL & ~PRIVATE)
            setPrivateWithin clazz
        )
        clazz.info.decls enter m
      }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def addGetter(sym: Symbol): Symbol = {
        val getr = addAccessor(
          sym, nme.getterName(sym.name), getterFlags(sym.flags))
        getr setInfo MethodType(List(), sym.tpe)
        defBuf += localTyper.typed {
          //util.trace("adding getter def for "+getr) {
          atPos(sym.pos) {
            DefDef(getr, Select(This(clazz), sym))
          }//}
        }
        getr
      }
<<<<<<< HEAD
      
      def addSetter(sym: Symbol): Symbol = {
        sym setFlag MUTABLE
        val setr = addAccessor(
          sym, nme.getterToSetter(nme.getterName(sym.name)), setterFlags(sym.flags)) 
=======

      def addSetter(sym: Symbol): Symbol = {
        sym setFlag MUTABLE
        val setr = addAccessor(
          sym, nme.getterToSetter(nme.getterName(sym.name)), setterFlags(sym.flags))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        setr setInfo MethodType(setr.newSyntheticValueParams(List(sym.tpe)), UnitClass.tpe)
        defBuf += localTyper.typed {
          //util.trace("adding setter def for "+setr) {
          atPos(sym.pos) {
<<<<<<< HEAD
            DefDef(setr, paramss => 
=======
            DefDef(setr, paramss =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              Assign(Select(This(clazz), sym), Ident(paramss.head.head)))
          }//}
        }
        setr
      }
<<<<<<< HEAD
      
      def ensureAccessor(sym: Symbol)(acc: => Symbol) = 
=======

      def ensureAccessor(sym: Symbol)(acc: => Symbol) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (sym.owner == clazz && !sym.isMethod && sym.isPrivate) { // there's an access to a naked field of the enclosing class
          var getr = acc
          getr makeNotPrivate clazz
          getr
        } else {
          if (sym.owner == clazz) sym makeNotPrivate clazz
          NoSymbol
        }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def ensureGetter(sym: Symbol): Symbol = ensureAccessor(sym) {
        val getr = sym.getter(clazz)
        if (getr != NoSymbol) getr else addGetter(sym)
      }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def ensureSetter(sym: Symbol): Symbol = ensureAccessor(sym) {
        var setr = sym.setter(clazz, hasExpandedName = false)
        if (setr == NoSymbol) setr = sym.setter(clazz, hasExpandedName = true)
        if (setr == NoSymbol) setr = addSetter(sym)
        setr
      }
<<<<<<< HEAD
      
      def delayedInitClosure(stats: List[Tree]) = 
        localTyper.typed {
          atPos(impl.pos) { 
=======

      def delayedInitClosure(stats: List[Tree]) =
        localTyper.typed {
          atPos(impl.pos) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            val closureClass = clazz.newClass(impl.pos, nme.delayedInitArg.toTypeName)
              .setFlag(SYNTHETIC | FINAL)
            val closureParents = List(AbstractFunctionClass(0).tpe, ScalaObjectClass.tpe)
            closureClass.setInfo(new ClassInfoType(closureParents, new Scope, closureClass))

            val outerField = closureClass.newValue(impl.pos, nme.OUTER)
<<<<<<< HEAD
              .setFlag(PRIVATE | LOCAL | PARAMACCESSOR)
=======
              .setFlag(PrivateLocal | PARAMACCESSOR)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              .setInfo(clazz.tpe)

            val applyMethod = closureClass.newMethod(impl.pos, nme.apply)
              .setFlag(FINAL)
              .setInfo(MethodType(List(), ObjectClass.tpe))

            closureClass.info.decls enter outerField
            closureClass.info.decls enter applyMethod

            val outerFieldDef = ValDef(outerField)

            val changeOwner = new ChangeOwnerTraverser(impl.symbol, applyMethod)

            val closureClassTyper = localTyper.atOwner(closureClass)
            val applyMethodTyper = closureClassTyper.atOwner(applyMethod)

            val constrStatTransformer = new Transformer {
              override def transform(tree: Tree): Tree = tree match {
<<<<<<< HEAD
                case This(_) if tree.symbol == clazz => 
=======
                case This(_) if tree.symbol == clazz =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                  applyMethodTyper.typed {
                    atPos(tree.pos) {
                      Select(This(closureClass), outerField)
                    }
                  }
                case _ =>
                  super.transform {
                    tree match {
<<<<<<< HEAD
                      case Select(qual, _) => 
=======
                      case Select(qual, _) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                        val getter = ensureGetter(tree.symbol)
                        if (getter != NoSymbol)
                          applyMethodTyper.typed {
                            atPos(tree.pos) {
                              Apply(Select(qual, getter), List())
                            }
                          }
                        else tree
                      case Assign(lhs @ Select(qual, _), rhs) =>
                        val setter = ensureSetter(lhs.symbol)
                        if (setter != NoSymbol)
                          applyMethodTyper.typed {
                            atPos(tree.pos) {
                              Apply(Select(qual, setter), List(rhs))
                            }
                          }
                        else tree
<<<<<<< HEAD
                      case _ => 
                        changeOwner.changeOwner(tree)
                        tree
                    }
                  } 
=======
                      case _ =>
                        changeOwner.changeOwner(tree)
                        tree
                    }
                  }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              }
            }

            def applyMethodStats = constrStatTransformer.transformTrees(stats)

            val applyMethodDef = DefDef(
              sym = applyMethod,
              vparamss = List(List()),
              rhs = Block(applyMethodStats, gen.mkAttributedRef(BoxedUnit_UNIT)))
<<<<<<< HEAD
              
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            ClassDef(
              sym = closureClass,
              constrMods = Modifiers(0),
              vparamss = List(List(outerFieldDef)),
              argss = List(List()),
              body = List(applyMethodDef),
              superPos = impl.pos)
          }
        }

<<<<<<< HEAD
      def delayedInitCall(closure: Tree) = 
        localTyper.typed {
          atPos(impl.pos) { 
            Apply(
              Select(This(clazz), delayedInitMethod), 
=======
      def delayedInitCall(closure: Tree) =
        localTyper.typed {
          atPos(impl.pos) {
            Apply(
              Select(This(clazz), delayedInitMethod),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              List(New(TypeTree(closure.symbol.tpe), List(List(This(clazz))))))
          }
        }

      /** Return a pair consisting of (all statements up to and including superclass and trait constr calls, rest) */
      def splitAtSuper(stats: List[Tree]) = {
        def isConstr(tree: Tree) = (tree.symbol ne null) && tree.symbol.isConstructor
        val (pre, rest0) = stats span (!isConstr(_))
        val (supercalls, rest) = rest0 span (isConstr(_))
        (pre ::: supercalls, rest)
      }

      var (uptoSuperStats, remainingConstrStats) = splitAtSuper(constrStatBuf.toList)

      /** XXX This is not corect: remainingConstrStats.nonEmpty excludes too much,
       *  but excluding it includes too much.  The constructor sequence being mimicked
       *  needs to be reproduced with total fidelity.
       *
       *  See test case files/run/bug4680.scala, the output of which is wrong in many
       *  particulars.
       */
      val needsDelayedInit =
        (clazz isSubClass DelayedInitClass) /*&& !(defBuf exists isInitDef)*/ && remainingConstrStats.nonEmpty

      if (needsDelayedInit) {
        val dicl = new ConstructorTransformer(unit) transform delayedInitClosure(remainingConstrStats)
        defBuf += dicl
        remainingConstrStats = List(delayedInitCall(dicl))
      }

      // Assemble final constructor
      defBuf += treeCopy.DefDef(
        constr, constr.mods, constr.name, constr.tparams, constr.vparamss, constr.tpt,
        treeCopy.Block(
          constrBody,
<<<<<<< HEAD
          paramInits ::: constrPrefixBuf.toList ::: uptoSuperStats ::: 
=======
          paramInits ::: constrPrefixBuf.toList ::: uptoSuperStats :::
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            guardSpecializedInitializer(remainingConstrStats),
          constrBody.expr));

      // Followed by any auxiliary constructors
      defBuf ++= auxConstructorBuf
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // Unlink all fields that can be dropped from class scope
      for (sym <- clazz.info.decls ; if !mustbeKept(sym))
        clazz.info.decls unlink sym

      // Eliminate all field definitions that can be dropped from template
<<<<<<< HEAD
      treeCopy.Template(impl, impl.parents, impl.self, 
        defBuf.toList filter (stat => mustbeKept(stat.symbol)))
    } // transformClassTemplate

    override def transform(tree: Tree): Tree = 
=======
      treeCopy.Template(impl, impl.parents, impl.self,
        defBuf.toList filter (stat => mustbeKept(stat.symbol)))
    } // transformClassTemplate

    override def transform(tree: Tree): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      tree match {
        case ClassDef(mods, name, tparams, impl) if !tree.symbol.isInterface && !isValueClass(tree.symbol) =>
          treeCopy.ClassDef(tree, mods, name, tparams, transformClassTemplate(impl))
        case _ =>
          super.transform(tree)
      }
  } // ConstructorTransformer
}
