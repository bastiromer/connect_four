import model.Field

object ConnectFour:

  def main(args: Array[String]): Unit =
    val field = new Field
    println("Connect Four")
    print(field.printField())
