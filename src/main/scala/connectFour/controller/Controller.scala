package connectFour
package controller


import connectFour.model.Field
import model.Stone
import util.Observable

case class Controller(var field: Field) extends Observable:
  def makeMove(stone: Stone, row: Int): Unit =
    field = field.put(stone, row)
    notifyObservers
    
  override def toString: String = field.toString
