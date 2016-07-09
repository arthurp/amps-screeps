package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait PathFinderResult extends js.Object {
  val path: js.Array[RoomPosition]
  val ops: Int
}


@js.native
trait PathFinder extends js.Object {
  // TODO: Add opts optional arguments
  def search(origin: RoomPosition, goal: RoomPosition): PathFinderResult
  // TODO: Add other overloads for goal
}