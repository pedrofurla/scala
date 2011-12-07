/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author Paul Phillips
 */
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
package scala.tools.nsc
package interpreter

import xml.{ XML, Group, Node, NodeSeq }
import XMLCompletion._
import scala.collection.{ mutable, immutable }

<<<<<<< HEAD
class XMLCompletion(root: Node) extends CompletionAware {  
=======
class XMLCompletion(root: Node) extends CompletionAware {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private val nodeCache = new mutable.HashMap[String, Node]
  private def getNode(s: String): Option[Node] = {
    completions // make sure cache is populated
    nodeCache get s
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  lazy val completions: List[String] = {
    def children = root.child.toList
    def uniqueTags = children groupBy (_.label) filter (_._2.size == 1) map (_._1)
    val uniqs = uniqueTags.toList
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    children.foldLeft(List[String]())((res, node) => {
      val name = node.label
      def count = res filter (_ startsWith (name + "[")) size  // ]
      val suffix = if (uniqs contains name) "" else "[%d]" format (count + 1)
      val s = name + suffix
<<<<<<< HEAD
      
      nodeCache(s) = node
      
=======

      nodeCache(s) = node

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      s :: res
    }).sorted
  }
  def completions(verbosity: Int) = completions
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  override def execute(id: String)  = getNode(id)
  override def follow(id: String)   = getNode(id) map (x => new XMLCompletion(x))
}

object XMLCompletion {
  def apply(x: Node) = new XMLCompletion(x)
}
