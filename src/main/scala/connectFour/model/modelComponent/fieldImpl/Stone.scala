package connectFour.model.modelComponent.fieldImpl

import org.scalactic.Prettifier.default

val reset = "\u001b[0m"
val red = "\u001b[31m"
val yellow = "\u001b[33m"
enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case Red extends Stone(s"${red}●${reset}")
  case Yellow extends Stone(s"${yellow}●${reset}")
  case Empty extends Stone(" ")
  
object Stone:
  import play.api.libs.json._
  
  implicit val stoneReads: Reads[Stone] = new Reads[Stone] {
    override def reads(json: JsValue): JsResult[Stone] =
      var player: Stone = null
      val stone = (json \ "stone").as[String]
      stone match
        case "Red" => JsSuccess(Stone.Red)
        case "Yellow" => JsSuccess(Stone.Yellow)
        case _ => JsSuccess(Stone.Empty)
  }

  implicit val playerWrites: Writes[Stone] = new Writes[Stone] {
    override def writes(stone: Stone): JsValue =
      stone match
        case Stone.Red => Json.obj("stone" -> "Red")
        case Stone.Yellow => Json.obj("stone" -> "Yellow")
        case Stone.Empty => Json.obj("stone" -> "Empty")
  }
    