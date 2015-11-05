package model

import model.FieldType.FieldType

/**
 * Created by iozi on 04/11/2015.
 */
case class Field(val visitor : Option[Creature], val kind : FieldType)

