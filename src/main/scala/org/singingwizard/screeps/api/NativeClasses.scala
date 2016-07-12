package org.singingwizard.screeps.api

import scala.scalajs.js
import scala.scalajs.js.annotation._
import js.|
import scala.scalajs.js.UndefOr

// Derived from TypeScript types from:
// https://github.com/screepers/Screeps-Typescript-Declarations @ 2d48f03149153f98806f35fe284fd2878c23d497

@js.native
trait HasID extends js.Object {
  val id: String  
}

@js.native
trait ConstructionSite extends RoomObject with HasID {
  val id: String
  val my: Boolean
  val owner: Owner
  val progress: Int
  val progressTotal: Int
  val structureType: String
  def remove(): Int
}

@js.native
trait Storage extends StructureStorage {
}

@js.native
trait Creep extends RoomObject with HasID {
  val body: js.Array[BodyPartDefinition]
  val carry: StoreDefinition
  val carryCapacity: Int
  val fatigue: Int
  val hits: Int
  val hitsMax: Int
  val id: String
  val memory: js.Dynamic
  val my: Boolean
  val name: String
  val owner: Owner
  val spawning: Boolean
  val ticksToLive: Int
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
  val color: Int
  val memory: js.Dynamic
  val name: String
  val roomName: String
  val secondaryColor: Int
  def remove(): Unit
  def setColor(color: String, secondaryColor: String = ???): Int
  def setPosition(x: Int, y: Int): Int
  def setPosition(pos: RoomPosition | js.Any): Int
}

@js.native
trait Game extends js.Object {
  val cpu: CPU
  val creeps: js.Dictionary[Creep]
  val flags: js.Dictionary[Flag]
  val gcl: GlobalControlLevel
  val map: GameMap
  val market: Market
  val rooms: js.Dictionary[Room]
  val spawns: js.Dictionary[Spawn]
  val structures: js.Dictionary[Structure]
  val constructionSites: js.Dictionary[ConstructionSite]
  val time: Int
  def getObjectById[T](id: String): T
  def notify(message: String, groupInterval: Int): Unit
}

@js.native
trait GlobalControlLevel extends js.Object {
  val level: Int
  val progress: Int
  val progressTotal: Int
}

@js.native
trait CPU extends js.Object {
  val limit: Int
  val tickLimit: Int
  val bucket: Int
  def getUsed(): Int
}

@js.native
trait BodyPartDefinition extends js.Object {
  var boost: String
  @JSName("type")
  val typ: String
  val hits: Int
}

@js.native
trait Owner extends js.Object {
  val username: String
}

@js.native
trait ReservationDefinition extends js.Object {
  val username: String
  val ticksToEnd: Int
}

@js.native
trait StoreDefinition extends js.Object {
  @JSBracketAccess
  def apply(resource: String): Int
  @JSBracketAccess
  def update(resource: String, v: Int): Unit
  val energy: Int
  val power: Int
}

@js.native
trait LookAtResultWithPos extends js.Object {
  val x: Int
  val y: Int
  // TODO: Only one of these will actually be available.
  @JSName("type")
  val typ: String
  val creep: Creep
  val terrain: String
  val structure: Structure
}

@js.native
trait LookAtResult extends js.Object {
  @JSName("type")
  val typ: String
  // TODO: Only one of these will actually be available.
  val constructionSite: ConstructionSite
  val creep: Creep
  val energy: Resource
  val exit: js.Dynamic // TODO: Make this a real type once it's documented.
  val flag: Flag
  val source: Source
  val structure: Structure
  val terrain: String
}

@js.native
trait LookAtResultLine extends js.Object {
  @JSBracketAccess
  def apply(coord: Int): js.Array[LookAtResult]
}

@js.native
trait LookAtResultMatrix extends js.Object {
  @JSBracketAccess
  def apply(coord: Int): LookAtResultLine
}

@js.native
trait FindPathOpts extends js.Object {
  val ignoreCreeps: Boolean
  val ignoreDestructibleStructures: Boolean
  val ignoreRoads: Boolean
  val ignore: js.Array[js.Any] | js.Array[RoomPosition]
  val avoid: js.Array[js.Any] | js.Array[RoomPosition]
  val maxOps: Int
  val heuristicWeight: Int
  val serialize: Boolean
  val maxRooms: Int
}

