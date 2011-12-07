/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.reflect

/** This type is required by the compiler and <b>should not be used in client code</b>. */
<<<<<<< HEAD
class Code[T](val tree: scala.reflect.mirror.Tree)
=======
class Code[T: Manifest](val tree: scala.reflect.mirror.Tree) {
  val manifest = implicitly[Manifest[T]]
  override def toString = "Code(tree = "+tree+", manifest = "+manifest+")"
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/** This type is required by the compiler and <b>should not be used in client code</b>. */
object Code {
  def lift[A](tree: A): Code[A] =
    throw new Error("Code was not lifted by compiler")
}
