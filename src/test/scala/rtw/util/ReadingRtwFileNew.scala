package rtw.util

import java.io._
import scala.io.Source

/**
  * The class is supposed maintained the index of messages that should be posted.
  * @param fileName Name of the file maintaining the index
  * @param cuttOff is the limit at which index is rolled back to start
  */
case class ReadingRtwFileNew(fileName: String, cuttOff: Int) {

  def fetchKeyFromFile: Int = {
    val source = Source.fromFile(fileName)
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

  def writeToFile(data: Int): Unit = {
    val pw = new PrintWriter(new File(fileName))
    try {
      pw.write((if (data > cuttOff) 1 else data).toString)
    } catch {
      case e: Exception =>
        println("\n\nKey does not exist to fetch the tweet")
        throw e
    } finally {
      pw.close
    }
  }
}
