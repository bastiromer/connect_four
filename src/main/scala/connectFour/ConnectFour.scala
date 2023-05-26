package connectFour

import connectFour.aview.TUI
import model.{Field, Stone}
import controller.{Controller, TUIMoveValidator}

object ConnectFour:
  @main def run: Unit =
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field,TUIMoveValidator)
    val tui = TUI(controller)
    tui.run
