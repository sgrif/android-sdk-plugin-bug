scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.google.android" % "android" % "4.1.1.4" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

scalacOptions ++= Seq("-feature", "-deprecation")
