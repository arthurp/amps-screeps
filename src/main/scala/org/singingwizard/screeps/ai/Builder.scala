package org.singingwizard.screeps.ai

import scalajs.js
import org.singingwizard.screeps.api._
import scala.scalajs.js.UndefOr

class Builder()(implicit ctx: ScreepsContext) extends Role {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  import LoDash._

  def run(creep: Creep): Boolean = {
    if (creep.memory.building.asInstanceOf[UndefOr[Boolean]].getOrElse(false) && creep.carry.energy == 0) {
      creep.memory.building = false;
    } else if (!creep.memory.building.asInstanceOf[UndefOr[Boolean]].getOrElse(false) && creep.carry.energy == creep.carryCapacity) {
      creep.memory.building = true;
    }

    if (creep.memory.building.asInstanceOf[UndefOr[Boolean]].getOrElse(false)) {
      var sites = creep.room.find[ConstructionSite](FIND_CONSTRUCTION_SITES);
      if (sites.size > 0) {
        val roads = sites.filter(_.structureType == STRUCTURE_ROAD).sortBy(r => creep.pos.getRangeTo(r))
        val target = roads.headOption.getOrElse(sites(0))
        if (creep.build(target) == ERR_NOT_IN_RANGE) {
          creep.moveTo(target);
        }
        true
      } else {
        false
      }
    } else {
      var sources = creep.room.find(FIND_SOURCES);
      if (creep.harvest(sources(0)) == ERR_NOT_IN_RANGE) {
        creep.moveTo(sources(0));
      }
      true
    }
  }
}