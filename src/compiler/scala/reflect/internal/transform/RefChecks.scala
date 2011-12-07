package scala.reflect
package internal
package transform

trait RefChecks {
<<<<<<< HEAD
  
  val global: SymbolTable
  import global._
    
=======

  val global: SymbolTable
  import global._

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def transformInfo(sym: Symbol, tp: Type): Type =
    if (sym.isModule && !sym.isStatic) NullaryMethodType(tp)
    else tp
}