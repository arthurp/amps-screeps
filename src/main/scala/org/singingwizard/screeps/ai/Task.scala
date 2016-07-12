package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._

trait Task {
  /** Compute the cost of running this task using creep.
    * The return value is very roughly in ticks it will take.
    *
    * Returns Int.MaxValue if creep cannot perform this task.
    */
  def costFor(creep: Creep)(implicit ctx: ScreepsContext): Int

  /** The normalized vector for the ideal creep to do this task.
    */
  val idealCreep: Option[CreepBuildVector]

  /** Start running this task with creep.
    *
    * @throws IllegalArgumentException if the creep cannot perform this task.
    */
  def performWith(creep: Creep)(implicit ctx: ScreepsContext): RunningTask
}

trait RunningTask {
  /** The task running
    */
  val task: Task
  
  /** The creep used for this running task.
    */
  val creep: Creep

  /** Perform the task.
    */
  def run(): Unit

  /** True once the task is completed.
    */
  def state(implicit ctx: ScreepsContext): RunningTask.State

  override def toString(): String = {
    s"$task with $creep"
  }
}

object RunningTask {
  sealed trait State

  case class Running(remainingCost: Option[Int]) extends State
  case object Complete extends State
  case class Failed(reason: String) extends State
}