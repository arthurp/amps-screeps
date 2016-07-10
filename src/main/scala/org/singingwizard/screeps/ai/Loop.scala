package org.singingwizard.screeps.ai

import scala.scalajs.js
import org.singingwizard.screeps.api._

class Loop()(implicit val ctx: ScreepsContext) {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  import LoDash._

  val harvester = new Harvester()
  val upgrader = new Upgrader()
  val builder = new Builder()

  val MIN_HARVESTERS = 2
  val MIN_UPGRADERS = 1
  val MIN_BUILDERS = 1

  def loop(): Unit = {
    PathFinder.use(true)

    /*
    var tower = Game.getObjectById('TOWER_ID');
    if(tower) {
        var closestDamagedStructure = tower.pos.findClosestByRange(FIND_STRUCTURES, {
            filter: (structure) => structure.hits < structure.hitsMax
        });
        if(closestDamagedStructure) {
            tower.repair(closestDamagedStructure);
        }

        var closestHostile = tower.pos.findClosestByRange(FIND_HOSTILE_CREEPS);
        if(closestHostile) {
            tower.attack(closestHostile);
        }
    }
    */

    for ((name, creep) <- Game.creeps) {
      val working = creep.memory.role.asInstanceOf[String] match {
        case "harvester" => harvester.run(creep)
        case "upgrader" => upgrader.run(creep)
        case "builder" => builder.run(creep)
      }
      
      val waitPos = Game.flags("WaitPoint").pos
      
      if(!working && creep.pos.getRangeTo(waitPos) > 5) {
        creep.moveTo(waitPos)
      }
    }

    val creepCounts = Game.creeps.values.groupBy(_.memory.role.asInstanceOf[String]).mapValues(_.size).withDefaultValue(0)
    val spawnRules = Seq(
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, CARRY, MOVE), "harvester"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, WORK, CARRY, CARRY, MOVE, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, CARRY, MOVE), "builder"),
      (creepCounts("upgrader") < MIN_UPGRADERS) -> (js.Array(WORK, WORK, CARRY, MOVE), "upgrader"))
    val spawnPriority = spawnRules.filter(_._1).map(_._2)

    def spawnFromList(l: Seq[(js.Array[String], String)]): Unit = l match {
      case (parts, role) :: rest =>
        (Game.spawns("Spawn1").createCreep(parts, null, jsObj(role = role)): Any) match {
          case _: String =>
            Console.log(s"Successfully spawned ${parts.size} part ${role}")
            ()
          case _ =>
            Console.log(s"Failed to spawn ${parts.size} part ${role}")
            spawnFromList(rest)
        }
      case Seq() =>
        ()
    }

    spawnFromList(spawnPriority)
  }
}
