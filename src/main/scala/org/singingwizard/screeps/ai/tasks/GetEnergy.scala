package org.singingwizard.screeps.ai.tasks

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.ai._
import org.singingwizard.screeps.api._
import ScreepsContext._
import prickle._
import org.singingwizard.screeps.wrappers.APIPickler
import org.singingwizard.prickle.WeakRef

case class GetEnergy(val creep: WeakRef[Creep], val amount: Int = Int.MaxValue,
                     var _source: Option[Source] = None,
                     var state: Task.State = Task.NeedCreep) extends Task {
  def findBestSource(p: RoomPosition): Source = {
    val c = creep.get
    val nearSource = c.pos.findInRange[Source](FIND_SOURCES, 3)
    nearSource.headOption match {
      case Some(s) => s
      case None =>
        //val rd = loop.roomData(creep.room)
        //val sources = rd.sources.filter(_.positions > 0).map(_.source.get()).filter(_.pos.findInRange(FIND_HOSTILE_CREEPS, 5).size == 0)
        val sources = creep.get.room.find[Source](FIND_SOURCES)
        if (sources.size > 0)
          c.pos.findClosestByRangeFrom[Source](sources.toJSArray)
        else
          c.pos.findClosestByRange[Source](FIND_SOURCES)
    }
  }

  def cost(implicit ctx: AIContext): Int = {
    val c = creep.get
    val range = findBestSource(c.pos).pos.getRangeTo(c)
    val moveTicks = (range * 2 * c.body.count(_.typ != MOVE)) / c.body.count(_.typ == MOVE)
    val harvestTicks = (amount min c.carryCapacity) / 2 / c.body.count(_.typ == WORK)
    moveTicks + harvestTicks
  }

  def run()(implicit ctx: AIContext): Unit = {
    try {
      val c = creep.get
      state match {
        case Task.NeedCreep =>
          if (ctx.claim(c)) {
            state = Task.Running()
            _source = Some(findBestSource(c.pos))
            run()
          }
        case Task.Running(_) =>
          if (c.carry.energy >= (amount min c.carryCapacity)) {
            ctx.unclaim(c)
            state = Task.Complete
          } else if (c.harvest(_source.get) == ERR_NOT_IN_RANGE) {
            c.moveTo(_source.get)
          }
        case _ => ()
      }
    } catch {
      case _: IllegalStateException =>
        state = Task.Failed("Creep disappeared")
    }
  }

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