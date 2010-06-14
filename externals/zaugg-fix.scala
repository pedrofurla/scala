object IntepreterFix {
  import scala.tools.nsc._
  import Interpreter._

  def break(args: DebugParam[_]*): Unit = {
    val intLoop = new InterpreterLoop
    intLoop.settings = {
      val s = new Settings(Console.println)
      // need to pass this explicitly to the settings for Scalac.
      // See: http://old.nabble.com/-scala--recent-changes-in-2.8-nightly-classpath-management-td26233977.html
      s.classpath.value = System.getProperty("java.class.path")
      s
    }
    intLoop.createInterpreter
    intLoop.in = InteractiveReader.createDefault(intLoop.interpreter)

    // rebind exit so people don't accidentally call System.exit by way of predef
    intLoop.interpreter.beQuietDuring {
      intLoop.interpreter.interpret("""def exit = println("Type :quit to resume program execution.")""")
      for (p <- args) {
        intLoop.interpreter.bind(p.name, p.typeStr, p.param)
        println("%s: %s".format(p.name, p.typeStr))
      }
    }
    intLoop.repl()
    intLoop.closeInterpreter
  }

}