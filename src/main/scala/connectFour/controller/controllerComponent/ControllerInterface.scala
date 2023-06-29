package connectFour
package controller.controllerComponent

import connectFour.controller.controllerComponent.controllerImpl.GameState
import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}
import connectFour.model.modelComponent.fieldImpl.{Move, Stone}
import util.Observable
trait ControllerInterface extends Observable:
  def doAndPublish(doThis: Move => FieldInterface, move:Move): Unit
  def doAndPublish(doThis: => FieldInterface): Unit
  def put(move: Move): FieldInterface
  def undo: FieldInterface
  def redo: FieldInterface
  def getRow(col: Int): Int
  def changePlayer(): Unit
  def currentPlayer: PlayerInterface
  def get(row:Int, col: Int): Stone
  def height: Int
  def width: Int
  def abort: Unit
  def save: Unit
  def load: Unit
  def changeGameState(state: GameState): Unit
  override def toString: String
