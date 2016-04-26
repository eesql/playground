spark-submit \
	--conf "spark.executor.extraJavaOptions=-Dlogback.configurationFile=file:/tmp/logback.xml" \
	--class "TitanicPredict" \
	--master local[4]    \
	target/scala-2.11/playground-assembly-0.1-SNAPSHOT.jar
