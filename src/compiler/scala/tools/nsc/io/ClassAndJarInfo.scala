/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package io

<<<<<<< HEAD
import java.net.URL
=======
import java.net.{ URL, URLClassLoader }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import java.io.IOException
import collection.JavaConverters._

/** A convenience class for finding the jar with the bytecode for
 *  a given Class object and similar common tasks.
 */
class ClassAndJarInfo[T: ClassManifest] {
<<<<<<< HEAD
  val man   = classManifest[T]
  def clazz = man.erasure
  
  def baseOfPath(path: String) = path indexOf '!' match {
    case -1   => path stripSuffix internalClassName
    case idx  => path take idx
  }
  
  def classUrl             = clazz getResource simpleClassName + ".class"
  def codeSource           = protectionDomain.getCodeSource()
  def internalClassName    = internalName + ".class"
  def internalName         = clazz.getName.replace('.', '/')
  def jarManifest          = (
    try new JManifest(jarManifestUrl.openStream())
    catch { case _: IOException => new JManifest() }
  )   
=======
  val man          = classManifest[T]
  def clazz        = man.erasure
  def internalName = clazz.getName.replace('.', '/')

  def resourceURL = new URLClassLoader(Array[URL]()) getResource internalName + ".class"

  def baseOfPath(path: String) = path indexOf '!' match {
    case -1   => path stripSuffix internalName + ".class"
    case idx  => path take idx
  }

  def simpleClassName      = clazz.getName split """[$.]""" last
  def classUrl             = clazz getResource simpleClassName + ".class"
  def codeSource           = protectionDomain.getCodeSource()
  def jarManifest          = (
    try new JManifest(jarManifestUrl.openStream())
    catch { case _: IOException => new JManifest() }
  )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def jarManifestMainAttrs = jarManifest.getMainAttributes().asScala
  def jarManifestUrl       = new URL(baseOfPath("" + classUrl) + "!/META-INF/MANIFEST.MF")
  def locationFile         = File(locationUrl.toURI.getPath())
  def locationUrl          = if (codeSource == null) new URL("file:///") else codeSource.getLocation()
  def protectionDomain     = clazz.getProtectionDomain()
  def rootClasspath        = rootPossibles find (_.exists)
  def rootFromLocation     = Path(locationUrl.toURI.getPath())
  def rootFromResource     = Path(baseOfPath(classUrl.getPath) stripPrefix "file:")
  def rootPossibles        = Iterator(rootFromResource, rootFromLocation)
<<<<<<< HEAD
  def simpleClassName      = clazz.getName split """[$.]""" last
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
