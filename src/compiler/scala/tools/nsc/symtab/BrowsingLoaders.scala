/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.tools.nsc
package symtab
<<<<<<< HEAD
 
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
import scala.tools.nsc.util.BatchSourceFile
import scala.tools.nsc.io.AbstractFile

/** A subclass of SymbolLoaders that implements browsing behavior.
 *  This class should be used whenever file dependencies and recompile sets
 *  are managed automatically.
 */
abstract class BrowsingLoaders extends SymbolLoaders {
  import global._

  import syntaxAnalyzer.{OutlineParser, MalformedInput}

  /** In browse mode, it can happen that an encountered symbol is already
   *  present. For instance, if the source file has a name different from
   *  the classes and objects it contains, the symbol loader will always
   *  reparse the source file. The symbols it encounters might already be loaded
   *  as class files. In this case we return the one which has a sourcefile
   *  (and the other has not), and issue an error if both have sourcefiles.
   */
  override protected def enterIfNew(owner: Symbol, member: Symbol, completer: SymbolLoader): Symbol = {
    completer.sourcefile match {
<<<<<<< HEAD
      case Some(src) => 
=======
      case Some(src) =>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        (if (member.isModule) member.moduleClass else member).sourceFile = src
      case _ =>
    }
    val decls = owner.info.decls
    val existing = decls.lookup(member.name)
    if (existing == NoSymbol) {
      decls enter member
      member
    } else if (existing.sourceFile == null) {
      decls unlink existing
      decls enter member
      member
    } else {
      if (member.sourceFile != null) {
<<<<<<< HEAD
        if (existing.sourceFile != member.sourceFile) 
=======
        if (existing.sourceFile != member.sourceFile)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
          error(member+"is defined twice,"+
                "\n in "+existing.sourceFile+
                "\n and also in "+member.sourceFile)
      }
      existing
    }
  }

  /** Browse the top-level of given abstract file `src` and enter
   *  eny encountered top-level classes and modules in `root`
   */
<<<<<<< HEAD
  def browseTopLevel(root: Symbol, src: AbstractFile) {  
=======
  def browseTopLevel(root: Symbol, src: AbstractFile) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    class BrowserTraverser extends Traverser {
      var packagePrefix = ""
      var entered = 0
      def addPackagePrefix(pkg: Tree): Unit = pkg match {
        case Select(pre, name) =>
          addPackagePrefix(pre)
          packagePrefix += ("." + name)
        case Ident(name) =>
          if (packagePrefix.length != 0) packagePrefix += "."
          packagePrefix += name
        case _ =>
          throw new MalformedInput(pkg.pos.point, "illegal tree node in package prefix: "+pkg)
      }
      override def traverse(tree: Tree): Unit = tree match {
        case PackageDef(pkg, body) =>
          addPackagePrefix(pkg)
          body foreach traverse
        case ClassDef(_, name, _, _) =>
          if (packagePrefix == root.fullName) {
            enterClass(root, name.toString, new SourcefileLoader(src))
            entered += 1
          } else println("prefixes differ: "+packagePrefix+","+root.fullName)
        case ModuleDef(_, name, _) =>
          if (packagePrefix == root.fullName) {
            val module = enterModule(root, name.toString, new SourcefileLoader(src))
            entered += 1
            if (name == nme.PACKAGEkw) {
              println("open package module: "+module)
<<<<<<< HEAD
              loaders.openPackageModule(module)()
=======
              openPackageModule(module, root)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            }
          } else println("prefixes differ: "+packagePrefix+","+root.fullName)
        case _ =>
      }
    }

//    System.out.println("Browsing "+src)
    val source = getSourceFile(src) // this uses the current encoding
    val body = new OutlineParser(source).parse()
//    System.out.println(body)
    val browser = new BrowserTraverser
    browser.traverse(body)
<<<<<<< HEAD
    if (browser.entered == 0) 
=======
    if (browser.entered == 0)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      warning("No classes or objects found in "+source+" that go in "+root)
  }

  /** Enter top-level symbols from a source file
   */
  override def enterToplevelsFromSource(root: Symbol, name: String, src: AbstractFile) {
    try {
      if (root == definitions.RootClass || root == definitions.EmptyPackageClass)
        super.enterToplevelsFromSource(root, name, src)
<<<<<<< HEAD
      else 
=======
      else
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        browseTopLevel(root, src)
    } catch {
      case ex: syntaxAnalyzer.MalformedInput =>
        println("[%s] caught malformed input exception at offset %d: %s".format(src, ex.offset, ex.msg))
        super.enterToplevelsFromSource(root, name, src)
    }
  }
}
