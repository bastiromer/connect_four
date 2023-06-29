package connectFour.model.modelComponent
package fileIOComponent.jsonImpl

import com.google.inject.Inject
import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}

import java.io.{File, PrintWriter}
import connectFour.model.modelComponent.fieldImpl.{Field, Matrix, Stone}
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import connectFour.model.modelComponent.playerImpl.HumanPlayer

import scala.io.Source
import play.api.libs.json.*

class FileO @Inject extends FileIOInterface {

  override def load: FieldInterface = {
    var field: FieldInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val col = (json \ "field" \ "height").as[Int]
    val row = (json \ "field" \ "width").as[Int]
    val player1 = HumanPlayer((json \ "field" \ "player1stone").as[Stone],
      (json \ "field" \ "player1name").toString)
    val player2 = HumanPlayer((json \ "field" \ "player2stone").as[Stone],
      (json \ "field" \ "player2name").toString)
    val player = Vector(player1,player2)
    field = new Field(row, col, Stone.Empty)

    val fieldArray = (json \ "field" \ "fields").as[JsArray]
    for (index <- fieldArray.value) {
      val row: Int = (index \ "row").as[Int]
      val col: Int = (index \ "col").as[Int]
      val value: Stone = (index \ "value").as[Stone]
      if (col < field.getCol-1 && row < field.getRow-1)then
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
        "width" -> field.getRow,
               "height" -> field.getCol,
               "fields" -> fields,
               "player1name" -> field.playerList(0).name,
               "player1stone" -> Json.toJson(field.playerList(0).stone),
               "player2name" -> field.playerList(1).name,
               "player2stone" -> Json.toJson(field.playerList(1).stone)
      )
    )
    json
  }

  def matrixToFieldsJsonArray(field: FieldInterface): JsArray = {
    val fields = for {
      row <- 0 until field.getRow
      col <- 0 until field.getCol
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