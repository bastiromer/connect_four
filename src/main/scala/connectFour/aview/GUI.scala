package connectFour.aview

import connectFour.controller.*
import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.*
import connectFour.model.modelComponent.fieldImpl
import connectFour.model.modelComponent.fieldImpl.{Move, Stone}
import connectFour.util.{Event, Observer}

import java.awt.{Color, Font, RenderingHints, Toolkit}
import javax.swing.{ImageIcon, SwingConstants, UIManager}
import scala.swing.*
import scala.swing.event.*

class GUI(using controller: ControllerInterface) extends Frame with Observer:
  controller.add(this)

  val fieldSize: (Int,Int) = (controller.width, controller.height)

  val panelSize: Dimension = new Dimension(830,750)

  val backgroundColor = Color(160,160,160)
  
  val stoneRed = ImageIcon("src/resources/StoneRed.png")
  val stoneRedhover = ImageIcon("src/resources/StoneRedhover.png")
  val stoneYellow = ImageIcon("src/resources/StoneYellow.png")
  val stoneYellowhover = ImageIcon("src/resources/StoneYellowhover.png")
  val stoneEmpty = ImageIcon("src/resources/StoneEmpty.png")
  val stoneEmptyhover = ImageIcon("src/resources/StoneEmptyhover.png")
  val playerRed = ImageIcon("src/resources/PlayerRed.png")
  val playerYellow = ImageIcon("src/resources/PlayerYellow.png")
  val menuIcon = ImageIcon("src/resources/MenuIcon.png")
  val logo: Image = Toolkit.getDefaultToolkit.getImage("src/resources/Logo.png")

  title = "Connect Four"
  iconImage = logo
  resizable = false

  menuBar = new MenuBar {
    contents += new Menu("") {
      icon = menuIcon
      borderPainted = false
      contents += MenuItem(Action("Exit") {controller.abort})
      contents += Separator()
      contents += MenuItem(Action("Undo") {controller.doAndPublish(controller.undo)})
      contents += MenuItem(Action("Redo") {controller.doAndPublish(controller.redo)})
      contents += Separator()
      contents += MenuItem(Action("Save") {controller.save})
      contents += MenuItem(Action("Load") {controller.load})
    }

    override def paintComponent(g: Graphics2D): Unit =
      renderHints(g)
      super.paintComponent(g)
  }

  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
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
    background = backgroundColor
    add(playerState, BorderPanel.Position.North)
    add(CellPanel(fieldSize._1,fieldSize._2), BorderPanel.Position.Center)
  }

  def playerTurn: FlowPanel = new FlowPanel {
    contents += new Label {
      icon = controller.currentPlayer.name match
        case "RED" => playerRed
        case "YELLOW" => playerYellow
    }
    val label = Label(" Turn")
    label.font = Font("Segoe Print", 1 , 24)
    contents += label

    override def paintComponent(g: Graphics2D) =
      renderHints(g)
      super.paintComponent(g)
  }

  def playerResult: FlowPanel = new FlowPanel {
    contents += new Label{
      icon = controller.currentPlayer.name match
        case "RED" => playerRed
        case "YELLOW" => playerYellow
    }
    val label = Label(" wins!!")
    label.font = Font("Segoe Print", 1 , 40)
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
        controller.doAndPublish(controller.put, Move(controller.currentPlayer, y, controller.getRow(y)))
      case MouseMoved(source) =>
        icon = stone match
          case Stone.Red => stoneRedhover
          case Stone.Yellow => stoneYellowhover
          case Stone.Empty => stoneEmptyhover
      case MouseExited(source) =>
        icon = stone match
          case Stone.Red => stoneRed
          case Stone.Yellow => stoneYellow
          case Stone.Empty => stoneEmpty
    }