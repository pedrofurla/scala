/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools.partest

import java.security._
import java.util._

abstract class SecurityTest extends App {
  def throwIt(x: Any) = throw new AccessControlException("" + x)

  def readPerm(p: PropertyPermission)            = p.getActions contains "read"
  def writePerm(p: PropertyPermission)           = p.getActions contains "write"
  def propertyCheck(p: PropertyPermission): Unit = throwIt(p)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def check(perm: Permission): Unit = perm match {
    case p: PropertyPermission  => propertyCheck(p)
    case _                      => ()
  }

  lazy val sm = new SecurityManager {
    // these two are the choke points for all permissions checks
    override def checkPermission(perm: Permission): Unit = check(perm)
    override def checkPermission(perm: Permission, context: Object): Unit = check(perm)
  }
  def securityOn(): Boolean = {
    try   { System.setSecurityManager(sm) ; true }
    catch { case _: SecurityException => false }
  }
}
