package connectFour

import connectFour.aview.{GUI, TUI}
import connectFour.controller.controllerComponent.controllerImpl.Controller
import connectFour.model.modelComponent.modelImpl.{Field, Stone}
import com.google.inject.Guice
import com.google.inject.name.Named

object ConnectFour:
  @main def run: Unit =
    val injector = Guice.createInjector(new ConnectFourModule)
    val field = new Field(7, 6, Stone.Empty)
    val controller = Controller(field)
    GUI(controller).top;TUI(controller).run
