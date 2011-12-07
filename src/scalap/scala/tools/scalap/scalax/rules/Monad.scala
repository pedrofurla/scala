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
trait Monad[+A] extends Functor[A] { 
=======
trait Monad[+A] extends Functor[A] {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  type M[+A] <: Monad[A]
  def flatMap[B](f : A => M[B]) : M[B]
}

trait Monads extends UnitFunctors {
  type M[+A] <: Monad[A]
<<<<<<< HEAD
  
  trait Monad[+A] extends Functor[A] with rules.Monad[A] { this : M[A] =>
    def map[B](f : A => B) = flatMap { a => unit(f(a)) }
  }
  
=======

  trait Monad[+A] extends Functor[A] with rules.Monad[A] { this : M[A] =>
    def map[B](f : A => B) = flatMap { a => unit(f(a)) }
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  trait ZeroMonad extends Monad[Nothing] with ZeroFunctor { this : M[Nothing] =>
    def flatMap[B](f : Nothing => M[B]) : M[B] = this
  }
}


trait StateReader extends Monads {
  type S
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def get : M[S]
  def read[A](f : S => A) : M[A]
  def set(s : => S) : M[S]
  def update(f : S => S) : M[S]
}




