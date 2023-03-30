
import model.Field

object ConnectFour:

  def main(args: Array[String]): Unit =
    val field = new Field
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    print(field.printField())
