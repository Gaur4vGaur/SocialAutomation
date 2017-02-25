package traits

import org.openqa.selenium.Keys
import rtw.util.TumblrPost._

trait TumblrPoster extends TumblrLogin {

  private val POST_LINK = "https://www.tumblr.com/new/link"
  private val TEXT_LINK = "https://www.tumblr.com/new/text"

  val SPECIAL_KEYSET = List(34,35,36,37)

  def postTextOnTumblr(key: Int): Unit = {

    val link = tumblrPost(key)

    def readRtwSpecialPostSite: Array[String] = {
      go to link
      val element = find(className("post"))
      val text = element.get.text.split("\n")

      var isProcessing = true
      val canProcess = (text: String, process:Boolean) => {
        isProcessing = (process && !text.contains("Plan your Travel – Visit"))
        isProcessing
      }

      text.filter(canProcess(_, isProcessing))
    }

    def readRtwSite: Array[String] = {
      go to link
      val element = find(className("left-column"))
      element.get.text.split("\n")
    }

    def openTextEditor {
      go to (TEXT_LINK)
      Thread.sleep(5000)
    }

    def enterTitleOfBlog(title: String): Unit = {
      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div/div[1]")
      pressKeys (title)

      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[3]/div[1]/div/div[1]")
      pressKeys ("originally on: www.ticketsroundtheworld.com")
      pressKeys (Keys.chord(Keys.ENTER))
    }

    def writeBlog(blog: Array[String]): Unit = {
      var isheadingOn = false
      for((text,index) <- blog.zipWithIndex if index > 1) {
        text.length match {
          case 0 =>
          case heading if heading < 80 =>
            isheadingOn = true
            pressKeys (Keys.chord(Keys.CONTROL, "b"))
            pressKeys (text)
            pressKeys (Keys.chord(Keys.ENTER))
          case _ =>
            if(isheadingOn) pressKeys (Keys.chord(Keys.CONTROL, "b"))
            val array = text.split(" ")
            array.foreach(t => pressKeys((t + " ")))
            pressKeys (Keys.chord(Keys.ENTER))
            isheadingOn = false
        }
      }
    }

    val content = if(SPECIAL_KEYSET.contains(key)) readRtwSpecialPostSite else readRtwSite
    tumblrLogin
    openTextEditor
    enterTitleOfBlog(content(0))
    writeBlog(content)
    postWithHashTags
    Thread.sleep(5000)
  }

  def postOnLinkOnTumblr(key: Int): Unit = {

    val link = tumblrPost(key)
    val description = tumblrPostDescriptions(key)

    def postLink(): Unit = {
      go to POST_LINK

      //click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]")
      pressKeys (link)
      Thread.sleep (6000)
      pressKeys (description)

      postWithHashTags
    }

    tumblrLogin()
    postLink()
    Thread.sleep(5000)
  }

  private def postWithHashTags {
    pressKeys (Keys.chord(Keys.TAB))
    TUMBLR_HASH_TAGS.split(" ").foreach(pressKeys)
    pressKeys (Keys.chord(Keys.TAB))
    pressKeys (Keys.chord(Keys.ENTER))
  }

}
