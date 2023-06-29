package connectFour.model.modelComponent

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}
import connectFour.model.modelComponent.playerImpl.HumanPlayer

trait PlayerInterface:

  val stone: Stone
  val name: String
  def move(field: FieldInterface, m: Move): Option[FieldInterface]

