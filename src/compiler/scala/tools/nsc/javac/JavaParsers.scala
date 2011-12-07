/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */
//todo: allow infix type patterns


package scala.tools.nsc
package javac

import scala.tools.nsc.util.OffsetPosition
import scala.collection.mutable.ListBuffer
import symtab.Flags
import JavaTokens._

trait JavaParsers extends ast.parser.ParsersCommon with JavaScanners {
<<<<<<< HEAD
  val global : Global 
=======
  val global : Global
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  import global._
  import definitions._

  case class JavaOpInfo(operand: Tree, operator: Name, pos: Int)

  class JavaUnitParser(val unit: global.CompilationUnit) extends JavaParser {
    val in = new JavaUnitScanner(unit)
    def freshName(prefix: String): Name = freshTermName(prefix)
    def freshTermName(prefix: String): TermName = unit.freshTermName(prefix)
    def freshTypeName(prefix: String): TypeName = unit.freshTypeName(prefix)
    def deprecationWarning(off: Int, msg: String) = unit.deprecationWarning(off, msg)
    implicit def i2p(offset : Int) : Position = new OffsetPosition(unit.source, offset)
    def warning(pos : Int, msg : String) : Unit = unit.warning(pos, msg)
    def syntaxError(pos: Int, msg: String) : Unit = unit.error(pos, msg)
  }

  abstract class JavaParser extends ParserCommon {
    val in: JavaScanner

    protected def posToReport: Int = in.currentPos
    def freshName(prefix : String): Name
    protected implicit def i2p(offset : Int) : Position
    private implicit def p2i(pos : Position): Int = if (pos.isDefined) pos.point else -1

    /** The simple name of the package of the currently parsed file */
    private var thisPackageName: TypeName = tpnme.EMPTY
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** this is the general parse method
     */
    def parse(): Tree = {
      val t = compilationUnit()
      accept(EOF)
      t
    }

    // -------- error handling ---------------------------------------

    private var lastErrorPos : Int = -1

    protected def skip() {
      var nparens = 0
      var nbraces = 0
      while (true) {
        in.token match {
          case EOF =>
            return
          case SEMI =>
            if (nparens == 0 && nbraces == 0) return
          case RPAREN =>
            nparens -= 1
          case RBRACE =>
            if (nbraces == 0) return
            nbraces -= 1
          case LPAREN =>
            nparens += 1
          case LBRACE =>
            nbraces += 1
          case _ =>
        }
        in.nextToken
      }
    }

    def warning(pos : Int, msg : String) : Unit
    def syntaxError(pos: Int, msg: String) : Unit
    def syntaxError(msg: String, skipIt: Boolean) {
      syntaxError(in.currentPos, msg, skipIt)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def syntaxError(pos: Int, msg: String, skipIt: Boolean) {
      if (pos > lastErrorPos) {
        syntaxError(pos, msg)
        // no more errors on this token.
        lastErrorPos = in.currentPos
      }
<<<<<<< HEAD
      if (skipIt) 
        skip()
    }
    def warning(msg: String) : Unit = warning(in.currentPos, msg)
        
=======
      if (skipIt)
        skip()
    }
    def warning(msg: String) : Unit = warning(in.currentPos, msg)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def errorTypeTree = TypeTree().setType(ErrorType) setPos in.currentPos
    def errorTermTree = Literal(Constant(null)) setPos in.currentPos
    def errorPatternTree = blankExpr setPos in.currentPos

    // --------- tree building -----------------------------

    import gen.{ rootId, scalaDot }

<<<<<<< HEAD
    def javaDot(name: Name): Tree = 
=======
    def javaDot(name: Name): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      Select(rootId(nme.java), name)

    def javaLangDot(name: Name): Tree =
      Select(javaDot(nme.lang), name)

    def javaLangObject(): Tree = javaLangDot(tpnme.Object)

<<<<<<< HEAD
    def arrayOf(tpt: Tree) = 
=======
    def arrayOf(tpt: Tree) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      AppliedTypeTree(scalaDot(tpnme.Array), List(tpt))

    def blankExpr = Ident(nme.WILDCARD)

<<<<<<< HEAD
    def makePackaging(pkg: RefTree, stats: List[Tree]): PackageDef = 
=======
    def makePackaging(pkg: RefTree, stats: List[Tree]): PackageDef =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      atPos(pkg.pos) {  PackageDef(pkg, stats) }

