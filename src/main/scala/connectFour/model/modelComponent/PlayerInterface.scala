package connectFour.model.modelComponent

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}

trait PlayerInterface:

  val stone: Stone
  val name: String
  def move(field: FieldInterface, m: Move): Option[FieldInterface]

object PlayerInterface:
  import play.api.libs.json._

  implicit val playerInterfaceReads: Reads[PlayerInterface] = new Reads[PlayerInterface] {
    override def reads(json: JsValue): JsResult[PlayerInterface] =
      val stone = (json \ "stone").validate[Stone]
      val name = (json \ "name").validate[String]
      
      val s = Json.parse(stone)
      json.validate[PlayerInterface]
  }
