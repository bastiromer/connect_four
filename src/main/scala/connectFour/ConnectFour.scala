package connectFour

import connectFour.aview.{TUI,GUI}
import model.{Field, Stone}
import controller.Controller

object ConnectFour:
  @main def run: Unit =
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field)
    val tui = TUI(controller)
    GUI(controller).top
    tui.run
