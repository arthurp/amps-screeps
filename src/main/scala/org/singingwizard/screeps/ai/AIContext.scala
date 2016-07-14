package org.singingwizard.screeps.ai

import scala.collection.mutable
import prickle._
import scala.util.Try
import org.singingwizard.screeps.ai.tasks.GetEnergy

case class AIContext(tasks: mutable.Set[Task] = mutable.Set()) {
  /*
  case class SourceData(source: Source, positions: Int)
  
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
  */  
}

object AIContext {
  implicit def mutablesetUnpickler[T](implicit unpickler: Unpickler[T]) =  new Unpickler[mutable.Set[T]] {
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