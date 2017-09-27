package bowling

import scala.annotation.tailrec

/***
  *
  * @param gpins total numbers of allowable pins in frame - default is 10
  * @param gframes total number of allowable frames in game - deafult is 10
  */
class BowlingGame (gpins:Int = 10, gframes:Int = 10) {
  val pins = gpins
  val maxframe = gframes

  /***
    * Function to tally score of a bowling game
    * @param rolls input array of rolls as integer
    * @return int total score
    */
  def scoreGame(rolls:Array[Int]):Int = {
    require(!rolls.exists(x => x < 0 || x > pins), "Invalid Roll")

    def isStrike(r1:Int):Boolean = r1 == pins

    def isSpare(frame:Array[Int]):Boolean = isStrike(frame.sum)

    def isLegalScore(frame:Array[Int]):Boolean = frame.sum <= pins

    @tailrec
    def scoreFrameHelper(rolls:Array[Int], score:Int, frame:Int):Int = {

      if (frame == maxframe) score
      else if (rolls.isEmpty) throw new IllegalArgumentException("Not enough frames to score game")
      else if (isStrike(rolls.head)) scoreFrameHelper(rolls.tail, score + (rolls take 3).sum, frame + 1)
      else if (isSpare(rolls take 2)) scoreFrameHelper(rolls drop 2, score + (rolls take 3).sum, frame + 1)
      else if (isLegalScore(rolls take 2)) scoreFrameHelper(rolls drop 2, score + (rolls take 2).sum, frame + 1)
      else throw new IllegalArgumentException("Invalid Score for Frame")
    }

    //get score per frame
    scoreFrameHelper(rolls, 0, 0)
  }

}