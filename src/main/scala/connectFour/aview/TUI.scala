package connectFour
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.{Move, Player, PlayerFactory, Stone}
import util.{Observer,Event}
import scala.util.{Try,Success,Failure}


class TUI(controller: Controller) extends Template(controller):

  override def update(e: Event): Unit = e match
    case Event.Abort => sys.exit
    case Event.End => println(finalStats)
    case Event.Move =>
      println(controller.toString)
      println(controller.currentPlayer.name + "s turn")
      println("Enter your move <row> (or q for quit)")

  override def start: Unit = gameLoop()

  override def finalStats: String =
    controller.toString
    controller.currentPlayer.name+" hat gewonnen!"

  override def aborted: Unit =
    println("\nGame was aborted\n")
    sys.exit()

  override def gameLoop(): Unit =
    val player = controller.currentPlayer
    handleInput(readLine,player) match
      case None =>
      case Some(move) => controller.doAndPublish(controller.put, move)
    gameLoop()


  def inputMatchAndToInt(str: String): Try[Int] =
    Try(Integer.parseInt(str))

  def inputToInt(input: String): Try[Int] = Try(Integer.parseInt(input))

  def displayError(message: Throwable): Unit = println("incorrect input!")

  def handleInput(input: String, player: Player): Option[Move] =
    input match
      case "q" => aborted; None
      case "z" => controller.doAndPublish(controller.redo); None
      case "y" => controller.doAndPublish(controller.undo); None
      case _ =>
        inputMatchAndToInt(input) match
          case Success(i) => Some(Move(player,i,controller.getCol(i)))
          case Failure(e) => displayError(e); None
  


