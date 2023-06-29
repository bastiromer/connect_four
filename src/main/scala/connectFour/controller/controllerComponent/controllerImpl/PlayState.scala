package connectFour.controller.controllerComponent.controllerImpl
import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.Move
import connectFour.util.UndoManager

class PlayState extends GameState:
  override def makeMove(undoManager: UndoManager[FieldInterface], field: FieldInterface, move: Move): Option[FieldInterface] =
    Some(undoManager.doStep(field, PutCommand(move)))
