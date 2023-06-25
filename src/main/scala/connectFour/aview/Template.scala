package connectFour
package aview

import connectFour.controller.controllerComponent.ControllerInterface
import connectFour.model.modelComponent.PlayerInterface
import connectFour.model.modelComponent.fieldImpl.Move
import util.{Event, Observer}

import scala.util.Try

abstract class Template(controller: ControllerInterface) extends Observer:
  controller.add(this)
  def run: Unit =
    println("Welcome to Connect Four!")
    update(Event.Move)
    start
    
  def start: Unit  
  def gameLoop(): Unit
  def finalStats: String
  def handleInput(input: String, player: PlayerInterface): Option[Move]
  def inputMatchAndToInt(str: String): Try[(Int, Int)]
  def displayError(message: Throwable): Unit = println("incorrect input!")