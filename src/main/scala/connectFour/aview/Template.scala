package connectFour
package aview

import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.model.modelComponent.modelImpl.{Move, Player}
import util.{Event, Observer}

abstract class Template(controller: ControllerInterface) extends Observer:
  controller.add(this)
  def run: Unit =
    update(Event.Move)
    start
    
  def start: Unit  
  def gameLoop(): Unit
  def finalStats: String
  def aborted: Unit
  
  //restlichen Methoden der TUI einfügen
