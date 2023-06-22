package connectFour.model.modelComponent.playerImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}

//Factory Pattern
abstract class Player:
  val stone: Stone
  val name: String

  def move(field: Field, m: Move): Option[Field] = None