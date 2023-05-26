package connectFour
package controller

import connectFour.model.{Field, Move, Player, Stone}

case object InProgressState extends GameState {
  override def makeMove(controller: Controller, field: Field, player: Player, row: Int): Option[Field] =
    player.move(field,Move(player, row,controller.getCol(row)))
    

  override def handleInput(player: Player, input: String): Option[Move] =
    None

  
  override def printfField(field: Field): Option[String] =
    Some(field.toString)
}
