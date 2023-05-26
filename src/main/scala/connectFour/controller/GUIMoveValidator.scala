package connectFour.controller
import connectFour.model.Field

case object GUIMoveValidator extends MoveValidator:
  override def validateMove(field: Field, row: Int): Boolean =
    //noch keine GUI
    //prüfen ob Reihe voll
    false
