package test

final class Test extends PackageProtected {
  def bar = foo
}

package another {
  object Main {
<<<<<<< HEAD
    def bug1(t: Test) {
      // Can always be replicated.
      println(t.foo)
    }
    def bug2(t: Test) {
=======
    def t1(t: Test) {
      // Can always be replicated.
      println(t.foo)
    }
    def t2(t: Test) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      // Conditions to replicate: must use -optimise, class Test must be final
      println(t.bar)
      //@noinline is a usable workaround
    }
    def main(args: Array[String]) {
<<<<<<< HEAD
      bug1(new Test)
      bug2(new Test)
=======
      t1(new Test)
      t2(new Test)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }
  }
}
