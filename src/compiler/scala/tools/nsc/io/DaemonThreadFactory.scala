/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.nsc
package io

import java.util.concurrent._

class DaemonThreadFactory extends ThreadFactory {
  def newThread(r: Runnable): Thread = {
    val thread = new Thread(r)
    thread setDaemon true
    thread
  }
}

object DaemonThreadFactory {
  def newPool() = Executors.newCachedThreadPool(new DaemonThreadFactory)
}