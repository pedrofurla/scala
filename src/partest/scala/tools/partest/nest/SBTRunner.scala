package scala.tools.partest
package nest

import java.io.File
import scala.tools.nsc.io.{ Directory }


class SBTRunner extends DirectRunner {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val fileManager = new FileManager {
    var JAVACMD: String        = "java"
    var JAVAC_CMD: String      = "javac"
    var CLASSPATH: String      = _
    var LATEST_LIB: String     = _
    val testRootPath: String   = PathSettings.testRoot.path
    val testRootDir: Directory = PathSettings.testRoot
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def reflectiveRunTestsForFiles(kindFiles: Array[File], kind: String):java.util.HashMap[String,Int] = {

    def convert(scalaM:scala.collection.immutable.Map[String,Int]):java.util.HashMap[String,Int] = {
      val javaM = new java.util.HashMap[String,Int]()
      for(elem <- scalaM) yield {javaM.put(elem._1,elem._2)}
      javaM
    }

    def failedOnlyIfRequired(files:List[File]):List[File]={
<<<<<<< HEAD
      if (fileManager.failed) files filter (x => fileManager.logFileExists(x, kind)) else files 
    }

    convert(runTestsForFiles(failedOnlyIfRequired(kindFiles.toList), kind))
    
=======
      if (fileManager.failed) files filter (x => fileManager.logFileExists(x, kind)) else files
    }

    convert(runTestsForFiles(failedOnlyIfRequired(kindFiles.toList), kind))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

