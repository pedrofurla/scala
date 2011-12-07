@ImplementedBy(classOf[Provider])
trait Service {
  def someMethod()
}

class Provider
    extends Service
{
  // test enumeration java annotations
  @Ann(Days.Friday) def someMethod() = ()

  // #2103
<<<<<<< HEAD
  @scala.reflect.BeanProperty
=======
  @scala.beans.BeanProperty
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  @Ann(value = Days.Sunday)
  val t2103 = "test"
}
