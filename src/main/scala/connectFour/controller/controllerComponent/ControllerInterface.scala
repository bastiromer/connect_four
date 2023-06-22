package connectFour
package controller.controllerComponent

import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.modelImpl.{Move, Player}
import util.Observable
trait ControllerInterface extends Observable:
  def doAndPublish(doThis: Move => FieldInterface, move:Move): Unit
  def doAndPublish(doThis: => FieldInterface): Unit
  def put(move: Move): FieldInterface
  def undo: FieldInterface
  def redo: FieldInterface
  def getCol(row: Int): Int
  def checkWin: Option[Player]
  def changePlayer(): Unit
  def currentPlayer: Player
  override def toString: String
