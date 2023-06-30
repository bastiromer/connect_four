package connectFour.model.modelComponent.fieldImpl

import org.scalatest.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.*

object StoneSpec {
  sealed trait TestStone
  case object TestRed extends TestStone
  case object TestYellow extends TestStone
  case object TestEmpty extends TestStone

  implicit val testStoneReads: Reads[TestStone] = new Reads[TestStone] {
    override def reads(json: JsValue): JsResult[TestStone] = {
      val stone = (json \ "stone").as[String]
      stone match {
        case "Red" => JsSuccess(TestRed)
        case "Yellow" => JsSuccess(TestYellow)
        case _ => JsSuccess(TestEmpty)
      }
    }
  }

  implicit val testStoneWrites: Writes[TestStone] = new Writes[TestStone] {
    override def writes(stone: TestStone): JsValue = {
      stone match {
        case TestRed => Json.obj("stone" -> "Red")
        case TestYellow => Json.obj("stone" -> "Yellow")
        case TestEmpty => Json.obj("stone" -> "Empty")
      }
    }
  }
}

class StoneSpec extends AnyWordSpec with Matchers {
  import StoneSpec._

  "Stone" should {
    "serialize and deserialize correctly using Reads and Writes" in {
      val redStone = Stone.Red
      val yellowStone = Stone.Yellow
      val emptyStone = Stone.Empty

      val redJson = Json.toJson(redStone)
      val yellowJson = Json.toJson(yellowStone)
      val emptyJson = Json.toJson(emptyStone)

      val redStoneFromJson = Json.fromJson[TestStone](redJson)
      val yellowStoneFromJson = Json.fromJson[TestStone](yellowJson)
      val emptyStoneFromJson = Json.fromJson[TestStone](emptyJson)

      redStoneFromJson shouldEqual JsSuccess(TestRed)
      yellowStoneFromJson shouldEqual JsSuccess(TestYellow)
      emptyStoneFromJson shouldEqual JsSuccess(TestEmpty)
    }
  }
}
