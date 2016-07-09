var constants = {
OK: OK,
ERR_NOT_OWNER: ERR_NOT_OWNER,
ERR_NO_PATH: ERR_NO_PATH,
ERR_NAME_EXISTS: ERR_NAME_EXISTS,
ERR_BUSY: ERR_BUSY,
ERR_NOT_FOUND: ERR_NOT_FOUND,
ERR_NOT_ENOUGH_ENERGY: ERR_NOT_ENOUGH_ENERGY,
ERR_NOT_ENOUGH_RESOURCES: ERR_NOT_ENOUGH_RESOURCES,
ERR_INVALID_TARGET: ERR_INVALID_TARGET,
ERR_FULL: ERR_FULL,
ERR_NOT_IN_RANGE: ERR_NOT_IN_RANGE,
ERR_INVALID_ARGS: ERR_INVALID_ARGS,
ERR_TIRED: ERR_TIRED,
ERR_NO_BODYPART: ERR_NO_BODYPART,
ERR_NOT_ENOUGH_EXTENSIONS: ERR_NOT_ENOUGH_EXTENSIONS,
ERR_RCL_NOT_ENOUGH: ERR_RCL_NOT_ENOUGH,
ERR_GCL_NOT_ENOUGH: ERR_GCL_NOT_ENOUGH,
FIND_EXIT_TOP: FIND_EXIT_TOP,
FIND_EXIT_RIGHT: FIND_EXIT_RIGHT,
FIND_EXIT_BOTTOM: FIND_EXIT_BOTTOM,
FIND_EXIT_LEFT: FIND_EXIT_LEFT,
FIND_EXIT: FIND_EXIT,
FIND_CREEPS: FIND_CREEPS,
FIND_MY_CREEPS: FIND_MY_CREEPS,
FIND_HOSTILE_CREEPS: FIND_HOSTILE_CREEPS,
FIND_SOURCES_ACTIVE: FIND_SOURCES_ACTIVE,
FIND_SOURCES: FIND_SOURCES,
FIND_DROPPED_ENERGY: FIND_DROPPED_ENERGY,
FIND_DROPPED_RESOURCES: FIND_DROPPED_RESOURCES,
FIND_STRUCTURES: FIND_STRUCTURES,
FIND_MY_STRUCTURES: FIND_MY_STRUCTURES,
FIND_HOSTILE_STRUCTURES: FIND_HOSTILE_STRUCTURES,
FIND_FLAGS: FIND_FLAGS,
FIND_CONSTRUCTION_SITES: FIND_CONSTRUCTION_SITES,
FIND_MY_SPAWNS: FIND_MY_SPAWNS,
FIND_HOSTILE_SPAWNS: FIND_HOSTILE_SPAWNS,
FIND_MY_CONSTRUCTION_SITES: FIND_MY_CONSTRUCTION_SITES,
FIND_HOSTILE_CONSTRUCTION_SITES: FIND_HOSTILE_CONSTRUCTION_SITES,
FIND_MINERALS: FIND_MINERALS,
FIND_NUKES: FIND_NUKES,
TOP: TOP,
TOP_RIGHT: TOP_RIGHT,
RIGHT: RIGHT,
BOTTOM_RIGHT: BOTTOM_RIGHT,
BOTTOM: BOTTOM,
BOTTOM_LEFT: BOTTOM_LEFT,
LEFT: LEFT,
TOP_LEFT: TOP_LEFT,
COLOR_RED: COLOR_RED,
COLOR_PURPLE: COLOR_PURPLE,
COLOR_BLUE: COLOR_BLUE,
COLOR_CYAN: COLOR_CYAN,
COLOR_GREEN: COLOR_GREEN,
COLOR_YELLOW: COLOR_YELLOW,
COLOR_ORANGE: COLOR_ORANGE,
COLOR_BROWN: COLOR_BROWN,
COLOR_GREY: COLOR_GREY,
COLOR_WHITE: COLOR_WHITE,
LOOK_CREEPS: LOOK_CREEPS,
LOOK_ENERGY: LOOK_ENERGY,
LOOK_RESOURCES: LOOK_RESOURCES,
LOOK_SOURCES: LOOK_SOURCES,
LOOK_MINERALS: LOOK_MINERALS,
LOOK_STRUCTURES: LOOK_STRUCTURES,
LOOK_FLAGS: LOOK_FLAGS,
LOOK_CONSTRUCTION_SITES: LOOK_CONSTRUCTION_SITES,
LOOK_NUKES: LOOK_NUKES,
LOOK_TERRAIN: LOOK_TERRAIN,
OBSTACLE_OBJECT_TYPES: OBSTACLE_OBJECT_TYPES,
MOVE: MOVE,
WORK: WORK,
CARRY: CARRY,
ATTACK: ATTACK,
RANGED_ATTACK: RANGED_ATTACK,
TOUGH: TOUGH,
HEAL: HEAL,
CLAIM: CLAIM,
BODYPART_COST: BODYPART_COST,
CREEP_LIFE_TIME: CREEP_LIFE_TIME,
CREEP_CLAIM_LIFE_TIME: CREEP_CLAIM_LIFE_TIME,
CREEP_CORPSE_RATE: CREEP_CORPSE_RATE,
CARRY_CAPACITY: CARRY_CAPACITY,
HARVEST_POWER: HARVEST_POWER,
HARVEST_MINERAL_POWER: HARVEST_MINERAL_POWER,
REPAIR_POWER: REPAIR_POWER,
DISMANTLE_POWER: DISMANTLE_POWER,
BUILD_POWER: BUILD_POWER,
ATTACK_POWER: ATTACK_POWER,
UPGRADE_CONTROLLER_POWER: UPGRADE_CONTROLLER_POWER,
RANGED_ATTACK_POWER: RANGED_ATTACK_POWER,
HEAL_POWER: HEAL_POWER,
RANGED_HEAL_POWER: RANGED_HEAL_POWER,
REPAIR_COST: REPAIR_COST,
DISMANTLE_COST: DISMANTLE_COST,
RAMPART_DECAY_AMOUNT: RAMPART_DECAY_AMOUNT,
RAMPART_DECAY_TIME: RAMPART_DECAY_TIME,
RAMPART_HITS: RAMPART_HITS,
RAMPART_HITS_MAX: RAMPART_HITS_MAX,
ENERGY_REGEN_TIME: ENERGY_REGEN_TIME,
ENERGY_DECAY: ENERGY_DECAY,
SPAWN_HITS: SPAWN_HITS,
SPAWN_ENERGY_START: SPAWN_ENERGY_START,
SPAWN_ENERGY_CAPACITY: SPAWN_ENERGY_CAPACITY,
CREEP_SPAWN_TIME: CREEP_SPAWN_TIME,
SOURCE_ENERGY_CAPACITY: SOURCE_ENERGY_CAPACITY,
SOURCE_ENERGY_NEUTRAL_CAPACITY: SOURCE_ENERGY_NEUTRAL_CAPACITY,
SOURCE_ENERGY_KEEPER_CAPACITY: SOURCE_ENERGY_KEEPER_CAPACITY,
WALL_HITS: WALL_HITS,
WALL_HITS_MAX: WALL_HITS_MAX,
EXTENSION_HITS: EXTENSION_HITS,
EXTENSION_ENERGY_CAPACITY: EXTENSION_ENERGY_CAPACITY,
ROAD_HITS: ROAD_HITS,
ROAD_WEAROUT: ROAD_WEAROUT,
ROAD_DECAY_AMOUNT: ROAD_DECAY_AMOUNT,
ROAD_DECAY_TIME: ROAD_DECAY_TIME,
LINK_HITS: LINK_HITS,
LINK_HITS_MAX: LINK_HITS_MAX,
LINK_CAPACITY: LINK_CAPACITY,
LINK_COOLDOWN: LINK_COOLDOWN,
LINK_LOSS_RATIO: LINK_LOSS_RATIO,
STORAGE_CAPACITY: STORAGE_CAPACITY,
STORAGE_HITS: STORAGE_HITS,
STRUCTURE_SPAWN: STRUCTURE_SPAWN,
STRUCTURE_EXTENSION: STRUCTURE_EXTENSION,
STRUCTURE_ROAD: STRUCTURE_ROAD,
STRUCTURE_WALL: STRUCTURE_WALL,
STRUCTURE_RAMPART: STRUCTURE_RAMPART,
STRUCTURE_KEEPER_LAIR: STRUCTURE_KEEPER_LAIR,
STRUCTURE_PORTAL: STRUCTURE_PORTAL,
STRUCTURE_CONTROLLER: STRUCTURE_CONTROLLER,
STRUCTURE_LINK: STRUCTURE_LINK,
STRUCTURE_STORAGE: STRUCTURE_STORAGE,
STRUCTURE_TOWER: STRUCTURE_TOWER,
STRUCTURE_OBSERVER: STRUCTURE_OBSERVER,
STRUCTURE_POWER_BANK: STRUCTURE_POWER_BANK,
STRUCTURE_POWER_SPAWN: STRUCTURE_POWER_SPAWN,
STRUCTURE_EXTRACTOR: STRUCTURE_EXTRACTOR,
STRUCTURE_LAB: STRUCTURE_LAB,
STRUCTURE_TERMINAL: STRUCTURE_TERMINAL,
STRUCTURE_CONTAINER: STRUCTURE_CONTAINER,
STRUCTURE_NUKER: STRUCTURE_NUKER,
CONSTRUCTION_COST: CONSTRUCTION_COST,
CONSTRUCTION_COST_ROAD_SWAMP_RATIO: CONSTRUCTION_COST_ROAD_SWAMP_RATIO,
CONTROLLER_LEVELS: CONTROLLER_LEVELS,
CONTROLLER_STRUCTURES: CONTROLLER_STRUCTURES,
CONTROLLER_DOWNGRADE: CONTROLLER_DOWNGRADE,
CONTROLLER_CLAIM_DOWNGRADE: CONTROLLER_CLAIM_DOWNGRADE,
CONTROLLER_RESERVE: CONTROLLER_RESERVE,
CONTROLLER_RESERVE_MAX: CONTROLLER_RESERVE_MAX,
CONTROLLER_MAX_UPGRADE_PER_TICK: CONTROLLER_MAX_UPGRADE_PER_TICK,
CONTROLLER_ATTACK_BLOCKED_UPGRADE: CONTROLLER_ATTACK_BLOCKED_UPGRADE,
TOWER_HITS: TOWER_HITS,
TOWER_CAPACITY: TOWER_CAPACITY,
TOWER_ENERGY_COST: TOWER_ENERGY_COST,
TOWER_POWER_ATTACK: TOWER_POWER_ATTACK,
TOWER_POWER_HEAL: TOWER_POWER_HEAL,
TOWER_POWER_REPAIR: TOWER_POWER_REPAIR,
TOWER_OPTIMAL_RANGE: TOWER_OPTIMAL_RANGE,
TOWER_FALLOFF_RANGE: TOWER_FALLOFF_RANGE,
TOWER_FALLOFF: TOWER_FALLOFF,
OBSERVER_HITS: OBSERVER_HITS,
OBSERVER_RANGE: OBSERVER_RANGE,
POWER_BANK_HITS: POWER_BANK_HITS,
POWER_BANK_CAPACITY_MAX: POWER_BANK_CAPACITY_MAX,
POWER_BANK_CAPACITY_MIN: POWER_BANK_CAPACITY_MIN,
POWER_BANK_CAPACITY_CRIT: POWER_BANK_CAPACITY_CRIT,
POWER_BANK_DECAY: POWER_BANK_DECAY,
POWER_BANK_HIT_BACK: POWER_BANK_HIT_BACK,
POWER_SPAWN_HITS: POWER_SPAWN_HITS,
POWER_SPAWN_ENERGY_CAPACITY: POWER_SPAWN_ENERGY_CAPACITY,
POWER_SPAWN_POWER_CAPACITY: POWER_SPAWN_POWER_CAPACITY,
POWER_SPAWN_ENERGY_RATIO: POWER_SPAWN_ENERGY_RATIO,
EXTRACTOR_HITS: EXTRACTOR_HITS,
LAB_HITS: LAB_HITS,
LAB_MINERAL_CAPACITY: LAB_MINERAL_CAPACITY,
LAB_ENERGY_CAPACITY: LAB_ENERGY_CAPACITY,
LAB_BOOST_ENERGY: LAB_BOOST_ENERGY,
LAB_BOOST_MINERAL: LAB_BOOST_MINERAL,
LAB_COOLDOWN: LAB_COOLDOWN,
GCL_POW: GCL_POW,
GCL_MULTIPLY: GCL_MULTIPLY,
GCL_NOVICE: GCL_NOVICE,
MODE_SIMULATION: MODE_SIMULATION,
MODE_SURVIVAL: MODE_SURVIVAL,
MODE_WORLD: MODE_WORLD,
MODE_ARENA: MODE_ARENA,
TERRAIN_MASK_WALL: TERRAIN_MASK_WALL,
TERRAIN_MASK_SWAMP: TERRAIN_MASK_SWAMP,
TERRAIN_MASK_LAVA: TERRAIN_MASK_LAVA,
MAX_CONSTRUCTION_SITES: MAX_CONSTRUCTION_SITES,
MAX_CREEP_SIZE: MAX_CREEP_SIZE,
MINERAL_REGEN_TIME: MINERAL_REGEN_TIME,
MINERAL_MIN_AMOUNT: MINERAL_MIN_AMOUNT,
MINERAL_RANDOM_FACTOR: MINERAL_RANDOM_FACTOR,
TERMINAL_CAPACITY: TERMINAL_CAPACITY,
TERMINAL_HITS: TERMINAL_HITS,
TERMINAL_SEND_COST: TERMINAL_SEND_COST,
TERMINAL_MIN_SEND: TERMINAL_MIN_SEND,
CONTAINER_HITS: CONTAINER_HITS,
CONTAINER_CAPACITY: CONTAINER_CAPACITY,
CONTAINER_DECAY: CONTAINER_DECAY,
CONTAINER_DECAY_TIME: CONTAINER_DECAY_TIME,
CONTAINER_DECAY_TIME_OWNED: CONTAINER_DECAY_TIME_OWNED,
NUKER_HITS: NUKER_HITS,
NUKER_COOLDOWN: NUKER_COOLDOWN,
NUKER_ENERGY_CAPACITY: NUKER_ENERGY_CAPACITY,
NUKER_GHODIUM_CAPACITY: NUKER_GHODIUM_CAPACITY,
NUKE_LAND_TIME: NUKE_LAND_TIME,
NUKE_RANGE: NUKE_RANGE,
NUKE_DAMAGE: NUKE_DAMAGE,
RESOURCE_ENERGY: RESOURCE_ENERGY,
RESOURCE_POWER: RESOURCE_POWER,
RESOURCE_HYDROGEN: RESOURCE_HYDROGEN,
RESOURCE_OXYGEN: RESOURCE_OXYGEN,
RESOURCE_UTRIUM: RESOURCE_UTRIUM,
RESOURCE_LEMERGIUM: RESOURCE_LEMERGIUM,
RESOURCE_KEANIUM: RESOURCE_KEANIUM,
RESOURCE_ZYNTHIUM: RESOURCE_ZYNTHIUM,
RESOURCE_CATALYST: RESOURCE_CATALYST,
RESOURCE_GHODIUM: RESOURCE_GHODIUM,
RESOURCE_HYDROXIDE: RESOURCE_HYDROXIDE,
RESOURCE_ZYNTHIUM_KEANITE: RESOURCE_ZYNTHIUM_KEANITE,
RESOURCE_UTRIUM_LEMERGITE: RESOURCE_UTRIUM_LEMERGITE,
RESOURCE_UTRIUM_HYDRIDE: RESOURCE_UTRIUM_HYDRIDE,
RESOURCE_UTRIUM_OXIDE: RESOURCE_UTRIUM_OXIDE,
RESOURCE_KEANIUM_HYDRIDE: RESOURCE_KEANIUM_HYDRIDE,
RESOURCE_KEANIUM_OXIDE: RESOURCE_KEANIUM_OXIDE,
RESOURCE_LEMERGIUM_HYDRIDE: RESOURCE_LEMERGIUM_HYDRIDE,
RESOURCE_LEMERGIUM_OXIDE: RESOURCE_LEMERGIUM_OXIDE,
RESOURCE_ZYNTHIUM_HYDRIDE: RESOURCE_ZYNTHIUM_HYDRIDE,
RESOURCE_ZYNTHIUM_OXIDE: RESOURCE_ZYNTHIUM_OXIDE,
RESOURCE_GHODIUM_HYDRIDE: RESOURCE_GHODIUM_HYDRIDE,
RESOURCE_GHODIUM_OXIDE: RESOURCE_GHODIUM_OXIDE,
RESOURCE_UTRIUM_ACID: RESOURCE_UTRIUM_ACID,
RESOURCE_UTRIUM_ALKALIDE: RESOURCE_UTRIUM_ALKALIDE,
RESOURCE_KEANIUM_ACID: RESOURCE_KEANIUM_ACID,
RESOURCE_KEANIUM_ALKALIDE: RESOURCE_KEANIUM_ALKALIDE,
RESOURCE_LEMERGIUM_ACID: RESOURCE_LEMERGIUM_ACID,
RESOURCE_LEMERGIUM_ALKALIDE: RESOURCE_LEMERGIUM_ALKALIDE,
RESOURCE_ZYNTHIUM_ACID: RESOURCE_ZYNTHIUM_ACID,
RESOURCE_ZYNTHIUM_ALKALIDE: RESOURCE_ZYNTHIUM_ALKALIDE,
RESOURCE_GHODIUM_ACID: RESOURCE_GHODIUM_ACID,
RESOURCE_GHODIUM_ALKALIDE: RESOURCE_GHODIUM_ALKALIDE,
RESOURCE_CATALYZED_UTRIUM_ACID: RESOURCE_CATALYZED_UTRIUM_ACID,
RESOURCE_CATALYZED_UTRIUM_ALKALIDE: RESOURCE_CATALYZED_UTRIUM_ALKALIDE,
RESOURCE_CATALYZED_KEANIUM_ACID: RESOURCE_CATALYZED_KEANIUM_ACID,
RESOURCE_CATALYZED_KEANIUM_ALKALIDE: RESOURCE_CATALYZED_KEANIUM_ALKALIDE,
RESOURCE_CATALYZED_LEMERGIUM_ACID: RESOURCE_CATALYZED_LEMERGIUM_ACID,
RESOURCE_CATALYZED_LEMERGIUM_ALKALIDE: RESOURCE_CATALYZED_LEMERGIUM_ALKALIDE,
RESOURCE_CATALYZED_ZYNTHIUM_ACID: RESOURCE_CATALYZED_ZYNTHIUM_ACID,
RESOURCE_CATALYZED_ZYNTHIUM_ALKALIDE: RESOURCE_CATALYZED_ZYNTHIUM_ALKALIDE,
RESOURCE_CATALYZED_GHODIUM_ACID: RESOURCE_CATALYZED_GHODIUM_ACID,
RESOURCE_CATALYZED_GHODIUM_ALKALIDE: RESOURCE_CATALYZED_GHODIUM_ALKALIDE,
REACTIONS: REACTIONS,
BOOSTS: BOOSTS,
BODYPARTS_ALL: BODYPARTS_ALL,
RESOURCES_ALL: RESOURCES_ALL,
COLORS_ALL: COLORS_ALL
};
module.exports.loop = ((typeof global === "object" && global &&
         global["Object"] === Object) ? global : this)["org"]["singingwizard"]["screeps"]["ai"]["Main"]().getLoop(__globals);