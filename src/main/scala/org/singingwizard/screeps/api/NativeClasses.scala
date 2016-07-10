package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._
import js.|

// Derived from TypeScript types from:
// https://github.com/screepers/Screeps-Typescript-Declarations @ 2d48f03149153f98806f35fe284fd2878c23d497

// TODO: Convert Doubles to Ints in appropriate places (most of them are appropriate I think).
// TODO: Figure out how to deal with overload and default arguments problems.

@js.native
trait ConstructionSite extends RoomObject {
  var id: String
  var my: Boolean
  var owner: Owner
  var progress: Double
  var progressTotal: Double
  var structureType: String
  def remove(): Double
}

@js.native
trait Storage extends StructureStorage {
}

@js.native
trait Creep extends RoomObject {
  var body: js.Array[BodyPartDefinition]
  var carry: StoreDefinition
  var carryCapacity: Double
  var fatigue: Double
  var hits: Double
  var hitsMax: Double
  var id: String
  var memory: js.Dynamic
  var my: Boolean
  var name: String
  var owner: Owner
  var spawning: Boolean
  var ticksToLive: Double
  def attack(target: Creep | Spawn | Structure): Double
  def attackController(target: Structure): Double
  def build(target: ConstructionSite): Double
  def cancelOrder(methodName: String): Double
  def claimController(target: StructureController): Double
  def dismantle(target: Spawn | Structure): Double
  def drop(resourceType: String, amount: Double = ???): Double
  def getActiveBodyparts(typ: String): Double
  def harvest(target: Source | Mineral): Double
  def heal(target: Creep): Double
  def move(direction: Double): Double
  def moveByPath(path: js.Array[PathStep] | js.Any | String): Double
  //def moveTo(x: Double, y: Double, opts: MoveToOpts | PathFinderOps = ???): Double
  def moveTo(target: RoomPosition | js.Any, opts: MoveToOpts | PathFinderOps = ???): Double
  def notifyWhenAttacked(enabled: Boolean): Double
  def pickup(target: Resource): Double
  def rangedAttack(target: Creep | Spawn | Structure): Double
  def rangedHeal(target: Creep): Double
  def rangedMassAttack(): Double
  def repair(target: Spawn | Structure): Double
  def reserveController(target: StructureController): Double
  def say(message: String): Double
  def suicide(): Double
  def transfer(target: Creep | Spawn | Structure, resourceType: String, amount: Double = ???): Double
  def upgradeController(target: StructureController): Double
}

@js.native
trait Flag extends RoomObject {
  var color: Double
  var memory: js.Dynamic
  var name: String
  var roomName: String
  var secondaryColor: Double
  def remove(): Unit
  def setColor(color: String, secondaryColor: String = ???): Double
  def setPosition(x: Double, y: Double): Double
  def setPosition(pos: RoomPosition | js.Any): Double
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
  var time: Double
  def getObjectById[T](id: String): T
  def notify(message: String, groupInterval: Double): Unit
}

@js.native
trait GlobalControlLevel extends js.Object {
  var level: Double
  var progress: Double
  var progressTotal: Double
}

@js.native
trait CPU extends js.Object {
  var limit: Double
  var tickLimit: Double
  var bucket: Double
  def getUsed(): Double
}

@js.native
trait BodyPartDefinition extends js.Object {
  var boost: String
  @JSName("type")
  var typ: String
  var hits: Double
}

@js.native
trait Owner extends js.Object {
  var username: String
}

@js.native
trait ReservationDefinition extends js.Object {
  var username: String
  var ticksToEnd: Double
}

@js.native
trait StoreDefinition extends js.Object {
  @JSBracketAccess
  def apply(resource: String): Double
  @JSBracketAccess
  def update(resource: String, v: Double): Unit
  var energy: Double
  var power: Double
}

@js.native
trait LookAtResultWithPos extends js.Object {
  var x: Double
  var y: Double
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
  var constructionSite: ConstructionSite
  var creep: Creep
  var energy: Resource
  var exit: js.Any
  var flag: Flag
  var source: Source
  var structure: Structure
  var terrain: String
}

