package connectFour
package controller

import model.{Field, Move, Player, PlayerFactory, Stone}
import util.{Command, Event, Observable, UndoManager}

case class Controller(var field: Field) extends Observable:
  val undoManager = new UndoManager[Field]

  
  def doAndPublish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    field.checkWin() match
      case Some(Stone.X) | Some(Stone.O) => notifyObservers(Event.End)
      case None => changePlayer;notifyObservers(Event.Move)

  def doAndPublish(doThis: => Field) =
    field = doThis
    field.checkWin() match
      case Some(Stone.X) | Some(Stone.O) => notifyObservers(Event.End)
      case None => changePlayer;notifyObservers(Event.Move)
  def put(move: Move): Field = undoManager.doStep(field, PutCommand(move))
  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)
  
  override def toString: String = field.toString
  
  def getCol(row: Int): Int = field.moveCorrect(row)
  
  def checkWin(): Option[Player] =
    field.checkWin().get match
      case p1.stone => Some(p1)
      case p2.stone => Some(p2)
      case _ => None

  def changePlayer: Unit =
    if currentPlayer == p1
    then currentPlayer = p2
    else currentPlayer = p1

  val p1: Player = new PlayerFactory().createPlayer("Player1", "red")
  val p2: Player = new PlayerFactory().createPlayer("Player2", "yellow")
  var currentPlayer: Player = p1

