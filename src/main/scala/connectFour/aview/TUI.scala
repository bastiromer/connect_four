package connectFour
package aview

import scala.io.StdIn.readLine
import controller.{Controller, TUIMoveValidator}
import model.{Move, Player, PlayerFactory, Stone}
import util.Observer
import scala.util.{Try,Success,Failure}


class TUI(controller: Controller) extends Template(controller) :
  controller.add(this)

  override def update: Unit = controller.toString

  override def start: Unit = gameLoop(p1)

  override def finalStat: String =
    controller.toString

  override def aborted: Unit =
    println("\nGame was aborted\n")
    sys.exit()

  override def gameLoop(player: Player): Unit =
    println(controller.toString)
    println(player.name + "s turn")
    println("Enter your move <row> (or q for quit)")
    handleInput(readLine,player) match
      case None =>
      case Some(move) => controller.doAndPublish(controller.put, move)
    gameLoop(if player == p1 then p2 else p1)


  def inputToInt(input: String): Try[Int] = Try(Integer.parseInt(input))

  def displayError(message: Throwable): Unit = println("incorrect input!")

  def handleInput(input: String, player: Player): Option[Move] =
    input match
      case "q" => aborted; None
      case "z" => controller.doAndPublish(controller.redo); None
      case "y" => controller.doAndPublish(controller.undo); None
      case _ =>
        inputToInt(input) match
          case Success(i) => Some(Move(player,i,controller.getCol(i)))
          case Failure(e) => displayError(e); None
  
  var p1: Player = new PlayerFactory().createPlayer("Player1", "yellow")
  var p2: Player = new PlayerFactory().createPlayer("Player2", "red")

