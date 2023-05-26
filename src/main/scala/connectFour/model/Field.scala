package connectFour.model

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
  def put(player: Player, row: Int, col: Int) =
    copy(matrix.replaceCell(col, row, player.stone))

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


  def checkWin(): Option[Stone] =

    // Überprüfung auf horizontale Gewinnmuster
    for (row <- 0 until height)
      for (col <- 0 to width - 4)
        val stones = (0 until 4).map(col +_).map(matrix.cell(row, _))
        if (stones.forall(_ == Stone.X))
          return Some(Stone.X)
        else if (stones.forall(_ == Stone.O))
          return Some(Stone.O)

    // Überprüfung auf vertikale Gewinnmuster
    for (col <- 0 until width)
      for (row <- 0 to height - 4)
        val stones = (0 until 4).map(row + _).map(matrix.cell(_ , col))
        if (stones.forall(_ == Stone.X))
          return Some(Stone.X)
        else if (stones.forall(_ == Stone.O))
          return Some(Stone.O)

    // Überprüfung auf diagonale Gewinnmuster (von links oben nach rechts unten)
    for (row <- 0 to height - 4)
      for (col <- 0 to width - 4)
        val stones = (0 until 4).map(i => matrix.cell(row + i, col + i))
        if (stones.forall(_ == Stone.X))
          return Some(Stone.X)
        else if (stones.forall(_ == Stone.O))
          return Some(Stone.O)
  
    // Überprüfung auf diagonale Gewinnmuster (von rechts oben nach links unten)
    for (row <- 0 to height - 4)
      for (col <- 3 until width)
        val stones = (0 until 4).map(i => matrix.cell(row + i, col - i))
        if (stones.forall(_ == Stone.X)) 
          return Some(Stone.X)
        else if (stones.forall(_ == Stone.O))
          return Some(Stone.O)
    // Kein Gewinner gefunden
    None