package traits

import util.Constants._
import org.scalatest.Matchers
import org.scalatest.selenium.Firefox

/**
  * Created by gaurav on 25/01/17.
  */
trait TwitterLogin extends Matchers with Firefox {

  val username: String
  val password: String

  def twitterLogin(): Unit = {
    go to TWITTER_LOGIN
    click on xpath("//*[@id=\"page-container\"]/div[2]/div[1]/form/fieldset/div[1]/input")
    enter(username)
    click on xpath("//*[@id=\"page-container\"]/div[2]/div[1]/form/fieldset/div[2]/input")
    enter(password)
    submit()
  }

}
