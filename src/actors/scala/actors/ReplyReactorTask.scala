/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id$

package scala.actors

/**
 *  @author Philipp Haller
<<<<<<< HEAD
 */
private[actors] class ReplyReactorTask(reactor: ReplyReactor,
                                       fun: () => Unit,
                                       handler: PartialFunction[Any, Any],
                                       msg: Any)
  extends ReactorTask(reactor, fun, handler, msg) {
=======
 *  @note   This class inherits a public var called 'reactor' from ReactorTask,
 *  and also defines a constructor parameter which shadows it (which makes any
 *  changes to the underlying var invisible.) I can't figure out what's supposed
 *  to happen, so I renamed the constructor parameter to at least be less confusing.
 */
private[actors] class ReplyReactorTask(replyReactor: ReplyReactor,
                                       fun: () => Unit,
                                       handler: PartialFunction[Any, Any],
                                       msg: Any)
  extends ReactorTask(replyReactor, fun, handler, msg) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  var saved: ReplyReactor = _

  protected override def beginExecution() {
    saved = Actor.tl.get
<<<<<<< HEAD
    Actor.tl set reactor
=======
    // !!! If this is supposed to be setting the current contents of the
    // inherited mutable var rather than always the value given in the constructor,
    // then it should be changed to "set reactor".
    Actor.tl set replyReactor
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  protected override def suspendExecution() {
    Actor.tl set saved
  }

}
