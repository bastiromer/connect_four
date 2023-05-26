import org.scalatest._

import connectFour.model.{Field, Stone, HumanPlayer}
import connectFour.controller.{Controller,TUIMoveValidator}
import connectFour.util.Observer

class ControllerSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {

  "A Controller" should "correctly make moves on the field" in {
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field,TUIMoveValidator)
    controller.makeMove(new HumanPlayer(Stone.X,"1"), 0)
    controller.field.matrix.cell(5, 0) shouldEqual Stone.X
    controller.makeMove(new HumanPlayer(Stone.O,"1"), 0)
    controller.field.matrix.cell(4, 0) shouldEqual Stone.O
  }

  it should "print the field" in {
    val controller = Controller(new Field(3, 3, Stone.Empty),TUIMoveValidator)
    controller.toString should be(("""#+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #  0   1   2   3  """).stripMargin('#'))
  }


  it should "notify its observers on change" in {
    class TestObserver(controller: Controller) extends Observer {
      controller.add(this)
      var bing = false

      def update = bing = true
    }

    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field,TUIMoveValidator)
    val testObserver = TestObserver(controller)

    testObserver.bing should be(false)
    controller.makeMove(new HumanPlayer(Stone.X,"1"), 1)
    testObserver.bing should be(true)
  }
}
