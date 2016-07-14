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

class Loop() {
  implicit val PrickleConfig = NativeJsConfig()

  import ScreepsContext._

  case class SourceData(source: Source, positions: Int)
 
  def loop(): Unit = {
    PathFinder.use(true)
    
    implicit val ctx: AIContext = try {
      Unpickle[AIContext].from(Memory.AIContext)
    } catch {
      case e: Exception => {
        Console.log(s"Error while loading AIContext: $e\nBuilding new empty instance.")
        AIContext()
      }
    }

    /*
    val room = Game.spawns.values.head.room
    val source = room.find[Source](FIND_SOURCES).head
    val creep = Game.creeps.values.headOption

    Memory.test.asInstanceOf[UndefOr[js.Any]].toOption match {
      case Some(s) =>
        Console.log(s)
        val v = Unpickle[(Seq[String], SourceData, WeakRef[Creep])].from(s)
        Console.log(v)
      case _ =>
        Console.log("Empty")
    }

    if (Game.creeps.size == 1) {
      creep match {
        case Some(creep) =>
          Memory.test = Pickle((Set("String", "Other"), SourceData(source, 2), creep))
        case None => ()
      }
      Console.log(Memory.test)
    }
    */

    
    /*
    var tasks = Queue[Task]()
    var runningTasks = Set[RunningTask]()
    var idleCreeps = Set[Creep]()

    if (tasks.isEmpty && runningTasks.isEmpty) {
      Console.log("Adding new tasks")
      idleCreeps = Set()
      for ((n, c) <- Game.creeps) {
        tasks += new GetEnergy(c)
        idleCreeps += c
      }
    }

    if (!tasks.isEmpty && !idleCreeps.isEmpty) {
      val t = tasks.dequeue()
      val c = idleCreeps.minBy { c => t.costFor(c) }
      try {
        runningTasks += t.performWith(c)
        Console.log(s"Running task $t with $c")
      } catch {
        case _: IllegalArgumentException => 
          tasks += t // Requeue the task that we failed to run
      }
    }

    for(t <- runningTasks.toSeq) {
      t.run()
      t.state match {
        case RunningTask.Running(_) => ()
        case s => 
          Console.log(s"Finished task task $t ($s)")
          runningTasks -= t
      }
    }
    */
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
