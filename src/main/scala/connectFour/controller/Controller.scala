package connectFour
package controller

import model.{Field, Move, Player, PlayerFactory, Stone}
import util.{Command, Observable, UndoManager}

case class Controller(var field: Field, moveValidator: MoveValidator) extends Observable:
  var gameState: GameState = InProgressState
  val undoManager = new UndoManager[Field]
  def changeGameState(state: GameState): Unit =
    gameState = state
  
  def doAndPublish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers
  def doAndPublish(doThis: => Field) =
    field = doThis
    notifyObservers
  def put(move: Move): Field = undoManager.doStep(field, PutCommand(move))
  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)

  def restartGame(): Unit =
    gameState.restartGame(this)
    notifyObservers

  override def toString: String = gameState.printfField(field).get
  
  def getCol(row: Int): Int = field.moveCorrect(row)
