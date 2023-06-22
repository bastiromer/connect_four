package connectFour.model.modelComponent.playerImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}

class HumanPlayer (st: Stone, n: String) extends Player:
  override val stone: Stone = st
  override val name: String = n

  override def move(field: Field, m: Move): Option[Field] =
    Some(field.put(this,m.row,m.col))