@js.native
trait LookAtResultMatrix extends js.Object {
  @JSBracketAccess
  def apply(coord: Double): LookAtResultMatrix | js.Array[LookAtResult]
  @JSBracketAccess
  def update(coord: Double, v: LookAtResultMatrix | js.Array[LookAtResult]): Unit
}

@js.native
trait FindPathOpts extends js.Object {
  var ignoreCreeps: Boolean
  var ignoreDestructibleStructures: Boolean
  var ignoreRoads: Boolean
  var ignore: js.Array[js.Any] | js.Array[RoomPosition]
  var avoid: js.Array[js.Any] | js.Array[RoomPosition]
  var maxOps: Double
  var heuristicWeight: Double
  var serialize: Boolean
  var maxRooms: Double
}

@js.native
trait MoveToOpts extends js.Object {
  var reusePath: Double
  var noPathFinding: Boolean
}

@js.native
trait PathStep extends js.Object {
  var x: Double
  var dx: Double
  var y: Double
  var dy: Double
  var direction: Double
}

@js.native
trait SurvivalGameInfo extends js.Object {
  var score: Double
  var timeToWave: Double
  var wave: Double
}

@js.native
trait GameMap extends js.Object {
  def describeExits(roomName: String): js.Any
  def findExit(fromRoom: String | Room, toRoom: String | Room): String | Double
  def findRoute(fromRoom: String | Room, toRoom: String | Room): js.Array[js.Any] | Double
  def getRoomLinearDistance(roomName1: String, roomName2: String): Double
  def getTerrainAt(x: Double, y: Double, roomName: String): String
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
  var time: Double
  var sender: js.Any
  var recipient: js.Any
  var resourceType: String
  var amount: Double
  var from: String
  var to: String
  var description: String
}

@js.native
trait Mineral extends RoomObject {
  var mineralAmount: Double
  var mineralType: String
  var id: String
  var ticksToRegeneration: Double
}

@js.native
trait RawMemory extends js.Object {
  def get(): String
  def set(value: String): js.Dynamic
}

@js.native
trait Resource extends RoomObject {
  var amount: Double
  var id: String
  var resourceType: String
}

@js.native
trait RoomObject extends js.Object {
  var pos: RoomPosition
  var room: Room
}

@js.native
trait Room extends js.Object {
  var controller: StructureController
  var energyAvailable: Double
  var energyCapacityAvailable: Double
  var memory: js.Dynamic
  var mode: String
  var name: String
  var storage: StructureStorage
  var survivalInfo: SurvivalGameInfo
  var terminal: StructureTerminal
  def createConstructionSite(x: Double, y: Double, structureType: String): Double
  //def createConstructionSite(pos: RoomPosition | js.Any, structureType: String): Double
  def createFlag(x: Double, y: Double, name: String, color: String, secondaryColor: String = ???): Double
  //def createFlag(pos: RoomPosition | js.Any, name: String, color: String, secondaryColor: String = ???): Double
  def find[T](typ: Double, opts: js.Any = ???): js.Array[T]
  def findExitTo(room: String | Room): String | Double
  def findPath(fromPos: RoomPosition, toPos: RoomPosition, opts: FindPathOpts = ???): js.Array[PathStep]
  def getPositionAt(x: Double, y: Double): RoomPosition
  def lookAt(x: Double, y: Double): js.Array[LookAtResult]
  def lookAt(target: RoomPosition | js.Any): js.Array[LookAtResult]
  def lookAtArea(top: Double, left: Double, bottom: Double, right: Double, asArray: Boolean = ???): LookAtResultMatrix | js.Array[LookAtResultWithPos]
  def lookForAt[T](typ: String, x: Double, y: Double): js.Array[T]
  def lookForAt[T](typ: String, target: RoomPosition | js.Any): js.Array[T]
  def lookForAtArea(typ: String, top: Double, left: Double, bottom: Double, right: Double, asArray: Boolean = ???): LookAtResultMatrix | js.Array[LookAtResultWithPos]
  def serializePath(path: js.Array[PathStep]): String
}

@js.native
trait Source extends js.Object {
  var energy: Double
  var energyCapacity: Double
  var id: String
  var pos: RoomPosition
  var room: Room
  var ticksToRegeneration: Double
}

