package control.action

import control.CreatureDecision
import model.Room

/**
 * Link in the chain responsible for verification and application of CreatureDecisions
 * Created by iozi on 04/11/2015.
 */
trait ActionLogicLink {

  def processDecision(action : CreatureDecision) : Room => Room
}
