package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait ScreepsContext extends js.Object {
  val OK: Int
  val ERR_NOT_OWNER: Int
  val ERR_NO_PATH: Int
  val ERR_NAME_EXISTS: Int
  val ERR_BUSY: Int
  val ERR_NOT_FOUND: Int
  val ERR_NOT_ENOUGH_ENERGY: Int
  val ERR_NOT_ENOUGH_RESOURCES: Int
  val ERR_INVALID_TARGET: Int
  val ERR_FULL: Int
  val ERR_NOT_IN_RANGE: Int
  val ERR_INVALID_ARGS: Int
  val ERR_TIRED: Int
  val ERR_NO_BODYPART: Int
  val ERR_NOT_ENOUGH_EXTENSIONS: Int
  val ERR_RCL_NOT_ENOUGH: Int
  val ERR_GCL_NOT_ENOUGH: Int

  val FIND_EXIT_TOP: Int
  val FIND_EXIT_RIGHT: Int
  val FIND_EXIT_BOTTOM: Int
  val FIND_EXIT_LEFT: Int
  val FIND_EXIT: Int
  val FIND_CREEPS: Int
  val FIND_MY_CREEPS: Int
  val FIND_HOSTILE_CREEPS: Int
  val FIND_SOURCES_ACTIVE: Int
  val FIND_SOURCES: Int
  val FIND_DROPPED_ENERGY: Int
  val FIND_DROPPED_RESOURCES: Int
  val FIND_STRUCTURES: Int
  val FIND_MY_STRUCTURES: Int
  val FIND_HOSTILE_STRUCTURES: Int
  val FIND_FLAGS: Int
  val FIND_CONSTRUCTION_SITES: Int
  val FIND_MY_SPAWNS: Int
  val FIND_HOSTILE_SPAWNS: Int
  val FIND_MY_CONSTRUCTION_SITES: Int
  val FIND_HOSTILE_CONSTRUCTION_SITES: Int
  val FIND_MINERALS: Int
  val FIND_NUKES: Int

  val TOP: Int
  val TOP_RIGHT: Int
  val RIGHT: Int
  val BOTTOM_RIGHT: Int
  val BOTTOM: Int
  val BOTTOM_LEFT: Int
  val LEFT: Int
  val TOP_LEFT: Int

  val COLOR_RED: Int
  val COLOR_PURPLE: Int
  val COLOR_BLUE: Int
  val COLOR_CYAN: Int
  val COLOR_GREEN: Int
  val COLOR_YELLOW: Int
  val COLOR_ORANGE: Int
  val COLOR_BROWN: Int
  val COLOR_GREY: Int
  val COLOR_WHITE: Int

  val LOOK_CREEPS: String
  val LOOK_ENERGY: String
  val LOOK_RESOURCES: String
  val LOOK_SOURCES: String
  val LOOK_MINERALS: String
  val LOOK_STRUCTURES: String
  val LOOK_FLAGS: String
  val LOOK_CONSTRUCTION_SITES: String
  val LOOK_NUKES: String
  val LOOK_TERRAIN: String

  val OBSTACLE_OBJECT_TYPES: js.Array[String]

  val MOVE: String
  val WORK: String
  val CARRY: String
  val ATTACK: String
  val RANGED_ATTACK: String
  val TOUGH: String
  val HEAL: String
  val CLAIM: String

  val BODYPART_COST: js.Dictionary[Int]

  val CREEP_LIFE_TIME: Int
  val CREEP_CLAIM_LIFE_TIME: Int
  val CREEP_CORPSE_RATE: Double

  val CARRY_CAPACITY: Int
  val HARVEST_POWER: Int
  val HARVEST_MINERAL_POWER: Int
  val REPAIR_POWER: Int
  val DISMANTLE_POWER: Int
  val BUILD_POWER: Int
  val ATTACK_POWER: Int
  val UPGRADE_CONTROLLER_POWER: Int
  val RANGED_ATTACK_POWER: Int
  val HEAL_POWER: Int
  val RANGED_HEAL_POWER: Int
  val REPAIR_COST: Double
  val DISMANTLE_COST: Double

  val RAMPART_DECAY_AMOUNT: Int
  val RAMPART_DECAY_TIME: Int
  val RAMPART_HITS: Int
  // TODO: RAMPART_HITS_MAX: {2: 300000, 3: 1000000, 4: 3000000, 5: 10000000, 6: 30000000, 7: 100000000, 8: 300000000},

  val ENERGY_REGEN_TIME: Int
  val ENERGY_DECAY: Int

  val SPAWN_HITS: Int
  val SPAWN_ENERGY_START: Int
  val SPAWN_ENERGY_CAPACITY: Int
  val CREEP_SPAWN_TIME: Int

  val SOURCE_ENERGY_CAPACITY: Int
  val SOURCE_ENERGY_NEUTRAL_CAPACITY: Int
  val SOURCE_ENERGY_KEEPER_CAPACITY: Int

  val WALL_HITS: Int
  val WALL_HITS_MAX: Int

  val EXTENSION_HITS: Int
  // TODO: EXTENSION_ENERGY_CAPACITY: {0: 50, 1: 50, 2: 50, 3: 50, 4: 50, 5: 50, 6: 50, 7: 100, 8: 200},

  val ROAD_HITS: Int
  val ROAD_WEAROUT: Int
  val ROAD_DECAY_AMOUNT: Int
  val ROAD_DECAY_TIME: Int

  val LINK_HITS: Int
  val LINK_HITS_MAX: Int
  val LINK_CAPACITY: Int
  val LINK_COOLDOWN: Int
  val LINK_LOSS_RATIO: Double

  val STORAGE_CAPACITY: Int
  val STORAGE_HITS: Int

  val STRUCTURE_SPAWN: String
  val STRUCTURE_EXTENSION: String
  val STRUCTURE_ROAD: String
  val STRUCTURE_WALL: String
  val STRUCTURE_RAMPART: String
  val STRUCTURE_KEEPER_LAIR: String
  val STRUCTURE_PORTAL: String
  val STRUCTURE_CONTROLLER: String
  val STRUCTURE_LINK: String
  val STRUCTURE_STORAGE: String
  val STRUCTURE_TOWER: String
  val STRUCTURE_OBSERVER: String
  val STRUCTURE_POWER_BANK: String
  val STRUCTURE_POWER_SPAWN: String
  val STRUCTURE_EXTRACTOR: String
  val STRUCTURE_LAB: String
  val STRUCTURE_TERMINAL: String
  val STRUCTURE_CONTAINER: String
  val STRUCTURE_NUKER: String

  val CONSTRUCTION_COST: js.Dictionary[Int]
  val CONSTRUCTION_COST_ROAD_SWAMP_RATIO: Int

  val CONTROLLER_LEVELS: js.Dictionary[Int] // TODO: Keys are Int

  val CONTROLLER_STRUCTURES: js.Dictionary[js.Dictionary[Int]] // TODO: 2nd level keys are Int
  // TODO: CONTROLLER_DOWNGRADE: {1: 20000, 2: 50000, 3: 50000, 4: 50000, 5: 50000, 6: 50000, 7: 50000, 8: 50000},
  val CONTROLLER_CLAIM_DOWNGRADE: Double
  val CONTROLLER_RESERVE: Int
  val CONTROLLER_RESERVE_MAX: Int
  val CONTROLLER_MAX_UPGRADE_PER_TICK: Int
  val CONTROLLER_ATTACK_BLOCKED_UPGRADE: Int

  val TOWER_HITS: Int
  val TOWER_CAPACITY: Int
  val TOWER_ENERGY_COST: Int
  val TOWER_POWER_ATTACK: Int
  val TOWER_POWER_HEAL: Int
  val TOWER_POWER_REPAIR: Int
  val TOWER_OPTIMAL_RANGE: Int
  val TOWER_FALLOFF_RANGE: Int
  val TOWER_FALLOFF: Double

  val OBSERVER_HITS: Int
  val OBSERVER_RANGE: Int

  val POWER_BANK_HITS: Int
  val POWER_BANK_CAPACITY_MAX: Int
  val POWER_BANK_CAPACITY_MIN: Int
  val POWER_BANK_CAPACITY_CRIT: Double
  val POWER_BANK_DECAY: Int
  val POWER_BANK_HIT_BACK: Double

  val POWER_SPAWN_HITS: Int
  val POWER_SPAWN_ENERGY_CAPACITY: Int
  val POWER_SPAWN_POWER_CAPACITY: Int
  val POWER_SPAWN_ENERGY_RATIO: Int

  val EXTRACTOR_HITS: Int

  val LAB_HITS: Int
  val LAB_MINERAL_CAPACITY: Int
  val LAB_ENERGY_CAPACITY: Int
  val LAB_BOOST_ENERGY: Int
  val LAB_BOOST_MINERAL: Int
  val LAB_COOLDOWN: Int

  val GCL_POW: Double
  val GCL_MULTIPLY: Int
  val GCL_NOVICE: Int

  val MODE_SIMULATION: String
  val MODE_SURVIVAL: String
  val MODE_WORLD: String
  val MODE_ARENA: String

  val TERRAIN_MASK_WALL: Int
  val TERRAIN_MASK_SWAMP: Int
  val TERRAIN_MASK_LAVA: Int

  val MAX_CONSTRUCTION_SITES: Int
  val MAX_CREEP_SIZE: Int

  val MINERAL_REGEN_TIME: Int
  val MINERAL_MIN_AMOUNT: js.Dictionary[Int]
  val MINERAL_RANDOM_FACTOR: Int

  val TERMINAL_CAPACITY: Int
  val TERMINAL_HITS: Int
  val TERMINAL_SEND_COST: Double
  val TERMINAL_MIN_SEND: Int

  val CONTAINER_HITS: Int
  val CONTAINER_CAPACITY: Int
  val CONTAINER_DECAY: Int
  val CONTAINER_DECAY_TIME: Int
  val CONTAINER_DECAY_TIME_OWNED: Int

  val NUKER_HITS: Int
  val NUKER_COOLDOWN: Int
  val NUKER_ENERGY_CAPACITY: Int
  val NUKER_GHODIUM_CAPACITY: Int
  val NUKE_LAND_TIME: Int
  val NUKE_RANGE: Int
  /* TODO:
    NUKE_DAMAGE: {
        0: 10000000,
        1: 1000000,
        4: 100000
    },
    */

  val RESOURCE_ENERGY: String
  val RESOURCE_POWER: String

  val RESOURCE_HYDROGEN: String
  val RESOURCE_OXYGEN: String
  val RESOURCE_UTRIUM: String
  val RESOURCE_LEMERGIUM: String
  val RESOURCE_KEANIUM: String
  val RESOURCE_ZYNTHIUM: String
  val RESOURCE_CATALYST: String
  val RESOURCE_GHODIUM: String

  val RESOURCE_HYDROXIDE: String
  val RESOURCE_ZYNTHIUM_KEANITE: String
  val RESOURCE_UTRIUM_LEMERGITE: String

  val RESOURCE_UTRIUM_HYDRIDE: String
  val RESOURCE_UTRIUM_OXIDE: String
  val RESOURCE_KEANIUM_HYDRIDE: String
  val RESOURCE_KEANIUM_OXIDE: String
  val RESOURCE_LEMERGIUM_HYDRIDE: String
  val RESOURCE_LEMERGIUM_OXIDE: String
  val RESOURCE_ZYNTHIUM_HYDRIDE: String
  val RESOURCE_ZYNTHIUM_OXIDE: String
  val RESOURCE_GHODIUM_HYDRIDE: String
  val RESOURCE_GHODIUM_OXIDE: String

  val RESOURCE_UTRIUM_ACID: String
  val RESOURCE_UTRIUM_ALKALIDE: String
  val RESOURCE_KEANIUM_ACID: String
  val RESOURCE_KEANIUM_ALKALIDE: String
  val RESOURCE_LEMERGIUM_ACID: String
  val RESOURCE_LEMERGIUM_ALKALIDE: String
  val RESOURCE_ZYNTHIUM_ACID: String
  val RESOURCE_ZYNTHIUM_ALKALIDE: String
  val RESOURCE_GHODIUM_ACID: String
  val RESOURCE_GHODIUM_ALKALIDE: String

  val RESOURCE_CATALYZED_UTRIUM_ACID: String
  val RESOURCE_CATALYZED_UTRIUM_ALKALIDE: String
  val RESOURCE_CATALYZED_KEANIUM_ACID: String
  val RESOURCE_CATALYZED_KEANIUM_ALKALIDE: String
  val RESOURCE_CATALYZED_LEMERGIUM_ACID: String
  val RESOURCE_CATALYZED_LEMERGIUM_ALKALIDE: String
  val RESOURCE_CATALYZED_ZYNTHIUM_ACID: String
  val RESOURCE_CATALYZED_ZYNTHIUM_ALKALIDE: String
  val RESOURCE_CATALYZED_GHODIUM_ACID: String
  val RESOURCE_CATALYZED_GHODIUM_ALKALIDE: String

  val REACTIONS: js.Dictionary[js.Dictionary[String]]

  val BOOSTS: js.Dictionary[js.Dictionary[js.Dictionary[Double]]]
  // TODO: Maybe use js.Dynamic for inner objects

  val BODYPARTS_ALL: js.Array[String]
  val RESOURCES_ALL: js.Array[String]
  val COLORS_ALL: js.Array[Int]

  val Game: Game
  
  val Memory: js.Dynamic

  val RawMemory: RawMemory
  
  val PathFinder: PathFinder
  
  @JSName("console")
  val Console: Console
  
  val Room: RoomTypeObject
}

@js.native
trait RoomTypeObject extends js.Object {
  def deserializePath(path: String): js.Array[PathStep]
}

object ScreepsContext {
  implicit class ScreepsContextOps(val ctx: ScreepsContext) {
    object RoomPosition {
      def apply(x: Int, y: Int, roomName: String) = {
        js.Dynamic.newInstance(ctx.asInstanceOf[js.Dynamic].RoomPosition)(x, y, roomName).asInstanceOf[RoomPosition]
      }
      def unapply(p: RoomPosition): Option[(Double, Double, String)] = Some((p.x, p.y, p.roomName))
    }
  }
}