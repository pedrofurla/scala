package scala.reflect
package internal
package transform

trait Transforms { self: SymbolTable =>
<<<<<<< HEAD
  
  object refChecks extends { val global: Transforms.this.type = self } with RefChecks
  object uncurry   extends { val global: Transforms.this.type = self } with UnCurry
  object erasure   extends { val global: Transforms.this.type = self } with Erasure
  
  def transformedType(sym: Symbol) = 
=======

  /** We need to encode laziness by hand here because the three components refChecks, uncurry and erasure
   *  are overwritten by objects in Global.
   *  It would be best of objects could override lazy values. See SI-5187.
   *  In the absence of this, the Lazy functionality should probably be somewhere
   *  in the standard library. Or is it already?
   */
  private class Lazy[T](op: => T) {
    private var value: T = _
    private var _isDefined = false
    def isDefined = _isDefined
    def force: T = {
      if (!isDefined) { value = op; _isDefined = true }
      value
    }
  }

  private val refChecksLazy = new Lazy(new { val global: Transforms.this.type = self } with RefChecks)
  private val uncurryLazy = new Lazy(new { val global: Transforms.this.type = self } with UnCurry)
  private val erasureLazy = new Lazy(new { val global: Transforms.this.type = self } with Erasure)

  def refChecks = refChecksLazy.force
  def uncurry = uncurryLazy.force
  def erasure = erasureLazy.force

  def transformedType(sym: Symbol) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    erasure.transformInfo(sym,
      uncurry.transformInfo(sym,
        refChecks.transformInfo(sym, sym.info)))

<<<<<<< HEAD
=======
  def transformedType(tpe: Type) =
    erasure.scalaErasure(uncurry.uncurry(tpe))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}