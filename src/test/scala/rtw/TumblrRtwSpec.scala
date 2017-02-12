package rtw

import org.scalatest.FlatSpec
import util.RtwConstants._
import _root_.util.Encryption.decrypt
import _root_.util.Encryption.encrypt
import traits.TumblrLogin

/**
  * Created by gaurav on 31/01/17.
  */
class TumblrRtwSpec extends FlatSpec with TumblrLogin {

  override val username = decrypt(RTW_TUMBLR_USERNAME)
  override val password = decrypt(RTW_PASSWORD)

  "The testcase" should "able to log in" in {
    tumblrLogin()

    go to "https://www.tumblr.com/new/link"

    click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]")
    pressKeys("https://www.ticketsroundtheworld.com/trip-planner.php".stripMargin)
    Thread.sleep(5000)
    pressKeys("Around the world travel description".stripMargin)
    click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[3]/div[1]/div/div[1]/div/div/div[1]")
    pressKeys("Travel, RTW, Around the world travel, ticketsroundtheworld, ticketsroundtheworld.com,")
    click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[5]/div[1]/div/div[3]/div/div/button")
    Thread.sleep(5000)
    close
  }

}
