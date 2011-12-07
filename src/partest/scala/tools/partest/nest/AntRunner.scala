/*                     __                                               *\
**     ________ ___   / /  ___     Scala Parallel Testing               **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.tools.partest
package nest

import java.io.File
import scala.tools.nsc.io.{ Directory }

class AntRunner extends DirectRunner {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val fileManager = new FileManager {
    var JAVACMD: String = "java"
    var JAVAC_CMD: String = "javac"
    var CLASSPATH: String = _
    var LATEST_LIB: String = _
    val testRootPath: String = "test"
    val testRootDir: Directory = Directory(testRootPath)
  }
<<<<<<< HEAD
  
  def reflectiveRunTestsForFiles(kindFiles: Array[File], kind: String) = 
=======

  def reflectiveRunTestsForFiles(kindFiles: Array[File], kind: String) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    runTestsForFiles(kindFiles.toList, kind)
}
