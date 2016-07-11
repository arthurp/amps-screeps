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

  def selectSource(creep: Creep) = {
    val nearSource = creep.pos.findInRange[Source](FIND_SOURCES, 3)
    nearSource.headOption match {
      case Some(s) => s
      case None =>
        val rd = loop.roomData(creep.room)
        val sources = rd.sources.filter(_.positions > 0).map(_.source.get())
        creep.pos.findClosestByRangeFrom[Source](sources.toJSArray)
    }
  }
}