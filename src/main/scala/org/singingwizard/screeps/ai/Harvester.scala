package org.singingwizard.screeps.ai

import scalajs.js
import org.singingwizard.screeps.api._

class Harvester()(implicit ctx: ScreepsContext) extends Role {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  import LoDash._

  def run(creep: Creep): Boolean = {
    if (creep.carry.energy < creep.carryCapacity) {
      val sources = creep.room.find(FIND_SOURCES);
      if (creep.harvest(sources(0)) == ERR_NOT_IN_RANGE) {
        creep.moveTo(sources(0));
      }
      true
    } else {
      val targets = creep.room.find[Structure](FIND_STRUCTURES, jsObj(filter = (structure: js.Dynamic) => {
          (structure.structureType == STRUCTURE_EXTENSION ||
            structure.structureType == STRUCTURE_SPAWN ||
            structure.structureType == STRUCTURE_TOWER) && structure.asInstanceOf[js.Dynamic].energy.asInstanceOf[Int] < structure.asInstanceOf[js.Dynamic].energyCapacity.asInstanceOf[Int]
      }))
      Console.log(targets.toString())
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