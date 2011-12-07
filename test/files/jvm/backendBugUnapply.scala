object Test { 
<<<<<<< HEAD
  import scala.xml.{Node,HasKeyValue}
  
  def domatch(x:Node): Node = {
    val hasBar = new HasKeyValue("bar")
    
    x match {
      case Node("foo", hasBar(z), _*) => z
      case _ => null
    }
  }
=======
  import scala.xml.{Node,UnprefixedAttribute}
  
  def domatch(x:Node) =
    x match {
      case Node("foo", UnprefixedAttribute("bar", z, _), _*) => z
      case _ => null
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  
  def main(args: Array[String]): Unit = {
    println(domatch(<foo bar="baz"><hi/></foo>))
    println(domatch(<foo bingo="donkey"><hi/></foo>))
    // 
    // assert(domatch(<foo bar="baz"><hi/></foo>).toString == "baz")
    // assert(domatch(<foo bar="baz2"><hi/></foo>) == null)//, domatch(<foo bar="baz2"><hi/></foo>))
  }
}
