val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "connect_four",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scoverage" % "sbt-scoverage_2.12_1.0" % "1.8.2"

  )
