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

def packageJSCode(name: String, launcher: File, code: File, out: File): File = {
	import java.util.Date
	
  println(s"Combining ${code.name} and ${launcher.name} into ${out.name}.")
  IO.write(out, s"// Generated from project ${name} at ${new Date().toString()}\n\n" + IO.read(code) + IO.read(launcher))
  out
}

packageJS := {
  val code = (fullOptJS in Compile).value.data
  val out = target.value / "amps-screeps-package.js"
  packageJSCode(name.value, jsLauncher.value, code, out)
}

packageFastJS := {
  val code = (fastOptJS in Compile).value.data
  val out = target.value / "amps-screeps-fastpackage.js"
  packageJSCode(name.value, jsLauncher.value, code, out)
}

val screepsUsername = taskKey[String]("Screeps username.")
val screepsPassword = taskKey[String]("Screeps password.")

upload := {
  import org.apache.commons.codec.binary.Base64
  import java.io._
  import java.net.HttpURLConnection

  val url = "https://screeps.com/api/user/code"
  val connection = new URL(url).openConnection()
  connection.setDoOutput(true)
  connection.setDoInput(true)
  val authToken = Base64.encodeBase64String((screepsUsername.value+":"+screepsPassword.value).getBytes())
  connection.setRequestProperty("Authorization", "Basic "+authToken)
  connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")

  val program = IO.read(packageFastJS.value)
  val quotedProgram = program.replace("\\", "\\\\").replace("\n", "\\n").replace("\"", "\\\"")

  val output = connection.getOutputStream()
  val writer = new PrintWriter(new OutputStreamWriter(output), true)
  try {
    writer.append(s"""{"branch":"sbt-upload","modules":{"main":"$quotedProgram"}}""")
  } finally {
    writer.close()
    output.close()
  }

  val input = connection.getInputStream()
  val reader = new BufferedReader(new InputStreamReader(input))
  try {
    println(reader.readLine())
  } finally {
    writer.close()
    output.close()
  }
  println(connection.asInstanceOf[HttpURLConnection].getResponseCode())
  println(connection.asInstanceOf[HttpURLConnection].getResponseMessage())
}
