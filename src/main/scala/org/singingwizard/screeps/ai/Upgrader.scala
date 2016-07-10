package org.singingwizard.screeps.ai

import scalajs.js
import org.singingwizard.screeps.api._
import scala.scalajs.js.UndefOr

class Upgrader()(implicit ctx: ScreepsContext) extends Role {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  import LoDash._

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
      var sources = creep.room.find(FIND_SOURCES);
      if (creep.harvest(sources(0)) == ERR_NOT_IN_RANGE) {
        creep.moveTo(sources(0))
      }
      true
    }
  }
}