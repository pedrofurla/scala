/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala

/** This class implements errors which are thrown whenever an
 *  object doesn't match any pattern of a pattern matching
 *  expression.
<<<<<<< HEAD
 *  
=======
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *  @author  Matthias Zenger
 *  @author  Martin Odersky
 *  @version 1.1, 05/03/2004
 *  @since   2.0
 */
final class MatchError(obj: Any) extends RuntimeException {
  /** There's no reason we need to call toString eagerly,
   *  so defer it until getMessage is called.
   */
  private lazy val objString =
    if (obj == null) "null"
<<<<<<< HEAD
    else obj.toString() + " (of class " + obj.asInstanceOf[AnyRef].getClass.getName + ")"
=======
    else obj.toString() + " (of class " + obj.getClass.getName + ")"
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  override def getMessage() = objString
}
