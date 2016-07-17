package org.singingwizard.screeps.ai.tasks

import scalajs.js
import scalajs.js.JSConverters._
import org.singingwizard.screeps.ai._
import org.singingwizard.screeps.api._
import ScreepsContext._
import prickle._
import org.singingwizard.screeps.wrappers.APIPickler
import org.singingwizard.prickle.WeakRef

case class SpawnCreep(val room: Room, val creep: CreepBuildVector,
                      val asLargeAsPossible: Boolean = false,
                      val k: Option[Task] = None,
                      var _spawn: Option[Spawn] = None,
                      var _creepName: Option[String] = None,
                      var state: Int = SpawnCreep.NEED_CREEP) extends Task {
  import SpawnCreep._

  def continuation: TraversableOnce[Task] = k

  def run()(implicit ctx: AIContext): Unit = {
    import ctx._
    state match {
      case NEED_CREEP =>
        val spawn = Game.spawns.values.find(_.room == room).getOrElse(throw new RuntimeException(s"No spawn in $room"))
        if (ctx.claim(spawn)) {
          state = CREATE_CREEP
          _spawn = Some(spawn)
          run()
        }
      case CREATE_CREEP =>
        val c = if (asLargeAsPossible) creep.scaledToCost(_spawn.get.energy) else creep
        val Err = ERR_NOT_ENOUGH_ENERGY
        Console.log(s"Creating creep with body: $c, $creep, ${c.toCreepDefinition}")
        (_spawn.get.createCreep(c.toCreepDefinition): Any) match {
          case n: String =>
            state = WAIT
            _creepName = Some(n)
            reschedule()
          case Err =>
            val energy = (creep.cost - _spawn.get.energy) / 3 + 1
            schedule(new TakeEnergyTo(_spawn.get, energy, k = Some(this)))
            schedule(new TakeEnergyTo(_spawn.get, energy, k = Some(this)))
            schedule(new TakeEnergyTo(_spawn.get, energy, k = Some(this)))
          case e: Int =>
            Console.log(errcodeToString(e))
            reschedule()
        }
      case WAIT =>
        if (_spawn.get.spawning == null) {
          ctx.unclaim(_spawn.get)
          state = COMPLETE
          ctx.available += WeakRef(Game.creeps(_creepName.get))
        } else {
          reschedule()
        }
      case _ => ()
    }
  }

  override def toString(): String = {
    s"SpawnCreep for $room ($state)"
  }
}

/** @author amp
  */
object SpawnCreep extends TaskCompanion {
  import APIPickler._
  import Task._

  val NEED_CREEP = 0
  val RUNNING = 1
  val CREATE_CREEP = 2
  val WAIT = 3
  val COMPLETE = 4

  def register(pickler: PicklerPair[Task]) = {
    pickler.concreteType[SpawnCreep]
  }
}