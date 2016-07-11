package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._
import js.|

// Derived from TypeScript types from:
// https://github.com/screepers/Screeps-Typescript-Declarations @ 2d48f03149153f98806f35fe284fd2878c23d497

@js.native
trait ConstructionSite extends RoomObject {
  var id: String
  var my: Boolean
  var owner: Owner
  var progress: Int
  var progressTotal: Int
  var structureType: String
  def remove(): Int
}

@js.native
trait Storage extends StructureStorage {
}

@js.native
trait Creep extends RoomObject {
  var body: js.Array[BodyPartDefinition]
  var carry: StoreDefinition
  var carryCapacity: Int
  var fatigue: Int
  var hits: Int
  var hitsMax: Int
  var id: String
  var memory: js.Dynamic
  var my: Boolean
  var name: String
  var owner: Owner
  var spawning: Boolean
  var ticksToLive: Int
  def attack(target: Creep | Spawn | Structure): Int
  def attackController(target: Structure): Int
  def build(target: ConstructionSite): Int
  def cancelOrder(methodName: String): Int
  def claimController(target: StructureController): Int
  def dismantle(target: Spawn | Structure): Int
  def drop(resourceType: String, amount: Int = ???): Int
  def getActiveBodyparts(typ: String): Int
  def harvest(target: Source | Mineral): Int
  def heal(target: Creep): Int
  def move(direction: Int): Int
  def moveByPath(path: js.Array[PathStep] | js.Array[RoomPosition] | String): Int
  @JSName("moveTo")
  def moveToCoords(x: Int, y: Int, opts: MoveToOpts | PathFinderOps = ???): Int
  def moveTo(target: RoomPosition | js.Any, opts: MoveToOpts | PathFinderOps = ???): Int
  def notifyWhenAttacked(enabled: Boolean): Int
  def pickup(target: Resource): Int
  def rangedAttack(target: Creep | Spawn | Structure): Int
  def rangedHeal(target: Creep): Int
  def rangedMassAttack(): Int
  def repair(target: Spawn | Structure): Int
  def reserveController(target: StructureController): Int
  def say(message: String): Int
  def suicide(): Int
  def transfer(target: Creep | Spawn | Structure, resourceType: String, amount: Int = ???): Int
  def upgradeController(target: StructureController): Int
}

@js.native
trait Flag extends RoomObject {
  var color: Int
  var memory: js.Dynamic
  var name: String
  var roomName: String
  var secondaryColor: Int
  def remove(): Unit
  def setColor(color: String, secondaryColor: String = ???): Int
  def setPosition(x: Int, y: Int): Int
  def setPosition(pos: RoomPosition | js.Any): Int
}

@js.native
trait Game extends js.Object {
  var cpu: CPU
  var creeps: js.Dictionary[Creep]
  var flags: js.Dictionary[Flag]
  var gcl: GlobalControlLevel
  var map: GameMap
  var market: Market
  var rooms: js.Dictionary[Room]
  var spawns: js.Dictionary[Spawn]
  var structures: js.Dictionary[Structure]
  var constructionSites: js.Dictionary[ConstructionSite]
  var time: Int
  def getObjectById[T](id: String): T
  def notify(message: String, groupInterval: Int): Unit
}

@js.native
trait GlobalControlLevel extends js.Object {
  var level: Int
  var progress: Int
  var progressTotal: Int
}

@js.native
trait CPU extends js.Object {
  var limit: Int
  var tickLimit: Int
  var bucket: Int
  def getUsed(): Int
}

@js.native
trait BodyPartDefinition extends js.Object {
  var boost: String
  @JSName("type")
  var typ: String
  var hits: Int
}

@js.native
trait Owner extends js.Object {
  var username: String
}

@js.native
trait ReservationDefinition extends js.Object {
  var username: String
  var ticksToEnd: Int
}

@js.native
trait StoreDefinition extends js.Object {
  @JSBracketAccess
  def apply(resource: String): Int
  @JSBracketAccess
  def update(resource: String, v: Int): Unit
  var energy: Int
  var power: Int
}

@js.native
trait LookAtResultWithPos extends js.Object {
  var x: Int
  var y: Int
  @JSName("type")
  var typ: String
  var creep: Creep
  var terrain: String
  var structure: Structure
}

