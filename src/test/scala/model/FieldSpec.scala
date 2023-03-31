package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class FieldSpec extends AnyWordSpec:

  "connectFour" should {
    "have a bar as String of form '+---+---+---+---+---+---+---+'" in {
      bar() should be("+---+---+---+---+---+---+---+" + eol)
    }

    "have a scalable bar" in {
      bar(1, 1) should be("+-+" + eol)
      bar(1, 2) should be("+-+-+" + eol)
      bar(5, 3) should be("+---+---+---+---+---+" + eol)
    }

    "have cells as String of form '|   |   |   |   |   |   |   |'" in {
      cells() should be("|   |   |   |   |   |   |   |" + eol)
    }

    "have scalable cells" in {
      cells(1, 1) should be("| |" + eol)
      cells(1, 2) should be("| | |" + eol)
      cells(5, 3) should be("|   |   |   |   |   |" + eol)
    }


    "have a printed field in form of \n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+---+---+\n" in {
      printfield(3, 3, 3) should be(
        "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n" +
          "|   |   |   |\n" +
          "+---+---+---+\n")
    }
  }
