package control.time.processing

import control.time.TurnProcessor
import model.Room

/**
 * Created by iozi on 09/11/2015.
 */
class FieldEffectProcessor extends TurnProcessor {

  override def processNextTurn() = (room : Room) => {

    Room(null)
  }
}
