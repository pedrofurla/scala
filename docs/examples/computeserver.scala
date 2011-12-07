package examples

import concurrent._, concurrent.ops._

class ComputeServer(n: Int) {

  private trait Job {
    type t
    def task: t
    def ret(x: t): Unit
  }

  private val openJobs = new Channel[Job]()

<<<<<<< HEAD
  private def processor(i: Int): Unit = {
=======
  private def processor(i: Int) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    while (true) {
      val job = openJobs.read
      println("read a job")
      job.ret(job.task)
    }
  }

  def future[a](p: => a): () => a = {
    val reply = new SyncVar[a]()
    openJobs.write{
      new Job {
        type t = a
        def task = p
        def ret(x: a) = reply.set(x)
      }
    }
    () => reply.get
  }

<<<<<<< HEAD
  spawn(replicate(0, n) { processor })
}

object computeserver extends Application {
=======
  //spawn(replicate(0, n) { processor })
  spawn((0 until n).par foreach { processor })
}

object computeserver extends App {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  def kill(delay: Int) = new java.util.Timer().schedule(
    new java.util.TimerTask {
      override def run() = {
        println("[killed]")
<<<<<<< HEAD
        System.exit(0)
=======
        sys exit 0
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    },
    delay) // in milliseconds

  val server = new ComputeServer(1)
  val f = server.future(42)
  println(f())
  kill(10000)
}
