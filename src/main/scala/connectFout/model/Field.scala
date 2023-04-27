package connectFout.model

import scala.util.control.Breaks._

case class Field(matrix: Matrix[Stone]):
  def this(width: Int, height: Int, filling: Stone) = this(new Matrix(width, height, filling))
  val width = matrix.width
  val height = matrix.height

  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3) =
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3) =
    (0 until height).map(cells(_, cellWidth)).mkString(bar(cellWidth, width), bar(cellWidth, width), bar(cellWidth, width) + (0 until width).mkString("  ","   ","  "))
  override def toString = mesh()
  def put(stone: Stone, row: Int) =
    val col = moveCorrect(row)
    copy(matrix.replaceCell(col, row, stone))

  def moveCorrect(row: Int): Int =
    var check = -1
    breakable {
      matrix.rows.zipWithIndex.foreach { case (innerVector, i) =>
        val elementToCheck = innerVector(row)
        if elementToCheck.toString != " " then
          check = i - 1
          break()
        else if i == 5 then
          check = i
          break()
      }
    }
    check