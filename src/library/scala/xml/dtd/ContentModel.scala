/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.xml
package dtd

import util.regexp.WordExp
import util.automata._
import Utility.sbToString
import PartialFunction._

object ContentModel extends WordExp {
  type _labelT = ElemName
  type _regexpT = RegExp

  object Translator extends WordBerrySethi {
    override val lang: ContentModel.this.type = ContentModel.this
  }

  case class ElemName(name: String) extends Label {
    override def toString() = """ElemName("%s")""" format name
  }

  def isMixed(cm: ContentModel) = cond(cm) { case _: MIXED => true }
  def containsText(cm: ContentModel) = (cm == PCDATA) || isMixed(cm)
  def parse(s: String): ContentModel = ContentModelParser.parse(s)

<<<<<<< HEAD
  def getLabels(r: RegExp): Set[String] = {    
=======
  def getLabels(r: RegExp): Set[String] = {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def traverse(r: RegExp): Set[String] = r match { // !!! check for match translation problem
      case Letter(ElemName(name)) => Set(name)
      case Star(  x @ _  ) => traverse( x ) // bug if x@_*
      case Sequ( xs @ _* ) => Set(xs map traverse flatten: _*)
      case Alt(  xs @ _* ) => Set(xs map traverse flatten: _*)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    traverse(r)
  }

  def buildString(r: RegExp): String = sbToString(buildString(r, _))

  /* precond: rs.length >= 1 */
  private def buildString(rs: Seq[RegExp], sb: StringBuilder, sep: Char) {
    buildString(rs.head, sb)
    for (z <- rs.tail) {
      sb append sep
      buildString(z, sb)
    }
<<<<<<< HEAD
    sb
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  def buildString(c: ContentModel, sb: StringBuilder): StringBuilder = c match {
    case ANY                    => sb append "ANY"
    case EMPTY                  => sb append "EMPTY"
    case PCDATA                 => sb append "(#PCDATA)"
    case ELEMENTS(_) | MIXED(_) => c buildString sb
  }

  def buildString(r: RegExp, sb: StringBuilder): StringBuilder =
    r match {  // !!! check for match translation problem
<<<<<<< HEAD
      case Eps => 
        sb
      case Sequ(rs @ _*) => 
        sb.append( '(' ); buildString(rs, sb, ','); sb.append( ')' )
      case Alt(rs @ _*) =>
        sb.append( '(' ); buildString(rs, sb, '|');  sb.append( ')' )
      case Star(r: RegExp) => 
=======
      case Eps =>
        sb
      case Sequ(rs @ _*) =>
        sb.append( '(' ); buildString(rs, sb, ','); sb.append( ')' )
      case Alt(rs @ _*) =>
        sb.append( '(' ); buildString(rs, sb, '|');  sb.append( ')' )
      case Star(r: RegExp) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        sb.append( '(' ); buildString(r, sb); sb.append( ")*" )
      case Letter(ElemName(name)) =>
        sb.append(name)
    }

}

sealed abstract class ContentModel
{
  override def toString(): String = sbToString(buildString)
  def buildString(sb: StringBuilder): StringBuilder
}

case object PCDATA extends ContentModel {
  override def buildString(sb: StringBuilder): StringBuilder = sb.append("(#PCDATA)")
}
case object EMPTY extends ContentModel {
  override def buildString(sb: StringBuilder): StringBuilder = sb.append("EMPTY")
}
case object ANY extends ContentModel {
  override def buildString(sb: StringBuilder): StringBuilder = sb.append("ANY")
}
sealed abstract class DFAContentModel extends ContentModel {
  import ContentModel.{ ElemName, Translator }
  def r: ContentModel.RegExp
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val dfa: DetWordAutom[ElemName] = {
    val nfa = Translator.automatonFrom(r, 1)
    new SubsetConstruction(nfa).determinize
  }
}

case class MIXED(r: ContentModel.RegExp) extends DFAContentModel {
  import ContentModel.{ Alt, RegExp }

  override def buildString(sb: StringBuilder): StringBuilder = {
    val newAlt = r match { case Alt(rs @ _*) => Alt(rs drop 1: _*) }

    sb append "(#PCDATA|"
    ContentModel.buildString(newAlt: RegExp, sb)
    sb append ")*"
  }
}

case class ELEMENTS(r: ContentModel.RegExp) extends DFAContentModel {
<<<<<<< HEAD
  override def buildString(sb: StringBuilder): StringBuilder =  
=======
  override def buildString(sb: StringBuilder): StringBuilder =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    ContentModel.buildString(r, sb)
}
