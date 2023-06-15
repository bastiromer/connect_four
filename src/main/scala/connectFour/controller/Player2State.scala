package connectFour.controller
import connectFour.model.{Field, Move}

case object Player2State extends GameState:

  override def makeMove(doThis: Move => Field, move: Move): Option[Field] =
    Some(doThis(move))

  override def makeMove(doThis: => Field): Option[Field] =
    Some(doThis)

  override def printfField(field: Field): Option[String] =
    Some(field.toString)