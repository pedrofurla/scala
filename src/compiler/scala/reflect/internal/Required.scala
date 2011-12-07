package scala.reflect
package internal

import settings.MutableSettings

trait Required { self: SymbolTable =>
<<<<<<< HEAD
  
  type AbstractFileType >: Null <: { def path: String }
  
  def picklerPhase: Phase
  
  val gen: TreeGen { val global: Required.this.type }
  
  def settings: MutableSettings
  
  def forInteractive: Boolean
  
=======

  type AbstractFileType >: Null <: {
    def path: String
    def canonicalPath: String
  }

  def picklerPhase: Phase

  val gen: TreeGen { val global: Required.this.type }

  def settings: MutableSettings

  def forInteractive: Boolean

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def forScaladoc: Boolean
}
