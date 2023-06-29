package connectFour.model.modelComponent

import connectFour.model.modelComponent.fieldImpl.{Stone, Field}
import connectFour.model.modelComponent.PlayerInterface

trait FieldInterface:
  def getCol: Int
  def getRow:Int
  def put(stone: Stone, row: Int, col: Int): Field
  def moveCorrect(row: Int): Int
  def checkWin: Option[PlayerInterface]
  def currentPlayer: PlayerInterface
  def changePlayer(): Unit
  def get(row: Int, col: Int): Stone
  def playerList: Vector[PlayerInterface]
  def getPlayerIndex: Int
  def updatePlayer(index: Int): Unit
  override def toString: String