@js.native
trait Spawn extends OwnedStructure {
  var energy: Double
  var energyCapacity: Double
  var hits: Double
  var hitsMax: Double
  var id: String
  var memory: js.Dynamic
  var my: Boolean
  var name: String
  var owner: Owner
  var pos: RoomPosition
  var room: Room
  var structureType: String
  var spawning: js.Any
  def canCreateCreep(body: js.Array[String], name: String = ???): Double
  def createCreep(body: js.Array[String], name: String = ???, memory: js.Any = ???): Int | String
  def destroy(): Double
  def isActive(): Boolean
  def notifyWhenAttacked(enabled: Boolean): Double
  def renewCreep(target: Creep): Double
  def recycleCreep(target: Creep): Double
  def transferEnergy(target: Creep, amount: Double = ???): Double
}

@js.native
trait Structure extends RoomObject {
  var hits: Double
  var hitsMax: Double
  var id: String
  var structureType: String
  def destroy(): Double
  def isActive(): Boolean
  def notifyWhenAttacked(enabled: Boolean): Double
}

@js.native
trait OwnedStructure extends Structure {
  var my: Boolean
  var owner: Owner
}

@js.native
trait StructureController extends OwnedStructure {
  var level: Double
  var progress: Double
  var progressTotal: Double
  var reservation: ReservationDefinition
  var ticksToDowngrade: Double
  def unclaim(): Double
}

@js.native
trait StructureExtension extends OwnedStructure {
  var energy: Double
  var energyCapacity: Double
  def transferEnergy(target: Creep, amount: Double = ???): Double
}

@js.native
trait StructureLink extends OwnedStructure {
  var cooldown: Double
  var energy: Double
  var energyCapacity: Double
  def transferEnergy(target: Creep | StructureLink, amount: Double = ???): Double
}

@js.native
trait StructureKeeperLair extends OwnedStructure {
  var ticksToSpawn: Double
}

@js.native
trait StructureObserver extends OwnedStructure {
  def observerRoom(roomName: String): Double
}

@js.native
trait StructurePowerBank extends OwnedStructure {
  var power: Double
  var ticksToDecay: Double
}

@js.native
trait StructurePowerSpawn extends OwnedStructure {
  var energy: Double
  var energyCapacity: Double
  var power: Double
  var powerCapacity: Double
  def createPowerCreep(name: String): Double
  def processPower(): Double
  def transferEnergy(target: Creep, amount: Double = ???): Double
}

@js.native
trait StructureRampart extends OwnedStructure {
  var ticksToDecay: Double
  var isPublic: Boolean
  def setPublic(isPublic: Boolean): js.Dynamic
}

@js.native
trait StructureRoad extends Structure {
  var ticksToDecay: Double
}

@js.native
trait StructureStorage extends OwnedStructure {
  var store: StoreDefinition
  var storeCapacity: Double
  def transfer(target: Creep, resourceType: String, amount: Double = ???): Double
  def transferEnergy(target: Creep, amount: Double = ???): Double
}

@js.native
trait StructureTower extends OwnedStructure {
  var energy: Double
  var energyCapacity: Double
  def attack(target: Creep): Double
  def heal(target: Creep): Double
  def repair(target: Spawn | Structure): Double
  def transferEnergy(target: Creep, amount: Double = ???): Double
}

@js.native
trait StructureWall extends Structure {
  var ticksToLive: Double
}

@js.native
trait StructureExtractor extends OwnedStructure {
}

@js.native
trait StructureLab extends OwnedStructure {
  var energy: Double
  var energyCapacity: Double
  var mineralAmount: Double
  var mineralType: String
  var mineralCapacity: Double
  def boostCreep(creep: Creep, bodyPartsCount: Double = ???): Double
  def runReaction(lab1: StructureLab, lab2: StructureLab): Double
  def transfer(target: Creep, resourceType: String, amount: Double = ???): Double
}

@js.native
trait StructureTerminal extends OwnedStructure {
  var store: js.Any
  var storeCapacity: Double
  def send(resourceType: String, amount: Double, destination: String, description: String = ???): Double
  def transfer(target: Creep, resourceType: String, amount: Double = ???): Double
}

@js.native
trait StructureContainer extends Structure {
  var store: js.Any
  var storeCapacity: Double
  def transfer(target: Creep, resourceType: String, amount: Double = ???): Double
}
