<<<<<<< HEAD
// bug1231: reported by Vladimir Reshetnikov on 19 July 2007
=======
// t1231: reported by Vladimir Reshetnikov on 19 July 2007
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
trait A {
 type T[_]
}

trait B // First-order type

class C extends A {
 type T = B // This compiles well (@M: ... but it shouldn't)
}
