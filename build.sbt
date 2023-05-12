val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "connect_four",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    //libraryDependencies += "org.scoverage" % "sbt-scoverage_2.12_1.0" % "1.8.2",
    jacocoCoverallsServiceName :="github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
)
    .enablePlugins(JacocoPlugin)

