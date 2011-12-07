/* NSC -- new Scala compiler
 * Copyright 2006-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package util

trait ClassPathSettings {
  def javabootclasspath: String   // -javabootclasspath
  def javaextdirs: String         // -javaextdirs
  def bootclasspath: String       // -bootclasspath
  def extdirs: String             // -extdirs
  def classpath: String           // -classpath
  def sourcepath: String          // -sourcepath
}

// val debugLogger = {
//   val f = File("/tmp/path-resolve-log.txt")
//   if (f.exists) f.truncate()
//   else f.createFile()
<<<<<<< HEAD
//   
=======
//
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
//   val res = f.bufferedWriter()
//   res write ("Started debug log: %s\n".format(new java.util.Date))
//   res
// }
// def log(msg: Any) = {
//   Console println msg
//   debugLogger.write(msg.toString + "\n")
//   debugLogger flush
// }

