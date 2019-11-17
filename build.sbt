scalaVersion := "2.13.1"
name := "numerapi"
organization := "uuazed"
version := "0.1"

resolvers += Resolver.bintrayRepo("americanexpress", "maven")
libraryDependencies += "io.aexp.nodes.graphql" % "nodes" % "0.5.0"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
