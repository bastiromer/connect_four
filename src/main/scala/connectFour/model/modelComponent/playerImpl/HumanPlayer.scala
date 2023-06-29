package connectFour.model.modelComponent.playerImpl

import connectFour.model.modelComponent.{FieldInterface, PlayerInterface}
import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}

class HumanPlayer (st: Stone, n: String) extends PlayerInterface:
  override val stone: Stone = st
  override val name: String = n

  override def move(field: FieldInterface, m: Move): Option[FieldInterface] =
    Some(field.put(this.stone,m.row,m.col))
