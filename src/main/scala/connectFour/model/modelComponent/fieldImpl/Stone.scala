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

  implicit val playerInterfaceReads: Reads[Stone] = new Reads[Stone] {
    override def reads(json: JsValue): JsResult[Stone] =
      val stone = (json \ "stone").validate[Stone]
  
      val s = Json.parse(stone)
      json.validate[Stone]
  }
    