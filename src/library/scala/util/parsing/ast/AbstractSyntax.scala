/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.util.parsing.ast

import scala.util.parsing.input.Positional

/** This component provides the core abstractions for representing an Abstract Syntax Tree
 *
<<<<<<< HEAD
 * @author Adriaan Moors 
=======
 * @author Adriaan Moors
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
trait AbstractSyntax {
  /** The base class for elements of the abstract syntax tree.
   */
  trait Element extends Positional
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** The base class for elements in the AST that represent names [[scala.util.parsing.ast.Binders]].
   */
  trait NameElement extends Element {
    def name: String
    override def equals(that: Any): Boolean = that match {
      case n: NameElement => n.name == name
      case _ => false
    }
  }
}
