package connectFour.aview

import connectFour.controller.*
import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.*
import connectFour.model.modelComponent.fieldImpl
import connectFour.model.modelComponent.fieldImpl.{Move, Stone}
import connectFour.util.{Event, Observer}

import java.awt.{Color, Font, RenderingHints}
import javax.swing.{ImageIcon, SwingConstants, UIManager}
import scala.swing.*
import scala.swing.event.*

class GUI(controller: ControllerInterface) extends Frame with Observer:
  controller.add(this)

  val fieldSize: (Int,Int) = (7,6)

  val panelSize: Dimension = new Dimension(830,750)
  
  val stoneRed = ImageIcon("src/resources/StoneRed.png")
  val stoneYellow = ImageIcon("src/resources/StoneYellow.png")
  val stoneEmpty = ImageIcon("src/resources/StoneEmpty.png")
  val playerRed = ImageIcon("src/resources/PlayerRed.png")
  val playerYellow = ImageIcon("src/resources/PlayerYellow.png")

  title = "Connect Four"
  //iconImage =
  resizable = false

  menuBar = new MenuBar {
    contents += new Menu("") {
      //icon =      //Menu icon
      borderPainted = false
      contents += MenuItem(Action("Exit") {controller.abort})
      contents += MenuItem(Action("Undo") {controller.undo})
      contents += MenuItem(Action("Redo") {controller.redo})
      contents += MenuItem(Action("Save") {}) //save methode in controller
      contents += MenuItem(Action("Load") {}) //load methode in controller
    }

    override def paintComponent(g: Graphics2D): Unit =
      renderHints(g)
      super.paintComponent(g)
  }

  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)

  menuBar.border = Swing.EmptyBorder(5, 10, 0, 0)
  //menuBar.background
  update(Event.Move)
  centerOnScreen
  open()


  override def update(e: Event): Unit =
    e match
      case Event.Move => contents = revise(playerTurn); repaint
      case Event.End => contents = revise(playerResult); repaint
      case Event.Abort => sys.exit()


  override def closeOperation(): Unit = controller.abort

  def renderHints(d: Graphics2D): Unit =
    d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY)


  def revise(playerState: FlowPanel): BorderPanel = new BorderPanel {
    preferredSize = panelSize
    //background =
    add(playerState, BorderPanel.Position.North)
    add(CellPanel(fieldSize._1,fieldSize._2), BorderPanel.Position.Center)
  }

  def playerTurn: FlowPanel = new FlowPanel {
    //background =
    contents += new Label {
      icon = controller.currentPlayer.name match
        case "RED" => playerRed
        case "YELLOW" => playerYellow
    }
    val label = Label(" Turn")
    //label.foreground =
    //label.font = Font()
    contents += label

    override def paintComponent(g: Graphics2D) =
      renderHints(g)
      super.paintComponent(g)
  }

  def playerResult: FlowPanel = new FlowPanel {
    //background =
    //val fontType = Font()
    contents += new Label{
      icon = controller.currentPlayer.name match
        case "RED" => playerRed
        case "YELLOW" => playerYellow
    }
    val label = Label(" wins!!")
    //label.font = fontType
    //label.foreground =
    contents += label

    override def paintComponent(g: Graphics2D): Unit =
      renderHints(g)
      super.paintComponent(g)
  }

  class CellPanel(x: Int, y: Int) extends GridPanel(fieldSize._2,fieldSize._1):
    opaque = false
    fieldBuilder

    private def fieldBuilder =
      (0 until y).foreach { row =>
        (0 until x).foreach(col => bar(row, col))
      }

    private def bar(row: Int, col: Int) =
      contents += CellButton(row, col, controller.get(row, col))



  class CellButton(x: Int, y: Int, stone: Stone) extends Button:
    listenTo(mouse.moves, mouse.clicks)
    borderPainted = false
    focusPainted = false
    opaque = false
    builder

    private def builder =
      icon =  stone match
        case Stone.Red => stoneRed
        case Stone.Yellow => stoneYellow
        case Stone.Empty => stoneEmpty

    override def paintComponent(g: Graphics2D): Unit =
      renderHints(g)
      super.paintComponent(g)

    reactions += {
      case MouseClicked(source) =>
        controller.doAndPublish(controller.put, Move(controller.currentPlayer, x, y))
      //case MouseMoved(source) =>
      //case MouseExited(source) =>
    }