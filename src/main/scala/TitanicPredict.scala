import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vectors

/**
  * Created by chad on 4/9/16.
  */

object TitanicPredict  {

  //val logger = Logger(LoggerFactory.getLogger("name"))

  /**
    * Transform RDD of strings into RDD of labeled points
    * Make Sure Label(Target value) is the first field of input data
    *
    * @param data RDD contains delimited data
    * @param delimiter
    * @return RDD of transformed labeled point
    */
  def toLabeledPoint(data: RDD[String], delimiter: String): RDD[LabeledPoint] = {

    val d = data.map( _.split(delimiter) )
    d.map(
      r => LabeledPoint(
          r.head.toDouble, //Label value
          Vectors.dense( r.tail.map(_.toDouble) ) // Vector contains feature value
      )
    )
  }

  /**
    * swap first two fields, also remove header line
    *
    * @param data
    * @return data with swapped first and second field
    */
  def swapData(data: RDD[String]): RDD[Array[String]] = {
    val d = data.mapPartitionsWithIndex(
      (i, iterator) => if (i == 0 && iterator.hasNext) {
        iterator.next
        iterator
      } else iterator // remove first record
    ).map( _.split(","))

    d.map( _.grouped(2).flatMap( _.reverse).toArray )//.map( _.mkString(" ") )
  }

  def normData(data: RDD[Array[String]]): RDD[String] = {
    data.filter(_.length == 13).map( _.map( transFields(_) ) )
    .map(_.mkString(","))
  }

  def transFields(x: Any):String =  {
    val re = "^\\d+.*".r

    val r = x match {
      case "male" => "1"
      case "female" => "0"
      case re(_*) => x
      case r => "0"
    }
    r.toString
  }


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TitanicPredictionExample")
    val sc   = new SparkContext(conf)

    // Logback logger
    val logger = Logger(LoggerFactory.getLogger(this.getClass))

    val trainData = normData( swapData( sc.textFile("resources/train.csv") ) )
    //trainData.foreach(println(_))

    // Train RandomForest Model
    val numClasses = 2
    val numTrees = 3
    val featureSubsetStrategy = "auto"
    val impurity = "gini"
    val maxDepth = 2
    val maxBins = 32
    val cateFeaturesInfo = Map[Int, Int]()

    // transform original text data to Labeled Point Data
    val tData = toLabeledPoint(trainData, ",")

    // for testing
    val splits = tData.randomSplit(Array(0.8, 0.2))
    val (ttData, eData) = ( splits(0), splits(1) )
    val model = RandomForest.trainClassifier(ttData, numClasses, cateFeaturesInfo,
      numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

    // Evaluation process
    val labelAndPreds = eData.map{ point =>
      val prediction = model.predict(point.features)

      (point.label, prediction)
    }
    val testErr = labelAndPreds.filter(r => r._1 != r._2 ).count.toDouble / eData.count()

    println("#####################################Test Error = " + testErr)
    //logger.info("++++++++++++++++++++++++++++++++++++++++" + testErr.toString)
    //println("Learned classification forest model:\n" + model.toDebugString)

    model.save(sc, "target/tmp/titanicModel")
    sc.stop()
  }
}
