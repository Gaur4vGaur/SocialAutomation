package rtw

import org.scalatest.FlatSpec
import util.RtwConstants._
import _root_.util.Encryption.decrypt
import _root_.util.Encryption.encrypt
import rtw.util.TumblrPost
import traits.TumblrPoster

/**
  * Created by gaurav on 31/01/17.
  */
class TumblrRtwSpec extends FlatSpec with TumblrPoster {

  override val username = decrypt(RTW_TUMBLR_USERNAME)
  override val password = decrypt(RTW_PASSWORD)

  "The testcase" should "able to log in" in {
    val tumblrPost = () => {
      val post = TumblrPost.CHOOSE_TRAVEL_ROUTE
      postLink(post)
    }

    postOnTumblr(tumblrPost)
    Thread.sleep(5000)
    close
  }

}
