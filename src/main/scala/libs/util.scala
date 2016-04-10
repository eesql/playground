package libs

import scala.io.Source

/**
  * Created by elainetuang on 3/31/16.
  */

object Util {

  def getFileConfig(): scala.collection.mutable.Map[String,String] = {

    val config = scala.collection.mutable.Map[String, String]()

    try {
      val file = Source.fromFile("resources/ultra-env.sh")

      for (line <- file.getLines()) {
        if (! line.startsWith("#")) {
          val ll = line.split("=")
          config += (ll(0) -> ll(1))
        }
      }

      for ( (k,v) <- config ) printf("k:%s v:%s\n", k, v)
      config
    }
  }
}