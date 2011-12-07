/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package cmd

import nsc.io.{ Path, File, Directory }
import scala.reflect.OptManifest

/** A general mechanism for defining how a command line argument
 *  (always a String) is transformed into an arbitrary type.  A few
 *  example instances are in the companion object, but in general
 *  either IntFromString will suffice or you'll want custom transformers.
 */
<<<<<<< HEAD
abstract class FromString[+T](implicit m: OptManifest[T]) extends PartialFunction[String, T] {
  def apply(s: String): T
  def isDefinedAt(s: String): Boolean = true
=======
abstract class FromString[+T](implicit m: OptManifest[T]) extends scala.runtime.AbstractPartialFunction[String, T] {
  def apply(s: String): T
  def _isDefinedAt(s: String): Boolean = true
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def zero: T = apply("")

  def targetString: String = m.toString
}

object FromString {
  // We need these because we clash with the String => Path implicits.
  private def toFile(s: String) = new File(new java.io.File(s))
  private def toDir(s: String)  = new Directory(new java.io.File(s))
<<<<<<< HEAD
  
  /** Path related stringifiers.
   */
  val ExistingFile: FromString[File] = new FromString[File] {
    override def isDefinedAt(s: String) = toFile(s).isFile
=======

  /** Path related stringifiers.
   */
  val ExistingFile: FromString[File] = new FromString[File] {
    override def _isDefinedAt(s: String) = toFile(s).isFile
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def apply(s: String): File =
      if (isDefinedAt(s)) toFile(s)
      else cmd.runAndExit(println("'%s' is not an existing file." format s))
  }
  val ExistingDir: FromString[Directory] = new FromString[Directory] {
<<<<<<< HEAD
    override def isDefinedAt(s: String) = toDir(s).isDirectory
    def apply(s: String): Directory = 
=======
    override def _isDefinedAt(s: String) = toDir(s).isDirectory
    def apply(s: String): Directory =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (isDefinedAt(s)) toDir(s)
      else cmd.runAndExit(println("'%s' is not an existing directory." format s))
  }
  def ExistingDirRelativeTo(root: Directory) = new FromString[Directory] {
    private def resolve(s: String) = toDir(s) toAbsoluteWithRoot root toDirectory
<<<<<<< HEAD
    override def isDefinedAt(s: String) = resolve(s).isDirectory
    def apply(s: String): Directory = 
      if (isDefinedAt(s)) resolve(s)
      else cmd.runAndExit(println("'%s' is not an existing directory." format resolve(s)))
  }
  
=======
    override def _isDefinedAt(s: String) = resolve(s).isDirectory
    def apply(s: String): Directory =
      if (isDefinedAt(s)) resolve(s)
      else cmd.runAndExit(println("'%s' is not an existing directory." format resolve(s)))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Argument expander, i.e. turns single argument "foo bar baz" into argument
   *  list "foo", "bar", "baz".
   */
  val ArgumentsFromString: FromString[List[String]] = new FromString[List[String]] {
    def apply(s: String) = toArgs(s)
  }
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Identity.
   */
  implicit val StringFromString: FromString[String] = new FromString[String] {
    def apply(s: String): String = s
  }

  /** Implicit as the most likely to be useful as-is.
   */
  implicit val IntFromString: FromString[Int] = new FromString[Int] {
<<<<<<< HEAD
    override def isDefinedAt(s: String)   = safeToInt(s).isDefined
=======
    override def _isDefinedAt(s: String)  = safeToInt(s).isDefined
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def apply(s: String)                  = safeToInt(s).get
    def safeToInt(s: String): Option[Int] = try Some(java.lang.Integer.parseInt(s)) catch { case _: NumberFormatException => None }
  }
}
