// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

name := "Amps-Screeps"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

//persistLauncher in Compile := true

//persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.3.0" % "test",
    "com.github.benhutchison" %%% "prickle" % "1.1.10"
)

scalacOptions ++= Seq("-feature")
