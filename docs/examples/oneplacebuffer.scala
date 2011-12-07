package examples

object oneplacebuffer {

<<<<<<< HEAD
  import scala.concurrent.{MailBox, ops}

  class OnePlaceBuffer {
    private val m = new MailBox() {}          // An internal mailbox
    private case class Empty()                // Types of messages we deal with
    private case class Full(x: Int)

    m send Empty()                            // Initialization

    def write(x: Int) {
      m receive {
        case Empty() =>
          println("put " + x)
          m send Full(x)
      }
    }

    def read: Int = m receive {
      case Full(x) =>
        println("get " + x)
        m send Empty(); x
    }
=======
  import scala.actors.Actor._
  import scala.concurrent.ops

  class OnePlaceBuffer {
    private case class Put(x: Int)
    private case object Get

    private val m = actor {
      var buf: Option[Int] = None
      loop {
        react {
          case Put(x) if buf.isEmpty =>
            println("put "+x); 
            buf = Some(x); reply()
          case Get if !buf.isEmpty =>
            val x = buf.get
            println("get "+x)
            buf = None; reply(x)
        }
      }
    }
    m.start()

    def write(x: Int) { m !? Put(x) }

    def read(): Int = (m !? Get).asInstanceOf[Int]
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  def kill(delay: Int) = new java.util.Timer().schedule(
    new java.util.TimerTask {
<<<<<<< HEAD
      override def run() = {
        println("[killed]")
        exit(0)
=======
      override def run() {
        println("[killed]")
        sys exit 0
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    },
    delay) // in milliseconds

  def main(args: Array[String]) {
    val buf = new OnePlaceBuffer
    val random = new java.util.Random()

    def producer(n: Int) {
<<<<<<< HEAD
      Thread.sleep(random.nextInt(1000))
      buf.write(n)
=======
      Thread.sleep(random nextInt 1000)
      buf write n
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      producer(n + 1)
    }

    def consumer {
<<<<<<< HEAD
      Thread.sleep(random.nextInt(1000))
      val n = buf.read
      consumer
    }

    ops.spawn(producer(0))
    ops.spawn(consumer)
=======
      Thread.sleep(random nextInt 1000)
      val n = buf.read()
      consumer
    }

    ops spawn producer(0)
    ops spawn consumer
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    kill(10000)
  }

}

