package scala.reflect.runtime

<<<<<<< HEAD
class AbstractFile(val jfile: java.io.File) {  
 def path: String = jfile.getPath()
=======
class AbstractFile(val jfile: java.io.File) {
 def path: String = jfile.getPath()
 def canonicalPath: String = jfile.getCanonicalPath()
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}