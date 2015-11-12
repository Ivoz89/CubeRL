package control.time

import control.time.processing.CreatureEffectProcessor
import model.effect.creature.{TimedEffect, CreatureEffect}
import model.functor.RoomMonad
import model.{Creature, Room}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import util.{RoomFactory, CreatureBuilder}
import model.CharAttribute._

/**
 * Created by iozi on 05/11/2015.
 */
class CreatureEffectProcessorSpec extends FlatSpec with Matchers with BeforeAndAfter {

  var processor : CreatureEffectProcessor = _
  var creature : Creature = _
  var room : Room = _
  var initialHP : Int = _
  var attrDeltaPerTurn : Int = _
  var damagingEffect : CreatureEffect = _

  before {
    processor = new CreatureEffectProcessor
    initialHP = 100
    attrDeltaPerTurn = -10
    damagingEffect = new TimedEffect(HP,attrDeltaPerTurn,1)
    creature = CreatureBuilder().withName("test").addAttribute(HP,100).addEffect(damagingEffect).get
    room = Room(RoomFactory.getRoom(10,10,5,5,creature))
  }

  "CreatureEffectProcessor" should "execute without exceptions" in {
    processor.processNextTurn()(room)
  }

  it should "damage the creature after one turn" in {
    RoomMonad(room).to(processor.processNextTurn()).getInhabitants(0).
      attrs.get(HP).get should be (initialHP + attrDeltaPerTurn)
  }

  it should "not damage the creature after the effect has expired" in {
    RoomMonad(room).to(processor.processNextTurn()).to(processor.processNextTurn()).getInhabitants(0).
      attrs.get(HP).get should be (initialHP + attrDeltaPerTurn)
  }

  it should "remove the effect after it is expired" in {
    RoomMonad(room).to(processor.processNextTurn()).to(processor.processNextTurn()).getInhabitants(0).
      effects.size should be (0)
  }
}
