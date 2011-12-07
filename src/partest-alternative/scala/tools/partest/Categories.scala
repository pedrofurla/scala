/*                     __                                               *\
**     ________ ___   / /  ___     Scala Parallel Testing               **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.tools
package partest

import nsc.Settings
import nsc.io._
import nsc.util.{ ClassPath }

trait Categories {
  self: Universe =>
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait TestCategory extends AbsTestCategory {
    def kind: String
    def startMessage: String = "Executing test group"
    def testSequence: TestSequence

    class TestSettings(entity: TestEntity, error: String => Unit) extends Settings(error) {
      def this(entity: TestEntity) = this(entity, Console println _)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      deprecation.value   = false
      encoding.value      = "ISO-8859-1"
      classpath.value     = entity.testClasspath
      outdir.value        = entity.outDir.path
    }

    def createSettings(entity: TestEntity): TestSettings = new TestSettings(entity)
    def createTest(location: Path): TestEntity =
      if (location.isFile) TestFile(this, location.toFile)
      else if (location.isDirectory) TestDirectory(this, location.toDirectory)
      else error("Failed to create test at '%s'" format location)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Category test identification.
     */
    def denotesTestFile(p: Path)  = p.isFile && (p hasExtension "scala")
    def denotesTestDir(p: Path)   = p.isDirectory && !ignorePath(p)
    def denotesTest(p: Path)      = denotesTestDir(p) || denotesTestFile(p)
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** This should verify that all necessary files are present.
     *  By default it delegates to denotesTest.
     */
    def denotesValidTest(p: Path) = denotesTest(p)
  }
<<<<<<< HEAD
  
  abstract class DirBasedCategory(val kind: String) extends TestCategory with CategoryContribution {
    lazy val root = Directory(src / kind).normalize
    def enumerate = root.list filter denotesTest map createTest toList
    
    /** Standard actions.  These can be overridden either on the
     *  Category level or by individual tests.
     */     
=======

  abstract class DirBasedCategory(val kind: String) extends TestCategory with CategoryContribution {
    lazy val root = Directory(src / kind).normalize
    def enumerate = root.list filter denotesTest map createTest toList

    /** Standard actions.  These can be overridden either on the
     *  Category level or by individual tests.
     */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def compile: TestStep         = (_: TestEntity).compile()
    def checkFileRequired: TestStep  = (_: TestEntity).checkFileRequired
    def diff: TestStep            = (_: TestEntity).diff()
    def run: TestStep             = (_: TestEntity).run()
    def exec: TestStep            = (_: TestEntity).exec()
<<<<<<< HEAD
    
    /** Combinators.
     */
    def not(f: TestStep): TestStep = !f(_: TestEntity)
    
=======

    /** Combinators.
     */
    def not(f: TestStep): TestStep = !f(_: TestEntity)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def toString = kind
  }
}