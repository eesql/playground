/**
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by chad on 3/31/16.
  */
private object HBaseLoader {

  def getHconfig(port: String, quorum: String): Configuration = {

    val configuration = HBaseConfiguration.create()

    configuration.set("hbase.zookeeper.property.clientPort", port);
    //设置zookeeper client端口

    configuration.set("hbase.zookeeper.quorum", quorum);
    //设置zookeeper quorum

    configuration

  }

  def getHRDD(sc: SparkContext, conf: HBaseConfiguration): RDD[(ImmutableBytesWritable, Result)] = {

    val resultRDD = sc.newAPIHadoopRDD(conf, classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])

    resultRDD
  }
}

  */