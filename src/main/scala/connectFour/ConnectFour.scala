package connectFour

import connectFour.aview.{GUI, TUI}
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.modelComponent.fieldImpl.{Field, Stone}
import com.google.inject.Guice
import com.google.inject.name.Named
import connectFour.model.modelComponent.fileIOComponent.FileIOInterface
import connectFour.model.modelComponent.playerImpl.PlayerFactory

object ConnectFour:
  @main def run: Unit =
    val injector = Guice.createInjector(new ConnectFourModule)
    val field = new Field(7, 6, Stone.Empty, 
        Vector(PlayerFactory().createPlayer("RED", "red"),
               PlayerFactory().createPlayer("YELLOW", "yellow")))
    val controller = Controller(field,injector.getInstance[FileIOInterface])
    GUI(controller)
    TUI(controller).run
