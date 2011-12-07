/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package util

<<<<<<< HEAD
import java.lang.{ ClassLoader => JavaClassLoader }
import java.lang.reflect.{ Constructor, Modifier, Method }
import java.net.URL
import ScalaClassLoader._
import scala.util.control.Exception.{ catching }

trait ScalaClassLoader extends JavaClassLoader {  
  /** Override to see classloader activity traced */
  protected def trace: Boolean = false
  
  /** Executing an action with this classloader as context classloader */
  def asContext[T](action: => T): T = {
    val oldLoader = getContextLoader
    try {
      setContextLoader(this)
      action
    }
    finally setContextLoader(oldLoader)
  }
  def setAsContext() { setContextLoader(this) }
  
  /** Load and link a class with this classloader */  
  def tryToLoadClass[T <: AnyRef](path: String): Option[Class[T]] = tryClass(path, false)
  /** Load, link and initialize a class with this classloader */
  def tryToInitializeClass[T <: AnyRef](path: String): Option[Class[T]] = tryClass(path, true)
  
  private def tryClass[T <: AnyRef](path: String, initialize: Boolean): Option[Class[T]] =
    catching(classOf[ClassNotFoundException], classOf[SecurityException]) opt 
=======
import java.lang.{ ClassLoader => JClassLoader }
import java.lang.reflect.{ Constructor, Modifier, Method }
import java.io.{ File => JFile }
import java.net.{ URLClassLoader => JURLClassLoader }
import java.net.URL
import scala.reflect.ReflectionUtils.unwrapHandler
import ScalaClassLoader._
import scala.util.control.Exception.{ catching }
// import Exceptional.unwrap

trait HasClassPath {
  def classPathURLs: Seq[URL]
}

/** A wrapper around java.lang.ClassLoader to lower the annoyance
 *  of java reflection.
 */
trait ScalaClassLoader extends JClassLoader {
  /** Override to see classloader activity traced */
  protected def trace: Boolean = false
  protected lazy val classLoaderUniqueId = "Cl#" + System.identityHashCode(this)
  protected def classLoaderLog(msg: => String) {
    if (trace)
      Console.err.println("[" + classLoaderUniqueId + "] " + msg)
  }

  /** Executing an action with this classloader as context classloader */
  def asContext[T](action: => T): T = {
    val saved = contextLoader
    try { setContext(this) ; action }
    finally setContext(saved)
  }
  def setAsContext() { setContext(this) }

  /** Load and link a class with this classloader */
  def tryToLoadClass[T <: AnyRef](path: String): Option[Class[T]] = tryClass(path, false)
  /** Load, link and initialize a class with this classloader */
  def tryToInitializeClass[T <: AnyRef](path: String): Option[Class[T]] = tryClass(path, true)

  private def tryClass[T <: AnyRef](path: String, initialize: Boolean): Option[Class[T]] =
    catching(classOf[ClassNotFoundException], classOf[SecurityException]) opt
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      Class.forName(path, initialize, this).asInstanceOf[Class[T]]

  /** Create an instance of a class with this classloader */
  def create(path: String): AnyRef =
    tryToInitializeClass[AnyRef](path) map (_.newInstance()) orNull
<<<<<<< HEAD
  
  override def findClass(name: String) = {
    val result = super.findClass(name)
    if (trace) println("findClass(%s) = %s".format(name, result))
    result
  }
  
  override def loadClass(name: String, resolve: Boolean) = {
    val result = super.loadClass(name, resolve)
    if (trace) println("loadClass(%s, %s) = %s".format(name, resolve, result))
    result
  }
  
  def constructorsOf[T <: AnyRef : Manifest]: List[Constructor[T]] =
    manifest[T].erasure.getConstructors.toList map (_.asInstanceOf[Constructor[T]])
  
=======

  override def findClass(name: String) = {
    val result = super.findClass(name)
    classLoaderLog("findClass(%s) = %s".format(name, result))
    result
  }

  override def loadClass(name: String, resolve: Boolean) = {
    val result = super.loadClass(name, resolve)
    classLoaderLog("loadClass(%s, %s) = %s".format(name, resolve, result))
    result
  }

