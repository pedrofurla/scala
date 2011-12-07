/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.xml
package parsing

import java.net.URL
import java.io.File.separator

import scala.io.Source

/**
 *  @author  Burak Emir
 *  @version 1.0
 */
<<<<<<< HEAD
trait ExternalSources { 
=======
trait ExternalSources {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  self: ExternalSources with MarkupParser with MarkupHandler =>

  /** ...
   *
   *  @param systemId ...
   *  @return         ...
   */
  def externalSource(systemId: String): Source = {
    if (systemId startsWith "http:")
      return Source fromURL new URL(systemId)
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    val fileStr: String = input.descr match {
      case x if x startsWith "file:"  => x drop 5
      case x                          => x take ((x lastIndexOf separator) + 1)
    }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    Source.fromFile(fileStr + systemId)
  }
}
