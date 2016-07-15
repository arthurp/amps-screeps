package org.singingwizard.screeps.ai

import scala.scalajs.js
import scala.scalajs.js.{ UndefOr }
import org.singingwizard.screeps.api._
import org.singingwizard.screeps.wrappers._
import org.singingwizard.screeps.wrappers.APIPickler._
import scala.collection.mutable
import prickle._
import org.singingwizard.prickle.WeakRef
import org.singingwizard.prickle.NativeJsConfig
import scala.util.{ Try, Failure, Success }
import AIContext._
import org.singingwizard.screeps.ai.tasks._

class Loop() {
  import ScreepsContext._

  implicit val PrickleConfig = NativeJsConfig()

  implicit val taskPickler = Task.pickler(GetEnergy, SpawnCreep, TakeEnergyTo)

  // Caching doesn't seem to work because for some reason the state gets messed up but not cleared when calling loop.
  //var contextCache: AIContext = null

  def loop(): Unit = {
    PathFinder.use(true)

    // Check the cache before loading to see if we still have our object state.
    implicit val ctx: AIContext = try {
      //if(contextCache == null) {
      Console.log("Loading from pickle in 'Memory'.")
      Unpickle[AIContext].from(Memory.AIContext.asInstanceOf[js.Any]).get
      /*} else { 
        Console.log("Loading from cache in this.")
        contextCache
      }*/
    } catch {
      case e: Exception => {
        Console.log(s"Error while loading AIContext: $e\nBuilding new empty instance.")
        AIContext()
      }
    }

    import ctx._

    if (tasks.isEmpty) {
      available.clear()
      for ((_, c) <- Game.creeps) {
        available += WeakRef(c)
      }
      for ((_, c) <- Game.structures if c.structureType != STRUCTURE_ROAD) {
        available += WeakRef(c)
      }
    }

    for (WeakRef(Commandable.Creep(c)) <- available if c.carry.energy < c.carryCapacity) {
      tasks += new GetEnergy(c)
    }
    for (s <- Game.spawns.values if s.energy < s.energyCapacity 
        if tasks.find({ 
          case TakeEnergyTo(`s`, _, _, _) => true
          case _ => false
        }).isEmpty) {
      tasks += new TakeEnergyTo(s, s.energyCapacity - s.energy)
    }
    
    if (Game.creeps.size + tasks.count(_.isInstanceOf[SpawnCreep]) < 5) {
      val kind = CreepBuildVector(move = 1, carry = 1, work = 1.1)
      tasks += new SpawnCreep(Game.rooms.values.head, kind) 
    }

    for (t <- tasks) {
      try {
        t.state match {
          case s @ (Task.Complete | Task.Failed(_)) =>
            Console.log(s"WARNING: Encountered finished task in first check: $t")
          case _ =>
            Console.log(s"Running task: $t")
            t.run()
        }
        t.state match {
          case s @ (Task.Complete | Task.Failed(_)) =>
            Console.log(s"Finished task $t (State: $s)")
            tasks -= t
          case _ =>
            ()
        }
      } catch {
        case e: Exception =>
          Console.log(s"Failed to run task $t: $e")
      }
    }

    // Store back into cache.
    //contextCache = ctx

    // Always pickle since we don't know if we will have our object state when we come back.
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
