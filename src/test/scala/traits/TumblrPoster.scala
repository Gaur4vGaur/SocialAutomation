package traits

trait TumblrPoster extends TumblrLogin {

  val POST_LINK = "https://www.tumblr.com/new/link"
  private val hashList = """aroundtheworldtravel, Travel, RTW, Around the world travel, ticketsroundtheworld.com,
                          ticketsroundtheworld, Round the world travel,"""

  def postLink(post: (String, String)): Unit = {

    val (link, description) = post
    go to POST_LINK

    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]")
    pressKeys (link)

    Thread.sleep (6000)
    pressKeys (description)

    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[3]/div[1]/div/div[1]/div/div/div[1]")
    pressKeys (hashList)

    click on xpath ("//*[@id=\"new_post_buttons\"]/div[4]/div[2]/div/div[5]/div[1]/div/div[3]/div/div/button")
  }

  def tumblrFlow(post: () => Unit): Unit = {
    tumblrLogin()
    post.apply
    close()
  }

}
