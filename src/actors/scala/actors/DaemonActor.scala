<<<<<<< HEAD
/*                     __                                               *\ 
**     ________ ___   / /  ___     Scala API                            ** 
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             ** 
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               ** 
** /____/\___/_/ |_/____/_/ | |                                         ** 
**                          |/                                          ** 
\*                                                                      */ 
 
package scala.actors 

import scheduler.DaemonScheduler
 
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.actors

import scheduler.DaemonScheduler

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
/**
 * Base trait for actors with daemon semantics.
 *
 * Unlike a regular `Actor`, an active `DaemonActor` will not
<<<<<<< HEAD
 * prevent an application terminating, much like a daemon thread. 
=======
 * prevent an application terminating, much like a daemon thread.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * @author Erik Engbrecht
 */
trait DaemonActor extends Actor {
  override def scheduler: IScheduler = DaemonScheduler
}
