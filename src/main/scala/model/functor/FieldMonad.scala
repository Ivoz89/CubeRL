package model.functor

import model.{Creature, Field}

/**
 * Created by iozi on 04/11/2015.
 */
class FieldMonad(value : Field) {

  def to(function: Field => Field) = {
    FieldMonad(function(value))
  }

  def assert(predicate: Field => Boolean) = predicate(value)

  def changeVisitor(mapping : Creature => Creature) = FieldMonad(Field(Some(mapping(value.visitor.get)),value.kind))

  def get = value
}

object FieldMonad {
  def apply(value : Field) = {
    new FieldMonad(value)
  }
}
