package connectFour.model.modelComponent.fileIOComponent
package jsonImpl

import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}

import java.io.{File, PrintWriter}
import connectFour.model.modelComponent.fieldImpl.{Field, Matrix, Stone}
import scala.io.Source
import play.api.libs.json.*

abstract class FileIO extends FileIOInterface {

  override def load: FieldInterface = {
    var field: FieldInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val col = (json \ "field" \ "height").as[Int]
    val row = (json \ "field" \ "width").as[Int]
    val player = (json \ "field" \ "players").as[Vector[PlayerInterface]]
    field = new Field(row, col, Stone.Empty,player)

    val fieldArray = (json \ "field" \ "fields").as[JsArray]
    for (index <- fieldArray.value) {
      val row: Int = (index \ "row").as[Int]
      val col: Int = (index \ "col").as[Int]
      val value: Stone = (index \ "value").as[Stone]
      field = field.put(value,row, col)
    }
    field
  }

  override def save(field: FieldInterface): Unit = {
    val json = matrixToJson(field)
    val prettyJson = Json.prettyPrint(json)
    saveString(prettyJson)
  }

  def saveString(jsonString: String): Unit = {
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(jsonString)
    pw.close()
  }

  def matrixToJson(field: FieldInterface): JsObject = {
    val fields = matrixToFieldsJsonArray(field)
    val json = Json.obj(
      "field" -> Json.obj(
        "width" -> field.width,
               "height" -> field.height,
               "fields" -> fields,
               "players" -> Json.toJson(field.playerList)
      )
    )
    json
  }

  def matrixToFieldsJsonArray(field: FieldInterface): JsArray = {
    val fields = for {
      row <- 0 until field.width
      col <- 0 until field.height
    } yield {
      Json.obj(
        "row" -> row,
        "col" -> col,
        "value" -> field.get(row, col).toString
      )
    }
    JsArray(fields)
  }
}