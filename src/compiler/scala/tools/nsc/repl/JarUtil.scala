/* NSC -- new Scala compiler
 * Copyright 2005-2010 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package repl

import java.io.IOException
import io._
import Properties.{ javaHome }
import Path.isJarOrZip
import Streamable.slurpString
import java.util.jar._
import scala.util.matching.Regex
import collection.JavaConversions._

object JarUtil {
  class SourceJar(jarfile: File) extends Iterable[JarEntry] {
    val jarFile = new JarFile(jarfile.jfile)
    def iterator: Iterator[JarEntry] = jarFile.entries()
    private def fuzzyMatch(entry: JarEntry, str: String) = {
      val s = if (str endsWith ".scala") str else str + ".scala"
      (entry.getName split '/' last) == s
    }
    private def fuzzyMatches(str: String): List[JarEntry] = this filter (x => fuzzyMatch(x, str)) toList
    private def asMap(xs: Iterable[JarEntry]): Map[JarEntry, String] =
      xs flatMap (x => file(x) map (x -> _)) toMap
    
    def file(entry: JarEntry): Option[String] =
      try oempty(slurpString(jarFile getInputStream entry))
      catch { case _: IOException | _: RuntimeException => None }
      
    def file(name: String): Option[String] =
      Option(jarFile getJarEntry name) filterNot (_.isDirectory) flatMap file
    
    def files(name: String): Map[JarEntry, String] =
      asMap(fuzzyMatches(name))
    
    def files(regex: Regex): Map[JarEntry, String] =
      asMap(this filter (_.getName matches regex.pattern.toString))
    
    /** Does a fuzzy search based on just the filename. */
    // def apply(name: String): List[String] = {
    //   val matches = this filter fuzzyMatch
    //   
    //   if (matches.size == 1) file(matches.head)
    //   else None
    // }
  }
  
  lazy val scalaLibrarySourceJar = findScalaLibrarySource() map (x => new SourceJar(x.toFile)) orNull
  
  /** Walks upward from wherever the scala library jar is searching for
   *  src/scala-library-src.jar.  This approach finds it in the release
   *  layout and in trunk builds going up from pack.
   */
  def findScalaLibrarySource() = {
    def toSrc(d: Directory) = d.dirs.toList map (_ / "scala-library-src.jar")
    def walk(d: Directory)  = d.parents flatMap toSrc find (_.exists)
    
    find(classOf[ScalaObject]) flatMap (x => walk(x.parent))
  }

  def find(clazz: JClass): Option[File] = {
    try Some(File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()))
    catch { case _: Exception => None }
  }
  
  def findAll(clazz: JClass): List[File] = findAll(clazz, x => x)
  // find(clazz) match {
  //   case Some(jar)  => jar.parent.files filter isJarOrZip toList
  //   case _          => Nil
  // }
  
  def findAll(clazz: JClass, where: Path => Path): List[File] = find(clazz) match {
    case Some(jar)  => where(jar.parent).toDirectory.files filter isJarOrZip toList
    case _          => Nil
  }    
}  
