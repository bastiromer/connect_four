package connectFour
package controller.controllerComponent

import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}
import connectFour.model.modelComponent.fieldImpl.{Move, Stone}
import util.Observable
trait ControllerInterface extends Observable:
  def doAndPublish(doThis: Move => FieldInterface, move:Move): Unit
  def doAndPublish(doThis: => FieldInterface): Unit
  def put(move: Move): FieldInterface
  def undo: FieldInterface
  def redo: FieldInterface
  def getCol(row: Int): Int
  def changePlayer(): Unit
  def currentPlayer: PlayerInterface
  def get(row:Int, col: Int): Stone
  def abort: Unit
  def save: Unit
  def load: FieldInterface
  override def toString: String
