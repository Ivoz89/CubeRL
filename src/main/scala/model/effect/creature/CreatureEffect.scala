package model.effect.creature

import model.Creature

/**
 * Created by iozi on 04/11/2015.
 */
trait CreatureEffect {

  def affect() : Creature => Creature
}
