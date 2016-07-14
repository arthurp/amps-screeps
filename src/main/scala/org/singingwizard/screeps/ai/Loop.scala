package org.singingwizard.screeps.ai

import scala.scalajs.js
import scala.scalajs.js.{ UndefOr }
import org.singingwizard.screeps.api._
import org.singingwizard.screeps.wrappers._
import org.singingwizard.screeps.wrappers.APIPickler._
import scala.collection.mutable
import org.singingwizard.screeps.ai.tasks.GetEnergy
import prickle._
import org.singingwizard.prickle.WeakRef
import org.singingwizard.prickle.NativeJsConfig
import scala.util.{ Try, Failure, Success }
import AIContext._

class Loop() {
  implicit val PrickleConfig = NativeJsConfig()

  import ScreepsContext._

  implicit val taskPickler = Task.pickler(GetEnergy) 

  def loop(): Unit = {
    PathFinder.use(true)

    implicit val ctx: AIContext = try {
      Unpickle[AIContext].from(Memory.AIContext.asInstanceOf[js.Any]).get
    } catch {
      case e: Exception => {
        Console.log(s"Error while loading AIContext: $e\nBuilding new empty instance.")
        AIContext()
      }
    }

    import ctx._

    if (tasks.isEmpty) {
      Console.log("Adding new tasks")
      for ((n, c) <- Game.creeps) {
        tasks += new GetEnergy(c)
      }
    }

    val idleCreeps = Game.creeps.values.toSet -- tasks.flatMap(_.assignedCreep)

    for (t <- tasks) {
      Console.log(s"Running task: $t")
      try {
        t.state match {
          case Task.NeedCreep =>
            val c = idleCreeps.minBy(t.costFor(_))
            t.assignCreep(c)
          case Task.Running(_) =>
            t.run()
          case s =>
            Console.log(s"Finished task $t (State: $s)")
            tasks -= t
        }
      } catch {
        case e: Exception =>
          Console.log(s"Failed to run task $t: $e")
      }
    }

    Memory.AIContext = Pickle(ctx)
  }

  /*
    roomDataCache.clear()

    val spawn = Game.spawns("Spawn1")

    spawn.room.find[StructureTower](FIND_STRUCTURES).filter(_.structureType == STRUCTURE_TOWER).headOption match {
      case Some(tower) =>
        val closestHostile = tower.pos.findClosestByRange[Creep](FIND_HOSTILE_CREEPS);
        if (closestHostile != null) {
          tower.attack(closestHostile)
        } else {
          val closestDamagedStructure = tower.pos.findClosestByRange[Structure](FIND_STRUCTURES,
            FindFilter[Structure](structure => {
              structure.hits.getOrElse(Int.MaxValue) < structure.hitsMax.getOrElse(Int.MaxValue)
            }))
          if (closestDamagedStructure != null) {
            tower.repair(closestDamagedStructure);
          }
        }
      case _ => ()
    }
   */
}
