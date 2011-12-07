package scala.reflect
package internal

trait Positions extends api.Positions { self: SymbolTable =>
<<<<<<< HEAD
  
  def focusPos(pos: Position): Position
  def isRangePos(pos: Position): Boolean
  def showPos(pos: Position): String
  
=======

  def focusPos(pos: Position): Position
  def isRangePos(pos: Position): Boolean
  def showPos(pos: Position): String

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A position that wraps a set of trees.
   *  The point of the wrapping position is the point of the default position.
   *  If some of the trees are ranges, returns a range position enclosing all ranges
   *  Otherwise returns default position.
   */
  def wrappingPos(default: Position, trees: List[Tree]): Position = default

  /** A position that wraps the non-empty set of trees.
   *  The point of the wrapping position is the point of the first trees' position.
   *  If all some the trees are non-synthetic, returns a range position enclosing the non-synthetic trees
   *  Otherwise returns a synthetic offset position to point.
   */
  def wrappingPos(trees: List[Tree]): Position = trees.head.pos

  /** Ensure that given tree has no positions that overlap with
   *  any of the positions of `others`. This is done by
   *  shortening the range or assigning TransparentPositions
   *  to some of the nodes in `tree`.
   */
  def ensureNonOverlapping(tree: Tree, others: List[Tree]) {}
}