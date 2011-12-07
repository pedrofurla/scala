/*                     __                                               *\
**     ________ ___   / /  ___     Scala Parallel Testing               **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.tools
package partest

import nsc.io._
import category.AllCategories
import io.Logging

/** The high level view of the partest infrastructure.
 */
<<<<<<< HEAD
abstract class Universe 
=======
abstract class Universe
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      extends Entities
      with BuildContributors
      with Logging
      with Dispatcher
      with Statistics
      with Housekeeping
      with Results
      with PartestCompilation
      with PartestSpec
      with Config
      with Alarms
<<<<<<< HEAD
      with Actions 
=======
      with Actions
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      with Categories {

  /** The abstract values from which all else is derived. */
  def partestDir: Directory
  def testBuildDir: Directory
  def allCategories: List[TestCategory]
  def selectedCategories: List[TestCategory]
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Some plausibly abstract types. */
  type TestBuild    <: BuildContributor   // e.g. quick, pack
  type TestCategory <: AbsTestCategory    // e.g. pos, neg, run
  type TestEntity   <: AbsTestEntity      // e.g. files/pos/test25.scala
  type TestSequence <: AbsTestSequence    // e.g. compile, run, diff

  /** Although TestStep isn't much more than Function1 right now,
   *  it exists this way so it can become more capable.
   */
  implicit def f1ToTestStep(f: TestEntity => Boolean): TestStep =
    new TestStep { def apply(test: TestEntity) = f(test) }
<<<<<<< HEAD
  
  abstract class TestStep extends (TestEntity => Boolean) {    
=======

  abstract class TestStep extends (TestEntity => Boolean) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def apply(test: TestEntity): Boolean
  }

  /** An umbrella category of tests, such as "pos" or "run".
   */
  trait AbsTestCategory extends BuildContributor {
    type TestSettings
<<<<<<< HEAD
    
    def kind: String
    def testSequence: TestSequence
    def denotesTest(location: Path): Boolean
    
=======

    def kind: String
    def testSequence: TestSequence
    def denotesTest(location: Path): Boolean

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def createTest(location: Path): TestEntity
    def createSettings(entity: TestEntity): TestSettings
    def enumerate: List[TestEntity]
  }
<<<<<<< HEAD
  
  /** A single test.  It may involve multiple files, but only a 
   *  single path is used to designate it.
   */
  trait AbsTestEntity extends BuildContributor {  
=======

  /** A single test.  It may involve multiple files, but only a
   *  single path is used to designate it.
   */
  trait AbsTestEntity extends BuildContributor {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def category: TestCategory
    def location: Path
    def onException(x: Throwable): Unit
    def testClasspath: String
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** Most tests will use the sequence defined by the category,
     *  but the test can override and define a custom sequence.
     */
    def testSequence: TestSequence
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** True if this test recognizes the given path as a piece of it.
     *  For validation purposes.
     */
    def acknowledges(path: Path): Boolean
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Every TestEntity is partly characterized by a series of actions
   *  which are applied to the TestEntity in the given order.  The test
   *  passes if all those actions return true, fails otherwise.
   */
  trait AbsTestSequence {
    def actions: List[TestStep]
  }
}