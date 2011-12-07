/*                     __                                               *\
**     ________ ___   / /  ___     Scala Ant Tasks                      **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.tools.ant.sabbus

import org.apache.tools.ant.Task

class Break extends Task {
<<<<<<< HEAD
  
  def setId(input: String) {
    id = Some(input)
  }
  
  private var id: Option[String] = None
  
=======

  def setId(input: String) {
    id = Some(input)
  }

  private var id: Option[String] = None

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def execute() {
    if (id.isEmpty) sys.error("Attribute 'id' is not set")
    Compilers.break(id.get)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
