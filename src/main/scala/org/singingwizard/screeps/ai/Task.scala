package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._
import prickle.CompositePickler
import prickle.PicklerPair

trait Task {
  /** Perform the actions of this task.
    */
  def run()(implicit ctx: AIContext): Unit

  /** True once the task is completed.
    */
  def state: Task.State
}

object Task {
  sealed trait State

  case object NeedCreep extends State
  case class Running(state: Int = 0) extends State
  case object Complete extends State
  case class Failed(reason: String) extends State

  object State {
    implicit val pickler = CompositePickler[State].concreteType[NeedCreep.type].concreteType[Running].concreteType[Complete.type].concreteType[Failed]
  }

  def pickler(taskTypes: TaskCompanion*): PicklerPair[Task] = {
    taskTypes.foldRight(CompositePickler[Task])(_.register(_))
  }
}

trait TaskCompanion {
  def register(pickler: PicklerPair[Task]): PicklerPair[Task]
}