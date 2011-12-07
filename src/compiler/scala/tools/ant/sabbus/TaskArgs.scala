/*                     __                                               *\
**     ________ ___   / /  ___     Scala Ant Tasks                      **
**    / __/ __// _ | / /  / _ |    (c) 2005-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.tools.ant.sabbus

import java.io.File
import org.apache.tools.ant.Task
import org.apache.tools.ant.types.{Path, Reference}
<<<<<<< HEAD

trait CompilationPathProperty {
  this: Task =>
  
  protected var compilationPath: Option[Path] = None
  
=======
import org.apache.tools.ant.types.Commandline.Argument

trait CompilationPathProperty {
  this: Task =>

  protected var compilationPath: Option[Path] = None

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setCompilationPath(input: Path) {
    if (compilationPath.isEmpty) compilationPath = Some(input)
    else compilationPath.get.append(input)
  }

  def createCompilationPath: Path = {
    if (compilationPath.isEmpty) compilationPath = Some(new Path(getProject()))
    compilationPath.get.createPath()
  }

  def setCompilationPathRef(input: Reference) {
    createCompilationPath.setRefid(input)
  }
}

trait TaskArgs extends CompilationPathProperty {
  this: Task =>
<<<<<<< HEAD
  
  def setId(input: String) {
    id = Some(input)
  }
  
  def setParams(input: String) {
    params = params match {
      case None => Some(input)
      case Some(ps) => Some(ps + " " + input)
    }
  }
  
=======

  def setId(input: String) {
    id = Some(input)
  }

  def setParams(input: String) {
    extraArgs ++= input.split(' ').map { s => val a = new Argument; a.setValue(s); a }
  }

  def createCompilerArg(): Argument = {
    val a = new Argument
    extraArgs :+= a
    a
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def setTarget(input: String) {
    compTarget = Some(input)
  }

  def setSrcPath(input: Path) {
    if (sourcePath.isEmpty) sourcePath = Some(input)
    else sourcePath.get.append(input)
  }

  def createSrcPath: Path = {
    if (sourcePath.isEmpty) sourcePath = Some(new Path(getProject()))
    sourcePath.get.createPath()
  }

  def setSrcPathRef(input: Reference) {
    createSrcPath.setRefid(input)
  }

  def setCompilerPath(input: Path) {
    if (compilerPath.isEmpty) compilerPath = Some(input)
    else compilerPath.get.append(input)
  }

  def createCompilerPath: Path = {
    if (compilerPath.isEmpty) compilerPath = Some(new Path(getProject()))
    compilerPath.get.createPath()
  }

  def setCompilerPathRef(input: Reference) {
    createCompilerPath.setRefid(input)
  }
<<<<<<< HEAD
  
  def setDestdir(input: File) {
    destinationDir = Some(input)
  }
  
  protected var id: Option[String] = None
  protected var params: Option[String] = None
=======

  def setDestdir(input: File) {
    destinationDir = Some(input)
  }

  protected var id: Option[String] = None
  protected var extraArgs: Seq[Argument] = Seq()
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  protected var compTarget: Option[String] = None
  protected var sourcePath: Option[Path] = None
  protected var compilerPath: Option[Path] = None
  protected var destinationDir: Option[File] = None
<<<<<<< HEAD
  
=======

  def extraArgsFlat: Seq[String] = extraArgs flatMap { a =>
    val parts = a.getParts
    if(parts eq null) Seq[String]() else parts.toSeq
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def isMSIL = compTarget exists (_ == "msil")
}
