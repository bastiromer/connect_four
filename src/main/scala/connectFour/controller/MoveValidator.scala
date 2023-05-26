package connectFour.controller

import connectFour.model.{Field,Stone}

//Stategy Pattern
trait MoveValidator:
  def validateMove(field: Field, row: Int): Boolean
