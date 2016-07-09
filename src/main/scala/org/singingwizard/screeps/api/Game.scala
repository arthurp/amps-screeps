package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait Spawn extends js.Object {
  val name: String
  def createCreep(body: js.Array[String], name: String = null, memory: js.Object = null): js.Any
}

@js.native
trait NamedObjects[T] extends js.Object {
  @JSBracketAccess
  def apply(index: String): T = js.native
}

@js.native
trait Game extends js.Object {
  def time: Int
  
  val spawns: NamedObjects[Spawn]
  
  val map: Map
  val market: Market
}

@js.native
trait Map extends js.Object {
}

@js.native
trait Market extends js.Object {
}

@js.native
class RoomPosition(val x: Int, val y: Int, val roomName: String) extends js.Object