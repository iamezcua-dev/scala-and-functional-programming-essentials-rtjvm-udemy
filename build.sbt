name := "rock-the-jvm-scala-and-functional-programming-for-beginners"
version := "0.1"
scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
	"com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
	"org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.12.1",
	"org.scalactic" %% "scalactic" % "3.0.8",
	"org.scalatest" %% "scalatest" % "3.0.8" % "test"
)
