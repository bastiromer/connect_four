package connectFour

import aview.TUI
import model.{Field, Stone}
import controller.Controller

object ConnectFour:
  @main def run: Unit =
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field)
    val tui = TUI(controller)
    tui.run
