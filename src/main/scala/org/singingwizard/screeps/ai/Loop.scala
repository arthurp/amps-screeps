package org.singingwizard.screeps.ai

import scala.scalajs.js
import org.singingwizard.screeps.api._

class Loop(val game: Game, val console : Console, val constants: Constants) {
  import constants._
  
  def loop(): Unit = {
    console.log("Loop... " + game.time)
    game.spawns("Spawn1").createCreep(js.Array(WORK, CARRY, MOVE), null, js.Dynamic.literal(role = "harvester"))
  }
}
