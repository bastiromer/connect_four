package connectFour.model.modelComponent.fileIOComponent

import connectFour.model.modelComponent.FieldInterface

trait FileIOInterface {
  def load: FieldInterface
  def save(field: FieldInterface): Unit
}