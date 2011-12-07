package scala.tools.nsc
package interactive

import util.{SourceFile, BatchSourceFile, RangePosition}
import collection.mutable.ArrayBuffer
import reflect.internal.Chars.isLineBreakChar

trait ScratchPadMaker { self: Global =>
<<<<<<< HEAD
  
  import definitions._
  
  private case class Patch(offset: Int, text: String)
  
  private class Patcher(contents: Array[Char], endOffset: Int) extends Traverser {
    var objectName: String = ""
      
=======

  import definitions._

  private case class Patch(offset: Int, text: String)

  private class Patcher(contents: Array[Char], endOffset: Int) extends Traverser {
    var objectName: String = ""

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private val patches = new ArrayBuffer[Patch]
    private val toPrint = new ArrayBuffer[String]
    private var skipped = 0
    private var resNum: Int = -1
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def nextRes(): String = {
      resNum += 1
      "res$"+resNum
    }
<<<<<<< HEAD
    
    private def nameType(name: String, tpe: Type): String = name+": "+tpe
    
    private def nameType(sym: Symbol): String = nameType(sym.name.toString, sym.tpe)
     
    private def literal(str: String) = "\"\"\""+str+"\"\"\""
    
=======

    private def nameType(name: String, tpe: Type): String = name+": "+tpe

    private def nameType(sym: Symbol): String = nameType(sym.name.toString, sym.tpe)

    private def literal(str: String) = "\"\"\""+str+"\"\"\""

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def applyPendingPatches(offset: Int) = {
      if (skipped == 0) patches += Patch(offset, "import scala.tools.nsc.scratchpad.Executor._; ")
      for (msg <- toPrint) patches += Patch(offset, ";System.out.println("+msg+")")
      toPrint.clear()
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    private def addSkip(stat: Tree): Unit = {
      if (stat.pos.start > skipped) applyPendingPatches(stat.pos.start)
      if (stat.pos.start >= endOffset)
        patches += Patch(stat.pos.start, ";$stop()")
      var end = stat.pos.end
      if (end > skipped) {
        while (end < contents.length && !(isLineBreakChar(contents(end)))) end += 1
        patches += Patch(stat.pos.start, ";$skip("+(end-skipped)+"); ")
        skipped = end
      }
    }
<<<<<<< HEAD
 
    private def addSandbox(expr: Tree) = {}
//      patches += (Patch(expr.pos.start, "sandbox("), Patch(expr.pos.end, ")"))
    
    private def resultString(prefix: String, expr: String) = 
      literal(prefix + " = ") + " + $show(" + expr + ")"
    
    private def traverseStat(stat: Tree) = 
=======

    private def addSandbox(expr: Tree) = {}
//      patches += (Patch(expr.pos.start, "sandbox("), Patch(expr.pos.end, ")"))

    private def resultString(prefix: String, expr: String) =
      literal(prefix + " = ") + " + $show(" + expr + ")"

    private def traverseStat(stat: Tree) =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      if (stat.pos.isInstanceOf[RangePosition]) {
        stat match {
          case ValDef(_, _, _, rhs) =>
            addSkip(stat)
            if (stat.symbol.isLazy)
              toPrint += literal(nameType(stat.symbol) + " = <lazy>")
            else if (!stat.symbol.isSynthetic) {
              addSandbox(rhs)
              toPrint += resultString(nameType(stat.symbol), stat.symbol.name.toString)
            }
          case DefDef(_, _, _, _, _, _) =>
            addSkip(stat)
            toPrint += literal(nameType(stat.symbol))
          case Annotated(_, arg) =>
            traverse(arg)
          case DocDef(_, defn) =>
            traverse(defn)
          case _ =>
            if (stat.isTerm) {
              addSkip(stat)
              if (stat.tpe.typeSymbol == UnitClass) {
                addSandbox(stat)
              } else {
                val resName = nextRes()
                val dispResName = resName filter ('$' !=)
                patches += Patch(stat.pos.start, "val " + resName + " = ")
                addSandbox(stat)
                toPrint += resultString(nameType(dispResName, stat.tpe), resName)
              }
            }
        }
      }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    override def traverse(tree: Tree): Unit = tree match {
      case PackageDef(_, _) =>
        super.traverse(tree)
      case ModuleDef(_, name, Template(_, _, body)) =>
<<<<<<< HEAD
        if (objectName.length == 0 /* objectName.isEmpty does not compile on Java 5 due to ambiguous implicit conversions: augmentString, stringToTermName */) 
          objectName = tree.symbol.fullName
        body foreach traverseStat
        applyPendingPatches(skipped)
      case _ =>  
    }
    
    /** The patched text. 
=======
        if (objectName.length == 0 /* objectName.isEmpty does not compile on Java 5 due to ambiguous implicit conversions: augmentString, stringToTermName */)
          objectName = tree.symbol.fullName
        body foreach traverseStat
        applyPendingPatches(skipped)
      case _ =>
    }

    /** The patched text.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *  @require  traverse is run first
     */
    def result: Array[Char] = {
      val reslen = contents.length + (patches map (_.text.length)).sum
      val res = Array.ofDim[Char](reslen)
      var lastOffset = 0
      var from = 0
      var to = 0
      for (Patch(offset, text) <- patches) {
        val delta = offset - lastOffset
        assert(delta >= 0)
        Array.copy(contents, from, res, to, delta)
        from += delta
        to += delta
        lastOffset = offset
        text.copyToArray(res, to)
        to += text.length
      }
      assert(contents.length - from == reslen - to)
      Array.copy(contents, from, res, to, contents.length - from)
      res
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /** Compute an instrumented version of a sourcefile.
   *  @param source  The given sourcefile.
   *  @param line    The line up to which results should be printed, -1 = whole document.
   *  @return        A pair consisting of
   *                  - the fully qualified name of the first top-level object definition in the file.
   *                    or "" if there are no object definitions.
<<<<<<< HEAD
   *                  - the text of the instrumented program which, when run, 
=======
   *                  - the text of the instrumented program which, when run,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *                    prints its output and all defined values in a comment column.
   */
  protected def instrument(source: SourceFile, line: Int): (String, Array[Char]) = {
    val tree = typedTree(source, true)
    val endOffset = if (line < 0) source.length else source.lineToOffset(line + 1)
    val patcher = new Patcher(source.content, endOffset)
<<<<<<< HEAD
    patcher.traverse(tree)    
=======
    patcher.traverse(tree)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    (patcher.objectName, patcher.result)
  }
}
