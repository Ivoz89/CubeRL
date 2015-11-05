package model.effect.field

import model.Field

/**
 * Created by iozi on 04/11/2015.
 */
trait FieldEffect {

  def effect() : Field => Field
}
