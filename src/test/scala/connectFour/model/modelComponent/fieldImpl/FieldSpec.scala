package connectFour.model.modelComponent.fieldImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec:
  val field = new Field(7,6,Stone.Empty)
  "A Field" should {
    "check for possible wins" in {
      field.checkWin should be(None)
    }
    "give the playerndex" in {
      field.getPlayerIndex should be(0)
    }
    "update the playerindex" in {
      field.updatePlayer(1)
      field.getPlayerIndex should be(1)
    }
  }
