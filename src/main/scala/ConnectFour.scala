import aview.TUI
import model.Stone
import model.Field

import scala.io.StdIn.readLine

object ConnectFour:

  @main def run: Unit =
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    val field = new Field(7, 6, Stone.Empty)
    val tui = TUI(field)
    tui.run
