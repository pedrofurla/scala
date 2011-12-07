/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.reflect
package internal

trait TypeDebugging {
  self: SymbolTable =>
<<<<<<< HEAD
  
  import definitions._

  // @M toString that is safe during debugging (does not normalize, ...)
  object typeDebug {  
=======

  import definitions._

  // @M toString that is safe during debugging (does not normalize, ...)
  object typeDebug {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def to_s(x: Any): String = x match {
      // otherwise case classes are caught looking like products
      case _: Tree | _: Type     => "" + x
      case x: TraversableOnce[_] => x mkString ", "
      case x: Product            => x.productIterator mkString ("(", ", ", ")")
      case _                     => "" + x
    }
    def ptIndent(x: Any) = ("" + x).replaceAll("\\n", "  ")
    def ptBlock(label: String, pairs: (String, Any)*): String = {
      if (pairs.isEmpty) label + "{ }"
      else {
        val width = pairs map (_._1.length) max
        val fmt   = "%-" + (width + 1) + "s %s"
        val strs  = pairs map { case (k, v) => fmt.format(k, to_s(v)) }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        strs.mkString(label + " {\n  ", "\n  ", "\n}")
      }
    }
    def ptLine(label: String, pairs: (String, Any)*): String = {
      val strs = pairs map { case (k, v) => k + "=" + to_s(v) }
<<<<<<< HEAD
      strs.mkString(label + ": ", ", ", "")      
=======
      strs.mkString(label + ": ", ", ", "")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
    def ptTree(t: Tree) = t match {
      case PackageDef(pid, _)            => "package " + pid
      case ModuleDef(_, name, _)         => "object " + name
      case ClassDef(_, name, tparams, _) => "class " + name + str.brackets(tparams)
      case _                             => to_s(t)
    }

    object str {
      def parentheses(xs: List[_]): String     = xs.mkString("(", ", ", ")")
      def brackets(xs: List[_]): String        = if (xs.isEmpty) "" else xs.mkString("[", ", ", "]")
      def tparams(tparams: List[Type]): String = brackets(tparams map debug)
      def parents(ps: List[Type]): String      = (ps map debug).mkString(" with ")
<<<<<<< HEAD
      def refine(defs: Scope): String          = defs.toList.mkString("{", " ;\n ", "}")      
    }
    
=======
      def refine(defs: Scope): String          = defs.toList.mkString("{", " ;\n ", "}")
    }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def dump(tp: Type): Unit = {
      println("** " + tp + " / " + tp.getClass + " **")
      import tp._

      println("typeSymbol = " + typeSymbol)
      println("termSymbol = " + termSymbol)
      println("widen = " + widen)
      println("deconst = " + deconst)
      println("typeOfThis = " + typeOfThis)
      println("bounds = " + bounds)
      println("parents = " + parents)
      println("prefixChain = " + prefixChain)
      println("typeConstructor = " + typeConstructor)
      println(" .. typeConstructor.typeParams = " + typeConstructor.typeParams)
      println(" .. _.variance = " + (typeConstructor.typeParams map (_.variance)))
      println("typeArgs = " + typeArgs)
      println("resultType = " + resultType)
      println("finalResultType = " + finalResultType)
      println("paramss = " + paramss)
      println("paramTypes = " + paramTypes)
      println("typeParams = " + typeParams)
      println("boundSyms = " + boundSyms)
      println("baseTypeSeq = " + baseTypeSeq)
      println("baseClasses = " + baseClasses)
      println("toLongString = " + toLongString)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def debug(tp: Type): String = tp match {
      case TypeRef(pre, sym, args)             => debug(pre) + "." + sym.nameString + str.tparams(args)
      case ThisType(sym)                       => sym.nameString + ".this"
      case SingleType(pre, sym)                => debug(pre) +"."+ sym.nameString +".type"
      case RefinedType(parents, defs)          => str.parents(parents) + str.refine(defs)
      case ClassInfoType(parents, defs, clazz) => "class "+ clazz.nameString + str.parents(parents) + str.refine(defs)
      case PolyType(tparams, result)           => str.brackets(tparams) + " " + debug(result)
      case TypeBounds(lo, hi)                  => ">: "+ debug(lo) +" <: "+ debug(hi)
      case tv @ TypeVar(_, _)                  => tv.toString
      case ExistentialType(tparams, qtpe)      => "forSome "+ str.brackets(tparams) + " " + debug(qtpe)
      case _                                   => "?"+tp.getClass.getName+"?"//tp.toString might produce cyclic error...
    }
    def debugString(tp: Type) = debug(tp)
  }
  def paramString(tp: Type)      = typeDebug.str parentheses (tp.params map (_.defString))
  def typeParamsString(tp: Type) = typeDebug.str brackets (tp.typeParams map (_.defString))
  def typeArgsString(tp: Type)   = typeDebug.str brackets (tp.typeArgs map (_.safeToString))
  def debugString(tp: Type)      = typeDebug debugString tp
}
