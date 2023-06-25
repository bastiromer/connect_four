package connectFour.model.modelComponent.playerImpl

import connectFour.model.modelComponent.PlayerInterface
import connectFour.model.modelComponent.fieldImpl.Stone

class PlayerFactory:
  def createPlayer(name: String, color: String): PlayerInterface =
    color match
      case "red" => new HumanPlayer(Stone.Red, name)
      case "yellow" => new HumanPlayer(Stone.Yellow, name)
      case "empty" => new HumanPlayer(Stone.Empty, name)
      case _ => throw new IllegalArgumentException("Ungültige Spieler eingabe!")
