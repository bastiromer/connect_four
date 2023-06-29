/*package connectFour.model.modelComponent.fileIOComponent.jsonImpl

import connectFour.model.modelComponent.fieldImpl.{Field, Stone}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FileIoSpec extends AnyWordSpec {
  "A field" when {
    val field: Field = new Field(3, 2, Stone.Empty)
    "saved to xml" should {
      val fileIO = new FileO()
      "be equal when loaded" in {
        fileIO.save(field)
        fileIO.load should be(field)
      }
    }
  }
}

 */
