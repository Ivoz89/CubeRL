package util

import model._
import model.FieldType._

/**
 * Created by iozi on 04/11/2015.
 */
object RoomFactory {

  def getRoom(x : Int, y : Int, px : Int, py : Int, creature: Creature) = {
    List.range(0,x).foldLeft(Vector[Vector[Field]]())((vecs,i) =>
      vecs :+ List.range(0,y).foldLeft(Vector[Field]())( (vec, j) => (i,j) match {
        case (a,b) if a == py && b == px => vec :+ Field(Some(creature),FLOOR)
        case (0,_) | (_,0)  => vec :+ Field(None,WALL)
        case (a,b) if a==y-1 || b==x-1 => vec :+ Field(None,WALL)
        case _ => vec :+ Field(None,FLOOR)
      }))
  }

}
