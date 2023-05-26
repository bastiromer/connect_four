package connectFour.controller
import connectFour.model.Field

case object TUIMoveValidator extends MoveValidator:
  override def validateMove(field: Field, row: Int): Boolean =
    if(row < 7 && row >= 0)
      if(field.moveCorrect(row) == -1)
        false
      else
        true 
    else
      false