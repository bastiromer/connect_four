package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec:
  val field = new Field()

  "connectFour" should {
    "have a bar as String of form '+---+---+---+---+---+---+---+'" in {
      field.bar() should be("+---+---+---+---+---+---+---+\n")
    }

    "have a scalable bar" in {
      field.bar(1, 1) should be("+-+\n")
      field.bar(1, 2) should be("+-+-+\n")
      field.bar(3, 5) should be("+---+---+---+---+---+\n")
    }

    "have cells as String of form '|   |   |   |   |   |   |   |'" in {
      field.cells() should be("|   |   |   |   |   |   |   |\n")
    }

    "have scalable cells" in {
      field.cells(1, 1) should be("| |\n")
      field.cells(1, 2) should be("| | |\n")
      field.cells(3, 5) should be("|   |   |   |   |   |\n")
    }


    "have a printed field in form of \n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" in {
      field.printField(3, 3, 3) should be(
        "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n")
    }
  }
