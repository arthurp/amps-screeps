package org.singingwizard.screeps.wrappers

import scala.collection.mutable
import org.singingwizard.screeps.api._
import prickle._
import scala.util.Success
import scala.util.Failure

object APIPickler {
  implicit def hasIDPickler[S <: HasID]: Pickler[S] = new Pickler[S] {
    def pickle[P](value: S, state: PickleState)(implicit config: PConfig[P]): P = {
      config.makeString(value.id)
    }
  }
  implicit def hasIDUnpickler[S <: HasID](implicit ctx: ScreepsContext): Unpickler[S] = new Unpickler[S] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]) = {
      val id = config.readString(pickle)

      id.flatMap { id =>
        val v = ctx.Game.getObjectById[S](id)
        if (v != null) Success(v) else Failure(new RuntimeException(s"Object with id $id does not exist"))
      }
    }
  }

}