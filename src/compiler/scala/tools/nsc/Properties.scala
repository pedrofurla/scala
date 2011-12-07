/* NSC -- new Scala compiler
 * Copyright 2006-2011 LAMP/EPFL
 * @author  Stephane Micheloud
 */

<<<<<<< HEAD

package scala.tools.nsc

/** Loads compiler.properties from the jar. */
object Properties extends scala.util.PropertiesTrait {
  protected def propCategory    = "compiler"
  protected def pickJarBasedOn  = classOf[Global]
  
  // settings based on jar properties
  def fileEndingString      = scalaPropOrElse("file.ending", ".scala|.java")
  def residentPromptString  = scalaPropOrElse("resident.prompt", "\nnsc> ")
  def shellPromptString     = scalaPropOrElse("shell.prompt", "\nscala> ")
  
  // settings based on system properties
  def msilLibPath           = propOrNone("msil.libpath")

  // derived values
  def isEmacsShell          = propOrEmpty("env.emacs") != ""
  def fileEndings           = fileEndingString.split("""\|""").toList
=======
package scala.tools.nsc

/** Loads `compiler.properties` from the jar archive file.
 */
object Properties extends scala.util.PropertiesTrait {
  protected def propCategory   = "compiler"
  protected def pickJarBasedOn = classOf[Global]

  // settings based on jar properties
  def fileEndingString     = scalaPropOrElse("file.ending", ".scala|.java")
  def residentPromptString = scalaPropOrElse("resident.prompt", "\nnsc> ")
  def shellPromptString    = scalaPropOrElse("shell.prompt", "\nscala> ")

  // settings based on system properties
  def msilLibPath          = propOrNone("msil.libpath")

  // derived values
  def isEmacsShell         = propOrEmpty("env.emacs") != ""
  def fileEndings          = fileEndingString.split("""\|""").toList

  // System property java.home is the JRE root.
  // Environment variable JAVA_HOME is (supposed to be) the jdk root.
  // We need the latter to find javac, tools.jar, etc.
  def jdkHome              = envOrElse("JAVA_HOME", javaHome)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
