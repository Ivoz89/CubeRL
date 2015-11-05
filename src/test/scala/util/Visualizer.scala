package util

import model.Room
import model._
import model.FieldType._

/**
 * Created by iozi on 04/11/2015.
 */
object Visualizer {

  def visualizeRoom(room : Room): Unit = {
    import room.fields
    fields.foreach( v => {
      v.foreach(e => e match {
        case Field(Some(creature),_) => print("@")
        case Field(None,FLOOR) => print(".")
        case Field(None,WALL) => print("#")
      })
      print("\n")
    })
  }
}
