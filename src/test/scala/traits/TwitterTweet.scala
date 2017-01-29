package traits

import org.scalatest.selenium.WebBrowser.click

/**
  * Created by gaurav on 28/01/17.
  */
trait TwitterTweet extends TwitterLogin {

  def tweetMe(tweet: String): Unit = {
    click on "tweet-box-home-timeline"
    pressKeys(tweet)
    click on xpath("//*[@id=\"timeline\"]/div[2]/div/form/div[2]/div[2]/button")
    Thread.sleep(5000)
  }

  def tweetFlow(tweet: () => Unit): Unit = {
    twitterLogin()
    tweet.apply
    close()
  }

}
