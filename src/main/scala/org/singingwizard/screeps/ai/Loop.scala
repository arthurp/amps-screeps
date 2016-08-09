package org.singingwizard.screeps.ai

import scala.scalajs.js
import scala.scalajs.js.{ UndefOr }
import scala.scalajs.js.JSConverters._
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
import be.doeraene.sjsreflect.Reflect

class Loop() {
  import ScreepsContext._

  implicit val PrickleConfig = NativeJsConfig()

  // Caching doesn't seem to work because for some reason the state gets messed up but not cleared when calling loop.
  //var contextCache: AIContext = null

  def loop(): Unit = {
    PathFinder.use(true)

    {
      val cls = Reflect.getClassForName("org.singingwizard.screeps.ai.tasks.GetEnergy")
      println(cls)
      val cstrs = Reflect.getDeclaredConstructors(cls.get)
      println(cstrs)
    }

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

    if (runnableTasks.isEmpty) {
      available.clear()
      for ((_, c) <- Game.creeps) {
        available += WeakRef(c)
      }
      for ((_, c) <- Game.structures if c.structureType != STRUCTURE_ROAD) {
        available += WeakRef(c)
      }
    }

    if (runnableTasks.isEmpty) {
      val kind = CreepBuildVector(move = 1, carry = 1, work = 1.1)
      schedule(new SpawnCreep(Game.rooms.values.head, kind)) 
      //schedule(new SpawnCreep(Game.rooms.values.head, kind.scaledToCost(300))) 
    }

    val ts = runnableTasks.toJSArray
    runnableTasks.clear()
    for (t <- ts) {
      try {
        t.run()
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
