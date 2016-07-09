package org.singingwizard.screeps.ai

import scala.scalajs.js
import org.singingwizard.screeps.api._

class Loop()(implicit val ctx: ScreepsContext) {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  
  def loop(): Unit = {
    PathFinder.use(true)
    
    Console.log("Loop... " + Game.time)
    Console.log(RoomPosition(2, 3, "test").toString())
    
    RoomPosition(5, 1, "E34R3") match {
      case RoomPosition(x, y, r) => Console.log((x, y, r).toString())
    }
    
    val room = Game.spawns("Spawn1").room
    Console.log(room.name)
        
    Console.log(PathFinder.search(RoomPosition(20, 20, room.name), Goal(RoomPosition(8, 8, room.name), 2)).path.toString())
        
    Game.spawns("Spawn1").createCreep(js.Array(WORK, CARRY, MOVE), null, js.Dynamic.literal(role = "harvester"))
  }
}
