package connectFour.model.modelComponent.fileIOComponent
package xmlImpl

import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}

import scala.xml.{Elem, Node, PrettyPrinter, XML}
import connectFour.model.modelComponent.fieldImpl.{Field, Matrix, Stone}

abstract class FileIO extends FileIOInterface {

  override def load: FieldInterface =
    var field: FieldInterface = null
    val file = XML.loadFile("grid.xml")
    val col = (file \\ "field" \ "@colSize").text.toInt
    val row = (file \\ "field" \ "@rowSize").text.toInt
    val player = (file \\ "field" \ "@player").to[PlayerInterface]
    field = new Field(col,row,Stone.Empty, player)

    val fieldNodes = (file \\ "field")
    for (index <- fieldNodes) {
      val row: Int = (index \ "@row").text.toInt
      val col: Int = (index \ "@col").text.toInt
      val value: Stone = index.to[Stone]
      field = field.put(value,row, col)
    }
    field
  }

  override def save(field: FieldInterface): Unit = saveXML(field)

  def saveXML(field: FieldInterface): Unit = {
    XML.save("grid.xml", matrixToXml(field))
  }

  def saveString(field: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(matrixToXml(field))
    pw.write(xml)
    pw.close
  }

  def matrixToXml(field: FieldInterface): Elem = {
    <field colSize={field.height.toString} rowSize={field.width.toString}>
      {
      for {
        row <- 0 until field.height
        col <- 0 until field.width
      } yield fieldToXml(field, row, col)
      <player>
      {field.playerList}
      </player>
      }
    </field>
  }

  def fieldToXml(field: FieldInterface, row: Int, col: Int): Elem = {
    val fieldValue = field.get(row, col)
    <field row={ row.toString } col={ col.toString }>
      { fieldValue }
    </field>
  }
}