package bowling

object Main extends App{

  if (args.length == 0) {
    println ("Enter rolls to tabulate score")
  }

  else {

    try {
      val rolls = args.toList.map ((s: String) => s.toInt)
      val game = new BowlingGame()
      println("Game Score: " + game.scoreGame(rolls.toArray))
    }
    catch
      {
        case e: Exception => println("Invalid Game: " + e.getMessage)
      }
  }
}
