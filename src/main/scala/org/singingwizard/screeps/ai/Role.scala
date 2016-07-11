package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._
import scalajs.js.JSConverters._

trait Role {
  val loop: Loop
  implicit val ctx: ScreepsContext
  val ctxops = new ScreepsContext.ScreepsContextOps(ctx)
  import ctx._
  import ctxops._

  def run(creep: Creep): Boolean

  def selectSource(creep: Creep): Option[Source] = {
    val nearSource = creep.pos.findInRange[Source](FIND_SOURCES, 3)
    nearSource.headOption match {
      case Some(s) => Some(s)
      case None =>
        val rd = loop.roomData(creep.room)
        val sources = rd.sources.filter(_.positions > 0).map(_.source.get()).filter(_.pos.findInRange(FIND_HOSTILE_CREEPS, 5).size == 0)
        if (sources.size > 0)
          Some(creep.pos.findClosestByRangeFrom[Source](sources.toJSArray))
        else
          None
    }
  }
}