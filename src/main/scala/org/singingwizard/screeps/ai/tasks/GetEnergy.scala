package org.singingwizard.screeps.ai.tasks

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.ai._
import org.singingwizard.screeps.api._
import ScreepsContext._
import prickle._
import org.singingwizard.screeps.wrappers.APIPickler

case class GetEnergy(val creep: Creep, val amount: Int = Int.MaxValue,
                     var _source: Option[Source] = None, var _assignedCreep: Option[Creep] = None,
                     var state: Task.State = Task.NeedCreep) extends Task {
  def findBestSource(p: RoomPosition): Source = {
    val nearSource = creep.pos.findInRange[Source](FIND_SOURCES, 3)
    nearSource.headOption match {
      case Some(s) => s
      case None =>
        //val rd = loop.roomData(creep.room)
        //val sources = rd.sources.filter(_.positions > 0).map(_.source.get()).filter(_.pos.findInRange(FIND_HOSTILE_CREEPS, 5).size == 0)
        val sources = creep.room.find[Source](FIND_SOURCES)
        if (sources.size > 0)
          creep.pos.findClosestByRangeFrom[Source](sources.toJSArray)
        else
          creep.pos.findClosestByRange[Source](FIND_SOURCES)
    }
  }

  def costFor(c: Creep)(implicit ctx: AIContext): Int = {
    import ctx._
    if (c != creep) {
      Int.MaxValue
    } else {
      val range = findBestSource(c.pos).pos.getRangeTo(c)
      val moveTicks = (range * 2 * c.body.count(_.typ != MOVE)) / c.body.count(_.typ == MOVE)
      val harvestTicks = (amount min c.carryCapacity) / 2 / c.body.count(_.typ == WORK)
      moveTicks + harvestTicks
    }
  }

  val idealCreep = None

  def assignCreep(c: Creep)(implicit ctx: AIContext): Unit = {
    import ctx._
    if (c != creep) {
      throw new IllegalArgumentException(s"Cannot act with $c")
    }
    state = Task.Running(None)
    _source = Some(findBestSource(c.pos))
  }

  def run()(implicit ctx: AIContext): Unit = {
    state match {
      case Task.Running(_) =>
        if (creep.carry.energy >= (amount min creep.carryCapacity)) {
          state = Task.Complete
        } else if (creep.harvest(_source.get) == ERR_NOT_IN_RANGE) {
          creep.moveTo(_source.get)
        }
      case _ => ()
    }
  }

  def assignedCreep = _assignedCreep

  override def toString(): String = {
    s"GetEnergy for $creep ($state)"
  }
}

/** @author amp
  */
object GetEnergy extends TaskCompanion {
  import APIPickler._

  def register(pickler: PicklerPair[Task]) = {
    pickler.concreteType[GetEnergy]
  }
}