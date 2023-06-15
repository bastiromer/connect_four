package connectFour
package aview

import util.{Observer,Event}
import controller.Controller
import model.{Move, Player}

abstract class Template(controller: Controller) extends Observer:
  controller.add(this)
  def run: Unit =
    update(Event.Move)
    start
    
  def start: Unit  
  def gameLoop(): Unit
  def finalStats: String
  def aborted: Unit
