package connectFour
package controller

import model.{Field, Move, Player, Stone}
import util.Observable

case class Controller(var field: Field, moveValidator: MoveValidator) extends Observable:
  var gameState: GameState = InProgressState

  def changeGameState(state: GameState): Unit =
    gameState = state

  def handleInput(player: Player, input: String): Move =
    gameState.handleInput(player, input).get

  def makeMove(player: Player, row: Int): Unit =
    if(moveValidator.validateMove(field,row))
      field = gameState.makeMove(this,field,player,row).get
      field.checkWin() match
        case None =>
        case Some(stone) => changeGameState(new GameOverState(player))
    else
      print("move incorrect\n")
    notifyObservers

  def restartGame(): Unit =
    gameState.restartGame(this)
    notifyObservers

  override def toString: String = gameState.printfField(field).toString

  def getCol(row: Int): Int = field.moveCorrect(row)