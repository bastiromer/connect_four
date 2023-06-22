package connectFour.model.modelComponent.fieldImpl

import com.google.inject.{Guice, Inject}
import connectFour.ConnectFourModule
import connectFour.model.modelComponent.playerImpl.Player
import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}

case class Matrix[T] @Inject() (rows: Vector[Vector[T]], player: Vector[PlayerInterface])extends FieldInterface:
  def this(width: Int, height: Int, filling: T, ) = this(Vector.tabulate(height, width) { (row, col) => filling })
  val width: Int = rows.size + 1
  val height: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(height, width) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] =
    copy(rows.updated(row, rows(row).updated(col, cell)),player)

  override def currentPlayer: PlayerInterface = player.head

  override def changePlayer(): Unit = copy(rows,player.tail :+ player.head)