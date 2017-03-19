import util.Constants.{PASSWORDS, USERNAME}
import util.Encryption.decrypt
import org.scalatest.selenium.Firefox
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by gaurav on 25/01/17.
  */
/*
class FacebookSpec extends FlatSpec with Matchers with Firefox {

  val username = decrypt(USERNAME)
  val password = decrypt(PASSWORDS)

  "FB Log in method " should "log into facebook" in {

    val post = () => {
      go to "http://www.facebook.com"
      click on "email"
      enter(username)

      click on "pass"
      enter(password)

      submit()
    }

    // by default browser will open we need to close even when we are not doing anything
    close()

    //postFlow(post)
  }

  // we can abstract it out like twitter
  def postFlow(post: () => Unit): Unit = {
    post.apply
  }
}
*/
