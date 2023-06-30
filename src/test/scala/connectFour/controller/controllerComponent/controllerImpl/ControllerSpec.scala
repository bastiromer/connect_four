package connectFour.controller.controllerComponent.controllerImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Move, Stone}
import connectFour.model.modelComponent.fileIOComponent.*
import connectFour.model.modelComponent.playerImpl.HumanPlayer
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class ControllerSpec extends AnyWordSpec:
  val controller = Controller(using new Field(7,6,Stone.Empty),new xmlImpl.FileIO())
  val player = new HumanPlayer(Stone.Red, "TestPlayer")
  "The Controller" should {
    "has the right width and height" in {
      controller.height should be(6)
      controller.width should be(7)
    }
    "put a stone in the field and notify observers" in {
      val move = Move(controller.currentPlayer,0,5)
      controller.doAndPublish(controller.put,move)
      controller.get(5,0) should be(Stone.Red)
    }
    "delete the Stone on undo and place it back on redo" in {
      val move = Move(player,0,5)
      val move1 = Move(player,1,5)
      controller.doAndPublish(controller.put,move)
      controller.doAndPublish(controller.put,move1)
      controller.get(5,1) should be(Stone.Red)
      controller.doAndPublish(controller.undo)
      controller.get(5,1) should be(Stone.Empty)
      controller.doAndPublish(controller.redo)
      controller.get(5,1) should be(Stone.Red)
    }
    "give the next free row from button to top" in {
      val move = Move(player, 0, 5)
      val move1 = Move(player, 0, 4)
      controller.doAndPublish(controller.put,move)
      controller.getRow(0) should be(4)
      controller.doAndPublish(controller.put,move1)
      controller.getRow(0) should be(3)
      println(controller.toString)
    }
    "return the current player" in {
      controller.currentPlayer should be(controller.currentPlayer)
      controller.changePlayer()
      controller.currentPlayer should be(controller.currentPlayer)
    }

    "should notify the Observer to abort" in {
      controller.abort
    }

    "should give the current Player" in {
      controller.currentPlayer should be(controller.currentPlayer)
    }
    "should save the score" in {
      controller.save should be(controller.load)
    }

    "change to WinState" in {
      val anotherController = Controller(using new Field(7, 6, Stone.Empty), new xmlImpl.FileIO())
      val state = new WinState
      val move = Move(anotherController.currentPlayer, 1, 1)
      println("1")
      anotherController.changeGameState(state)
      println("2")
      anotherController.gameState should be(state)
      println("3")
      anotherController.doAndPublish(anotherController.put, move)
      println("4")

    }
    "return a String field" in {
      val newController = Controller(using new Field(3, 3, Stone.Empty), new xmlImpl.FileIO())
      newController.toString should be(("""#+---+---+---+---+
                                    #|   |   |   |
                                    #+---+---+---+---+
                                    #|   |   |   |
                                    #+---+---+---+---+
                                    #|   |   |   |
                                    #+---+---+---+---+
                                    #  0   1   2   3  """).stripMargin('#'))
    }
  }
