/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package interpreter

// Coming soon
trait Dossiers {
  val intp: IMain

  import intp._
  import intp.global._
<<<<<<< HEAD
  
  trait Dossier {
    def symbol: Symbol
    def staticType: Type
    
=======

  trait Dossier {
    def symbol: Symbol
    def staticType: Type

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def id               = name.toString
    def name             = symbol.name
    def normalizedType   = staticType.typeSymbolDirect.tpe.normalize
    def simpleNameOfType = staticType.typeSymbol.simpleName
    def staticTypeString = staticType.toString
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString = "Dossier on %s:\n  static type %s (normalized %s)".format(
      symbol, staticType, normalizedType
    )
  }
<<<<<<< HEAD
  
  class TypeDossier(val symbol: TypeSymbol, val staticType: Type) extends Dossier {
    override def toString = super.toString
  }
  
=======

  class TypeDossier(val symbol: TypeSymbol, val staticType: Type) extends Dossier {
    override def toString = super.toString
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  class TermDossier(val symbol: TermSymbol, val staticType: Type, val value: AnyRef) extends Dossier {
    def runtimeClass: JClass = value.getClass
    def runtimeSymbol: Symbol  = safeClass(runtimeClass.getName) getOrElse NoSymbol
    def runtimeType: Type      = runtimeSymbol.tpe
    def runtimeTypeString      = TypeStrings.fromClazz(runtimeClass)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def runtimeTypedParam = NamedParamClass(id, runtimeTypeString, value)
    def staticTypedParam  = NamedParamClass(id, staticTypeString, value)

    def isRuntimeTypeTighter = runtimeSymbol.ancestors contains normalizedType.typeSymbol
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString = super.toString + (
      "\n  runtime type %s/%s\n  value %s".format(
        runtimeType, runtimeTypeString, value
      )
    )
  }
}

