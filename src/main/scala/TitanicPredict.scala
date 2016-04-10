import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.RandomForest

/**
  * Created by chad on 4/9/16.
  */

object TitanicPredict {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TitanicPredictionExample")
    val sc   = new SparkContext(conf)

    val trainData = sc.textFile("~/Documents/MOOC/train.csv")

    // Train RandomForest Model
    val numClasses = 2
    val numTrees = 3
    val featureSubsetStrategy = "auto"
    val impurity = "gini"
    val maxDepth = 2
    val maxBins = 32
    val cateFeaturesInfo = Map[Int, Int]()


    //val model = RandomForest


  }
}
