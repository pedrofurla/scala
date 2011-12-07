package scala.reflect
package runtime

/** The Settings class for runtime reflection.
<<<<<<< HEAD
 *  This should be refined, so that settings are settable via command 
 *  line options or properties.
 */
class Settings extends internal.settings.MutableSettings {
  
  class Setting extends SettingValue
  
  class BooleanSetting(x: Boolean) extends Setting {
    type T = Boolean
    v = x
  }
  
  class IntSetting(x: Int) extends Setting {
    type T = Int
    v = x
  }

  val debug = new BooleanSetting(false)
  val YdepMethTpes = new BooleanSetting(false)
=======
 *  This should be refined, so that settings are settable via command
 *  line options or properties.
 */
class Settings extends internal.settings.MutableSettings {

  trait Setting extends SettingValue { }

  class BooleanSetting(x: Boolean) extends Setting {
    type T = Boolean
    protected var v: Boolean = x
    override def value: Boolean = v
  }

  class IntSetting(x: Int) extends Setting {
    type T = Int
    protected var v: Int = x
    override def value: Int = v
  }

  val overrideObjects = new BooleanSetting(false)
  val debug = new BooleanSetting(false)
  // val YdepMethTpes = new BooleanSetting(true)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  val Ynotnull = new BooleanSetting(false)
  val explaintypes = new BooleanSetting(false)
  val verbose = new BooleanSetting(false)
  val uniqid = new BooleanSetting(false)
  val Xprintpos = new BooleanSetting(false)
  val printtypes = new BooleanSetting(false)
  val Yrecursion = new IntSetting(0)
  val maxClassfileName = new IntSetting(255)
<<<<<<< HEAD
}
=======
  val Xexperimental = new BooleanSetting(false)
}
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
