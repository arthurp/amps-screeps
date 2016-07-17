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
                      var _spawn: Option[Spawn] = None,
                      var _creepName: Option[String] = None,
                      var state: Task.State = Task.NeedCreep) extends Task {
  def run()(implicit ctx: AIContext): Unit = {
    try {
      state match {
        case Task.NeedCreep =>
          val spawn = Game.spawns.values.find(_.room == room).getOrElse(throw new RuntimeException(s"No spawn in $room"))
          if (ctx.claim(spawn)) {
            state = Task.Running(0)
            _spawn = Some(spawn)
            run()
          }
        case Task.Running(0) =>
          val c = if (asLargeAsPossible) creep.scaledToCost(_spawn.get.energy) else creep
          (_spawn.get.createCreep(c.toCreepDefinition): Any) match {
            case n: String =>
              state = Task.Running(1)
              _creepName = Some(n)
            case _ =>
              ()
          }
        case Task.Running(1) =>
          if (_spawn.get.spawning == null) {
            ctx.unclaim(_spawn.get)
            state = Task.Complete
            ctx.available += WeakRef(Game.creeps(_creepName.get))
          }
        case _ => ()
      }
    } catch {
      case _: IllegalStateException =>
        state = Task.Failed("Creep disappeared")
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

  def register(pickler: PicklerPair[Task]) = {
    pickler.concreteType[SpawnCreep]
  }
}