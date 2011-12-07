package scala.tools.nsc.io

import java.io.Writer

class PrettyWriter(wr: Writer) extends Writer {
  protected val indentStep = "  "
  private var indent = 0
  private def newLine() {
    wr.write('\n')
    wr.write(indentStep * indent)
  }
  def close() = wr.close()
  def flush() = wr.flush()
  def write(str: Array[Char], off: Int, len: Int): Unit = {
    if (off < str.length && off < len) {
      str(off) match {
<<<<<<< HEAD
        case '{' | '[' | '(' => 
=======
        case '{' | '[' | '(' =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          indent += 1
          wr.write(str(off))
          newLine()
          wr.write(str, off + 1, len - 1)
        case '}' | ']' | ')' =>
          wr.write(str, off, len)
          indent -= 1
        case ',' =>
          wr.write(',')
          newLine()
          wr.write(str, off + 1, len - 1)
        case ':' =>
          wr.write(':')
          wr.write(' ')
          wr.write(str, off + 1, len - 1)
        case _ =>
          wr.write(str, off, len)
<<<<<<< HEAD
      } 
=======
      }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    } else {
      wr.write(str, off, len)
    }
  }
  override def toString = wr.toString
<<<<<<< HEAD
} 
=======
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
