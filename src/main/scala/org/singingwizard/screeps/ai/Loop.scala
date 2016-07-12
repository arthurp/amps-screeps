package org.singingwizard.screeps.ai

import scala.scalajs.js
import org.singingwizard.screeps.api._
import org.singingwizard.screeps.wrappers._
import scala.collection.mutable

class Loop()(implicit val ctx: ScreepsContext) {
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._
  import LoDash._

  val harvester = new Harvester(this)
  val upgrader = new Upgrader(this)
  val builder = new Builder(this)

  val MIN_HARVESTERS = 6
  val MIN_UPGRADERS = 2
  val MIN_BUILDERS = 5

  case class SourceData(source: ObjectById[Source], positions: Int)
  case class RoomData(room: String, sources: Seq[SourceData])

  val roomDataCache = mutable.Map[String, RoomData]()

  def computeSourceData(s: Source): SourceData = {
    val room = s.room
    val RoomPosition(x, y, _) = s.pos
    val area = room.lookAtArea(y - 1, x - 1, y + 1, x + 1).asInstanceOf[LookAtResultMatrix]
    val positions = 8 - (for (i <- y - 1 to y + 1; j <- x - 1 to x + 1 if i != y || j != x) yield {
      val l = area(i)(j)
      l.exists((r: LookAtResult) => {
        r.typ == "creep" ||
          (r.typ == "terrain" && r.terrain == "wall") ||
          (r.typ == "structure" && r.structure.structureType != STRUCTURE_ROAD && r.structure.structureType != STRUCTURE_RAMPART)
      })
    }).count(x => x)
    SourceData(ObjectById(s), positions)
  }
  def computeRoomData(r: Room): RoomData = {
    RoomData(r.name, r.find[Source](FIND_SOURCES).map(computeSourceData))
  }

  def roomData(r: Room): RoomData = roomDataCache.getOrElseUpdate(r.name, computeRoomData(r))

  def creepCost(parts: Seq[String]) = {
    parts.map(BODYPART_COST(_)).sum
  }

  def loop(): Unit = {
    PathFinder.use(true)
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

    for ((name, creep) <- Game.creeps) {
      val working = creep.memory.role.asInstanceOf[String] match {
        case "harvester" => harvester.run(creep)
        case "upgrader"  => upgrader.run(creep)
        case "builder"   => builder.run(creep)
      }

      val waitPos = Game.flags.get("WaitPoint").map(_.pos).getOrElse(RoomPosition(32, 32, creep.room.name))

      if (!working && creep.pos.getRangeTo(waitPos) > 3) {
        creep.moveTo(waitPos)
      }
    }

    val creepCounts = Game.creeps.values.groupBy(_.memory.role.asInstanceOf[String]).mapValues(_.size).withDefaultValue(0)
    val spawnRules = Seq(
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE, MOVE, MOVE, WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE, MOVE, MOVE), "harvester"),
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE, MOVE, MOVE), "harvester"),
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, CARRY, CARRY, MOVE, MOVE, MOVE), "harvester"),
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, CARRY, MOVE, MOVE), "harvester"),
      (creepCounts("harvester") < MIN_HARVESTERS) -> (js.Array(WORK, CARRY, MOVE), "harvester"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, WORK, WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE, WORK, WORK, WORK, WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, WORK, WORK, WORK, CARRY, CARRY, CARRY, MOVE, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, WORK, CARRY, CARRY, MOVE, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, CARRY, CARRY, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, WORK, CARRY, MOVE), "builder"),
      (creepCounts("builder") < MIN_BUILDERS) -> (js.Array(WORK, CARRY, MOVE, MOVE), "builder"),
      (creepCounts("upgrader") < MIN_UPGRADERS) -> (js.Array(WORK, WORK, WORK, WORK, WORK, WORK, CARRY, CARRY, CARRY, CARRY, CARRY, CARRY, MOVE, MOVE, MOVE, MOVE, MOVE, MOVE), "upgrader"),
      (creepCounts("upgrader") < MIN_UPGRADERS) -> (js.Array(WORK, WORK, CARRY, CARRY, MOVE), "upgrader"),
      (creepCounts("upgrader") < MIN_UPGRADERS) -> (js.Array(WORK, WORK, CARRY, MOVE), "upgrader"),
      (creepCounts("upgrader") < MIN_UPGRADERS) -> (js.Array(WORK, CARRY, MOVE, MOVE), "upgrader"))
    val spawnPriority = spawnRules.filter(_._1).map(_._2)

    def spawnFromList(l: Seq[(js.Array[String], String)]): Unit = l match {
      case (parts, role) :: rest =>
        (spawn.createCreep(parts, null, jsObj(role = role)): Any) match {
          case _: String =>
            Console.log(s"Successfully spawned ${parts.size} part ${role}")
            ()
          case _ =>
            //Console.log(s"Failed to spawn ${parts.size} part ${role}")
            spawnFromList(rest)
        }
      case Seq() =>
        ()
    }

    spawnFromList(spawnPriority)
  }
}