@js.native
trait LookAtResult extends js.Object {
  @JSName("type")
  var typ: String
  // TODO: Only one of these will actually be available.
  var constructionSite: ConstructionSite
  var creep: Creep
  var energy: Resource
  var exit: js.Dynamic // TODO: Make this a real type once it's documented.
  var flag: Flag
  var source: Source
  var structure: Structure
  var terrain: String
}

@js.native
trait LookAtResultMatrix extends js.Object {
  @JSBracketAccess
  def apply(coord: Int): LookAtResultMatrix | js.Array[LookAtResult]
  @JSBracketAccess
  def update(coord: Int, v: LookAtResultMatrix | js.Array[LookAtResult]): Unit
}

@js.native
trait FindPathOpts extends js.Object {
  var ignoreCreeps: Boolean
  var ignoreDestructibleStructures: Boolean
  var ignoreRoads: Boolean
  var ignore: js.Array[js.Any] | js.Array[RoomPosition]
  var avoid: js.Array[js.Any] | js.Array[RoomPosition]
  var maxOps: Int
  var heuristicWeight: Int
  var serialize: Boolean
  var maxRooms: Int
}

@js.native
trait MoveToOpts extends js.Object {
  var reusePath: Int
  var noPathFinding: Boolean
}

@js.native
trait PathStep extends js.Object {
  var x: Int
  var dx: Int
  var y: Int
  var dy: Int
  var direction: Int
}

@js.native
trait SurvivalGameInfo extends js.Object {
  var score: Int
  var timeToWave: Int
  var wave: Int
}

@js.native
trait RoomRoute extends js.Object {
  val exit: Int
  val room: String
}

@js.native
trait GameMap extends js.Object {
  def describeExits(roomName: String): js.Any
  def findExit(fromRoom: String | Room, toRoom: String | Room): String | Int
  def findRoute(fromRoom: String | Room, toRoom: String | Room): js.Array[RoomRoute] | Int
  def getRoomLinearDistance(roomName1: String, roomName2: String): Int
  def getTerrainAt(x: Int, y: Int, roomName: String): String
  def getTerrainAt(pos: RoomPosition): String
  def isRoomProtected(roomName: String): Boolean
}

@js.native
trait Market extends js.Object {
  var incomingTransactions: js.Array[Transaction]
  var outgoingTransactions: js.Array[Transaction]
}

@js.native
trait Transaction extends js.Object {
  var transactionId: String
  var time: Int
  var sender: Owner
  var recipient: Owner
  var resourceType: String
  var amount: Int
  var from: String
  var to: String
  var description: String
}

@js.native
trait Mineral extends RoomObject {
  var mineralAmount: Int
  var mineralType: String
  var id: String
  var ticksToRegeneration: Int
}

@js.native
trait RawMemory extends js.Object {
  def get(): String
  def set(value: String): js.Dynamic
}

@js.native
trait Resource extends RoomObject {
  var amount: Int
  var id: String
  var resourceType: String
}

@js.native
trait RoomObject extends js.Object {
  var pos: RoomPosition
  var room: Room
}

@js.native
trait RoomFindOpts[T] extends js.Object {
  val filter: (T) => Boolean
}

@js.native
trait Room extends js.Object {
  var controller: StructureController
  var energyAvailable: Int
  var energyCapacityAvailable: Int
  var memory: js.Dynamic
  var mode: String
  var name: String
  var storage: StructureStorage
  var survivalInfo: SurvivalGameInfo
  var terminal: StructureTerminal
  @JSName("createConstructionSite")
  def createConstructionSiteCoords(x: Int, y: Int, structureType: String): Int
  def createConstructionSite(pos: RoomPosition | js.Any, structureType: String): Int
  @JSName("createFlag")
  def createFlagCoords(x: Int, y: Int, name: String, color: String, secondaryColor: String = ???): Int
  def createFlag(pos: RoomPosition | js.Any, name: String, color: String, secondaryColor: String = ???): Int
  def find[T](typ: Int, opts: js.Any = ???): js.Array[T]
  def findExitTo(room: String | Room): String | Int
  def findPath(fromPos: RoomPosition, toPos: RoomPosition, opts: FindPathOpts = ???): js.Array[PathStep]
  def getPositionAt(x: Int, y: Int): RoomPosition
  def lookAt(x: Int, y: Int): js.Array[LookAtResult]
  def lookAt(target: RoomPosition | js.Any): js.Array[LookAtResult]
  def lookAtArea(top: Int, left: Int, bottom: Int, right: Int, asArray: Boolean = ???): LookAtResultMatrix | js.Array[LookAtResultWithPos]
  def lookForAt[T](typ: String, x: Int, y: Int): js.Array[T]
  def lookForAt[T](typ: String, target: RoomPosition | js.Any): js.Array[T]
  def lookForAtArea(typ: String, top: Int, left: Int, bottom: Int, right: Int, asArray: Boolean = ???): LookAtResultMatrix | js.Array[LookAtResultWithPos]
  def serializePath(path: js.Array[PathStep]): String
}

