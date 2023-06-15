package connectFour.controller

import connectFour.model.{Field, Move, Player, Stone}

//State Pattern
trait GameState:
  def makeMove(doThis: Move => Field, move: Move): Option[Field] = None

  def makeMove(doThis: => Field): Option[Field] = None

  //def restartGame(controller: Controller): Unit = None

  def printfField(field: Field): Option[String] = None


