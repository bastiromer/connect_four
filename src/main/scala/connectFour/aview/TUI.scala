package connectFour
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Stone
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)

  override def update: Unit = controller.toString

  def run =
    println(controller.field.toString)
    getInputAndPrintLoop()


  def getInputAndPrintLoop(): Unit =
    println("Enter yout move <player><row>")
    val input = readLine
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val stone = chars(0) match
          case '1' => Stone.X
          case '2' => Stone.O
          case _ => Stone.Empty
        val row = chars(1).toString.toInt
        controller.makeMove(stone, row)
        println(controller.toString)
        getInputAndPrintLoop()
      }
      