@js.native
trait MoveToOpts extends js.Object {
  val reusePath: Int
  val noPathFinding: Boolean
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
  val score: Int
  val timeToWave: Int
  val wave: Int
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
  val incomingTransactions: js.Array[Transaction]
  val outgoingTransactions: js.Array[Transaction]
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
trait Mineral extends RoomObject with HasID {
  val mineralAmount: Int
  val mineralType: String
  val id: String
  val ticksToRegeneration: Int
}

@js.native
trait RawMemory extends js.Object {
  def get(): String
  def set(value: String): js.Dynamic
}

@js.native
trait Resource extends RoomObject with HasID {
  val amount: Int
  val id: String
  val resourceType: String
}

@js.native
trait RoomObject extends js.Object {
  val pos: RoomPosition
  val room: Room
}

@js.native
trait FindFilter[T] extends js.Object {
  val filter: (T) => Boolean
}

object FindFilter {
  def apply[T](f: (T) => Boolean): FindFilter[T] = js.Dynamic.literal("filter" -> f).asInstanceOf[FindFilter[T]]
}

@js.native
trait Room extends js.Object {
  val controller: StructureController
  val energyAvailable: Int
  val energyCapacityAvailable: Int
  val memory: js.Dynamic
  val mode: String
  val name: String
  val storage: StructureStorage
  val survivalInfo: SurvivalGameInfo
  val terminal: StructureTerminal
  @JSName("createConstructionSite")
  def createConstructionSiteCoords(x: Int, y: Int, structureType: String): Int
  def createConstructionSite(pos: RoomPosition | js.Any, structureType: String): Int
  @JSName("createFlag")
  def createFlagCoords(x: Int, y: Int, name: String, color: String, secondaryColor: String = ???): Int
  def createFlag(pos: RoomPosition | js.Any, name: String, color: String, secondaryColor: String = ???): Int
  def find[T](typ: Int, opts: FindFilter[T] = ???): js.Array[T]
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
trait FindFilterAlgorithm[T] extends FindFilter[T] {
  val filter: (T) => Boolean
  val algorithm: UndefOr[String]
}

object FindFilterAlgorithm {
  import scala.language.implicitConversions
  def apply[T](f: (T) => Boolean, algo: String): FindFilterAlgorithm[T] = 
    js.Dynamic.literal("filter" -> f, "algorithm" -> algo).asInstanceOf[FindFilterAlgorithm[T]]
  
  // TODO: This is a dangerous hack I think. The member algorithm is not set. This may cause runtime errors.
  def apply[T](f: (T) => Boolean): FindFilterAlgorithm[T] = 
    js.Dynamic.literal("filter" -> f).asInstanceOf[FindFilterAlgorithm[T]]
  
  implicit def FindFilter2FindFilterAlgorithm[T](f: FindFilter[T]): FindFilterAlgorithm[T] =
    apply(f.filter)
}

@js.native
trait RoomPosition extends js.Object {
  val x: Int
  val y: Int
  val roomName: String

  def createConstructionSite(structureType: String): Double
  def createFlag(name: String = ???, color: Double = ???, secondaryColor: Double = ???): Double
  
  def findClosestByPath[T](typ: Int, opts: FindFilterAlgorithm[T] = ???): T
  @JSName("findClosestByPath")
  def findClosestByPathFrom[T](objects: js.Array[T] | js.Array[RoomPosition], opts: FindFilterAlgorithm[T] = ???): T
  
  def findClosestByRange[T](typ: Int, opts: FindFilter[T] = ???): T
  @JSName("findClosestByRange")
  def findClosestByRangeFrom[T](objects: js.Array[T] | js.Array[RoomPosition], opts: FindFilter[T] = ???): T
  
  def findInRange[T](typ: Int, range: Int, opts: FindFilter[T] = ???): js.Array[T]
  @JSName("findInRange")
  def findInRangeFrom[T](objects: js.Array[T] | js.Array[RoomPosition], range: Double, opts: FindFilter[T] = ???): js.Array[T]
  
