/* NSC -- new Scala compiler
 * Copyright 2007-2011 LAMP/EPFL
 * @author  Pedro Furlanetto
 */

package scala.tools.nsc
package doc
package html
package page
import doc.model._

class ReferenceIndex(letter: Char, index: doc.Index, universe: Universe) extends HtmlPage {

  def path = List("index-"+letter+".html", "index")

  def title = {
    val s = universe.settings
    ( if (!s.doctitle.isDefault) s.doctitle.value else "" ) +
<<<<<<< HEAD
    ( if (!s.docversion.isDefault) (" " + s.docversion.value) else "" ) 
=======
    ( if (!s.docversion.isDefault) (" " + s.docversion.value) else "" )
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  }

  def headers =
    <xml:group>
      <link href={ relativeLinkTo(List("ref-index.css", "lib")) }  media="screen" type="text/css" rel="stylesheet"/>
      <script type="text/javascript" src={ relativeLinkTo{List("jquery.js", "lib")} }></script>
    </xml:group>


  private def entry(name: String, methods: Iterable[MemberEntity]) = {
    val occurrences = methods.map(method => {
      val html = templateToHtml(method.inDefinitionTemplates.head)
      if (method.deprecation.isDefined) {
        <strike>{ html }</strike>
      } else {
        html
      }
    })

    <div class="entry">
      <div class="name">{
        if (methods.find { ! _.deprecation.isDefined } != None)
          name
        else
          <strike>{ name }</strike>
      }</div>
      <div class="occurrences">{
        for (owner <- occurrences) yield owner ++ xml.Text(" ")
      }</div>
    </div>
  }

  def body =
    <body>{
      for(groups <- index.firstLetterIndex(letter)) yield
        entry(groups._1, groups._2.view)
    }</body>

}