package scala.tools.nsc
package symtab

import scala.tools.nsc.util.{ SourceFile, Position, OffsetPosition, NoPosition }

<<<<<<< HEAD
trait Positions extends scala.reflect.internal.Positions { 
self: scala.tools.nsc.symtab.SymbolTable =>

  def rangePos(source: SourceFile, start: Int, point: Int, end: Int) = 
=======
trait Positions extends scala.reflect.internal.Positions {
self: scala.tools.nsc.symtab.SymbolTable =>

  def rangePos(source: SourceFile, start: Int, point: Int, end: Int) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new OffsetPosition(source, point)

  def validatePositions(tree: Tree) {}

  type Position = scala.tools.nsc.util.Position
  val NoPosition = scala.tools.nsc.util.NoPosition
<<<<<<< HEAD
  
  def focusPos(pos: Position): Position = pos.focus
  def isRangePos(pos: Position): Boolean = pos.isRange
  def showPos(pos: Position): String = pos.show
  
=======

  def focusPos(pos: Position): Position = pos.focus
  def isRangePos(pos: Position): Boolean = pos.isRange
  def showPos(pos: Position): String = pos.show

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
