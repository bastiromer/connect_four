package connectFour
package aview

import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.model.modelComponent.PlayerInterface
import connectFour.model.modelComponent.fieldImpl.{Move, Stone}

import scala.io.StdIn.readLine
import model.modelComponent
import util.Event

import scala.util
import scala.util.{Failure, Success, Try}


class TUI(using controller: ControllerInterface) extends Template(controller):

  override def update(e: Event): Unit = e match
    case Event.Abort => sys.exit
    case Event.End => println(finalStats)
    case Event.Move =>
      println(controller.toString)
      println(controller.currentPlayer.name + "s turn")
      println("Enter your move <row>")

  override def start: Unit = gameLoop()

  override def finalStats: String =
    controller.toString + "\n" +
    controller.currentPlayer.name+" wins!!"
  

  override def gameLoop(): Unit =
    val player = controller.currentPlayer
    handleInput(readLine,player) match
      case None =>
      case Some(move) => controller.doAndPublish(controller.put, move)
    gameLoop()


  override def inputMatchAndToInt(input: String): Try[(Int,Int)] =
    val col = Try(Integer.parseInt(input))
    col match
      case Success(value) =>
        if value < controller.width then
          if controller.getRow(value) == -1 then
            Failure(new IllegalArgumentException("column is full"))
          else
            Success((value, controller.getRow(value)))
        else
          Failure(new IllegalArgumentException("not a row"))
      case Failure(exception) => Failure(exception)

  override def displayError(message: Throwable): Unit = println("incorrect input! "+message.getMessage)

  override def handleInput(input: String, player: PlayerInterface): Option[Move] =
    input match
      case "quit" => controller.abort; None
      case "redo" => controller.doAndPublish(controller.redo); None
      case "undo" => controller.doAndPublish(controller.undo); None
      case "save" => controller.save; None
      case "load" => controller.load; None
      case _ =>
        inputMatchAndToInt(input) match
          case Success(i) => Some(Move(player,i._1,i._2))
          case Failure(e) => displayError(e); None
