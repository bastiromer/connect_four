package model

class Field():

  def printField(height: Int = 6, width: Int = 3, cellNum: Int = 7): String =
    (bar(width, cellNum) + cells(width, cellNum)) * height + bar(width, cellNum)

  def bar(width: Int = 3, cellNum: Int = 7): String =
    (("+" + "-" * width) * cellNum) + "+" + "\n"

  def cells(width: Int = 3, cellNum: Int = 7): String =
    ("|" + " " * width) * cellNum + "|" + "\n"
