package connectFour

import net.codingwell.scalaguice.ScalaModule
import com.google.inject.{AbstractModule, TypeLiteral}
import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.modelComponent.FieldInterface
import connectFour.model.modelComponent.fieldImpl.{Field, Stone}

class ConnectFourModule extends AbstractModule with ScalaModule:

  override def configure(): Unit =
    //bind(classOf[FieldInterface]).to(classOf[Field])
    //bind[FieldInterface].annotatedWithName("Field").toInstance(new Field(6,7,Stone.Empty))
    bind[FieldInterface].annotatedWithName("Field").toInstance(new Field(6,7,Stone.Empty))
    bind(classOf[ControllerInterface]).to(classOf[Controller])
    bind(new TypeLiteral[Vector[Vector[Stone]]]() {})
      .toInstance(Vector.empty[Vector[Stone]])
