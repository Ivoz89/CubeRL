package model

/**
 * Created by iozi on 04/11/2015.
 */
class Room(val fields: Vector[Vector[Field]])

object Room {
  def apply(fields: Vector[Vector[Field]]) = {
    new Room(fields)
  }
}
