/*                     __                                               *\
 **     ________ ___   / /  ___     Scala API                            **
 **    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
 **  __\ \/ /__/ __ |/ /__/ __ |    http://www.scala-lang.org/           **
 ** /____/\___/_/ |_/____/_/ | |                                         **
 **                          |/                                          **
 \*                                                                      */

<<<<<<< HEAD

=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.xml
package dtd

import Utility.sbToString

abstract class Decl

abstract class MarkupDecl extends Decl {
  def buildString(sb: StringBuilder): StringBuilder
}

<<<<<<< HEAD
/** an element declaration 
 */
case class ElemDecl(name: String, contentModel: ContentModel)
extends MarkupDecl
{
  override def buildString(sb: StringBuilder): StringBuilder = {
    sb
    .append("<!ELEMENT ")
    .append(name)
    .append(' ');

    ContentModel.buildString(contentModel, sb);
    sb.append('>');
=======
/** an element declaration
 */
case class ElemDecl(name: String, contentModel: ContentModel)
extends MarkupDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
    sb append "<!ELEMENT " append name append ' '

    ContentModel.buildString(contentModel, sb)
    sb append '>'
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

case class AttListDecl(name: String, attrs:List[AttrDecl])
extends MarkupDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb
    .append("<!ATTLIST ")
    .append(name)
    .append('\n')
    .append(attrs.mkString("","\n",">"));
=======
    sb append "<!ATTLIST " append name append '\n' append attrs.mkString("","\n",">")
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

/** an attribute declaration. at this point, the tpe is a string. Future
 *  versions might provide a way to access the attribute types more
 *  directly.
 */
case class AttrDecl(name: String, tpe: String, default: DefaultDecl) {
  override def toString(): String = sbToString(buildString)

  def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb.append("  ").append(name).append(' ').append(tpe).append(' ');
    default.buildString(sb)
=======
    sb append "  " append name append ' ' append tpe append ' '
    default buildString sb
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

}

/** an entity declaration */
<<<<<<< HEAD
abstract class EntityDecl extends MarkupDecl 
=======
abstract class EntityDecl extends MarkupDecl
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/** a parsed general entity declaration */
case class ParsedEntityDecl(name: String, entdef: EntityDef) extends EntityDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb.append("<!ENTITY ").append( name ).append(' ');
    entdef.buildString(sb).append('>')
=======
    sb append "<!ENTITY " append name append ' '
    entdef buildString sb append '>'
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

/** a parameter entity declaration */
case class ParameterEntityDecl(name: String, entdef: EntityDef) extends EntityDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb.append("<!ENTITY % ").append( name ).append(' ');
    entdef.buildString(sb).append('>')
=======
    sb append "<!ENTITY % " append name append ' '
    entdef buildString sb append '>'
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

/** an unparsed entity declaration */
case class UnparsedEntityDecl( name:String, extID:ExternalID, notation:String ) extends EntityDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb.append("<!ENTITY ").append( name ).append(' ');
    extID.buildString(sb).append(" NDATA ").append(notation).append('>');
=======
    sb append "<!ENTITY " append name append ' '
    extID buildString sb append " NDATA " append notation append '>'
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}
/** a notation declaration */
case class NotationDecl( name:String, extID:ExternalID ) extends MarkupDecl {
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    sb.append("<!NOTATION ").append( name ).append(' ');
    extID.buildString(sb)
=======
    sb append "<!NOTATION " append name append ' '
    extID buildString sb
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }
}

abstract class EntityDef {
  def buildString(sb: StringBuilder): StringBuilder
}

case class IntDef(value:String) extends EntityDef {
  private def validateValue() {
<<<<<<< HEAD
    var tmp = value;
    var ix  = tmp.indexOf('%');
    while( ix != -1) {
      val iz = tmp.indexOf(';', ix);
      if(iz == -1 && iz == ix + 1)
        throw new IllegalArgumentException("no % allowed in entity value, except for parameter-entity-references");
      else {
        val n = tmp.substring(ix, iz);

        if( !Utility.isName( n )) 
          throw new IllegalArgumentException("internal entity def: \""+n+"\" must be an XML Name");

        tmp = tmp.substring(iz+1, tmp.length());
        ix  = tmp.indexOf('%');
      }
    }
  }
  validateValue();

  override def buildString(sb: StringBuilder): StringBuilder = 
    Utility.appendQuoted(value, sb);
=======
    var tmp = value
    var ix  = tmp indexOf '%'
    while (ix != -1) {
      val iz = tmp.indexOf(';', ix)
      if(iz == -1 && iz == ix + 1)
        throw new IllegalArgumentException("no % allowed in entity value, except for parameter-entity-references")
      else {
        val n = tmp.substring(ix, iz)

        if (!Utility.isName(n))
          throw new IllegalArgumentException("internal entity def: \""+n+"\" must be an XML Name")

        tmp = tmp.substring(iz+1, tmp.length)
        ix  = tmp indexOf '%'
      }
    }
  }
  validateValue()

  override def buildString(sb: StringBuilder): StringBuilder =
    Utility.appendQuoted(value, sb)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

}

case class ExtDef(extID:ExternalID) extends EntityDef {
<<<<<<< HEAD
  override def buildString(sb: StringBuilder): StringBuilder = 
    extID.buildString(sb);
=======
  override def buildString(sb: StringBuilder): StringBuilder =
    extID buildString sb
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}


/** a parsed entity reference */
case class PEReference(ent:String) extends MarkupDecl {
<<<<<<< HEAD
  if( !Utility.isName( ent )) 
    throw new IllegalArgumentException("ent must be an XML Name");
  
  override def buildString(sb: StringBuilder): StringBuilder = 
    sb.append('%').append(ent).append(';');
=======
  if( !Utility.isName( ent ))
    throw new IllegalArgumentException("ent must be an XML Name");

  override def buildString(sb: StringBuilder): StringBuilder =
    sb append '%' append ent append ';'
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}


// default declarations for attributes

abstract class DefaultDecl {
  override def toString(): String
  def buildString(sb: StringBuilder): StringBuilder
}

case object REQUIRED extends DefaultDecl {
  override def toString(): String = "#REQUIRED"
<<<<<<< HEAD
  override def buildString(sb: StringBuilder) = sb.append("#REQUIRED")
=======
  override def buildString(sb: StringBuilder) = sb append "#REQUIRED"
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

case object IMPLIED extends DefaultDecl {
  override def toString(): String = "#IMPLIED"
<<<<<<< HEAD
  override def buildString(sb: StringBuilder) = sb.append("#IMPLIED")
=======
  override def buildString(sb: StringBuilder) = sb append "#IMPLIED"
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}

case class DEFAULT(fixed: Boolean, attValue: String) extends DefaultDecl {
  override def toString(): String = sbToString(buildString)
  override def buildString(sb: StringBuilder): StringBuilder = {
<<<<<<< HEAD
    if (fixed) sb.append("#FIXED ")
=======
    if (fixed) sb append "#FIXED "
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    Utility.appendEscapedQuoted(attValue, sb)
  }
}
