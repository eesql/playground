spark-submit \
	--class "TitanicPredict" \
	--master local[4]    \
	target/scala-2.10/playground-assembly-0.1-SNAPSHOT.jar
