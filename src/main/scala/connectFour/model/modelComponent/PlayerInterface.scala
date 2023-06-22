package connectFour.model.modelComponent

import connectFour.model.modelComponent.fieldImpl.{Field, Move}
import connectFour.model.modelComponent.playerImpl.Player

trait PlayerInterface:
  
  def createPlayer(name: String, color: String): Player
  def move(field: Field, m: Move): Option[Field]
