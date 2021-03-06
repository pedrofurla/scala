/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc;


import java.sql.{Connection, Driver};


/** This class ..
 */
@deprecated(DbcIsDeprecated, "2.9.0") abstract class Vendor {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def nativeDriverClass: Class[_];
  def uri: java.net.URI;
  def user: String;
  def pass: String;
  def nativeProperties: java.util.Properties = {
    val properties = new java.util.Properties();
    properties.setProperty("user", user);
    properties.setProperty("password", pass);
    properties
  }

  def retainedConnections: Int;

  def getConnection: Connection = {
    val driver = nativeDriverClass.newInstance().asInstanceOf[Driver];
    driver.connect(uri.toString(),nativeProperties)
  }

  def urlProtocolString: String;

}
