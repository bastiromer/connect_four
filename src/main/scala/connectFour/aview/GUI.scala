package connectFour.aview

import connectFour.controller.*
import connectFour.model.*
import connectFour.util.{Event, Observer}

import java.awt.{Color, Font}
import javax.swing.SwingConstants
import scala.swing.*
import scala.swing.event.*

class GUI(controller: Controller) extends Frame with Observer{
  controller.add(this)

  val ROWS = 6
  val COLUMNS = 7

  val buttonSize = new Dimension(80, 80)
  val buttons = Array.ofDim[Button](ROWS, COLUMNS)

  val textArea = new TextArea(rows = 1, columns = COLUMNS) {
    font = new Font("Arial", Font.BOLD,24)
    editable = true
    lineWrap = true
    wordWrap = true
  }

  def createButton(row: Int, col: Int): Button = {
    val button = new Button
    button.background = Color.white
    button.preferredSize = buttonSize
    button.maximumSize = buttonSize
    button.minimumSize = buttonSize
    button.reactions += {
      case ButtonClicked(_) =>
        controller.doAndPublish(controller.put,Move(controller.currentPlayer,col,controller.getCol(col)))
    }
    button
  }

  def top: Frame = new MainFrame {
    title = "Grid Example"
    menuBar = new MenuBar {
      contents += new Menu("Menu") {
        contents += new MenuItem(Action("Exit") {
          sys.exit(0)
        })
        contents += new Separator
        contents += new MenuItem(Action("Undo") {
          controller.doAndPublish(controller.undo)
        })
        contents += new MenuItem(Action("Redo") {
          controller.doAndPublish(controller.redo)
        })
      }
    }

    textArea.text = controller.currentPlayer.name +"s turn"
    contents = new BorderPanel {
      add(textArea, BorderPanel.Position.North)
      add(new GridPanel(ROWS, COLUMNS) {
        for {
          row <- 0 until ROWS
          col <- 0 until COLUMNS
        } {
          val button = createButton(row, col)
          buttons(row)(col) = button
          contents += button
        }
      }, BorderPanel.Position.Center)
    }
    centerOnScreen()
    pack()
    open
  }

  def redraw: Unit =
    textArea.text = controller.currentPlayer.name +"s turn"
    for {
      row <- 0 until ROWS
      col <- 0 until COLUMNS
    } {
      controller.field.matrix.rows(row)(col) match {
        case Stone.X => buttons(row)(col).background = Color.RED
        case Stone.O => buttons(row)(col).background = Color.YELLOW
        case Stone.Empty => buttons(row)(col).background = Color.white
      }
    }


  def finalStats: Unit =
    redraw
    Dialog.showMessage(this,controller.currentPlayer.name+" hat gewonnen!",title = "Gewonnen")
    sys.exit()


  override def update(event: Event): Unit = event match
    case Event.Abort => sys.exit
    case Event.End   => finalStats
    case Event.Move  => redraw

}