  //def findPathTo(x: Double, y: Double, opts: FindPathOpts = ???): js.Array[PathStep]
  def findPathTo(target: RoomPosition | js.Any, opts: FindPathOpts = ???): js.Array[PathStep]
  def getDirectionTo(x: Int, y: Int): Int
  def getDirectionTo(target: RoomPosition | js.Any): Int
  def getRangeTo(x: Int, y: Int): Int
  def getRangeTo(target: RoomPosition | js.Any): Int
  def inRangeTo(toPos: RoomPosition, range: Double): Boolean
  def isEqualTo(x: Int, y: Int): Boolean
  def isEqualTo(target: RoomPosition | js.Any): Boolean
  def isNearTo(x: Int, y: Int): Boolean
  def isNearTo(target: RoomPosition | js.Any): Boolean
  def look(): js.Array[LookAtResult]
  def lookFor[T](typ: String): js.Array[T]
}



@js.native
trait Source extends js.Object with HasID {
  val energy: Int
  val energyCapacity: Int
  val id: String
  val pos: RoomPosition
  val room: Room
  val ticksToRegeneration: Int
}

@js.native
trait SpawningResult extends js.Object {
  val name: String
  val needTime: Int
  val remainingTime: Int
}

@js.native
trait Spawn extends OwnedStructure with HasID {
  val energy: Int
  val energyCapacity: Int
  val id: String
  val memory: js.Dynamic
  val my: Boolean
  val name: String
  val owner: Owner
  val pos: RoomPosition
  val room: Room
  val structureType: String
  val spawning: SpawningResult
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
trait Structure extends RoomObject with HasID {
  val hits: UndefOr[Int]
  val hitsMax: UndefOr[Int]
  val id: String
  val structureType: String
  def destroy(): Int
  def isActive(): Boolean
  def notifyWhenAttacked(enabled: Boolean): Int
}

@js.native
trait OwnedStructure extends Structure {
  val my: Boolean
  val owner: Owner
}

@js.native
trait StructureController extends OwnedStructure {
  val level: Int
  val progress: Int
  val progressTotal: Int
  val reservation: ReservationDefinition
  val ticksToDowngrade: Int
  def unclaim(): Int
}

@js.native
trait StructureExtension extends OwnedStructure {
  val energy: Int
  val energyCapacity: Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureLink extends OwnedStructure {
  val cooldown: Int
  val energy: Int
  val energyCapacity: Int
  def transferEnergy(target: Creep | StructureLink, amount: Int = ???): Int
}

@js.native
trait StructureKeeperLair extends OwnedStructure {
  val ticksToSpawn: Int
}

@js.native
trait StructureObserver extends OwnedStructure {
  def observerRoom(roomName: String): Int
}

@js.native
trait StructurePowerBank extends OwnedStructure {
  val power: Int
  val ticksToDecay: Int
}

@js.native
trait StructurePowerSpawn extends OwnedStructure {
  val energy: Int
  val energyCapacity: Int
  val power: Int
  val powerCapacity: Int
  def createPowerCreep(name: String): Int
  def processPower(): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureRampart extends OwnedStructure {
  val ticksToDecay: Int
  val isPublic: Boolean
  def setPublic(isPublic: Boolean): js.Dynamic
}

@js.native
trait StructureRoad extends Structure {
  val ticksToDecay: Int
}

@js.native
trait StructureStorage extends OwnedStructure {
  val store: StoreDefinition
  val storeCapacity: Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureTower extends OwnedStructure {
  val energy: Int
  val energyCapacity: Int
  def attack(target: Creep): Int
  def heal(target: Creep): Int
  def repair(target: Spawn | Structure): Int
  def transferEnergy(target: Creep, amount: Int = ???): Int
}

@js.native
trait StructureWall extends Structure {
  val ticksToLive: Int
}

@js.native
trait StructureExtractor extends OwnedStructure {
}

@js.native
trait StructureLab extends OwnedStructure {
  val energy: Int
  val energyCapacity: Int
  val mineralAmount: Int
  val mineralType: String
  val mineralCapacity: Int
  def boostCreep(creep: Creep, bodyPartsCount: Int = ???): Int
  def runReaction(lab1: StructureLab, lab2: StructureLab): Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}

@js.native
trait StructureTerminal extends OwnedStructure {
  val store: js.Dictionary[Int]
  val storeCapacity: Int
  def send(resourceType: String, amount: Int, destination: String, description: String = ???): Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}

@js.native
trait StructureContainer extends Structure {
  val store: js.Dictionary[Int]
  var storeCapacity: Int
  def transfer(target: Creep, resourceType: String, amount: Int = ???): Int
}
