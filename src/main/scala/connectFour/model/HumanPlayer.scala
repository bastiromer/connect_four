package connectFour.model

class HumanPlayer (st: Stone, n: String) extends Player:
  override val stone: Stone = st
  override val name: String = n

  override def move(field: Field, m: Move): Option[Field] =
    Some(field.put(m.player,m.row,m.col))
