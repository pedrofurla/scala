class ann(i: Int) extends Annotation

// annotations on abstract types
abstract class C1[@serializable @cloneable +T, U, V[_]]
abstract class C2[@deprecated
                  @ann(1) T <: Number,
                  V]
abstract class C3 {
  @ann(2) type X <: Number
}

object Test {

  // bug #1028
  val x = 1
  @ann(x) val a = ()
  @ann({val y = 2; y}) val b = ()

  def c: Int @ann(x) = 1
  def d: String @ann({val z = 0; z - 1}) = "2"
  def e[@deprecated T, U](x: T) = x

  //bug #1214
  val y = new (Integer @ann(0))(2)

<<<<<<< HEAD
  import scala.reflect.BeanProperty
=======
  import scala.beans.BeanProperty
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  // bug #637
  trait S { def getField(): Int }
  class O extends S { @BeanProperty val field = 0 }

  // bug #1070
  trait T { @BeanProperty var field = 1 }
}

