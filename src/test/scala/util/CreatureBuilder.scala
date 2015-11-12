package util

import model.CharAttribute.CharAttribute
import model.Creature
import model.effect.creature.CreatureEffect

/**
 * Created by iozi on 04/11/2015.
 */
class CreatureBuilder(creature: Creature) {

  import creature._

  def get = creature

  def addEffect(effect: CreatureEffect) = {
    CreatureBuilder(Creature(name, attrs, effect :: effects))
  }

  def addAttribute(charAttribute: CharAttribute, value: Int) = {
    CreatureBuilder(Creature(name, attrs.updated(charAttribute, value), effects))
  }

  def withName(newName : String) = {
    CreatureBuilder(Creature(newName, attrs, effects))
  }
}

object CreatureBuilder {
  def apply(creature: Creature) = {
    new CreatureBuilder(creature)
  }

  def apply() = {
    new CreatureBuilder(Creature("test", Map(), List()))
  }
}