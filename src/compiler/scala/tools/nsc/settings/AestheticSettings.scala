/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Paul Phillips
 */

package scala.tools.nsc
package settings

/** Taking flag checking to a somewhat higher level. */
trait AestheticSettings {
  def settings: Settings
<<<<<<< HEAD
  
  // Some(value) if setting has been set by user, None otherwise.
  def optSetting[T](s: Settings#Setting): Option[T] =
    if (s.isDefault) None else Some(s.value.asInstanceOf[T])
  
  def script       = optSetting[String](settings.script)
  def encoding     = optSetting[String](settings.encoding)
  def sourceReader = optSetting[String](settings.sourceReader)
  
=======

  // Some(value) if setting has been set by user, None otherwise.
  def optSetting[T](s: Settings#Setting): Option[T] =
    if (s.isDefault) None else Some(s.value.asInstanceOf[T])

  def script       = optSetting[String](settings.script)
  def encoding     = optSetting[String](settings.encoding)
  def sourceReader = optSetting[String](settings.sourceReader)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def debug           = settings.debug.value
  def declsOnly       = false
  def deprecation     = settings.deprecation.value
  def experimental    = settings.Xexperimental.value
  def fatalWarnings   = settings.fatalWarnings.value
<<<<<<< HEAD
=======
  def future          = settings.future.value
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def logClasspath    = settings.Ylogcp.value
  def printStats      = settings.Ystatistics.value
  def richExes        = settings.YrichExes.value || sys.props.traceSourcePath.isSet
  def target          = settings.target.value
  def unchecked       = settings.unchecked.value
  def verbose         = settings.verbose.value
<<<<<<< HEAD
=======
  def virtPatmat      = settings.YvirtPatmat.value
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

  /** Derived values */
  def jvm           = target startsWith "jvm"
  def msil          = target == "msil"
  def verboseDebug  = debug && verbose
}
