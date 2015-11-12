package model.effect.field

import model.Field
import model.effect.creature.CreatureEffect
import model.functor.{CreatureMonad, FieldMonad}
import util.Utils._

/**
 * Created by iozi on 06/11/2015.
 */
class EffectApplyingFieldEffect(creatureEffect: CreatureEffect, duration: Int) extends FieldEffect {

  /**
   * Adds effect to the visitor on the field
   * @return
   */
  override def effect() = ( f : Field) => {
    if (duration == 1)
      FieldMonad(f).modifyEffects(updateElement(this,(elem, list) => list)).get
    else
      FieldMonad(f).modifyVisitor(CreatureMonad(_).modifyEffects(creatureEffect :: _).get).get
  }
}
