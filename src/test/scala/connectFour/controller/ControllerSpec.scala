import org.scalatest._

import connectFour.model.{Field, Stone}
import connectFour.controller.Controller
import connectFour.util.Observer

class ControllerSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {

  "A Controller" should "correctly make moves on the field" in {
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field)
    controller.makeMove(Stone.X, 0)
    controller.field.matrix.cell(5, 0) shouldEqual Stone.X
    controller.makeMove(Stone.O, 0)
    controller.field.matrix.cell(4, 0) shouldEqual Stone.O
  }

  it should "print the field" in {
    val controller = Controller(new Field(3, 3, Stone.Empty))
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
    val controller = Controller(field)
    val testObserver = TestObserver(controller)

    testObserver.bing should be(false)
    controller.makeMove(Stone.X, 1)
    testObserver.bing should be(true)
  }
}