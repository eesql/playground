import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.FunSuite

/**
  * Created by elainetuang on 5/12/16.
  */
class TitanicSuite extends FunSuite {
  test("hello test") {
    assert( 1 == 1)

  }

  test("transform fields test") {
    val testString = "male"
    assert(TitanicPredict.transFields(testString) == "1")
  }

  test("spark context test") {
    val conf = new SparkConf().setAppName("TitanicPredictionExample")
    val sc   = new SparkContext(conf)

    sc.stop()
  }
}
