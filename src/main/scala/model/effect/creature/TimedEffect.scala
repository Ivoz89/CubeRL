package model.effect.creature

import model.CharAttribute.CharAttribute
import model.Creature
import model.functor.CreatureMonad

import scala.collection.immutable.::

/**
 * Created by iozi on 04/11/2015.
 */
class TimedEffect(attribute : CharAttribute, delta : Int, duration : Int) extends CreatureEffect{

  override def affect() = (creature : Creature) => {
    checkExpiration(CreatureMonad(creature).modifyStat(attribute,delta))

    }

  def checkExpiration(monad : CreatureMonad) = {
    if (duration==0)
      monad.modifyEffects(dropOne(this))
    else
      monad.modifyEffects(dropOne(this))
  }

  def dropOne[A](elem :A)(ls : List[A]) : List[A] = ls match {
    case head :: tail if head == elem => tail
    case head :: tail => head :: dropOne(tail,elem)
    case _ => _
  }

  def replaceOne[A](elem :A)(ls : List[A]) : List[A] = ls match {
    case head :: tail if head == elem => elem :: tail
    case head :: tail => head :: replaceOne(tail,elem)
    case _ => _
  }

}
