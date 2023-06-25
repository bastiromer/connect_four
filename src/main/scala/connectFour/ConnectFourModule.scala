package connectFour

import net.codingwell.scalaguice.ScalaModule
import com.google.inject.{AbstractModule, TypeLiteral}
import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Stone}
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import connectFour.model.modelComponent.fileIOComponent.xmlImpl.FileIO
import connectFour.model.modelComponent.playerImpl.PlayerFactory

class ConnectFourModule extends AbstractModule with ScalaModule:

  override def configure(): Unit =
    bind[FieldInterface].annotatedWithName("Field").toInstance(new Field(7, 6, Stone.Empty,
              Vector(PlayerFactory().createPlayer("ROT", "red"),
                     PlayerFactory().createPlayer("YELLOW", "yellow"))))
    bind(classOf[ControllerInterface]).to(classOf[Controller])
    bind(new TypeLiteral[Vector[Vector[Stone]]]() {})
      .toInstance(Vector.empty[Vector[Stone]])
    bind[FileIOInterface].toInstance[FileIO]
