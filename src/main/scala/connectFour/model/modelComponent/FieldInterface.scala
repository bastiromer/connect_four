package connectFour.model.modelComponent

import connectFour.model.modelComponent.modelImpl.{Player, Stone, Field}

trait FieldInterface:
  def put(player: Player, row: Int, col: Int): Field
  def moveCorrect(row: Int): Int
  def checkWin: Option[Stone]
  override def toString: String
