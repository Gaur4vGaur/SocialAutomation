package traits

import org.openqa.selenium.Keys
import rtw.util.TumblrPost._

trait TumblrPoster extends TumblrLogin {

  private val POST_LINK = "https://www.tumblr.com/new/link"
  private val TEXT_LINK = "https://www.tumblr.com/new/text"

  val SPECIAL_KEYSET = List(34,35,36,37)

  def postTextOnTumblr(link: String, key: Int): Unit = {

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
      for((text,index) <- blog.zipWithIndex if index > 1 && index < 5) {
        text.length match {
          case 0 =>
          case heading if heading < 100 =>
            pressKeys (Keys.chord(Keys.CONTROL, "b"))
            isheadingOn = true
            pressKeys (text)
            pressKeys (Keys.chord(Keys.ENTER))
          case _ =>
            if(isheadingOn) pressKeys (Keys.chord(Keys.CONTROL, "b"))
            val array = text.split(" ")
            array.foreach(t => pressKeys((t + " ")))
            pressKeys (Keys.chord(Keys.ENTER))
        }
      }
    }

    def postWithHashTags {
      pressKeys (Keys.chord(Keys.TAB))
      TUMBLR_HASH_TAGS.split(" ").foreach(pressKeys)
      pressKeys (Keys.chord(Keys.TAB))
      pressKeys (Keys.chord(Keys.ENTER))
    }

    val content = if(SPECIAL_KEYSET.contains(key)) readRtwSpecialPostSite else readRtwSite
    tumblrLogin
    openTextEditor
    enterTitleOfBlog(content(0))
    writeBlog(content)
    postWithHashTags
    Thread.sleep(5000)
  }



  def postLink(post: (String, String)): Unit = {
    val (link, description) = post
    go to POST_LINK

    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]")
    pressKeys (link)
    Thread.sleep (6000)
    pressKeys (description)

    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[3]/div[1]/div/div[1]/div/div/div[1]")
    pressKeys (TUMBLR_HASH_TAGS)
    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[5]/div[1]/div/div[3]/div/div/button")
  }

  def postOnTumblr(post: () => Unit): Unit = {
    tumblrLogin()
    post.apply
  }

}
