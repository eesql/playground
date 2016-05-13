import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.FunSuite
import TitanicPredict.normData
import TitanicPredict.swapData

/**
  * Created by elainetuang on 5/12/16.
  */
class TitanicSuite extends FunSuite {
  test("hello test") {
    assert( 1 == 1)

  }

  test("transform fields test") {
    val testString = "male"
    assert(TitanicPredict.transFields(testString) == "0")
  }

  test("spark context test") {
    val conf = new SparkConf().setAppName("TitanicPredictionExample")
    conf.setMaster("local[2]")
    val sc   = new SparkContext(conf)

    val trainData = normData( swapData( sc.textFile("resources/train.csv") ) )

    assert( trainData.count() == 10000 )

    sc.stop()
  }
}
