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
val upload = taskKey[Unit]("Upload Screeps JS to server.")

packageJS := {
  val code = (fullOptJS in Compile).value.data
  val launcher = baseDirectory.value / "screeps-launcher.js"
  val out = target.value / "amps-screeps-package.js"
  println(s"Combining $code and $launcher into $out.")
  IO.write(out, IO.read(code) + IO.read(launcher))
  out
}

upload := {
}
