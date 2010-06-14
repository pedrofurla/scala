import scala.tools.nsc.interpreter._
import scala.tools.nsc.io.Process

def xmlc(url: String) = XMLCompletion(xml.XML.load(new java.net.URL(url)))

lazy val trac = xmlc("""https://lampsvn.epfl.ch/trac/scala/query?status=assigned&status=new&status=reopened&format=rss&order=priority&owner=extempore""")

lazy val airport = XMLCompletion(xml.XML loadString Process("airport -xs").stdout.mkString)

lazy val tracAll = xmlc(
  "https://lampsvn.epfl.ch/trac/scala/query?status=assigned&status=new&status=reopened&group=owner&format=rss" +
  "&component=!Eclipse+plugin&order=priority&col=id&col=summary&col=component&col=type&col=priority"
)

val connections = new CompletionAware {  
  lazy val completions = Process("netstat -p tcp").toList drop 2 map (_ split "\\s+" apply 4)
  override def execute(s: String) = Some(new scala.util.Random)
}
