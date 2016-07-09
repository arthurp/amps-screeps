package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._
import js.|

/*
@js.native
trait RoomObject extends js.Object {
  def pos: RoomPosition
  def room: String
}

@js.native
trait Spawn extends RoomObject {
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
*/


@js.native
trait RoomPosition extends js.Object {
  val x: Double
  val y: Double
  val roomName: String

  def createConstructionSite(structureType: String): Double
  def createFlag(name: String = ???, color: Double = ???, secondaryColor: Double = ???): Double
  def findClosestByPath[T](typ: Double, opts: js.Any = ???): T
  //def findClosestByPath[T](objects: js.Array[T] | js.Array[RoomPosition], opts: js.Any = ???): T
  def findClosestByRange[T](typ: Double, opts: js.Any = ???): T
  //def findClosestByRange[T](objects: js.Array[T] | js.Array[RoomPosition], opts: js.Any = ???): T
  def findInRange[T](typ: Double, range: Double, opts: js.Any = ???): js.Array[T]
  //def findInRange[T](objects: js.Array[T] | js.Array[RoomPosition], range: Double, opts: js.Any = ???): js.Array[T]
  def findPathTo(x: Double, y: Double, opts: FindPathOpts = ???): js.Array[PathStep]
  //def findPathTo(target: RoomPosition | js.Any, opts: FindPathOpts = ???): js.Array[PathStep]
  def getDirectionTo(x: Double, y: Double): Double
  def getDirectionTo(target: RoomPosition | js.Any): Double
  def getRangeTo(x: Double, y: Double): Double
  def getRangeTo(target: RoomPosition | js.Any): Double
  def inRangeTo(toPos: RoomPosition, range: Double): Boolean
  def isEqualTo(x: Double, y: Double): Boolean
  def isEqualTo(target: RoomPosition | js.Any): Boolean
  def isNearTo(x: Double, y: Double): Boolean
  def isNearTo(target: RoomPosition | js.Any): Boolean
  def look(): js.Array[LookAtResult]
  def lookFor[T](typ: String): js.Array[T]
}

