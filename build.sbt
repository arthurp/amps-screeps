// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

name := "Amps-Screeps"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

persistLauncher in Compile := true

persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
)


val packageJS = taskKey[File]("Package the Screeps JS.")
val packageFastJS = taskKey[File]("Package the Screeps JS (using fastOptJS version).")
val jsLauncher = taskKey[File]("The launcher script for Screeps scripts")

jsLauncher := baseDirectory.value / "screeps-launcher.js"

val upload = taskKey[Unit]("Upload Screeps JS to server.")

def packageJSCode(launcher: File, code: File, out: File): File = {
  println(s"Combining ${code.name} and ${launcher.name} into ${out.name}.")
  IO.write(out, IO.read(code) + IO.read(launcher))
  out
}

packageJS := {
  val code = (fullOptJS in Compile).value.data
  val out = target.value / "amps-screeps-package.js"
  packageJSCode(jsLauncher.value, code, out)
}

packageFastJS := {
  val code = (fastOptJS in Compile).value.data
  val out = target.value / "amps-screeps-fastpackage.js"
  packageJSCode(jsLauncher.value, code, out)
}

upload := {
}
