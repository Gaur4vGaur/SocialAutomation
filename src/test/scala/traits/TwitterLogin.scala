package traits

import org.openqa.selenium.Keys
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
    go to "http://www.twitter.com"
    for(i <- 0 until 3)
      pressKeys (Keys.chord(Keys.TAB))

    pressKeys (Keys.chord(Keys.ENTER))
    enter(username)
    pressKeys (Keys.chord(Keys.TAB))
    enter(password)
    submit()
  }

}
