package connectFour.model.modelComponent.fieldImpl

import com.google.inject.{Guice, Inject}
import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}

case class Matrix[T] (rows: Vector[Vector[T]], player: Vector[PlayerInterface]):
  def this(width: Int, height: Int, filling: T, vector: Vector[PlayerInterface]) = 
    this(Vector.tabulate(height, width) { (row, col) => filling }, player = vector)
  val width: Int = rows.size + 1
  val height: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(height, width) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] =
    copy(rows.updated(row, rows(row).updated(col, cell)))

  def currentPlayer(index: Int): PlayerInterface = player(index)