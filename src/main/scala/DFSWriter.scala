import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}



/**
  * Created by elainetuang on 4/19/16.
  */
object DFSWriter {

  def main(args: Array[String]):Unit = {
    val inURI = new Path(args(0))
    val outURI = new Path(args(1))

    val conf = new Configuration()
    conf.set("fs.default.name", "hdfs://bigdata2:8020")

    conf.set("dfs.client.use.datanode.hostname", "true")

    conf.set("dfs.datanode.use.datanode.hostname", "false")

    val fs = FileSystem.get(conf)
    fs.copyFromLocalFile(false,true,inURI,outURI)

    fs.close()
  }



}
