package org.singingwizard.screeps.ai

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.api._
/*
class Harvester(val loop: Loop)(implicit val ctx: ScreepsContext) extends Role {
  import ctx._
  import ctxops._

  def run(creep: Creep): Boolean = {
    if (creep.carry.energy < creep.carryCapacity) {
      selectSource(creep) match {
        case Some(source) =>
          if (creep.harvest(source) == ERR_NOT_IN_RANGE) {
            creep.moveTo(source)
          }
          true
        case None =>
          false
      }
    } else {
      val targets = creep.room.find[Structure](FIND_STRUCTURES, FindFilter(structure => {
        (structure.structureType == STRUCTURE_EXTENSION ||
          structure.structureType == STRUCTURE_SPAWN ||
          structure.structureType == STRUCTURE_TOWER) &&
          (structure.asInstanceOf[js.Dynamic].energy < structure.asInstanceOf[js.Dynamic].energyCapacity).asInstanceOf[Boolean]
      }))
      
      if (targets.length > 0) {
        val target = creep.pos.findClosestByRangeFrom[Structure](targets.toJSArray)
        if (creep.transfer(target, RESOURCE_ENERGY) == ERR_NOT_IN_RANGE) {
          creep.moveTo(target)
        }
        true
      } else {
        false
      }
    }
  }
}
*/