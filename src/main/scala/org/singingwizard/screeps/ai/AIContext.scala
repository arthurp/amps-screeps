package org.singingwizard.screeps.ai

import scala.collection.mutable
import prickle._
import scala.util.Try
import org.singingwizard.screeps.ai.tasks.GetEnergy
import org.singingwizard.prickle._
import org.singingwizard.screeps.api
import org.singingwizard.screeps.api._
import org.singingwizard.screeps.api.ScreepsContext._

sealed trait Commandable {
  val o: api.RoomObject
}

object Commandable {
  import org.singingwizard.screeps.wrappers.APIPickler._

  case class Creep(o: api.Creep) extends Commandable
  case class Structure(o: api.Structure) extends Commandable

  import scala.language.implicitConversions

  implicit def fromCreep(c: api.Creep) = Creep(c)
  implicit def fromStructure(c: api.Structure) = Structure(c)

  implicit val pickler = CompositePickler[Commandable].concreteType[Creep].concreteType[Structure]
}

case class SourceData(source: Source, positions: Int)
  
case class RoomData(room: String, sources: Seq[SourceData])

case class AIContext(runnableTasks: mutable.Set[Task] = mutable.Set(), available: mutable.Set[WeakRef[Commandable]] = mutable.Set()) {
  def isAvailable(c: Commandable): Boolean = {
    val cc = WeakRef(c)
    //Console.log(s"Claiming $cc (${cc.hashCode}) from ${available.map(cc => s"$cc (${cc.hashCode})").mkString("[",",","]")} (${available.contains(c)})") 
    available.contains(c)
  }
  def claim(c: Commandable): Boolean = {
    val cc = WeakRef(c)
    //Console.log(s"Claiming $cc (${cc.hashCode}) from ${available.map(cc => s"$cc (${cc.hashCode})").mkString("[",",","]")} (${available.contains(c)})") 
    available.remove(c)
  }
  def unclaim(c: Commandable): Unit = {
    if (!available.add(c)) {
      Console.log(s"Added commandable to available list that was already there: $c")
    }
  }
  
  def schedule(t: Task): Unit = {
    runnableTasks += t
  }

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
          (r.typ == "structure" && OBSTACLE_OBJECT_TYPES.contains(r.structure.structureType))
      })
    }).count(x => x)
    SourceData(s, positions)
  }
  def computeRoomData(r: Room): RoomData = {
    RoomData(r.name, r.find[Source](FIND_SOURCES).map(computeSourceData))
  }

  def roomData(r: Room): RoomData = roomDataCache.getOrElseUpdate(r.name, computeRoomData(r))
}

object AIContext {
  implicit def mutablesetUnpickler[T](implicit unpickler: Unpickler[T]) = new Unpickler[mutable.Set[T]] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]): Try[mutable.Set[T]] = {
      Unpickler.unpickleSeqish[T, mutable.Set, P](pickle, state)
    }
  }
  implicit def mutablesetPickler[T](implicit pickler: Pickler[T]) = new Pickler[mutable.Set[T]] {
    def pickle[P](value: mutable.Set[T], state: PickleState)(implicit config: PConfig[P]): P = {
      Pickler.resolvingSharingCollection[P](value, value.toSeq.map(e => Pickle(e, state)), state, config)
    }
  }
}