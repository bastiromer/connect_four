package connectFour.model.modelComponent

import connectFour.model.modelComponent.fieldImpl.{Stone, Field}
import connectFour.model.modelComponent.playerImpl.Player

trait FieldInterface:
  def put(player: Player, row: Int, col: Int): Field
  def moveCorrect(row: Int): Int
  def checkWin: Option[Stone]
  def currentPlayer: Player
  def changePlayer(): Unit
  override def toString: String
