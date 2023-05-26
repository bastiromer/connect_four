import org.scalatest.*
import connectFour.model.{Field, HumanPlayer, Stone}

class FieldSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {

  "A Field" should "have the correct dimensions" in {
    val field = new Field(7, 6, Stone.Empty)
    field.width shouldEqual 7
    field.height shouldEqual 6
  }

  it should "print the field" in {
    val field = new Field(3, 3, Stone.Empty)
    field.toString should be(("""#+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #|   |   |   |
                              #+---+---+---+---+
                              #  0   1   2   3  """).stripMargin('#'))
  }

  it should "correctly put a stone in the matrix" in {
    val field = new Field(7, 6, Stone.Empty)
    val newField = field.put(new HumanPlayer(Stone.X,"1"), 0,5)
    newField.matrix.cell(5, 0) shouldEqual Stone.X
  }

  it should "return the correct column index to put the stone in" in {
    val field1 = new Field(7, 6, Stone.Empty)
    val newField1 = field1.put(new HumanPlayer(Stone.X,"1"), 0,4)
    newField1.moveCorrect(0) shouldEqual 4
    newField1.put(new HumanPlayer(Stone.O,"1"), 0,3).moveCorrect(0) shouldEqual 3
    newField1.put(new HumanPlayer(Stone.X,"1"), 1,4).moveCorrect(0) shouldEqual 4
  }
}

