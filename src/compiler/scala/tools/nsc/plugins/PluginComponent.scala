/* NSC -- new Scala compiler
 * Copyright 2007-2011 LAMP/EPFL
 * @author Lex Spoon
 * Updated by Anders Bach Nielsen
 */

package scala.tools.nsc
package plugins

/** A component that is part of a Plugin.
 *
<<<<<<< HEAD
 * @author Lex Spoon 
=======
 * @author Lex Spoon
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @version 1.1, 2009/1/2
 * Updated 2009/1/2 by Anders Bach Nielsen: Added features to implement SIP 00002
 */
abstract class PluginComponent extends SubComponent {

  /** Internal flag to tell external from internal phases */
  final override val internal = false

  /** Phases supplied by plugins should not have give the runsRightAfter constraint,
   * but can override it */
  val runsRightAfter: Option[String] = None

}
