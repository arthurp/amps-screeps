package org.singingwizard.screeps.wrappers

import org.singingwizard.screeps.api._

class ObjectById[T <: HasID](v: T) {
  val id = v.id
  def get()(implicit ctx: ScreepsContext): T = ctx.Game.getObjectById[T](id)
}

object ObjectById {
  def apply[T <: HasID](v: T) = new ObjectById(v)
  def unapply[T <: HasID](v: String)(implicit ctx: ScreepsContext): T = ctx.Game.getObjectById[T](v)
  def unapply[T <: HasID](v: ObjectById[T])(implicit ctx: ScreepsContext): T = v.get()
}
