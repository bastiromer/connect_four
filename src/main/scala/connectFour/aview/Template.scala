package connectFour
package aview

import util.Observer
import controller.Controller
import model.{Move, Player}

abstract class Template(controller: Controller) extends Observer:
  controller.add(this)
  def run: Unit =
    start
    update
  def start: Unit  
  def gameLoop(player:Player): Unit
  def finalStat: String
  def aborted: Unit
