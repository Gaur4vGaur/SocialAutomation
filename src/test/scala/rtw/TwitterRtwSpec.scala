package rtw

import org.scalatest.FlatSpec
import traits.TwitterTweet
import _root_.util.Encryption.decrypt
import rtw.util.Tweets._
import util.RtwConstants._
import ReadingRtwFile._

class TwitterRtwSpec extends FlatSpec with TwitterTweet {

  override val username: String = decrypt(RTW_USERNAME)
  override val password: String = decrypt(RTW_PASSWORD)

  "Twitter Login" should "tweet RTW tweets" in {

    val tweet = () => {
      val key = fetchKeyFromFile
      println(key)
      val tweet = rtwTweets(key)
      tweetMe(tweet)
      writeToFile(key+1, rtwTweets.size)
    }

    tweetFlow(tweet)
    close()
  }

}
object ReadingRtwFile {
  import _root_.util.Properties._
  import scala.io.Source

  val defaultFileName = value("RTW")

  def fetchKeyFromFile: Int = {
    val source = Source.fromFile(defaultFileName)
    try {
      source.mkString.toInt
    } catch {
      case e: Exception =>
        println("\n\nKey does not exist to fetch the tweet")
        throw e
    } finally {
      source close
    }
  }

  import java.io._

  def writeToFile(data: Int, cutOff: Int): Unit = {
    val pw = new PrintWriter(new File(defaultFileName))
    try {
      pw.write((if (data > cutOff) 1 else data).toString)
    } catch {
      case e: Exception =>
        println("\n\nKey does not exist to fetch the tweet")
        throw e
    } finally {
      pw.close
    }
  }
}