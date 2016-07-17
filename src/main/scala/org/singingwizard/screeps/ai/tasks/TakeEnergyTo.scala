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
                        val k: Option[Task] = None,
                        var creep: Option[WeakRef[Creep]] = None,
                        var state: Int = TakeEnergyTo.NEED_CREEP) extends TaskWithContinuation {
  import TakeEnergyTo._

  def continuation: TraversableOnce[Task] = k

  def run()(implicit ctx: AIContext): Unit = {
    import ctx._

    try {
      val t = target.get
      state match {
        case NEED_CREEP =>
          val creeps = t.room.find[Creep](FIND_CREEPS).filter(c => c.carry.energy >= c.carryCapacity || c.carry.energy >= amount)
          val c = if (!creeps.isEmpty) t.pos.findClosestByRangeFrom[Creep](creeps) else null
          Console.log(s"Checking full creeps: $creeps")
          if (!creeps.isEmpty && ctx.claim(c)) {
            creep = Some(c)
            state = RUNNING
            run()
          } else {
            // TODO: Make it prefer creeps better for this job: && (c.carryCapacity >= amount || amount == Int.MaxValue)
            val cs = t.room.find[Creep](FIND_CREEPS).filter(c => isAvailable(c))
            Console.log(s"Checking other creeps: $cs")
            cs.headOption match {
              case Some(c) =>
                schedule(new GetEnergy(c, amount, k = Some(this)))
              case None =>
                // TODO: Make this block on a new creep becoming available.
                reschedule()
            }
          }
        case RUNNING =>
          val c = creep.get.get
          val n = c.carry.energy min amount
          if (c.transfer(t, RESOURCE_ENERGY, n) == ERR_NOT_IN_RANGE) {
            c.moveTo(t)
            reschedule()
          } else {
            amount -= n
            ctx.unclaim(c)
            if (amount <= 0) {
              state = COMPLETE
            } else {
              state = NEED_CREEP
              creep = None
              reschedule()
            }
          }
        case _ => ()
      }
    } catch {
      case _: IllegalStateException =>
        fail("Creep disappeared")
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
  import Task._

  val NEED_CREEP = 0
  val RUNNING = 1
  val COMPLETE = 2

  def register(pickler: PicklerPair[Task]) = {
    pickler.concreteType[TakeEnergyTo]
  }
}