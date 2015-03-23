scalaVersion := "2.11.6"

scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

resolvers += "jcenter" at "http://jcenter.bintray.com"

libraryDependencies ++= Seq(
  "com.android.support" % "multidex" % "1.0.0",
  "com.squareup.okhttp" % "okhttp" % "2.3.0",
  "com.android.support.test.espresso" % "espresso-core" % "2.0",
  "com.squareup.okhttp" % "mockwebserver" % "2.3.0",
  "org.macroid" %% "macroid" % "2.0.0-M3"
)

apkbuildExcludes in Android += "LICENSE.txt"

useProguard in Android := false

useProguardInDebug in Android := false

proguardScala in Android := false

dexMulti in Android := true

dexMinimizeMainFile in Android := false

dexMaxHeap in Android := "2g"

instrumentTestRunner in Android := "android.support.test.runner.AndroidJUnitRunner"

// enable improved (experimental) incremental compilation algorithm called "name hashing"
incOptions := incOptions.value.withNameHashing(true)

lazy val core = Project(
  id = "com-obsidian",
  base = file("."),
  settings = Defaults.defaultSettings ++ android.Plugin.androidBuild
)

lazy val unitTests = project in file("unit-tests") dependsOn core
