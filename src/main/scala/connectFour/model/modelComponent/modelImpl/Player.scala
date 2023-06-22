package connectFour.model.modelComponent.modelImpl

import connectFour.model.modelComponent.modelImpl.{Field, Move}

//Factory Pattern
abstract class Player:
  val stone: Stone
  val name: String

  def move(field: Field, m: Move): Option[Field] = None