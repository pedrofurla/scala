object depexists {

<<<<<<< HEAD
  val c: Cell[(a, b)] forSome { type a <: Number; type b <: (a, a) } = null
=======
  val c: Option[(a, b)] forSome { type a <: Number; type b <: (a, a) } = null
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val d = c
}
