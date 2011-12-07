<<<<<<< HEAD
package scala.annotation

/**
 * When defining a field, the Scala compiler creates up to four accessors
 * for it: a getter, a setter, and if the field is annotated with
 * `@BeanProperty`, a bean getter and a bean setter.
 *
 * For instance in the following class definition
 *
 * {{{
 * class C(@myAnnot @BeanProperty var c: Int)
 * }}}
 *
 * there are six entities which can carry the annotation `@myAnnot`: the
 * constructor parameter, the generated field and the four accessors.
 *
 * By default, annotations on (`val`-, `var`- or plain) constructor parameters
 * end up on the parameter, not on any other entity. Annotations on fields
 * by default only end up on the field.
 *
 * The meta-annotations in package `scala.annotation.target` are used
 * to control where annotations on fields and class parameters are copied.
 * This is done by annotating either the annotation type or the annotation
 * class with one or several of the meta-annotations in this package.
 *
 * ==Annotating the annotation type==
 *
 * The target meta-annotations can be put on the annotation type when
 * instantiating the annotation. In the following example, the annotation
 * `@Id` will be added only to the bean getter `getX`.
 *
 * {{{
 * import javax.persistence.Id
 * class A {
 *   @(Id @beanGetter) @BeanProperty val x = 0
 * }
 * }}}
 *
 * In order to annotate the field as well, the meta-annotation `@field`
 * would need to be added.
 *
 * The syntax can be improved using a type alias:
 *
 * {{{
 * object ScalaJPA {
 *   type Id = javax.persistence.Id @beanGetter
 * }
 * import ScalaJPA.Id
 * class A {
 *   @Id @BeanProperty val x = 0
 * }
 * }}}
 *
 * ==Annotating the annotation class==
 *
 * For annotations defined in Scala, a default target can be specified
 * in the annotation class itself, for example
 *
 * {{{
 * @getter
 * class myAnnotation extends Annotation
 * }}}
 *
 * This only changes the default target for the annotation `myAnnotation`.
 * When instantiating the annotation, the target can still be specified
 * as described in the last section.
 */
package object target
=======
/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.annotation

package object target {
  @deprecated("Use `@scala.annotation.meta.beanGetter` instead", "2.10.0")
  type beanGetter = scala.annotation.meta.beanGetter

  @deprecated("Use `@scala.annotation.meta.beanSetter` instead", "2.10.0")
  type beanSetter = scala.annotation.meta.beanSetter

  @deprecated("Use `@scala.annotation.meta.field` instead", "2.10.0")
  type field = scala.annotation.meta.field

  @deprecated("Use `@scala.annotation.meta.getter` instead", "2.10.0")
  type getter = scala.annotation.meta.getter

  @deprecated("Use `@scala.annotation.meta.param` instead", "2.10.0")
  type param = scala.annotation.meta.param

  @deprecated("Use `@scala.annotation.meta.setter` instead", "2.10.0")
  type setter = scala.annotation.meta.setter
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
