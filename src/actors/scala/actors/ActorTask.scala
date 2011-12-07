/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.actors

<<<<<<< HEAD
/** 
 *  @author Philipp Haller
=======
/**
 *  @author Philipp Haller
 *  @note   This class inherits a public var called 'msg' from ReactorTask,
 *  and also defines a constructor parameter which shadows it (which makes any
 *  changes to the underlying var invisible.) I can't figure out what's supposed
 *  to happen, so I renamed the constructor parameter to at least be less confusing.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */
private[actors] class ActorTask(actor: Actor,
                                fun: () => Unit,
                                handler: PartialFunction[Any, Any],
<<<<<<< HEAD
                                msg: Any)
  extends ReplyReactorTask(actor, fun, handler, msg) {
=======
                                initialMsg: Any)
  extends ReplyReactorTask(actor, fun, handler, initialMsg) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  protected override def beginExecution() {
    super.beginExecution()
    actor.synchronized { // shouldExit guarded by actor
      if (actor.shouldExit)
        actor.exit()
    }
  }

  protected override def terminateExecution(e: Throwable) {
    val senderInfo = try { Some(actor.sender) } catch {
      case _: Exception => None
    }
<<<<<<< HEAD
    val uncaught = UncaughtException(actor,
                                     if (msg != null) Some(msg) else None,
=======
    // !!! If this is supposed to be setting the current contents of the
    // inherited mutable var rather than always the value given in the constructor,
    // then it should be changed from initialMsg to msg.
    val uncaught = UncaughtException(actor,
                                     if (initialMsg != null) Some(initialMsg) else None,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                                     senderInfo,
                                     Thread.currentThread,
                                     e)

    val todo = actor.synchronized {
      if (!actor.links.isEmpty)
        actor.exitLinked(uncaught)
      else {
        super.terminateExecution(e)
        () => {}
      }
    }
    todo()
  }

}
