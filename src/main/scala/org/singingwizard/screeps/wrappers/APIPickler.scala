package org.singingwizard.screeps.wrappers

import scalajs.js
import scala.collection.mutable
import org.singingwizard.screeps.api._
import ScreepsContext._
import prickle._
import scala.util.Success
import scala.util.Failure

object APIPickler {
  implicit def hasIDPickler[S <: HasID]: Pickler[S] = new Pickler[S] {
    def pickle[P](value: S, state: PickleState)(implicit config: PConfig[P]): P = {
      config.makeString(value.id)
    }
  }
  implicit def hasIDUnpickler[S <: HasID]: Unpickler[S] = new Unpickler[S] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]) = {
      val id = config.readString(pickle)

      id.flatMap { id =>
        val v = Game.getObjectById[S](id)
        if (v != null) Success(v) else Failure(new RuntimeException(s"Object with id $id does not exist"))
      }
    }
  }

  def namePickler[S <: HasName]: Pickler[S] = new Pickler[S] {
    def pickle[P](value: S, state: PickleState)(implicit config: PConfig[P]): P = {
      config.makeString(value.name)
    }
  }
  def nameUnpickler[S <: HasName](lookup: js.Dictionary[S]): Unpickler[S] = new Unpickler[S] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]) = {
      config.readString(pickle).flatMap { name =>
        val v = lookup(name)
        if (v != null) Success(v) else Failure(new RuntimeException(s"Object with name $name does not exist"))
      }
    }
  }
  
  implicit val roomPickler = namePickler[Room]
  implicit val roomUnpickler = nameUnpickler(Game.rooms)

}