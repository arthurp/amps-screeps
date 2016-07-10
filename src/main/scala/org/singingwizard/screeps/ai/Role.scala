package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._

trait Role {
  def run(creep: Creep): Boolean
}