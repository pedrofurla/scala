trait Complex extends Product2[Double, Double] {
  def canEqual(other: Any) = other.isInstanceOf[Complex]
}

class ComplexRect(val _1: Double, val _2: Double) extends Complex {
  override def toString = "ComplexRect("+_1+","+_2+")"
}

class ComplexPolar(val _1: Double, val _2: Double) extends Complex {
  override def toString = "ComplexPolar("+_1+","+_2+")"
}

object ComplexRect {
  def unapply(z:Complex): Option[Complex] = {
    if(z.isInstanceOf[ComplexRect]) Some(z) else z match {
      case ComplexPolar(mod, arg) =>
<<<<<<< HEAD
	Some(new ComplexRect(mod*Math.cos(arg), mod*Math.sin(arg)))
=======
	Some(new ComplexRect(mod*math.cos(arg), mod*math.sin(arg)))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
} } }

object ComplexPolar {
  def unapply(z:Complex): Option[Complex] = {
    if(z.isInstanceOf[ComplexPolar]) Some(z) else z match {
      case ComplexRect(re,im) =>
<<<<<<< HEAD
	Some(new ComplexPolar(Math.sqrt(re*re + im*im), Math.atan(re/im)))
=======
	Some(new ComplexPolar(math.sqrt(re*re + im*im), math.atan(re/im)))
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
} } }

object Test {
  def main(args:Array[String]) = {
    new ComplexRect(1,1) match {
      case ComplexPolar(mod,arg) => // z @ ???
	Console.println("mod"+mod+"arg"+arg)
    }
    val Komplex = ComplexRect
<<<<<<< HEAD
    new ComplexPolar(Math.sqrt(2),Math.Pi / 4.0) match {
=======
    new ComplexPolar(math.sqrt(2),math.Pi / 4.0) match {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      case Komplex(re,im) => // z @ ???
	Console.println("re"+re+" im"+im)
    }
  }
}
