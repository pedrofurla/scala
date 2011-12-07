package scala.tools.nsc.scratchpad

import java.io.Writer
import reflect.internal.Chars._


class CommentWriter(underlying: SourceInserter, startCol: Int = 40, endCol: Int = 152) extends Writer {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def rightCol(marker: String) = {
    while (underlying.column < startCol) underlying.write(' ')
    underlying.write(marker)
  }
<<<<<<< HEAD
   
  private var lastWasNL = false
  
  private def writeChar(ch: Char) = {
    if (underlying.column >= endCol) {
      underlying.write('\n'); rightCol("//| ") 
    }
    if (underlying.column < startCol) rightCol("//> ")
    underlying.write(ch)  
    lastWasNL = isLineBreakChar(ch)
  }
  
=======

  private var lastWasNL = false

  private def writeChar(ch: Char) = {
    if (underlying.column >= endCol) {
      underlying.write('\n'); rightCol("//| ")
    }
    if (underlying.column < startCol) rightCol("//> ")
    underlying.write(ch)
    lastWasNL = isLineBreakChar(ch)
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def write(chs: Array[Char], off: Int, len: Int) = {
    for (i <- off until off + len) writeChar(chs(i))
    flush()
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def skip(len: Int) {
    if (lastWasNL) {
      underlying.backspace()
      lastWasNL = false
    }
    underlying.skip(len)
    if (underlying.column >= startCol) underlying.write('\n')
  }

  override def close() = underlying.close()
  override def flush() = underlying.flush()
}