    def makeTemplate(parents: List[Tree], stats: List[Tree]) =
      Template(
<<<<<<< HEAD
        parents, 
=======
        parents,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        emptyValDef,
        if (treeInfo.firstConstructor(stats) == EmptyTree) makeConstructor(List()) :: stats
        else stats)

    def makeParam(name: String, tpt: Tree) =
      ValDef(Modifiers(Flags.JAVA | Flags.PARAM), newTermName(name), tpt, EmptyTree)

    def makeConstructor(formals: List[Tree]) = {
      val vparams = formals.zipWithIndex map { case (p, i) => makeParam("x$" + (i + 1), p) }
      DefDef(Modifiers(Flags.JAVA), nme.CONSTRUCTOR, List(), List(vparams), TypeTree(), blankExpr)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // ------------- general parsing ---------------------------

    /** skip parent or brace enclosed sequence of things */
    def skipAhead() {
      var nparens = 0
      var nbraces = 0
      do {
        in.token match {
          case LPAREN =>
            nparens += 1
          case LBRACE =>
            nbraces += 1
          case _ =>
        }
        in.nextToken
        in.token match {
          case RPAREN =>
            nparens -= 1
          case RBRACE =>
            nbraces -= 1
          case _ =>
        }
      } while (in.token != EOF && (nparens > 0 || nbraces > 0))
    }

    def skipTo(tokens: Int*) {
      while (!(tokens contains in.token) && in.token != EOF) {
        if (in.token == LBRACE) { skipAhead(); accept(RBRACE) }
        else if (in.token == LPAREN) { skipAhead(); accept(RPAREN) }
        else in.nextToken
      }
    }

    /** Consume one token of the specified type, or
      * signal an error if it is not there.
      */
    def accept(token: Int): Int = {
      val pos = in.currentPos
      if (in.token != token) {
<<<<<<< HEAD
        val posToReport = 
=======
        val posToReport =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          //if (in.currentPos.line(unit.source).get(0) > in.lastPos.line(unit.source).get(0))
          //  in.lastPos
          //else
            in.currentPos
        val msg =
          JavaScannerConfiguration.token2string(token) + " expected but " +
            JavaScannerConfiguration.token2string(in.token) + " found."

        syntaxError(posToReport, msg, true)
      }
      if (in.token == token) in.nextToken
      pos
    }

    def acceptClosingAngle() {
      val closers: PartialFunction[Int, Int] = {
        case GTGTGTEQ => GTGTEQ
        case GTGTGT   => GTGT
        case GTGTEQ   => GTEQ
        case GTGT     => GT
        case GTEQ     => ASSIGN
      }
      if (closers isDefinedAt in.token) in.token = closers(in.token)
      else accept(GT)
    }

    def identForType(): TypeName = ident().toTypeName
    def ident(): Name =
      if (in.token == IDENTIFIER) {
        val name = in.name
        in.nextToken
        name
      } else {
        accept(IDENTIFIER)
        nme.ERROR
      }

    def repsep[T <: Tree](p: () => T, sep: Int): List[T] = {
      val buf = ListBuffer[T](p())
      while (in.token == sep) {
        in.nextToken
        buf += p()
      }
      buf.toList
    }

    /** Convert (qual)ident to type identifier
     */
    def convertToTypeId(tree: Tree): Tree = gen.convertToTypeName(tree) match {
      case Some(t)  => t setPos tree.pos
      case _        => tree match {
        case AppliedTypeTree(_, _) | ExistentialTypeTree(_, _) | SelectFromTypeTree(_, _) =>
          tree
        case _ =>
          syntaxError(tree.pos, "identifier expected", false)
          errorTypeTree
      }
    }

    // -------------------- specific parsing routines ------------------

    def qualId(): RefTree = {
      var t: RefTree = atPos(in.currentPos) { Ident(ident()) }
<<<<<<< HEAD
      while (in.token == DOT) { 
=======
      while (in.token == DOT) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        in.nextToken
        t = atPos(in.currentPos) { Select(t, ident()) }
      }
      t
    }

<<<<<<< HEAD
    def optArrayBrackets(tpt: Tree): Tree = 
=======
    def optArrayBrackets(tpt: Tree): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (in.token == LBRACKET) {
        val tpt1 = atPos(in.pos) { arrayOf(tpt) }
        in.nextToken
        accept(RBRACKET)
        optArrayBrackets(tpt1)
      } else tpt

<<<<<<< HEAD
    def basicType(): Tree = 
=======
    def basicType(): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      atPos(in.pos) {
        in.token match {
          case BYTE => in.nextToken; TypeTree(ByteClass.tpe)
          case SHORT => in.nextToken; TypeTree(ShortClass.tpe)
          case CHAR => in.nextToken; TypeTree(CharClass.tpe)
          case INT => in.nextToken; TypeTree(IntClass.tpe)
          case LONG => in.nextToken; TypeTree(LongClass.tpe)
          case FLOAT => in.nextToken; TypeTree(FloatClass.tpe)
          case DOUBLE => in.nextToken; TypeTree(DoubleClass.tpe)
          case BOOLEAN => in.nextToken; TypeTree(BooleanClass.tpe)
          case _ => syntaxError("illegal start of type", true); errorTypeTree
        }
      }

    def typ(): Tree =
      optArrayBrackets {
        if (in.token == FINAL) in.nextToken
        if (in.token == IDENTIFIER) {
          var t = typeArgs(atPos(in.currentPos)(Ident(ident())))
          // typeSelect generates Select nodes is the lhs is an Ident or Select,
          // SelectFromTypeTree otherwise. See #3567.
          // Select nodes can be later
          // converted in the typechecker to SelectFromTypeTree if the class
          // turns out to be an instance ionner class instead of a static inner class.
          def typeSelect(t: Tree, name: Name) = t match {
            case Ident(_) | Select(_, _) => Select(t, name)
            case _ => SelectFromTypeTree(t, name.toTypeName)
          }
          while (in.token == DOT) {
            in.nextToken
            t = typeArgs(atPos(in.currentPos)(typeSelect(t, ident())))
          }
          convertToTypeId(t)
        } else {
          basicType()
        }
      }

    def typeArgs(t: Tree): Tree = {
      val wildcards = new ListBuffer[TypeDef]
<<<<<<< HEAD
      def typeArg(): Tree = 
=======
      def typeArg(): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (in.token == QMARK) {
          val pos = in.currentPos
          in.nextToken
          var lo: Tree = TypeTree(NothingClass.tpe)
          var hi: Tree = TypeTree(AnyClass.tpe)
          if (in.token == EXTENDS) {
            in.nextToken
            hi = typ()
          } else if (in.token == SUPER) {
            in.nextToken
            lo = typ()
          }
          val tdef = atPos(pos) {
            TypeDef(
<<<<<<< HEAD
              Modifiers(Flags.JAVA | Flags.DEFERRED), 
              newTypeName("_$"+ (wildcards.length + 1)),
              List(), 
=======
              Modifiers(Flags.JAVA | Flags.DEFERRED),
              newTypeName("_$"+ (wildcards.length + 1)),
              List(),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              TypeBoundsTree(lo, hi))
          }
          wildcards += tdef
          atPos(pos) { Ident(tdef.name) }
        } else {
          typ()
        }
      if (in.token == LT) {
        in.nextToken
        val t1 = convertToTypeId(t)
        val args = repsep(typeArg, COMMA)
        acceptClosingAngle()
        atPos(t1.pos) {
          val t2: Tree = AppliedTypeTree(t1, args)
          if (wildcards.isEmpty) t2
          else ExistentialTypeTree(t2, wildcards.toList)
        }
      } else t
    }

    def annotations(): List[Tree] = {
      //var annots = new ListBuffer[Tree]
      while (in.token == AT) {
        in.nextToken
        annotation()
      }
      List() // don't pass on annotations for now
    }

    /** Annotation ::= TypeName [`(` AnnotationArgument {`,` AnnotationArgument} `)`]
     */
    def annotation() {
      val pos = in.currentPos
      var t = qualId()
      if (in.token == LPAREN) { skipAhead(); accept(RPAREN) }
      else if (in.token == LBRACE) { skipAhead(); accept(RBRACE) }
    }
/*
    def annotationArg() = {
      val pos = in.token
      if (in.token == IDENTIFIER && in.lookaheadToken == ASSIGN) {
        val name = ident()
        accept(ASSIGN)
        atPos(pos) {
          ValDef(Modifiers(Flags.JAVA), name, TypeTree(), elementValue())
        }
      } else {
        elementValue()
      }
    }

    def elementValue(): Tree =
      if (in.token == AT) annotation()
      else if (in.token == LBRACE) elementValueArrayInitializer()
      else expression1()

    def elementValueArrayInitializer() = {
      accept(LBRACE)
      val buf = new ListBuffer[Tree]
      def loop() =
        if (in.token != RBRACE) {
          buf += elementValue()
          if (in.token == COMMA) {
            in.nextToken
            loop()
          }
        }
      loop()
      accept(RBRACE)
      buf.toList
    }
 */

    def modifiers(inInterface: Boolean): Modifiers = {
      var flags: Long = Flags.JAVA
      // assumed true unless we see public/private/protected
      var isPackageAccess = true
      var annots: List[Tree] = Nil
      def addAnnot(sym: Symbol) =
        annots :+= New(TypeTree(sym.tpe), List(Nil))

      while (true) {
        in.token match {
<<<<<<< HEAD
          case AT if (in.lookaheadToken != INTERFACE) => 
            in.nextToken
            annotation()
          case PUBLIC => 
            isPackageAccess = false
            in.nextToken
          case PROTECTED => 
=======
          case AT if (in.lookaheadToken != INTERFACE) =>
            in.nextToken
            annotation()
          case PUBLIC =>
            isPackageAccess = false
            in.nextToken
          case PROTECTED =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            flags |= Flags.PROTECTED
            in.nextToken
          case PRIVATE =>
            isPackageAccess = false
            flags |= Flags.PRIVATE
            in.nextToken
<<<<<<< HEAD
          case STATIC =>  
=======
          case STATIC =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            flags |= Flags.STATIC
            in.nextToken
          case ABSTRACT =>
            flags |= Flags.ABSTRACT
            in.nextToken
          case FINAL =>
            flags |= Flags.FINAL
            in.nextToken
          case NATIVE =>
            addAnnot(NativeAttr)
            in.nextToken
          case TRANSIENT =>
            addAnnot(TransientAttr)
            in.nextToken
          case VOLATILE =>
            addAnnot(VolatileAttr)
            in.nextToken
          case SYNCHRONIZED | STRICTFP =>
            in.nextToken
          case _ =>
            val privateWithin: TypeName =
              if (isPackageAccess && !inInterface) thisPackageName
              else tpnme.EMPTY
<<<<<<< HEAD
            
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            return Modifiers(flags, privateWithin) withAnnotations annots
        }
      }
      abort("should not be here")
    }

    def typeParams(): List[TypeDef] =
      if (in.token == LT) {
        in.nextToken
        val tparams = repsep(typeParam, COMMA)
        acceptClosingAngle()
        tparams
      } else List()

<<<<<<< HEAD
    def typeParam(): TypeDef = 
      atPos(in.currentPos) {
        val name = identForType()
        val hi = 
=======
    def typeParam(): TypeDef =
      atPos(in.currentPos) {
        val name = identForType()
        val hi =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          if (in.token == EXTENDS) {
            in.nextToken
            bound()
          } else {
            scalaDot(tpnme.Any)
          }
<<<<<<< HEAD
        TypeDef(Modifiers(Flags.JAVA | Flags.DEFERRED | Flags.PARAM), name, List(), 
                TypeBoundsTree(scalaDot(tpnme.Nothing), hi))
      }

    def bound(): Tree = 
=======
        TypeDef(Modifiers(Flags.JAVA | Flags.DEFERRED | Flags.PARAM), name, List(),
                TypeBoundsTree(scalaDot(tpnme.Nothing), hi))
      }

    def bound(): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      atPos(in.currentPos) {
        val buf = ListBuffer[Tree](typ())
        while (in.token == AMP) {
          in.nextToken
          buf += typ()
        }
        val ts = buf.toList
        if (ts.tail.isEmpty) ts.head
        else CompoundTypeTree(Template(ts, emptyValDef, List()))
      }
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def formalParams(): List[ValDef] = {
      accept(LPAREN)
      val vparams = if (in.token == RPAREN) List() else repsep(formalParam, COMMA)
      accept(RPAREN)
      vparams
    }

    def formalParam(): ValDef = {
      if (in.token == FINAL) in.nextToken
      annotations()
      var t = typ()
      if (in.token == DOTDOTDOT) {
        in.nextToken
        t = atPos(t.pos) {
          AppliedTypeTree(scalaDot(tpnme.JAVA_REPEATED_PARAM_CLASS_NAME), List(t))
        }
      }
     varDecl(in.currentPos, Modifiers(Flags.JAVA | Flags.PARAM), t, ident())
    }

    def optThrows() {
      if (in.token == THROWS) {
        in.nextToken
        repsep(typ, COMMA)
      }
    }

    def methodBody(): Tree = {
      skipAhead()
      accept(RBRACE) // skip block
      blankExpr
<<<<<<< HEAD
    } 
=======
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    def definesInterface(token: Int) = token == INTERFACE || token == AT

    def termDecl(mods: Modifiers, parentToken: Int): List[Tree] = {
      val inInterface = definesInterface(parentToken)
      val tparams = if (in.token == LT) typeParams() else List()
      val isVoid = in.token == VOID
      var rtpt =
        if (isVoid) {
          in.nextToken
          TypeTree(UnitClass.tpe) setPos in.pos
        } else typ()
      var pos = in.currentPos
      val rtptName = rtpt match {
        case Ident(name) => name
        case _ => nme.EMPTY
      }
<<<<<<< HEAD
      if (in.token == LPAREN && rtptName != nme.EMPTY && !inInterface) { 
=======
      if (in.token == LPAREN && rtptName != nme.EMPTY && !inInterface) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        // constructor declaration
        val vparams = formalParams()
        optThrows()
        List {
          atPos(pos) {
            DefDef(mods, nme.CONSTRUCTOR, tparams, List(vparams), TypeTree(), methodBody())
          }
        }
<<<<<<< HEAD
      } else {        
=======
      } else {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        var mods1 = mods
        if (mods hasFlag Flags.ABSTRACT) mods1 = mods &~ Flags.ABSTRACT | Flags.DEFERRED
        pos = in.currentPos
        val name = ident()
        if (in.token == LPAREN) {
          // method declaration
          val vparams = formalParams()
          if (!isVoid) rtpt = optArrayBrackets(rtpt)
          optThrows()
<<<<<<< HEAD
          val body = 
=======
          val body =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            if (!inInterface && in.token == LBRACE) {
              methodBody()
            } else {
              if (parentToken == AT && in.token == DEFAULT) {
<<<<<<< HEAD
                val annot = 
=======
                val annot =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                  atPos(pos) {
                    New(Select(scalaDot(newTermName("runtime")), tpnme.AnnotationDefaultATTR), List(List()))
                  }
                mods1 = mods1 withAnnotations List(annot)
                skipTo(SEMI)
                accept(SEMI)
                blankExpr
              } else {
                accept(SEMI)
<<<<<<< HEAD
                EmptyTree 
=======
                EmptyTree
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
              }
            }
          if (inInterface) mods1 |= Flags.DEFERRED
          List {
            atPos(pos) {
              DefDef(mods1, name, tparams, List(vparams), rtpt, body)
            }
          }
        } else {
          if (inInterface) mods1 |= Flags.FINAL | Flags.STATIC
          val result = fieldDecls(pos, mods1, rtpt, name)
          accept(SEMI)
          result
        }
      }
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Parse a sequence of field declarations, separated by commas.
     *  This one is tricky because a comma might also appear in an
     *  initializer. Since we don't parse initializers we don't know
     *  what the comma signifies.
     *  We solve this with a second list buffer `maybe` which contains
<<<<<<< HEAD
     *  potential variable definitions. 
=======
     *  potential variable definitions.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *  Once we have reached the end of the statement, we know whether
     *  these potential definitions are real or not.
     */
    def fieldDecls(pos: Position, mods: Modifiers, tpt: Tree, name: Name): List[Tree] = {
      val buf = ListBuffer[Tree](varDecl(pos, mods, tpt, name))
      val maybe = new ListBuffer[Tree] // potential variable definitions.
      while (in.token == COMMA) {
        in.nextToken
        if (in.token == IDENTIFIER) { // if there's an ident after the comma ...
          val name = ident()
          if (in.token == ASSIGN || in.token == SEMI) { // ... followed by a `=` or `;`, we know it's a real variable definition
<<<<<<< HEAD
            buf ++= maybe 
=======
            buf ++= maybe
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            buf += varDecl(in.currentPos, mods, tpt.duplicate, name)
            maybe.clear()
          } else if (in.token == COMMA) { // ... if there's a comma after the ident, it could be a real vardef or not.
            maybe += varDecl(in.currentPos, mods, tpt.duplicate, name)
          } else { // ... if there's something else we were still in the initializer of the
                   // previous var def; skip to next comma or semicolon.
            skipTo(COMMA, SEMI)
            maybe.clear()
          }
        } else { // ... if there's no ident following the comma we were still in the initializer of the
                 // previous var def; skip to next comma or semicolon.
          skipTo(COMMA, SEMI)
          maybe.clear()
        }
      }
      if (in.token == SEMI) {
<<<<<<< HEAD
        buf ++= maybe // every potential vardef that survived until here is real. 
=======
        buf ++= maybe // every potential vardef that survived until here is real.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
      buf.toList
    }

    def varDecl(pos: Position, mods: Modifiers, tpt: Tree, name: TermName): ValDef = {
      val tpt1 = optArrayBrackets(tpt)
      if (in.token == ASSIGN && !mods.isParameter) skipTo(COMMA, SEMI)
      val mods1 = if (mods.isFinal) mods &~ Flags.FINAL else mods | Flags.MUTABLE
      atPos(pos) {
        ValDef(mods1, name, tpt1, blankExpr)
      }
    }

    def memberDecl(mods: Modifiers, parentToken: Int): List[Tree] = in.token match {
<<<<<<< HEAD
      case CLASS | ENUM | INTERFACE | AT => 
        typeDecl(if (definesInterface(parentToken)) mods | Flags.STATIC else mods)
      case _ => 
        termDecl(mods, parentToken)
    }

    def makeCompanionObject(cdef: ClassDef, statics: List[Tree]): Tree = 
=======
      case CLASS | ENUM | INTERFACE | AT =>
        typeDecl(if (definesInterface(parentToken)) mods | Flags.STATIC else mods)
      case _ =>
        termDecl(mods, parentToken)
    }

    def makeCompanionObject(cdef: ClassDef, statics: List[Tree]): Tree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      atPos(cdef.pos) {
        ModuleDef(cdef.mods & (Flags.AccessFlags | Flags.JAVA), cdef.name.toTermName,
                  makeTemplate(List(), statics))
      }

    def importCompanionObject(cdef: ClassDef): Tree =
      atPos(cdef.pos) {
        Import(Ident(cdef.name.toTermName), List(ImportSelector(nme.WILDCARD, -1, null, -1)))
      }

    // Importing the companion object members cannot be done uncritically: see
    // ticket #2377 wherein a class contains two static inner classes, each of which
    // has a static inner class called "Builder" - this results in an ambiguity error
    // when each performs the import in the enclosing class's scope.
    //
    // To address this I moved the import Companion._ inside the class, as the first
    // statement.  This should work without compromising the enclosing scope, but may (?)
    // end up suffering from the same issues it does in scala - specifically that this
    // leaves auxiliary constructors unable to access members of the companion object
    // as unqualified identifiers.
<<<<<<< HEAD
    def addCompanionObject(statics: List[Tree], cdef: ClassDef): List[Tree] = {      
=======
    def addCompanionObject(statics: List[Tree], cdef: ClassDef): List[Tree] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def implWithImport(importStmt: Tree) = {
        import cdef.impl._
        treeCopy.Template(cdef.impl, parents, self, importStmt :: body)
      }
      // if there are no statics we can use the original cdef, but we always
      // create the companion so import A._ is not an error (see ticket #1700)
      val cdefNew =
        if (statics.isEmpty) cdef
        else treeCopy.ClassDef(cdef, cdef.mods, cdef.name, cdef.tparams, implWithImport(importCompanionObject(cdef)))
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      List(makeCompanionObject(cdefNew, statics), cdefNew)
    }

    def importDecl(): List[Tree] = {
      accept(IMPORT)
      val pos = in.currentPos
      val buf = new ListBuffer[Name]
      def collectIdents() : Int = {
        if (in.token == ASTERISK) {
          val starOffset = in.pos
          in.nextToken
          buf += nme.WILDCARD
          starOffset
        } else {
          val nameOffset = in.pos
          buf += ident()
          if (in.token == DOT) {
            in.nextToken
            collectIdents()
          } else nameOffset
        }
      }
      if (in.token == STATIC) in.nextToken
      else buf += nme.ROOTPKG
      val lastnameOffset = collectIdents()
      accept(SEMI)
      val names = buf.toList
      if (names.length < 2) {
        syntaxError(pos, "illegal import", false)
        List()
      } else {
        val qual = ((Ident(names.head): Tree) /: names.tail.init) (Select(_, _))
        val lastname = names.last
        val selector = lastname match {
          case nme.WILDCARD => ImportSelector(lastname, lastnameOffset, null, -1)
          case _            => ImportSelector(lastname, lastnameOffset, lastname, lastnameOffset)
        }
        List(atPos(pos)(Import(qual, List(selector))))
      }
    }

    def interfacesOpt() =
      if (in.token == IMPLEMENTS) {
        in.nextToken
        repsep(typ, COMMA)
      } else {
        List()
      }

    def classDecl(mods: Modifiers): List[Tree] = {
      accept(CLASS)
      val pos = in.currentPos
      val name = identForType()
      val tparams = typeParams()
<<<<<<< HEAD
      val superclass = 
=======
      val superclass =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (in.token == EXTENDS) {
          in.nextToken
          typ()
        } else {
          javaLangObject()
        }
      val interfaces = interfacesOpt()
<<<<<<< HEAD
      val (statics, body) = typeBody(CLASS, name) 
=======
      val (statics, body) = typeBody(CLASS, name)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      addCompanionObject(statics, atPos(pos) {
        ClassDef(mods, name, tparams, makeTemplate(superclass :: interfaces, body))
      })
    }

    def interfaceDecl(mods: Modifiers): List[Tree] = {
      accept(INTERFACE)
      val pos = in.currentPos
      val name = identForType()
      val tparams = typeParams()
<<<<<<< HEAD
      val parents = 
=======
      val parents =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (in.token == EXTENDS) {
          in.nextToken
          repsep(typ, COMMA)
        } else {
          List(javaLangObject)
        }
      val (statics, body) = typeBody(INTERFACE, name)
      addCompanionObject(statics, atPos(pos) {
<<<<<<< HEAD
        ClassDef(mods | Flags.TRAIT | Flags.INTERFACE | Flags.ABSTRACT, 
                 name, tparams, 
=======
        ClassDef(mods | Flags.TRAIT | Flags.INTERFACE | Flags.ABSTRACT,
                 name, tparams,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                 makeTemplate(parents, body))
      })
    }

    def typeBody(leadingToken: Int, parentName: Name): (List[Tree], List[Tree]) = {
      accept(LBRACE)
      val defs = typeBodyDecls(leadingToken, parentName)
      accept(RBRACE)
      defs
    }
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def typeBodyDecls(parentToken: Int, parentName: Name): (List[Tree], List[Tree]) = {
      val inInterface = definesInterface(parentToken)
      val statics = new ListBuffer[Tree]
      val members = new ListBuffer[Tree]
      while (in.token != RBRACE && in.token != EOF) {
        var mods = modifiers(inInterface)
        if (in.token == LBRACE) {
          skipAhead() // skip init block, we just assume we have seen only static
          accept(RBRACE)
        } else if (in.token == SEMI) {
          in.nextToken
        } else {
          if (in.token == ENUM || definesInterface(in.token)) mods |= Flags.STATIC
          val decls = memberDecl(mods, parentToken)
          (if (mods.hasStaticFlag || inInterface && !(decls exists (_.isInstanceOf[DefDef])))
<<<<<<< HEAD
             statics 
           else 
=======
             statics
           else
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
             members) ++= decls
        }
      }
      def forwarders(sdef: Tree): List[Tree] = sdef match {
        case ClassDef(mods, name, tparams, _) if (parentToken == INTERFACE) =>
          val tparams1: List[TypeDef] = tparams map (_.duplicate)
          var rhs: Tree = Select(Ident(parentName.toTermName), name)
          if (!tparams1.isEmpty) rhs = AppliedTypeTree(rhs, tparams1 map (tp => Ident(tp.name)))
          List(TypeDef(Modifiers(Flags.PROTECTED), name, tparams1, rhs))
        case _ =>
          List()
      }
      val sdefs = statics.toList
      val idefs = members.toList ::: (sdefs flatMap forwarders)
      (sdefs, idefs)
    }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def annotationDecl(mods: Modifiers): List[Tree] = {
      accept(AT)
      accept(INTERFACE)
      val pos = in.currentPos
      val name = identForType()
<<<<<<< HEAD
      val parents = List(scalaDot(newTypeName("Annotation")), 
=======
      val parents = List(scalaDot(newTypeName("Annotation")),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                         Select(javaLangDot(newTermName("annotation")), newTypeName("Annotation")),
                         scalaDot(newTypeName("ClassfileAnnotation")))
      val (statics, body) = typeBody(AT, name)
      def getValueMethodType(tree: Tree) = tree match {
        case DefDef(_, nme.value, _, _, tpt, _) => Some(tpt.duplicate)
        case _ => None
      }
      var templ = makeTemplate(parents, body)
      for (stat <- templ.body; tpt <- getValueMethodType(stat))
        templ = makeTemplate(parents, makeConstructor(List(tpt)) :: templ.body)
      addCompanionObject(statics, atPos(pos) {
        ClassDef(mods, name, List(), templ)
      })
    }

    def enumDecl(mods: Modifiers): List[Tree] = {
      accept(ENUM)
      val pos = in.currentPos
      val name = identForType()
      def enumType = Ident(name)
      val interfaces = interfacesOpt()
      accept(LBRACE)
      val buf = new ListBuffer[Tree]
      def parseEnumConsts() {
        if (in.token != RBRACE && in.token != SEMI && in.token != EOF) {
          buf += enumConst(enumType)
          if (in.token == COMMA) {
            in.nextToken
            parseEnumConsts()
          }
        }
      }
<<<<<<< HEAD
      parseEnumConsts() 
      val consts = buf.toList
      val (statics, body) = 
=======
      parseEnumConsts()
      val consts = buf.toList
      val (statics, body) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (in.token == SEMI) {
          in.nextToken
          typeBodyDecls(ENUM, name)
        } else {
          (List(), List())
        }
      val predefs = List(
        DefDef(
<<<<<<< HEAD
          Modifiers(Flags.JAVA | Flags.STATIC), newTermName("values"), List(), 
=======
          Modifiers(Flags.JAVA | Flags.STATIC), newTermName("values"), List(),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          List(List()),
          arrayOf(enumType),
          blankExpr),
        DefDef(
<<<<<<< HEAD
          Modifiers(Flags.JAVA | Flags.STATIC), newTermName("valueOf"), List(), 
=======
          Modifiers(Flags.JAVA | Flags.STATIC), newTermName("valueOf"), List(),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          List(List(makeParam("x", TypeTree(StringClass.tpe)))),
          enumType,
          blankExpr))
      accept(RBRACE)
<<<<<<< HEAD
      val superclazz = 
        AppliedTypeTree(javaLangDot(newTypeName("Enum")), List(enumType))
      addCompanionObject(consts ::: statics ::: predefs, atPos(pos) {
        ClassDef(mods, name, List(), 
=======
      val superclazz =
        AppliedTypeTree(javaLangDot(newTypeName("Enum")), List(enumType))
      addCompanionObject(consts ::: statics ::: predefs, atPos(pos) {
        ClassDef(mods, name, List(),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                 makeTemplate(superclazz :: interfaces, body))
      })
    }

    def enumConst(enumType: Tree) = {
      annotations()
      atPos(in.currentPos) {
        val name = ident()
        if (in.token == LPAREN) {
          // skip arguments
          skipAhead()
          accept(RPAREN)
        }
        if (in.token == LBRACE) {
          // skip classbody
          skipAhead()
          accept(RBRACE)
        }
        ValDef(Modifiers(Flags.JAVA | Flags.STATIC), name, enumType, blankExpr)
      }
    }

    def typeDecl(mods: Modifiers): List[Tree] = in.token match {
      case ENUM      => enumDecl(mods)
      case INTERFACE => interfaceDecl(mods)
      case AT        => annotationDecl(mods)
      case CLASS     => classDecl(mods)
      case _         => in.nextToken; syntaxError("illegal start of type declaration", true); List(errorTypeTree)
    }

<<<<<<< HEAD
    /** CompilationUnit ::= [package QualId semi] TopStatSeq 
     */
    def compilationUnit(): Tree = {
      var pos = in.currentPos;
      val pkg: RefTree = 
=======
    /** CompilationUnit ::= [package QualId semi] TopStatSeq
     */
    def compilationUnit(): Tree = {
      var pos = in.currentPos;
      val pkg: RefTree =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if (in.token == AT || in.token == PACKAGE) {
          annotations()
          pos = in.currentPos
          accept(PACKAGE)
          val pkg = qualId()
          accept(SEMI)
          pkg
        } else {
          Ident(nme.EMPTY_PACKAGE_NAME)
        }
      thisPackageName = gen.convertToTypeName(pkg) match {
        case Some(t)  => t.name.toTypeName
        case _        => tpnme.EMPTY
      }
      val buf = new ListBuffer[Tree]
      while (in.token == IMPORT)
        buf ++= importDecl()
      while (in.token != EOF && in.token != RBRACE) {
        while (in.token == SEMI) in.nextToken
        buf ++= typeDecl(modifiers(false))
      }
      accept(EOF)
      atPos(pos) {
        makePackaging(pkg, buf.toList)
      }
    }
  }
}
