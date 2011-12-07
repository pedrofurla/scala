/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */
package scala.tools.nsc
package ast.parser

/** A patch that postulates that a brace needs to be inserted or deleted at a given position.
<<<<<<< HEAD
 *  @param off  The offset where the brace needs to be inserted or deleted 
 *  @param inserted  If true, brace needs to be inserted, otherwise brace needs to be deleted.
 */
case class BracePatch(off: Int, inserted: Boolean) 
=======
 *  @param off  The offset where the brace needs to be inserted or deleted
 *  @param inserted  If true, brace needs to be inserted, otherwise brace needs to be deleted.
 */
case class BracePatch(off: Int, inserted: Boolean)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
extends Patch(off, if (inserted) Insertion("{") else Deletion(1))