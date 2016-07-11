package org.singingwizard.screeps.ai

import scalajs.js
import org.singingwizard.screeps.api._
import scala.scalajs.js.UndefOr

class Upgrader(val loop: Loop)(implicit val ctx: ScreepsContext) extends Role {
  import ctx._
  import ctxops._

  def run(creep: Creep): Boolean = {
    if (creep.memory.upgrading.asInstanceOf[UndefOr[Boolean]].getOrElse(false) && creep.carry.energy == 0) {
      creep.memory.upgrading = false
    } else if (!creep.memory.upgrading.asInstanceOf[UndefOr[Boolean]].getOrElse(false) && creep.carry.energy == creep.carryCapacity) {
      creep.memory.upgrading = true
    }

    if (creep.memory.upgrading.asInstanceOf[UndefOr[Boolean]].getOrElse(false)) {
      if (creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE) {
        creep.moveTo(creep.room.controller)
      }
      true
    } else {
      val source = selectSource(creep)
      if (creep.harvest(source) == ERR_NOT_IN_RANGE) {
        creep.moveTo(source)
      }
      true
    }
  }
}