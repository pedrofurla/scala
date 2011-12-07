import scala.tools.partest.ReplTest

object Test extends ReplTest {
  def code = """
(2)
(2 + 2)
((2 + 2))
  ((2 + 2))
  (  (2 + 2))
  (  (2 + 2 )  )
5 ;   (  (2 + 2 )  ) ; ((5))
(((2 + 2)), ((2 + 2)))
(((2 + 2)), ((2 + 2)), 2)
((((2 + 2)), ((2 + 2)), 2).productIterator ++ Iterator(3) mkString)

55 ; ((2 + 2)) ; (1, 2, 3)
55 ; (x: Int) => x + 1 ; () => ((5))

() => 5
55 ; () => 5
() => { class X ; new X }

def foo(x: Int)(y: Int)(z: Int) = x+y+z
foo(5)(10)(15)+foo(5)(10)(15)

<<<<<<< HEAD
=======
List(1) ++ List('a')

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  """.trim
}