@js.native
trait Source extends js.Object {
  var energy: Int
  var energyCapacity: Int
  var id: String
  var pos: RoomPosition
  var room: Room
  var ticksToRegeneration: Int
}

@js.native
trait SpawningResult extends js.Object {
  val name: String
  val needTime: Int
  val remainingTime: Int
}

@js.native
trait Spawn extends OwnedStructure {
  var energy: Int
  var energyCapacity: Int
  var hits: Int
  var hitsMax: Int
  var id: String
  var memory: js.Dynamic
  var my: Boolean
  var name: String
  var owner: Owner
  var pos: RoomPosition
  var room: Room
  var structureType: String
  var spawning: SpawningResult
  def canCreateCreep(body: js.Array[String], name: String = ???): Int
  def createCreep(body: js.Array[String], name: String = ???, memory: js.Any = ???): Int | String
  def destroy(): Int
  def isActive(): Boolean
  def notifyWhenAttacked(enabled: Boolean): Int
  def renewCreep(target: Creep): Int
  def recycleCreep(target: Creep): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait Structure extends RoomObject {
  var hits: Int
  var hitsMax: Int
  var id: String
  var structureType: String
  def destroy(): Int
  def isActive(): Boolean
  def notifyWhenAttacked(enabled: Boolean): Int
}

@js.native
trait OwnedStructure extends Structure {
  var my: Boolean
  var owner: Owner
}

@js.native
trait StructureController extends OwnedStructure {
  var level: Int
  var progress: Int
  var progressTotal: Int
  var reservation: ReservationDefinition
  var ticksToDowngrade: Int
  def unclaim(): Int
}

@js.native
trait StructureExtension extends OwnedStructure {
  var energy: Int
  var energyCapacity: Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureLink extends OwnedStructure {
  var cooldown: Int
  var energy: Int
  var energyCapacity: Int
  def transferEnergy(target: Creep | StructureLink, amount: Int = ???): Int
}

@js.native
trait StructureKeeperLair extends OwnedStructure {
  var ticksToSpawn: Int
}

@js.native
trait StructureObserver extends OwnedStructure {
  def observerRoom(roomName: String): Int
}

@js.native
trait StructurePowerBank extends OwnedStructure {
  var power: Int
  var ticksToDecay: Int
}

@js.native
trait StructurePowerSpawn extends OwnedStructure {
  var energy: Int
  var energyCapacity: Int
  var power: Int
  var powerCapacity: Int
  def createPowerCreep(name: String): Int
  def processPower(): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureRampart extends OwnedStructure {
  var ticksToDecay: Int
  var isPublic: Boolean
  def setPublic(isPublic: Boolean): js.Dynamic
}

@js.native
trait StructureRoad extends Structure {
  var ticksToDecay: Int
}

@js.native
trait StructureStorage extends OwnedStructure {
  var store: StoreDefinition
  var storeCapacity: Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureTower extends OwnedStructure {
  var energy: Int
  var energyCapacity: Int
  def attack(target: Creep): Int
  def heal(target: Creep): Int
  def repair(target: Spawn | Structure): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureWall extends Structure {
  var ticksToLive: Int
}

@js.native
trait StructureExtractor extends OwnedStructure {
}

@js.native
trait StructureLab extends OwnedStructure {
  var energy: Int
  var energyCapacity: Int
  var mineralAmount: Int
  var mineralType: String
  var mineralCapacity: Int
  def boostCreep(creep: Creep, bodyPartsCount: Int = ???): Int
  def runReaction(lab1: StructureLab, lab2: StructureLab): Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}

@js.native
trait StructureTerminal extends OwnedStructure {
  var store: js.Dictionary[Int]
  var storeCapacity: Int
  def send(resourceType: String, amount: Int, destination: String, description: String = ???): Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}

@js.native
trait StructureContainer extends Structure {
  var store: js.Dictionary[Int]
  var storeCapacity: Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}
