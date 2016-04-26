name := "playground" // project name
version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io"
  //"Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

/**
resolvers ++= Seq(
  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/"
)*/

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0" 
//libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.1.0"
//libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.1.0"
//libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.1.0"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.6.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "net.liftweb" %% "lift-json" % "2.6.2"


assemblyMergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".dtd" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xsd" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
