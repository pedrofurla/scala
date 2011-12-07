/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools
package reflect

import java.lang.reflect.Constructor
import nsc.util.ScalaClassLoader

/** A support class for simplifying the otherwise disbelief-inspiring
 *  process of working with classes completely reflectively.  This is
 *  the case with e.g. sun.misc.Signal* due to environments which are
 *  antagonistic to their use.  See SignalManager for an example.
 *
 *  The name "Shield" is a reference to shielding the JVM from knowledge
 *  of what we're doing.
 */
trait Shield {
  def className: String
  def classLoader: ScalaClassLoader
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // Override this if you are more ambitious about logging or throwing.
  def onError[T >: Null](msg: String): T = null

  /** This is handy because all reflective calls want back an AnyRef but
   *  we will often be generating Units.
   */
  protected implicit def boxedUnit(x: Unit): AnyRef = scala.runtime.BoxedUnit.UNIT
<<<<<<< HEAD
  
  lazy val clazz: Class[_] = classLoader.tryToLoadClass(className) getOrElse onError("Failed to load " + className)
  lazy val methods = clazz.getMethods.toList
  
  def constructor(paramTypes: Class[_]*) = clazz.getConstructor(paramTypes: _*).asInstanceOf[Constructor[AnyRef]]
  def method(name: String, arity: Int)   = uniqueMethod(name, arity)
  def field(name: String)                = clazz getField name
  
=======

  lazy val clazz: Class[_] = classLoader.tryToLoadClass(className) getOrElse onError("Failed to load " + className)
  lazy val methods = clazz.getMethods.toList

  def constructor(paramTypes: Class[_]*) = clazz.getConstructor(paramTypes: _*).asInstanceOf[Constructor[AnyRef]]
  def method(name: String, arity: Int)   = uniqueMethod(name, arity)
  def field(name: String)                = clazz getField name

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def matchingMethods(name: String, arity: Int) = methods filter (m => nameAndArity(m) == ((name, arity)))
  def uniqueMethod(name: String, arity: Int) = matchingMethods(name, arity) match {
    case List(x)  => x
    case _        => onError("No unique match for " + name)
  }
}
