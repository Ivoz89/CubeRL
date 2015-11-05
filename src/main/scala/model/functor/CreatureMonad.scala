package model.functor

import model.CharAttribute.CharAttribute
import model.effect.creature.CreatureEffect
import model.{Room, Creature}

/**
 * Created by iozi on 04/11/2015.
 */
class CreatureMonad(val value : Creature) {

  import value._

  def to(function: Creature => Creature) =  CreatureMonad(function(value))

  def modifyStat(attribute : CharAttribute, delta : Int) =
    CreatureMonad(Creature(name,attrs.updated(attribute,attrs.get(attribute).get+delta),effects))

  def modifyEffects(modification : (List[CreatureEffect] => List[CreatureEffect])) =
    CreatureMonad(Creature(name,attrs,modification(effects)))

  def get = value

}

object CreatureMonad {
  def apply(value : Creature) = {
    new CreatureMonad(value)
  }
}