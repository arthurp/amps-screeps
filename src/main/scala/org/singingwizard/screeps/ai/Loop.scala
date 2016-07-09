package org.singingwizard.screeps.ai

import scala.scalajs.js
import org.singingwizard.screeps.api._

class Loop()(implicit val ctx: ScreepsContext) {
  import ctx._
  
  def loop(): Unit = {
    Console.log("Loop... " + Game.time)
        
    Game.spawns("Spawn1").createCreep(js.Array(WORK, CARRY, MOVE), null, js.Dynamic.literal(role = "harvester"))
  }
}
