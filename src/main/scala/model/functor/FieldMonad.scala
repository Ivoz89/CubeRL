package model.functor

import model.effect.creature.CreatureEffect
import model.effect.field.FieldEffect
import model.{Creature, Field}

/**
 * Created by iozi on 04/11/2015.
 */
class FieldMonad(value : Field) {

  import value._

  /**
   * Maps
   * @param function
   * @return field
   */
  def to(function: Field => Field) = {
    FieldMonad(function(value))
  }

  /**
   * Checks if field satisfies a condition
   * @param predicate
   * @return
   */
  def assert(predicate: Field => Boolean) = predicate(value)

  /**
   * Applies modifying function to the visitor
   * @param mapping
   * @return
   */
  def modifyVisitor(mapping : Creature => Creature) = FieldMonad(Field(Some(mapping(visitor.get)),kind,effects))

  /**
   *
   * @param modification lambda mapping old list to a new one
   * @return
   */
  def modifyEffects(modification : (List[FieldEffect] => List[FieldEffect])) =
    FieldMonad(Field(visitor,kind,modification(effects)))

  def get = value
}

object FieldMonad {
  def apply(value : Field) = {
    new FieldMonad(value)
  }
}
