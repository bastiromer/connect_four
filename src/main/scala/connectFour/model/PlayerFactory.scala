package connectFour.model

class PlayerFactory:
  def createPlayer(name: String, color: String): Player =
    color match
      case "red" => new HumanPlayer(Stone.X, name)
      case "yellow" => new HumanPlayer(Stone.O, name)
      case "start" => new HumanPlayer(Stone.Empty, name)
      case _ => throw new IllegalArgumentException("Ungültige Spieler eingabe!")
