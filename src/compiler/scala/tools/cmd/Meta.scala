/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools
package cmd

import nsc.io.File
import Interpolation._

/** Meta-options for command line tools.  We could have all kinds
 *  of additional goodness here, but for now it's completion and script
 *  generation.  See Demo for example usage.
 */
object Meta {
  trait Opt {
    def name: String
    def action: () => Unit
  }
<<<<<<< HEAD
  
  trait StdOpts {
    self: Spec with Interpolation =>
    
                                Bash.name   --> runAndExit(Bash.action())
    val selfUpdateName  = SelfUpdate.name   --| ;
    
    if (selfUpdateName.isDefined)
      runAndExit(SelfUpdate.action())
    
=======

  trait StdOpts {
    self: Spec with Interpolation =>

                                Bash.name   --> runAndExit(Bash.action())
    val selfUpdateName  = SelfUpdate.name   --| ;

    if (selfUpdateName.isDefined)
      runAndExit(SelfUpdate.action())

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    /** I think we're as close as we can get to bundling completion with
     *  the program given the constraints imposed by bash.  This outputs
     *  the completion function to a tempfile and echoes ". /path/to/file"
     *  to the console.  Place it inside backtickes like `partest --bash`
     *  and voila, you have absorbed command completion.
     */
    object Bash extends Opt {
      val name    = "bash"
      val action  = () => {
        val file = File.makeTemp("scala.cmd.bash")
        file writeAll interpolate(bashTemplate)

        // Would be nice to print something like this but comments are
        // not always comments in bash, and breaking it is worse.
        // Console println ("# Run the following line, or issue the --bash command in `backticks`.")
        Console println (". " + file.normalize.path)
      }
    }

    /** Generates a very basic runner script.  It's called SelfUpdate
     *  because once it exists you can do something like
     *
     *    tools/scmp --self-update tools/scmp
     *
<<<<<<< HEAD
     *  and it will overwrite itself with the current version.  
=======
     *  and it will overwrite itself with the current version.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    object SelfUpdate extends Opt {
      val name    = "self-update"
      val action  = () => {
        val file = File(selfUpdateName.get)
        file writeAll interpolate(runnerTemplate)
        file setExecutable true
        ()
      }
    }
  }
}
