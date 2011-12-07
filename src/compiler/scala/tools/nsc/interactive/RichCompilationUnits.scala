/* NSC -- new Scala compiler
 * Copyright 2009-2011 Scala Solutions and LAMP/EPFL
 * @author Martin Odersky
 */
package scala.tools.nsc
package interactive

import scala.tools.nsc.util.{SourceFile, Position, NoPosition}
import collection.mutable.ArrayBuffer

trait RichCompilationUnits { self: Global =>

  /** The status value of a unit that has not yet been loaded */
  final val NotLoaded = -2

  /** The status value of a unit that has not yet been typechecked */
  final val JustParsed = -1

  /** The status value of a unit that has been partially typechecked */
  final val PartiallyChecked = 0

  class RichCompilationUnit(source: SourceFile) extends CompilationUnit(source) {
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** The runid of the latest compiler run that typechecked this unit,
     *  or else @see NotLoaded, JustParsed
     */
    var status: Int = NotLoaded

    /** Unit has been parsed */
    def isParsed: Boolean = status >= JustParsed

    /** Unit has been typechecked, but maybe not in latest runs */
    def isTypeChecked: Boolean = status > JustParsed

    /** Unit has been typechecked and is up to date */
    def isUpToDate: Boolean = status >= minRunId
<<<<<<< HEAD
    
    /** the current edit point offset */
    var editPoint: Int = -1
    
    /** The problems reported for this unit */
    val problems = new ArrayBuffer[Problem]
    
=======

    /** the current edit point offset */
    var editPoint: Int = -1

    /** The problems reported for this unit */
    val problems = new ArrayBuffer[Problem]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** The position of a targeted type check
     *  If this is different from NoPosition, the type checking
     *  will stop once a tree that contains this position range
     *  is fully attributed.
     */
    var _targetPos: Position = NoPosition
    override def targetPos: Position = _targetPos
    def targetPos_=(p: Position) { _targetPos = p }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    var contexts: Contexts = new Contexts

    /** The last fully type-checked body of this unit */
    var lastBody: Tree = EmptyTree
  }
}
