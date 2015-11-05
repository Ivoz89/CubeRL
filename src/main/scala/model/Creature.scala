package model

import model.CharAttribute._
import model.effect.creature.CreatureEffect

/**
 * Created by iozi on 04/11/2015.
 */
class Creature(val name : String, val attrs : Map[CharAttribute,Int], val effects : List[CreatureEffect])

object Creature {
  def apply(name : String, attrs : Map[CharAttribute,Int],effects : List[CreatureEffect]) = {
    new Creature(name,attrs,effects)
  }
}
