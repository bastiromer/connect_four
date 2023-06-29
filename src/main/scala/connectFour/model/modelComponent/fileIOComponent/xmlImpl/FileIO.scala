package connectFour.model.modelComponent
package fileIOComponent.xmlImpl

import com.google.inject.Inject
import connectFour.model.modelComponent.{FieldInterface, PlayerInterface, playerImpl}

import scala.xml.{Elem, Node, NodeSeq, PrettyPrinter, XML}
import java.io._
import connectFour.model.modelComponent.fieldImpl.{Field, Matrix, Stone}
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import connectFour.model.modelComponent.playerImpl.HumanPlayer

class FileIO @Inject extends FileIOInterface:
  override def save(field: FieldInterface): Unit =
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(toXml(field))
    val pw = new PrintWriter(new File("field.xml"))
    pw.write(xml)
    pw.close

  def toXml(field: FieldInterface): Elem =
    <field colSize={field.getCol.toString} rowSize={field.getRow.toString}>
      <status> {
        for
          row <- 0 until field.getRow
          col <- 0 until field.getCol
        yield
          cellToXml(field, row, col)
      }</status>
      <player> {
        field.getPlayerIndex.toString}
      </player>
    </field>

  def cellToXml(field: FieldInterface, row: Int, col: Int): Elem =
    <value row={ row.toString } col={ col.toString }>
      {field.get(col, row).toString}
    </value>

  override def load: FieldInterface =
    val file = XML.loadFile("field.xml")
    val col = (file \\ "field" \ "@colSize").text.toInt
    val row = (file \\ "field" \ "@rowSize").text.toInt
    val playerIndex = (file \\ "field" \ "player").text.trim.toInt
    var field: FieldInterface = new Field(col, row, Stone.Empty)

    val fieldSeq = (file \\ "field" \ "status" \ "value")
    for (fieldNode <- fieldSeq) {
      val row: Int = (fieldNode \ "@row").text.toInt
      val col: Int = (fieldNode \ "@col").text.toInt
      val value = fieldNode.text.trim match
        case "[31m●[0m" => Stone.Red
        case "[33m●[0m" => Stone.Yellow
        case _ => Stone.Empty
      if col < field.getRow then
        field = field.put(value, col, row)
      field.updatePlayer(playerIndex)
    }
    field