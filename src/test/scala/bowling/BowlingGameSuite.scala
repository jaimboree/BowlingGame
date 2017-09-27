package bowling

import org.scalatest.{FunSuite, GivenWhenThen}


/***
  * This is a test suite to test functionality of BowlingGame class
  */
class BowlingGameSuite extends FunSuite with GivenWhenThen{
  import bowling.BowlingGame._

  val bg = new BowlingGame()

  test("No Bonus Test"){
    Given("NB1 Game with no bonus points and rolls 1, 2, 3, 4, 5, 0, 6, 1, 7, 2, 8, 1, 9, 0, 1, 3, 1, 2, 6, 3")
    val game1 = Array(1, 2, 3, 4, 5, 0, 6, 1, 7, 2, 8, 1, 9, 0, 1, 3, 1, 2, 6, 3)
    Then("NB1 Score Should = 65")
    assert (bg.scoreGame(game1) === 65, "NB1 Score = 65")

    Given("NB2 Game with same frame score but every other roll swapped ")
    val game2 = game1.grouped(2).flatMap( _.reverse ).toArray
    Then("NB2 Score Should Also = 65")
    assert (bg.scoreGame(game2) === 65, "NB2 Score = 65")

    Given("NB3 Game with all gutter balls")
    val game3 = Array.fill(20)(0)
    Then("NB3 Score Should Be 0")
    assert (bg.scoreGame(game3) === 0, "NB3 (Gutter Ball): Score Should = 0")
  }

  test("Strike Bonus Test"){
    Given("SB1 Game with strike bonus and rolls 10, 5, 3, 7, 1, 10, 2, 4, 10, 10, 8, 1, 3, 3, 4, 2")
    val game1 = Array(10, 5, 3, 7, 1, 10, 2, 4, 10, 10, 8, 1, 3, 3, 4, 2)
    Then("SB1 Score Should = 124")
    assert (bg.scoreGame(game1) === 124, "SB1: Score Should = 124")

    Given("SB2 Game with strike bonus in last frame and rolls  10, 5, 3, 7, 1, 10, 2, 4, 10, 10, 8, 1, 3, 3, 10, 10, 4")
    val game2 = Array(10, 5, 3, 7, 1, 10, 2, 4, 10, 10, 8, 1, 3, 3, 10, 10, 4)
    Then("SB2 Score Should = 142")
    assert (bg.scoreGame(game2) === 142, "SB2: Score Should = 142")

    Given("SB3 Game with all strike rolls")
    val game3 = Array.fill(12)(10)
    Then("SB3 Perfect Game Should = 300")
    assert (bg.scoreGame(game3) === 300, "SB3 (Perfect Game): Score Should = 300")
  }

  test("Spare Bonus Test"){
    Given("SPB1 Game with spare bonus in last frame and rolls 4, 4, 6, 3, 5, 5, 2, 8, 3, 2, 8, 2, 2, 4, 9, 0, 5, 0, 6, 4, 3")
    val game1 = Array(4, 4, 6, 3, 5, 5, 2, 8, 3, 2, 8, 2, 2, 4, 9, 0, 5, 0, 6, 4, 3)
    Then("SPB1 Score Should = 92")
    assert (bg.scoreGame(game1) === 92, "SPB1: Score Should = 92")

    Given("SPB2 Game with spare bonus and rolls 4, 4, 6, 3, 5, 5, 2, 8, 3, 2, 8, 2, 2, 4, 9, 0, 5, 0, 6, 3")
    val game2 = Array(4, 4, 6, 3, 5, 5, 2, 8, 3, 2, 8, 2, 2, 4, 9, 0, 5, 0, 6, 3)
    Then("SPB2 Score Should = 88")
    assert (bg.scoreGame(game2) === 88, "SPB2: Score Should = 88")

    Given("SPB3 Game with all spares and all rolls of 5")
    val game3 = Array.fill(21)(5)
    Then("SPB3 Score Should = 150")
    assert (bg.scoreGame(game3) === 150, "SPB3 (All Spares): Score Should = 150")
  }

  test("Normal Game Test including 10th Frame Bonus"){
    Given("NB1 Game with rolls 4, 4, 6, 4, 10, 5, 5, 10, 10, 3, 2, 8, 2, 2, 4, 10, 0, 1")
    val game1 = Array(4, 4, 6, 4, 10, 5, 5, 10, 10, 3, 2, 8, 2, 2, 4, 10, 0, 1)
    Then("NG1: Score Should = 140")
    assert (bg.scoreGame(game1) === 140, "NG1: Score Should = 140")

    Given("NB2 Game with rolls 4, 4, 6, 4, 10, 5, 5, 10, 10, 3, 2, 8, 2, 2, 4, 9, 1, 10")
    val game2 = Array(4, 4, 6, 4, 10, 5, 5, 10, 10, 3, 2, 8, 2, 2, 4, 9, 1, 10)
    Then("NG2: Score Should = 149")
    assert (bg.scoreGame(game2) === 149, "NG2: Score Should = 149")
  }

  test("Invalid Pins Test"){
    Given("IP1: Game with rolls of negative numbers")
    val game1 = Array.fill(21)(-5)
    Then("IP1 Game throws IllegalArgumentException Invalid Roll")
    assertThrows[IllegalArgumentException ] (bg.scoreGame(game1), "Intercepts IllegalArgumentException Invalid Roll")

    Given("IP2: Game with rolls greater than 10 ")
    val game2 = Array.fill(21)(15)
    Then("IP2 Game throws IllegalArgumentException Invalid Roll")
    assertThrows[IllegalArgumentException ] (bg.scoreGame(game2), "Intercepts IllegalArgumentException Invalid Roll")

    Given("IP3: Game where frame total exceeds frame pins")
    val game3 = Array.fill(21)(6)
    Then("IP3: throws IllegealArgumentException Invalid Score for Frame")
    assertThrows[IllegalArgumentException ] (bg.scoreGame(game3), "Intercepts IllegalArgumentException Invalid Score for Frame")
 }

  test("Invalid Frames Test"){
    Given("IF1: Game with rolls for incompleted game")
    val game1 = Array.fill(5)(5)
    Then("IF1: Game throws IllegalArgumentException Not enough frames to score game")
    assertThrows[IllegalArgumentException ] (bg.scoreGame(game1), "Intercepts IllegalArgumentException Not enough frames to score game")

    Given("IF2: Game with extra rolls")
    val game2 = Array.fill(22)(5)
    Then("IF2: Game ignores additional rolls and score should = 150")
    assert (bg.scoreGame(game2) === 150, "IF2 (Ignores extra frames): Score = 150")
  }
}


