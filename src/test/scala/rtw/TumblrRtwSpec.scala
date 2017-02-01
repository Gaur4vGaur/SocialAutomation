package rtw

import org.scalatest.FlatSpec
import org.scalatest.selenium.Firefox
import _root_.util.Constants._
import _root_.util.Encryption.decrypt

/**
  * Created by gaurav on 31/01/17.
  */
class TumblrRtwSpec extends FlatSpec with Firefox {

  val url = "https://www.tumblr.com/login?from_splash=1"
  val username = decrypt(OLD_USERNAME)
  val password = decrypt(PASSWORDS)

  "The testcase" should "able to log in" in {
    go to url
    click on "signup_determine_email"
    enter(username)
    click on "signup_forms_submit"

    Thread.sleep(5000)

    click on xpath("//*[@id=\"signup_password\"]")
    enter(password)
    click on "signup_forms_submit"

    go to "https://www.tumblr.com/new/link"
  }

}
