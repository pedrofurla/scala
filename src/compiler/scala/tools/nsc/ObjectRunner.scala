/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Lex Spoon
 */


package scala.tools.nsc

import java.net.URL
import util.ScalaClassLoader
import java.lang.reflect.InvocationTargetException
import util.Exceptional.unwrap

<<<<<<< HEAD
/** An object that runs another object specified by name.
 *
 *  @author  Lex Spoon
 *  @version 1.1, 2007/7/13
 */
object ObjectRunner {
=======
trait CommonRunner {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Check whether a class with the specified name
   *  exists on the specified class path. */
  def classExists(urls: List[URL], objectName: String): Boolean =
    ScalaClassLoader.classExists(urls, objectName)
<<<<<<< HEAD
  
  /** Run a given object, specified by name, using a
   *  specified classpath and argument list.
   *
   *  @throws ClassNotFoundException   
   *  @throws NoSuchMethodException
   *  @throws InvocationTargetException 
   */  
  def run(urls: List[URL], objectName: String, arguments: Seq[String]) {
    (ScalaClassLoader fromURLs urls).run(objectName, arguments)    
  }
  
=======

  /** Run a given object, specified by name, using a
   *  specified classpath and argument list.
   *
   *  @throws ClassNotFoundException
   *  @throws NoSuchMethodException
   *  @throws InvocationTargetException
   */
  def run(urls: List[URL], objectName: String, arguments: Seq[String]) {
    (ScalaClassLoader fromURLs urls).run(objectName, arguments)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Catches exceptions enumerated by run (in the case of InvocationTargetException,
   *  unwrapping it) and returns it any thrown in Left(x).
   */
  def runAndCatch(urls: List[URL], objectName: String, arguments: Seq[String]): Either[Throwable, Boolean] = {
    try   { run(urls, objectName, arguments) ; Right(true) }
    catch { case e => Left(unwrap(e)) }
  }
}
<<<<<<< HEAD
=======

/** An object that runs another object specified by name.
 *
 *  @author  Lex Spoon
 *  @version 1.1, 2007/7/13
 */
object ObjectRunner extends CommonRunner { }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
