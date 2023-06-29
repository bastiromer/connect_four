package connectFour
package controller.controllerComponent
package controllerImpl

import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}
import connectFour.util.{Command, Event, Observable, UndoManager}
import com.google.inject.{Guice, Inject}
import com.google.inject.name.Named
import connectFour.model.modelComponent.PlayerInterface
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

class Controller (using var field: FieldInterface,val fileIO: FileIOInterface) extends ControllerInterface:

  val undoManager = new UndoManager[FieldInterface]
  var gameState: GameState = new PlayState
  
  override def doAndPublish(doThis: Move => FieldInterface, move: Move) =
    field = doThis(move)
    field.checkWin match
      case Some(_) => changeGameState(new WinState);notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)

  override def doAndPublish(doThis: => FieldInterface) =
    field = doThis
    field.checkWin match
      case Some(_) => changeGameState(new WinState);notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)
  override def put(move: Move): FieldInterface = gameState.makeMove(undoManager, field, move).get//undoManager.doStep(field, PutCommand(move))
  override def undo: FieldInterface = undoManager.undoStep(field)
  override def redo: FieldInterface = undoManager.redoStep(field)

  override def save =
    fileIO.save(field)
    notifyObservers(Event.Move)
  override def load: Unit =
    field = fileIO.load
    notifyObservers(Event.Move)
  override def toString: String = field.toString
  override def getRow(col: Int): Int = field.moveCorrect(col)
  override def get(row: Int, col: Int): Stone = field.get(row, col)
  override def width: Int = field.getRow
  override def height: Int = field.getCol
  override def changePlayer(): Unit = field.changePlayer()
  override def currentPlayer: PlayerInterface = field.currentPlayer
  override def abort: Unit = notifyObservers(Event.Abort)
  override def changeGameState(state: GameState): Unit = gameState = state