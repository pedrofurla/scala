/* NEST (New Scala Test)
 * Copyright 2007-2011 LAMP/EPFL
 * @author Paul Phillips
 */

package scala.tools
package cmd

import scala.collection.mutable.ListBuffer

trait CommandLineConfig {
  def enforceArity: Boolean = true
  def onlyKnownOptions: Boolean = true
}

/** An instance of a command line, parsed according to a Spec.
 */
class CommandLine(val spec: Reference, val originalArgs: List[String]) extends CommandLineConfig {
  def this(spec: Reference, line: String) = this(spec, Parser tokenize line)
  def this(spec: Reference, args: Array[String]) = this(spec, args.toList)
<<<<<<< HEAD
    
  import spec.{ isAnyOption, isUnaryOption, isBinaryOption, isExpandOption }
  
=======

  import spec.{ isAnyOption, isUnaryOption, isBinaryOption, isExpandOption }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Terminator = "--"
  val ValueForUnaryOption = "true"  // so if --opt is given, x(--opt) = true

  def mapForUnary(opt: String) = Map(opt -> ValueForUnaryOption)
  def errorFn(msg: String) = println(msg)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** argMap is option -> argument (or "" if it is a unary argument)
   *  residualArgs are what is left after removing the options and their args.
   */
  lazy val (argMap, residualArgs) = {
    val residualBuffer = new ListBuffer[String]
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def loop(args: List[String]): Map[String, String] = {
      def residual(xs: List[String]) = { residualBuffer ++= xs ; Map[String, String]() }

      /** Returns Some(List(args)) if this option expands to an
       *  argument list and it's not returning only the same arg.
       */
      def expand(s1: String) = {
        if (isExpandOption(s1)) {
          val s2 = spec expandArg s1
          if (s2 == List(s1)) None
          else Some(s2)
        }
        else None
      }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      /** Assumes known options have all been ruled out already. */
      def isUnknown(opt: String) =
        onlyKnownOptions && (opt startsWith "-") && {
          errorFn("Option '%s' not recognized.".format(opt))
          true
        }

      args match {
        case Nil              => Map()
        case Terminator :: xs => residual(xs)
        case x :: Nil         =>
          expand(x) foreach (exp => return loop(exp))
          if (isBinaryOption(x) && enforceArity)
            errorFn("Option '%s' requires argument, found EOF instead.".format(x))
<<<<<<< HEAD
      
          if (isUnaryOption(x)) mapForUnary(x)
          else if (isUnknown(x)) Map()
          else residual(args)
          
        case x1 :: x2 :: xs   =>
          expand(x1) foreach (exp => return loop(exp ++ args.tail))
          
=======

          if (isUnaryOption(x)) mapForUnary(x)
          else if (isUnknown(x)) Map()
          else residual(args)

        case x1 :: x2 :: xs   =>
          expand(x1) foreach (exp => return loop(exp ++ args.tail))

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          if (x2 == Terminator)         mapForUnary(x1) ++ residual(xs)
          else if (isUnaryOption(x1))   mapForUnary(x1) ++ loop(args.tail)
          else if (isBinaryOption(x1))  Map(x1 -> x2) ++ loop(xs)
          else if (isUnknown(x1))       loop(args.tail)
          else                          residual(List(x1)) ++ loop(args.tail)
      }
    }
<<<<<<< HEAD
    
    (loop(originalArgs), residualBuffer map stripQuotes toList)
  }
  
=======

    (loop(originalArgs), residualBuffer map stripQuotes toList)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def apply(arg: String)  = argMap(arg)
  def get(arg: String)    = argMap get arg
  def isSet(arg: String)  = argMap contains arg

  def getOrElse(arg: String, orElse: => String) = if (isSet(arg)) apply(arg) else orElse

  override def toString() = argMap.toString + " " + residualArgs.toString
}
