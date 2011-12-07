package scala.tools
package util

import com.yourkit.api._
import com.yourkit.runtime._
import nsc.io._

class YourkitProfiling extends Profiling {
  @volatile private var active = false
  @volatile private var freq: Option[Int] = None
  lazy val controller = new Controller
<<<<<<< HEAD
  
  def defaultFreq = 100
  def allocationFreq = freq
  def setAllocationFreq(x: Int) = freq = if (x <= 0) None else Some(x)
  
=======

  def defaultFreq = 100
  def allocationFreq = freq
  def setAllocationFreq(x: Int) = freq = if (x <= 0) None else Some(x)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def startRecordingAllocations() = {
    controller.startAllocationRecording(true, freq getOrElse defaultFreq, false, 0)
  }
  def stopRecordingAllocations() = {
    controller.stopAllocationRecording()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def startProfiling(): Unit = {
    if (isActive)
      return

    active = true
<<<<<<< HEAD
    daemonize(true) {
=======
    daemonize {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      try {
        controller.startCPUProfiling(ProfilingModes.CPU_SAMPLING, Controller.DEFAULT_FILTERS)
        if (freq.isDefined)
          startRecordingAllocations()
      }
      catch {
<<<<<<< HEAD
        case _: PresentableException  => () // if it's already running, no big deal 
=======
        case _: PresentableException  => () // if it's already running, no big deal
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      }
    }
  }

  def captureSnapshot() = {
<<<<<<< HEAD
    daemonize(true)(controller.captureSnapshot(ProfilingModes.SNAPSHOT_WITH_HEAP))
  }
  
=======
    daemonize(controller.captureSnapshot(ProfilingModes.SNAPSHOT_WITH_HEAP))
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def stopProfiling() = {
    try {
      if (freq.isDefined)
        stopRecordingAllocations()
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      controller.stopCPUProfiling()
    }
    catch {
      case _: PresentableException  => () // if it's already running, no big deal
    }
    finally active = false
  }
<<<<<<< HEAD
  
  def advanceGeneration(desc: String) {
    controller.advanceGeneration(desc)
  }
  
=======

  def advanceGeneration(desc: String) {
    controller.advanceGeneration(desc)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isActive = active
}
