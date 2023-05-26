package connectFour
package controller

import model.{Field, Move, PlayerFactory, Stone}
import util.Command
import util.UndoManager

class PutCommand(move: Move) extends Command[Field]:
  val p = new PlayerFactory().createPlayer("P", "start")
  override def noStep(field: Field): Field = field
  override def doStep(field: Field): Field = field.put(move.player, move.row, move.col)
  override def undoStep(field: Field): Field = field.put(p, move.row,move.col)
  override def redoStep(field: Field): Field = field.put(move.player, move.row,move.col)
