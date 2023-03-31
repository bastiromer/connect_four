
import model.Field

object ConnectFour:

  def main(args: Array[String]): Unit =
    val field = new Field
    println("\u001B[32mWelcome to connect four!!")
    println("\u001B[31mPlayer1")
    println("\u001B[33mPlayer2\u001B[0m")
    print(field.printField())
