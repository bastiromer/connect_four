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
    "habe another name and Stone" in {
      val newPlayer = HumanPlayer(Stone.Empty,"empty")
      newPlayer.name should be("empty")
      newPlayer.stone should be(Stone.Empty)
    }
  }
