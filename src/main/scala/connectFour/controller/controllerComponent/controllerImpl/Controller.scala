package connectFour
package controller.controllerComponent
package controllerImpl

import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.modelImpl.{Field, Move, Player, PlayerFactory, Stone}
import connectFour.util.{Command, Event, Observable, UndoManager}

import com.google.inject.{Inject,Guice}
import com.google.inject.name.Named
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

case class Controller @Inject() (@Named("Field") var field: FieldInterface) extends ControllerInterface:

  val undoManager = new UndoManager[FieldInterface]
  
  override def doAndPublish(doThis: Move => FieldInterface, move: Move) =
    field = doThis(move)
    field.checkWin match
      case Some(Stone.X) | Some(Stone.O) => notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)

  override def doAndPublish(doThis: => FieldInterface) =
    field = doThis
    field.checkWin match
      case Some(Stone.X) | Some(Stone.O) => notifyObservers(Event.End)
      case None => changePlayer();notifyObservers(Event.Move)
  override def put(move: Move): FieldInterface = undoManager.doStep(field, PutCommand(move))
  override def undo: FieldInterface = undoManager.undoStep(field)
  override def redo: FieldInterface = undoManager.redoStep(field)
  
  override def toString: String = field.toString

  override def getCol(row: Int): Int = field.moveCorrect(row)

  override def checkWin: Option[Player] =
    field.checkWin.get match
      case p1.stone => Some(p1)
      case p2.stone => Some(p2)
      case _ => None

  override def changePlayer(): Unit =
    if currentP == p1
    then currentP = p2
    else currentP = p1

  override def currentPlayer: Player =
    currentP

  val p1: Player = new PlayerFactory().createPlayer("Player1", "red")
  val p2: Player = new PlayerFactory().createPlayer("Player2", "yellow")
  var currentP: Player = p1

//Spielerlogig in model Schicht


