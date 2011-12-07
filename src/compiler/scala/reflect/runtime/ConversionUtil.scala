package scala.reflect
package runtime

import java.lang.{Class => jClass, Package => jPackage}
import java.lang.reflect.{
<<<<<<< HEAD
  Method => jMethod, Constructor => jConstructor, Modifier => jModifier, Field => jField, 
  Member => jMember, Type => jType, TypeVariable => jTypeVariable, GenericDeclaration}
import collection.mutable.HashMap

trait ConversionUtil extends internal.transform.Transforms { self: Universe =>
  
=======
  Method => jMethod, Constructor => jConstructor, Modifier => jModifier, Field => jField,
  Member => jMember, Type => jType, TypeVariable => jTypeVariable, GenericDeclaration}
import collection.mutable.HashMap

trait ConversionUtil { self: SymbolTable =>

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** A cache that maintains a bijection between Java reflection type `J`
   *  and Scala reflection type `S`.
   */
  protected class TwoWayCache[J, S] {
<<<<<<< HEAD
    
    private val toScalaMap = new HashMap[J, S]
    private val toJavaMap = new HashMap[S, J]
    
    def enter(j: J, s: S) = {
=======

    private val toScalaMap = new HashMap[J, S]
    private val toJavaMap = new HashMap[S, J]

    def enter(j: J, s: S) = {
      debugInfo("cached: "+j+"/"+s)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      toScalaMap(j) = s
      toJavaMap(s) = j
    }

    def toScala(key: J)(body: => S): S = toScalaMap get key match {
<<<<<<< HEAD
      case Some(v) => 
        v
      case none => 
=======
      case Some(v) =>
        v
      case none =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val result = body
        enter(key, result)
        result
    }
<<<<<<< HEAD
    
    def toJava(key: S)(body: => J): J = toJavaMap get key match {
      case Some(v) => 
        v
      case none => 
=======

    def toJava(key: S)(body: => J): J = toJavaMap get key match {
      case Some(v) =>
        v
      case none =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val result = body
        enter(result, key)
        result
    }
<<<<<<< HEAD
    
    def toJavaOption(key: S)(body: => Option[J]): Option[J] = toJavaMap get key match {
      case None => 
=======

    def toJavaOption(key: S)(body: => Option[J]): Option[J] = toJavaMap get key match {
      case None =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        val result = body
        for (value <- result) enter(value, key)
        result
      case some => some
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected val classCache = new TwoWayCache[jClass[_], Symbol]
  protected val packageCache = new TwoWayCache[Package, Symbol]
  protected val methodCache = new TwoWayCache[jMethod, Symbol]
  protected val constructorCache = new TwoWayCache[jConstructor[_], Symbol]
  protected val fieldCache = new TwoWayCache[jField, Symbol]
  protected val tparamCache = new TwoWayCache[jTypeVariable[_], Symbol]
<<<<<<< HEAD
  
  def typeToJavaClass(tpe: Type): jClass[_]
  
=======

  /** the type of this symbol after Scala -> Java transformsi in refChecks, uncurry, erasure
   */
  def transformedType(sym: Symbol): Type

  /** The Java class thaty given type compiles to */
  def typeToJavaClass(tpe: Type): jClass[_]

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Does method `meth` erase to Java method `jmeth`?
   *  This is true if the Java method type is the same as the Scala method type after performing
   *  all Scala-specific transformations in InfoTransformers. (to be done)
   */
  protected def erasesTo(meth: Symbol, jmeth: jMethod): Boolean = {
    val mtpe = transformedType(meth)
    (mtpe.paramTypes map typeToJavaClass) == jmeth.getParameterTypes.toList &&
    typeToJavaClass(mtpe.resultType) == jmeth.getReturnType
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Does constructor `meth` erase to Java method `jconstr`?
   *  This is true if the Java constructor type is the same as the Scala constructor type after performing
   *  all Scala-specific transformations in InfoTransformers. (to be done)
   */
  protected def erasesTo(meth: Symbol, jconstr: jConstructor[_]): Boolean = {
    val mtpe = transformedType(meth)
    (mtpe.paramTypes map typeToJavaClass) == jconstr.getParameterTypes.toList
  }
}