import com.typesafe.scalalogging.LazyLogging

/**
	* import org.apache.spark._
	* import org.apache.hadoop.conf.Configuration
*import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
*import org.apache.hadoop.hbase.mapreduce.TableInputFormat
*import org.apache.hadoop.hbase.client.{ConnectionFactory, Get, HBaseAdmin}
*import org.apache.hadoop.hbase.util.Bytes
*import org.apache.spark.SparkContext
*import org.apache.spark.SparkContext._
*import org.apache.spark.SparkConf
 **
 *
 *object HelloWorld {
	*def main(args: Array[String]) {
		*println("Hello,SBT")
 **
 *
  * Initialize spark context
		*val conf = new SparkConf().setAppName("HelloSpark")
		*val sc = new SparkContext(conf)
 **
 *val configuration = HBaseConfiguration.create();
 **
 *configuration.set("hbase.zookeeper.property.clientPort", "2181");
  * 设置zookeeper client端口
 **
 *configuration.set("hbase.zookeeper.quorum", "172.19.0.5");
		*设置zookeeper quorum
 **
 *configuration.set("hbase.master", "172.19.0.5:60000");
		*设置hbase master
 **
 *configuration.set(TableInputFormat.INPUT_TABLE, "user")
 **
 *val usersRDD = sc.newAPIHadoopRDD(configuration, classOf[TableInputFormat],
  								*classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
  								*classOf[org.apache.hadoop.hbase.client.Result])
 **
 *
 *
 ****
			* spark read hbase
 **
 *val conn = ConnectionFactory.createConnection(configuration)
 **
 *val userTable = TableName.valueOf("user")
		*val table = conn.getTable(userTable)
 **
 *try {
			*val g = new Get("id001".getBytes)
			*val result  = table.get(g)
			*println("GET : "+Bytes.toString(result.getValue("basic".getBytes, "name".getBytes)))
		*}
  *
  **
 *val count = usersRDD.count()
	* println("Users RDD Count:" + count)
	*}
*}
*/

object LogTest extends LazyLogging {
  def main(args: Array[String]): Unit = {
    logger.info("starting....")
  }
}