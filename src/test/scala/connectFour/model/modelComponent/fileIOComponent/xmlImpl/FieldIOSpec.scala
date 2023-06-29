/*package connectFour.model.modelComponent.fileIOComponent.xmlImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Stone}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FileIoSpec extends AnyWordSpec {
  "A field" when {
    val field: Field = new Field(3, 2, Stone.Empty)
    val grid = "+---+---+---+---+\n|   |   |\n+---+---+---+---+\n|   |   |\n+---+---+---+---+\n|   |   |\n+---+---+---+---+\n  0   1   2   3  "
    "saved to xml" should {
      val fileIO = new FileIO()
      "be equal when loaded" in {
        fileIO.save(field)
        fileIO.load should be(grid)
      }
    }
  }
}

 */