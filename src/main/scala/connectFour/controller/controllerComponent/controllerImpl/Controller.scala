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

case class Controller @Inject() (@Named("Field") var field: FieldInterface, val fileIO: FileIOInterface) extends ControllerInterface:

  val undoManager = new UndoManager[FieldInterface]
  
  override def doAndPublish(doThis: Move => FieldInterface, move: Move) =
    field = doThis(move)
    field.checkWin match
      case Some(_) => notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)

  override def doAndPublish(doThis: => FieldInterface) =
    field = doThis
    field.checkWin match
      case Some(_) => notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)
  override def put(move: Move): FieldInterface = undoManager.doStep(field, PutCommand(move))
  override def undo: FieldInterface = undoManager.undoStep(field)
  override def redo: FieldInterface = undoManager.redoStep(field)

  override def save = fileIO.save(field)
  override def load: FieldInterface = fileIO.load
  
  override def toString: String = field.toString

  override def getCol(row: Int): Int = field.moveCorrect(row)

  override def get(row: Int, col: Int): Stone =
    field.get(row, col)
  
  override def changePlayer(): Unit =
    field.changePlayer()

  override def currentPlayer: PlayerInterface =
    field.currentPlayer

  override def abort: Unit = notifyObservers(Event.Abort)