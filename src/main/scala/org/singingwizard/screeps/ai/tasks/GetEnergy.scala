package org.singingwizard.screeps.ai.tasks

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.ai._
import org.singingwizard.screeps.api._

class GetEnergy(val creep: Creep, val amount: Int = Int.MaxValue) extends Task {
  def findBestSource(p: RoomPosition)(implicit ctx: ScreepsContext): Source = {
    import ctx._
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

  def costFor(c: Creep)(implicit ctx: ScreepsContext): Int = {
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

  def performWith(c: Creep)(implicit ctx: ScreepsContext): RunningTask = {
    import ctx._
    val source = findBestSource(c.pos)
    new RunningTask {
      var done = false
      
      val creep: Creep = c
      def run(): Unit = {
        if(!done) {
          if (creep.carry.energy >= (amount min c.carryCapacity)) {
            done = true
          } else if (creep.harvest(source) == ERR_NOT_IN_RANGE) {
            creep.moveTo(source)
          }
        }
      }
      def state(implicit ctx: ScreepsContext): RunningTask.State = 
        if (done) RunningTask.Complete else RunningTask.Running(None)
      val task: Task = GetEnergy.this
    }
  }
  
  override def toString(): String = {
    s"GetEnergy for $creep"
  }
}