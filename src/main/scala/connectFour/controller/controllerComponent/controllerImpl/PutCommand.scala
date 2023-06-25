package connectFour
package controller.controllerComponent.controllerImpl

import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Move}
import connectFour.model.modelComponent.playerImpl.PlayerFactory
import connectFour.util.{Command, UndoManager}


class PutCommand(move: Move) extends Command[FieldInterface]:
  val p = new PlayerFactory().createPlayer("P", "empty")
  override def doStep(field: FieldInterface): FieldInterface = move.player.move(field, move).get
  override def undoStep(field: FieldInterface): FieldInterface = p.move(field, move).get
  override def redoStep(field: FieldInterface): FieldInterface = move.player.move(field, move).get
