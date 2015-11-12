package model

import model.FieldType.FieldType
import model.effect.field.FieldEffect

/**
 * Created by iozi on 04/11/2015.
 */
case class Field(val visitor : Option[Creature], val kind : FieldType, val effects : List[FieldEffect])

object Field {
  def apply(visitor : Option[Creature], kind : FieldType, effects : List[FieldEffect]) =
    new Field(visitor,kind,effects)
}

