package connectFour.model

import connectFour.model.modelComponent.fieldImpl.Matrix
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of something. A Matrix" when {
    "empty " should {
      "be created by using a dimention and a sample cell" in {
        val matrix = new Matrix[String](2,2, "x")
        matrix.height should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector("x")))
        testMatrix.height should be(1)
      }

    }
    "filled" should {
      val matrix = new Matrix[String](2,2, "x")
      "give access to its cells" in {
        matrix.cell(0, 0) should be("x")
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, "o")
        matrix.cell(0, 0) should be("x")
        returnedMatrix.cell(0, 0) should be("o")
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill("x")
        returnedMatrix.cell(0, 0) should be("x")
      }
      "replacing a cell" should {
        "return a new Matrix with the updated value" in {
          val matrix = Matrix(Vector(Vector(1, 2), Vector(3, 4)))
          val updatedMatrix = matrix.replaceCell(0, 0, 5)
          updatedMatrix should not be theSameInstanceAs(matrix)
          updatedMatrix.cell(0, 0) should be(5)
          updatedMatrix.cell(0, 1) should be(2)
          updatedMatrix.cell(1, 0) should be(3)
          updatedMatrix.cell(1, 1) should be(4)
        }
      }
    }
  }

}
