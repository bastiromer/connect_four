package aview

import scala.io.StdIn.readLine

import model.Field
import model.Stone

class TUI(field: Field):

  def run =
    println(field.toString)
    getInputAndPrintLoop(field)


  def getInputAndPrintLoop(field: Field): Unit =
    println("Enter yout move <player><row>")
    val input = readLine
    parseInput(input, field) match
      case None => field
      case Some(newfield) =>
        println(newfield)
        getInputAndPrintLoop(newfield)

  def parseInput(input: String, field: Field): Option[Field] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val stone = chars(0) match
          case '1' => Stone.X
          case '2' => Stone.O
          case _ => Stone.Empty
        val x = chars(1).toString.toInt
        Some(field.put(stone, x))
      }
