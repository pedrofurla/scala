<<<<<<< HEAD
/* NSC -- new Scala compiler
 * Copyright 2007-2011 LAMP/EPFL
 * @author  David Bernard, Manohar Jonnalagedda
 */
=======
/* NSC -- new Scala compiler
 * Copyright 2007-2011 LAMP/EPFL
 * @author  David Bernard, Manohar Jonnalagedda
 */
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

package scala.tools.nsc.doc
package html

import doclet._

/** The default doclet used by the scaladoc command line tool
  * when no user-provided doclet is provided. */
class Doclet extends Generator with Universer with Indexer {

  def generateImpl() {
    new html.HtmlFactory(universe, index).generate
  }

}
