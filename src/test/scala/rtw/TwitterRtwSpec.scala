package rtw

import org.scalatest.FlatSpec
import traits.TwitterTweet
import _root_.util.Encryption.decrypt
import rtw.util.Tweets.{rtwTweets, _}
import util.RtwConstants._
import rtw.util.ReadingRtwFileNew
import _root_.util.Properties

class TwitterRtwSpec extends FlatSpec {

  /*override val username: String = decrypt(RTW_USERNAME)
  override val password: String = decrypt(RTW_PASSWORD)*/
  private val fileName = Properties.value("RTW")
  private val cutOff = rtwTweets.size

  "Twitter Login" should "tweet RTW tweets" in {
    rtwTweets.foreach {
      rtwEntry => {
        val (k, v) = rtwEntry
        if(v.contains("www.ticketsroundtheworld.com")) {
          val split = v.split("https://")
          println(k + "  -  " + split.last + "\n")
        }

      }
    }
  }

  /*"Twitter Login" should "tweet RTW tweets" in {

    val tweet = () => {
      val rtw = ReadingRtwFileNew(fileName, cutOff)
      val key = rtw.fetchKeyFromFile
      println(key)
      val tweet = rtwTweets(key)
      tweetMe(tweet)
      rtw.writeToFile(key+1)
    }

    tweetFlow(tweet)
    close()
  }*/

}
