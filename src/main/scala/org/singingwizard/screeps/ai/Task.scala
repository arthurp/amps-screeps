package org.singingwizard.screeps.ai

import org.singingwizard.screeps.api._
import prickle.CompositePickler

trait Task {
  /** Compute the cost of running this task using creep.
    * The return value is very roughly in ticks it will take.
    *
    * @return an approximate number of ticks to perform the task
    *         with creep, or Int.MaxValue if creep cannot perform 
    *         this task.
    */
  def costFor(creep: Creep)(implicit ctx: AIContext): Int

  /** The normalized vector for the ideal creep to do this task.
    */
  val idealCreep: Option[CreepBuildVector]

  /** Start running this task with `creep`.
    *
    * @throws IllegalArgumentException if the creep cannot perform this task.
    */
  def assignCreep(creep: Creep)(implicit ctx: AIContext): Unit

  /** Perform the actions of this task.
    */
  def run()(implicit ctx: AIContext): Unit

  /** The creep used for this running task.
    */
  val creep: Creep

  /** True once the task is completed.
    */
  def state: Task.State
}

object Task {
  sealed trait State

  case class Running(remainingCost: Option[Int]) extends State
  case object Complete extends State
  case class Failed(reason: String) extends State
  
  implicit val pickler = CompositePickler[Task] 
}