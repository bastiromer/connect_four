import model._

import scala.io.StdIn.readLine


object ConnectFour:


  @main def run: Unit =
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    val field = new Field(7, 6, Stone.Empty)
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