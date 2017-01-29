package util

import com.typesafe.config.ConfigFactory

/**
  * Created by gaurav on 28/01/17.
  */
object Properties {

  val conf = ConfigFactory.load
  val value = (key: String) => conf.getString(key)

}
