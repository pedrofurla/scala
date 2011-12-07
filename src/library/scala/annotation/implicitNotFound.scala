/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.annotation
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
/**
 * An annotation that specifies the error message that is emitted when the compiler
 * cannot find an implicit value of the annotated type.
 *
 * @author Adriaan Moors
 * @since 2.8.1
 */
final class implicitNotFound(msg: String) extends annotation.StaticAnnotation {}