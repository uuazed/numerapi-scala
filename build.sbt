scalaVersion := "2.13.1"
name := "numerapi"
organization := "uuazed"
version := "0.1"

resolvers += Resolver.bintrayRepo("americanexpress", "maven")
libraryDependencies += "io.aexp.nodes.graphql" % "nodes" % "0.5.0"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.4"
