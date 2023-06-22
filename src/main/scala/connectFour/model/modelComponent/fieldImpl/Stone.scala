package connectFour.model.modelComponent.fieldImpl

import org.scalactic.Prettifier.default

val reset = "\u001b[0m"
val red = "\u001b[31m"
val yellow = "\u001b[33m"
enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case X extends Stone(s"${red}●${reset}")
  case O extends Stone(s"${yellow}●${reset}")
  case Empty extends Stone(" ")
  