import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;

import scala.tools.nsc.Settings;
import scala.tools.nsc.Interpreter

object ScalaInterp {
  private var interpSingle:Interpreter  = null
  private val interpOutStream = new ByteArrayOutputStream()
  private val output= new PrintStream(interpOutStream)

  def interp = {
    if(interpSingle==null) {
      val codebasePath = List(
        "/scala/Array.class","/java/lang/String.class"
      ).map{s=>getJarFromResource(getClass().getResource(s))}.mkString(" ")
      val settings = new Settings()
      settings.Xcodebase.value=codebasePath
      settings.classpath.value=""
      settings.bootclasspath.value=""
      settings.extdirs.value=""
      val pwout =new PrintWriter(new OutputStreamWriter(interpOutStream));
      interpSingle = new scala.tools.nsc.Interpreter(settings, pwout) {
        override def parentClassLoader():ClassLoader= Thread.currentThread().getContextClassLoader();
      };
    }
    interpSingle
  }
  

  def execute(script:String,hist:Seq[String]) = {
    interp.reset()
    interpOutStream.reset()

    val out = System.out
    val err = System.err
    System.setOut(output)
    System.setErr(output)

    Console.setOut(output)
      
    var success=false

    try {
      // load the state
      for (s<- hist)
        interp.interpret(s)
      interp.reporter.flush()
      output.flush()
      interpOutStream.reset()

      // Eval the user text
      val status=interp.interpret(script)
      if (status==scala.tools.nsc.InterpreterResults.Incomplete)
        output.println("error: unexpected EOF found, incomplete expression")
      success=status==scala.tools.nsc.InterpreterResults.Success
      interp.reporter.flush()
    } finally {
      System.setOut(out)
      System.setErr(err)
    }

    output.flush()
    (interpOutStream.toString(),success)
  }


  /**
   * Returns a URL to a Jar file in which a resource from that Jar file is
   * located. This is the easiest way that we can locate certain Jar files.
   */
  private def getJarFromResource(resource:URL)={
    resource.toString.replaceAll(".*(file:.*)!.*","$1").replaceAll(" ", "%20")
  }
}