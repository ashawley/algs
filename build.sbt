scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test"
)

scalariformSettings
