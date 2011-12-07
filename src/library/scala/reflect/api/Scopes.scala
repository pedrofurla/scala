package scala.reflect
package api

trait Scopes { self: Universe =>

  type Scope <: Iterable[Symbol]

  def newScope(): Scope
}
<<<<<<< HEAD
  
  
=======


>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
