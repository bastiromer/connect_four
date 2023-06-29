package connectFour

import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Stone}
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import connectFour.model.modelComponent.fileIOComponent._

object Default:
  given FieldInterface = new Field(7,6,Stone.Empty)
  given ControllerInterface = Controller()
  given FileIOInterface = xmlImpl.FileIO()