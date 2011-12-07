/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */
// $Id$

package scala.reflect.internal
package settings

/** A mutable Settings object.
 */
abstract class MutableSettings extends AbsSettings {
<<<<<<< HEAD
  
  type Setting <: SettingValue

  // basically this is a value which remembers if it's been modified
  trait SettingValue extends AbsSettingValue {
    protected var v: T = _
    protected var setByUser: Boolean = false

    def postSetHook(): Unit = ()
    def isDefault: Boolean = !setByUser
=======

  type Setting <: SettingValue
  type BooleanSetting <: Setting { type T = Boolean }
  type IntSetting <: Setting { type T = Int }

  // basically this is a value which remembers if it's been modified
  trait SettingValue extends AbsSettingValue {
    protected var v: T
    protected var setByUser: Boolean = false

    def postSetHook(): Unit = ()
    def isDefault = !setByUser
    def isSetByUser = setByUser
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def value: T = v
    def value_=(arg: T) = {
      setByUser = true
      v = arg
      postSetHook()
    }
  }

<<<<<<< HEAD
  def printtypes: SettingValue { type T = Boolean }
  def debug: SettingValue { type T = Boolean }
  def YdepMethTpes: SettingValue { type T = Boolean }
  def Ynotnull: SettingValue { type T = Boolean }
  def explaintypes: SettingValue { type T = Boolean }
  def verbose: SettingValue { type T = Boolean }
  def uniqid: SettingValue { type T = Boolean }
  def Xprintpos: SettingValue { type T = Boolean }
  def Yrecursion: SettingValue { type T = Int }
  def maxClassfileName: SettingValue { type T = Int }
=======
  def overrideObjects: BooleanSetting
  def printtypes: BooleanSetting
  def debug: BooleanSetting
  def Ynotnull: BooleanSetting
  def explaintypes: BooleanSetting
  def verbose: BooleanSetting
  def uniqid: BooleanSetting
  def Xprintpos: BooleanSetting
  def Yrecursion: IntSetting
  def maxClassfileName: IntSetting
  def Xexperimental: BooleanSetting
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}