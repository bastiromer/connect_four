package connectFour.controller

import connectFour.model.{Field, Move, Player, Stone}

//State Pattern
trait GameState {
  def makeMove(controller: Controller, field: Field, player: Player, row: Int): Option[Field] = None

  def restartGame(controller: Controller): Unit = None

  def printfField(field: Field): Option[String] = None
  
  def handleInput(player: Player, input: String): Option[Move] = None
}