package util

import Properties._

/**
  * Created by gaurav on 21/01/17.
  */
object Constants {

  val USERNAME: String = value("USERNAME")
  val OLD_USERNAME: String = value("OLD_USERNAME")
  val PASSWORDS: String = value("PASSWORDS")

  // Driver
  val WEBDRIVER: String = value("WEBDRIVER")
  val FIREFOX: String = value("FIREFOX")

  // Encryption constants
  val KEY: String = value("KEY")
  val SALT: String = value("SALT")

  // URLs
  val TWITTER_LOGIN: String = value("TWITTER_LOGIN")
}