  def constructorsOf[T <: AnyRef : Manifest]: List[Constructor[T]] =
    manifest[T].erasure.getConstructors.toList map (_.asInstanceOf[Constructor[T]])

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The actual bytes for a class file, or an empty array if it can't be found. */
  def classBytes(className: String): Array[Byte] = classAsStream(className) match {
    case null   => Array()
    case stream => io.Streamable.bytes(stream)
  }

  /** An InputStream representing the given class name, or null if not found. */
  def classAsStream(className: String) =
    getResourceAsStream(className.replaceAll("""\.""", "/") + ".class")
<<<<<<< HEAD
  
  /** Run the main method of a class to be loaded by this classloader */
  def run(objectName: String, arguments: Seq[String]) {
    val clsToRun = tryToInitializeClass(objectName) getOrElse ( 
      throw new ClassNotFoundException(objectName)
    )
      
=======

  /** Run the main method of a class to be loaded by this classloader */
  def run(objectName: String, arguments: Seq[String]) {
    val clsToRun = tryToInitializeClass(objectName) getOrElse (
      throw new ClassNotFoundException(objectName)
    )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val method = clsToRun.getMethod("main", classOf[Array[String]])
    if (!Modifier.isStatic(method.getModifiers))
      throw new NoSuchMethodException(objectName + ".main is not static")

<<<<<<< HEAD
    asContext(method.invoke(null, Array(arguments.toArray: AnyRef): _*))  // !!! : AnyRef shouldn't be necessary
  }
}

object ScalaClassLoader {
  implicit def apply(cl: JavaClassLoader): ScalaClassLoader = {
    val loader = if (cl == null) JavaClassLoader.getSystemClassLoader() else cl
    new JavaClassLoader(loader) with ScalaClassLoader
  }
  
  class URLClassLoader(urls: Seq[URL], parent: JavaClassLoader) 
      extends java.net.URLClassLoader(urls.toArray, parent) 
      with ScalaClassLoader {

    private var classloaderURLs = urls.toList
    private def classpathString = ClassPath.fromURLs(urls: _*)
    
    /** Override to widen to public */
    override def addURL(url: URL) = {
      classloaderURLs +:= url
      super.addURL(url)
    }
    override def run(objectName: String, arguments: Seq[String]) {
      try super.run(objectName, arguments)
      catch { case x: ClassNotFoundException  =>
        throw new ClassNotFoundException(objectName + 
          " (args = %s, classpath = %s)".format(arguments mkString ", ", classpathString))
      }
    }
    override def toString = urls.mkString("URLClassLoader(\n  ", "\n  ", "\n)\n")
  }
  
  def setContextLoader(cl: JavaClassLoader) = Thread.currentThread.setContextClassLoader(cl)
  def getContextLoader() = Thread.currentThread.getContextClassLoader()
  def getSystemLoader(): ScalaClassLoader = ScalaClassLoader(null)
  def defaultParentClassLoader() = findExtClassLoader()
  
  def fromURLs(urls: Seq[URL], parent: ClassLoader = defaultParentClassLoader()): URLClassLoader =
    new URLClassLoader(urls.toList, parent)
  
  /** True if supplied class exists in supplied path */
  def classExists(urls: Seq[URL], name: String): Boolean =
    (fromURLs(urls) tryToLoadClass name).isDefined  
  
  // we cannot use the app classloader here or we get what looks to
  // be classloader deadlock, but if we pass null we bypass the extension
  // classloader and our extensions, so we search the hierarchy to find
  // the classloader whose parent is null.  Resolves bug #857.
  def findExtClassLoader(): JavaClassLoader = {
    def search(cl: JavaClassLoader): JavaClassLoader = {
      if (cl == null) null
      else if (cl.getParent == null) cl
      else search(cl.getParent)
    }
    
    search(getContextLoader())
  }

  /** Finding what jar a clazz or instance came from */
  def origin(x: Any): Option[URL] = originOfClass(x.asInstanceOf[AnyRef].getClass)  
=======
    try asContext(method.invoke(null, Array(arguments.toArray: AnyRef): _*)) // !!! : AnyRef shouldn't be necessary
    catch unwrapHandler({ case ex => throw ex })
  }

