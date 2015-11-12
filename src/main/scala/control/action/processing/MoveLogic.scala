package control.action.processing

import control.action.{ActionLogicLink, MovementAction}
import control.{CreatureDecision, Direction}
import model.FieldType._
import model.functor.RoomMonad
import model.{Field, Room}

/**
 * Created by iozi on 04/11/2015.
 */
class MoveLogic extends ActionLogicLink {

  override def processDecision(decision: CreatureDecision): (Room) => Room = decision.action match {
    case MovementAction(direction) => (room: Room) => {
      import decision.creature
      val creatureCoordinates = {
        (for (h <- 0 until room.fields.length;
              w <- 0 until room.fields(0).length) yield (h, w)).
          filter(pair => room.fields(pair._1)(pair._2).visitor.getOrElse(null) == creature).head
      }

      def remove(f: Field) = f match {
        case Field(visitor, kind) => new Field(None, kind)
      }

      def put(f: Field) = f match {
        case Field(None, kind) => new Field(Some(decision.creature), kind)
      }

      def moveCreature(from: (Int, Int)) = {
        direction match {
          case Direction(dy, dx) if room.fields(from._1 + dy)(from._2 + dx).kind == FLOOR
            => RoomMonad(room).mapField(from)(remove).mapField((from._1 + dy, from._2 + dx))(put).get
          case _ => room //TODO log unsuccessful moves
        }
      }

      moveCreature(creatureCoordinates)
    }
  }
}