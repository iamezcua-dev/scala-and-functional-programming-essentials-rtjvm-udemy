ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "3.3.3"
ThisBuild / organization := "com.rockthejvm"

val scalacticVersion = "3.2.19"
val scalaTestVersion = "3.2.19"
val scalaLoggingVersion = "3.9.5"
val log4jVersion = "2.24.0"

Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / "src" / "test" / "scala"

lazy val root = (project in file("."))
  .settings(
    name := "scala-and-functional-programming-essentials-rtjvm-udemy",
    idePackagePrefix := Some("com.rockthejvm"),
    Global / excludeLintKeys += idePackagePrefix,
    libraryDependencies ++= Seq(
      // Logging
      "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
      "org.apache.logging.log4j" % "log4j-slf4j2-impl" % log4jVersion,
      
      // Testing
      "org.scalatest" %% "scalatest" % scalaTestVersion,
      "org.scalactic" %% "scalactic" % scalacticVersion,
      "org.scalactic" %% "scalactic" % scalacticVersion % Test
    )
  )
