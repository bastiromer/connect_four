package model

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case X extends Stone("1")
  case O extends Stone("2")
  case Empty extends Stone(" ")