  /** A list comprised of this classloader followed by all its
   *  (non-null) parent classloaders, if any.
   */
  def loaderChain: List[ScalaClassLoader] = this :: (getParent match {
    case null => Nil
    case p    => p.loaderChain
  })
  override def toString = classLoaderUniqueId
}

/** Methods for obtaining various classloaders.
 *      appLoader: the application classloader.  (Also called the java system classloader.)
 *      extLoader: the extension classloader.
 *     bootLoader: the boot classloader.
 *  contextLoader: the context classloader.
 */
object ScalaClassLoader {
  /** Returns loaders which are already ScalaClassLoaders unaltered,
   *  and translates java.net.URLClassLoaders into scala URLClassLoaders.
   *  Otherwise creates a new wrapper.
   */
  implicit def apply(cl: JClassLoader): ScalaClassLoader = cl match {
    case cl: ScalaClassLoader => cl
    case cl: JURLClassLoader  => new URLClassLoader(cl.getURLs.toSeq, cl.getParent)
    case _                    => new JClassLoader(cl) with ScalaClassLoader
  }
  def contextLoader = apply(Thread.currentThread.getContextClassLoader)
  def appLoader     = apply(JClassLoader.getSystemClassLoader)
  def extLoader     = apply(appLoader.getParent)
  def bootLoader    = apply(null)
  def contextChain  = loaderChain(contextLoader)

  def pathToErasure[T: ClassManifest] = pathToClass(classManifest[T].erasure)
  def pathToClass(clazz: Class[_])    = clazz.getName.replace('.', JFile.separatorChar) + ".class"
  def locate[T: ClassManifest]        = contextLoader getResource pathToErasure[T]

  /** Tries to guess the classpath by type matching the context classloader
   *  and its parents, looking for any classloaders which will reveal their
   *  classpath elements as urls.  It it can't find any, creates a classpath
   *  from the supplied string.
   */
  def guessClassPathString(default: String = ""): String = {
    val classpathURLs = contextChain flatMap {
      case x: HasClassPath    => x.classPathURLs
      case x: JURLClassLoader => x.getURLs.toSeq
      case _                  => Nil
    }
    if (classpathURLs.isEmpty) default
    else JavaClassPath.fromURLs(classpathURLs).asClasspathString
  }

  def loaderChain(head: JClassLoader) = {
    def loop(cl: JClassLoader): List[JClassLoader] =
      if (cl == null) Nil else cl :: loop(cl.getParent)

    loop(head)
  }
  def setContext(cl: JClassLoader) =
    Thread.currentThread.setContextClassLoader(cl)
  def savingContextLoader[T](body: => T): T = {
    val saved = contextLoader
    try body
    finally setContext(saved)
  }

  class URLClassLoader(urls: Seq[URL], parent: JClassLoader)
      extends JURLClassLoader(urls.toArray, parent)
         with ScalaClassLoader
         with HasClassPath {

    private var classloaderURLs: Seq[URL] = urls
    private def classpathString = ClassPath.fromURLs(urls: _*)
    def classPathURLs: Seq[URL] = classloaderURLs
    def classPath: ClassPath[_] = JavaClassPath fromURLs classPathURLs

    /** Override to widen to public */
    override def addURL(url: URL) = {
      classloaderURLs :+= url
      super.addURL(url)
    }
    def toLongString = urls.mkString("URLClassLoader(id=" + classLoaderUniqueId + "\n  ", "\n  ", "\n)\n")
  }

  def fromURLs(urls: Seq[URL], parent: ClassLoader = null): URLClassLoader =
    new URLClassLoader(urls, parent)

  /** True if supplied class exists in supplied path */
  def classExists(urls: Seq[URL], name: String): Boolean =
    fromURLs(urls) tryToLoadClass name isDefined

  /** Finding what jar a clazz or instance came from */
  def origin(x: Any): Option[URL] = originOfClass(x.getClass)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def originOfClass(x: Class[_]): Option[URL] =
    Option(x.getProtectionDomain.getCodeSource) flatMap (x => Option(x.getLocation))
}
