// -----------------------------------------------------------------------------
//
//  Scalax - The Scala Community Library
//  Copyright (c) 2005-8 The Scalax Project. All rights reserved.
//
//  The primary distribution site is http://scalax.scalaforge.org/
//
//  This software is released under the terms of the Revised BSD License.
//  There is NO WARRANTY.  See the file LICENSE for the full text.
//
// -----------------------------------------------------------------------------

package scala.tools.scalap
package scalax
package rules

<<<<<<< HEAD
/** 
=======
/**
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * A workaround for the difficulties of dealing with
 * a contravariant 'In' parameter type...
 */
class InRule[In, +Out, +A, +X](rule : Rule[In, Out, A, X]) {
<<<<<<< HEAD
  
  def mapRule[Out2, B, Y](f : Result[Out, A, X] => In => Result[Out2, B, Y]) : Rule[In, Out2, B, Y] = rule.factory.rule { 
=======

  def mapRule[Out2, B, Y](f : Result[Out, A, X] => In => Result[Out2, B, Y]) : Rule[In, Out2, B, Y] = rule.factory.rule {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    in : In => f(rule(in))(in)
  }

  /** Creates a rule that succeeds only if the original rule would fail on the given context. */
<<<<<<< HEAD
  def unary_! : Rule[In, In, Unit, Nothing] = mapRule { 
=======
  def unary_! : Rule[In, In, Unit, Nothing] = mapRule {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    case Success(_, _) => in : In => Failure
    case _ => in : In => Success(in, ())
  }

  /** Creates a rule that succeeds if the original rule succeeds, but returns the original input. */
  def & : Rule[In, In, A, X] = mapRule {
    case Success(_, a) => in : In => Success(in, a)
    case Failure => in : In => Failure
    case Error(x) => in : In => Error(x)
  }
}

class SeqRule[S, +A, +X](rule : Rule[S, S, A, X]) {
  import rule.factory._

<<<<<<< HEAD
  def ? = rule mapRule { 
=======
  def ? = rule mapRule {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    case Success(out, a) => in : S => Success(out, Some(a))
    case Failure => in : S => Success(in, None)
    case Error(x) => in : S => Error(x)
  }
<<<<<<< HEAD
  
  /** Creates a rule that always succeeds with a Boolean value.  
   *  Value is 'true' if this rule succeeds, 'false' otherwise */
  def -? = ? map { _ isDefined }
        
  def * = from[S] { 
=======

  /** Creates a rule that always succeeds with a Boolean value.
   *  Value is 'true' if this rule succeeds, 'false' otherwise */
  def -? = ? map { _ isDefined }

  def * = from[S] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    // tail-recursive function with reverse list accumulator
    def rep(in : S, acc : List[A]) : Result[S, List[A], X] = rule(in) match {
       case Success(out, a) => rep(out, a :: acc)
       case Failure => Success(in, acc.reverse)
       case err : Error[_] => err
    }
    in => rep(in, Nil)
  }
<<<<<<< HEAD
  
  def + = rule ~++ *
    
  def ~>?[B >: A, X2 >: X](f : => Rule[S, S, B => B, X2]) = for (a <- rule; fs <- f?) yield fs.foldLeft[B](a) { (b, f) => f(b) }
  
  def ~>*[B >: A, X2 >: X](f : => Rule[S, S, B => B, X2]) = for (a <- rule; fs <- f*) yield fs.foldLeft[B](a) { (b, f) => f(b) }
    
  def ~*~[B >: A, X2 >: X](join : => Rule[S, S, (B, B) => B, X2]) = {
    this ~>* (for (f <- join; a <- rule) yield f(_ : B, a))
  }
  
=======

  def + = rule ~++ *

  def ~>?[B >: A, X2 >: X](f : => Rule[S, S, B => B, X2]) = for (a <- rule; fs <- f?) yield fs.foldLeft[B](a) { (b, f) => f(b) }

  def ~>*[B >: A, X2 >: X](f : => Rule[S, S, B => B, X2]) = for (a <- rule; fs <- f*) yield fs.foldLeft[B](a) { (b, f) => f(b) }

  def ~*~[B >: A, X2 >: X](join : => Rule[S, S, (B, B) => B, X2]) = {
    this ~>* (for (f <- join; a <- rule) yield f(_ : B, a))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Repeats this rule one or more times with a separator (which is discarded) */
  def +/[X2 >: X](sep : => Rule[S, S, Any, X2]) = rule ~++ (sep -~ rule *)

  /** Repeats this rule zero or more times with a separator (which is discarded) */
  def */[X2 >: X](sep : => Rule[S, S, Any, X2]) = +/(sep) | state[S].nil
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def *~-[Out, X2 >: X](end : => Rule[S, Out, Any, X2]) = (rule - end *) ~- end
  def +~-[Out, X2 >: X](end : => Rule[S, Out, Any, X2]) = (rule - end +) ~- end

  /** Repeats this rule num times */
<<<<<<< HEAD
  def times(num : Int) : Rule[S, S, Seq[A], X] = from[S] { 
=======
  def times(num : Int) : Rule[S, S, Seq[A], X] = from[S] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val result = new collection.mutable.ArraySeq[A](num)
    // more compact using HoF but written this way so it's tail-recursive
    def rep(i : Int, in : S) : Result[S, Seq[A], X] = {
      if (i == num) Success(in, result)
      else rule(in) match {
       case Success(out, a) => {
         result(i) = a
         rep(i + 1, out)
       }
       case Failure => Failure
       case err : Error[_] => err
      }
    }
    in => rep(0, in)
  }
}

