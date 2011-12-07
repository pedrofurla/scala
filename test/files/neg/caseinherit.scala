<<<<<<< HEAD
case class A(x: Int)
case class B(y: Int) extends A(y)
case object Bippy extends A(55)

class Innocent extends A(5)
case class Dingus(y: Int) extends Innocent
=======
package foo {
  case class A(x: Int)
  case class B(y: Int) extends A(y)
  case object Bippy extends A(55)
}
import foo._

package bar {
  class Blameless(x: Int)
  class Innocent extends A(5)
  case class Dingus(y: Int) extends Innocent
  case object Hungle extends Blameless(5)
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
