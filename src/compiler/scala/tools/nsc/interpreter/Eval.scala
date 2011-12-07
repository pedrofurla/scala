/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package interpreter

trait Eval {
  /** Executes code looking for an implicit conversion from the type
   *  of the given identifier to CompletionAware.
   */
  // def completionAwareImplicit[T](id: String) = {
  //   val f1string = "%s => %s".format(typeForIdent(id).get, classOf[CompletionAware].getName)
  //   val code = """{
  //     |  def f(implicit x: (%s) = null): %s = x
  //     |  val f1 = f
  //     |  if (f1 == null) None else Some(f1(%s))
  //     |}""".stripMargin.format(f1string, f1string, id)
<<<<<<< HEAD
  //   
  //   evalExpr[Option[CompletionAware]](code)
  // }
  
  // Coming soon  
=======
  //
  //   evalExpr[Option[CompletionAware]](code)
  // }

  // Coming soon
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  // implicit def string2liftedcode(s: String): LiftedCode = new LiftedCode(s)
  // case class LiftedCode(code: String) {
  //   val lifted: String = {
  //     beQuietDuring { interpret(code) }
  //     eval2[String]("({ " + code + " }).toString")
  //   }
  //   def >> : String = lifted
  // }
}