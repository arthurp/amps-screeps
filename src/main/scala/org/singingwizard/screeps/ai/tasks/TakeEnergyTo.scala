package org.singingwizard.screeps.ai.tasks

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.ai._
import org.singingwizard.screeps.api._
import ScreepsContext._
import prickle._
import org.singingwizard.screeps.wrappers.APIPickler
import org.singingwizard.prickle.WeakRef

case class TakeEnergyTo(val target: WeakRef[Structure], var amount: Int = Int.MaxValue,
                        var creep: Option[WeakRef[Creep]] = None,
                        var state: Task.State = Task.NeedCreep) extends Task {
  def run()(implicit ctx: AIContext): Unit = {
    try {
      val t = target.get
      state match {
        case Task.NeedCreep =>
          val creeps = t.room.find[Creep](FIND_CREEPS).filter(c => c.carry.energy >= c.carryCapacity || c.carry.energy >= amount)
          val c = t.pos.findClosestByRangeFrom[Creep](creeps)
          if (ctx.claim(c)) {
            creep = Some(c)
            state = Task.Running()
            run()
          }
        case Task.Running(_) =>
          val c = creep.get.get
          val n = c.carry.energy min amount
          if (c.transfer(t, RESOURCE_ENERGY, n) == ERR_NOT_IN_RANGE) {
            c.moveTo(t)
          } else {
            amount -= n
            ctx.unclaim(c)
            if (amount <= 0) {
              state = Task.Complete
            } else {
              state = Task.NeedCreep
              creep = None
            }
          }
        case _ => ()
      }
    } catch {
      case _: IllegalStateException =>
        state = Task.Failed("Creep disappeared")
    }
  }

  override def toString(): String = {
    s"TakeEnergyTo for $target ($state, $creep)"
  }
}

/** @author amp
  */
object TakeEnergyTo extends TaskCompanion {
  import APIPickler._

  def register(pickler: PicklerPair[Task]) = {
    pickler.concreteType[TakeEnergyTo]
  }
}