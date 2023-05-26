package connectFour
package aview

import scala.io.StdIn.readLine
import controller.{Controller, TUIMoveValidator}
import model.{Player, PlayerFactory, Stone}
import util.Observer

class TUI(controller: Controller) extends Template(controller) :
  controller.add(this)

  override def update: Unit = controller.toString

  override def finalStat: String =
    controller.toString

  override def aborted: Unit =
    println("\nGame was aborted\n")
    sys.exit()

  override def gameLoop(player: Player): Unit =
    getInputAndPrintLoop(player = p1)

  def start: Unit =
    initializePlayers()
    println(controller.field.toString)
    getInputAndPrintLoop()

  def initializePlayers(): Unit =
    val factory = new PlayerFactory()
    println("Name Spieler 1")
    p1 = factory.createPlayer(readLine(),"red")
    println("Name Spieler 2")
    p2 = factory.createPlayer(readLine(), "yellow")

  def getInputAndPrintLoop(player: Player = p1): Unit =
    println(player.name + "s turn")
    println("Enter your move <row> (or q for quit)")
    val input = readLine
    //val move = controller.handleInput(player, input)
    input match
      case "q" => aborted
      case _ => {
        val row = input.toInt
        //controller.makeMove(player, row)
        println(controller.toString)
        getInputAndPrintLoop(if player == p1 then p2 else p1)
      }
  var p1: Player = new PlayerFactory().createPlayer(" ", "start")
  var p2: Player = new PlayerFactory().createPlayer(" ", "start")