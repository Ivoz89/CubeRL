package logic

import control.CreatureDecision
import model.Room

/**
 * Created by iozi on 04/11/2015.
 */
trait ActionLogicLink {

  def processDecision(action : CreatureDecision) : Room => Room
}
