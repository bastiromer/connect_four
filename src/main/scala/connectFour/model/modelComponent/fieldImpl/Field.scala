package connectFour.model.modelComponent
package fieldImpl

import scala.util.control.Breaks.*
import com.google.inject.Inject
import com.google.inject.name.Named
import connectFour.model.modelComponent.playerImpl.PlayerFactory

case class Field @Inject() (matrix: Matrix[Stone], var player: Vector[PlayerInterface]) extends FieldInterface:
  def this(width: Int, height: Int, filling: Stone, vector: Vector[PlayerInterface]) =
    this(new Matrix(width, height, filling), player = vector)
  val width = matrix.width
  val height = matrix.height

  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3) =
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3) =
    (0 until height).map(cells(_, cellWidth)).mkString(bar(cellWidth, width), bar(cellWidth, width), bar(cellWidth, width) + (0 until width).mkString("  ","   ","  "))
  override def toString = mesh()
  override def put(stone: Stone, row: Int, col: Int) =
    copy(matrix.replaceCell(col, row, stone))

  override def get(row: Int, col: Int): Stone =
    matrix.cell(row, col)
  
  override def moveCorrect(row: Int): Int =
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

  override def checkWin: Option[PlayerInterface] =
    for (row <- 0 until height)
      for (col <- 0 to width - 4)
        val stones = (0 until 4).map(col +_).map(matrix.cell(row, _))
        if (stones.forall(_ == player.head.stone))
          return Some(player.head)

    for (col <- 0 until width)
      for (row <- 0 to height - 4)
        val stones = (0 until 4).map(row + _).map(matrix.cell(_ , col))
        if (stones.forall(_ == player.head.stone))
          return Some(player.head)

    for (row <- 0 to height - 4)
      for (col <- 0 to width - 4)
        val stones = (0 until 4).map(i => matrix.cell(row + i, col + i))
        if (stones.forall(_ == player.head.stone))
          return Some(player.head)

    for (row <- 0 to height - 4)
      for (col <- 3 until width)
        val stones = (0 until 4).map(i => matrix.cell(row + i, col - i))
        if (stones.forall(_ == player.head.stone))
          return Some(player.head)
    None

  override def currentPlayer: PlayerInterface = player.head
  override def changePlayer(): Unit = player = player.tail :+ player.head
  override def playerList: Vector[PlayerInterface] = player
