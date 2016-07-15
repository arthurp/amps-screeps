package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._
import scalajs.js
import scalajs.js.JSConverters._
import ScreepsContext._

case class CreepBuildVector(
    move: Double = 1, work: Double = 0, carry: Double = 0,
    attack: Double = 0, rangedAttack: Double = 0, heal: Double = 0,
    claim: Double = 0, tough: Double = 0) {
  private def valueMapping = {
    js.Array(TOUGH -> tough, WORK -> work, CARRY -> carry,
      ATTACK -> attack, RANGED_ATTACK -> rangedAttack, HEAL -> heal,
      CLAIM -> claim, MOVE -> move)
  }

  def size = move + work + carry + attack + rangedAttack + heal + claim + tough

  def normalized = {
    CreepBuildVector(
      move / size, work / size, carry / size, attack / size, rangedAttack / size,
      heal / size, claim / size, tough / size)
  }
  
  def scaledToSize(n: Int) = {
    val factor = n.toDouble / size
    CreepBuildVector(
      move / factor, work / factor, carry / factor, attack / factor, rangedAttack / factor,
      heal / factor, claim / factor, tough / factor)    
  }
  
  def scaledToCost(c: Int) = {
    val factor = c.toDouble / cost
    CreepBuildVector(
      move / factor, work / factor, carry / factor, attack / factor, rangedAttack / factor,
      heal / factor, claim / factor, tough / factor) 
  }

  def cost: Double = {
    (for ((n, v) <- valueMapping) yield v * BODYPART_COST(n)).sum
  }

  // TODO: It may be useful to allow intermixing of parts to control damage.
  def toCreepDefinition: js.Array[String] = {
    (for ((n, v) <- valueMapping) yield Seq.fill(v.toInt)(n)).flatten.toJSArray
  }
}

object CreepBuildVector {
  def fromCreepDefinition(body: js.Array[String]) = {
    val counts = body.groupBy(x => x).mapValues(_.size)
    CreepBuildVector(counts(MOVE), counts(WORK), counts(CARRY),
      counts(ATTACK), counts(RANGED_ATTACK), counts(HEAL),
      counts(CLAIM), counts(TOUGH))
  }
}