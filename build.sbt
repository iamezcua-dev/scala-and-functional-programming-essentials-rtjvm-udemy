ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "com.rockthejvm"

Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / "src" / "test" / "scala"

lazy val root = (project in file("."))
  .settings(
    name := "scala_and_functional_programming_essentials_rock_the_jvm_udemy",
    idePackagePrefix := Some("com.rockthejvm"),
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.20.0",
      "org.scalactic" %% "scalactic" % "3.2.15",
      "org.scalatest" %% "scalatest" % "3.2.15" % "test"
    )
  )
