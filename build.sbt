name := """konrad-city"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  "org.mockito" % "mockito-all" % "1.9.5",
  cache,
  javaWs
)
