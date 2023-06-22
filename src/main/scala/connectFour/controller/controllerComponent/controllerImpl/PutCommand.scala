package connectFour
package controller.controllerComponent.controllerImpl

import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Move}
import connectFour.model.modelComponent.playerImpl.PlayerFactory
import connectFour.util.{Command, UndoManager}


class PutCommand(move: Move) extends Command[FieldInterface]:
  val p = new PlayerFactory().createPlayer("P", "start")
  override def doStep(field: FieldInterface): FieldInterface = field.put(move.player, move.row, move.col)
  override def undoStep(field: FieldInterface): FieldInterface = field.put(p, move.row,move.col)
  override def redoStep(field: FieldInterface): FieldInterface = field.put(move.player, move.row,move.col)