package model.effect.creature

import model.CharAttribute.CharAttribute
import model.Creature
import model.functor.CreatureMonad
import util.Utils._

/**
 * Created by iozi on 04/11/2015.
 */
class TimedEffect(attribute : CharAttribute, delta : Int, duration : Int) extends CreatureEffect{

  override def affect() = (creature : Creature) =>
    checkExpiration(CreatureMonad(creature).modifyStat(attribute,delta)).get

  def checkExpiration(monad : CreatureMonad) = {
    if (duration==1)
      monad.modifyEffects(updateElement(this,(elem, list) => list))
    else
      monad.modifyEffects(updateElement(TimedEffect(attribute,delta,duration-1),_ :: _))
  }
}

object TimedEffect {
  def apply(attribute : CharAttribute, delta : Int, duration : Int) = {
    new TimedEffect(attribute,delta,duration)
  }
}
