package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._
import org.singingwizard.screeps.api.ScreepsContext._
import prickle.CompositePickler
import prickle.PicklerPair
import org.singingwizard.screeps.ai.tasks._

trait Task {
  /** Execute the task.
    */
  def run()(implicit ctx: AIContext): Unit
  
  def reschedule()(implicit ctx: AIContext) = {
    ctx.schedule(this)
  }
  
  def fail(msg: String) = {
    Console.log(s"Task '$this' failed with: $msg")
  }
}

object Task {  
  implicit val taskPickler = Task.pickler(GetEnergy, SpawnCreep, TakeEnergyTo)
  
  def pickler(taskTypes: TaskCompanion*): PicklerPair[Task] = {
    taskTypes.foldRight(CompositePickler[Task])(_.register(_))
  }
}

trait TaskCompanion {
  def register(pickler: PicklerPair[Task]): PicklerPair[Task]
}

trait TaskWithContinuation extends Task {
  def continuation: TraversableOnce[Task]
  
  def finish()(implicit ctx: AIContext) = {
    continuation.map(ctx.schedule(_))
  }
}