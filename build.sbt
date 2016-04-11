name := "playground" // project name
version := "0.1-SNAPSHOT"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0" % "provided"
libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.1.0"
libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.1.0"
libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.1.0"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.6.0" % "provided"


assemblyMergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".dtd" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xsd" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
