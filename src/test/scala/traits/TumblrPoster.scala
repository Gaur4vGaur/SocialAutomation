package traits

import org.openqa.selenium.Keys
import rtw.util.TumblrPost._

trait TumblrPoster extends TumblrLogin {

  private val POST_LINK = "https://www.tumblr.com/new/link"
  private val TEXT_LINK = "https://www.tumblr.com/new/text"
  private val PHOTO_LINK = "https://www.tumblr.com/new/photo"

  val SPECIAL_KEYSET = List(34,35,36,37)

  def postTextOnTumblr(key: Int): Unit = {

    val link: String = tumblrPost(key)

    def openTextEditor {
      go to (TEXT_LINK)
      Thread.sleep(5000)
    }

    def enterTitleOfBlog(title: String): Unit = {
      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div/div[1]")
      pressKeys (title)
    }

    val content = if(SPECIAL_KEYSET.contains(key)) readRtwSpecialPostSite(link) else readRtwSite(link)
    tumblrLogin
    openTextEditor
    writeBlogContent(content)
    enterTitleOfBlog(content(0))
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
      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/span[2]")
      postWithHashTags
    }

    tumblrLogin()
    postLink()
    Thread.sleep(5000)
  }

  def postPhotoOnTumblr(key: Int): Unit = {

    val link: String = tumblrPost(key)

    def openPhotoEditor {
      go to (PHOTO_LINK)
      Thread.sleep(2000)

      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div[1]/div/div/div[1]")
      pressKeys ("https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/17265092_1486544738022551_7887586187099208037_n.jpg?oh=24ed020790f18fdfbf93b020637883af&oe=59615FF0")
      pressKeys (Keys.chord(Keys.ENTER))
      Thread.sleep(4000)
    }

    def writeBlog(blog: Array[String]): Unit = {
      pressKeys ("## **" + blog(0) + "**")
      changeTumblrPara
      writeBlogContent(blog)

      click on xpath("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div[1]/div/div/div[1]")
    }

    val content = if(SPECIAL_KEYSET.contains(key)) readRtwSpecialPostSite(link) else readRtwSite(link)
    tumblrLogin
    openPhotoEditor
    writeBlog(content)
    postWithHashTags
    Thread.sleep(5000)
  }

  private def writeBlogContent(blog: Array[String]) = {
    pressKeys("originally on: www.ticketsroundtheworld.com")
    changeTumblrPara

    for ((text, index) <- blog.zipWithIndex if index > 1) {
      text.length match {
        case 0 =>
        case heading if heading < 80 =>
          val markHeading = "**"
          pressKeys(markHeading + text + markHeading)
          changeTumblrPara
        case _ =>
          val array = text.split(" ")
          array.foreach(t => pressKeys((t + " ")))
          changeTumblrPara
      }
    }
  }

  private def readRtwSpecialPostSite(link: String): Array[String] = {
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

  private def readRtwSite(link: String): Array[String] = {
    go to link
    val element = find(className("left-column"))
    element.get.text.split("\n")
  }

  private def postWithHashTags {
    pressKeys (Keys.chord(Keys.TAB))
    TUMBLR_HASH_TAGS.split(",").foreach(hash => pressKeys(hash + ","))
    pressKeys (Keys.chord(Keys.TAB))
    pressKeys (Keys.chord(Keys.ENTER))
  }

  private def changeTumblrPara {
    pressKeys (Keys.chord(Keys.ENTER))
    pressKeys (Keys.chord(Keys.ENTER))
  }

}
