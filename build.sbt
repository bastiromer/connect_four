val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "connect_four",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0").cross(CrossVersion.for3Use2_13),
)
