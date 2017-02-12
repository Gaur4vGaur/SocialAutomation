package traits

import org.scalatest.Matchers
import org.scalatest.selenium.Firefox
import _root_.util.Constants._

/**
  * Created by gaurav on 12/02/17.
  */
trait TumblrLogin extends Matchers with Firefox {

  val username: String
  val password: String

  protected def tumblrLogin() = {
    go to TUMBLR_LOGIN
    click on "signup_determine_email"
    enter(username)
    click on "signup_forms_submit"

    Thread.sleep(5000)

    click on xpath("//*[@id=\"signup_password\"]")
    enter(password)
    click on "signup_forms_submit"
  }
}
