/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 */

package scala.tools
package partest

import nsc.io._
import nsc.util.ClassPath

trait BuildContributors {
  universe: Universe =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A trait mixed into types which contribute a portion of the values.
   *  The basic mechanism is the TestBuild, TestCategory, and TestEntity
   *  can each contribute to each value.  They are assembled at the last
   *  moment by the ContributorAssembler (presently the TestEntity.)
   */
<<<<<<< HEAD
  trait BuildContributor {    
=======
  trait BuildContributor {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def javaFlags: List[String]
    def scalacFlags: List[String]
    def classpathPaths: List[Path]
    def buildProperties: List[(String, Any)]
    def buildEnvironment: Map[String, String]
  }
<<<<<<< HEAD
  
  trait ContributorAssembler {    
    def contributors: List[BuildContributor]
    def assemble[T](what: BuildContributor => List[T]): List[T] = contributors flatMap what
    
=======

  trait ContributorAssembler {
    def contributors: List[BuildContributor]
    def assemble[T](what: BuildContributor => List[T]): List[T] = contributors flatMap what

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** !!! This will need work if we want to achieve real composability,
     *  but it can wait for the demand.
     */
    def assembleScalacArgs(args: List[String])  = assemble(_.scalacFlags) ++ args
    def assembleJavaArgs(args: List[String])    = assemble(_.javaFlags) ++ args
    def assembleProperties()                    = assemble(_.buildProperties)
<<<<<<< HEAD
    def assembleClasspaths(paths: List[Path])   = assemble(_.classpathPaths) ++ paths    
    def assembleEnvironment()                   = assemble(_.buildEnvironment.toList).toMap

    def createClasspathString() = ClassPath fromPaths (assembleClasspaths(Nil) : _*)
    def createPropertyString()  = assembleProperties() map { case (k, v) => "-D%s=%s".format(k, v.toString) }    
  }
  
=======
    def assembleClasspaths(paths: List[Path])   = assemble(_.classpathPaths) ++ paths
    def assembleEnvironment()                   = assemble(_.buildEnvironment.toList).toMap

    def createClasspathString() = ClassPath fromPaths (assembleClasspaths(Nil) : _*)
    def createPropertyString()  = assembleProperties() map { case (k, v) => "-D%s=%s".format(k, v.toString) }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait BuildContribution extends BuildContributor {
    self: TestBuild =>

    /** The base classpath and system properties.
     *  !!! TODO - this should adjust itself depending on the build
     *  being tested, because pack and quick at least need different jars.
     */
    def classpathPaths    = List[Path](library, compiler, partest, fjbg) ++ forkJoinPath
    def buildProperties   = List(
      "scala.home"          -> testBuildDir,
      "partest.lib"         -> library,   // used in jvm/inner
      "java.awt.headless"   -> true,
      "user.language"       -> "en",
      "user.country"        -> "US",
      "partest.debug"       -> isDebug,
      "partest.verbose"     -> isVerbose
      // Disabled because there are no natives tests.
      // "java.library.path"   -> srcLibDir
    )
    def javaFlags: List[String]   = toArgs(javaOpts)
    def scalacFlags: List[String] = toArgs(scalacOpts)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** We put the build being tested's /bin directory in the front of the
     *  path so the scripts and such written to execute "scala" will use this
     *  build and not whatever happens to be on their path.
     */
    private def modifiedPath  = ClassPath.join(scalaBin.path, Properties.envOrElse("PATH", ""))
    def buildEnvironment      = Map("PATH" -> modifiedPath)
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait CategoryContribution extends BuildContributor {
    self: DirBasedCategory =>

    /** Category-wide classpath additions placed in <category>/lib. */
    private def libContents = root / "lib" ifDirectory (_.list.toList)

    def classpathPaths    = libContents getOrElse Nil
    def buildProperties   = Nil
    def javaFlags         = Nil
    def scalacFlags       = Nil
    def buildEnvironment  = Map()
  }

  trait TestContribution extends BuildContributor with ContributorAssembler {
    self: TestEntity =>
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def jarsInTestDir     = location.walk collect { case f: File if f hasExtension "jar" => f } toList

    def contributors      = List(build, category, self)
    def javaFlags         = safeArgs(javaOptsFile)
    def scalacFlags       = safeArgs(scalaOptsFile)
    def classpathPaths    = jarsInTestDir :+ outDir
    def buildProperties   = List(
      "partest.output"  -> outDir.toAbsolute,         // used in jvm/inner
      "partest.cwd"     -> outDir.parent.toAbsolute   // used in shootout tests
    )
    def buildEnvironment  = Map("JAVA_OPTS" -> fromArgs(assembleJavaArgs(Nil)))
  }
}