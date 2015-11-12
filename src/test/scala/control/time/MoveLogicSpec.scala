package control.time

import control._
import control.action.MovementAction
import control.action.processing.MoveLogic
import model._
import model.functor.RoomMonad
import org.scalatest._
import util.{CreatureBuilder, Visualizer, RoomFactory}

/**
 * Created by iozi on 04/11/2015.
 */
class MoveLogicSpec extends FlatSpec with Matchers with BeforeAndAfter {

  var initialX : Int=  _
  var initialY : Int = _
  var moveLogic: MoveLogic = _
  var fields: Vector[Vector[Field]] = _
  var creature: Creature = _

  before {
    initialX = 5;
    initialY = 5;
    moveLogic = new MoveLogic
    fields = RoomFactory.getRoom(10, 10, initialY, initialX,CreatureBuilder().get)
    creature = fields.flatMap(v => v.map(e => e.visitor)).flatten.head
  }

  "Move control.logic" should "move a creature from one field to another" in {
    moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(NORTH)))(Room(fields))
  }

  it should "move creature in the correct direction " in {
    List(NORTH, SOUTH, WEST, EAST, SOUTHEAST, SOUTHWEST, NORTHWEST, NORTHEAST).foreach(dir => {
      val result = RoomMonad(Room(fields)).to(moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(dir))))
      result.getField((initialY + dir.dy, initialX + dir.dx)).assert(_.visitor.isDefined) should be (true)
      result.getField((initialY, initialX)).assert(_.visitor.isDefined) should be (false)
    })
  }

  it should "move creature in the correct direction multiple times if necessary " in {
    List(NORTH, SOUTH, WEST, EAST, SOUTHEAST, SOUTHWEST, NORTHWEST, NORTHEAST).foreach(dir => {
      val result = RoomMonad(Room(fields)).to(moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(dir)))).
        to(moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(dir))))
      result.getField((initialY + 2 * dir.dy, initialX + 2 * dir.dx)).assert(_.visitor.isDefined) should be (true)
      result.getField((initialY, initialX)).assert(_.visitor.isDefined) should be (false)
    })
  }

  it should "stop creature from moving into a wall" in {
    val initialX = 1;
    val initialY = 1;
    fields = RoomFactory.getRoom(10, 10, initialY, initialX,CreatureBuilder().get)
    creature = fields.flatMap(v => v.map(e => e.visitor)).flatten.head
    List(NORTH, WEST, NORTHWEST, NORTHEAST).foreach(dir => {
      val result = RoomMonad(Room(fields)).to(moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(dir)))).
        to(moveLogic.processDecision(new CreatureDecision(creature, new MovementAction(dir))))
      Visualizer.visualizeRoom(result.get)
      result.getField((initialY + dir.dy, initialX + dir.dx)).assert(_.visitor.isDefined) should be (false)
      result.getField((initialY, initialX)).assert(_.visitor.isDefined) should be (true)
    })
  }
}
