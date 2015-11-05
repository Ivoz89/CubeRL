package logic

import model._
import model.functor.{FieldMonad, CreatureMonad, RoomMonad}

/**
 * Created by iozi on 05/11/2015.
 */
class CreatureEffectProcessor extends TurnProcessor {

  def processCreatureEffects(creature: Creature) : Creature = creature.effects.foldLeft(CreatureMonad(creature))((a,e) => a.to(e.affect())).get

  override def processNextTurn() = (room : Room) => {
    RoomMonad(room).to((room : Room) => {
      val fields = room.fields.map(_.map( (f : Field) => f match  {
        case f if f.visitor.isDefined => FieldMonad(f).to( (f : Field) => FieldMonad(f).changeVisitor(processCreatureEffects).get).get
        case _ => f
      }))
      Room(fields)
    }).get
  }
}
