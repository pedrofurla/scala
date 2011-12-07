import org.scalacheck._
import org.scalacheck.Prop._

import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.page.IndexScript
<<<<<<< HEAD
import java.net.URLClassLoader
=======
import java.net.{URLClassLoader, URLDecoder}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

object Test extends Properties("IndexScript") {

  def getClasspath = {
    val loader = Thread.currentThread.getContextClassLoader
    val paths = loader.asInstanceOf[URLClassLoader].getURLs
    val morepaths = loader.getParent.asInstanceOf[URLClassLoader].getURLs
<<<<<<< HEAD
    (paths ++ morepaths).map(_.getPath).mkString(java.io.File.pathSeparator)
=======
    (paths ++ morepaths).map(u => URLDecoder.decode(u.getPath)).mkString(java.io.File.pathSeparator)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  val docFactory = {
    val settings = new doc.Settings({Console.err.println(_)})
    settings.classpath.value = getClasspath
    val reporter = new scala.tools.nsc.reporters.ConsoleReporter(settings)
    new doc.DocFactory(reporter, settings)
  }

  val indexModelFactory = doc.model.IndexModelFactory

  def createIndexScript(path: String) =
    docFactory.makeUniverse(List(path)) match {
      case Some(universe) => {
        val index = new IndexScript(universe,
                                    indexModelFactory.makeIndex(universe))
        Some(index)
      }
      case _ =>
        None
    }

  property("allPackages") = {
    createIndexScript("src/compiler/scala/tools/nsc/doc/html/page/Index.scala") match {
      case Some(index) =>
        index.allPackages.map(_.toString) == List(
          "scala",
          "scala.tools",
          "scala.tools.nsc",
          "scala.tools.nsc.doc",
          "scala.tools.nsc.doc.html",
          "scala.tools.nsc.doc.html.page"
        )
      case None =>
        false
    }
  }
}
