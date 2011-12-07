/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools
package reflect

import java.lang.reflect.{ Method, Proxy, InvocationHandler }

/** A wrapper around java dynamic proxies to make it easy to pose
 *  as an interface.  See SignalManager for an example usage.
 */
trait Mock extends (Invoked => AnyRef) {
  mock =>
<<<<<<< HEAD
  
  def interfaces: List[Class[_]]
  def classLoader: ClassLoader
  def apply(invoked: Invoked): AnyRef
  
=======

  def interfaces: List[Class[_]]
  def classLoader: ClassLoader
  def apply(invoked: Invoked): AnyRef

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newProxyInstance(handler: InvocationHandler): AnyRef =
    Proxy.newProxyInstance(classLoader, interfaces.toArray, handler)
  def newProxyInstance(): AnyRef =
    newProxyInstance(newInvocationHandler())
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newInvocationHandler() = new InvocationHandler {
    def invoke(proxy: AnyRef, method: Method, args: Array[AnyRef]) =
      mock(Invoked(proxy, method, args))
  }
}

/** The methods in Mock create the actual proxy instance which can be used
 *  in place of the associated interface(s).
 */
object Mock {
  /** The default implementation calls the partial function if defined, and
   *  routes Object methods to the proxy: otherwise it throws an exception.
   */
  def fromInterfaces(clazz: Class[_], clazzes: Class[_]*)(pf: PartialFunction[Invoked, AnyRef]): AnyRef = {
    val ints = clazz :: clazzes.toList
    require(ints forall (_.isInterface), "All class objects must represent interfaces")
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val mock = new Mock {
      val interfaces  = ints
      def classLoader = clazz.getClassLoader
      def apply(invoked: Invoked) =
        if (pf.isDefinedAt(invoked)) pf(invoked)
        else if (invoked.isObjectMethod) invoked invokeOn this
        else throw new NoSuchMethodException("" + invoked)
    }
    mock.newProxyInstance()
  }
  /** Tries to implement all the class's interfaces.
   */
  def fromClass(clazz: Class[_])(pf: PartialFunction[Invoked, AnyRef]): AnyRef = allInterfaces(clazz) match {
    case Nil      => sys.error(clazz + " implements no interfaces.")
    case x :: xs  => fromInterfaces(x, xs: _*)(pf)
  }
}
