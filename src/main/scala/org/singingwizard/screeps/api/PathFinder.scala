package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait PathFinderResult extends js.Object {
  val path: js.Array[RoomPosition]
  val ops: Int
}

@JSExportAll
case class Goal(pos: RoomPosition, range: Int) {
}

@js.native
trait CostMatrix extends js.Object {
  def set(x: Int, y: Int, cost: Int): Unit
  def get(x: Int, y: Int): Int
  @JSName("clone")
  def copy(): CostMatrix
  def serialize(): js.Object
}

@js.native
trait PathFinderOps extends js.Object {
  var plainCost: Double = js.native
  var swampCost: Double = js.native
  var flee: Boolean = js.native
  var maxOps: Double = js.native
  var maxRooms: Double = js.native
  var heuristicWeight: Double = js.native
  def roomCallback(roomName: String): CostMatrix = js.native
}

@js.native
trait PathFinder extends js.Object {
  // TODO: Add opts optional arguments
  def search(origin: RoomPosition, goal: RoomPosition): PathFinderResult
  def search(origin: RoomPosition, goal: Goal): PathFinderResult
  // TODO: Add other overloads for goal

  /*
  def search(origin: RoomPosition, goal: RoomPosition | js.Any, opts: PathFinderOps = ???): js.Any = js.native
  def search(origin: RoomPosition, goal: js.Array[RoomPosition] | js.Array[js.Any], opts: PathFinderOps = ???): js.Any = js.native
	*/

  def use(isEnabled: Boolean): Unit
}

object PathFinder {
  implicit class PathFinderOps(val pf: PathFinder) {
    object CostMatrix {
      def apply(): CostMatrix = {
        js.Dynamic.newInstance(pf.asInstanceOf[js.Dynamic].CostMatrix)().asInstanceOf[CostMatrix]
      }
      def deserialize(o: js.Object): CostMatrix = {
        pf.asInstanceOf[js.Dynamic].CostMatrix.deserialize(o).asInstanceOf[CostMatrix]
      }
    }
  }
}