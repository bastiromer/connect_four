import model._

object ConnectFour:

  def main(args: Array[String]): Unit =
    var field = new Field(size = 3, filling = Stone.Empty)
    println("\u001B[31mWelcome to connect four!!\u001B[0m")
    print(field.toString)