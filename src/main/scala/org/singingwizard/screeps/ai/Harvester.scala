package org.singingwizard.screeps.ai

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.api._

class Harvester(val loop: Loop)(implicit val ctx: ScreepsContext) extends Role {
  import ctx._
  import ctxops._

  def run(creep: Creep): Boolean = {
    if (creep.carry.energy < creep.carryCapacity) {
      val source = selectSource(creep)
      if (creep.harvest(source) == ERR_NOT_IN_RANGE) {
        creep.moveTo(source)
      }
      true
    } else {
      val targets = creep.room.find[Structure](FIND_STRUCTURES, RoomFindOpts(structure => {
        (structure.structureType == STRUCTURE_EXTENSION ||
          structure.structureType == STRUCTURE_SPAWN ||
          structure.structureType == STRUCTURE_TOWER) &&
          (structure.asInstanceOf[js.Dynamic].energy < structure.asInstanceOf[js.Dynamic].energyCapacity).asInstanceOf[Boolean]
      }))
      if (targets.length > 0) {
        if (creep.transfer(targets(0), RESOURCE_ENERGY) == ERR_NOT_IN_RANGE) {
          creep.moveTo(targets(0));
        }
        true
      } else {
        false
      }
    }
  }
}