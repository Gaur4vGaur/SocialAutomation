package rtw

import org.scalatest.FlatSpec
import util.RtwConstants._
import _root_.util.Encryption.decrypt
import rtw.util.TumblrPost._
import traits.TumblrPoster
import rtw.util.ReadingRtwFileNew
import _root_.util.Properties

/**
  * Created by gaurav on 31/01/17.
  */
class TumblrRtwSpec extends FlatSpec with TumblrPoster {

  override val username = decrypt(RTW_TUMBLR_USERNAME)
  override val password = decrypt(RTW_PASSWORD)
  private val fileName = Properties.value("TUMBLR_RTW")
  private val cutOff = tumblrPost.size

  "The test" should "post text on tumblr" in {

    val post = () => {
      val rtw = ReadingRtwFileNew(fileName, cutOff)
      val key = rtw.fetchKeyFromFile
      println(key)
      //No defensive coding because we are pretty much sure that the map has values.
      if(tumblrPost(key).contains(WEBSITE))
        postTextOnTumblr(key)
      else
        postOnLinkOnTumblr(key)

      rtw.writeToFile(key+1)
    }

    post.apply()
    close
  }

}
