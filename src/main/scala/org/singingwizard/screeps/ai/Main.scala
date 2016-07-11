package org.singingwizard.screeps.ai

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.singingwizard.screeps.api._

object Main extends js.JSApp {
  def main(): Unit = {
    println("This should not be called")
  }

  @JSExport
  def getLoop(ctx: ScreepsContext): js.Function0[Unit] = {
    val loop = new Loop()(ctx)
    () => {
      try {
        loop.loop()
      } catch {
        case e: Throwable => 
          ctx.Console.log(s"Exception while running loop: $e")
      }
      ()
    }
  }
}
