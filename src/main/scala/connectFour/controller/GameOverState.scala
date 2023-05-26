package connectFour
package controller

import connectFour.model.{Field, Move, Player, Stone}

class GameOverState(winner: Player) extends GameState {

  override def restartGame(controller: Controller): Unit = {
    // Logik für Neustart des Spiels
    controller.changeGameState(InProgressState)
  }
  

  override def printfField(field: Field): Option[String] =
    Some(field.toString + winner.name + " hat gewonnen!!!")
}