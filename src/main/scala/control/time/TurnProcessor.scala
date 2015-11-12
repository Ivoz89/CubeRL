package control.time

import model.Room

/**
 * Created by iozi on 04/11/2015.
 */
trait TurnProcessor {

  def processNextTurn() : Room => Room
}
