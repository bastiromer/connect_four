package connectFour.model.modelComponent
package fieldImpl

import scala.util.control.Breaks.*
import com.google.inject.Inject
import com.google.inject.name.Named
import connectFour.model.modelComponent.playerImpl.PlayerFactory

case class Field (matrix: Matrix[Stone], var playerIndex: Int) extends FieldInterface:
  def this(width: Int, height: Int, filling: Stone) =
    this(new Matrix(width, height, filling, Vector(PlayerFactory().createPlayer("RED", "red"),
                                            PlayerFactory().createPlayer("YELLOW", "yellow")))
    , playerIndex = 0)
  val width = matrix.width
  val height = matrix.height

  override def getCol: Int = height
  override def getRow: Int = width

  val eol = sys.props("line.separator")
  def bar(cellWidth: Int, cellNum: Int) = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3) =
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3) =
    (0 until height).map(cells(_, cellWidth)).mkString(bar(cellWidth, width), bar(cellWidth, width), bar(cellWidth, width) + (0 until width).mkString("  ","   ","  "))
  override def toString = mesh()
  override def put(stone: Stone, row: Int, col: Int) =
    copy(matrix.replaceCell(col, row, stone))

  override def get(row: Int, col: Int): Stone =
    matrix.cell(row, col)
  
  override def moveCorrect(col: Int): Int =
    var check = -1
    breakable {
      matrix.rows.zipWithIndex.foreach { case (innerVector, i) =>
        val elementToCheck = innerVector(col)
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
    for (col <- 0 until height)
      for (row <- 0 to width - 4)
        val stones = (0 until 4).map(row +_).map(matrix.cell(col, _))
        if (stones.forall(_ == currentPlayer.stone))
          return Some(currentPlayer)

    for (row <- 0 until width)
      for (col <- 0 to height - 4)
        val stones = (0 until 4).map(col + _).map(matrix.cell(_ , row))
        if (stones.forall(_ == currentPlayer.stone))
          return Some(currentPlayer)

    for (col <- 0 to height - 4)
      for (row <- 0 to width - 4)
        val stones = (0 until 4).map(i => matrix.cell(col + i, row + i))
        if (stones.forall(_ == currentPlayer.stone))
          return Some(currentPlayer)

    for (col <- 0 to height - 4)
      for (row <- 3 until width)
        val stones = (0 until 4).map(i => matrix.cell(col + i, row - i))
        if (stones.forall(_ == currentPlayer.stone))
          return Some(currentPlayer)
    None
  override def currentPlayer: PlayerInterface = matrix.currentPlayer(playerIndex)
  override def changePlayer(): Unit = playerIndex = (playerIndex + 1) % 2
  override def playerList: Vector[PlayerInterface] = matrix.player
  override def getPlayerIndex: Int = playerIndex
  override def updatePlayer(index: Int): Unit = playerIndex = index
