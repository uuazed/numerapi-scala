scalaVersion := "2.13.4"

name := "numerapi"

organization := "com.github.uuazed"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

publishTo := sonatypePublishToBundle.value
