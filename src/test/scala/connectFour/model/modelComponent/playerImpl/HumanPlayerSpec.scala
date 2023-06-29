package connectFour.model.modelComponent.playerImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class HumanPlayerSpec extends AnyWordSpec:
  val player = HumanPlayer(Stone.Red,"Red")
  "A HumanPlayer" should {
    "have a name an an stone" in {
      player.name should be("Red")
      player.stone should be(Stone.Red)
    }
    /*"should put his stone in the field" in {
      val field = new Field(3, 3, Stone.Empty)
      val player1 = HumanPlayer(Stone.Empty,"Red")
      val move = Move(player1, 0, 0)
      player1.move(field, move).get should be(("""#+---+---+---+---+
                                          #|   |   |   |
                                          #+---+---+---+---+
                                          #|   |   |   |
                                          #+---+---+---+---+
                                          #|   |   |   |
                                          #+---+---+---+---+
                                          #  0   1   2   3  """).stripMargin('#'))
    }
    */
  }
