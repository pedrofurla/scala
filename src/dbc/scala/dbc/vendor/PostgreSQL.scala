/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.dbc
package vendor;


import compat.Platform

@deprecated(DbcIsDeprecated, "2.9.0") abstract class PostgreSQL extends Vendor {
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def uri:java.net.URI;
  def user:String;
  def pass:String;

  val retainedConnections = 5;

  val nativeDriverClass = Platform.getClassForName("org.postgresql.Driver");

  val urlProtocolString = "jdbc:postgresql:"

}
