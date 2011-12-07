<<<<<<< HEAD
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala

import annotation.target._
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2010-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala

import annotation.meta._
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/**
 * An annotation that designates the name of the parameter to which it is
 * applied as deprecated. Using that name in a named argument generates
 * a deprecation warning.
 *
 * For instance, evaluating the code below in the Scala interpreter
 * {{{
 *   def inc(x: Int, @deprecatedName('y) n: Int): Int = x + n
 *   inc(1, y = 2)
 * }}}
 * will produce the following output:
 * {{{
 * warning: there were 1 deprecation warnings; re-run with -deprecation for details
 * res0: Int = 3
 * }}}
 *
 * @since 2.8.1
 */
@param
class deprecatedName(name: Symbol) extends annotation.StaticAnnotation
