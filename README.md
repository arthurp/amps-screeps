# Amp's Screeps
Some [Screeps](https://screeps.com/) stuff written in Scala.JS.

The Scala.JS integration code (in org.singingwizard.screeps.api and in build.sbt) may be useful for other people with interest in this.

The AI itself (org.singingwizard.screeps.api) is based on the idea of global tasks which are assigned to particular creeps.
What tasks need to be done is globally decided by the system.
The individual tasks then give commands to the creep(s) assigned to the task.
